package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 납부확인서 vo
 * @author HSJ
 *
 */
public class NHISPayment implements Serializable {
	
	private static final long serialVersionUID = -312443936830477475L;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지
	
	private NHISPaymentResult Result;

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

	public NHISPaymentResult getResult() {
		return Result;
	}

	public void setResult(NHISPaymentResult result) {
		Result = result;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
