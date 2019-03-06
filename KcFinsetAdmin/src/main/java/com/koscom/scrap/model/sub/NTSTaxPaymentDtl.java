package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 납부내역증명(납세사실증명)SUB
 * @author czsjhong-n1
 *
 */
public class NTSTaxPaymentDtl implements Serializable {	

	private static final long serialVersionUID = 548644467436883696L;

	private int SEQ_REQ; 		// 일련번호
	
	private int ORDER_TAXPMNT_CERT_DETAIL;	// 순서_납세증명상세
	
	@SerializedName("귀속연도")
	private String REVERSION_YEAR;				// 귀속연도
	
	@SerializedName("세목")
	private String ITEMS;		// 세목
	
	@SerializedName("납부일")
	private String PAY_DD;		// 납부일
	
	@SerializedName("합계")
	private String SUM;			// 합계
	
	@SerializedName("내국세")
	private String INLAND_TAX;		// 내국세	
	
	@SerializedName("농어촌특별세")
	private String FRMNGNFSNG_SPECIAL_TAX;	// 농어촌특별세
	
	@SerializedName("교육_방위세")
	private String EDUCATION_DEFENSE_TAX;	// 교육방위세
	
	@SerializedName("가산금")
	private String ADDED_AMT;		// 가산금

	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public int getORDER_TAXPMNT_CERT_DETAIL() {
		return ORDER_TAXPMNT_CERT_DETAIL;
	}

	public void setORDER_TAXPMNT_CERT_DETAIL(int oRDER_TAXPMNT_CERT_DETAIL) {
		ORDER_TAXPMNT_CERT_DETAIL = oRDER_TAXPMNT_CERT_DETAIL;
	}

	public String getREVERSION_YEAR() {
		return REVERSION_YEAR;
	}

	public void setREVERSION_YEAR(String rEVERSION_YEAR) {
		REVERSION_YEAR = rEVERSION_YEAR;
	}

	public String getITEMS() {
		return ITEMS;
	}

	public void setITEMS(String iTEMS) {
		ITEMS = iTEMS;
	}

	public String getPAY_DD() {
		return PAY_DD;
	}

	public void setPAY_DD(String pAY_DD) {
		PAY_DD = pAY_DD;
	}

	public String getSUM() {
		return SUM;
	}

	public void setSUM(String sUM) {
		SUM = sUM;
	}

	public String getINLAND_TAX() {
		return INLAND_TAX;
	}

	public void setINLAND_TAX(String iNLAND_TAX) {
		INLAND_TAX = iNLAND_TAX;
	}

	public String getFRMNGNFSNG_SPECIAL_TAX() {
		return FRMNGNFSNG_SPECIAL_TAX;
	}

	public void setFRMNGNFSNG_SPECIAL_TAX(String fRMNGNFSNG_SPECIAL_TAX) {
		FRMNGNFSNG_SPECIAL_TAX = fRMNGNFSNG_SPECIAL_TAX;
	}

	public String getEDUCATION_DEFENSE_TAX() {
		return EDUCATION_DEFENSE_TAX;
	}

	public void setEDUCATION_DEFENSE_TAX(String eDUCATION_DEFENSE_TAX) {
		EDUCATION_DEFENSE_TAX = eDUCATION_DEFENSE_TAX;
	}

	public String getADDED_AMT() {
		return ADDED_AMT;
	}

	public void setADDED_AMT(String aDDED_AMT) {
		ADDED_AMT = aDDED_AMT;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
