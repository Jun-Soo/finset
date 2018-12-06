package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrCardInfoVO implements Serializable{
	private static final long serialVersionUID = 7648621072275545015L;
	protected String no_person= "";				//회원고유번호
	protected String cd_fc= "";					//금융사코드
	@SerializedName("NO_CARD")
	protected String no_card= "";				//카드번호
	@SerializedName("NM_CARD")
	protected String nm_card= "";				//카드명
	@SerializedName("DT_PAYMENT")
	protected String dt_payment= "";			//결제일
	@SerializedName("AMT_PAYMENT")
	protected String amt_payment= "";			//당월결제액
	@SerializedName("FC_MEMBER")
	protected String fc_member= "";				//회원사
	protected String yn_delete= "";				//삭제여부
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
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
	}
	public String getNm_card() {
		return nm_card;
	}
	public void setNm_card(String nm_card) {
		this.nm_card = nm_card;
	}
	public String getDt_payment() {
		return dt_payment;
	}
	public void setDt_payment(String dt_payment) {
		this.dt_payment = dt_payment;
	}
	public String getAmt_payment() {
		return amt_payment;
	}
	public void setAmt_payment(String amt_payment) {
		this.amt_payment = amt_payment;
	}
	public String getFc_member() {
		return fc_member;
	}
	public void setFc_member(String fc_member) {
		this.fc_member = fc_member;
	}
	public String getYn_delete() {
		return yn_delete;
	}
	public void setYn_delete(String yn_delete) {
		this.yn_delete = yn_delete;
	}
}
