package com.koscom.consume.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PaymentForm implements Serializable{

	private static final long serialVersionUID = 3624567199963252874L;

	private String			no_person;			//회원 관리 번호
	private List<String> 	no_person_list;		//회원 관리 번호 리스트
	private String			charge_yyyymm;		//청구 년월
	private String 			no_card;			//카드 번호
	private String			cd_fc;				//금융사 관리 코드

	public PaymentForm() {
	}
	public PaymentForm(String no_person, List<String> no_person_list,
			String charge_yyyymm, String no_card, String cd_fc) {
		this.no_person = no_person;
		this.no_person_list = no_person_list;
		this.charge_yyyymm = charge_yyyymm;
		this.no_card = no_card;
		this.cd_fc = cd_fc;
	}
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
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
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}