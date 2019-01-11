package com.koscom.consume.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PaymentDetailVO implements Serializable{
	
	private static final long serialVersionUID = -578559704591747648L;
	
	private String dt_use;				// 사용일
	private String nm_member;			// 가맹정 명
	private String month_installment;	// 할부 개월
	private String amt_charge;			// 청구 금액
	
	public PaymentDetailVO() {
	}
	public PaymentDetailVO(String dt_use, String nm_member,
			String month_installment, String amt_charge) {
		this.dt_use = dt_use;
		this.nm_member = nm_member;
		this.month_installment = month_installment;
		this.amt_charge = amt_charge;
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
	public String getAmt_charge() {
		return amt_charge;
	}
	public void setAmt_charge(String amt_charge) {
		this.amt_charge = amt_charge;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}