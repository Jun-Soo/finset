package com.koscom.consume.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeSettingVO implements Serializable{

	private static final long serialVersionUID = 535713890966360398L;
	
	private String no_person;		//회원관리번호
	private String dt_basic;		//기준일
	private String yn_installment;	//할부분할여부
	
	public ConsumeSettingVO() {
	}
	public ConsumeSettingVO(String no_person, String dt_basic,
			String yn_installment) {
		this.no_person = no_person;
		this.dt_basic = dt_basic;
		this.yn_installment = yn_installment;
	}
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getDt_basic() {
		return dt_basic;
	}
	public void setDt_basic(String dt_basic) {
		this.dt_basic = dt_basic;
	}
	public String getYn_installment() {
		return yn_installment;
	}
	public void setYn_installment(String yn_installment) {
		this.yn_installment = yn_installment;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}