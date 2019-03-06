package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NTSBizCert implements Serializable {

	private static final long serialVersionUID = 1802464543659046686L;
	
	private String ErrorCode;			// 에러코드
	private String ErrorMessage;		// 에러메시지
	
	private NTSBizCertResult Result;

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

	
	public NTSBizCertResult getResult() {
		return Result;
	}

	public void setResult(NTSBizCertResult result) {
		Result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
