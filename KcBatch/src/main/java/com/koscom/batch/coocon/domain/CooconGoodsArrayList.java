package com.koscom.batch.coocon.domain;
import java.io.Serializable;

public class CooconGoodsArrayList implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;
	
	private String OUTLINE;
	private String SEQ;
	private String PRODUCT_NAME;
	private String DETAIL_ERRCODE;
	public String getOUTLINE() {
		return OUTLINE;
	}
	public void setOUTLINE(String oUTLINE) {
		OUTLINE = oUTLINE;
	}
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	
	public String toResult(){
		String result =
				"\nOUTLINE			    =" +OUTLINE
				+"\nSEQ			   		=" +SEQ
				+"\nPRODUCT_NAMEL       =" +PRODUCT_NAME
				;
		return result;
	}
	public String getDETAIL_ERRCODE() {
		return DETAIL_ERRCODE;
	}
	public void setDETAIL_ERRCODE(String dETAIL_ERRCODE) {
		DETAIL_ERRCODE = dETAIL_ERRCODE;
	}
	
}