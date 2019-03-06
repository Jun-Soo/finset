package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LoginSocialInfo implements Serializable {
	private static final long serialVersionUID = 2916112961238021271L;
	protected String no_person;
	protected String comp_social;
	protected String id_social;
	protected String nm_social;
	protected String token_social;
	protected String dt_agree_social;
	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;
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
	public String getComp_social() {
		return comp_social;
	}
	public void setComp_social(String comp_social) {
		this.comp_social = comp_social;
	}
	public String getId_social() {
		return id_social;
	}
	public void setId_social(String id_social) {
		this.id_social = id_social;
	}
	public String getNm_social() {
		return nm_social;
	}
	public void setNm_social(String nm_social) {
		this.nm_social = nm_social;
	}
	public String getToken_social() {
		return token_social;
	}
	public void setToken_social(String token_social) {
		this.token_social = token_social;
	}
	public String getDt_agree_social() {
		return dt_agree_social;
	}
	public void setDt_agree_social(String dt_agree_social) {
		this.dt_agree_social = dt_agree_social;
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
