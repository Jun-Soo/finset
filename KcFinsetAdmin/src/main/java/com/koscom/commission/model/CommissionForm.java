package com.koscom.commission.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class CommissionForm extends SearchForm implements Serializable {

	private static final long serialVersionUID = 4694112476376527631L;
	//	private static final long serialVersionUID = -7693376532078788852L;
	private String cd_fc;
	private String cd_goods;
	private String nm_fc;
	private String no_apply;
	private String yn_exe;
	
	
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

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public String getNm_fc() {
		return nm_fc;
	}

	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}
	public String getYn_exe() {
		return yn_exe;
	}

	public void setYn_exe(String yn_exe) {
		this.yn_exe = yn_exe;
	}

}