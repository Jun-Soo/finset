package com.koscom.pusheach.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class PushEachForm extends SearchForm implements Serializable {

	private static final long serialVersionUID = -1496889499684741720L;

	private String id_board = "push"; // 게시판id
	private String no_person;
	private String push_divcd;

	public String getId_board() {
		return id_board;
	}

	public void setId_board(String id_board) {
		this.id_board = id_board;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getPush_divcd() {
		return push_divcd;
	}

	public void setPush_divcd(String push_divcd) {
		this.push_divcd = push_divcd;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
