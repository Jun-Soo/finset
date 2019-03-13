package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.domain.PersonLoginHist;

public class PersonLoginHistVO extends PersonLoginHist implements Serializable{

	private static final long serialVersionUID = 5584017416514302830L;
	
	private String nm_person;

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}
	
	

}
