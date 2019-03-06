package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbOverdueDefaultInfo implements Serializable {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;	//회원관리번호
	private String cd_fc;		//금융사코드
	private String nm_agency;	//관리점명
	private String cd_default;	//채무불이행(신용정보사)등록사유코드
	private String ymd_default;	//채무불이행(신용정보사)발생일자
	private String amt_regist;	//등록금액
	private String amt_delay;	//연체금액
	private String ymd_repay;	//상환일자
	private String amt_repay;	//상환금액
	private String id_frt;		//최초입력아이디
	
	public KcbOverdueDefaultInfo() {
	}

	public KcbOverdueDefaultInfo(String no_person, String cd_fc,
			String nm_agency, String cd_default, String ymd_default,
			String amt_regist, String amt_delay, String ymd_repay,
			String amt_repay, String id_frt) {
		this.no_person = no_person;
		this.cd_fc = cd_fc;
		this.nm_agency = nm_agency;
		this.cd_default = cd_default;
		this.ymd_default = ymd_default;
		this.amt_regist = amt_regist;
		this.amt_delay = amt_delay;
		this.ymd_repay = ymd_repay;
		this.amt_repay = amt_repay;
		this.id_frt = id_frt;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getNm_agency() {
		return nm_agency;
	}

	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}

	public String getCd_default() {
		return cd_default;
	}

	public void setCd_default(String cd_default) {
		this.cd_default = cd_default;
	}

	public String getYmd_default() {
		return ymd_default;
	}

	public void setYmd_default(String ymd_default) {
		this.ymd_default = ymd_default;
	}

	public String getAmt_regist() {
		return amt_regist;
	}

	public void setAmt_regist(String amt_regist) {
		this.amt_regist = amt_regist;
	}

	public String getAmt_delay() {
		return amt_delay;
	}

	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}

	public String getYmd_repay() {
		return ymd_repay;
	}

	public void setYmd_repay(String ymd_repay) {
		this.ymd_repay = ymd_repay;
	}

	public String getAmt_repay() {
		return amt_repay;
	}

	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}