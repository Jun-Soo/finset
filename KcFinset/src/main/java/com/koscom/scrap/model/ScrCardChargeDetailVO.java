package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrCardChargeDetailVO implements Serializable{
	private static final long serialVersionUID = 1496122284878829032L;
	protected String no_person= "";				//회원고유번호
	protected String cd_fc= "";					//금융사코드
	@SerializedName("CHARGE_YYYYMM")
	protected String charge_yyyymm= "";			//청구년월
	@SerializedName("TYPE_CARD")
	protected String type_card= "";				//카드종류
	@SerializedName("NO_CARD")
	protected String no_card= "";				//카드번호
	@SerializedName("DT_PAYMENT")
	protected String dt_payment= "";			//결제일
	@SerializedName("DT_USE")
	protected String dt_use= "";				//이용일자
	@SerializedName("NM_MEMBER")
	protected String nm_member= "";				//가맹점명
	@SerializedName("MONTH_INSTALLMENT")
	protected String month_installment= "";		//할부개월
	@SerializedName("DEPOSIT_NUMBER")
	protected String deposit_number= "";		//입금회차
	@SerializedName("USAGE_FEE")
	protected String usage_fee= "";				//이용대금
	@SerializedName("AMT_CHARGE")
	protected String amt_charge= "";			//청구금액
	@SerializedName("FEES")
	protected String fees= "";					//수수료
	@SerializedName("AMT_AFTER_PAYMENT")
	protected String amt_after_payment= "";		//결제후잔액
	@SerializedName("NO_BIZ_MEMBER")
	protected String no_biz_member= "";			//가맹점사업자번호
	@SerializedName("TYPE_BIZ_MEMBER")
	protected String type_biz_member= "";		//가맹점업종
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
	public String getCharge_yyyymm() {
		return charge_yyyymm;
	}
	public void setCharge_yyyymm(String charge_yyyymm) {
		this.charge_yyyymm = charge_yyyymm;
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
	public String getDt_payment() {
		return dt_payment;
	}
	public void setDt_payment(String dt_payment) {
		this.dt_payment = dt_payment;
	}
	public String getDt_use() {
		return dt_use;
	}
	public void setDt_use(String dt_use) {
		this.dt_use = dt_use;
	}
	public String getNm_member() {
		return nm_member;
	}
	public void setNm_member(String nm_member) {
		this.nm_member = nm_member;
	}
	public String getMonth_installment() {
		return month_installment;
	}
	public void setMonth_installment(String month_installment) {
		this.month_installment = month_installment;
	}
	public String getDeposit_number() {
		return deposit_number;
	}
	public void setDeposit_number(String deposit_number) {
		this.deposit_number = deposit_number;
	}
	public String getUsage_fee() {
		return usage_fee;
	}
	public void setUsage_fee(String usage_fee) {
		this.usage_fee = usage_fee;
	}
	public String getAmt_charge() {
		return amt_charge;
	}
	public void setAmt_charge(String amt_charge) {
		this.amt_charge = amt_charge;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getAmt_after_payment() {
		return amt_after_payment;
	}
	public void setAmt_after_payment(String amt_after_payment) {
		this.amt_after_payment = amt_after_payment;
	}
	public String getNo_biz_member() {
		return no_biz_member;
	}
	public void setNo_biz_member(String no_biz_member) {
		this.no_biz_member = no_biz_member;
	}
	public String getType_biz_member() {
		return type_biz_member;
	}
	public void setType_biz_member(String type_biz_member) {
		this.type_biz_member = type_biz_member;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
