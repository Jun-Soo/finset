package com.koscom.conditionbiz.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.ConditionbizInfo;

public class ConditionbizVO extends ConditionbizInfo implements Serializable{
	private static final long serialVersionUID = 3829698515913007427L;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}