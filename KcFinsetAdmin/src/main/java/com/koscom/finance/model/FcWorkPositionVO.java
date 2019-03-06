package com.koscom.finance.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.WorkPositionInfo;

public class FcWorkPositionVO extends WorkPositionInfo implements Serializable {
	private static final long serialVersionUID = -4067852151088798825L;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}