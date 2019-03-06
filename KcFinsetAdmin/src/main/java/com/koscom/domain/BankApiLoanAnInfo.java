package com.koscom.domain;

import java.io.Serializable;

public class BankApiLoanAnInfo implements Serializable{
	private static final long serialVersionUID = 8838561456411948547L;
	protected String no_person= "";				//회원고유번호
	protected String an= "";				//계좌번호
	protected String type= "";				//예금종류
	protected String loan_balance= "";				//대출잔액
	protected String loan_ceiling= "";				//대출한도액
	protected String new_date= "";				//신규일자
	protected String last_date= "";				//만기일자
	protected String lending_rate= "";				//대출금리
	protected String few_days= "";				//최종이수일자
	protected String interest_date= "";				//이자납입일
	protected String id_frt= "";				//admin
	protected String dt_frt= "";				//admin
	protected String id_lst= "";				//admin
	protected String dt_lst= "";				//admin
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLoan_balance() {
		return loan_balance;
	}
	public void setLoan_balance(String loan_balance) {
		this.loan_balance = loan_balance;
	}
	public String getLoan_ceiling() {
		return loan_ceiling;
	}
	public void setLoan_ceiling(String loan_ceiling) {
		this.loan_ceiling = loan_ceiling;
	}
	public String getNew_date() {
		return new_date;
	}
	public void setNew_date(String new_date) {
		this.new_date = new_date;
	}
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}
	public String getLending_rate() {
		return lending_rate;
	}
	public void setLending_rate(String lending_rate) {
		this.lending_rate = lending_rate;
	}
	public String getFew_days() {
		return few_days;
	}
	public void setFew_days(String few_days) {
		this.few_days = few_days;
	}
	public String getInterest_date() {
		return interest_date;
	}
	public void setInterest_date(String interest_date) {
		this.interest_date = interest_date;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
}
