package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbJobInfo implements Serializable{
	
	private static final long serialVersionUID = -6112708731591126476L;

	private String no_person;
	private String nm_comp;
	private String ym_start_comp;
	private String nm_dept;
	private String tel_dept;
	
	public KcbJobInfo() {
	}

	public KcbJobInfo(String no_person, String nm_comp, String ym_start_comp,
			String nm_dept, String tel_dept) {
		this.no_person = no_person;
		this.nm_comp = nm_comp;
		this.ym_start_comp = ym_start_comp;
		this.nm_dept = nm_dept;
		this.tel_dept = tel_dept;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getNm_comp() {
		return nm_comp;
	}

	public void setNm_comp(String nm_comp) {
		this.nm_comp = nm_comp;
	}

	public String getYm_start_comp() {
		return ym_start_comp;
	}

	public void setYm_start_comp(String ym_start_comp) {
		this.ym_start_comp = ym_start_comp;
	}

	public String getNm_dept() {
		return nm_dept;
	}

	public void setNm_dept(String nm_dept) {
		this.nm_dept = nm_dept;
	}

	public String getTel_dept() {
		return tel_dept;
	}

	public void setTel_dept(String tel_dept) {
		this.tel_dept = tel_dept;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
