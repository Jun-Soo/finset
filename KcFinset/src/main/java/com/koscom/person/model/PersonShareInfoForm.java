package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class PersonShareInfoForm extends SearchForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1076342545064591404L;

	private String no_person; //회원번호
	private String type_list; //list type(요청 - req, 허용 - offer)
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getType_list() {
		return type_list;
	}
	public void setType_list(String type_list) {
		this.type_list = type_list;
	}

}
