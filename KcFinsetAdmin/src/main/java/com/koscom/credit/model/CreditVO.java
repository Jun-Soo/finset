package com.koscom.credit.model;


import java.util.HashMap;
import java.util.List;

import com.koscom.domain.CreditInfo;

public class CreditVO {

	private static final long serialVersionUID = 6192197545025546983L;

	private String req_yyyymm;      //기준년월 (이용년월)
	private String cd_score;		//스코어구분코드
	private String grade_credit;	//신용등급
	private String rating_credit;	//신용평점
	private String percentage;		//백분율

	private String cnt_credit_change;	//신용정보변동건수
	private String cnt_normal_info;		//일반정보변동건수
	private String cnt_overdue_info;	//연체정보변동건수
	private String cnt_credit_ref_1y;	//신용정보조회건수_1년
	private String cnt_credit_ref_3y;	//신용정보조회건수_3년
	private String cnt_card;			//카드현황
	private String cnt_loan;			//대출현황
	private String cnt_overdue;			//연체현황
	private String cnt_guarantee;		//연대보증현황
	private String cnt_card_open;		//카드개설수
	private String cnt_card_use;		//이용카드수
	private String amt_card_total;		//총이용금액
	private String list_card_open;		//카드개설내역
	private String list_card_use;		//카드이용내역
	private String cnt_debt_open;		//개설대출수
	private String amt_debt;			//대출금액
	private String list_debt_open;		//대출개설내역
	private String list_debt_use;		//대출이용내역
	private String bal_overdue;			//연체잔액
	private String list_overdue_info;	//연체정보내역
	private String bal_pay;				//대지급잔액
	private String cnt_default;			//채무불이행건수
	private String cnt_public;			//공공정보건수
	private String cnt_fin_disorder;	//금융질서문란건수
	private String list_overdue_etc;	//기타연체내역
	private String cnt_dt_guarantee;	//연대보증수
	private String amt_guarantee;		//연대보증금액
	private String list_guarantee;		//보증상세내역

	protected String id_frt= "";	//최초입력아이디

	private List<CreditDtlVO> crawlingCreditList;	//신용정보 변동 내역

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

	public String getCnt_card_open() {
		return cnt_card_open;
	}

	public void setCnt_card_open(String cnt_card_open) {
		this.cnt_card_open = cnt_card_open;
	}

	public String getCnt_card_use() {
		return cnt_card_use;
	}

	public void setCnt_card_use(String cnt_card_use) {
		this.cnt_card_use = cnt_card_use;
	}

	public String getAmt_card_total() {
		return amt_card_total;
	}

	public void setAmt_card_total(String amt_card_total) {
		this.amt_card_total = amt_card_total;
	}

	public String getList_card_open() {
		return list_card_open;
	}

	public void setList_card_open(String list_card_open) {
		this.list_card_open = list_card_open;
	}

	public String getList_card_use() {
		return list_card_use;
	}

	public void setList_card_use(String list_card_use) {
		this.list_card_use = list_card_use;
	}

	public String getCnt_debt_open() {
		return cnt_debt_open;
	}

	public void setCnt_debt_open(String cnt_debt_open) {
		this.cnt_debt_open = cnt_debt_open;
	}

	public String getAmt_debt() {
		return amt_debt;
	}

	public void setAmt_debt(String amt_debt) {
		this.amt_debt = amt_debt;
	}

	public String getList_debt_open() {
		return list_debt_open;
	}

	public void setList_debt_open(String list_debt_open) {
		this.list_debt_open = list_debt_open;
	}

	public String getList_debt_use() {
		return list_debt_use;
	}

	public void setList_debt_use(String list_debt_use) {
		this.list_debt_use = list_debt_use;
	}

	public String getBal_overdue() {
		return bal_overdue;
	}

	public void setBal_overdue(String bal_overdue) {
		this.bal_overdue = bal_overdue;
	}

	public String getList_overdue_info() {
		return list_overdue_info;
	}

	public void setList_overdue_info(String list_overdue_info) {
		this.list_overdue_info = list_overdue_info;
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

	public String getList_overdue_etc() {
		return list_overdue_etc;
	}

	public void setList_overdue_etc(String list_overdue_etc) {
		this.list_overdue_etc = list_overdue_etc;
	}

	public String getCnt_dt_guarantee() {
		return cnt_dt_guarantee;
	}

	public void setCnt_dt_guarantee(String cnt_dt_guarantee) {
		this.cnt_dt_guarantee = cnt_dt_guarantee;
	}

	public String getAmt_guarantee() {
		return amt_guarantee;
	}

	public void setAmt_guarantee(String amt_guarantee) {
		this.amt_guarantee = amt_guarantee;
	}

	public String getList_guarantee() {
		return list_guarantee;
	}

	public void setList_guarantee(String list_guarantee) {
		this.list_guarantee = list_guarantee;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public List<CreditDtlVO> getCrawlingCreditList() {
		return crawlingCreditList;
	}

	public void setCrawlingCreditList(List<CreditDtlVO> crawlingCreditList) {
		this.crawlingCreditList = crawlingCreditList;
	}



}