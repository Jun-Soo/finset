package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.domain.PersonInfoHist;

public class PersonInfoHistVO extends PersonInfoHist implements Serializable{

	private static final long serialVersionUID = 2471119223138588637L;
	
	/**
	 * 01 : 이름
	 * 02 : 생년월일
	 * 03 : 휴대폰 
	 * 04 : 자택 우편번호
	 * 05 : 자택 상세주소
	 */
	public static final String CD_INFO_01 = "01";
	public static final String CD_INFO_02 = "02";
	public static final String CD_INFO_03 = "03";
	public static final String CD_INFO_04 = "04";
	public static final String CD_INFO_05 = "05";
	
	private String nm_person;

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}
	
}
