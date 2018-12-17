package com.koscom.scrap.model.sub;

import java.io.Serializable;

public class DepositAnListHistoryVO implements Serializable{
	private static final long serialVersionUID = 2217068015059146116L;
	long   SEQ_SVNG = 0;		//시퀀스
	String NO_PERSON = "";		//회원고유번호
	String AN = "";				//계좌번호
	String DATE = "";			//거래일자
	String TIME = "";			//거래시각
	String BATCH = "";			//거래회차
	String MONTH = "";			//거래월분
	String IN_PAYMENT = "";		//입금액
	String OUT_PAYMENT = "";	//출금액
	String BALANCE = "";		//거래후잔액
	String DESCRIPTION = "";	//적요
	String DISTRIBUTOR = "";	//취급점
	String ID_FRT = "";			//admin
	
	public long getSEQ_SVNG() {
		return SEQ_SVNG;
	}
	public void setSEQ_SVNG(long sEQ_SVNG) {
		SEQ_SVNG = sEQ_SVNG;
	}
	public String getNO_PERSON() {
		return NO_PERSON;
	}
	public void setNO_PERSON(String nO_PERSON) {
		NO_PERSON = nO_PERSON;
	}
	public String getAN() {
		return AN;
	}
	public void setAN(String aN) {
		AN = aN;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getBATCH() {
		return BATCH;
	}
	public void setBATCH(String bATCH) {
		BATCH = bATCH;
	}
	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	public String getIN_PAYMENT() {
		return IN_PAYMENT;
	}
	public void setIN_PAYMENT(String iN_PAYMENT) {
		IN_PAYMENT = iN_PAYMENT;
	}
	public String getOUT_PAYMENT() {
		return OUT_PAYMENT;
	}
	public void setOUT_PAYMENT(String oUT_PAYMENT) {
		OUT_PAYMENT = oUT_PAYMENT;
	}
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getDISTRIBUTOR() {
		return DISTRIBUTOR;
	}
	public void setDISTRIBUTOR(String dISTRIBUTOR) {
		DISTRIBUTOR = dISTRIBUTOR;
	}
	public String getID_FRT() {
		return ID_FRT;
	}
	public void setID_FRT(String iD_FRT) {
		ID_FRT = iD_FRT;
	}

		
}