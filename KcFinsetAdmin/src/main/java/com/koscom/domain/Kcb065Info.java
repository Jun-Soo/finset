package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Kcb065Info implements Serializable{
	private static final long serialVersionUID = -3162271159522404746L;
	protected String seq_kcb_response= "";	//조회일련번호KCB_065_INFO
	protected String no_person= "";	//회원관리번호KCB_065_INFO
	protected String nm_trade= "";	//거래기관명KCB_065_INFO
	protected String nm_agency= "";	//관리점명KCB_065_INFO
	protected String cd_default= "";	//채무불이행(신용정보사)등록사유코드KCB_065_INFO
	protected String ymd_default= "";	//채무불이행(신용정보사)발생일자KCB_065_INFO
	protected String amt_regist= "";	//등록금액KCB_065_INFO
	protected String amt_delay= "";	//연체금액KCB_065_INFO
	protected String ymd_repay= "";	//상환일자KCB_065_INFO
	protected String amt_repay= "";	//상환금액KCB_065_INFO
	protected String filler= "";	//FILLERKCB_065_INFO
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSeq_kcb_response() {
		return seq_kcb_response;
	}
	public void setSeq_kcb_response(String seq_kcb_response) {
		this.seq_kcb_response = seq_kcb_response;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNm_trade() {
		return nm_trade;
	}
	public void setNm_trade(String nm_trade) {
		this.nm_trade = nm_trade;
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
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
}
