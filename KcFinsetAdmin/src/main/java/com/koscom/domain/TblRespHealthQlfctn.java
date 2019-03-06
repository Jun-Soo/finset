package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespHealthQlfctn implements Serializable{

	/**
	 * 자격확인서
	 */
	private static final long serialVersionUID = 2754499217091699273L;

	protected String card_no 						;	//증번호
	protected String comp_management_no  			;	//사업장관리번호
	protected String error_cd  						;	//에러코드
	protected String error_msg  					;	//에러메세지
	protected String inquiry_result  				;	//조회결과
	protected String member_name  					;	//가입자성명
	protected String member_ssn  					;	//가입자주민번호
	protected String notice_no  					;	//통보번호
	protected String qualification_confirm_req_ymd  ;	//자격확인요청일자
	protected String receive_institution_nm  		;	//수신기관명
	protected String register_dt  					;	//등록일시
	protected int    seq_health_insu  				;	//일련번호_건강보험
	
	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getComp_management_no() {
		return comp_management_no;
	}

	public void setComp_management_no(String comp_management_no) {
		this.comp_management_no = comp_management_no;
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

	public String getInquiry_result() {
		return inquiry_result;
	}

	public void setInquiry_result(String inquiry_result) {
		this.inquiry_result = inquiry_result;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_ssn() {
		return member_ssn;
	}

	public void setMember_ssn(String member_ssn) {
		this.member_ssn = member_ssn;
	}

	public String getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
	}

	public String getQualification_confirm_req_ymd() {
		return qualification_confirm_req_ymd;
	}

	public void setQualification_confirm_req_ymd(String qualification_confirm_req_ymd) {
		this.qualification_confirm_req_ymd = qualification_confirm_req_ymd;
	}

	public String getReceive_institution_nm() {
		return receive_institution_nm;
	}

	public void setReceive_institution_nm(String receive_institution_nm) {
		this.receive_institution_nm = receive_institution_nm;
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
