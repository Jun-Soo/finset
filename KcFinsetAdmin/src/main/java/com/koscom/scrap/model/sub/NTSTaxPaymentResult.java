package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 납부내역증명(납세사실증명)VO
 * @author czsjhong-n1
 *
 */
public class NTSTaxPaymentResult implements Serializable {
	
	private static final long serialVersionUID = -1092043208352511738L;

	private int SEQ_REQ;		// 일련번호
	
	@SerializedName("발급번호")
	private String PBLS_NO;		// 발급번호
	
	@SerializedName("상호_법인명")
	private String CORP_NM;		// 법인명
	
	@SerializedName("사업자등록번호")
	private String BIZ_LICENCE;	// 사업자등록번호
	
	@SerializedName("성명_대표자")
	private String CEO;			// 대표자
	
	@SerializedName("주민_법인_등록번호")
	private String SSN;			// 주민번호
	
	@SerializedName("주소_본점")
	private String ADDR1;		// 주소_본점
	
	@SerializedName("사업장_지점")
	private String ADDR2;		// 사업장_지점
	
	@SerializedName("증명발급대상수납기간")
	private String CERT_PBLS_TARGET_RCPT_TERM;	// 증명발급대상수납기간
	
	@SerializedName("납부내역증명상세")
	private List<NTSTaxPaymentDtl> taxPaymentList;
	
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

	public String getCEO() {
		return CEO;
	}

	public void setCEO(String cEO) {
		CEO = cEO;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getADDR1() {
		return ADDR1;
	}

	public void setADDR1(String aDDR1) {
		ADDR1 = aDDR1;
	}

	public String getADDR2() {
		return ADDR2;
	}

	public void setADDR2(String aDDR2) {
		ADDR2 = aDDR2;
	}

	public String getCERT_PBLS_TARGET_RCPT_TERM() {
		return CERT_PBLS_TARGET_RCPT_TERM;
	}

	public void setCERT_PBLS_TARGET_RCPT_TERM(String cERT_PBLS_TARGET_RCPT_TERM) {
		CERT_PBLS_TARGET_RCPT_TERM = cERT_PBLS_TARGET_RCPT_TERM;
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

	public List<NTSTaxPaymentDtl> getTaxPaymentList() {
		return taxPaymentList;
	}

	public void setTaxPaymentList(List<NTSTaxPaymentDtl> taxPaymentList) {
		this.taxPaymentList = taxPaymentList;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
