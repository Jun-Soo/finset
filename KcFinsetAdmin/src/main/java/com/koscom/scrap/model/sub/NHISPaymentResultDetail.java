package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class NHISPaymentResultDetail implements Serializable {	

	private static final long serialVersionUID = 8693916899338528803L;

	private int SEQ_HEALTH_INSU;	// 일련번호_건강보험
	
	private int ORDER_PAY_CONFIRM;	// 순서
	
	private String INQUIRY_RESULT;	// 조회결과
	
	@SerializedName("발급번호")
	private String PBLS_NO;			// 발급번호
	
	@SerializedName("납부자번호")
	private String PAYER_NO;		// 납부자번호
	
	@SerializedName("가입자구분")
	private String MEMBER_DIVISION;	// 가입자구분
	
	@SerializedName("사업장명칭")
	private String COMP_NM;			// 회사명
	
	@SerializedName("취득일")
	private String GAIN_YMD;		// 취득일자
	
	@SerializedName("상실일")
	private String LOSS_DD;			// 상실일
	
	@SerializedName("소속지사")
	private String BELONG_BRANCH;	// 소속지사
	
	@SerializedName("가입자성명")
	private String MEMBER_NAME;		// 가입자성명
	
	@SerializedName("생년월일")
	private String BGN;				// 생년월일
	
	private String REGISTER_DT;		// 등록일시
	
	@SerializedName("납부내역상세")
	private List<NHISPaymentResultDetailSub> nhisPaymentDetailList;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지

	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}

	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
	}

	public int getORDER_PAY_CONFIRM() {
		return ORDER_PAY_CONFIRM;
	}

	public void setORDER_PAY_CONFIRM(int oRDER_PAY_CONFIRM) {
		ORDER_PAY_CONFIRM = oRDER_PAY_CONFIRM;
	}

	public String getINQUIRY_RESULT() {
		return INQUIRY_RESULT;
	}

	public void setINQUIRY_RESULT(String iNQUIRY_RESULT) {
		INQUIRY_RESULT = iNQUIRY_RESULT;
	}

	public String getPBLS_NO() {
		return PBLS_NO;
	}

	public void setPBLS_NO(String pBLS_NO) {
		PBLS_NO = pBLS_NO;
	}

	public String getPAYER_NO() {
		return PAYER_NO;
	}

	public void setPAYER_NO(String pAYER_NO) {
		PAYER_NO = pAYER_NO;
	}

	public String getMEMBER_DIVISION() {
		return MEMBER_DIVISION;
	}

	public void setMEMBER_DIVISION(String mEMBER_DIVISION) {
		MEMBER_DIVISION = mEMBER_DIVISION;
	}

	public String getCOMP_NM() {
		return COMP_NM;
	}

	public void setCOMP_NM(String cOMP_NM) {
		COMP_NM = cOMP_NM;
	}

	public String getGAIN_YMD() {
		return GAIN_YMD;
	}

	public void setGAIN_YMD(String gAIN_YMD) {
		GAIN_YMD = gAIN_YMD;
	}

	public String getLOSS_DD() {
		return LOSS_DD;
	}

	public void setLOSS_DD(String lOSS_DD) {
		LOSS_DD = lOSS_DD;
	}

	public String getBELONG_BRANCH() {
		return BELONG_BRANCH;
	}

	public void setBELONG_BRANCH(String bELONG_BRANCH) {
		BELONG_BRANCH = bELONG_BRANCH;
	}

	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}

	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}

	public String getBGN() {
		return BGN;
	}

	public void setBGN(String bGN) {
		BGN = bGN;
	}

	public String getREGISTER_DT() {
		return REGISTER_DT;
	}

	public void setREGISTER_DT(String rEGISTER_DT) {
		REGISTER_DT = rEGISTER_DT;
	}

	public List<NHISPaymentResultDetailSub> getNhisPaymentDetailList() {
		return nhisPaymentDetailList;
	}

	public void setNhisPaymentDetailList(List<NHISPaymentResultDetailSub> nhisPaymentDetailList) {
		this.nhisPaymentDetailList = nhisPaymentDetailList;
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
