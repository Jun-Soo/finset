/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180724~26
 * 소비목표 관리 데이터
 */
package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeGoalVO implements Serializable {
	
	private static final long serialVersionUID = -7640575271489371205L;
	
	private String 	no_person;				// 회원관리번호
	private String 	req_yyyymm;				// 기준년월
	private String 	amt_budget;				// 예산금액
	private String 	amt_expense;			// 지출금액
	private String 	dt_basic;				// 기준일
	private String 	id_frt;					// 최초입력아이디
	private Date 	dt_frt;					// 최초입력시간
	private String 	id_lst;					// 최종수정아이디
	private Date 	dt_lst;					// 최종수정시간

	public ConsumeGoalVO() {
	}
	public ConsumeGoalVO(String no_person, String req_yyyymm, String amt_budget, String amt_expense, String dt_basic, String id_frt, Date dt_frt, String id_lst, Date dt_lst) {
		this.no_person = no_person;
		this.req_yyyymm = req_yyyymm;
		this.amt_budget = amt_budget;
		this.amt_expense = amt_expense;
		this.dt_basic = dt_basic;
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
	public String getReq_yyyymm() {
		return req_yyyymm;
	}
	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
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
	public String getDt_basic() {
		return dt_basic;
	}
	public void setDt_basic(String dt_basic) {
		this.dt_basic = dt_basic;
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