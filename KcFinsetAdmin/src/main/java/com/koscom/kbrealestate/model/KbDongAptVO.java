package com.koscom.kbrealestate.model;

import java.io.Serializable;

public class KbDongAptVO implements Serializable{
	private static final long serialVersionUID = -2318059038706060975L;
	private String RESULT_CD = "";	//조회 결과 코드값
	private String RESULT_MG = "";	//조회 결과 메시지
	private int TOTAL_COUNT = 0;	//결과건수
	private String BUILDING_TYPE = "";	//1 : 아파트, 2 : 오피스텔
	private String REGION1_CODE= "";			//대지역코드
	private String REGION1_NAME= "";			//대지역명
	private String REGION2_CODE= "";			//중지역코드
	private String REGION2_NAME= "";			//중지역명
	private String REGION3_CODE= "";			//소지역코드
	private String REGION3_NAME= "";			//소지역명
	private String LDONG_CODE= "";			//법정동코드
	private String APARTMENT= "";			//아파트코드-물건식별자
	private String APARTMENT_NAME= "";			//단지명
	private String auto_com_txt= "";			
	
	public String getRESULT_CD() {
		return RESULT_CD;
	}
	public void setRESULT_CD(String rESULT_CD) {
		RESULT_CD = rESULT_CD;
	}
	public String getRESULT_MG() {
		return RESULT_MG;
	}
	public void setRESULT_MG(String rESULT_MG) {
		RESULT_MG = rESULT_MG;
	}
	public int getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}
	public void setTOTAL_COUNT(int tOTAL_COUNT) {
		TOTAL_COUNT = tOTAL_COUNT;
	}
	public String getBUILDING_TYPE() {
		return BUILDING_TYPE;
	}
	public void setBUILDING_TYPE(String bUILDING_TYPE) {
		BUILDING_TYPE = bUILDING_TYPE;
	}
	public String getREGION1_CODE() {
		return REGION1_CODE;
	}
	public void setREGION1_CODE(String rEGION1_CODE) {
		REGION1_CODE = rEGION1_CODE;
	}
	public String getREGION1_NAME() {
		return REGION1_NAME;
	}
	public void setREGION1_NAME(String rEGION1_NAME) {
		REGION1_NAME = rEGION1_NAME;
	}
	public String getREGION2_CODE() {
		return REGION2_CODE;
	}
	public void setREGION2_CODE(String rEGION2_CODE) {
		REGION2_CODE = rEGION2_CODE;
	}
	public String getREGION2_NAME() {
		return REGION2_NAME;
	}
	public void setREGION2_NAME(String rEGION2_NAME) {
		REGION2_NAME = rEGION2_NAME;
	}
	public String getREGION3_CODE() {
		return REGION3_CODE;
	}
	public void setREGION3_CODE(String rEGION3_CODE) {
		REGION3_CODE = rEGION3_CODE;
	}
	public String getREGION3_NAME() {
		return REGION3_NAME;
	}
	public void setREGION3_NAME(String rEGION3_NAME) {
		REGION3_NAME = rEGION3_NAME;
	}
	public String getLDONG_CODE() {
		return LDONG_CODE;
	}
	public void setLDONG_CODE(String lDONG_CODE) {
		LDONG_CODE = lDONG_CODE;
	}
	public String getAPARTMENT() {
		return APARTMENT;
	}
	public void setAPARTMENT(String aPARTMENT) {
		APARTMENT = aPARTMENT;
	}
	public String getAPARTMENT_NAME() {
		return APARTMENT_NAME;
	}
	public void setAPARTMENT_NAME(String aPARTMENT_NAME) {
		APARTMENT_NAME = aPARTMENT_NAME;
	}
	public String getAuto_com_txt() {
		return auto_com_txt;
	}
	public void setAuto_com_txt(String auto_com_txt) {
		this.auto_com_txt = auto_com_txt;
	}
	
}