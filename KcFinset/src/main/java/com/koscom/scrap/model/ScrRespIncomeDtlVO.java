package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ScrRespIncomeDtlVO implements Serializable{
	private static final long serialVersionUID = -3249088528069937620L;
	protected String no_person= "";					//회원고유번호
	@SerializedName("CERT_DIVISION")
	protected String cert_division= "";				//증명구분
	@SerializedName("NO_PBLS")
	protected String no_pbls= "";					//발급번호
	@SerializedName("REVERSION_YEAR")
	protected String reversion_year= "";			//귀속연도
	@SerializedName("BIZ_LICENCE")
	protected String biz_licence= "";				//사업자등록번호
	@SerializedName("INCOME_DIVISION")
	protected String income_division= "";			//소득구분
	@SerializedName("CORP_NM")
	protected String corp_nm= "";					//법인명
	@SerializedName("AMT_INCOME")
	protected String amt_income= "";				//소득금액
	@SerializedName("AMT_TOTAL_DECISION_TAX")
	protected String amt_total_decision_tax= "";	//총결정세금금액
	protected String dt_frt= "";					//등록일시
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCert_division() {
		return cert_division;
	}
	public void setCert_division(String cert_division) {
		this.cert_division = cert_division;
	}
	public String getNo_pbls() {
		return no_pbls;
	}
	public void setNo_pbls(String no_pbls) {
		this.no_pbls = no_pbls;
	}
	public String getReversion_year() {
		return reversion_year;
	}
	public void setReversion_year(String reversion_year) {
		this.reversion_year = reversion_year;
	}
	public String getBiz_licence() {
		return biz_licence;
	}
	public void setBiz_licence(String biz_licence) {
		this.biz_licence = biz_licence;
	}
	public String getIncome_division() {
		return income_division;
	}
	public void setIncome_division(String income_division) {
		this.income_division = income_division;
	}
	public String getCorp_nm() {
		return corp_nm;
	}
	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}
	public String getAmt_income() {
		return amt_income;
	}
	public void setAmt_income(String amt_income) {
		this.amt_income = amt_income;
	}
	public String getAmt_total_decision_tax() {
		return amt_total_decision_tax;
	}
	public void setAmt_total_decision_tax(String amt_total_decision_tax) {
		this.amt_total_decision_tax = amt_total_decision_tax;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
