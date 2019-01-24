package com.koscom.diags.dao;

import java.util.List;
import java.util.Map;

import com.koscom.diags.model.DiagsResult;
import com.koscom.diags.model.analysis.FactorAnalysis;
import com.koscom.diags.model.analysis.StockRisk;

public interface DiagsMapper { 

	/**
	 * 진단결과 조회  
	 * @param String
	 */
	DiagsResult getDiagsResult(String noPerson);
	/**
	 * 보유종목코드 조회 
	 * @param noPerson
	 * @return
	 */
	List<String> getMyStocks(String noPerson);
	/**
	 * 포트폴리오 factor analysis
	 * 
	 * @param noPerson
	 * @return
	 */
	FactorAnalysis factorAnalysis(String noPerson);
	/**
	 * 종목별 factor analysis
	 * 
	 * @param noPerson
	 * @return
	 */
	List<FactorAnalysis> stockFactorAnalysis(String noPerson);
	
	/**
	 * days 동안 종목 종가 
	 * 
	 * @param isincode
	 * @param days
	 * @return
	 * 	{day(yyyy-mm-dd), closePrice}
	 */
	Map<String, Integer> stockClosePrices(String isincode, int days);
	/**
	 * days 동안 지수(업종) 종가 
	 * @param issueCode
	 * @param days
	 * @return
	 * 	{day(yyyy-mm-dd), closePrice}
	 */
	Map<String, Integer> indexClosePrices(String issueCode, int days);

	/**
	 * 보유종목리스크(위험도, 수익률) 조회 
	 * @param String
	 */
	List<StockRisk> getStockRisks(String noPerson);
	
	/**
	 * kospi200 리스크(위험도, 수익률) 조회 
	 * 
	 */
	StockRisk getKospi200Risk();
	
	


}