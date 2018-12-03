package com.koscom.batch.push.domain;

import java.io.Serializable;

public class PushEachVO extends PushEachInfo implements Serializable {

	private static final long serialVersionUID = -6376747577583515390L;

	private String sendTo;
	private String send_to;
	private String rnum;

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getSend_to() {
		return send_to;
	}

	public void setSend_to(String send_to) {
		this.send_to = send_to;
	}
}
