package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

public class AnAllListVO implements Serializable{
	private static final long serialVersionUID = -5238091914959561246L;
	String ERROR_CODE = "";			//에러코드
	String ERROR_MESSAGE = "";		//에러메시지
	String AN = "";					//계좌번호
	String BALANCE = "";			//잔액
	String CODE = "";				//통화코드
	String DATE = "";				//신규일자
	String NAME = "";				//예금명(ex.보통예금(IBK평생한가족통장))
	String DT_START = "";			//조회시작일
	String DT_END = "";				//조회종료일
	List<AnAllListHistoryVO> HISTORY;	//거래내역 리스트
	public String getERROR_CODE() {
		return ERROR_CODE;
	}
	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}
	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}
	public void setERROR_MESSAGE(String eRROR_MESSAGE) {
		ERROR_MESSAGE = eRROR_MESSAGE;
	}
	public String getAN() {
		return AN;
	}
	public void setAN(String aN) {
		AN = aN;
	}
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getDT_START() {
		return DT_START;
	}
	public void setDT_START(String dT_START) {
		DT_START = dT_START;
	}
	public String getDT_END() {
		return DT_END;
	}
	public void setDT_END(String dT_END) {
		DT_END = dT_END;
	}
	public List<AnAllListHistoryVO> getHISTORY() {
		return HISTORY;
	}
	public void setHISTORY(List<AnAllListHistoryVO> hISTORY) {
		HISTORY = hISTORY;
	}
}