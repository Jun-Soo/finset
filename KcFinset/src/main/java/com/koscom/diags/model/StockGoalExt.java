package com.koscom.diags.model;

import java.io.Serializable;

public class StockGoalExt extends StockGoal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041241914973650849L;
	
	//
	protected String isuKorNm;
	
	protected Long qty;			// 수량 
	protected Long valAtTrade; 	// 매수금액 합 
	protected Long valAtCur; 	// 평가금액 합 
	
	protected Long proLoss; 		// 	평가손 합 
	protected Float earningRate;	// 	수익률 합 

	public String getIsuKorNm() {
		return isuKorNm;
	}
	public void setIsuKorNm(String isuKorNm) {
		this.isuKorNm = isuKorNm;
	}

	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Long getValAtTrade() {
		return valAtTrade;
	}
	public void setValAtTrade(Long valAtTrade) {
		this.valAtTrade = valAtTrade;
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

	@Override
	public String toString() {
		return "StockGoalExt [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
