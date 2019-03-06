package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonSmsList implements Serializable {

	private static final long serialVersionUID = 1941974153313079273L;
	
	protected String no_person;
	protected long seq;
	protected String msg_info;
	protected String dt_msg;
	protected String id_frt;
	protected String dt_frt;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getMsg_info() {
		return msg_info;
	}

	public void setMsg_info(String msg_info) {
		this.msg_info = msg_info;
	}

	public String getDt_msg() {
		return dt_msg;
	}

	public void setDt_msg(String dt_msg) {
		this.dt_msg = dt_msg;
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

}
