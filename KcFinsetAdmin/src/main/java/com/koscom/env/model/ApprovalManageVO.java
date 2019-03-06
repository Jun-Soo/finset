package com.koscom.env.model;

import java.io.Serializable;

public class ApprovalManageVO extends ApprovalManage implements Serializable {
	private static final long serialVersionUID = 5079615053713699422L;
	private String id_emp;
	private String nm_emp;
	private String c3_branch;

	public String getId_emp() {
		return id_emp;
	}

	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}

	public String getNm_emp() {
		return nm_emp;
	}

	public void setNm_emp(String nm_emp) {
		this.nm_emp = nm_emp;
	}

	public String getC3_branch() {
		return c3_branch;
	}

	public void setC3_branch(String c3_branch) {
		this.c3_branch = c3_branch;
	}
	
}
