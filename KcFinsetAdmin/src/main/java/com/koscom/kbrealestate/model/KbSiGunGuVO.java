package com.koscom.kbrealestate.model;

import java.io.Serializable;

public class KbSiGunGuVO implements Serializable {
	private static final long serialVersionUID = -8932305931874283510L;
	private String REGION1_CODE= "";			//대지역코드
	private String REGION1_NAME= "";			//대지역명
	private String REGION2_CODE= "";			//중지역코드
	private String REGION2_NAME= "";			//중지역명
	private String RESULT_CD = "";	//조회 결과 코드값
	private String RESULT_MG = "";	//조회 결과 메시지
	private int TOTAL_COUNT = 0;	//결과건수
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
}
