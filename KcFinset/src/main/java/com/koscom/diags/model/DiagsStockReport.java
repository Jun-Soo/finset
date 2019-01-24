package com.koscom.diags.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.diags.model.analysis.StockAnalysis;

public class DiagsStockReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	//

	private List<StockAnalysis> stockAnalysisList;

	public List<StockAnalysis> getStockAnalysisList() {
		return stockAnalysisList;
	}

	public void setStockAnalysisList(List<StockAnalysis> stockAnalysisList) {
		this.stockAnalysisList = stockAnalysisList;
	}

	
	
}
