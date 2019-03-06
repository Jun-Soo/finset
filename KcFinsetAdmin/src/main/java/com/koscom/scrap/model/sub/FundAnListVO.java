package com.koscom.scrap.model.sub;

import java.io.Serializable;

public class FundAnListVO implements Serializable{
	private static final long serialVersionUID = 483543054932889570L;
	String AN = "";			//계좌번호,
	String TYPE = "";			//예금종류,
	String PRINCIPAL = "";			//투자원금,
	String EVALUATION_AMOUNT = "";			//평가금액,
	String NEW_DATE = "";			//신규일자,
	String LAST_DATE = "";			//만기일자,
	String YIELD = "";			//수익률
	public String getAN() {
		return AN;
	}
	public void setAN(String aN) {
		AN = aN;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getPRINCIPAL() {
		return PRINCIPAL;
	}
	public void setPRINCIPAL(String pRINCIPAL) {
		PRINCIPAL = pRINCIPAL;
	}
	public String getEVALUATION_AMOUNT() {
		return EVALUATION_AMOUNT;
	}
	public void setEVALUATION_AMOUNT(String eVALUATION_AMOUNT) {
		EVALUATION_AMOUNT = eVALUATION_AMOUNT;
	}
	public String getNEW_DATE() {
		return NEW_DATE;
	}
	public void setNEW_DATE(String nEW_DATE) {
		NEW_DATE = nEW_DATE;
	}
	public String getLAST_DATE() {
		return LAST_DATE;
	}
	public void setLAST_DATE(String lAST_DATE) {
		LAST_DATE = lAST_DATE;
	}
	public String getYIELD() {
		return YIELD;
	}
	public void setYIELD(String yIELD) {
		YIELD = yIELD;
	}
}