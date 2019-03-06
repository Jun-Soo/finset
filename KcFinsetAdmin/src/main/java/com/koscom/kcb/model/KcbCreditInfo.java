package com.koscom.kcb.model;


import java.io.Serializable;
import java.util.HashMap;

import com.koscom.domain.CreditInfo;

public class KcbCreditInfo implements Serializable {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;			//회원관리번호
	private String cnt_credit_change;	//신용정보변동건수
	private String cnt_normal_info;		//일반정보변동건수
	private String cnt_overdue_info;	//연체정보변동건수
	private String cnt_credit_ref_1y;	//신용정보조회건수_1년
	private String cnt_credit_ref_3y;	//신용정보조회건수_3년
	private String cnt_card;			//카드현황
	private String cnt_loan;			//대출현황
	private String cnt_overdue;			//연체현황
	private String cnt_guarantee;		//연대보증현황
	private String bal_overdue;			//연체잔액
	private String bal_pay;				//대지급잔액
	private String cnt_default;			//채무불이행건수
	private String cnt_public;			//공공정보건수
	private String cnt_fin_disorder;	//금융질서문란건수
	private String amt_guarantee;		//연대보증금액
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCnt_credit_change() {
		return cnt_credit_change;
	}
	public void setCnt_credit_change(String cnt_credit_change) {
		this.cnt_credit_change = cnt_credit_change;
	}
	public String getCnt_normal_info() {
		return cnt_normal_info;
	}
	public void setCnt_normal_info(String cnt_normal_info) {
		this.cnt_normal_info = cnt_normal_info;
	}
	public String getCnt_overdue_info() {
		return cnt_overdue_info;
	}
	public void setCnt_overdue_info(String cnt_overdue_info) {
		this.cnt_overdue_info = cnt_overdue_info;
	}
	public String getCnt_credit_ref_1y() {
		return cnt_credit_ref_1y;
	}
	public void setCnt_credit_ref_1y(String cnt_credit_ref_1y) {
		this.cnt_credit_ref_1y = cnt_credit_ref_1y;
	}
	public String getCnt_credit_ref_3y() {
		return cnt_credit_ref_3y;
	}
	public void setCnt_credit_ref_3y(String cnt_credit_ref_3y) {
		this.cnt_credit_ref_3y = cnt_credit_ref_3y;
	}
	public String getCnt_card() {
		return cnt_card;
	}
	public void setCnt_card(String cnt_card) {
		this.cnt_card = cnt_card;
	}
	public String getCnt_loan() {
		return cnt_loan;
	}
	public void setCnt_loan(String cnt_loan) {
		this.cnt_loan = cnt_loan;
	}
	public String getCnt_overdue() {
		return cnt_overdue;
	}
	public void setCnt_overdue(String cnt_overdue) {
		this.cnt_overdue = cnt_overdue;
	}
	public String getCnt_guarantee() {
		return cnt_guarantee;
	}
	public void setCnt_guarantee(String cnt_guarantee) {
		this.cnt_guarantee = cnt_guarantee;
	}
	public String getBal_overdue() {
		return bal_overdue;
	}
	public void setBal_overdue(String bal_overdue) {
		this.bal_overdue = bal_overdue;
	}
	public String getBal_pay() {
		return bal_pay;
	}
	public void setBal_pay(String bal_pay) {
		this.bal_pay = bal_pay;
	}
	public String getCnt_default() {
		return cnt_default;
	}
	public void setCnt_default(String cnt_default) {
		this.cnt_default = cnt_default;
	}
	public String getCnt_public() {
		return cnt_public;
	}
	public void setCnt_public(String cnt_public) {
		this.cnt_public = cnt_public;
	}
	public String getCnt_fin_disorder() {
		return cnt_fin_disorder;
	}
	public void setCnt_fin_disorder(String cnt_fin_disorder) {
		this.cnt_fin_disorder = cnt_fin_disorder;
	}
	public String getAmt_guarantee() {
		return amt_guarantee;
	}
	public void setAmt_guarantee(String amt_guarantee) {
		this.amt_guarantee = amt_guarantee;
	}

}