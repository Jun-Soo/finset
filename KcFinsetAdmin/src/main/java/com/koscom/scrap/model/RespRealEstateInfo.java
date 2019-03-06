package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespRealEstateInfo implements Serializable {

	private static final long serialVersionUID = 1422636656393998461L;

	public int SEQ_REALSTATE_REGISTER;
//	public List<String> REAL_ESTATE_OWNER;
//	public List<String> REAL_ESTATE_ADDRESS;
//	public List<String> REAL_ESTATE_STATE;
//	public List<String> REAL_ESTATE_NO;
	public String REAL_ESTATE_OWNER;
	public String REAL_ESTATE_ADDRESS;
	public String REAL_ESTATE_STATE;
	public String REAL_ESTATE_NO;
	public String ERRCODE;
	public String ERRMSG;
	public String REGISTER_DT;
	public int TOTAL_COUNT;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public int getSEQ_REALSTATE_REGISTER() {
		return SEQ_REALSTATE_REGISTER;
	}
	public void setSEQ_REALSTATE_REGISTER(int SEQ_REALSTATE_REGISTER) {
		this.SEQ_REALSTATE_REGISTER = SEQ_REALSTATE_REGISTER;
	}

	public String getREAL_ESTATE_OWNER() {
		return REAL_ESTATE_OWNER;
	}

	public void setREAL_ESTATE_OWNER(String REAL_ESTATE_OWNER) {
		this.REAL_ESTATE_OWNER = REAL_ESTATE_OWNER;
	}

	public String getREAL_ESTATE_ADDRESS() {
		return REAL_ESTATE_ADDRESS;
	}

	public void setREAL_ESTATE_ADDRESS(String REAL_ESTATE_ADDRESS) {
		this.REAL_ESTATE_ADDRESS = REAL_ESTATE_ADDRESS;
	}

	public String getREAL_ESTATE_STATE() {
		return REAL_ESTATE_STATE;
	}

	public void setREAL_ESTATE_STATE(String REAL_ESTATE_STATE) {
		this.REAL_ESTATE_STATE = REAL_ESTATE_STATE;
	}

	public String getREAL_ESTATE_NO() {
		return REAL_ESTATE_NO;
	}

	public void setREAL_ESTATE_NO(String REAL_ESTATE_NO) {
		this.REAL_ESTATE_NO = REAL_ESTATE_NO;
	}

	public String getERRCODE() {
		return ERRCODE;
	}
	public void setERRCODE(String ERRCODE) {
		this.ERRCODE = ERRCODE;
	}
	public String getERRMSG() {
		return ERRMSG;
	}
	public void setERRMSG(String ERRMSG) {
		this.ERRMSG = ERRMSG;
	}
	public String getREGISTER_DT() {
		return REGISTER_DT;
	}
	public void setREGISTER_DT(String REGISTER_DT) {
		this.REGISTER_DT = REGISTER_DT;
	}

	public int getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}

	public void setTOTAL_COUNT(int TOTAL_COUNT) {
		this.TOTAL_COUNT = TOTAL_COUNT;
	}

	
}
