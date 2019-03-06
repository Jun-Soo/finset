package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NTSTaxPayment implements Serializable {

	private static final long serialVersionUID = -3839917280575769629L;
	
	private String ErrorCode;				// 에러코드
	private String ErrorMessage;			// 에러메세지
	
	private NTSTaxPaymentResult Result;

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

	public NTSTaxPaymentResult getResult() {
		return Result;
	}

	public void setResult(NTSTaxPaymentResult result) {
		Result = result;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
