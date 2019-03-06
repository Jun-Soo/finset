package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceInfo implements Serializable {
	private static final long serialVersionUID = 8194287705022571841L;
	protected String no_person        ;
	protected int score_cb         ;		//CB등급         
	protected int avg_credit       ;   //신용평점       
	protected int score_subprime   ;   //서브프라임등급  	SP
	protected int avg_subprime     ;   //서브평점       
	protected int score_bankrupt   ;   //채무건전성등급   PI
	protected int avg_bankrupt     ;   //채무건전성평점       
	protected int avg_12month      ;   //12개월 평균평점       
	protected int grade_12month    ;   //12개월 평균등급       
	protected String default_bank     ;   //채무불이행(은행연합회)
	protected String info_public      ;   //공공정보              
	protected String disorder_fin     ;   //금융질서문란          
	protected String default_credit   ;   //채무불이행(신용정보사)
	
	protected int grade_health      ;  //채무건전성등급	DEP
	protected int avg_health        ;  //채무건전성평점
	protected int cnt_all_loan      ;  //총대출건수     
	protected String amt_loan          ;  //대출금액       
	protected int cnt_cash_service  ;  //현금서비스건수 
	protected String amt_cash_service  ;  //현금서비스 금액
	protected int cnt_guarantee     ;  //보증건수       
	protected String amt_guarantee     ;  //보증금액       
	protected int cnt_creditcard    ;  //신용카드 수    
	protected int cnt_delay         ;  //연체건수       
	
	protected int cnt_lend         ;  //대부연체건수       
	protected String amt_lend  ;  //대부서비스 금액

	public String getAmt_lend() {
		return amt_lend;
	}
	public void setAmt_lend(String amt_lend) {
		this.amt_lend = amt_lend;
	}
	public int getCnt_lend() {
		return cnt_lend;
	}
	public void setCnt_lend(int cnt_lend) {
		this.cnt_lend = cnt_lend;
	}
	protected String id_frt           ;
	protected String dt_frt           ;
	protected String id_lst           ;
	protected String dt_lst           ; 
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public int getScore_cb() {
		return score_cb;
	}
	public void setScore_cb(int score_cb) {
		this.score_cb = score_cb;
	}
	public int getAvg_credit() {
		return avg_credit;
	}
	public void setAvg_credit(int avg_credit) {
		this.avg_credit = avg_credit;
	}
	public int getScore_subprime() {
		return score_subprime;
	}
	public void setScore_subprime(int score_subprime) {
		this.score_subprime = score_subprime;
	}
	public int getAvg_subprime() {
		return avg_subprime;
	}
	public void setAvg_subprime(int avg_subprime) {
		this.avg_subprime = avg_subprime;
	}
	public int getScore_bankrupt() {
		return score_bankrupt;
	}
	public void setScore_bankrupt(int score_bankrupt) {
		this.score_bankrupt = score_bankrupt;
	}
	public int getAvg_bankrupt() {
		return avg_bankrupt;
	}
	public void setAvg_bankrupt(int avg_bankrupt) {
		this.avg_bankrupt = avg_bankrupt;
	}
	public int getAvg_12month() {
		return avg_12month;
	}
	public void setAvg_12month(int avg_12month) {
		this.avg_12month = avg_12month;
	}
	public int getGrade_12month() {
		return grade_12month;
	}
	public void setGrade_12month(int grade_12month) {
		this.grade_12month = grade_12month;
	}
	public String getDefault_bank() {
		return default_bank;
	}
	public void setDefault_bank(String default_bank) {
		this.default_bank = default_bank;
	}
	public String getInfo_public() {
		return info_public;
	}
	public void setInfo_public(String info_public) {
		this.info_public = info_public;
	}
	public String getDisorder_fin() {
		return disorder_fin;
	}
	public void setDisorder_fin(String disorder_fin) {
		this.disorder_fin = disorder_fin;
	}
	public String getDefault_credit() {
		return default_credit;
	}
	public void setDefault_credit(String default_credit) {
		this.default_credit = default_credit;
	}
	public int getGrade_health() {
		return grade_health;
	}
	public void setGrade_health(int grade_health) {
		this.grade_health = grade_health;
	}
	public int getAvg_health() {
		return avg_health;
	}
	public void setAvg_health(int avg_health) {
		this.avg_health = avg_health;
	}
	public int getCnt_all_loan() {
		return cnt_all_loan;
	}
	public void setCnt_all_loan(int cnt_all_loan) {
		this.cnt_all_loan = cnt_all_loan;
	}
	public String getAmt_loan() {
		return amt_loan;
	}
	public void setAmt_loan(String amt_loan) {
		this.amt_loan = amt_loan;
	}
	public int getCnt_cash_service() {
		return cnt_cash_service;
	}
	public void setCnt_cash_service(int cnt_cash_service) {
		this.cnt_cash_service = cnt_cash_service;
	}
	public String getAmt_cash_service() {
		return amt_cash_service;
	}
	public void setAmt_cash_service(String amt_cash_service) {
		this.amt_cash_service = amt_cash_service;
	}
	public int getCnt_guarantee() {
		return cnt_guarantee;
	}
	public void setCnt_guarantee(int cnt_guarantee) {
		this.cnt_guarantee = cnt_guarantee;
	}
	public String getAmt_guarantee() {
		return amt_guarantee;
	}
	public void setAmt_guarantee(String amt_guarantee) {
		this.amt_guarantee = amt_guarantee;
	}
	public int getCnt_creditcard() {
		return cnt_creditcard;
	}
	public void setCnt_creditcard(int cnt_creditcard) {
		this.cnt_creditcard = cnt_creditcard;
	}
	public int getCnt_delay() {
		return cnt_delay;
	}
	public void setCnt_delay(int cnt_delay) {
		this.cnt_delay = cnt_delay;
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
