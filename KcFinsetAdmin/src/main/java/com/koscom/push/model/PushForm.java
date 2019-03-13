package com.koscom.push.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class PushForm extends SearchForm implements Serializable {

	private static final long serialVersionUID = -6200894360489216491L;

	private String id_board = "push"; // 게시판id
	private long new_seq_push;	

	public String getId_board() {
		return id_board;
	}

	public void setId_board(String id_board) {
		this.id_board = id_board;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public long getNew_seq_push() {
		return new_seq_push;
	}

	public void setNew_seq_push(long new_seq_push) {
		this.new_seq_push = new_seq_push;
	}

}
