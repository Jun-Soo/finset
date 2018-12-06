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
	private String nm_code;
	private String yn_code_group;
	
	private String id_frt;
	
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
	
	public String getNm_code() {
		return nm_code;
	}

	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}

	public String getYn_code_group() {
		return yn_code_group;
	}
	
	public void setYn_code_group(String yn_code_group) {
		this.yn_code_group = yn_code_group;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	
}
