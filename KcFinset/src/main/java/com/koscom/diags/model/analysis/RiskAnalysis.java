package com.koscom.diags.model.analysis;

import java.io.Serializable;
import java.util.List;

public class RiskAnalysis extends AnalysisBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	// 
	private List<StockRisk> stockRisks;
		
	private String riskReturnComment;

	public List<StockRisk> getStockRisks() {
		return stockRisks;
	}

	public void setStockRisks(List<StockRisk> stockRisks) {
		this.stockRisks = stockRisks;
	}

	public String getRiskReturnComment() {
		return riskReturnComment;
	}

	public void setRiskReturnComment(String riskReturnComment) {
		this.riskReturnComment = riskReturnComment;
	}



}
