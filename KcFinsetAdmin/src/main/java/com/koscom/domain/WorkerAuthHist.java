package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class WorkerAuthHist implements Serializable{
	private static final long serialVersionUID = -2917712759736828202L;
	private String id_emp;				// 사용자
	private String cd_auth;				// 권한구분(프로그램,결재권한)
	private String id_auth;				// 권한ID
	private String cd_proc;				// 처리구분(등록,제거)
	private String id_frt;				// 최초등록자
	private String dt_frt;				// 최초등록일
	private String cd_detail;			// 권한상세
	private String id_lst = "";	//최종입력아이디
	private String dt_lst = "";	//최종입력날짜
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getId_emp() {
		return id_emp;
	}

	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}

	public String getCd_auth() {
		return cd_auth;
	}

	public void setCd_auth(String cd_auth) {
		this.cd_auth = cd_auth;
	}

	public String getId_auth() {
		return id_auth;
	}

	public void setId_auth(String id_auth) {
		this.id_auth = id_auth;
	}

	public String getCd_proc() {
		return cd_proc;
	}

	public void setCd_proc(String cd_proc) {
		this.cd_proc = cd_proc;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getDt_frt() {
		return dt_frt;
	}

	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	public String getCd_detail() {
		return cd_detail;
	}

	public void setCd_detail(String cd_detail) {
		this.cd_detail = cd_detail;
	}

	public String getId_lst() {
		return id_lst;
	}

	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}

	public String getDt_lst() {
		return dt_lst;
	}

	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	
	
}
