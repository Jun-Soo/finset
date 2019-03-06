package com.koscom.conditioncredit.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.ConditioncreditInfo;

public class ConditioncreditVO extends ConditioncreditInfo implements Serializable{
	private static final long serialVersionUID = 7252713325395481825L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}