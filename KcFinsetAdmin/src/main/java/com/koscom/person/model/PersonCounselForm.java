package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class PersonCounselForm extends SearchForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9105262037486968135L;

	private String no_person; //회원관리번호

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
}