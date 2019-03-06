package com.koscom.loginsocial.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class LoginSocialForm extends SearchForm implements Serializable {
	
	private static final long serialVersionUID = 6738160863942328093L;
	
	private String cd_login_result;		// 로그인 결과
	private String cd_auth;				// 권한구분
	private String cd_auth_proc;		// 처리구분
	private String cd_system;			// 시스템구분
	private String cd_template_group;	// 탬플릿 그룹 코드
	private String no_person;			//고객번호
	
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

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	
}
