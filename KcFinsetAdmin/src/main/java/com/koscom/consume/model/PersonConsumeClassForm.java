/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180731
 * 회원별 소비 분류 코드 관리 조회용 데이터
 */
package com.koscom.consume.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonConsumeClassForm implements Serializable{

	private static final long serialVersionUID = 8110211888780173840L;
	
	//분류, 항목 삭제 처리용
	private String 	no_person;							// 회원관리번호
	private String 	cd_consume_class;					// 소비항목코드
	
	//항목 조회용
	private String	cd_class;							// 분류코드
	
	public PersonConsumeClassForm() {
	}
	public PersonConsumeClassForm(String no_person, String cd_consume_class, String cd_class) {
		this.no_person = no_person;
		this.cd_consume_class = cd_consume_class;
		this.cd_class = cd_class;
	}

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_consume_class() {
		return cd_consume_class;
	}
	public void setCd_consume_class(String cd_consume_class) {
		this.cd_consume_class = cd_consume_class;
	}
	public String getCd_class() {
		return cd_class;
	}
	public void setCd_class(String cd_class) {
		this.cd_class = cd_class;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}