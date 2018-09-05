package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

public class AppCardInfo implements Serializable{
	private static final long serialVersionUID = -7131982343623610981L;

	protected String NO_PERSON = "";
	protected String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패;
	protected long SEQ_SCRAP = 0;
	
	protected List<UserCardOutputVO> USER_CARD_OUTPUT;  //대출상품 리스트
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
	public List<UserCardOutputVO> getUSER_CARD_OUTPUT() {
		return USER_CARD_OUTPUT;
	}
	public void setUSER_CARD_OUTPUT(List<UserCardOutputVO> uSER_CARD_OUTPUT) {
		USER_CARD_OUTPUT = uSER_CARD_OUTPUT;
	}
}