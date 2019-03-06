package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Scrap implements Serializable {  // com.koscom.scrap.model.Scrap

	private static final long serialVersionUID = 5226701841397340456L;
	
	private NHISInfo NHIS_OUTPUT;		// 국민건강보험공단
	private NTSInfo NTS_OUTPUT;			// 국세청
	
	private String NO_BIZ_COMP;			// 사업자 등록 번호
	private String HP;					// 핸드폰번호
	private String INQUIRY_PATH;		// 조회경로
	private String CUST_NM;
	private String TERMS_AGREE;			// 약관
	private String NO_PERSON;			
	private String GOODS_CD;
	
	private String HEALTH_INSU_RESULT;	// 건강보험결과
	private String NTNLTXSRVC_RESULT;   // 국세청결과
	
	private int SEQ_SCRAPING_RESULT; 	// 일련번호_스크래핑결과
	
	public String getCUST_NM() {
		return CUST_NM;
	}

	public void setCUST_NM(String cUST_NM) {
		CUST_NM = cUST_NM;
	}

	public NHISInfo getNHIS_OUTPUT() {
		return NHIS_OUTPUT;
	}

	public void setNHIS_OUTPUT(NHISInfo nHIS_OUTPUT) {
		NHIS_OUTPUT = nHIS_OUTPUT;
	}

	public NTSInfo getNTS_OUTPUT() {
		return NTS_OUTPUT;
	}

	public void setNTS_OUTPUT(NTSInfo nTS_OUTPUT) {
		NTS_OUTPUT = nTS_OUTPUT;
	}

	public int getSEQ_SCRAPING_RESULT() {
		return SEQ_SCRAPING_RESULT;
	}

	public void setSEQ_SCRAPING_RESULT(int sEQ_SCRAPING_RESULT) {
		SEQ_SCRAPING_RESULT = sEQ_SCRAPING_RESULT;
	}

	public String getNO_BIZ_COMP() {
		return NO_BIZ_COMP;
	}

	public void setNO_BIZ_COMP(String nO_BIZ_COMP) {
		NO_BIZ_COMP = nO_BIZ_COMP;
	}

	public String getHP() {
		return HP;
	}

	public void setHP(String hP) {
		HP = hP;
	}

	public String getINQUIRY_PATH() {
		return INQUIRY_PATH;
	}

	public void setINQUIRY_PATH(String iNQUIRY_PATH) {
		INQUIRY_PATH = iNQUIRY_PATH;
	}

	public String getTERMS_AGREE() {
		return TERMS_AGREE;
	}

	public void setTERMS_AGREE(String tERMS_AGREE) {
		TERMS_AGREE = tERMS_AGREE;
	}

	public String getNO_PERSON() {
		return NO_PERSON;
	}

	public void setNO_PERSON(String nO_PERSON) {
		NO_PERSON = nO_PERSON;
	}

	public String getGOODS_CD() {
		return GOODS_CD;
	}

	public void setGOODS_CD(String gOODS_CD) {
		GOODS_CD = gOODS_CD;
	}

	public String getHEALTH_INSU_RESULT() {
		return HEALTH_INSU_RESULT;
	}

	public void setHEALTH_INSU_RESULT(String hEALTH_INSU_RESULT) {
		HEALTH_INSU_RESULT = hEALTH_INSU_RESULT;
	}

	public String getNTNLTXSRVC_RESULT() {
		return NTNLTXSRVC_RESULT;
	}

	public void setNTNLTXSRVC_RESULT(String nTNLTXSRVC_RESULT) {
		NTNLTXSRVC_RESULT = nTNLTXSRVC_RESULT;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
