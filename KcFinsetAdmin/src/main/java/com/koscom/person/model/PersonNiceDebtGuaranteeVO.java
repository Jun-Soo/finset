package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceDebtGuaranteeInfo;

public class PersonNiceDebtGuaranteeVO extends PersonNiceDebtGuaranteeInfo implements Serializable {
	private static final long serialVersionUID = 2291980116048053889L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}