package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

/**
 * 국민건강보험공단 VO
 * @author HSJ
 */
public class NHISInfo implements Serializable {	// com.koscom.scrap.model.NhisHealth

	private static final long serialVersionUID = -7606872792690330418L;
	
	private String PAYMENT_START_YM = "";		//조회시작일
	private String PAYMENT_END_YM = "";		//조회종료일

	private String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패
	private String ERROR_MESSAGE = "";		//결과메시지

	private List<ScrRespHealthPaymentVO> PAYMENT;	//납부확인서

	public String getPAYMENT_START_YM() {
		return PAYMENT_START_YM;
	}
	public void setPAYMENT_START_YM(String pAYMENT_START_YM) {
		PAYMENT_START_YM = pAYMENT_START_YM;
	}
	public String getPAYMENT_END_YM() {
		return PAYMENT_END_YM;
	}
	public void setPAYMENT_END_YM(String pAYMENT_END_YM) {
		PAYMENT_END_YM = pAYMENT_END_YM;
	}
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
	public List<ScrRespHealthPaymentVO> getPAYMENT() {
		return PAYMENT;
	}
	public void setPAYMENT(List<ScrRespHealthPaymentVO> pAYMENT) {
		PAYMENT = pAYMENT;
	}
}
