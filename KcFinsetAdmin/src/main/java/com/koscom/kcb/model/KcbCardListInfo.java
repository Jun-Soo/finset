package com.koscom.kcb.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.credit.model.CreditVO;
import com.koscom.debt.model.CrawlingLoanVO;

public class KcbCardListInfo implements Serializable {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;			//회원관리번호
	private String req_yyyymm;			//기준년월

	private String cnt_card;				//신용카드수
	private String amt_limit_sum;			//총한도합계금액
	private String amt_ca_sum;				//단기카드대출(ca)한도합계금액
	private String cnt_use_card;			//이용카드수
	private String amt_all_sum;				//총이용금액
	private String cnt_ca_use_card;			//단기카드대출(ca)이용카드수
	private String amt_ca_use_card;			//단기카드대출(ca)이용금액
	private String cnt_install_use_card;	//할부이용카드수
	private String amt_install_use;			//할부이용금액
	private String cnt_overdue;				//연체건수
	private String amt_overdue;				//연체금액
	private String days_due;				//최장연체일수
	private String id_frt;					//최초입력아이디

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
	public String getCnt_card() {
		return cnt_card;
	}
	public void setCnt_card(String cnt_card) {
		this.cnt_card = cnt_card;
	}
	public String getAmt_limit_sum() {
		return amt_limit_sum;
	}
	public void setAmt_limit_sum(String amt_limit_sum) {
		this.amt_limit_sum = amt_limit_sum;
	}
	public String getAmt_ca_sum() {
		return amt_ca_sum;
	}
	public void setAmt_ca_sum(String amt_ca_sum) {
		this.amt_ca_sum = amt_ca_sum;
	}
	public String getCnt_use_card() {
		return cnt_use_card;
	}
	public void setCnt_use_card(String cnt_use_card) {
		this.cnt_use_card = cnt_use_card;
	}
	public String getAmt_all_sum() {
		return amt_all_sum;
	}
	public void setAmt_all_sum(String amt_all_sum) {
		this.amt_all_sum = amt_all_sum;
	}
	public String getCnt_ca_use_card() {
		return cnt_ca_use_card;
	}
	public void setCnt_ca_use_card(String cnt_ca_use_card) {
		this.cnt_ca_use_card = cnt_ca_use_card;
	}
	public String getAmt_ca_use_card() {
		return amt_ca_use_card;
	}
	public void setAmt_ca_use_card(String amt_ca_use_card) {
		this.amt_ca_use_card = amt_ca_use_card;
	}
	public String getCnt_install_use_card() {
		return cnt_install_use_card;
	}
	public void setCnt_install_use_card(String cnt_install_use_card) {
		this.cnt_install_use_card = cnt_install_use_card;
	}
	public String getAmt_install_use() {
		return amt_install_use;
	}
	public void setAmt_install_use(String amt_install_use) {
		this.amt_install_use = amt_install_use;
	}
	public String getCnt_overdue() {
		return cnt_overdue;
	}
	public void setCnt_overdue(String cnt_overdue) {
		this.cnt_overdue = cnt_overdue;
	}
	public String getAmt_overdue() {
		return amt_overdue;
	}
	public void setAmt_overdue(String amt_overdue) {
		this.amt_overdue = amt_overdue;
	}
	public String getDays_due() {
		return days_due;
	}
	public void setDays_due(String days_due) {
		this.days_due = days_due;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
}