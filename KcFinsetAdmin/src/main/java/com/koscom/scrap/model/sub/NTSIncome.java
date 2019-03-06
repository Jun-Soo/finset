package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 국세청 VO sub
 * @author HSJ
 *
 */
public class NTSIncome implements Serializable {

	private static final long serialVersionUID = -669262442466971597L;
	
	private String ErrorCode;			// 에러코드
	private String ErrorMessage;		// 에러메시지
	
	private NTSIncomeResult Result;		// Output

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
	
	public NTSIncomeResult getResult() {
		return Result;
	}

	public void setResult(NTSIncomeResult result) {
		Result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	

}
