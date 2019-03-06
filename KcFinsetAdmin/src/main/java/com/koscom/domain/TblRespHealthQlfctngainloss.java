package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespHealthQlfctngainloss implements Serializable{

	/**
	 * 자격득실확인서
	 */
	private static final long serialVersionUID = -6794383776743991339L;

	protected String biz_nm ; //사업자명
	protected String error_cd ; //에러코드
	protected String error_msg ; //에러메세지
	protected String gain_ymd ; //취득일자
	protected String inquiry_result ; //조회결과
	protected String loss_ymd ; //상실일자
	protected String member_division ; //가입자구분
	protected String register_dt ; //등록일시
	protected int    seq_health_insu ; //일련번호_건강보험
	protected int    seq_qualification_gainsnlosses ; //일련번호_자격득실
	
	public String getBiz_nm() {
		return biz_nm;
	}

	public void setBiz_nm(String biz_nm) {
		this.biz_nm = biz_nm;
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

	public String getLoss_ymd() {
		return loss_ymd;
	}

	public void setLoss_ymd(String loss_ymd) {
		this.loss_ymd = loss_ymd;
	}

	public String getMember_division() {
		return member_division;
	}

	public void setMember_division(String member_division) {
		this.member_division = member_division;
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

	public int getSeq_qualification_gainsnlosses() {
		return seq_qualification_gainsnlosses;
	}

	public void setSeq_qualification_gainsnlosses(int seq_qualification_gainsnlosses) {
		this.seq_qualification_gainsnlosses = seq_qualification_gainsnlosses;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
