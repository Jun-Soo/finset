package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblReqCertification implements Serializable{

	/**
	 * 민원증명통합조회요청
	 */
	private static final long serialVersionUID = -4450667991790148352L;
	
	protected String biz_licence 			; //사업자등록번호
	protected String cert_division 			; //증명구분
	protected String error_cd 				; //에러코드
	protected String error_msg 				; //에러메세지
	protected String open_yn_cd 			; //공개여부코드
	protected String rcpt_end_month 		; //수납종료월
	protected String rcpt_start_month 		; //수납시작월
	protected String register_dt 			; //등록일시
	protected int    seq_req 				; //일련번호
	protected int    seq_scraping_result 	; //일련번호스크래핑결과
	protected String taxation_end_month 	; //과세종료월
	protected String taxation_start_month 	; //과세시작월
	protected String taxation_year 		 	; //과세연도

	
	public String getBiz_licence() {
		return biz_licence;
	}


	public void setBiz_licence(String biz_licence) {
		this.biz_licence = biz_licence;
	}


	public String getCert_division() {
		return cert_division;
	}


	public void setCert_division(String cert_division) {
		this.cert_division = cert_division;
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


	public String getOpen_yn_cd() {
		return open_yn_cd;
	}


	public void setOpen_yn_cd(String open_yn_cd) {
		this.open_yn_cd = open_yn_cd;
	}


	public String getRcpt_end_month() {
		return rcpt_end_month;
	}


	public void setRcpt_end_month(String rcpt_end_month) {
		this.rcpt_end_month = rcpt_end_month;
	}


	public String getRcpt_start_month() {
		return rcpt_start_month;
	}


	public void setRcpt_start_month(String rcpt_start_month) {
		this.rcpt_start_month = rcpt_start_month;
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


	public int getSeq_scraping_result() {
		return seq_scraping_result;
	}


	public void setSeq_scraping_result(int seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}


	public String getTaxation_end_month() {
		return taxation_end_month;
	}


	public void setTaxation_end_month(String taxation_end_month) {
		this.taxation_end_month = taxation_end_month;
	}


	public String getTaxation_start_month() {
		return taxation_start_month;
	}


	public void setTaxation_start_month(String taxation_start_month) {
		this.taxation_start_month = taxation_start_month;
	}


	public String getTaxation_year() {
		return taxation_year;
	}


	public void setTaxation_year(String taxation_year) {
		this.taxation_year = taxation_year;
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
