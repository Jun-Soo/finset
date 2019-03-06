package com.koscom.scrap.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class AppBankForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = -1868786104179083571L;
	private String BUILDING_TYPE = "";	//1 : 아파트, 2 : 오피스텔
	private String REGION1_CODE= "";			//대지역코드
	private String REGION2_CODE= "";			//중지역코드
	private String REGION3_CODE= "";			//소지역코드
	private String APARTMENT= "";			//아파트코드-물건식별자
	private String PRICE_TYPE = "";
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
	public String getREGION2_CODE() {
		return REGION2_CODE;
	}
	public void setREGION2_CODE(String rEGION2_CODE) {
		REGION2_CODE = rEGION2_CODE;
	}
	public String getREGION3_CODE() {
		return REGION3_CODE;
	}
	public void setREGION3_CODE(String rEGION3_CODE) {
		REGION3_CODE = rEGION3_CODE;
	}
	public String getAPARTMENT() {
		return APARTMENT;
	}
	public void setAPARTMENT(String aPARTMENT) {
		APARTMENT = aPARTMENT;
	}
	public String getPRICE_TYPE() {
		return PRICE_TYPE;
	}
	public void setPRICE_TYPE(String pRICE_TYPE) {
		PRICE_TYPE = pRICE_TYPE;
	}
}
