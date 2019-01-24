package com.koscom.diags.model;

import java.io.Serializable;

public class InvestSurvey implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5166033122507585874L;

	//
	private String noPerson; //회원번호
    private String investYr; //투자경험 
    private String stockSelect; // 종목선정방법 
    private String riskLimit;	// 리스크한계 
    private String holdPeriod;	// 평균보유예정기간
    private Integer stockProfit;	// 종목목표수익률 
    private String yrProfit;		// 연간목표수익률 
    private String investTime;	// 주당투자시간 
    private String losscut;		// 로스컷
    private String regDate;		// 등록일자
    private String modDate;		// 수정일자 

	public String getNoPerson() {
		return noPerson;
	}
	public void setNoPerson(String noPerson) {
		this.noPerson = noPerson;
	}
	public String getInvestYr() {
		return investYr;
	}
	public void setInvestYr(String investYr) {
		this.investYr = investYr;
	}
	public String getStockSelect() {
		return stockSelect;
	}
	public void setStockSelect(String stockSelect) {
		this.stockSelect = stockSelect;
	}
	public String getRiskLimit() {
		return riskLimit;
	}
	public void setRiskLimit(String riskLimit) {
		this.riskLimit = riskLimit;
	}
	public String getHoldPeriod() {
		return holdPeriod;
	}
	public void setHoldPeriod(String holdPeriod) {
		this.holdPeriod = holdPeriod;
	}
	public Integer getStockProfit() {
		return stockProfit;
	}

	public void setStockProfit(Integer stockProfit) {
		this.stockProfit = stockProfit;
	}
	public String getYrProfit() {
		return yrProfit;
	}
	public void setYrProfit(String yrProfit) {
		this.yrProfit = yrProfit;
	}
	public String getInvestTime() {
		return investTime;
	}
	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}
	public String getLosscut() {
		return losscut;
	}
	public void setLosscut(String losscut) {
		this.losscut = losscut;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	
	public int getHoldGoal() {
		// 30 : 1일 이내 : 1
		// 31 : 1~3    : 2
		// 32 : 3일~1주 : 5
		// 33 : 1~2 주  : 10
		// 34 : 2주~1개월 : 21
		// 35 : 1~3개월   : 60
		// 36 : 3~6개월   : 135
		// 37 : 6개월~1년 : 270
		// 38 : 1년~2년   : 18*30
		// 39 : 2년~5년   : 42*30
		// 40 : 5~10년    : 90*30
		// 41 : 10년 이상  : 120*30
		if("30".equals(holdPeriod)) return 1;
		else if("31".equals(holdPeriod)) return 2;
		else if("32".equals(holdPeriod)) return 5;
		else if("33".equals(holdPeriod)) return 10;
		else if("34".equals(holdPeriod)) return 21;
		else if("35".equals(holdPeriod)) return 60;
		else if("36".equals(holdPeriod)) return 135;
		else if("37".equals(holdPeriod)) return 270;
		else if("38".equals(holdPeriod)) return 18*30;
		else if("39".equals(holdPeriod)) return 42*30;
		else if("40".equals(holdPeriod)) return 90*30;
		else if("41".equals(holdPeriod)) return 120*30;
		else return 0;
	}

	@Override
	public String toString() {
		return "InvestSurvey [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
