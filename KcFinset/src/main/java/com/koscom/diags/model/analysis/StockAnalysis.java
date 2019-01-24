package com.koscom.diags.model.analysis;

import java.io.Serializable;

public class StockAnalysis extends AnalysisBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	//

	private String isincode; //종목코드  
	private String isuKorNm; // 종목명 
	
	private Long valAtCur; //평가금액 합 
	private Long proLoss; // 평가손 합 
	private Float earningRate;	// 수익률 합 
	
	private Double  riskRate;		// 위험도 
	private Float	weight;			// 비중 
	
	private FactorAnalysis factorAnalysis; // factor분석 

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

	public Long getValAtCur() {
		return valAtCur;
	}
	public void setValAtCur(Long valAtCur) {
		this.valAtCur = valAtCur;
	}

	public Long getProLoss() {
		return proLoss;
	}
	public void setProLoss(Long proLoss) {
		this.proLoss = proLoss;
	}

	public Float getEarningRate() {
		return earningRate;
	}
	public void setEarningRate(Float earningRate) {
		this.earningRate = earningRate;
	}	
	
	public Double getRiskRate() {
		return riskRate;
	}
	public void setRiskRate(Double riskRate) {
		this.riskRate = riskRate;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public FactorAnalysis getFactorAnalysis() {
		return factorAnalysis;
	}
	public void setFactorAnalysis(FactorAnalysis factorAnalysis) {
		this.factorAnalysis = factorAnalysis;
	}

	
}
