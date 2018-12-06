package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonActiveHistInfo implements Serializable{
	private static final long serialVersionUID = 5019051940087480296L;
	protected String no_person;
	protected String nm_dir; //
	protected String nm_page; //
	protected String id_frt; //
	protected String dt_frt; //
	protected String id_lst; //
	protected String dt_lst; //
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
	public String getNm_dir() {
		return nm_dir;
	}
	public void setNm_dir(String nm_dir) {
		this.nm_dir = nm_dir;
	}
	public String getNm_page() {
		return nm_page;
	}
	public void setNm_page(String nm_page) {
		this.nm_page = nm_page;
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
