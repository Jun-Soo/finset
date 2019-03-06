package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class NTSVatResultDtl implements Serializable {	// com.koscom.scrap.model.sub.NtsVatResultDtl

	private static final long serialVersionUID = -3664061696742027804L;

	private int SEQ_REQ;						// 일련번호
	
	private int ORDER_VAT;					// 순서_부가가치세
	
	@SerializedName("과세시작일")
	private String TAXATION_START_DD;			// 과세시작일
	
	@SerializedName("과세종료일")
	private String TAXATION_END_DD;				// 과세종료일	
	
	@SerializedName("매출과세표준_계")
	private String SALES_TAXATION_STANDARD_SUM;	// 매출과세표준합계	
	
	@SerializedName("매출과세표준_과세분")
	private String SALES_TXTN_STNDRD_TXTN_AMT;	// 매출과세표준과세금액	
	
	@SerializedName("매출과세표준_면제분")
	private String SLS_TXTN_STNDRD_EXMPTN_AMT;	// 매출과세표준면제금액
	
	@SerializedName("납부세액")
	private String PAY_TAX_AMT;					// 납부세금금액	
	
	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public int getORDER_VAT() {
		return ORDER_VAT;
	}

	public void setORDER_VAT(int oRDER_VAT) {
		ORDER_VAT = oRDER_VAT;
	}

	public String getTAXATION_START_DD() {
		return TAXATION_START_DD;
	}

	public void setTAXATION_START_DD(String tAXATION_START_DD) {
		TAXATION_START_DD = tAXATION_START_DD;
	}

	public String getTAXATION_END_DD() {
		return TAXATION_END_DD;
	}

	public void setTAXATION_END_DD(String tAXATION_END_DD) {
		TAXATION_END_DD = tAXATION_END_DD;
	}

	public String getSALES_TAXATION_STANDARD_SUM() {
		return SALES_TAXATION_STANDARD_SUM;
	}

	public void setSALES_TAXATION_STANDARD_SUM(String sALES_TAXATION_STANDARD_SUM) {
		SALES_TAXATION_STANDARD_SUM = sALES_TAXATION_STANDARD_SUM;
	}

	public String getSALES_TXTN_STNDRD_TXTN_AMT() {
		return SALES_TXTN_STNDRD_TXTN_AMT;
	}

	public void setSALES_TXTN_STNDRD_TXTN_AMT(String sALES_TXTN_STNDRD_TXTN_AMT) {
		SALES_TXTN_STNDRD_TXTN_AMT = sALES_TXTN_STNDRD_TXTN_AMT;
	}

	public String getSLS_TXTN_STNDRD_EXMPTN_AMT() {
		return SLS_TXTN_STNDRD_EXMPTN_AMT;
	}

	public void setSLS_TXTN_STNDRD_EXMPTN_AMT(String sLS_TXTN_STNDRD_EXMPTN_AMT) {
		SLS_TXTN_STNDRD_EXMPTN_AMT = sLS_TXTN_STNDRD_EXMPTN_AMT;
	}

	public String getPAY_TAX_AMT() {
		return PAY_TAX_AMT;
	}

	public void setPAY_TAX_AMT(String pAY_TAX_AMT) {
		PAY_TAX_AMT = pAY_TAX_AMT;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
