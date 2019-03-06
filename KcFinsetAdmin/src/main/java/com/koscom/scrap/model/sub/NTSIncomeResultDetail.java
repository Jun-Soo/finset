package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 국세청 VO sub
 * @author HSJ
 *
 */
public class NTSIncomeResultDetail implements Serializable { // com.koscom.scrap.model.sub.NTSIncomeResultDetail
	
	private static final long serialVersionUID = 8766045905108605979L;

	private int SEQ_REQ;					 // 일련번호
	
	private int ORDER_INCOME_CERT_DETAIL; // 순서_소득증명상세
	
	@SerializedName("소득구분")
	private String INCOME_DIVISION;			 // 소득구분
	
	@SerializedName("법인명")
	private String CORP_NM;					 // 법인명
	
	@SerializedName("귀속연도")
	private String REVERSION_YEAR;			 // 귀속연도
	
	@SerializedName("사업자등록번호")
	private String BIZ_LICENCE;				 // 사업자등록번호
	
	@SerializedName("소득금액")
	private String INCOME_AMT;				 // 소득금액
	
	@SerializedName("총결정세액")
	private String TOTAL_DECISION_TAX_AMT;	 // 총결정세금금액
	
	private String ErrorCode;					// 에러코드
	private String ErrorMessage;				// 에러메세지

	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public int getORDER_INCOME_CERT_DETAIL() {
		return ORDER_INCOME_CERT_DETAIL;
	}

	public void setORDER_INCOME_CERT_DETAIL(int oRDER_INCOME_CERT_DETAIL) {
		ORDER_INCOME_CERT_DETAIL = oRDER_INCOME_CERT_DETAIL;
	}

	public String getINCOME_DIVISION() {
		return INCOME_DIVISION;
	}

	public void setINCOME_DIVISION(String iNCOME_DIVISION) {
		INCOME_DIVISION = iNCOME_DIVISION;
	}

	public String getCORP_NM() {
		return CORP_NM;
	}

	public void setCORP_NM(String cORP_NM) {
		CORP_NM = cORP_NM;
	}

	public String getREVERSION_YEAR() {
		return REVERSION_YEAR;
	}

	public void setREVERSION_YEAR(String rEVERSION_YEAR) {
		REVERSION_YEAR = rEVERSION_YEAR;
	}

	public String getBIZ_LICENCE() {
		return BIZ_LICENCE;
	}

	public void setBIZ_LICENCE(String bIZ_LICENCE) {
		BIZ_LICENCE = bIZ_LICENCE;
	}

	public String getINCOME_AMT() {
		return INCOME_AMT;
	}

	public void setINCOME_AMT(String iNCOME_AMT) {
		INCOME_AMT = iNCOME_AMT;
	}

	public String getTOTAL_DECISION_TAX_AMT() {
		return TOTAL_DECISION_TAX_AMT;
	}

	public void setTOTAL_DECISION_TAX_AMT(String tOTAL_DECISION_TAX_AMT) {
		TOTAL_DECISION_TAX_AMT = tOTAL_DECISION_TAX_AMT;
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
