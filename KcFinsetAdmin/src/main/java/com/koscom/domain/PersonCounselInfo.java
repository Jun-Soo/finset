package com.koscom.domain;

import java.io.Serializable;

public class PersonCounselInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6538066223542972245L;

	private String nm_person; //성명
	private String age;  //나이
	private String sex;  //성별
	private String grade_credit;  //신용등급
	private String rating_credit;  //신용점수
	private String bal_overdue;  //연체금액
	private String amt_contract;  //대출원금
	private String amt_remain;  //대출잔액
	private String cur_mm_amt_repay;  //월상환액
	private String cnt_card_use;  //이용카드수
	private String amt_card_total; //월이용액

	private String cd_counsel_status; //상담상태코드
	private String dt_apply; //상담신청일시
	private String dt_pre_counsel; //상담준비일시
	private String dt_counsel; //상담완료일시
	private String yn_wedding; //결혼여부(N:미혼,Y:기혼)
	private String cd_family_cnt; //부양가족수코드(0:없음,1:1명,2:2명,3:3명이상)
	private String cd_job; //직업코드(10:일반기업,20:전문직,30:공공기관,40:교육기관,50:의료기관,60:자영업)
	private String cd_living; //주거형태코드(10:자가,20:임차,30:기숙사,40:무상주거,90:기타)
	private int amt_mm_income; //월소득금액
	private int amt_mm_expense; //월지출금액
	private String list_card_use; //카드이용내역
	private String list_overdue_info; //연체정보내역
	private String list_overdue_etc; //기타연체내역
	private String counsel_contents; //상담내용
	private String etc_contents; //기타내용
	private String id_emp_counsel; //상담직원아이디

	private String id_frt; //최초입력아이디
	private String dt_frt; //최초입력시간
	private String id_lst; //최종수정아이디
	private String dt_lst; //최종수정시간

	public String getNm_person() {
		return nm_person;
	}
	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getBal_overdue() {
		return bal_overdue;
	}
	public void setBal_overdue(String bal_overdue) {
		this.bal_overdue = bal_overdue;
	}
	public String getAmt_contract() {
		return amt_contract;
	}
	public void setAmt_contract(String amt_contract) {
		this.amt_contract = amt_contract;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getCur_mm_amt_repay() {
		return cur_mm_amt_repay;
	}
	public void setCur_mm_amt_repay(String cur_mm_amt_repay) {
		this.cur_mm_amt_repay = cur_mm_amt_repay;
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
	public String getCd_counsel_status() {
		return cd_counsel_status;
	}
	public void setCd_counsel_status(String cd_counsel_status) {
		this.cd_counsel_status = cd_counsel_status;
	}
	public String getDt_apply() {
		return dt_apply;
	}
	public void setDt_apply(String dt_apply) {
		this.dt_apply = dt_apply;
	}
	public String getDt_pre_counsel() {
		return dt_pre_counsel;
	}
	public void setDt_pre_counsel(String dt_pre_counsel) {
		this.dt_pre_counsel = dt_pre_counsel;
	}
	public String getDt_counsel() {
		return dt_counsel;
	}
	public void setDt_counsel(String dt_counsel) {
		this.dt_counsel = dt_counsel;
	}
	public String getYn_wedding() {
		return yn_wedding;
	}
	public void setYn_wedding(String yn_wedding) {
		this.yn_wedding = yn_wedding;
	}
	public String getCd_family_cnt() {
		return cd_family_cnt;
	}
	public void setCd_family_cnt(String cd_family_cnt) {
		this.cd_family_cnt = cd_family_cnt;
	}
	public String getCd_job() {
		return cd_job;
	}
	public void setCd_job(String cd_job) {
		this.cd_job = cd_job;
	}
	public String getCd_living() {
		return cd_living;
	}
	public void setCd_living(String cd_living) {
		this.cd_living = cd_living;
	}
	public int getAmt_mm_income() {
		return amt_mm_income;
	}
	public void setAmt_mm_income(int amt_mm_income) {
		this.amt_mm_income = amt_mm_income;
	}
	public int getAmt_mm_expense() {
		return amt_mm_expense;
	}
	public void setAmt_mm_expense(int amt_mm_expense) {
		this.amt_mm_expense = amt_mm_expense;
	}
	public String getList_card_use() {
		return list_card_use;
	}
	public void setList_card_use(String list_card_use) {
		this.list_card_use = list_card_use;
	}
	public String getList_overdue_info() {
		return list_overdue_info;
	}
	public void setList_overdue_info(String list_overdue_info) {
		this.list_overdue_info = list_overdue_info;
	}
	public String getList_overdue_etc() {
		return list_overdue_etc;
	}
	public void setList_overdue_etc(String list_overdue_etc) {
		this.list_overdue_etc = list_overdue_etc;
	}
	public String getCounsel_contents() {
		return counsel_contents;
	}
	public void setCounsel_contents(String counsel_contents) {
		this.counsel_contents = counsel_contents;
	}
	public String getEtc_contents() {
		return etc_contents;
	}
	public void setEtc_contents(String etc_contents) {
		this.etc_contents = etc_contents;
	}
	public String getId_emp_counsel() {
		return id_emp_counsel;
	}
	public void setId_emp_counsel(String id_emp_counsel) {
		this.id_emp_counsel = id_emp_counsel;
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
