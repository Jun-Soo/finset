package com.koscom.oppf.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.StockEquitylistInfo;
import com.koscom.domain.StockEtclistInfo;
import com.koscom.domain.StockFundlistInfo;
import com.koscom.domain.StockListInfo;
import com.koscom.domain.StockSummaryInfo;
import com.koscom.domain.StockTransactionInfo;
import com.koscom.oppf.dao.OppfMapper;
import com.koscom.oppf.model.AssetsInfo;
import com.koscom.oppf.model.StockEquityForm;
import com.koscom.oppf.model.StockEquityVO;
import com.koscom.oppf.model.StockEtcForm;
import com.koscom.oppf.model.StockEtcVO;
import com.koscom.oppf.model.StockForm;
import com.koscom.oppf.model.StockFundForm;
import com.koscom.oppf.model.StockFundVO;
import com.koscom.oppf.model.StockSummaryForm;
import com.koscom.oppf.model.StockTransactionForm;
import com.koscom.oppf.model.StockVO;
import com.koscom.oppf.service.OppfManager;
import com.koscom.util.Constant;
import com.koscom.util.NumberUtil;
import com.koscom.util.ReturnClass;

@Service("oppfManager")
public class OppfManagerImpl implements OppfManager{

	private static final Logger logger = LoggerFactory.getLogger(OppfManagerImpl.class);

	@Autowired
	private OppfMapper oppfMapper;

