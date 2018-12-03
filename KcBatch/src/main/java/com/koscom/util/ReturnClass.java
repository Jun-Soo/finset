package com.koscom.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 파일명 : ReturnClass.java
 * 기능설명 : 공통적으로 사용되는 리턴 클래스
 * =========================================================================
 *   작성일자        작성자           기                    능
 * ------------   ---------    ---------------------------------------------
 * 2007. 1. 10      CYK         최초작성
 * =========================================================================
 * Copyright (c) 2007 Crizen Solution, All rights reserved.
 */
public class ReturnClass implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2984861801589895345L;
	
	private String cd_result;   // 작업결과코드 00.실패, 01.성공
	private String message;  	// 메시지코드
	private String des_message; // 메지지 설명
	private Object returnObj;   // 리턴객체(모델, 리스트 등 리턴할 객체를 닮기 위한 오브젝트)
	
	public ReturnClass() { }
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	/**
	 * @param cd_result 작업결과코드
	 */
	public ReturnClass(String cd_result) {
		this.cd_result = cd_result;
	}
	
	/**
	 * @param cd_result 작업결과코드
	 * @param cd_message 메시지코드
	 */
	public ReturnClass(String cd_result, String message) {
		this(cd_result);
		this.message = message;
		
		if ( !StringUtil.isEmptyTrimmed(message) ) {
			this.des_message = message;
		}
	}
	
	/**
	 * @param cd_result 작업결과코드
	 * @param cd_message 메시지코드
	 * @param returnObj 리턴객체
	 */
	public ReturnClass(String cd_result, String cd_message, Object returnObj) {
		this(cd_result, cd_message);
		this.returnObj = returnObj;
	}
	
	/**
	 * @param cd_result		:작업결과코드
	 * @param cd_message	:메세지코드
	 * @param des_message	:메세지설명
	 */
	public ReturnClass(String cd_result, String cd_message, String des_message){
		this(cd_result, cd_message);
		this.des_message = StringUtil.nullToEmpty( des_message );
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCd_result() {
		return cd_result;
	}

	public void setCd_result(String cd_result) {
		this.cd_result = cd_result;
	}

	public String getDes_message() {
		return des_message;
	}

	public void setDes_message(String des_message) {
		this.des_message = des_message;
	}

	public Object getReturnObj() {
		return returnObj;
	}

	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	
	public boolean isSuccess(){
		if(cd_result == null) return false;
		
		return Constant.SUCCESS.equals(cd_result);
	}
}
