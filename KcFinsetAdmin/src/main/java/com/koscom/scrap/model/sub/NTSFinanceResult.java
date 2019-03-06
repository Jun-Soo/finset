package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 표준재무제표증명 sub
 * @author czsjhong-n1
 *
 */
public class NTSFinanceResult implements Serializable {

	private static final long serialVersionUID = 7662869086198540167L;

	private int SEQ_REQ; 		// 일련번호
	
	@SerializedName("발급번호")
	private String PBLS_NO; 	// 발급번호
	
	@SerializedName("상호_법인명")
	private String CORP_NM; 	// 법인명
	
	@SerializedName("사업자등록번호")
	private String BIZ_LICENCE;	// 사업자등록번호
	
	@SerializedName("성명_대표자")
	private String CEO;			// 대표자
	
	@SerializedName("주민_법인_등록번호")
	private String SSN;			// 주민번호
	
	@SerializedName("사업장주소")
	private String COMP_ADDR1;	// 회사주소1
	
	@SerializedName("업태")
	private String BIZTYPE;		// 업태
	
	@SerializedName("종목")
	private String ITEMS;		// 종목
	
	@SerializedName("귀속연도")
	private String REVERSION_YEAR;		// 귀속연도
	
	@SerializedName("발급기관")
	private String PBLS_INSTITUTION_NM; // 발급기관명
	
	@SerializedName("표준재무제표증명_표준재무상태표")
	private List<NTSFinanceStatusDtl> finDtlList;
	
	@SerializedName("표준재무제표증명_표준손익계산서")
	private List<NTSFinanceCalDtl> calDtlList;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지

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

	public String getCOMP_ADDR1() {
		return COMP_ADDR1;
	}

	public void setCOMP_ADDR1(String cOMP_ADDR1) {
		COMP_ADDR1 = cOMP_ADDR1;
	}

	public String getBIZTYPE() {
		return BIZTYPE;
	}

	public void setBIZTYPE(String bIZTYPE) {
		BIZTYPE = bIZTYPE;
	}

	public String getITEMS() {
		return ITEMS;
	}

	public void setITEMS(String iTEMS) {
		ITEMS = iTEMS;
	}

	public String getREVERSION_YEAR() {
		return REVERSION_YEAR;
	}

	public void setREVERSION_YEAR(String rEVERSION_YEAR) {
		REVERSION_YEAR = rEVERSION_YEAR;
	}

	public String getPBLS_INSTITUTION_NM() {
		return PBLS_INSTITUTION_NM;
	}

	public void setPBLS_INSTITUTION_NM(String pBLS_INSTITUTION_NM) {
		PBLS_INSTITUTION_NM = pBLS_INSTITUTION_NM;
	}

	public List<NTSFinanceStatusDtl> getFinDtlList() {
		return finDtlList;
	}

	public void setFinDtlList(List<NTSFinanceStatusDtl> finDtlList) {
		this.finDtlList = finDtlList;
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
	
	public List<NTSFinanceCalDtl> getCalDtlList() {
		return calDtlList;
	}

	public void setCalDtlList(List<NTSFinanceCalDtl> calDtlList) {
		this.calDtlList = calDtlList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
