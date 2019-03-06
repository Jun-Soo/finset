package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 표준재무제표증명 VO
 * @author czsjhong-n1
 *
 */
public class NTSFinance implements Serializable {

	private static final long serialVersionUID = -965099540108444915L;
	
	private String ErrorCode;				// 에러코드
	private String ErrorMessage;			// 에러메세지
	
	private NTSFinanceResult Result;

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

	public NTSFinanceResult getResult() {
		return Result;
	}

	public void setResult(NTSFinanceResult result) {
		Result = result;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
