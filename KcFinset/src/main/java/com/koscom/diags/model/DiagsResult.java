package com.koscom.diags.model;

import java.io.Serializable;

public class DiagsResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	//
	private int stocks; // 보유주식갯수
	private int surveys ; // 설문갯수
	private int stockGoals; // 목표설정갯

	public DiagsResult() {
		
	};
	public DiagsResult( int accounts, int surveys, int stockGoals ) {
		this.stocks = accounts;
		this.surveys = surveys;
		this.stockGoals = stockGoals;
	}
	public int getStocks() {
		return stocks;
	}
	public void setStocks(int stocks) {
		this.stocks = stocks;
	}
	public int getSurveys() {
		return surveys;
	}
	public void setSurveys(int surveys) {
		this.surveys = surveys;
	}
	public int getStockGoals() {
		return stockGoals;
	}
	public void setStockGoals(int stockGoals) {
		this.stockGoals = stockGoals;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiagsResult [stocks=");
		builder.append(stocks);
		builder.append(", surveys=");
		builder.append(surveys);
		builder.append(", stockGoals=");
		builder.append(stockGoals);
		builder.append("]");
		return builder.toString();
	}

}
