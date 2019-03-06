package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
public class BoardComment implements Serializable{
	
	private static final long serialVersionUID = -8938140431955980853L;
	
	protected String seq;    // 코멘트번호
	protected String seq_board;    // 원글번호
	protected String content_comment;    // 코멘트 내용
	protected String id_frt;    // 등록 ID
	protected String dt_frt;    // 등록일자
	protected String id_lst;    // 수정 ID
	protected String dt_lst;    // 수정일자
	protected String nm_person;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

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
	
	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}
	
}
