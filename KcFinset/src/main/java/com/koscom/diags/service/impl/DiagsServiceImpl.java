package com.koscom.diags.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.diags.dao.DiagsMapper;
import com.koscom.diags.model.DiagsReport;
import com.koscom.diags.model.DiagsResult;
import com.koscom.diags.model.DiagsStockReport;
import com.koscom.diags.model.StockGoalExt;
import com.koscom.diags.model.analysis.FactorAnalysis;
import com.koscom.diags.model.analysis.RiskAnalysis;
import com.koscom.diags.model.analysis.SectorAnalysis;
import com.koscom.diags.model.analysis.SectorExposure;
import com.koscom.diags.model.analysis.StockAnalysis;
import com.koscom.diags.model.analysis.StockRisk;
import com.koscom.diags.model.analysis.StockSector;
import com.koscom.diags.service.DiagsService;
import com.koscom.diags.service.StockGoalService;

@Service
public class DiagsServiceImpl implements DiagsService {

	private static final Logger logger = LoggerFactory.getLogger(DiagsServiceImpl.class);

	@Autowired
	private DiagsMapper diagsMapper;
	
	@Autowired
	private StockGoalService stockGoalService;

	@Override
	public DiagsResult diagsResult(String noPerson) {
		if(noPerson==null) return null;
		
		return diagsMapper.getDiagsResult(noPerson);
	}

	@Override
	public DiagsReport diagsReport(String noPerson) {
		if(noPerson==null) return null;
		
		FactorAnalysis factorAnalysis = getFactorAnalysis(noPerson);
		
		// 보유종목조회 
		List<StockGoalExt> myStocks = stockGoalService.getGoals(noPerson);
		if(myStocks==null) {
			logger.error("no my stocks for noPerson={}", noPerson);
			return null;
		}

		RiskAnalysis riskAnalysis = makeRiskAnalysis(noPerson, myStocks);
		
		SectorAnalysis sectorAnalysis = makeSectorAnalysis(noPerson, myStocks);
		
		DiagsReport report = new DiagsReport();
		report.setFactorAnalysis(factorAnalysis);
		report.setRiskAnalysis(riskAnalysis);
		report.setSectorAnalysis(sectorAnalysis);
		return report;
	}

	private FactorAnalysis getFactorAnalysis(String noPerson) {
		if(noPerson==null) return null;
		
		return diagsMapper.factorAnalysis(noPerson);
	}
	
