package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceCashServiceInfo;

public class PersonNiceCashServiceVO extends PersonNiceCashServiceInfo implements Serializable {
	private static final long serialVersionUID = 7592767119343840030L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}