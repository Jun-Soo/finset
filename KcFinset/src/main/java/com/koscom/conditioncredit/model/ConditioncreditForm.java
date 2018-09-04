package com.koscom.conditioncredit.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class ConditioncreditForm extends SearchForm implements Serializable{
	private static final long serialVersionUID = 91602495545980624L;
	private String no_person;
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
