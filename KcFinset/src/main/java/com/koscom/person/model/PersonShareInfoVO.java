package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.domain.PersonShareInfo;

public class PersonShareInfoVO extends PersonShareInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7508496413935175355L;
	
	private String yn_itgt_mngm; //통합관리항목 존재여부

	//소득정보
	private String cd_etm_basic; //추정기준코드
	private String nm_etm_basic; //추정기준코드명
	private String std_year; //소득기준년도
	private String amt_etm_income; //연소득

	private String setting_mode; //공유설정mode(00 - 공유요청 / 01-공유재요청 / 02-허용/거절 / 03-변경/해지 / 04-업데이트요청)
	
	public String getYn_itgt_mngm() {
		return yn_itgt_mngm;
	}
	public void setYn_itgt_mngm(String yn_itgt_mngm) {
		this.yn_itgt_mngm = yn_itgt_mngm;
	}
	public String getCd_etm_basic() {
		return cd_etm_basic;
	}
	public void setCd_etm_basic(String cd_etm_basic) {
		this.cd_etm_basic = cd_etm_basic;
	}
	public String getNm_etm_basic() {
		return nm_etm_basic;
	}
	public void setNm_etm_basic(String nm_etm_basic) {
		this.nm_etm_basic = nm_etm_basic;
	}
	public String getStd_year() {
		return std_year;
	}
	public void setStd_year(String std_year) {
		this.std_year = std_year;
	}
	public String getAmt_etm_income() {
		return amt_etm_income;
	}
	public void setAmt_etm_income(String amt_etm_income) {
		this.amt_etm_income = amt_etm_income;
	}
	public String getSetting_mode() {
		return setting_mode;
	}
	public void setSetting_mode(String setting_mode) {
		this.setting_mode = setting_mode;
	}
	
}
