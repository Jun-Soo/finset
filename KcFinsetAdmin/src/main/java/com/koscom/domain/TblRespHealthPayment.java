package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespHealthPayment implements Serializable{

	/**
	 * 납부확인서 
	 */
	private static final long serialVersionUID = 3939449621763181213L;
	
	protected String belong_branch 		; //소속지사
	protected String bgn 				; //생년월일
	protected String comp_nm 			; //회사명
	protected String error_cd 			; //에러코드
	protected String error_msg 			; //에러메세지
	protected String gain_ymd 			; //취득일자
	protected String inquiry_result 	; //조회결과
	protected String loss_dd 			; //상실일
	protected String member_division 	; //가입자구분
	protected String member_name 		; //가입자성명
	protected int    order_pay_confirm 	; //순서_납부확인
	protected String payer_no 			; //납부자번호
	protected String pbls_no 			; //발급번호
	protected String register_dt 		; //등록일시
	protected int    seq_health_insu 	; //일련번호_건강보험
		
	public String getBelong_branch() {
		return belong_branch;
	}



	public void setBelong_branch(String belong_branch) {
		this.belong_branch = belong_branch;
	}



	public String getBgn() {
		return bgn;
	}



	public void setBgn(String bgn) {
		this.bgn = bgn;
	}



	public String getComp_nm() {
		return comp_nm;
	}



	public void setComp_nm(String comp_nm) {
		this.comp_nm = comp_nm;
	}



	public String getError_cd() {
		return error_cd;
	}



	public void setError_cd(String error_cd) {
		this.error_cd = error_cd;
	}



	public String getError_msg() {
		return error_msg;
	}



	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}



	public String getGain_ymd() {
		return gain_ymd;
	}



	public void setGain_ymd(String gain_ymd) {
		this.gain_ymd = gain_ymd;
	}



	public String getInquiry_result() {
		return inquiry_result;
	}



	public void setInquiry_result(String inquiry_result) {
		this.inquiry_result = inquiry_result;
	}



	public String getLoss_dd() {
		return loss_dd;
	}



	public void setLoss_dd(String loss_dd) {
		this.loss_dd = loss_dd;
	}



	public String getMember_division() {
		return member_division;
	}



	public void setMember_division(String member_division) {
		this.member_division = member_division;
	}



	public String getMember_name() {
		return member_name;
	}



	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}



	public int getOrder_pay_confirm() {
		return order_pay_confirm;
	}



	public void setOrder_pay_confirm(int order_pay_confirm) {
		this.order_pay_confirm = order_pay_confirm;
	}



	public String getPayer_no() {
		return payer_no;
	}



	public void setPayer_no(String payer_no) {
		this.payer_no = payer_no;
	}



	public String getPbls_no() {
		return pbls_no;
	}



	public void setPbls_no(String pbls_no) {
		this.pbls_no = pbls_no;
	}



	public String getRegister_dt() {
		return register_dt;
	}



	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}



	public int getSeq_health_insu() {
		return seq_health_insu;
	}



	public void setSeq_health_insu(int seq_health_insu) {
		this.seq_health_insu = seq_health_insu;
	}



	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
