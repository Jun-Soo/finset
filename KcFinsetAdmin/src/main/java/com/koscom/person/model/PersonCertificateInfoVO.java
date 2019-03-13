package com.koscom.person.model;

import java.io.Serializable;

public class PersonCertificateInfoVO implements Serializable{
	private static final long serialVersionUID = -1616650805306728360L;
	private String no_person;
	private String seq;
	private String cn;
	private String certificate_agency;
	private String dt_expire;
	private String yn_use;
	private String id_frt;
	private String dt_frt;
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getCertificate_agency() {
		return certificate_agency;
	}
	public void setCertificate_agency(String certificate_agency) {
		this.certificate_agency = certificate_agency;
	}
	public String getDt_expire() {
		return dt_expire;
	}
	public void setDt_expire(String dt_expire) {
		this.dt_expire = dt_expire;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
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