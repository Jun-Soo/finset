package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 국세청 휴폐업 VO sub
 * @author 홍성준
 *
 */
public class NTSBizStatusResult implements Serializable {	
	
	private static final long serialVersionUID = -2392942614235254129L;

	private int SEQ_REQ; 				// 일련번호
	
	private String INQUIRY_TARGET_BIZ_NO; 	// 사업자번호
	
	@SerializedName("폐업일자")
	private String CLOSE_DD;				// 폐업일	
	
	@SerializedName("조회결과코드")
	private String SITE_MSG_CD;				// 사이트메세지코드	
	
	private String ErrorCode;				// 에러코드
	
	private String ErrorMessage;			// 에러메세지

	public int getSEQ_REQ() {
		return SEQ_REQ;
	}

	public void setSEQ_REQ(int sEQ_REQ) {
		SEQ_REQ = sEQ_REQ;
	}

	public String getINQUIRY_TARGET_BIZ_NO() {
		return INQUIRY_TARGET_BIZ_NO;
	}

	public void setINQUIRY_TARGET_BIZ_NO(String iNQUIRY_TARGET_BIZ_NO) {
		INQUIRY_TARGET_BIZ_NO = iNQUIRY_TARGET_BIZ_NO;
	}

	public String getCLOSE_DD() {
		return CLOSE_DD;
	}

	public void setCLOSE_DD(String cLOSE_DD) {
		CLOSE_DD = cLOSE_DD;
	}

	public String getSITE_MSG_CD() {
		return SITE_MSG_CD;
	}

	public void setSITE_MSG_CD(String sITE_MSG_CD) {
		SITE_MSG_CD = sITE_MSG_CD;
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
