package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 자격확인서 vo
 * @author HSJ
 *
 */
public class NHISQlfctngainlos implements Serializable {

	private static final long serialVersionUID = -2462315716129318527L;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지
	
	private NHISQlfctngainlosResult Result;

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

	public NHISQlfctngainlosResult getResult() {
		return Result;
	}

	public void setResult(NHISQlfctngainlosResult result) {
		Result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
