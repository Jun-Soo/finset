package com.koscom.debt.model;


import java.util.HashMap;

import com.koscom.domain.CreditInfo;

public class CrawlingLoanDtlVO {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;       	// 회원관리번호
	private String no_manage_info;  	// 정보관리번호
	private String req_yyyymm;        	// 기준년월 (이용년월)
	private String amt_repay;        	// 상환금액
	private String amt_remain;        	// 대출잔액
	private String amt_due;       		// 연체금액
	private String cd_account;        	// 계좌상태코드
	private String nm_account;        	// 계좌상태명


	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_manage_info() {
		return no_manage_info;
	}
	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
	}
	public String getReq_yyyymm() {
		return req_yyyymm;
	}
	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
	}
	public String getAmt_repay() {
		return amt_repay;
	}
	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getAmt_due() {
		return amt_due;
	}
	public void setAmt_due(String amt_due) {
		this.amt_due = amt_due;
	}
	public String getCd_account() {
		return cd_account;
	}
	public void setCd_account(String cd_account) {
		this.cd_account = cd_account;
	}
	public String getNm_account() {
		return nm_account;
	}
	public void setNm_account(String nm_account) {
		this.nm_account = nm_account;
	}
}