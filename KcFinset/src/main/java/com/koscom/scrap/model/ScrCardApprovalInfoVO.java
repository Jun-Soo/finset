package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrCardApprovalInfoVO implements Serializable{
	private static final long serialVersionUID = 2362517822894897416L;
	protected String no_person= "";				//회원고유번호
	protected String cd_fc= "";					//금융사코드
	@SerializedName("DT_APPROVAL")
	protected String dt_approval= "";			//승인일자
	@SerializedName("TM_APPROVAL")
	protected String tm_approval= "";			//승인시간
	@SerializedName("NO_APPROVAL")
	protected String no_approval= "";			//승인번호
	@SerializedName("TYPE_CARD")
	protected String type_card= "";				//카드종류
	@SerializedName("NO_CARD")
	protected String no_card= "";				//카드번호
	@SerializedName("NM_MEMBER")
	protected String nm_member= "";				//가맹점명
	@SerializedName("TYPE_SALES")
	protected String type_sales= "";			//매출종류
	@SerializedName("PERIOD_INSTALLMENT")
	protected String period_installment= "";	//할부기간
	@SerializedName("AMT_APPROVAL")
	protected String amt_approval= "";			//승인금액
	@SerializedName("YMD_CANCEL")
	protected String ymd_cancel= "";			//취소년월일
	@SerializedName("DT_PAYMENT_DUE")
	protected String dt_payment_due= "";		//결제예정일
	@SerializedName("NO_BIZ_MEMBER")
	protected String no_biz_member= "";			//가맹점사업자번호
	@SerializedName("CD_BIZ_MEMBER")
	protected String cd_biz_member= "";			//가맹점코드
	@SerializedName("TYPE_BIZ_MEMBER")
	protected String type_biz_member= "";		//가맹점업종
	@SerializedName("CD_CURRENCY")
	protected String cd_currency= "";			//통화코드
	@SerializedName("CD_IN_OUT")
	protected String cd_in_out= "";				//국내외구분
	protected String dt_frt= "";				//등록일시
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
	public String getDt_approval() {
		return dt_approval;
	}
	public void setDt_approval(String dt_approval) {
		this.dt_approval = dt_approval;
	}
	public String getTm_approval() {
		return tm_approval;
	}
	public void setTm_approval(String tm_approval) {
		this.tm_approval = tm_approval;
	}
	public String getNo_approval() {
		return no_approval;
	}
	public void setNo_approval(String no_approval) {
		this.no_approval = no_approval;
	}
	public String getType_card() {
		return type_card;
	}
	public void setType_card(String type_card) {
		this.type_card = type_card;
	}
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
	}
	public String getNm_member() {
		return nm_member;
	}
	public void setNm_member(String nm_member) {
		this.nm_member = nm_member;
	}
	public String getType_sales() {
		return type_sales;
	}
	public void setType_sales(String type_sales) {
		this.type_sales = type_sales;
	}
	public String getPeriod_installment() {
		return period_installment;
	}
	public void setPeriod_installment(String period_installment) {
		this.period_installment = period_installment;
	}
	public String getAmt_approval() {
		return amt_approval;
	}
	public void setAmt_approval(String amt_approval) {
		this.amt_approval = amt_approval;
	}
	public String getYmd_cancel() {
		return ymd_cancel;
	}
	public void setYmd_cancel(String ymd_cancel) {
		this.ymd_cancel = ymd_cancel;
	}
	public String getDt_payment_due() {
		return dt_payment_due;
	}
	public void setDt_payment_due(String dt_payment_due) {
		this.dt_payment_due = dt_payment_due;
	}
	public String getNo_biz_member() {
		return no_biz_member;
	}
	public void setNo_biz_member(String no_biz_member) {
		this.no_biz_member = no_biz_member;
	}
	public String getCd_biz_member() {
		return cd_biz_member;
	}
	public void setCd_biz_member(String cd_biz_member) {
		this.cd_biz_member = cd_biz_member;
	}
	public String getType_biz_member() {
		return type_biz_member;
	}
	public void setType_biz_member(String type_biz_member) {
		this.type_biz_member = type_biz_member;
	}
	public String getCd_currency() {
		return cd_currency;
	}
	public void setCd_currency(String cd_currency) {
		this.cd_currency = cd_currency;
	}
	public String getCd_in_out() {
		return cd_in_out;
	}
	public void setCd_in_out(String cd_in_out) {
		this.cd_in_out = cd_in_out;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
