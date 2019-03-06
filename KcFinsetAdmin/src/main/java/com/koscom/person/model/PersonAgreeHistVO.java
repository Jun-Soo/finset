package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonAgreeHistInfo;

public class PersonAgreeHistVO extends PersonAgreeHistInfo implements Serializable{
	private static final long serialVersionUID = 6836951564805120769L;
	private String no_person;
	private String yn_provide;
	private String yn_collect;
	private String yn_search;
	private String yn_option;
	private String dt_agree;
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getYn_provide() {
		return yn_provide;
	}
	public void setYn_provide(String yn_provide) {
		this.yn_provide = yn_provide;
	}
	public String getYn_collect() {
		return yn_collect;
	}
	public void setYn_collect(String yn_collect) {
		this.yn_collect = yn_collect;
	}
	public String getYn_search() {
		return yn_search;
	}
	public void setYn_search(String yn_search) {
		this.yn_search = yn_search;
	}
	public String getYn_option() {
		return yn_option;
	}
	public void setYn_option(String yn_option) {
		this.yn_option = yn_option;
	}
	public String getDt_agree() {
		return dt_agree;
	}
	public void setDt_agree(String dt_agree) {
		this.dt_agree = dt_agree;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}