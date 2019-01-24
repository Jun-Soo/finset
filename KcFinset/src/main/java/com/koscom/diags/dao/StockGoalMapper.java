package com.koscom.diags.dao;

import java.util.List;

import com.koscom.diags.model.StockGoal;
import com.koscom.diags.model.StockGoalExt;
import com.koscom.diags.model.analysis.StockSector;

public interface StockGoalMapper { // com.koscom.scrap.dao.ScrapMapper

	/**
	 * 목표수익률 조회 
	 * @param String
	 */
	List<StockGoalExt> getStockGoals(String noPerson);
	
	/**
	 * 목표수익률 등록
	 * @param stockGoals
	 */
	int insertStockGoals(List<StockGoal> stockGoals);
	
	/**
	 * 목표수익률 삭제  
	 * @param String
	 */
	int deleteStockGoals(String noPerson);
	
	int getStockGoalCount(String noPersion);
	
	/*
	 * 보유종목 섹터 조회 
	 */
	List<StockSector> getStockSectors(String noPerson);
	
}