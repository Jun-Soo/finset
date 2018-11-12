package com.koscom.scrap.model;

import java.io.Serializable;

public class LinkedFcInfoVO implements Serializable{
	private static final long serialVersionUID = -4159327904064889977L;
	private String no_person = "";		//회원고유번호
	private String cn = "";				//공인인증서일련번호
	private String dn = "";				//공인인증서일련번호2
	private String cd_agency = "";		//기관구분
	private String cd_fc = "";			//금융기관코드
	private String nm_fc = "";			//금융기관이름
	private String cd_fin = "";			//금융기관구분코드
	private String nm_code = "";		//금융기관구분이름
	private String cd_coocon = "";		//쿠콘 금융사 코드
	private String cd_link_stat = "";	//연동상태
	private String rsn_link_message = "";	//연동결과메세지
	private String yn_link = "";		//연동여부
	private String type_login = "";		//로그인방식
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getCd_agency() {
		return cd_agency;
	}
	public void setCd_agency(String cd_agency) {
		this.cd_agency = cd_agency;
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
	public String getCd_fin() {
		return cd_fin;
	}
	public void setCd_fin(String cd_fin) {
		this.cd_fin = cd_fin;
	}
	public String getNm_code() {
		return nm_code;
	}
	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}
	public String getCd_coocon() {
		return cd_coocon;
	}
	public void setCd_coocon(String cd_coocon) {
		this.cd_coocon = cd_coocon;
	}
	public String getCd_link_stat() {
		return cd_link_stat;
	}
	public void setCd_link_stat(String cd_link_stat) {
		this.cd_link_stat = cd_link_stat;
	}
	public String getRsn_link_message() {
		return rsn_link_message;
	}
	public void setRsn_link_message(String rsn_link_message) {
		this.rsn_link_message = rsn_link_message;
	}
	public String getYn_link() {
		return yn_link;
	}
	public void setYn_link(String yn_link) {
		this.yn_link = yn_link;
	}
	public String getType_login() {
		return type_login;
	}
	public void setType_login(String type_login) {
		this.type_login = type_login;
	}
}