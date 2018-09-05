package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

public class UserCardOutputVO implements Serializable{
	private static final long serialVersionUID = 3983890694732901290L;
	protected String CARD_CODE = "";				//은행코드,
	protected String ERROR_CODE = "";				//결과코드(00000000) 성공 나머지 실패
	protected String ERROR_MESSAGE = "";			//결과메시지
	protected String APPROVAL_ERROR_CODE = "";		//결과코드(00000000) 성공 나머지 실패
	protected String APPROVAL_ERROR_MESSAGE = "";	//결과메시지
	protected String DT_START = "";					//조회시작일
	protected String DT_END = "";					//조회종료일
	protected List<ScrCardInfoVO> CARD_INFO;  				//카드내역
	protected List<ScrCardApprovalInfoVO> CARD_APPROVAL;	//카드승인내역
	public String getCARD_CODE() {
		return CARD_CODE;
	}
	public void setCARD_CODE(String cARD_CODE) {
		CARD_CODE = cARD_CODE;
	}
	public String getERROR_CODE() {
		return ERROR_CODE;
	}
	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}
	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}
	public void setERROR_MESSAGE(String eRROR_MESSAGE) {
		ERROR_MESSAGE = eRROR_MESSAGE;
	}
	public String getAPPROVAL_ERROR_CODE() {
		return APPROVAL_ERROR_CODE;
	}
	public void setAPPROVAL_ERROR_CODE(String aPPROVAL_ERROR_CODE) {
		APPROVAL_ERROR_CODE = aPPROVAL_ERROR_CODE;
	}
	public String getAPPROVAL_ERROR_MESSAGE() {
		return APPROVAL_ERROR_MESSAGE;
	}
	public void setAPPROVAL_ERROR_MESSAGE(String aPPROVAL_ERROR_MESSAGE) {
		APPROVAL_ERROR_MESSAGE = aPPROVAL_ERROR_MESSAGE;
	}
	public String getDT_START() {
		return DT_START;
	}
	public void setDT_START(String dT_START) {
		DT_START = dT_START;
	}
	public String getDT_END() {
		return DT_END;
	}
	public void setDT_END(String dT_END) {
		DT_END = dT_END;
	}
	public List<ScrCardInfoVO> getCARD_INFO() {
		return CARD_INFO;
	}
	public void setCARD_INFO(List<ScrCardInfoVO> cARD_INFO) {
		CARD_INFO = cARD_INFO;
	}
	public List<ScrCardApprovalInfoVO> getCARD_APPROVAL() {
		return CARD_APPROVAL;
	}
	public void setCARD_APPROVAL(List<ScrCardApprovalInfoVO> cARD_APPROVAL) {
		CARD_APPROVAL = cARD_APPROVAL;
	}
}