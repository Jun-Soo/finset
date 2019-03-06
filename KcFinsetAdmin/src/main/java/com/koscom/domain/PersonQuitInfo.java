package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonQuitInfo implements Serializable {

	private static final long serialVersionUID = -693450596366654062L;
	
	private String no_person;
	private String dt_quit;
	private String nm_person;
	private String bgn;
	private String hp;
		
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

	public String getDt_quit() {
		return dt_quit;
	}

	public void setDt_quit(String dt_quit) {
		this.dt_quit = dt_quit;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getBgn() {
		return bgn;
	}

	public void setBgn(String bgn) {
		this.bgn = bgn;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	
}
