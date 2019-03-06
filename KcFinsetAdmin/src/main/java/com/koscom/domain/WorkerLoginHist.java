package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class WorkerLoginHist implements Serializable{

	private static final long serialVersionUID = 4740413941491946560L;
	
	private String cd_system;			// 시스템구분
	private String id_emp;				// 사용자ID
	private String cd_result;			// 결과
	private String authority;			// 
	private String ip_server;			// WAS IP
	private String ip_client;			// client IP
	private String id_frt;				// 최초등록자
	private String dt_frt;				// 최초등록일
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_system() {
		return cd_system;
	}

	public void setCd_system(String cd_system) {
		this.cd_system = cd_system;
	}

	public String getId_emp() {
		return id_emp;
	}

	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}

	public String getCd_result() {
		return cd_result;
	}

	public void setCd_result(String cd_result) {
		this.cd_result = cd_result;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getIp_server() {
		return ip_server;
	}

	public void setIp_server(String ip_server) {
		this.ip_server = ip_server;
	}

	public String getIp_client() {
		return ip_client;
	}

	public void setIp_client(String ip_client) {
		this.ip_client = ip_client;
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
	
}
