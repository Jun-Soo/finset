package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CounselInfo implements Serializable{

	private static final long serialVersionUID = -7427850512177889250L;
	
	protected String no_person;			// 개인번호
	protected String no_prepare;		// 사전접수번호
	protected String no_apply;			// 접수번호
	protected String seq_counsel;		// 순서
	protected String cd_counsel_class;	// 상담구분
	protected String yn_meet;			// 비대면여부
	protected String cd_contact;		// 컨택(바운딩)구분 : in-bound, out-bound
	protected String cd_counsel_method;	// 접촉방법
	protected String etc_counsel;		// 상담내용
	
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

	public String getSeq_counsel() {
		return seq_counsel;
	}

	public void setSeq_counsel(String seq_counsel) {
		this.seq_counsel = seq_counsel;
	}

	public String getCd_counsel_class() {
		return cd_counsel_class;
	}

	public void setCd_counsel_class(String cd_counsel_class) {
		this.cd_counsel_class = cd_counsel_class;
	}

	public String getYn_meet() {
		return yn_meet;
	}

	public void setYn_meet(String yn_meet) {
		this.yn_meet = yn_meet;
	}

	public String getCd_contact() {
		return cd_contact;
	}

	public void setCd_contact(String cd_contact) {
		this.cd_contact = cd_contact;
	}

	public String getCd_counsel_method() {
		return cd_counsel_method;
	}

	public void setCd_counsel_method(String cd_counsel_method) {
		this.cd_counsel_method = cd_counsel_method;
	}

	public String getEtc_counsel() {
		return etc_counsel;
	}

	public void setEtc_counsel(String etc_counsel) {
		this.etc_counsel = etc_counsel;
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
