package com.koscom.diags.model;

import java.io.Serializable;
import java.util.List;

public class StockGoalReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2056472747803722088L;

	List<StockGoal> stockGoals;

	public List<StockGoal> getStockGoals() {
		return stockGoals;
	}

	public void setStockGoals(List<StockGoal> stockGoals) {
		this.stockGoals = stockGoals;
	}

	
}
