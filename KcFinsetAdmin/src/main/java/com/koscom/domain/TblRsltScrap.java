package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRsltScrap implements Serializable{

	/**
	 * 스크래핑실행결과
	 */
	private static final long serialVersionUID = 1223310348728084727L;
	
	protected String goods_cd 			;	// 상품코드
	protected String health_insu_result ;	// 건강보험결과
	protected String hp 				;	// 휴대폰
	protected String inquiry_path 		;	// 조회경로
	protected String no_biz_comp 		;	// 번호사업자회사
	protected String no_person 			;	// 회원고유번호
	protected String ntnltxsrvc_result 	;	// 국세청결과
	protected String register_dt 		;	// 등록일시
	protected int    seq_scraping_result; 	// 일련번호_스크래핑결과
	protected String terms_agree 		;	// 약관동의
	
	public String getGoods_cd() {
		return goods_cd;
	}



	public void setGoods_cd(String goods_cd) {
		this.goods_cd = goods_cd;
	}



	public String getHealth_insu_result() {
		return health_insu_result;
	}



	public void setHealth_insu_result(String health_insu_result) {
		this.health_insu_result = health_insu_result;
	}



	public String getHp() {
		return hp;
	}



	public void setHp(String hp) {
		this.hp = hp;
	}



	public String getInquiry_path() {
		return inquiry_path;
	}



	public void setInquiry_path(String inquiry_path) {
		this.inquiry_path = inquiry_path;
	}



	public String getNo_biz_comp() {
		return no_biz_comp;
	}



	public void setNo_biz_comp(String no_biz_comp) {
		this.no_biz_comp = no_biz_comp;
	}



	public String getNo_person() {
		return no_person;
	}



	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}



	public String getNtnltxsrvc_result() {
		return ntnltxsrvc_result;
	}



	public void setNtnltxsrvc_result(String ntnltxsrvc_result) {
		this.ntnltxsrvc_result = ntnltxsrvc_result;
	}



	public String getRegister_dt() {
		return register_dt;
	}



	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}



	public int getSeq_scraping_result() {
		return seq_scraping_result;
	}



	public void setSeq_scraping_result(int seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}



	public String getTerms_agree() {
		return terms_agree;
	}



	public void setTerms_agree(String terms_agree) {
		this.terms_agree = terms_agree;
	}



	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
