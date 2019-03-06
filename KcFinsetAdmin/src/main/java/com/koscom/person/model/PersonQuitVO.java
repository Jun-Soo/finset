package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonQuitInfo;

public class PersonQuitVO extends PersonQuitInfo implements Serializable{
	private static final long serialVersionUID = -6676747844222444442L;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}