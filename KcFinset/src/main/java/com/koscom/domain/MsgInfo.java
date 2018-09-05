package com.koscom.domain;

import java.io.Serializable;

public class MsgInfo implements Serializable {
	
	private static final long serialVersionUID = -3351413166395267894L;
	
	protected String msg1;
	protected String msg2;
	protected String msg3;
	
	public String getMsg1() {
		return msg1;
	}
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public String getMsg3() {
		return msg3;
	}
	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}
	
}
