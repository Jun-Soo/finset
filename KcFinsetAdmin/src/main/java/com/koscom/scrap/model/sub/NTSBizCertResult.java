package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class NTSBizCertResult implements Serializable { // com.koscom.scrap.model.sub.NTSBizCertResult

	private static final long serialVersionUID = 3878635583504167578L;

	private int SEQ_REQ;				// 일련번호_요청
	
	@SerializedName("발급번호")
	private String PBLS_NO;				// 발급번호
	
	@SerializedName("상호_법인명")
	private String CORP_NM;				// 법인명
	
	@SerializedName("사업자등록번호")
	private String BIZ_LICENCE;			// 사업자등록번호
	
	@SerializedName("성명_대표자")
	private String CEO_NAME; 			// 대표자성명
	
	@SerializedName("주민_법인_등록번호")
	private String SSN;					// 주민번호	
	
	@SerializedName("사업장소재지")
	private String COMP_ADDR1;			// 회사주소1
	
	@SerializedName("개업일")
	private String OPEN_DD;				// 개업일
	
	@SerializedName("사업자등록일")
	private String BIZ_REGISTER_DD;		// 사업자등록일
	
	@SerializedName("발급세무서")
	private String PBLS_INSTITUTION_NM;	// 발급기관명
	
	@SerializedName("접수번호")
	private String RCPT_NO;				// 접수번호
	
	@SerializedName("담당부서")
	private String RESPONSIBILITY_DEPT;	// 담당부서
	
	@SerializedName("담당자")
	private String PERSONINCHARGE;		// 담당자
	
	@SerializedName("담당자_연락처")
	private String CONTACT_PH;			// 연락전화
	
	@SerializedName("접수일시")
	private String PBLS_YMD;			// 발급일자	
	
	@SerializedName("과세유형")
	private String TAXATION_TYPE;		// 과세유형	
	
	private String ErrorCode;			// 에러코드
	private String ErrorMessage;		// 에러메시지

	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public String getPBLS_NO() {
		return PBLS_NO;
	}

	public void setPBLS_NO(String pBLS_NO) {
		PBLS_NO = pBLS_NO;
	}

	public String getCORP_NM() {
		return CORP_NM;
	}

	public void setCORP_NM(String cORP_NM) {
		CORP_NM = cORP_NM;
	}

	public String getBIZ_LICENCE() {
		return BIZ_LICENCE;
	}

	public void setBIZ_LICENCE(String bIZ_LICENCE) {
		BIZ_LICENCE = bIZ_LICENCE;
	}

	public String getCEO_NAME() {
		return CEO_NAME;
	}

	public void setCEO_NAME(String cEO_NAME) {
		CEO_NAME = cEO_NAME;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getCOMP_ADDR1() {
		return COMP_ADDR1;
	}

	public void setCOMP_ADDR1(String cOMP_ADDR1) {
		COMP_ADDR1 = cOMP_ADDR1;
	}

	public String getOPEN_DD() {
		return OPEN_DD;
	}

	public void setOPEN_DD(String oPEN_DD) {
		OPEN_DD = oPEN_DD;
	}

	public String getBIZ_REGISTER_DD() {
		return BIZ_REGISTER_DD;
	}

	public void setBIZ_REGISTER_DD(String bIZ_REGISTER_DD) {
		BIZ_REGISTER_DD = bIZ_REGISTER_DD;
	}

	public String getPBLS_INSTITUTION_NM() {
		return PBLS_INSTITUTION_NM;
	}

	public void setPBLS_INSTITUTION_NM(String pBLS_INSTITUTION_NM) {
		PBLS_INSTITUTION_NM = pBLS_INSTITUTION_NM;
	}

	public String getRCPT_NO() {
		return RCPT_NO;
	}

	public void setRCPT_NO(String rCPT_NO) {
		RCPT_NO = rCPT_NO;
	}

	public String getRESPONSIBILITY_DEPT() {
		return RESPONSIBILITY_DEPT;
	}

	public void setRESPONSIBILITY_DEPT(String rESPONSIBILITY_DEPT) {
		RESPONSIBILITY_DEPT = rESPONSIBILITY_DEPT;
	}

	public String getPERSONINCHARGE() {
		return PERSONINCHARGE;
	}

	public void setPERSONINCHARGE(String pERSONINCHARGE) {
		PERSONINCHARGE = pERSONINCHARGE;
	}

	public String getCONTACT_PH() {
		return CONTACT_PH;
	}

	public void setCONTACT_PH(String cONTACT_PH) {
		CONTACT_PH = cONTACT_PH;
	}

	public String getPBLS_YMD() {
		return PBLS_YMD;
	}

	public void setPBLS_YMD(String pBLS_YMD) {
		PBLS_YMD = pBLS_YMD;
	}

	public String getTAXATION_TYPE() {
		return TAXATION_TYPE;
	}

	public void setTAXATION_TYPE(String tAXATION_TYPE) {
		TAXATION_TYPE = tAXATION_TYPE;
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
