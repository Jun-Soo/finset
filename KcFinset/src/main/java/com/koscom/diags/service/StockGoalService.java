package com.koscom.diags.service;

import java.util.List;

import com.koscom.diags.model.StockGoal;
import com.koscom.diags.model.StockGoalExt;
import com.koscom.diags.model.analysis.StockSector;

public interface StockGoalService {

	/**
	 * 종목별 투자목표 조회 
	 * @param String
	 * @return StockGoalExt
	 */
	public List<StockGoalExt> getGoals(String noPerson);

	/**
	 * 종목별 투자목표 수정 
	 * @param InvestSurvey
	 * @return void
	 */
	public void saveStockGoals(String noPerson, List<StockGoal> stockGoals);

	/**
	 * 보유종목 섹터정보 조회 
	 * @param String
	 * @return StockSector
	 */
	public List<StockSector> getStockSectors(String noPerson);

}
