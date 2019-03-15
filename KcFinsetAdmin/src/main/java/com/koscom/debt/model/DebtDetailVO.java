package com.koscom.debt.model;

import java.io.Serializable;

public class DebtDetailVO implements Serializable{

	private static final long serialVersionUID = -8150626461527211168L;

	private String yyyy ="";	
	private String mm_dot_dd ="";
	private String amt_pni = "0";	//원리금
	private String amt_p ="0";		//원금
	private String amt_i ="0";		//이자
	private String cd_account_before ="";	//최근 12개월 상환 상태코드
	private String year_display_yn = "N";
	
	public String getAmt_pni() {
		return amt_pni;
	}
	public String getAmt_p() {
		return amt_p;
	}
	public String getAmt_i() {
		return amt_i;
	}
	public String getCd_account_before() {
		return cd_account_before;
	}
	public String getYear_display_yn() {
		return year_display_yn;
	}
	public void setAmt_pni(String amt_pni) {
		this.amt_pni = amt_pni;
	}
	public void setAmt_p(String amt_p) {
		this.amt_p = amt_p;
	}
	public void setAmt_i(String amt_i) {
		this.amt_i = amt_i;
	}
	public void setCd_account_before(String cd_account_before) {
		this.cd_account_before = cd_account_before;
	}
	public void setYear_display_yn(String year_display_yn) {
		this.year_display_yn = year_display_yn;
	}
	public String getYyyy() {
		return yyyy;
	}
	public String getMm_dot_dd() {
		return mm_dot_dd;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public void setMm_dot_dd(String mm_dot_dd) {
		this.mm_dot_dd = mm_dot_dd;
	}		

}
