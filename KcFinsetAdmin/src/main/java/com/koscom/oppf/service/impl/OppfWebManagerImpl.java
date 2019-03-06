package com.koscom.oppf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.koscom.oppf.service.OppfManager;
import com.koscom.oppf.service.OppfWebManager;
import com.koscom.util.ReturnClass;

@Service("oppfWebManager")
public class OppfWebManagerImpl implements OppfWebManager{
	
	@Autowired
	private OppfManager oppfManager;
	@Override
	public List<StockEquitylistInfo> listStockEquitylistInfo(StockEquityForm stockEquityForm) {
		return oppfManager.listStockEquitylistInfo(stockEquityForm);
	}
	@Override
	public List<StockEtclistInfo> listStockEtclistInfo(StockEtcForm stockEtcForm) {
		return oppfManager.listStockEtclistInfo(stockEtcForm);
	}
	@Override
	public List<StockFundlistInfo> listStockFundlistInfo(StockFundForm stockFundForm) {
		return oppfManager.listStockFundlistInfo(stockFundForm);
	}
	@Override
	public List<StockListInfo> listStockListInfo(StockForm stockForm) {
		return oppfManager.listStockListInfo(stockForm);
	}
	@Override
	public List<StockSummaryInfo> listStockSummaryInfo(StockSummaryForm stockSummaryForm) {
		return oppfManager.listStockSummaryInfo(stockSummaryForm) ;
	}
	@Override
	public List<StockTransactionInfo> listStockTransactionInfo(StockTransactionForm stockTransactionForm) {
		return oppfManager.listStockTransactionInfo(stockTransactionForm) ;
	}
	@Override
	public StockEquitylistInfo getStockEquitylistInfo(StockEquityForm stockEquityForm) {
		return oppfManager.getStockEquitylistInfo(stockEquityForm) ;
	}
	@Override
	public StockEtclistInfo getStockEtclistInfo(StockEtcForm stockEtcForm) {
		return oppfManager.getStockEtclistInfo(stockEtcForm) ;
	}
	@Override
	public StockFundlistInfo getStockFundlistInfo(StockFundForm stockFundForm) {
		return oppfManager.getStockFundlistInfo(stockFundForm) ;
	}
	@Override
	public StockListInfo getStockListInfo(StockForm stockForm) {
		return oppfManager.getStockListInfo(stockForm) ;
	}
	@Override
	public StockSummaryInfo getStockSummaryInfo(StockSummaryForm stockSummaryForm) {
		return oppfManager.getStockSummaryInfo(stockSummaryForm) ;
	}
	@Override
	public StockTransactionInfo getStockTransactionInfo(StockTransactionForm stockTransactionForm) {
		return oppfManager.getStockTransactionInfo(stockTransactionForm) ;
	}
	@Override
	public ReturnClass procStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo) {
		return oppfManager.procStockEquitylistInfo(stockEquitylistInfo) ;
	}
	@Override
	public ReturnClass procStockEtclistInfo(StockEtclistInfo stockEtclistInfo) {
		return oppfManager.procStockEtclistInfo(stockEtclistInfo) ;
	}
	@Override
	public ReturnClass procStockFundlistInfo(StockFundlistInfo stockFundlistInfo) {
		return oppfManager.procStockFundlistInfo(stockFundlistInfo) ;
	}
	@Override
	public ReturnClass procStockListInfo(StockListInfo stockListInfo) {
		return oppfManager.procStockListInfo(stockListInfo) ;
	}
	@Override
	public ReturnClass procStockSummaryInfo(StockSummaryInfo stockSummaryInfo) {
		return oppfManager.procStockSummaryInfo(stockSummaryInfo) ;
	}
	@Override
	public ReturnClass procStockTransactionInfo(StockTransactionInfo stockTransactionInfo) {
		return oppfManager.procStockTransactionInfo(stockTransactionInfo) ;
	}
	@Override
	public ReturnClass delStockEquitylistInfo(StockEquitylistInfo stockEquitylistInfo) {
		return oppfManager.delStockEquitylistInfo(stockEquitylistInfo) ;
	}
	@Override
	public ReturnClass delStockEtclistInfo(StockEtclistInfo stockEtclistInfo) {
		return oppfManager.delStockEtclistInfo(stockEtclistInfo) ;
	}
	@Override
	public ReturnClass delStockFundlistInfo(StockFundlistInfo stockFundlistInfo) {
		return oppfManager.delStockFundlistInfo(stockFundlistInfo) ;
	}
	@Override
	public ReturnClass delStockListInfo(StockListInfo stockListInfo) {
		return oppfManager.delStockListInfo(stockListInfo) ;
	}
	@Override
	public ReturnClass delStockSummaryInfo(StockSummaryInfo stockSummaryInfo) {
		return oppfManager.delStockSummaryInfo(stockSummaryInfo) ;
	}
	@Override
	public ReturnClass delStockTransactionInfo(StockTransactionInfo stockTransactionInfo) {
		return oppfManager.delStockTransactionInfo(stockTransactionInfo) ;
	}
	@Override
	public StockVO getAssetsMainData(StockForm stockForm) {
		return oppfManager.getAssetsMainData(stockForm);
	}
	@Override
	public List<StockEquityVO> listAssetsStock(StockEquityForm stockEquityForm) {
		// TODO Auto-generated method stub
		return oppfManager.listAssetsStock(stockEquityForm);
	}
	@Override
	public int listAssetsStockCount(StockEquityForm stockEquityForm) {
		// TODO Auto-generated method stub
		return oppfManager.listAssetsStockCount(stockEquityForm);
	}
	@Override
	public List<StockFundVO> listAssetsFund(StockFundForm stockFundForm) {
		// TODO Auto-generated method stub
		return oppfManager.listAssetsFund(stockFundForm);
	}
	@Override
	public int listAssetsFundCount(StockFundForm stockFundForm) {
		// TODO Auto-generated method stub
		return oppfManager.listAssetsFundCount(stockFundForm);
	}
	@Override
	public List<StockEtcVO> listAssetsEtc(StockEtcForm stockEtcForm) {
		// TODO Auto-generated method stub
		return oppfManager.listAssetsEtc(stockEtcForm);
	}
	@Override
	public int listAssetsEtcCount(StockEtcForm stockEtcForm) {
		// TODO Auto-generated method stub
		return oppfManager.listAssetsEtcCount(stockEtcForm);
	}
	@Override
	public List<StockFundVO> getAssetsFund(StockFundForm stockFundForm) {
		// TODO Auto-generated method stub
		return oppfManager.getAssetsFund(stockFundForm);
	}
}
