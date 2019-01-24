package com.koscom.diags.model;

import java.io.Serializable;

public class StockGoal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	//
	protected String noPerson; //회원번호
	protected String isincode; //종목코드  

	protected Integer profitGoal;	// 목표수익률_% 
	protected Integer holdGoal;	// 예상보유기간_일  
    
	protected String regDate;		// 등록일자
	protected String modDate;		// 수정일자 

	public String getNoPerson() {
		return noPerson;
	}
	public void setNoPerson(String noPerson) {
		this.noPerson = noPerson;
	}

	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	public Integer getProfitGoal() {
		return profitGoal;
	}
	public void setProfitGoal(Integer profitGoal) {
		this.profitGoal = profitGoal;
	}
	public Integer getHoldGoal() {
		return holdGoal;
	}
	public void setHoldGoal(Integer holdGoal) {
		this.holdGoal = holdGoal;
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
	@Override
	public String toString() {
		return "StockGoal [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
