package com.koscom.scrap.model.sub;

import java.io.Serializable;

public class LoanAnListVO implements Serializable{
	
	private static final long serialVersionUID = 2470016823891491176L;
	String AN = "";					//계좌번호,
	String TYPE = "";				//예금종류,
	String LOAN_BALANCE = "";		//대출잔액,
	String LOAN_CEILING = "";		//대출한도액,
	String NEW_DATE = "";			//신규일자,
	String LAST_DATE = "";			//만기일자,
	String LENDING_RATE = "";		//대출금리,
	String FEW_DAYS = "";			//최종이수일자,
	String INTEREST_DATE = "";		//이자납입일
	
	private String id_frt; //최초입력아이디
	private String dt_frt; //최초입력시간
	private String id_lst; //최종수정아이디
	private String dt_lst; //최종수정시간
	
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
	public String getLOAN_BALANCE() {
		return LOAN_BALANCE;
	}
	public void setLOAN_BALANCE(String lOAN_BALANCE) {
		LOAN_BALANCE = lOAN_BALANCE;
	}
	public String getLOAN_CEILING() {
		return LOAN_CEILING;
	}
	public void setLOAN_CEILING(String lOAN_CEILING) {
		LOAN_CEILING = lOAN_CEILING;
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
	public String getLENDING_RATE() {
		return LENDING_RATE;
	}
	public void setLENDING_RATE(String lENDING_RATE) {
		LENDING_RATE = lENDING_RATE;
	}
	public String getFEW_DAYS() {
		return FEW_DAYS;
	}
	public void setFEW_DAYS(String fEW_DAYS) {
		FEW_DAYS = fEW_DAYS;
	}
	public String getINTEREST_DATE() {
		return INTEREST_DATE;
	}
	public void setINTEREST_DATE(String iNTEREST_DATE) {
		INTEREST_DATE = iNTEREST_DATE;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	
	
}