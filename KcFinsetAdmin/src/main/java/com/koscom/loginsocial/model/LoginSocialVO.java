package com.koscom.loginsocial.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.LoginSocialInfo;

public class LoginSocialVO extends LoginSocialInfo implements Serializable {
	private static final long serialVersionUID = -3693611059796796917L;
	private String formerLocation;
	private String yn_use;
	public String getFormerLocation() {
		return formerLocation;
	}
	public void setFormerLocation(String formerLocation) {
		this.formerLocation = formerLocation;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}