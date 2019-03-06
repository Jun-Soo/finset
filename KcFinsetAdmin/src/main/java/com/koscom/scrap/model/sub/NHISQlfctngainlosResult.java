package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 자격확인서 vo
 * @author HSJ
 *
 */
public class NHISQlfctngainlosResult implements Serializable { 

	private static final long serialVersionUID = -2029349033434743840L;

	private int SEQ_HEALTH_INSU;					// 일련번호_건강보험
	
	private String INQUIRY_RESULT;					// 조회결과
	
	@SerializedName("통보번호")
	private String NOTICE_NO;						// 통보번호
	
	@SerializedName("수신기관명")
	private String RECEIVE_INSTITUTION_NM;			// 수신기관명
	
	@SerializedName("자격확인요청일")
	private String QUALIFICATION_CONFIRM_REQ_YMD;	// 자격확인요청일자
	
	@SerializedName("증번호")
	private String CARD_NO;							// 증번호
	
	@SerializedName("사업장관리번호")
	private String COMP_MANAGEMENT_NO;				// 사업장관리번호
	
	@SerializedName("가입자_성명")
	private String MEMBER_NAME;						// 가입자성명
	
	@SerializedName("가입자_주민등록번호")
	private String MEMBER_SSN;						// 가입자주민번호
	
	@SerializedName("자격확인내역")
	private List<NHISQlfctngainlosResultDetail> nhisQlfctngainlosResultDetailList;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지

	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}

	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
	}

	public String getINQUIRY_RESULT() {
		return INQUIRY_RESULT;
	}

	public void setINQUIRY_RESULT(String iNQUIRY_RESULT) {
		INQUIRY_RESULT = iNQUIRY_RESULT;
	}

	public String getNOTICE_NO() {
		return NOTICE_NO;
	}

	public void setNOTICE_NO(String nOTICE_NO) {
		NOTICE_NO = nOTICE_NO;
	}

	public String getRECEIVE_INSTITUTION_NM() {
		return RECEIVE_INSTITUTION_NM;
	}

	public void setRECEIVE_INSTITUTION_NM(String rECEIVE_INSTITUTION_NM) {
		RECEIVE_INSTITUTION_NM = rECEIVE_INSTITUTION_NM;
	}

	public String getQUALIFICATION_CONFIRM_REQ_YMD() {
		return QUALIFICATION_CONFIRM_REQ_YMD;
	}

	public void setQUALIFICATION_CONFIRM_REQ_YMD(String qUALIFICATION_CONFIRM_REQ_YMD) {
		QUALIFICATION_CONFIRM_REQ_YMD = qUALIFICATION_CONFIRM_REQ_YMD;
	}

	public String getCARD_NO() {
		return CARD_NO;
	}

	public void setCARD_NO(String cARD_NO) {
		CARD_NO = cARD_NO;
	}

	public String getCOMP_MANAGEMENT_NO() {
		return COMP_MANAGEMENT_NO;
	}

	public void setCOMP_MANAGEMENT_NO(String cOMP_MANAGEMENT_NO) {
		COMP_MANAGEMENT_NO = cOMP_MANAGEMENT_NO;
	}

	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}

	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}

	public String getMEMBER_SSN() {
		return MEMBER_SSN;
	}

	public void setMEMBER_SSN(String mEMBER_SSN) {
		MEMBER_SSN = mEMBER_SSN;
	}

	public List<NHISQlfctngainlosResultDetail> getNhisQlfctngainlosResultDetailList() {
		return nhisQlfctngainlosResultDetailList;
	}

	public void setNhisQlfctngainlosResultDetailList(
			List<NHISQlfctngainlosResultDetail> nhisQlfctngainlosResultDetailList) {
		this.nhisQlfctngainlosResultDetailList = nhisQlfctngainlosResultDetailList;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
