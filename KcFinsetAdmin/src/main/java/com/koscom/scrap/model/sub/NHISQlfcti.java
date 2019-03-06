package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 자격득실확인서 vo
 * @author HSJ
 *
 */
public class NHISQlfcti implements Serializable {
	
	private static final long serialVersionUID = 7158498401763835690L;
	
	private String ErrorCode;		// 에러코드
	private String ErrorMessage;	// 에러메세지
	
	private NHISQlfctiResult Result;

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

	public NHISQlfctiResult getResult() {
		return Result;
	}

	public void setResult(NHISQlfctiResult result) {
		Result = result;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