	private RiskAnalysis makeRiskAnalysis(String noPerson, List<StockGoalExt> myStocks) {
		if(noPerson==null || myStocks==null) return null;
		
		// 보유종목 리스크 조회 
		List<StockRisk> myStockRisks = diagsMapper.getStockRisks(noPerson);
		if(myStockRisks==null || myStockRisks.size()<1) {
			logger.error("not found my stock risks for noPerson={}", noPerson);
			return null;
		}
		
		StockRisk portfolioRisk = computePortfolioRisk(myStocks, myStockRisks);
		
		StockRisk kospi200Risk = diagsMapper.getKospi200Risk();
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		riskAnalysis.setComment( makeRiskComment(portfolioRisk.getRiskRate(), kospi200Risk.getRiskRate()) );
		riskAnalysis.setRiskReturnComment( makeRiskReturnComment(portfolioRisk, kospi200Risk) );
		
		riskAnalysis.setStockRisks(myStockRisks);
		
		return riskAnalysis;
	}
	private StockRisk computePortfolioRisk(List<StockGoalExt> myStocks, List<StockRisk> myStockRisks) {
		if(myStocks==null || myStockRisks==null) return null;
		
		// 보유주식 총수 
		float total=0f;
		for(StockGoalExt s : myStocks) {
			total += s.getQty();
		}
		if(total==0) {
			logger.info("보유수량 없음");
			return null;
		}
		logger.info("보유주식 총수={}", total);
		
		// 종목별 목표수익률 {종목코드, 목표수익률}
		Map<String, Integer> profitGoals = new HashMap<String, Integer>();
		
		// 종목별 비중 {종목코드, 비중}
		Map<String, Float> weights = new HashMap<String, Float>();
		for(StockGoalExt s : myStocks) {
			weights.put(s.getIsincode(), (float)(s.getQty()/total));
			profitGoals.put(s.getIsincode(), s.getProfitGoal());
		}
		
		int n = 0;
		Double portfolioWeight = 0d;
		Double portfolioProfit = 0d;
		for(StockRisk r : myStockRisks) {
			String isincode = r.getIsincode();
			Float weight = weights.get(isincode);
			Integer profitGoal = profitGoals.get(isincode);
			
			portfolioWeight += Math.pow(weight*r.getRiskRate(), 2);
			
			portfolioProfit += weight * profitGoal;
			
			n++;
		}
		double portfolioRiskRate = Math.sqrt(portfolioWeight);
		portfolioProfit /= 100;
		portfolioProfit /= n;
		return new StockRisk("0", portfolioRiskRate, portfolioProfit);
	}
	private String makeRiskComment(Double portfolioRiskRate, Double kospi200RiskRate) {
		if(portfolioRiskRate==null || kospi200RiskRate==null) return "";
		
		StringBuilder sb = new StringBuilder();
		sb.append("포트폴리오 위험도는 ");
		if(portfolioRiskRate > kospi200RiskRate) {
			sb.append("시장대비 위험도보다 높으므로 적절한 위험관리가 필요하며 종목의 교체없이 위험을 낮추기 위해서는 포트롤리오 비중 조정이 필요합니다.");
		} else if (portfolioRiskRate < kospi200RiskRate ) {
			sb.append("시장대비 위험도보다 낮으며 포트폴리오는 비교적 보수적으로 편성되어 있습니다.");
		} else {
			sb.append("포트폴리오 위험도는 시장과 비슷한 위험도를 가지고 있습니다.");
		}
		return sb.toString();
	}
	private String makeRiskReturnComment(StockRisk portfolioRisk, StockRisk kospi200Risk) {
		if(portfolioRisk==null || kospi200Risk==null) return null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("포트폴리오 기대 수익률은 ").append(String.format("%.3f", portfolioRisk.getProfitRate())).append("%로 지난 3개월 시장수익률보다 ");
		if( portfolioRisk.getProfitRate()>=kospi200Risk.getProfitRate() ) sb.append("높");
		else sb.append("낮");
		sb.append("았으며, 위험대비수익률은 ").append(String.format("%.3f",portfolioRisk.getRiskPerProfit())).append("%로 시장대비위험조정수익률보다 ");
		if(portfolioRisk.getRiskPerProfit()>=kospi200Risk.getRiskPerProfit()) sb.append("높");
		else sb.append("낮");
		sb.append("습니다.");
				
		return sb.toString();
	}
	
	private SectorAnalysis makeSectorAnalysis(String noPerson, List<StockGoalExt> myStocks) {
		if(noPerson==null || myStocks==null) return null;
		
		// 섹터 정보 조회 
		List<StockSector> mySectors = stockGoalService.getStockSectors(noPerson);
		if(mySectors==null) {
			logger.error("not found my sectors for noPerson={}", noPerson);
			return null;
		}
		
		// 섹터목록 변환 
		// - {종목코드, StockSector}
		Map<String, StockSector> mySectorsMap = new HashMap<String, StockSector>();
		
		// 섹터별 평가금액 합계 {sectorCd, 평가금액}
		Map<String, Long> sectorValsMap = new HashMap<String, Long>();
		
		// - {sectorCd, sectorNm}
		Map<String, String> sectors = new HashMap<String, String>();
		
		for(StockSector s : mySectors) {
			mySectorsMap.put( s.getIsincode(), s );
			
			String sectorCd = s.getSectorCd();
			
			sectorValsMap.put(sectorCd, 0L);	// 섹터별 평가금액 초기화 
			
			sectors.put( s.getSectorCd(), s.getSectorNm() );	
		}
		
		// 평가금액 합 및 섹터별 평가금액 합 
		long totalVal = 0l;
		for(StockGoalExt sg : myStocks) {
			long val = sg.getValAtCur();
			totalVal += val;
			
			StockSector sector = mySectorsMap.get(sg.getIsincode());
			String sectorCd = sector.getSectorCd();
			long old = sectorValsMap.get(sectorCd);
			sectorValsMap.put(sectorCd, old+val);
		}
		
		// 섹터별 투자비중 
		List<String> sectorsOver50p = null;
		
		for(String sectorCd : sectorValsMap.keySet()) {
			long sectorVal = sectorValsMap.get(sectorCd);
			if( sectorVal/totalVal >= 0.5 ) {
				if(sectorsOver50p==null) sectorsOver50p = new ArrayList<String>();
				if(sectorsOver50p!=null) sectorsOver50p.add(sectorCd);
			}
		}
		
		SectorAnalysis sectorAnalysis = new SectorAnalysis();
		
		List<SectorExposure> sectorExposures = getSectorExposures(sectorValsMap, sectors);
		sectorAnalysis.setComment( getSectorExposureComment(sectors, sectorsOver50p) );

		sectorAnalysis.setSectorExposures(sectorExposures);
		
		return sectorAnalysis;
	}	
	private List<SectorExposure> getSectorExposures(Map<String, Long> sectorValsMap, Map<String, String> sectors) {
		if(sectorValsMap==null || sectors==null) return null;
		
		List<SectorExposure> exposures = new ArrayList<SectorExposure>();
		for(String sectorCd : sectorValsMap.keySet()) {
			exposures.add( new SectorExposure(sectors.get(sectorCd), sectorValsMap.get(sectorCd)) );
		}
		
		return exposures;
	}	
	private String getSectorExposureComment(Map<String, String> sectors, List<String> sectorsOver50p  ) {

		int sectorCnt = sectors!=null ? sectors.size() : 0;
		StringBuilder sb = new StringBuilder();
		if( sectorCnt>4 ) sb.append("포트폴리오 편입 종목은 섹터별로 비교적 잘 분산되어 있습니다.");
		else if(sectorCnt<1) sb.append(""); 
		else { // 1,2,3,4
			sb.append("포트폴리오 편입 종목이 ( ");
			
			int i=0;
			for(String sectorCd : sectors.keySet()) {
				if(i>1) sb.append(",");
				sb.append( sectors.get(sectorCd) );
				i++;
			}

			sb.append(" ) 에 편중되어 있습니다.");
		}
		int halfCnt = sectorsOver50p != null ? sectorsOver50p.size() : 0;
		if(halfCnt>0) {
			sb.append("( ");
			
			for( int i=0; i<sectorsOver50p.size(); i++) {
				if(i>1) sb.append(",");
					
				String sectorCd = sectorsOver50p.get(i);
				sb.append( sectors.get(sectorCd) );
			}
			sb.append(") 섹터에 전체 투자액의 절반이상이 투자되어 포트폴리오가 특정 섹터에 과도히 노출되어 있습니다.");
		}
		return sb.toString();
	}

