package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonSetInfoVO implements Serializable{
	
	private static final long serialVersionUID = -2454040683902763197L;
	
	private String	no_person;			//회원관리번호
	private String	dt_basic;			//기준일
	private String	yn_installment;		//할부 분할 여부
	private String	id_frt;				
	private Date	dt_frt;
	private String	id_lst;
	private Date	dt_lst;
	
	public PersonSetInfoVO() {
	}

	public PersonSetInfoVO(String no_person, String dt_basic,
			String yn_installment, String id_frt, Date dt_frt, String id_lst,
			Date dt_lst) {
		this.no_person = no_person;
		this.dt_basic = dt_basic;
		this.yn_installment = yn_installment;
		this.id_frt = id_frt;
		this.dt_frt = dt_frt;
		this.id_lst = id_lst;
		this.dt_lst = dt_lst;
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
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public Date getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(Date dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public Date getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(Date dt_lst) {
		this.dt_lst = dt_lst;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
