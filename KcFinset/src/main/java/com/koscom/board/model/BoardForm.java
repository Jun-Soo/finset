package com.koscom.board.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BoardForm extends SearchForm implements Serializable{

	private static final long serialVersionUID = -8095439827830506874L;
	
	private String id_board;	//게시판id
	private String nm_board;	//게시판 이름
	private String id_agency;	//매체사 id
	private String seq;			//seq
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getId_board() {
		return id_board;
	}

	public void setId_board(String id_board) {
		this.id_board = id_board;
	}

	public String getNm_board() {
		return nm_board;
	}

	public void setNm_board(String nm_board) {
		this.nm_board = nm_board;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}
