package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Kcb062Info implements Serializable{
	private static final long serialVersionUID = -6928143805631875274L;
	protected String seq_kcb_response= "";	//조회일련번호KCB_062_INFO
	protected String no_person= "";	//회원관리번호KCB_062_INFO
	protected String nm_biz_type= "";	//업종명KCB_062_INFO
	protected String nm_trade= "";	//거래기관명KCB_062_INFO
	protected String nm_agency= "";	//관리점명KCB_062_INFO
	protected String ymd_frt_stead_pay= "";	//대지급발생일자KCB_062_INFO
	protected String amt_stead_pay= "";	//대지급금액KCB_062_INFO
	protected String ymd_stead_repay= "";	//대지급상환일자KCB_062_INFO
	protected String amt_repay= "";	//상환금액KCB_062_INFO
	protected String filler= "";	//FILLERKCB_062_INFO
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
	public String getNm_biz_type() {
		return nm_biz_type;
	}
	public void setNm_biz_type(String nm_biz_type) {
		this.nm_biz_type = nm_biz_type;
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
	public String getYmd_frt_stead_pay() {
		return ymd_frt_stead_pay;
	}
	public void setYmd_frt_stead_pay(String ymd_frt_stead_pay) {
		this.ymd_frt_stead_pay = ymd_frt_stead_pay;
	}
	public String getAmt_stead_pay() {
		return amt_stead_pay;
	}
	public void setAmt_stead_pay(String amt_stead_pay) {
		this.amt_stead_pay = amt_stead_pay;
	}
	public String getYmd_stead_repay() {
		return ymd_stead_repay;
	}
	public void setYmd_stead_repay(String ymd_stead_repay) {
		this.ymd_stead_repay = ymd_stead_repay;
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
