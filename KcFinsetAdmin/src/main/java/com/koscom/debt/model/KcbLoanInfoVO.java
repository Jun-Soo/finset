package com.koscom.debt.model;

public class KcbLoanInfoVO {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;			//회원관리번호
	private String req_yyyymm;			//기준년월
	private String amt_loan;			//대출금액
	private String amt_month;			//연체금액
	private String cnt_delay;			//연체건수
	private String cnt_loan;			//대출건수
	private String day_longest_overdue;	//최장연체일수

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
	public String getAmt_loan() {
		return amt_loan;
	}
	public void setAmt_loan(String amt_loan) {
		this.amt_loan = amt_loan;
	}
	public String getAmt_month() {
		return amt_month;
	}
	public void setAmt_month(String amt_month) {
		this.amt_month = amt_month;
	}
	public String getCnt_delay() {
		return cnt_delay;
	}
	public void setCnt_delay(String cnt_delay) {
		this.cnt_delay = cnt_delay;
	}
	public String getCnt_loan() {
		return cnt_loan;
	}
	public void setCnt_loan(String cnt_loan) {
		this.cnt_loan = cnt_loan;
	}
	public String getDay_longest_overdue() {
		return day_longest_overdue;
	}
	public void setDay_longest_overdue(String day_longest_overdue) {
		this.day_longest_overdue = day_longest_overdue;
	}


}