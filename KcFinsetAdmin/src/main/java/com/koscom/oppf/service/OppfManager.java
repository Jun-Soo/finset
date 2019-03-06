package com.koscom.oppf.service;

import java.util.List;

import com.koscom.domain.StockEquitylistInfo;
import com.koscom.domain.StockEtclistInfo;
import com.koscom.domain.StockFundlistInfo;
import com.koscom.domain.StockListInfo; 
import com.koscom.domain.StockSummaryInfo;
import com.koscom.domain.StockTransactionInfo;
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
import com.koscom.util.ReturnClass;

public interface OppfManager {
	List<StockEquitylistInfo> listStockEquitylistInfo(StockEquityForm stockEquityForm); 
	List<StockEtclistInfo> listStockEtclistInfo(StockEtcForm stockEtcForm); 
	List<StockFundlistInfo>  listStockFundlistInfo(StockFundForm stockFundForm);  
	List<StockListInfo> listStockListInfo(StockForm stockForm);  
	List<StockSummaryInfo> listStockSummaryInfo(StockSummaryForm stockSummaryForm); 
	List<StockTransactionInfo> listStockTransactionInfo(StockTransactionForm stockTransactionForm); 
	StockEquitylistInfo getStockEquitylistInfo(StockEquityForm stockEquityForm); 
	StockEtclistInfo getStockEtclistInfo(StockEtcForm stockEtcForm); 
	StockFundlistInfo  getStockFundlistInfo(StockFundForm stockFundForm);  
	StockListInfo getStockListInfo(StockForm stockForm);  
	StockSummaryInfo getStockSummaryInfo(StockSummaryForm stockSummaryForm); 
	StockTransactionInfo getStockTransactionInfo(StockTransactionForm stockTransactionForm); 
	ReturnClass procStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo); 
	ReturnClass procStockEtclistInfo(StockEtclistInfo stockEtclistInfo); 
	ReturnClass procStockFundlistInfo(StockFundlistInfo stockFundlistInfo);
	ReturnClass procStockListInfo(StockListInfo stockListInfo);
	ReturnClass procStockSummaryInfo(StockSummaryInfo stockSummaryInfo);
	ReturnClass procStockTransactionInfo(StockTransactionInfo stockTransactionInfo);
	ReturnClass delStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo);
	ReturnClass delStockEtclistInfo(StockEtclistInfo stockEtclistInfo);
	ReturnClass delStockFundlistInfo(StockFundlistInfo stockFundlistInfo);
	ReturnClass delStockListInfo(StockListInfo stockListInfo);
	ReturnClass delStockSummaryInfo(StockSummaryInfo stockSummaryInfo);
	ReturnClass delStockTransactionInfo(StockTransactionInfo stockTransactionInfo);
	
	/*
	 * 총자산 구하기
	 * @param StockForm
	 * return StockVO
	 */
	StockVO getAssetsMainData(StockForm stockForm);
	
	/*
	 * 주식 조회
	 * @param StockEquityForm
	 * return List<StockEquityVO>
	 */
	List<StockEquityVO> listAssetsStock(StockEquityForm stockEquityForm);
	
	/*
	 * 주식 조회 건수
	 * @param StockEquityForm
	 * return int
	 */
	int listAssetsStockCount(StockEquityForm stockEquityForm);
	
	/*
	 * 펀드 조회
	 * @param StockFundForm
	 * return List<StockFundVO>
	 */
	List<StockFundVO> listAssetsFund(StockFundForm stockFundForm);
	
	/*
	 * 펀드 조회 건수
	 * @param StockEquityForm
	 * return int
	 */
	int listAssetsFundCount(StockFundForm stockFundForm);
	
	/*
	 * 기타 조회
	 * @param StockEtcForm
	 * return List<StockFundVO>
	 */
	List<StockEtcVO> listAssetsEtc(StockEtcForm stockEtcForm);
	
	/*
	 * 펀드 조회 건수
	 * @param StockEtcForm
	 * return int
	 */
	int listAssetsEtcCount(StockEtcForm stockEtcForm);
	
	/*
	 * 펀드 조회
	 * @param StockFundForm
	 * return List<StockFundVO>
	 */
	List<StockFundVO> getAssetsFund(StockFundForm stockFundForm);
}
