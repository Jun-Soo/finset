package com.koscom.finance.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class FinanceMsgVO extends SearchForm implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7729844793413719001L;
	/**
	 * DB INSERT, UPDATE, DELETE, SELECT RESULTList 
	 */
	
	String seq;
	String seq_board;
	String content_comment;
	String id_frt;
	public String getSeq() {
		return seq;
	}



	public void setSeq(String seq) {
		this.seq = seq;
	}



	public String getSeq_board() {
		return seq_board;
	}



	public void setSeq_board(String seq_board) {
		this.seq_board = seq_board;
	}



	public String getContent_comment() {
		return content_comment;
	}



	public void setContent_comment(String content_comment) {
		this.content_comment = content_comment;
	}



	public String getId_frt() {
		return id_frt;
	}



	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}



	public String getDt_frt() {
		return dt_frt;
	}



	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}



	public String getId_lst() {
		return id_lst;
	}



	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}



	public String getDt_lst() {
		return dt_lst;
	}



	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}



	String dt_frt;
	String id_lst;
	String dt_lst;
	


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}