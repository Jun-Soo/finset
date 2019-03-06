package com.koscom.scrapData.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class AssetsDataForm extends SearchForm implements Serializable{

	private static final long serialVersionUID = -7048381305403047820L;

	private String no_person;

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

}
