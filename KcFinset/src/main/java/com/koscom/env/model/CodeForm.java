package com.koscom.env.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class CodeForm extends SearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5717238593626630875L;

	private String code_group;
	private String code_value;
	private String yn_code_group;
	
	public String getCode_group() {
		return code_group;
	}
	
	public void setCode_group(String code_group) {
		this.code_group = code_group;
	}
	
	public String getCode_value() {
		return code_value;
	}
	
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	
	public String getYn_code_group() {
		return yn_code_group;
	}
	
	public void setYn_code_group(String yn_code_group) {
		this.yn_code_group = yn_code_group;
	}
	
}
