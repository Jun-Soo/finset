package com.koscom.consume.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PaymentForm implements Serializable{

	private static final long serialVersionUID = 3624567199963252874L;

	private List<String> 	no_person_list;		//회원 관리 번호 리스트
	private String			charge_yyyymm;		//청구 년월

	public PaymentForm() {
	}
	public PaymentForm(List<String> no_person_list, String charge_yyyymm) {
		this.no_person_list = no_person_list;
		this.charge_yyyymm = charge_yyyymm;
	}
	
	public List<String> getNo_person_list() {
		return no_person_list;
	}
	public void setNo_person_list(List<String> no_person_list) {
		this.no_person_list = no_person_list;
	}
	public String getCharge_yyyymm() {
		return charge_yyyymm;
	}
	public void setCharge_yyyymm(String charge_yyyymm) {
		this.charge_yyyymm = charge_yyyymm;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}