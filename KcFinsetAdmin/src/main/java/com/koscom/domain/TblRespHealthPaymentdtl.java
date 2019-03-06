package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespHealthPaymentdtl implements Serializable{

	/**
	 * 납부내역상세
	 */
	private static final long serialVersionUID = 6847997361240073689L;

	protected int    notice_amt_health_insu_amt 		; //고지금액건강보험료
	protected int    ntc_amt_lngtrm_cr_ins_amt 			; //고지금액장기요양보험료
	protected int    order_pay_confirm 					; //순서_납부확인
	protected int    order_pay_detail 					; //순서_납부상세
	protected int    pay_amt_health_insu_amt 			; //납부금액건강보험료
	protected int    pay_amt_longterm_care_insu_amt 	; //납부금액장기요양보험료
	protected String pay_yearmonth 						; //납부연월
	protected String register_dt 						; //등록일시
	protected int    seq_health_insu 					; //일련번호_건강보험
	
	public int getNotice_amt_health_insu_amt() {
		return notice_amt_health_insu_amt;
	}




	public void setNotice_amt_health_insu_amt(int notice_amt_health_insu_amt) {
		this.notice_amt_health_insu_amt = notice_amt_health_insu_amt;
	}




	public int getNtc_amt_lngtrm_cr_ins_amt() {
		return ntc_amt_lngtrm_cr_ins_amt;
	}




	public void setNtc_amt_lngtrm_cr_ins_amt(int ntc_amt_lngtrm_cr_ins_amt) {
		this.ntc_amt_lngtrm_cr_ins_amt = ntc_amt_lngtrm_cr_ins_amt;
	}




	public int getOrder_pay_confirm() {
		return order_pay_confirm;
	}




	public void setOrder_pay_confirm(int order_pay_confirm) {
		this.order_pay_confirm = order_pay_confirm;
	}




	public int getOrder_pay_detail() {
		return order_pay_detail;
	}




	public void setOrder_pay_detail(int order_pay_detail) {
		this.order_pay_detail = order_pay_detail;
	}




	public int getPay_amt_health_insu_amt() {
		return pay_amt_health_insu_amt;
	}




	public void setPay_amt_health_insu_amt(int pay_amt_health_insu_amt) {
		this.pay_amt_health_insu_amt = pay_amt_health_insu_amt;
	}




	public int getPay_amt_longterm_care_insu_amt() {
		return pay_amt_longterm_care_insu_amt;
	}




	public void setPay_amt_longterm_care_insu_amt(int pay_amt_longterm_care_insu_amt) {
		this.pay_amt_longterm_care_insu_amt = pay_amt_longterm_care_insu_amt;
	}




	public String getPay_yearmonth() {
		return pay_yearmonth;
	}




	public void setPay_yearmonth(String pay_yearmonth) {
		this.pay_yearmonth = pay_yearmonth;
	}




	public String getRegister_dt() {
		return register_dt;
	}




	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}




	public int getSeq_health_insu() {
		return seq_health_insu;
	}




	public void setSeq_health_insu(int seq_health_insu) {
		this.seq_health_insu = seq_health_insu;
	}




	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
