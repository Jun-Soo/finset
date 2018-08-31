package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonLoginHistInfo implements Serializable {
			
	private static final long serialVersionUID = 2663099370063186738L;
	
	protected String no_person;			//사용자 고유번호
	protected String cd_system;			// 시스템구분
	protected String ip_client;			// client IP
	private String id_frt = "";	//최초입력아이디
	private String dt_frt = "";	//최종입력날짜
	private String id_lst = "";	//최종입력아이디
	private String dt_lst = "";	//최종입력날짜
	protected String user_agent;		
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_system() {
		return cd_system;
	}
	public void setCd_system(String cd_system) {
		this.cd_system = cd_system;
	}
	public String getIp_client() {
		return ip_client;
	}
	public void setIp_client(String ip_client) {
		this.ip_client = ip_client;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
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