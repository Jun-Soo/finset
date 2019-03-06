package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespIncome implements Serializable{

	
	/**
	 * 소득금액증명원조회결과_소득증명
	 */
	private static final long serialVersionUID = -6680667119582932479L;

	protected String addr ; //주소
	protected String cert_pbls_target_rcpt_term ; //증명발급대상수납기간
	protected String error_cd ; //에러코드
	protected String error_msg ; //에러메세지
	protected String name ; //성명
	protected String pbls_institution_nm ; //발급기관명
	protected String pbls_no ; //발급번호
	protected String pbls_ymd ; //발급일자
	protected String register_dt ; //등록일시
	protected int    seq_req ; //일련번호_요청
	protected String ssn ; //주민번호
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCert_pbls_target_rcpt_term() {
		return cert_pbls_target_rcpt_term;
	}

	public void setCert_pbls_target_rcpt_term(String cert_pbls_target_rcpt_term) {
		this.cert_pbls_target_rcpt_term = cert_pbls_target_rcpt_term;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPbls_institution_nm() {
		return pbls_institution_nm;
	}

	public void setPbls_institution_nm(String pbls_institution_nm) {
		this.pbls_institution_nm = pbls_institution_nm;
	}

	public String getPbls_no() {
		return pbls_no;
	}

	public void setPbls_no(String pbls_no) {
		this.pbls_no = pbls_no;
	}

	public String getPbls_ymd() {
		return pbls_ymd;
	}

	public void setPbls_ymd(String pbls_ymd) {
		this.pbls_ymd = pbls_ymd;
	}

	public String getRegister_dt() {
		return register_dt;
	}

	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}

	public int getSeq_req() {
		return seq_req;
	}

	public void setSeq_req(int seq_req) {
		this.seq_req = seq_req;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
