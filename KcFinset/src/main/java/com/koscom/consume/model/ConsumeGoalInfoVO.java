package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeGoalInfoVO implements Serializable{
	
	private static final long serialVersionUID = -3799625681668984065L;
	
	private String 	no_person;
	private String	req_yyymm;
	private String	amt_budget;
	private String	amt_expense;
	private String	id_frt;
	private Date	dt_frt;
	private String	id_lst;
	private Date	dt_lst;
	
	public ConsumeGoalInfoVO() {}

	public ConsumeGoalInfoVO(String no_person, String req_yyymm,
			String amt_budget, String amt_expense, String id_frt, Date dt_frt,
			String id_lst, Date dt_lst) {
		this.no_person = no_person;
		this.req_yyymm = req_yyymm;
		this.amt_budget = amt_budget;
		this.amt_expense = amt_expense;
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
	public String getReq_yyymm() {
		return req_yyymm;
	}
	public void setReq_yyymm(String req_yyymm) {
		this.req_yyymm = req_yyymm;
	}
	public String getAmt_budget() {
		return amt_budget;
	}
	public void setAmt_budget(String amt_budget) {
		this.amt_budget = amt_budget;
	}
	public String getAmt_expense() {
		return amt_expense;
	}
	public void setAmt_expense(String amt_expense) {
		this.amt_expense = amt_expense;
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
