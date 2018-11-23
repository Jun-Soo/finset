package com.koscom.counsel.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class CounselForm extends SearchForm implements Serializable{

	private static final long serialVersionUID = 4702028913926086586L;
	
	private String no_person;	// 개인번호
	private String counsel_seq;    		/*상담순번*/
	private String dt_apply;    		/*상담신청일시*/
	private String id_frt;		// 작성자
	
	private String no_agency_person; //대리점 개인번호 
	private String no_prepare;	// 사전접수번호
	private String no_apply;	// 접수번호
	private String id_agency;	// 매체사
	
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
	public String getCounsel_seq() {
		return counsel_seq;
	}
	public void setCounsel_seq(String counsel_seq) {
		this.counsel_seq = counsel_seq;
	}

	public String getDt_apply() {
		return dt_apply;
	}

	public void setDt_apply(String dt_apply) {
		this.dt_apply = dt_apply;
	}
	
	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getNo_agency_person() {
		return no_agency_person;
	}

	public void setNo_agency_person(String no_agency_person) {
		this.no_agency_person = no_agency_person;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}
	
}
