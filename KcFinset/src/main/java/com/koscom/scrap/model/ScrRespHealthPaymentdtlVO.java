package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrRespHealthPaymentdtlVO implements Serializable{
	private static final long serialVersionUID = 588417226926190290L;
	protected String no_person= "";						//회원고유번호
	@SerializedName("NO_PAYER")
	protected String no_payer= "";						//납부자번호
	@SerializedName("PAY_YYYYMM")
	protected String pay_yyyymm= "";					//납부연월
	@SerializedName("AMT_NT_HEALTH_INSU")
	protected String amt_nt_health_insu= "";			//고지금액건강보험료
	@SerializedName("AMT_NT_LNGTRM_CR_INS")
	protected String amt_nt_lngtrm_cr_ins= "";			//고지금액장기요양보험료
	@SerializedName("AMT_PAY_HEALTH_INSU")
	protected String amt_pay_health_insu= "";			//납부금액건강보험료
	@SerializedName("AMT_PAY_LONGTERM_CARE_INSU")
	protected String amt_pay_longterm_care_insu= "";	//소득고지금액건강보험료
	@SerializedName("AMT_ICNT_HEALTH_INSU")
	protected String amt_icnt_health_insu= "";			//소득고지금액장기요양보험료
	@SerializedName("AMT_ICNT_LNGTRM_CR_INS")
	protected String amt_icnt_lngtrm_cr_ins= "";		//소득납부금액건강보험료
	@SerializedName("AMT_ICPAY_HEALTH_INSU")
	protected String amt_icpay_health_insu= "";			//소득납부금액장기요양보험료
	@SerializedName("AMT_ICPAY_LONGTERM_CARE_INSU")
	protected String amt_icpay_longterm_care_insu= "";	//납부금액장기요양보험료
	protected String dt_frt= "";						//등록일시
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_payer() {
		return no_payer;
	}
	public void setNo_payer(String no_payer) {
		this.no_payer = no_payer;
	}
	public String getPay_yyyymm() {
		return pay_yyyymm;
	}
	public void setPay_yyyymm(String pay_yyyymm) {
		this.pay_yyyymm = pay_yyyymm;
	}
	public String getAmt_nt_health_insu() {
		return amt_nt_health_insu;
	}
	public void setAmt_nt_health_insu(String amt_nt_health_insu) {
		this.amt_nt_health_insu = amt_nt_health_insu;
	}
	public String getAmt_nt_lngtrm_cr_ins() {
		return amt_nt_lngtrm_cr_ins;
	}
	public void setAmt_nt_lngtrm_cr_ins(String amt_nt_lngtrm_cr_ins) {
		this.amt_nt_lngtrm_cr_ins = amt_nt_lngtrm_cr_ins;
	}
	public String getAmt_pay_health_insu() {
		return amt_pay_health_insu;
	}
	public void setAmt_pay_health_insu(String amt_pay_health_insu) {
		this.amt_pay_health_insu = amt_pay_health_insu;
	}
	public String getAmt_pay_longterm_care_insu() {
		return amt_pay_longterm_care_insu;
	}
	public void setAmt_pay_longterm_care_insu(String amt_pay_longterm_care_insu) {
		this.amt_pay_longterm_care_insu = amt_pay_longterm_care_insu;
	}
	public String getAmt_icnt_health_insu() {
		return amt_icnt_health_insu;
	}
	public void setAmt_icnt_health_insu(String amt_icnt_health_insu) {
		this.amt_icnt_health_insu = amt_icnt_health_insu;
	}
	public String getAmt_icnt_lngtrm_cr_ins() {
		return amt_icnt_lngtrm_cr_ins;
	}
	public void setAmt_icnt_lngtrm_cr_ins(String amt_icnt_lngtrm_cr_ins) {
		this.amt_icnt_lngtrm_cr_ins = amt_icnt_lngtrm_cr_ins;
	}
	public String getAmt_icpay_health_insu() {
		return amt_icpay_health_insu;
	}
	public void setAmt_icpay_health_insu(String amt_icpay_health_insu) {
		this.amt_icpay_health_insu = amt_icpay_health_insu;
	}
	public String getAmt_icpay_longterm_care_insu() {
		return amt_icpay_longterm_care_insu;
	}
	public void setAmt_icpay_longterm_care_insu(String amt_icpay_longterm_care_insu) {
		this.amt_icpay_longterm_care_insu = amt_icpay_longterm_care_insu;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
