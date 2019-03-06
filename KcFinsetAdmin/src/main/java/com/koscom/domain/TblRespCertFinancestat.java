package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespCertFinancestat implements Serializable{

	
	/**
	 * 민원증명통합조회결과_표준재무제표증명
	 */
	private static final long serialVersionUID = 8673707516307534932L;
	protected int seq_req;	//일련번호_요청
	protected String reversion_year= "";	//귀속연도
	protected String inquiry_result= "";	//조회결과
	protected String pbls_no= "";	//발급번호
	protected String corp_nm= "";	//법인명
	protected String biz_licence= "";	//사업자등록번호
	protected String ceo= "";	//대표자
	protected String comp_addr1= "";	//회사주소1
	protected String comp_addr2= "";	//회사주소2
	protected String biztype= "";	//업태
	protected String items= "";	//종목
	protected String pbls_institution_nm= "";	//발급기관명
	protected String error_cd= "";	//에러코드
	protected String error_msg= "";	//에러메세지
	protected String register_dt= "";	//등록일시

	public String getBiztype() {
		return biztype;
	}


	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}


	public String getBiz_licence() {
		return biz_licence;
	}


	public void setBiz_licence(String biz_licence) {
		this.biz_licence = biz_licence;
	}


	public String getCeo() {
		return ceo;
	}


	public void setCeo(String ceo) {
		this.ceo = ceo;
	}


	public String getComp_addr1() {
		return comp_addr1;
	}


	public void setComp_addr1(String comp_addr1) {
		this.comp_addr1 = comp_addr1;
	}


	public String getComp_addr2() {
		return comp_addr2;
	}


	public void setComp_addr2(String comp_addr2) {
		this.comp_addr2 = comp_addr2;
	}


	public String getCorp_nm() {
		return corp_nm;
	}


	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
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


	public String getItems() {
		return items;
	}


	public void setItems(String items) {
		this.items = items;
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


	public String getRegister_dt() {
		return register_dt;
	}


	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}


	public String getReversion_year() {
		return reversion_year;
	}


	public void setReversion_year(String reversion_year) {
		this.reversion_year = reversion_year;
	}


	public int getSeq_req() {
		return seq_req;
	}


	public void setSeq_req(int seq_req) {
		this.seq_req = seq_req;
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
