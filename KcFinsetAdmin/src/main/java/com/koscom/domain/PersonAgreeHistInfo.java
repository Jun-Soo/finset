package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonAgreeHistInfo implements Serializable {
	private static final long serialVersionUID = 8687749132878349297L;
	protected String no_person;
	protected String yn_provide;
	protected String yn_collect;
	protected String yn_search;
	protected String yn_option;
	protected String dt_agree;
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
}