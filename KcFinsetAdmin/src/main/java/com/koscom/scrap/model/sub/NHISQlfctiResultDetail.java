package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 자격득실확인서 vo
 * @author HSJ
 *
 */
public class NHISQlfctiResultDetail implements Serializable {	

	private static final long serialVersionUID = -171316463599750535L;
	
	private int SEQ_HEALTH_INSU; // 일련번호_건강보험
	private int SEQ_QUALIFICATION_GAINSNLOSSES;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지
	
	private String INQUIRY_RESULT; // 조회결과
	
	@SerializedName("가입자구분")
	private String MEMBER_DIVISION; // 가입자구분
	
	@SerializedName("사업자명칭")
	private String BIZ_NM; // 사업자명
	
	@SerializedName("취득일")
	private String GAIN_YMD; // 취득일자
	
	@SerializedName("상실일")
	private String LOSS_YMD; // 상실일자

	public int getSEQ_QUALIFICATION_GAINSNLOSSES() {
		return SEQ_QUALIFICATION_GAINSNLOSSES;
	}

	public void setSEQ_QUALIFICATION_GAINSNLOSSES(int sEQ_QUALIFICATION_GAINSNLOSSES) {
		SEQ_QUALIFICATION_GAINSNLOSSES = sEQ_QUALIFICATION_GAINSNLOSSES;
	}

	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}

	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
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

	public String getINQUIRY_RESULT() {
		return INQUIRY_RESULT;
	}

	public void setINQUIRY_RESULT(String iNQUIRY_RESULT) {
		INQUIRY_RESULT = iNQUIRY_RESULT;
	}

	public String getMEMBER_DIVISION() {
		return MEMBER_DIVISION;
	}

	public void setMEMBER_DIVISION(String mEMBER_DIVISION) {
		MEMBER_DIVISION = mEMBER_DIVISION;
	}

	public String getBIZ_NM() {
		return BIZ_NM;
	}

	public void setBIZ_NM(String bIZ_NM) {
		BIZ_NM = bIZ_NM;
	}

	public String getGAIN_YMD() {
		return GAIN_YMD;
	}

	public void setGAIN_YMD(String gAIN_YMD) {
		GAIN_YMD = gAIN_YMD;
	}

	public String getLOSS_YMD() {
		return LOSS_YMD;
	}

	public void setLOSS_YMD(String lOSS_YMD) {
		LOSS_YMD = lOSS_YMD;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
