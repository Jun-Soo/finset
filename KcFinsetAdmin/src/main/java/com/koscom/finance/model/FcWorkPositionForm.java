package com.koscom.finance.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class FcWorkPositionForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = 5437732438647127824L;

	private String cd_fc; //금융사코드
	private String cd_job;//직업코드

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getCd_job() {
		return cd_job;
	}

	public void setCd_job(String cd_job) {
		this.cd_job = cd_job;
	}
	
	
}