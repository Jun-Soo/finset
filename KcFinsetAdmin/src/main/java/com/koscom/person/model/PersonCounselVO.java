package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.domain.PersonCounselInfo;

public class PersonCounselVO extends PersonCounselInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1377015192790240033L;

	private String no_person; //회원관리번호
	private String counsel_seq; //상담순번

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCounsel_seq() {
		return counsel_seq;
	}
	public void setCounsel_seq(String counsel_seq) {
		this.counsel_seq = counsel_seq;
	}

}
