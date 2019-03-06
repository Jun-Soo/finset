package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NTSVat implements Serializable {

	private static final long serialVersionUID = 2791072416572172293L;
	
	private String ErrorCode;			// 에러코드
	private String ErrorMessage;		// 에러메시지
	
	private NTSVatResult Result;

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

	public NTSVatResult getResult() {
		return Result;
	}

	public void setResult(NTSVatResult result) {
		Result = result;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
