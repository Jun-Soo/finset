package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 국세청 휴폐업 VO
 * @author 홍성준
 *
 */
public class NTSBizStatus implements Serializable {	// com.hdsb.cstmr.scrap.model.sub.NTSBizStatus
	
	private static final long serialVersionUID = 271692940108744988L;

	private String INQUIRY_TARGET_BIZ_NO; 	// 사업자번호	
	
	private String ErrorCode;				// 에러코드
	private String ErrorMessage;			// 에러메세지
	
	private NTSBizStatusResult Result;

	public String getINQUIRY_TARGET_BIZ_NO() {
		return INQUIRY_TARGET_BIZ_NO;
	}

	public void setINQUIRY_TARGET_BIZ_NO(String iNQUIRY_TARGET_BIZ_NO) {
		INQUIRY_TARGET_BIZ_NO = iNQUIRY_TARGET_BIZ_NO;
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

	public NTSBizStatusResult getResult() {
		return Result;
	}

	public void setResult(NTSBizStatusResult result) {
		Result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
