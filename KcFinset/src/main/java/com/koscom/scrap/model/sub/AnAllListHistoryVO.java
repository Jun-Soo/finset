package com.koscom.scrap.model.sub;

import java.io.Serializable;

public class AnAllListHistoryVO implements Serializable{
	private static final long serialVersionUID = 1517231956288388324L;
	long   SEQ_TRAN = 0;		//시퀀스
	String NO_PERSON = "";		//회원고유번호
	String AN = "";				//계좌번호
	String BALANCE = "";		//거래후잔액
	String CODE = "";			//통화코드
	String DATE = "";			//거래일자
	String TIME = "";			//거래시각
	String DEALWAY1 = "";		//거래수단1(ex.신협중앙회)
	String DEALWAY2 = "";		//거래수단2
	String DOC1 = "";			//기재사항1(이경훈)
	String DOC2 = "";			//기재사항2(스마트뱅킹)
	String IN_PAYMENT = "";		//입금액
	String OUT_PAYMENT = "";	//출금액
	String ID_FRT = "";			//admin

	public long getSEQ_TRAN() {
		return SEQ_TRAN;
	}
	public void setSEQ_TRAN(long sEQ_TRAN) {
		SEQ_TRAN = sEQ_TRAN;
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
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
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
	public String getDEALWAY1() {
		return DEALWAY1;
	}
	public void setDEALWAY1(String dEALWAY1) {
		DEALWAY1 = dEALWAY1;
	}
	public String getDEALWAY2() {
		return DEALWAY2;
	}
	public void setDEALWAY2(String dEALWAY2) {
		DEALWAY2 = dEALWAY2;
	}
	public String getDOC1() {
		return DOC1;
	}
	public void setDOC1(String dOC1) {
		DOC1 = dOC1;
	}
	public String getDOC2() {
		return DOC2;
	}
	public void setDOC2(String dOC2) {
		DOC2 = dOC2;
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
	public String getID_FRT() {
		return ID_FRT;
	}
	public void setID_FRT(String iD_FRT) {
		ID_FRT = iD_FRT;
	}	
}