package com.koscom.batch.coocon.domain;

import java.util.List;

/**
 * Created by lee on 2017-10-20.
 */

public class CooconGoodsArray {
	
	private static final long serialVersionUID = 7828890049046758009L;
	
	protected List<CooconGoodsArrayList> RESP_DATA;  //대출상품 리스트
	protected String REST_DATA = "";
	protected String ERRMSG = "";
	protected String ERRCODE = "";
	protected String TOTAL_COUNT = "";
	public List<CooconGoodsArrayList> getRESP_DATA() {
		return RESP_DATA;
	}
	public void setRESP_DATA(List<CooconGoodsArrayList> rESP_DATA) {
		RESP_DATA = rESP_DATA;
	}
	public String getREST_DATA() {
		return REST_DATA;
	}
	public void setREST_DATA(String rEST_DATA) {
		REST_DATA = rEST_DATA;
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
	public String getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}
	public void setTOTAL_COUNT(String tOTAL_COUNT) {
		TOTAL_COUNT = tOTAL_COUNT;
	}
	
	
}