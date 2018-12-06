package com.koscom.person.model;

import java.io.Serializable;

public class PersonEtmIncomeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5375593577954560956L;

	
	private int	seq;	
	private String no_person = "";		
	private String cd_etm_basic = "";		//10:건강보험, 20:국민연금, 30:KCB, 40:국세청
	private String std_year = "";	
	private String amt_etm_income = "";
	private String ymd_etm_basic = "";
	public String getNo_person() {
		return no_person;
	}
	public String getCd_etm_basic() {
		return cd_etm_basic;
	}
	public String getAmt_etm_income() {
		return amt_etm_income;
	}
	public String getYmd_etm_basic() {
		return ymd_etm_basic;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public void setCd_etm_basic(String cd_etm_basic) {
		this.cd_etm_basic = cd_etm_basic;
	}
	public void setAmt_etm_income(String amt_etm_income) {
		this.amt_etm_income = amt_etm_income;
	}
	public void setYmd_etm_basic(String ymd_etm_basic) {
		this.ymd_etm_basic = ymd_etm_basic;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getStd_year() {
		return std_year;
	}
	public void setStd_year(String std_year) {
		this.std_year = std_year;
	}
	

}
