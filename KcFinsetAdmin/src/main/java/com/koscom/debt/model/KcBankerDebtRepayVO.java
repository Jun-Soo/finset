package com.koscom.debt.model;

import java.io.Serializable;

public class KcBankerDebtRepayVO implements Serializable{

	private static final long serialVersionUID = -8763892641885448050L;

	private String no_person;		    //회원관리번호         
	private String no_manage_info;      //정보관리번호         
	private String req_yyyymm;          //기준년월             
	private String yn_kcb;              //KCB여부              
	private String cd_account;          //계좌상태코드         
	private String nm_account;          //계좌상태명           
	private String amt_repay;           //상환금액             
	private String amt_repay_p;         //상환원금             
	private String amt_repay_i;         //상환이자             
	private String amt_mid_repay;       //중도상환금           
	private String fees_mid_repay;      //중도상환수수료       
	private String amt_remain;          //대출잔액             
	private String amt_delay;           //1개월전연체금액      
	private String etm_interest;        //추정금리             
	private String bank_interest;       //은행금리             
	private String interest_day;        //이자계산일(DD)
	private String payment_ymd;         //결제예정일           
	private String interest_ymd;        //이자납입일           
	private String interest_cal_dcnt;   //이자계산일수         
	private String cd_etm_basic;        //금리산정기준코드(KA:KCB추정/KF:KCB소급/AF:일괄소급/BI:은행정보/MB:문자정보)
	private String id_frt;              //최초입력아이디       
	private String dt_frt;              //최초입력시간         
	private String id_lst;              //최종수정아이디       
	private String dt_lst;              //최종수정시간         
	private String interest_strt_ymd;   //이자계산시작일       
	private String interest_end_ymd;    //이자계산종료일 
	private String etm_basic_nm;		//금리산정기준코드명
	
	
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
	public String getYn_kcb() {
		return yn_kcb;
	}
	public void setYn_kcb(String yn_kcb) {
		this.yn_kcb = yn_kcb;
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
	public String getAmt_repay() {
		return amt_repay;
	}
	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}
	public String getAmt_repay_p() {
		return amt_repay_p;
	}
	public void setAmt_repay_p(String amt_repay_p) {
		this.amt_repay_p = amt_repay_p;
	}
	public String getAmt_repay_i() {
		return amt_repay_i;
	}
	public void setAmt_repay_i(String amt_repay_i) {
		this.amt_repay_i = amt_repay_i;
	}
	public String getAmt_mid_repay() {
		return amt_mid_repay;
	}
	public void setAmt_mid_repay(String amt_mid_repay) {
		this.amt_mid_repay = amt_mid_repay;
	}
	public String getFees_mid_repay() {
		return fees_mid_repay;
	}
	public void setFees_mid_repay(String fees_mid_repay) {
		this.fees_mid_repay = fees_mid_repay;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public String getEtm_interest() {
		return etm_interest;
	}
	public void setEtm_interest(String etm_interest) {
		this.etm_interest = etm_interest;
	}
	public String getBank_interest() {
		return bank_interest;
	}
	public void setBank_interest(String bank_interest) {
		this.bank_interest = bank_interest;
	}
	public String getInterest_day() {
		return interest_day;
	}
	public void setInterest_day(String interest_day) {
		this.interest_day = interest_day;
	}
	public String getPayment_ymd() {
		return payment_ymd;
	}
	public void setPayment_ymd(String payment_ymd) {
		this.payment_ymd = payment_ymd;
	}
	public String getInterest_ymd() {
		return interest_ymd;
	}
	public void setInterest_ymd(String interest_ymd) {
		this.interest_ymd = interest_ymd;
	}
	public String getInterest_cal_dcnt() {
		return interest_cal_dcnt;
	}
	public void setInterest_cal_dcnt(String interest_cal_dcnt) {
		this.interest_cal_dcnt = interest_cal_dcnt;
	}
	public String getCd_etm_basic() {
		return cd_etm_basic;
	}
	public void setCd_etm_basic(String cd_etm_basic) {
		this.cd_etm_basic = cd_etm_basic;
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
	public String getInterest_strt_ymd() {
		return interest_strt_ymd;
	}
	public void setInterest_strt_ymd(String interest_strt_ymd) {
		this.interest_strt_ymd = interest_strt_ymd;
	}
	public String getInterest_end_ymd() {
		return interest_end_ymd;
	}
	public void setInterest_end_ymd(String interest_end_ymd) {
		this.interest_end_ymd = interest_end_ymd;
	}
	public String getEtm_basic_nm() {
		return etm_basic_nm;
	}
	public void setEtm_basic_nm(String etm_basic_nm) {
		this.etm_basic_nm = etm_basic_nm;
	}
}
