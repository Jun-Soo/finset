package com.koscom.batch.coocon.domain;

import java.util.List;

/**
 * Created by lee on 2017-10-20.
 */

public class CooconJsonArray {
	
	private static final long serialVersionUID = 7828890049046758009L;

	protected List<CooconVO> RESP_DATA;  //대출상품 리스트
	protected String ERRMSG = "";
	protected String ERRCODE = "";
	protected String SEQ = "";
	protected String TOTAL_COUNT = "";
	public List<CooconVO> getRESP_DATA() {
		return RESP_DATA;
	}
	public void setRESP_DATA(List<CooconVO> rESP_DATA) {
		RESP_DATA = rESP_DATA;
	}
	public String getERRMSG() {
		return ERRMSG;
	}
	public void setERRMSG(String eRRMSG) {
		ERRMSG = eRRMSG;
	}
	public String getERRCODE() {
		return ERRCODE;
	}
	public void setERRCODE(String eRRCODE) {
		ERRCODE = eRRCODE;
	}
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public String getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}
	public void setTOTAL_COUNT(String tOTAL_COUNT) {
		TOTAL_COUNT = tOTAL_COUNT;
	}

}