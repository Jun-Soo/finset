package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrRespCashReceiptVO implements Serializable{
	private static final long serialVersionUID = -666315099798736428L;
	protected String no_person= "";				//회원고유번호
	@SerializedName("YMD_DEAL")
	protected String ymd_deal= "";				//거래일시
	@SerializedName("TIME_DEAL")
	protected String time_deal= "";				//거래시간
	@SerializedName("NM_AFFILIATE")
	protected String nm_affiliate= "";			//가맹점명
	@SerializedName("AMT_USE")
	protected String amt_use= "";				//사용금액
	@SerializedName("NO_APPROVED")
	protected String no_approval= "";			//승인번호
	@SerializedName("TYPE_ID_CHECK")
	protected String type_id_check= "";			//신분확인수단
	@SerializedName("TYPE_DEAL")
	protected String type_deal= "";				//거래구분
	@SerializedName("YN_DEDUCTION")
	protected String yn_deduction= "";			//공제여부
	@SerializedName("TYPE_ISSUE")
	protected String type_issue= "";			//발행구분
	protected String dt_frt= "";				//발행구분
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getYmd_deal() {
		return ymd_deal;
	}
	public void setYmd_deal(String ymd_deal) {
		this.ymd_deal = ymd_deal;
	}
	public String getTime_deal() {
		return time_deal;
	}
	public void setTime_deal(String time_deal) {
		this.time_deal = time_deal;
	}
	public String getNm_affiliate() {
		return nm_affiliate;
	}
	public void setNm_affiliate(String nm_affiliate) {
		this.nm_affiliate = nm_affiliate;
	}
	public String getAmt_use() {
		return amt_use;
	}
	public void setAmt_use(String amt_use) {
		this.amt_use = amt_use;
	}
	public String getNo_approval() {
		return no_approval;
	}
	public void setNo_approval(String no_approval) {
		this.no_approval = no_approval;
	}
	public String getType_id_check() {
		return type_id_check;
	}
	public void setType_id_check(String type_id_check) {
		this.type_id_check = type_id_check;
	}
	public String getType_deal() {
		return type_deal;
	}
	public void setType_deal(String type_deal) {
		this.type_deal = type_deal;
	}
	public String getYn_deduction() {
		return yn_deduction;
	}
	public void setYn_deduction(String yn_deduction) {
		this.yn_deduction = yn_deduction;
	}
	public String getType_issue() {
		return type_issue;
	}
	public void setType_issue(String type_issue) {
		this.type_issue = type_issue;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
