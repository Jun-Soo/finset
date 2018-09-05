package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

public class AppNHISInfo implements Serializable{
	private static final long serialVersionUID = -7131982343623610981L;

	protected String NO_PERSON = "";
	
	protected String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패
		
	protected NHISInfo  USER_NHIS_OUTPUT;  		//건강보험 수집 내역

	public String getNO_PERSON() {
		return NO_PERSON;
	}

	public void setNO_PERSON(String nO_PERSON) {
		NO_PERSON = nO_PERSON;
	}

	public String getERROR_CODE() {
		return ERROR_CODE;
	}

	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}

	public NHISInfo getUSER_NHIS_OUTPUT() {
		return USER_NHIS_OUTPUT;
	}

	public void setUSER_NHIS_OUTPUT(NHISInfo uSER_NHIS_OUTPUT) {
		USER_NHIS_OUTPUT = uSER_NHIS_OUTPUT;
	}


}