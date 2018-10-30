package com.koscom.person.model;

import java.io.Serializable;

public class PersonAgreeHistVO implements Serializable{
	private static final long serialVersionUID = 6836951564805120769L;
	private String no_person;
	private String dt_agree;
	private String cd_agree;
	private String terms_content;
	private String terms_version;
	private String terms_date;
	private String terms_start_date;
	private String terms_end_date;
	private String yn_disuse;
	private String id_frt;
	private String dt_frt;
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getDt_agree() {
		return dt_agree;
	}
	public void setDt_agree(String dt_agree) {
		this.dt_agree = dt_agree;
	}
	public String getCd_agree() {
		return cd_agree;
	}
	public void setCd_agree(String cd_agree) {
		this.cd_agree = cd_agree;
	}
	public String getTerms_content() {
		return terms_content;
	}
	public void setTerms_content(String terms_content) {
		this.terms_content = terms_content;
	}
	public String getTerms_version() {
		return terms_version;
	}
	public void setTerms_version(String terms_version) {
		this.terms_version = terms_version;
	}
	public String getTerms_date() {
		return terms_date;
	}
	public void setTerms_date(String terms_date) {
		this.terms_date = terms_date;
	}
	public String getTerms_start_date() {
		return terms_start_date;
	}
	public void setTerms_start_date(String terms_start_date) {
		this.terms_start_date = terms_start_date;
	}
	public String getTerms_end_date() {
		return terms_end_date;
	}
	public void setTerms_end_date(String terms_end_date) {
		this.terms_end_date = terms_end_date;
	}
	public String getYn_disuse() {
		return yn_disuse;
	}
	public void setYn_disuse(String yn_disuse) {
		this.yn_disuse = yn_disuse;
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