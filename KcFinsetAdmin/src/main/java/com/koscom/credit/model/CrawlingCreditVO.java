package com.koscom.credit.model;


import java.util.HashMap;
import java.util.List;

import com.koscom.domain.CreditInfo;

public class CrawlingCreditVO {

	private static final long serialVersionUID = 6192197545025546983L;

	private String req_yyyymm;      //기준년월 (이용년월)
	private String cd_score;		//스코어구분코드
	private String grade_credit;	//신용등급
	private String rating_credit;	//신용평점
	private String percentage;		//백분율

	private String cnt_change_credit;	//신용변동내역(최근1년)
	private String cnt_ref_credit;		//신용정보조회내역(최근1년)

	private String cnt_card;		//카드현황
	private String cnt_loan;		//대출현황
	private String cnt_overdue;		//연체현황
	private String cnt_sldt_gart;	//연대보증현황
	protected String id_frt= "";	//최초입력아이디

	private List<CrawlingCreditDtlVO> crawlingCreditList;	//신용정보 변동 내역


	public String getReq_yyyymm() {
		return req_yyyymm;
	}

	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
	}

	public String getCd_score() {
		return cd_score;
	}

	public void setCd_score(String cd_score) {
		this.cd_score = cd_score;
	}

	public String getGrade_credit() {
		return grade_credit;
	}

	public void setGrade_credit(String grade_credit) {
		this.grade_credit = grade_credit;
	}

	public String getRating_credit() {
		return rating_credit;
	}

	public void setRating_credit(String rating_credit) {
		this.rating_credit = rating_credit;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getCnt_change_credit() {
		return cnt_change_credit;
	}

	public void setCnt_change_credit(String cnt_change_credit) {
		this.cnt_change_credit = cnt_change_credit;
	}

	public String getCnt_ref_credit() {
		return cnt_ref_credit;
	}

	public void setCnt_ref_credit(String cnt_ref_credit) {
		this.cnt_ref_credit = cnt_ref_credit;
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

	public String getCnt_sldt_gart() {
		return cnt_sldt_gart;
	}

	public void setCnt_sldt_gart(String cnt_sldt_gart) {
		this.cnt_sldt_gart = cnt_sldt_gart;
	}

	public List<CrawlingCreditDtlVO> getCrawlingCreditList() {
		return crawlingCreditList;
	}

	public void setCrawlingCreditList(List<CrawlingCreditDtlVO> crawlingCreditList) {
		this.crawlingCreditList = crawlingCreditList;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}


}