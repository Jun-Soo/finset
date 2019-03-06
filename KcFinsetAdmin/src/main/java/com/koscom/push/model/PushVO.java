package com.koscom.push.model;

import java.io.Serializable;

import com.koscom.domain.PushInfo;

public class PushVO extends PushInfo implements Serializable {

	private static final long serialVersionUID = 8225189203706876786L;
	
	private String sendTo;

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	
	

	
}
