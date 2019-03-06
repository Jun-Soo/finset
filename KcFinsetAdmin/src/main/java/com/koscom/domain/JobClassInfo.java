package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class JobClassInfo implements Serializable{
	
	private static final long serialVersionUID = 910315450614607230L;
	
	private String job_cd;
	private String job_nm;
	private String index;
	private String search;
	private String code;
	private String apply_type;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getJob_cd() {
		return job_cd;
	}

	public void setJob_cd(String job_cd) {
		this.job_cd = job_cd;
	}

	public String getJob_nm() {
		return job_nm;
	}

	public void setJob_nm(String job_nm) {
		this.job_nm = job_nm;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	
}
