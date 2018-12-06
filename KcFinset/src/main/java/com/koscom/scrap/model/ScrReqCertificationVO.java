package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrReqCertificationVO implements Serializable{
	private static final long serialVersionUID = 5924274835701526360L;
	protected String no_person= "";				//회원고유번호
	protected long seq_scraping_result= 0;		//스크래핑일련번호
	protected String cd_type= "";				//국세청항목구분
	protected String biz_licence= "";			//사업자등록번호
	protected String hp= "";					//전화번호
	protected String rcpt_start_month= "";		//수납시작월
	protected String rcpt_end_month= "";		//수납종료월
	protected String taxation_start_month= "";	//과세시작월
	protected String taxation_end_month= "";	//과세종료월
	protected String taxation_year= "";			//과세연도
	protected String cd_income= "";				//소득구분
	protected String cert_division= "";			//증명구분
	protected String ymd_hope_open= "";			//발급희망개업일자
	protected String cd_open_yn= "";			//공개여부코드
	protected String no_reception= "";			//접수번호
	protected String no_pbls= "";				//발급번호
	protected String nm_issued_agency= "";		//발급기관명
	protected String department= "";			//담당부서
	protected String manager= "";				//담당자
	protected String tel_manager= "";			//담당자연락처
	protected String error_cd= "";				//에러코드
	protected String error_msg= "";				//에러메세지
	protected String dt_frt= "";				//발행구분
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public long getSeq_scraping_result() {
		return seq_scraping_result;
	}
	public void setSeq_scraping_result(long seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}
	public String getCd_type() {
		return cd_type;
	}
	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}
	public String getBiz_licence() {
		return biz_licence;
	}
	public void setBiz_licence(String biz_licence) {
		this.biz_licence = biz_licence;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getRcpt_start_month() {
		return rcpt_start_month;
	}
	public void setRcpt_start_month(String rcpt_start_month) {
		this.rcpt_start_month = rcpt_start_month;
	}
	public String getRcpt_end_month() {
		return rcpt_end_month;
	}
	public void setRcpt_end_month(String rcpt_end_month) {
		this.rcpt_end_month = rcpt_end_month;
	}
	public String getTaxation_start_month() {
		return taxation_start_month;
	}
	public void setTaxation_start_month(String taxation_start_month) {
		this.taxation_start_month = taxation_start_month;
	}
	public String getTaxation_end_month() {
		return taxation_end_month;
	}
	public void setTaxation_end_month(String taxation_end_month) {
		this.taxation_end_month = taxation_end_month;
	}
	public String getTaxation_year() {
		return taxation_year;
	}
	public void setTaxation_year(String taxation_year) {
		this.taxation_year = taxation_year;
	}
	public String getCd_income() {
		return cd_income;
	}
	public void setCd_income(String cd_income) {
		this.cd_income = cd_income;
	}
	public String getCert_division() {
		return cert_division;
	}
	public void setCert_division(String cert_division) {
		this.cert_division = cert_division;
	}
	public String getYmd_hope_open() {
		return ymd_hope_open;
	}
	public void setYmd_hope_open(String ymd_hope_open) {
		this.ymd_hope_open = ymd_hope_open;
	}
	public String getCd_open_yn() {
		return cd_open_yn;
	}
	public void setCd_open_yn(String cd_open_yn) {
		this.cd_open_yn = cd_open_yn;
	}
	public String getNo_reception() {
		return no_reception;
	}
	public void setNo_reception(String no_reception) {
		this.no_reception = no_reception;
	}
	public String getNo_pbls() {
		return no_pbls;
	}
	public void setNo_pbls(String no_pbls) {
		this.no_pbls = no_pbls;
	}
	public String getNm_issued_agency() {
		return nm_issued_agency;
	}
	public void setNm_issued_agency(String nm_issued_agency) {
		this.nm_issued_agency = nm_issued_agency;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getTel_manager() {
		return tel_manager;
	}
	public void setTel_manager(String tel_manager) {
		this.tel_manager = tel_manager;
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
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
