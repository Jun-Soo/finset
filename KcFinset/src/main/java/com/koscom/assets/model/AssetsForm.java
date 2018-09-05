package com.koscom.assets.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class AssetsForm extends SearchForm implements Serializable{

	private static final long serialVersionUID = -7048381305403047820L;

	private String no_person;
	private String yyyymm_trd;
	private String type_list;

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getYyyymm_trd() {
		return yyyymm_trd;
	}
	public void setYyyymm_trd(String yyyymm_trd) {
		this.yyyymm_trd = yyyymm_trd;
	}
	public String getType_list() {
		return type_list;
	}
	public void setType_list(String type_list) {
		this.type_list = type_list;
	}


}
