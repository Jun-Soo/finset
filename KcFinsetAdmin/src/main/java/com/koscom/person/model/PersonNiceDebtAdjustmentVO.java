package com.koscom.person.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonNiceDebtAdjustmentInfo;

public class PersonNiceDebtAdjustmentVO extends PersonNiceDebtAdjustmentInfo implements Serializable {
	private static final long serialVersionUID = -6453385791670827227L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}