	@Override
	public DiagsStockReport diagsStockReport(String noPerson) {
		if(noPerson==null) return null;
		
		// 보유종목조회 
		List<StockGoalExt> myStocks = stockGoalService.getGoals(noPerson);
		if(myStocks==null) {
			logger.error("no my stocks for noPerson={}", noPerson);
			return null;
		}
		float total = 0f;
		for(StockGoalExt s : myStocks) {
			total += s.getQty();
		}
		// 보유종목 리스크 조회 
		List<StockRisk> myStockRisks = diagsMapper.getStockRisks(noPerson);
		if(myStockRisks==null || myStockRisks.size()<1) {
			logger.error("not found my stock risks for noPerson={}", noPerson);
			return null;
		}
		Map<String, Double> stockRisksMap = new HashMap<String, Double>();
		for(StockRisk r : myStockRisks) {
			stockRisksMap.put(r.getIsincode(), r.getRiskRate());
		}
		
		// 종목 factor 분석 
		List<FactorAnalysis> stockFactorAnalysis = diagsMapper.stockFactorAnalysis(noPerson);
		if(stockFactorAnalysis==null) {
			logger.error("no stock factor analysis for noPerson={}", noPerson);
			return null;
		}
		Map<String, FactorAnalysis> stockFactorAnalysisMap = new HashMap<String, FactorAnalysis>();
		for(FactorAnalysis fa : stockFactorAnalysis) {
			stockFactorAnalysisMap.put(fa.getIsincode(), fa);
		}
		
		// create StockAnalysis List 
		List<StockAnalysis> stockAnalysisList = new ArrayList<StockAnalysis>();
		for(StockGoalExt s : myStocks) {
			StockAnalysis stockAnalysis = new StockAnalysis();
			stockAnalysis.setIsincode(s.getIsincode());
			stockAnalysis.setIsuKorNm(s.getIsuKorNm());
			stockAnalysis.setValAtCur(s.getValAtCur());
			stockAnalysis.setProLoss(s.getProLoss());
			stockAnalysis.setEarningRate(s.getEarningRate());
			stockAnalysis.setFactorAnalysis(stockFactorAnalysisMap.get(s.getIsincode()));
			
			stockAnalysis.setRiskRate( stockRisksMap.get(s.getIsincode()));
			stockAnalysis.setWeight( total!=0 ? s.getQty()/total : 0f );
			
			stockAnalysisList.add(stockAnalysis);
		}
		
		DiagsStockReport diagsStockReport = new DiagsStockReport();
		diagsStockReport.setStockAnalysisList(stockAnalysisList);
		return diagsStockReport;
	}



}
