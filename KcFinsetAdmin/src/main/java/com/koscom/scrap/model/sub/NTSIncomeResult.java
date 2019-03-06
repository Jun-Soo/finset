package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 국세청 VO sub
 * @author HSJ
 *
 */
public class NTSIncomeResult implements Serializable {

	private static final long serialVersionUID = -2079659842921483661L;

	private int SEQ_REQ;					// 일련번호 국세청
	
	@SerializedName("발급번호")
	private String PBLS_NO;
	
	@SerializedName("주소")
	private String ADDR1;
	
	@SerializedName("성명")
	private String NAME;
	
	@SerializedName("주민등록번호")
	private String SSN;
	
	@SerializedName("발급세무서")
	private String PBLS_INSTITUTION_NM;
	
	@SerializedName("소득금액상세")
	private List<NTSIncomeResultDetail> ntsIncomeResultDetailList;
	
	private String ErrorCode;					// 에러코드
	private String ErrorMessage;				// 에러메세지

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

	public String getADDR1() {
		return ADDR1;
	}

	public void setADDR1(String aDDR1) {
		ADDR1 = aDDR1;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getPBLS_INSTITUTION_NM() {
		return PBLS_INSTITUTION_NM;
	}

	public void setPBLS_INSTITUTION_NM(String pBLS_INSTITUTION_NM) {
		PBLS_INSTITUTION_NM = pBLS_INSTITUTION_NM;
	}

	public List<NTSIncomeResultDetail> getNtsIncomeResultDetailList() {
		return ntsIncomeResultDetailList;
	}

	public void setNtsIncomeResultDetailList(List<NTSIncomeResultDetail> ntsIncomeResultDetailList) {
		this.ntsIncomeResultDetailList = ntsIncomeResultDetailList;
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
