package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblReqHealth implements Serializable{

	/**
	 * 건강보험조회요청
	 */
	private static final long serialVersionUID = -364484547132948959L;
	
	protected String ERROR_CD 					; //에러코드
	protected String ERROR_MSG 					; //에러메세지
	protected String INQUIRY_END_YEARMONTH 		; //조회종료연월
	protected String INQUIRY_START_YEARMONTH 	; //조회시작연월
	protected String REGISTER_DT 				; //등록일시
	protected int    SEQ_HEALTH_INSU 			; //일련번호_건강보험
	protected int    SEQ_SCRAPING_RESULT 		; //일련번호스크래핑결과
	
	

	public String getERROR_CD() {
		return ERROR_CD;
	}



	public void setERROR_CD(String eRROR_CD) {
		ERROR_CD = eRROR_CD;
	}



	public String getERROR_MSG() {
		return ERROR_MSG;
	}



	public void setERROR_MSG(String eRROR_MSG) {
		ERROR_MSG = eRROR_MSG;
	}



	public String getINQUIRY_END_YEARMONTH() {
		return INQUIRY_END_YEARMONTH;
	}



	public void setINQUIRY_END_YEARMONTH(String iNQUIRY_END_YEARMONTH) {
		INQUIRY_END_YEARMONTH = iNQUIRY_END_YEARMONTH;
	}



	public String getINQUIRY_START_YEARMONTH() {
		return INQUIRY_START_YEARMONTH;
	}



	public void setINQUIRY_START_YEARMONTH(String iNQUIRY_START_YEARMONTH) {
		INQUIRY_START_YEARMONTH = iNQUIRY_START_YEARMONTH;
	}



	public String getREGISTER_DT() {
		return REGISTER_DT;
	}



	public void setREGISTER_DT(String rEGISTER_DT) {
		REGISTER_DT = rEGISTER_DT;
	}



	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}



	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
	}



	public int getSEQ_SCRAPING_RESULT() {
		return SEQ_SCRAPING_RESULT;
	}



	public void setSEQ_SCRAPING_RESULT(int sEQ_SCRAPING_RESULT) {
		SEQ_SCRAPING_RESULT = sEQ_SCRAPING_RESULT;
	}



	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
