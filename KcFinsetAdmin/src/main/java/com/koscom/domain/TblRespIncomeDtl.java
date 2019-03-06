package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespIncomeDtl implements Serializable{

	/**
	 * 소득금액증명원조회결과_소득증명상세
	 */
	private static final long serialVersionUID = 3168887230513724696L;

	protected String biz_licence ; //사업자등록번호
	protected String corp_nm ; //법인명
	protected int    income_amt ; //소득금액
	protected String income_division ; //소득구분
	protected int    order_income_cert_detail ; //순서_소득증명상세
	protected String register_dt ; //등록일시
	protected String reversion_year ; //귀속연도
	protected int    seq_req ; //일련번호_요청
	protected int    total_decision_tax_amt ; //총결정세금금액
	
	public String getBiz_licence() {
		return biz_licence;
	}

	public void setBiz_licence(String biz_licence) {
		this.biz_licence = biz_licence;
	}

	public String getCorp_nm() {
		return corp_nm;
	}

	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}

	public int getIncome_amt() {
		return income_amt;
	}

	public void setIncome_amt(int income_amt) {
		this.income_amt = income_amt;
	}

	public String getIncome_division() {
		return income_division;
	}

	public void setIncome_division(String income_division) {
		this.income_division = income_division;
	}

	public int getOrder_income_cert_detail() {
		return order_income_cert_detail;
	}

	public void setOrder_income_cert_detail(int order_income_cert_detail) {
		this.order_income_cert_detail = order_income_cert_detail;
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

	public int getTotal_decision_tax_amt() {
		return total_decision_tax_amt;
	}

	public void setTotal_decision_tax_amt(int total_decision_tax_amt) {
		this.total_decision_tax_amt = total_decision_tax_amt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
