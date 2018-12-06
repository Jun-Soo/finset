package com.koscom.apply.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class ApplyResultForm extends SearchForm {

	private static final long serialVersionUID = 8478046251378121121L;
	
	private String cd_goods;
	private String nm_goods;
	private String cd_fc;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public String getNm_goods() {
		return nm_goods;
	}

	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	
	
}
