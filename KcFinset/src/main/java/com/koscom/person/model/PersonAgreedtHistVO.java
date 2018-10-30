package com.koscom.person.model;

import java.io.Serializable;

public class PersonAgreedtHistVO implements Serializable{
	private static final long serialVersionUID = 2710937160608251291L;
	private String no_person;
	private String dt_agree;
	private String cd_agree;
	private String cd_fc;
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
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
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