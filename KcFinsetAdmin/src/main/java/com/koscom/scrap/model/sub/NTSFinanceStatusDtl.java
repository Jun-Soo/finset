package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 표준재무제표증명 sub
 * @author czsjhong-n1
 *
 */
public class NTSFinanceStatusDtl implements Serializable {	

	private static final long serialVersionUID = 2099061356181807556L;

	private int SEQ_REQ;
	
	private int ORDER_FINANCIALSTAT;
	
	@SerializedName("자산총계")
	private String ASSETS_TOTAL_AMT;
	
	@SerializedName("부채총계")
	private String DEBT_TOTAL_AMT;
	
	@SerializedName("자본금")
	private String CAPITAL_AMT;
	
	@SerializedName("당기순이익")
	private String THISTERM_NETINCOME;
	
	@SerializedName("자본총계")
	private String CAPITAL_TOTAL_AMT;
	
	@SerializedName("부채자본총계")
	private String DEBT_CAPITAL_TOTAL_SUM;
	
	private String REVERSION_YEAR;		// 귀속연도
	
	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public int getORDER_FINANCIALSTAT() {
		return ORDER_FINANCIALSTAT;
	}

	public void setORDER_FINANCIALSTAT(int oRDER_FINANCIALSTAT) {
		ORDER_FINANCIALSTAT = oRDER_FINANCIALSTAT;
	}

	public String getASSETS_TOTAL_AMT() {
		return ASSETS_TOTAL_AMT;
	}

	public void setASSETS_TOTAL_AMT(String aSSETS_TOTAL_AMT) {
		ASSETS_TOTAL_AMT = aSSETS_TOTAL_AMT;
	}

	public String getDEBT_TOTAL_AMT() {
		return DEBT_TOTAL_AMT;
	}

	public void setDEBT_TOTAL_AMT(String dEBT_TOTAL_AMT) {
		DEBT_TOTAL_AMT = dEBT_TOTAL_AMT;
	}

	public String getCAPITAL_AMT() {
		return CAPITAL_AMT;
	}

	public void setCAPITAL_AMT(String cAPITAL_AMT) {
		CAPITAL_AMT = cAPITAL_AMT;
	}

	public String getTHISTERM_NETINCOME() {
		return THISTERM_NETINCOME;
	}

	public void setTHISTERM_NETINCOME(String tHISTERM_NETINCOME) {
		THISTERM_NETINCOME = tHISTERM_NETINCOME;
	}

	public String getCAPITAL_TOTAL_AMT() {
		return CAPITAL_TOTAL_AMT;
	}

	public void setCAPITAL_TOTAL_AMT(String cAPITAL_TOTAL_AMT) {
		CAPITAL_TOTAL_AMT = cAPITAL_TOTAL_AMT;
	}

	public String getDEBT_CAPITAL_TOTAL_SUM() {
		return DEBT_CAPITAL_TOTAL_SUM;
	}

	public void setDEBT_CAPITAL_TOTAL_SUM(String dEBT_CAPITAL_TOTAL_SUM) {
		DEBT_CAPITAL_TOTAL_SUM = dEBT_CAPITAL_TOTAL_SUM;
	}

	public String getREVERSION_YEAR() {
		return REVERSION_YEAR;
	}

	public void setREVERSION_YEAR(String rEVERSION_YEAR) {
		REVERSION_YEAR = rEVERSION_YEAR;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
