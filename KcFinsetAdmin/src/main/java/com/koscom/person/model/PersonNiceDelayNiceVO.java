package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceDelayNiceInfo;

public class PersonNiceDelayNiceVO extends PersonNiceDelayNiceInfo implements Serializable {
	private static final long serialVersionUID = 2164861223664420514L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}