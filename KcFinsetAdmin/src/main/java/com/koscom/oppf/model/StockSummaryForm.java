package com.koscom.oppf.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class StockSummaryForm extends SearchForm implements Serializable{
	private static final long serialVersionUID = -7093613680960968861L;
	private String dt;	//조회일자
	private String no_person;	// 사용자일련번호
	private String accno;	//가상계좌번호
	private String secuno;	//증권사번호
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getSecuno() {
		return secuno;
	}
	public void setSecuno(String secuno) {
		this.secuno = secuno;
	}
}
