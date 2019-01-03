package com.koscom.main.model;

import java.io.Serializable;

public class MainForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6112241797171360391L;

	private String no_person; //회원번호
	private String cd_agency; //기관구분

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_agency() {
		return cd_agency;
	}
	public void setCd_agency(String cd_agency) {
		this.cd_agency = cd_agency;
	}


}