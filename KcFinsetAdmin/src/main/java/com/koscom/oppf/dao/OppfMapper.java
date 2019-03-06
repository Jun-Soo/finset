package com.koscom.oppf.dao;

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

public interface OppfMapper {
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
	int procStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo); 
	int procStockEtclistInfo(StockEtclistInfo stockEtclistInfo); 
	int procStockFundlistInfo(StockFundlistInfo stockFundlistInfo);
	int procStockListInfo(StockListInfo stockListInfo);
	int procStockSummaryInfo(StockSummaryInfo stockSummaryInfo);
	int procStockTransactionInfo(StockTransactionInfo stockTransactionInfo);
	int delStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo);
	int delStockEtclistInfo(StockEtclistInfo stockEtclistInfo);
	int delStockFundlistInfo(StockFundlistInfo stockFundlistInfo);
	int delStockListInfo(StockListInfo stockListInfo);
	int delStockSummaryInfo(StockSummaryInfo stockSummaryInfo);
	int delStockTransactionInfo(StockTransactionInfo stockTransactionInfo);
	List<StockEquityVO> listAssetsStock(StockEquityForm stockEquityForm);
	int listAssetsStockCount(StockEquityForm stockEquityForm);
	List<StockFundVO> listAssetsFund(StockFundForm stockFundForm);
	int listAssetsFundCount(StockFundForm stockFundForm);
	List<StockEtcVO> listAssetsEtc(StockEtcForm stockEtcForm);
	int listAssetsEtcCount(StockEtcForm stockEtcForm);
	List<StockFundVO> getAssetsFund(StockFundForm stockFundForm);
}