	@Override
	public List<StockEquitylistInfo> listStockEquitylistInfo(StockEquityForm stockEquityForm){
		return oppfMapper.listStockEquitylistInfo(stockEquityForm);
	}
	@Override
	public List<StockEtclistInfo> listStockEtclistInfo(StockEtcForm stockEtcForm){
		return oppfMapper.listStockEtclistInfo(stockEtcForm);
	}
	@Override
	public List<StockFundlistInfo> listStockFundlistInfo(StockFundForm stockFundForm){
		return oppfMapper.listStockFundlistInfo(stockFundForm);
	}
	@Override
	public List<StockListInfo> listStockListInfo(StockForm stockForm){
		return oppfMapper.listStockListInfo(stockForm);
	}
	@Override
	public List<StockSummaryInfo> listStockSummaryInfo(StockSummaryForm stockSummaryForm){
		return oppfMapper.listStockSummaryInfo(stockSummaryForm);
	}
	@Override
	public List<StockTransactionInfo> listStockTransactionInfo(StockTransactionForm stockTransactionForm){
		return oppfMapper.listStockTransactionInfo(stockTransactionForm);
	}
	@Override
	public StockEquitylistInfo getStockEquitylistInfo(StockEquityForm stockEquityForm){
		return oppfMapper.getStockEquitylistInfo(stockEquityForm);
	}
	@Override
	public StockEtclistInfo getStockEtclistInfo(StockEtcForm stockEtcForm){
		return oppfMapper.getStockEtclistInfo(stockEtcForm);
	}
	@Override
	public StockFundlistInfo getStockFundlistInfo(StockFundForm stockFundForm){
		return oppfMapper.getStockFundlistInfo(stockFundForm);
	}
	@Override
	public StockListInfo getStockListInfo(StockForm stockForm){
		return oppfMapper.getStockListInfo(stockForm);
	}
	@Override
	public StockSummaryInfo getStockSummaryInfo(StockSummaryForm stockSummaryForm){
		return oppfMapper.getStockSummaryInfo(stockSummaryForm);
	}
	@Override
	public StockTransactionInfo getStockTransactionInfo(StockTransactionForm stockTransactionForm){
		return oppfMapper.getStockTransactionInfo(stockTransactionForm);
	}
	@Override
	public ReturnClass procStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo) {
		if (1 != oppfMapper.procStockEquitylistInfo(stockEquitylistInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass procStockEtclistInfo(StockEtclistInfo stockEtclistInfo) {
		if (1 != oppfMapper.procStockEtclistInfo(stockEtclistInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass procStockFundlistInfo(StockFundlistInfo stockFundlistInfo) {
		if (1 != oppfMapper.procStockFundlistInfo(stockFundlistInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass procStockListInfo(StockListInfo stockListInfo) {
		if (1 != oppfMapper.procStockListInfo(stockListInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass procStockSummaryInfo(StockSummaryInfo stockSummaryInfo) {
		if (1 != oppfMapper.procStockSummaryInfo(stockSummaryInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass procStockTransactionInfo(StockTransactionInfo stockTransactionInfo) {
		if (1 != oppfMapper.procStockTransactionInfo(stockTransactionInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo) {
		if (1 != oppfMapper.delStockEquitylistInfo(stockEquitylistInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStockEtclistInfo(StockEtclistInfo stockEtclistInfo) {
		if (1 != oppfMapper.delStockEtclistInfo(stockEtclistInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStockFundlistInfo(StockFundlistInfo stockFundlistInfo) {
		if (1 != oppfMapper.delStockFundlistInfo(stockFundlistInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStockListInfo(StockListInfo stockListInfo) {
		if (1 != oppfMapper.delStockListInfo(stockListInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStockSummaryInfo(StockSummaryInfo stockSummaryInfo) {
		if (1 != oppfMapper.delStockSummaryInfo(stockSummaryInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStockTransactionInfo(StockTransactionInfo stockTransactionInfo) {
		if (1 != oppfMapper.delStockTransactionInfo(stockTransactionInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	/*
	 * 총자산 계산
	 * @param StockForm
	 * @return StockVO
	 */
	@Override
	public StockVO getAssetsMainData(StockForm stockForm) {
		// TODO Auto-generated method stub

		/* 총 자산 구하기
		 * stockSummaryInfo.Cashbalance + sum(StockEquitylistInfo.Valatcur) + sum(StockFundlistInfo.Valatcur) + sum(StockEtclistInfo.Valueatcur)
		 *
		 * 총 평가금액
		 * 주식 : sum(StockEquitylistInfo.Valatcur)
		 * 펀드 : sum(StockFundlistInfo.Valatcur)
		 * 기타 : sum(StockEtclistInfo.Valueatcur)
		 *
		 * 총 매수금액
		 * 주식 : sum(StockEquitylistInfo.Valattrade)
		 * 펀드 : sum(StockFundlistInfo.Valattrade)
		 * 기타 : sum(StockEtclistInfo.Valattrade)
		 *
		 * 자산별 비중
		 * 주식 : 총평가금액 / 총자산 * 100
		 * 펀드 : 총평가금액 / 총자산 * 100
		 * 기타 : 총평가금액 / 총자산 * 100
		 *
		 * 자산별 수익률
		 * 주식 : (총평가금액 - 총매수금액) / 총매수금액 * 100
		 * 펀드 : (총평가금액 - 총매수금액) / 총매수금액 * 100
		 * 기타 : (총평가금액 - 총매수금액) / 총매수금액 * 100
		 */

		long sumValatcur = 0;     //총평가금액
		long sumValAtTrade = 0;   //총매수금액
		long totalAssetsVal = 0;  //총자산금액

		String no_person = stockForm.getNo_person();
		StockVO stockVO = new StockVO();

		//summaryInfo get
		StockSummaryForm stockSummaryForm = new StockSummaryForm();
		stockSummaryForm.setNo_person(no_person);
		List<StockSummaryInfo> liststockSummary = listStockSummaryInfo(stockSummaryForm);

		sumValatcur = 0;
		sumValAtTrade = 0;
		for(StockSummaryInfo list : liststockSummary){
			sumValatcur += list.getCashbalance();
		}

		logger.info("summary amt : " + sumValatcur);
		totalAssetsVal = sumValatcur;

		//EquityList get
		StockEquityForm stockEquityForm = new StockEquityForm();
		stockEquityForm.setNo_person(no_person);
		List<StockEquitylistInfo> listStockEquity = listStockEquitylistInfo(stockEquityForm);

		sumValatcur = 0;
		sumValAtTrade = 0;
		for(StockEquitylistInfo list : listStockEquity){
			sumValatcur += list.getValatcur();
			sumValAtTrade += list.getValattrade();
		}

		logger.info("equity amt : " + sumValatcur);

		AssetsInfo assetsInfoEquity = new AssetsInfo();
		assetsInfoEquity.setTotal_evaluation_Amt(sumValatcur); //총평가금액
		assetsInfoEquity.setTotal_purchase_amt(sumValAtTrade); //총평가금액
		assetsInfoEquity.setAccno_cnt( listStockEquity.size() );

		totalAssetsVal += sumValatcur;


		//FundList get
		StockFundForm stockFundForm = new StockFundForm();
		stockFundForm.setNo_person(no_person);
		List<StockFundlistInfo> listStockFund = listStockFundlistInfo(stockFundForm);

		sumValatcur = 0;
		sumValAtTrade = 0;
		for(StockFundlistInfo list : listStockFund){
			sumValatcur += list.getValatcur();
			sumValAtTrade += list.getValattrade();
		}

		logger.info("fund amt : " + sumValatcur);

		AssetsInfo assetsInfoFund = new AssetsInfo();
		assetsInfoFund.setTotal_evaluation_Amt(sumValatcur); //총평가금액
		assetsInfoFund.setTotal_purchase_amt(sumValAtTrade); //총평가금액
		assetsInfoFund.setAccno_cnt( listStockFund.size() );

		totalAssetsVal += sumValatcur;

		//etcList get
		StockEtcForm stockEtcForm = new StockEtcForm();
		stockEtcForm.setNo_person(no_person);

		List<StockEtclistInfo> listStockEtc = listStockEtclistInfo(stockEtcForm);

		sumValatcur = 0;
		sumValAtTrade = 0;
		for(StockEtclistInfo list : listStockEtc){
			sumValatcur += list.getValueatcur();
			sumValAtTrade += list.getValattrade();
		}

		logger.info("etc amt : " + sumValatcur);

		AssetsInfo assetsInfoEtc = new AssetsInfo();
		assetsInfoEtc.setTotal_evaluation_Amt(sumValatcur); //총평가금액
		assetsInfoEtc.setTotal_purchase_amt(sumValAtTrade); //총평가금액
		assetsInfoEtc.setAccno_cnt( listStockEtc.size() );

		totalAssetsVal += sumValatcur;

		stockVO.setTotal_assets_amt(totalAssetsVal);//총자산

		logger.info("totalAssetsAmt : " + stockVO.getTotal_assets_amt());

		//자산별 비중
		assetsInfoFund.setRto_assets_proportion( totalAssetsVal != 0 ? NumberUtil.doubleRound(((assetsInfoFund.getTotal_evaluation_Amt() / (double)totalAssetsVal) * 100), 2) : 0.0 );
		assetsInfoEquity.setRto_assets_proportion( totalAssetsVal != 0 ? NumberUtil.doubleRound(((assetsInfoEquity.getTotal_evaluation_Amt() / (double)totalAssetsVal) * 100), 2) : 0.0 );
		assetsInfoEtc.setRto_assets_proportion( totalAssetsVal != 0 ? NumberUtil.doubleRound(((assetsInfoEtc.getTotal_evaluation_Amt() / (double)totalAssetsVal) * 100), 2) : 0.0 );

		//자산별 수익률
		assetsInfoFund.setRto_assets_tevenue( assetsInfoFund.getTotal_purchase_amt() != 0 ? NumberUtil.doubleRound(((assetsInfoFund.getTotal_evaluation_Amt() - assetsInfoFund.getTotal_purchase_amt()) / (double)assetsInfoFund.getTotal_purchase_amt() * 100), 2) : 0.0 );
		assetsInfoEquity.setRto_assets_tevenue( assetsInfoEquity.getTotal_purchase_amt() != 0 ? NumberUtil.doubleRound(((assetsInfoEquity.getTotal_evaluation_Amt() - assetsInfoEquity.getTotal_purchase_amt()) / (double)assetsInfoEquity.getTotal_purchase_amt() * 100), 2) : 0.0 );
		assetsInfoEtc.setRto_assets_tevenue( assetsInfoEtc.getTotal_purchase_amt() != 0 ? NumberUtil.doubleRound(((assetsInfoEtc.getTotal_evaluation_Amt() - assetsInfoEtc.getTotal_purchase_amt()) / (double)assetsInfoEtc.getTotal_purchase_amt() * 100), 2) : 0.0 );
		
		/*
		List<AssetsInfo> list = new ArrayList<AssetsInfo>();

		list.add(assetsInfoFund);
		list.add(assetsInfoEquity);
		list.add(assetsInfoEquity);

		stockVO.setListAssets(list);
		*/
		stockVO.setAssetsInfoFund(assetsInfoFund);
		stockVO.setAssetsInfoEquity(assetsInfoEquity);
		stockVO.setAssetsInfoEtc(assetsInfoEtc);

		logger.info("!!!!!!!!!!!!!!!!!! : " + stockVO.toString());
		return stockVO;
	}

	@Override
	public List<StockEquityVO> listAssetsStock(StockEquityForm stockEquityForm) {
		// TODO Auto-generated method stub
		return oppfMapper.listAssetsStock(stockEquityForm);
	}
	@Override
	public int listAssetsStockCount(StockEquityForm stockEquityForm) {
		// TODO Auto-generated method stub
		return oppfMapper.listAssetsStockCount(stockEquityForm);
	}
	@Override
	public List<StockFundVO> listAssetsFund(StockFundForm stockFundForm) {
		// TODO Auto-generated method stub
		return oppfMapper.listAssetsFund(stockFundForm);
	}
	@Override
	public int listAssetsFundCount(StockFundForm stockFundForm) {
		// TODO Auto-generated method stub
		return oppfMapper.listAssetsFundCount(stockFundForm);
	}
	@Override
	public List<StockEtcVO> listAssetsEtc(StockEtcForm stockEtcForm) {
		// TODO Auto-generated method stub
		return oppfMapper.listAssetsEtc(stockEtcForm);
	}
	@Override
	public int listAssetsEtcCount(StockEtcForm stockEtcForm) {
		// TODO Auto-generated method stub
		return oppfMapper.listAssetsEtcCount(stockEtcForm);
	}
	@Override
	public List<StockFundVO> getAssetsFund(StockFundForm stockFundForm) {
		// TODO Auto-generated method stub
		return oppfMapper.getAssetsFund(stockFundForm);
	}
}
