package com.koscom.finance.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.util.NumberUtil;

public class FcEdocVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8030267172057625941L;
	
	private List<FcEdocForm> fcEdocForm;
	private int msgSize;
	private int msgCount;
	
	public List<FcEdocForm> getFcEdocForm() {
		return fcEdocForm;
	}

	public void setFcEdocForm(List<FcEdocForm> fcEdocForm) {
		this.fcEdocForm = fcEdocForm;
	}
	
	public int getMsgSize(){
		return msgSize;
	}
	
	
	public void setMsgSize(List<FcEdocForm> fcEdocForm){
		int sum = 0;
		
		for(FcEdocForm list : fcEdocForm){
			sum += NumberUtil.parseInt( list.getField_len() );
		}
		this.msgSize = sum;
	}
	
	public int getMsgCount(){
		return msgCount;
	}
	
	public void setMsgCount(int msgCount){
		this.msgCount = msgCount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}