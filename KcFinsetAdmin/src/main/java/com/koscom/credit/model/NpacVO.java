package com.koscom.credit.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.NpacHist;

public class NpacVO extends NpacHist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5510028795729532190L;
	
	private String birth_yyyy;
	private String birth_mm;
	private String birth_dd;
	private String c1_gender;
	private String cd_goods;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getBirth_yyyy() {
		return birth_yyyy;
	}

	public void setBirth_yyyy(String birth_yyyy) {
		this.birth_yyyy = birth_yyyy;
	}

	public String getBirth_mm() {
		return birth_mm;
	}

	public void setBirth_mm(String birth_mm) {
		this.birth_mm = birth_mm;
	}

	public String getBirth_dd() {
		return birth_dd;
	}

	public void setBirth_dd(String birth_dd) {
		this.birth_dd = birth_dd;
	}

	public String getC1_gender() {
		return c1_gender;
	}

	public void setC1_gender(String c1_gender) {
		this.c1_gender = c1_gender;
	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

}
