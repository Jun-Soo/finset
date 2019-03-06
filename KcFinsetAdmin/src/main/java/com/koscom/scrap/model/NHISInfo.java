package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.scrap.model.sub.NHISPayment;
import com.koscom.scrap.model.sub.NHISQlfcti;
import com.koscom.scrap.model.sub.NHISQlfctngainlos;

/**
 * 국민건강보험공단 VO
 * @author HSJ
 */
public class NHISInfo implements Serializable {	// com.koscom.scrap.model.NhisHealth

	private static final long serialVersionUID = -7606872792690330418L;

	private int SEQ_HEALTH_INSU;		// 일련번호_건강보험
	
	private String INQUIRY_START_YEARMONTH; // 조회시작연월
	private String INQUIRY_END_YEARMONTH;	// 조회종료연월
	
	private NHISPayment PAYMENT;	// 납부확인서
	private NHISQlfcti QLFCTI; 		// 자격득실확인서
	private NHISQlfctngainlos QLFCTNGAINLOS;	// 자격확인서
	
	private int SEQ_SCRAPING_RESULT; 		// 일련번호_스크래핑결과
	
	private String ErrorCode; 	
	private String ErrorMessage; 

	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}

	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
	}

	public String getINQUIRY_START_YEARMONTH() {
		return INQUIRY_START_YEARMONTH;
	}

	public void setINQUIRY_START_YEARMONTH(String iNQUIRY_START_YEARMONTH) {
		INQUIRY_START_YEARMONTH = iNQUIRY_START_YEARMONTH;
	}

	public String getINQUIRY_END_YEARMONTH() {
		return INQUIRY_END_YEARMONTH;
	}

	public void setINQUIRY_END_YEARMONTH(String iNQUIRY_END_YEARMONTH) {
		INQUIRY_END_YEARMONTH = iNQUIRY_END_YEARMONTH;
	}
	
	public NHISPayment getPAYMENT() {
		return PAYMENT;
	}

	public void setPAYMENT(NHISPayment pAYMENT) {
		PAYMENT = pAYMENT;
	}

	public NHISQlfcti getQLFCTI() {
		return QLFCTI;
	}

	public void setQLFCTI(NHISQlfcti qLFCTI) {
		QLFCTI = qLFCTI;
	}

	public NHISQlfctngainlos getQLFCTNGAINLOS() {
		return QLFCTNGAINLOS;
	}

	public void setQLFCTNGAINLOS(NHISQlfctngainlos qLFCTNGAINLOS) {
		QLFCTNGAINLOS = qLFCTNGAINLOS;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public int getSEQ_SCRAPING_RESULT() {
		return SEQ_SCRAPING_RESULT;
	}

	public void setSEQ_SCRAPING_RESULT(int sEQ_SCRAPING_RESULT) {
		SEQ_SCRAPING_RESULT = sEQ_SCRAPING_RESULT;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	

}
