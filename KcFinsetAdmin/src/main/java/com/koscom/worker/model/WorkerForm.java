package com.koscom.worker.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class WorkerForm extends SearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092252522679998493L;

	private String cd_login_result;		// 로그인 결과
	private String cd_auth;				// 권한구분
	private String cd_auth_proc;		// 처리구분
	private String cd_system;			// 시스템구분
	private String cd_template_group;	// 탬플릿 그룹 코드
	
	private String cd_fc;
	private String nm_fc;
	private String txt_detail;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_login_result() {
		return cd_login_result;
	}

	public void setCd_login_result(String cd_login_result) {
		this.cd_login_result = cd_login_result;
	}

	public String getCd_auth() {
		return cd_auth;
	}

	public void setCd_auth(String cd_auth) {
		this.cd_auth = cd_auth;
	}

	public String getCd_auth_proc() {
		return cd_auth_proc;
	}

	public void setCd_auth_proc(String cd_auth_proc) {
		this.cd_auth_proc = cd_auth_proc;
	}

	public String getCd_system() {
		return cd_system;
	}

	public void setCd_system(String cd_system) {
		this.cd_system = cd_system;
	}

	public String getCd_template_group() {
		return cd_template_group;
	}

	public void setCd_template_group(String cd_template_group) {
		this.cd_template_group = cd_template_group;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getNm_fc() {
		return nm_fc;
	}

	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}

	public String getTxt_detail() {
		return txt_detail;
	}

	public void setTxt_detail(String txt_detail) {
		this.txt_detail = txt_detail;
	}
	
}
