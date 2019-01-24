package com.koscom.diags.model.analysis;

import java.io.Serializable;

public class StockRisk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	
	private String isincode;	// 종목코드 
	private String isuKorNm; // 종목명 
	private Double  riskRate;		// 위험도 
	private Double  profitRate;	// 수익률 
	
	public StockRisk() {};
	public StockRisk(String isincode, Double riskRate, Double profitRate) {
		this.isincode = isincode;
		this.riskRate = riskRate;
		this.profitRate = profitRate;
	}
	
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	
	public String getIsuKorNm() {
		return isuKorNm;
	}
	public void setIsuKorNm(String isuKorNm) {
		this.isuKorNm = isuKorNm;
	}
	
	public Double getRiskRate() {
		return riskRate;
	}
	public void setRiskRate(Double riskRate) {
		this.riskRate = riskRate;
	}
	public Double getProfitRate() {
		return profitRate;
	}
	public void setProfitRate(Double profitRate) {
		this.profitRate = profitRate;
	}

	public Double getRiskPerProfit() {
		return profitRate!=null && profitRate!=0 ? riskRate/profitRate : 0;
	}

}
