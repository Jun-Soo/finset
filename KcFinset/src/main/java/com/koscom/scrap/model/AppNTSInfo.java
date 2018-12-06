package com.koscom.scrap.model;

import java.io.Serializable;

public class AppNTSInfo implements Serializable{
	private static final long serialVersionUID = 754855622525105151L;
	protected String NO_PERSON = "";			//회원관리번호
	protected String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패;
	protected long SEQ_SCRAP = 0;				//스크래핑 결과 시퀀스
	
	protected NTSInfo USER_NTS_OUTPUT;  		//국세청 스크래핑 내역
	
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
	public long getSEQ_SCRAP() {
		return SEQ_SCRAP;
	}
	public void setSEQ_SCRAP(long sEQ_SCRAP) {
		SEQ_SCRAP = sEQ_SCRAP;
	}
	public NTSInfo getUSER_NTS_OUTPUT() {
		return USER_NTS_OUTPUT;
	}
	public void setUSER_NTS_OUTPUT(NTSInfo uSER_NTS_OUTPUT) {
		USER_NTS_OUTPUT = uSER_NTS_OUTPUT;
	}
}