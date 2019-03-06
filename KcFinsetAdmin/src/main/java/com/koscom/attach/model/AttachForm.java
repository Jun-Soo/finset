package com.koscom.attach.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class AttachForm extends SearchForm implements Serializable{

	private static final long serialVersionUID = -7048381305403047820L;
	private String no_person;
	private String nm_if;				//신용조회
	private String view_type;				
	private String cd_cfs_goods;				
	
	public String getCd_cfs_goods() {
		return cd_cfs_goods;
	}
	public void setCd_cfs_goods(String cd_cfs_goods) {
		this.cd_cfs_goods = cd_cfs_goods;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNm_if() {
		return nm_if;
	}
	public void setNm_if(String nm_if) {
		this.nm_if = nm_if;
	}
	public String getView_type() {
		return view_type;
	}
	public void setView_type(String view_type) {
		this.view_type = view_type;
	}
}
