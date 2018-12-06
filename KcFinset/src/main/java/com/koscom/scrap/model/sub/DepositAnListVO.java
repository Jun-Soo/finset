package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

public class DepositAnListVO implements Serializable{
	private static final long serialVersionUID = 5429917307616046874L;
	String ERROR_CODE = "";			//에러코드
	String ERROR_MESSAGE = "";		//에러메시지
	String AN = "";					//계좌번호
	String TYPE = "";				//예금종류
	String NEW_DATE = "";			//신규일자
	String LAST_DATE = "";			//만기일자
	String BALANCE = "";			//현재잔액
	String INTEREST = "";			//이자율  
	String DT_START = "";			//조회시작일
	String DT_END = "";				//조회종료일
	List<DepositAnListHistoryVO> HISTORY;	//거래내역 리스트
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
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getNEW_DATE() {
		return NEW_DATE;
	}
	public void setNEW_DATE(String nEW_DATE) {
		NEW_DATE = nEW_DATE;
	}
	public String getLAST_DATE() {
		return LAST_DATE;
	}
	public void setLAST_DATE(String lAST_DATE) {
		LAST_DATE = lAST_DATE;
	}
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getINTEREST() {
		return INTEREST;
	}
	public void setINTEREST(String iNTEREST) {
		INTEREST = iNTEREST;
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
	public List<DepositAnListHistoryVO> getHISTORY() {
		return HISTORY;
	}
	public void setHISTORY(List<DepositAnListHistoryVO> hISTORY) {
		HISTORY = hISTORY;
	}
}