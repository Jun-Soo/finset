package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CarInfo implements Serializable{

	private static final long serialVersionUID = 2330481524611598992L;
	
	protected String no_prepare;
	protected String no_reg_car; //차량등록번호
	protected String no_id_car; //차대번호
	protected String nm_kind_car; //차종
	protected String nm_car; //차명
	protected String nm_used_car; //용도
	protected String year_car; //연식
	protected String nm_comp_car; //제조사명
	protected String nm_model_car; //차량모델
	protected String cd_fuel; //연료종류
	protected String cc_car; //배기량
	protected String mile_car; //주행거리
	protected String cd_gear; //변속기
	protected String nm_color_car; // 차량색상
	protected String amt_new_car;	 // 신차가격
	protected String amt_now_car;  //중고시세
	protected String yn_accident;	 //사고유무
	protected String memo_car; //사고내용
	protected String nm_insu_car; //보험회사명
	
	protected String nm_sort_insu_car; //보험종류
	protected String cnt_term_insu_car; //보험기간
	protected String cnt_rcpt_insu_car; //보험납부회차
	protected String cd_bank_insu_car; //할부기관
	protected String amt_loan_car; //총할부금액
	protected String cnt_total_loan_car; //총할부기간
	protected String cnt_rcpt_loan_car; //납부회차
	protected String amt_bal_loan_car; //현재잔액
	protected String amt_delay_loan_car; //현재연체액

	protected String id_frt; //
	protected String dt_frt; //
	protected String id_lst; //
	protected String dt_lst; //
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getNo_reg_car() {
		return no_reg_car;
	}

	public void setNo_reg_car(String no_reg_car) {
		this.no_reg_car = no_reg_car;
	}

	public String getNo_id_car() {
		return no_id_car;
	}

	public void setNo_id_car(String no_id_car) {
		this.no_id_car = no_id_car;
	}

	public String getNm_kind_car() {
		return nm_kind_car;
	}

	public void setNm_kind_car(String nm_kind_car) {
		this.nm_kind_car = nm_kind_car;
	}

	public String getNm_car() {
		return nm_car;
	}

	public void setNm_car(String nm_car) {
		this.nm_car = nm_car;
	}

	public String getNm_used_car() {
		return nm_used_car;
	}

	public void setNm_used_car(String nm_used_car) {
		this.nm_used_car = nm_used_car;
	}

	public String getYear_car() {
		return year_car;
	}

	public void setYear_car(String year_car) {
		this.year_car = year_car;
	}

	public String getNm_comp_car() {
		return nm_comp_car;
	}

	public void setNm_comp_car(String nm_comp_car) {
		this.nm_comp_car = nm_comp_car;
	}

	public String getNm_model_car() {
		return nm_model_car;
	}

	public void setNm_model_car(String nm_model_car) {
		this.nm_model_car = nm_model_car;
	}

	public String getCd_fuel() {
		return cd_fuel;
	}

	public void setCd_fuel(String cd_fuel) {
		this.cd_fuel = cd_fuel;
	}

	public String getCc_car() {
		return cc_car;
	}

	public void setCc_car(String cc_car) {
		this.cc_car = cc_car;
	}

	public String getMile_car() {
		return mile_car;
	}

	public void setMile_car(String mile_car) {
		this.mile_car = mile_car;
	}

	public String getCd_gear() {
		return cd_gear;
	}

	public void setCd_gear(String cd_gear) {
		this.cd_gear = cd_gear;
	}

	public String getNm_color_car() {
		return nm_color_car;
	}

	public void setNm_color_car(String nm_color_car) {
		this.nm_color_car = nm_color_car;
	}

	public String getAmt_new_car() {
		return amt_new_car;
	}

	public void setAmt_new_car(String amt_new_car) {
		this.amt_new_car = amt_new_car;
	}

	public String getAmt_now_car() {
		return amt_now_car;
	}

	public void setAmt_now_car(String amt_now_car) {
		this.amt_now_car = amt_now_car;
	}

	public String getYn_accident() {
		return yn_accident;
	}

	public void setYn_accident(String yn_accident) {
		this.yn_accident = yn_accident;
	}

	public String getMemo_car() {
		return memo_car;
	}

	public void setMemo_car(String memo_car) {
		this.memo_car = memo_car;
	}

	public String getNm_insu_car() {
		return nm_insu_car;
	}

	public void setNm_insu_car(String nm_insu_car) {
		this.nm_insu_car = nm_insu_car;
	}

	public String getNm_sort_insu_car() {
		return nm_sort_insu_car;
	}

	public void setNm_sort_insu_car(String nm_sort_insu_car) {
		this.nm_sort_insu_car = nm_sort_insu_car;
	}

	public String getCnt_term_insu_car() {
		return cnt_term_insu_car;
	}

	public void setCnt_term_insu_car(String cnt_term_insu_car) {
		this.cnt_term_insu_car = cnt_term_insu_car;
	}

	public String getCnt_rcpt_insu_car() {
		return cnt_rcpt_insu_car;
	}

	public void setCnt_rcpt_insu_car(String cnt_rcpt_insu_car) {
		this.cnt_rcpt_insu_car = cnt_rcpt_insu_car;
	}

	public String getCd_bank_insu_car() {
		return cd_bank_insu_car;
	}

	public void setCd_bank_insu_car(String cd_bank_insu_car) {
		this.cd_bank_insu_car = cd_bank_insu_car;
	}

	public String getAmt_loan_car() {
		return amt_loan_car;
	}

	public void setAmt_loan_car(String amt_loan_car) {
		this.amt_loan_car = amt_loan_car;
	}

	public String getCnt_total_loan_car() {
		return cnt_total_loan_car;
	}

	public void setCnt_total_loan_car(String cnt_total_loan_car) {
		this.cnt_total_loan_car = cnt_total_loan_car;
	}

	public String getCnt_rcpt_loan_car() {
		return cnt_rcpt_loan_car;
	}

	public void setCnt_rcpt_loan_car(String cnt_rcpt_loan_car) {
		this.cnt_rcpt_loan_car = cnt_rcpt_loan_car;
	}

	public String getAmt_bal_loan_car() {
		return amt_bal_loan_car;
	}

	public void setAmt_bal_loan_car(String amt_bal_loan_car) {
		this.amt_bal_loan_car = amt_bal_loan_car;
	}

	public String getAmt_delay_loan_car() {
		return amt_delay_loan_car;
	}

	public void setAmt_delay_loan_car(String amt_delay_loan_car) {
		this.amt_delay_loan_car = amt_delay_loan_car;
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
