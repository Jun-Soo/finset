package com.koscom.debt.model;

import java.io.Serializable;

/**
 * 개인별 개별 부채 정보
 * @author crizen
 */
public class DebtPersonalRepayListVO implements Serializable{


	private static final long serialVersionUID = -4113349353635587599L;
	protected String no_person= "";			//회원관리번호
	protected String no_manage_info= "";	//정보관리번호
	protected String req_yyyymm= "";		//기준년월
	protected String yn_kcb= "";			//KCB자료 여부
	protected String cd_account= "";		//계좌상태코드  01:정상, 02:해지, 30:1~30일 연체, 60:31~60일 연체, 90 : 61~90일 연체, 90+ : 91일~ 연체, - : 개설 전 또는 유효기간 경과';
	protected String nm_account= "";		//계좌상태
	protected String amt_repay= "";			//상환금액
	protected String amt_repay_p= "";		//상환원금
	protected String amt_repay_i= "";		//상환이자

	protected String amt_mid_repay= "";		//중도상환금
	protected String fees_mid_repay= "";	//중도상환수수료

	protected String amt_remain= "";		//대출잔액
	protected String amt_delay= "";			//연체금액
	protected String etm_interest= "";		//추정금리
	protected String bank_interest= "";		//은행금리
	protected String interest_day= "";		//이자계산일(DD)

	protected String payment_ymd= "";		//결제예정일
	protected String interest_ymd= "";		//이자납입일
	protected String interest_cal_dcnt= "";	//이자계산일수
	protected String cd_etm_basic = "";		//금리산정기준코드(KA:KCB추정 / KF:KCB소급 / AF:일괄소급 / BI:은행정보 / MB:문자정보)

	protected String interest_strt_ymd = "";//이자계산시작일
	protected String interest_end_ymd = "";	//이자계산종료일

	protected String id_frt= "";			//최초입력아이디
	protected String dt_frt= "";			//최초입력시간
	protected String id_lst= "";			//수정입력아이디
	protected String dt_lst= "";			//수정입력시간

	//계산을 위한 필드 (DB에는 없음)

	public DebtPersonalRepayListVO(){

	}

	public String getNo_person() {
		return no_person;
	}
	public String getNo_manage_info() {
		return no_manage_info;
	}
	public String getReq_yyyymm() {
		return req_yyyymm;
	}
	public String getCd_account() {
		return cd_account;
	}
	public String getNm_account() {
		return nm_account;
	}
	public String getAmt_repay() {
		return amt_repay;
	}
	public String getAmt_repay_p() {
		return amt_repay_p;
	}
	public String getAmt_repay_i() {
		return amt_repay_i;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public String getEtm_interest() {
		return etm_interest;
	}
	public String getBank_interest() {
		return bank_interest;
	}
	public String getPayment_ymd() {
		return payment_ymd;
	}
	public String getId_frt() {
		return id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
	}
	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
	}
	public void setCd_account(String cd_account) {
		this.cd_account = cd_account;
	}
	public void setNm_account(String nm_account) {
		this.nm_account = nm_account;
	}
	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}
	public void setAmt_repay_p(String amt_repay_p) {
		this.amt_repay_p = amt_repay_p;
	}
	public void setAmt_repay_i(String amt_repay_i) {
		this.amt_repay_i = amt_repay_i;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public void setEtm_interest(String etm_interest) {
		this.etm_interest = etm_interest;
	}
	public void setBank_interest(String bank_interest) {
		this.bank_interest = bank_interest;
	}
	public void setPayment_ymd(String payment_ymd) {
		this.payment_ymd = payment_ymd;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
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
	public String getYn_kcb() {
		return yn_kcb;
	}
	public String getAmt_mid_repay() {
		return amt_mid_repay;
	}
	public String getFees_mid_repay() {
		return fees_mid_repay;
	}
	public String getInterest_day() {
		return interest_day;
	}
	public String getInterest_ymd() {
		return interest_ymd;
	}
	public String getInterest_cal_dcnt() {
		return interest_cal_dcnt;
	}
	public String getCd_etm_basic() {
		return cd_etm_basic;
	}
	public void setYn_kcb(String yn_kcb) {
		this.yn_kcb = yn_kcb;
	}
	public void setAmt_mid_repay(String amt_mid_repay) {
		this.amt_mid_repay = amt_mid_repay;
	}
	public void setFees_mid_repay(String fees_mid_repay) {
		this.fees_mid_repay = fees_mid_repay;
	}
	public void setInterest_day(String interest_day) {
		this.interest_day = interest_day;
	}
	public void setInterest_ymd(String interest_ymd) {
		this.interest_ymd = interest_ymd;
	}
	public void setInterest_cal_dcnt(String interest_cal_dcnt) {
		this.interest_cal_dcnt = interest_cal_dcnt;
	}
	public void setCd_etm_basic(String cd_etm_basic) {
		this.cd_etm_basic = cd_etm_basic;
	}

	public String getInterest_strt_ymd() {
		return interest_strt_ymd;
	}

	public String getInterest_end_ymd() {
		return interest_end_ymd;
	}

	public void setInterest_strt_ymd(String interest_strt_ymd) {
		this.interest_strt_ymd = interest_strt_ymd;
	}

	public void setInterest_end_ymd(String interest_end_ymd) {
		this.interest_end_ymd = interest_end_ymd;
	}


}
