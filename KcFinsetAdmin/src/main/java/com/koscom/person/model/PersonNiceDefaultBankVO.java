package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceDefaultBankInfo;

public class PersonNiceDefaultBankVO extends PersonNiceDefaultBankInfo implements Serializable {
	private static final long serialVersionUID = -7460398952220713894L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}