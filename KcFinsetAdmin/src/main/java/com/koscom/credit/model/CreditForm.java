package com.koscom.credit.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CreditForm implements Serializable{
	private static final long serialVersionUID = 2254789704866807521L;

	private String no_person;	//고객 번호
	
	private String nm_cust; //성명
	
	private String no_inq_key;	// 조회키  ( safekey , 인증송부 재조회키 , kcb 대체키 등 실제 전문조회에 사용하는 키값)
	
	private String nm_if;		// 전문명
	
	private String nm_if_sub; //전문상세명
	
	//private String cd_cb_cause; //조회사유코드
	private String cd_cust_type;	////조회사유코드  : 이걸 이용하여 전문별로 조회 사유코드 적절히 셋팅되도록 한다. 
	
	private String cd_agree_cause; //조회동의코드
	
	private String id_emp; //직원아이디
	
	private String cd_cb_comp; //신용정보사 코드
	
	private List<String> in_search_datas; //IN검색 조건절 데이터들
	
	private String ymd_basic;
	
	private String no_person_srch;
	
	private String fk1;
	
	private String fk2;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	

	public String getNm_cust() {
		return nm_cust;
	}



	public void setNm_cust(String nm_cust) {
		this.nm_cust = nm_cust;
	}



	public String getNo_person() {
		return no_person;
	}


	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}


	public String getNo_inq_key() {
		return no_inq_key;
	}


	public void setNo_inq_key(String no_inq_key) {
		this.no_inq_key = no_inq_key;
	}


	public String getNm_if() {
		return nm_if;
	}


	public void setNm_if(String nm_if) {
		this.nm_if = nm_if;
	}


	public String getCd_cust_type() {
		return cd_cust_type;
	}


	public void setCd_cust_type(String cd_cust_type) {
		this.cd_cust_type = cd_cust_type;
	}


	public String getCd_agree_cause() {
		return cd_agree_cause;
	}


	public void setCd_agree_cause(String cd_agree_cause) {
		this.cd_agree_cause = cd_agree_cause;
	}


	public List<String> getIn_search_datas() {
		return in_search_datas;
	}


	public void setIn_search_datas(List<String> in_search_datas) {
		this.in_search_datas = in_search_datas;
	}



	public String getId_emp() {
		return id_emp;
	}



	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}



	public String getNm_if_sub() {
		return nm_if_sub;
	}



	public void setNm_if_sub(String nm_if_sub) {
		this.nm_if_sub = nm_if_sub;
	}



	public String getCd_cb_comp() {
		return cd_cb_comp;
	}



	public void setCd_cb_comp(String cd_cb_comp) {
		this.cd_cb_comp = cd_cb_comp;
	}



	public String getYmd_basic() {
		return ymd_basic;
	}



	public void setYmd_basic(String ymd_basic) {
		this.ymd_basic = ymd_basic;
	}



	public String getNo_person_srch() {
		return no_person_srch;
	}



	public void setNo_person_srch(String no_person_srch) {
		this.no_person_srch = no_person_srch;
	}



	public String getFk1() {
		return fk1;
	}



	public void setFk1(String fk1) {
		this.fk1 = fk1;
	}



	public String getFk2() {
		return fk2;
	}



	public void setFk2(String fk2) {
		this.fk2 = fk2;
	}
	
}
