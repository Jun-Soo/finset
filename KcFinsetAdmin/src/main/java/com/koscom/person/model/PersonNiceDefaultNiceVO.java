package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceDefaultNiceInfo;

public class PersonNiceDefaultNiceVO extends PersonNiceDefaultNiceInfo implements Serializable {
	private static final long serialVersionUID = 7019666917264737769L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}