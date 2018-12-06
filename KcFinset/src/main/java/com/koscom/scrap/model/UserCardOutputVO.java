package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

public class UserCardOutputVO implements Serializable{
	private static final long serialVersionUID = 3983890694732901290L;
	protected String CARD_CODE = "";					//카드코드,
	protected String ERROR_CODE = "";					//결과코드(00000000) 성공 나머지 실패
	protected String ERROR_MESSAGE = "";				//결과메시지
	protected String CARD_ERROR_CODE = "";				//결과코드(00000000) 성공 나머지 실패
	protected String CARD_ERROR_MESSAGE = "";			//결과메시지
	protected String APPROVAL_ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패
	protected String APPROVAL_ERROR_MESSAGE = "";		//결과메시지
	protected String CHARGE_ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패
	protected String CHARGE_ERROR_MESSAGE = "";			//결과메시지
	protected String LIMIT_ERROR_CODE = "";				//결과코드(00000000) 성공 나머지 실패
	protected String LIMIT_ERROR_MESSAGE = "";			//결과메시지
	protected String POINT_ERROR_CODE = "";				//결과코드(00000000) 성공 나머지 실패
	protected String POINT_ERROR_MESSAGE = "";			//결과메시지
	protected String DT_APPROVAL_START = "";			//승인내역조회시작일
	protected String DT_APPROVAL_END = "";				//승인내역조회종료일
	protected String DT_CHARGE_START = "";				//청구내역조회시작일
	protected String DT_CHARGE_END = "";				//청구내역조회종료일
	protected List<ScrCardInfoVO> CARD_INFO;  			//카드내역
	protected List<ScrCardLimitInfoVO> CARD_LIMIT;		//카드한도내역
	protected List<ScrCardPointInfoVO> CARD_POINT;  	//카드포인트내역
	protected List<ScrCardApprovalInfoVO> CARD_APPROVAL;	//카드승인내역
	protected List<ScrCardChargeInfoVO> CARD_CHARGE;		//카드청구내역
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
	public String getCARD_ERROR_CODE() {
		return CARD_ERROR_CODE;
	}
	public void setCARD_ERROR_CODE(String cARD_ERROR_CODE) {
		CARD_ERROR_CODE = cARD_ERROR_CODE;
	}
	public String getCARD_ERROR_MESSAGE() {
		return CARD_ERROR_MESSAGE;
	}
	public void setCARD_ERROR_MESSAGE(String cARD_ERROR_MESSAGE) {
		CARD_ERROR_MESSAGE = cARD_ERROR_MESSAGE;
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
	public String getCHARGE_ERROR_CODE() {
		return CHARGE_ERROR_CODE;
	}
	public void setCHARGE_ERROR_CODE(String cHARGE_ERROR_CODE) {
		CHARGE_ERROR_CODE = cHARGE_ERROR_CODE;
	}
	public String getCHARGE_ERROR_MESSAGE() {
		return CHARGE_ERROR_MESSAGE;
	}
	public void setCHARGE_ERROR_MESSAGE(String cHARGE_ERROR_MESSAGE) {
		CHARGE_ERROR_MESSAGE = cHARGE_ERROR_MESSAGE;
	}
	public String getLIMIT_ERROR_CODE() {
		return LIMIT_ERROR_CODE;
	}
	public void setLIMIT_ERROR_CODE(String lIMIT_ERROR_CODE) {
		LIMIT_ERROR_CODE = lIMIT_ERROR_CODE;
	}
	public String getLIMIT_ERROR_MESSAGE() {
		return LIMIT_ERROR_MESSAGE;
	}
	public void setLIMIT_ERROR_MESSAGE(String lIMIT_ERROR_MESSAGE) {
		LIMIT_ERROR_MESSAGE = lIMIT_ERROR_MESSAGE;
	}
	public String getPOINT_ERROR_CODE() {
		return POINT_ERROR_CODE;
	}
	public void setPOINT_ERROR_CODE(String pOINT_ERROR_CODE) {
		POINT_ERROR_CODE = pOINT_ERROR_CODE;
	}
	public String getPOINT_ERROR_MESSAGE() {
		return POINT_ERROR_MESSAGE;
	}
	public void setPOINT_ERROR_MESSAGE(String pOINT_ERROR_MESSAGE) {
		POINT_ERROR_MESSAGE = pOINT_ERROR_MESSAGE;
	}
	public String getDT_APPROVAL_START() {
		return DT_APPROVAL_START;
	}
	public void setDT_APPROVAL_START(String dT_APPROVAL_START) {
		DT_APPROVAL_START = dT_APPROVAL_START;
	}
	public String getDT_APPROVAL_END() {
		return DT_APPROVAL_END;
	}
	public void setDT_APPROVAL_END(String dT_APPROVAL_END) {
		DT_APPROVAL_END = dT_APPROVAL_END;
	}
	public String getDT_CHARGE_START() {
		return DT_CHARGE_START;
	}
	public void setDT_CHARGE_START(String dT_CHARGE_START) {
		DT_CHARGE_START = dT_CHARGE_START;
	}
	public String getDT_CHARGE_END() {
		return DT_CHARGE_END;
	}
	public void setDT_CHARGE_END(String dT_CHARGE_END) {
		DT_CHARGE_END = dT_CHARGE_END;
	}
	public List<ScrCardInfoVO> getCARD_INFO() {
		return CARD_INFO;
	}
	public void setCARD_INFO(List<ScrCardInfoVO> cARD_INFO) {
		CARD_INFO = cARD_INFO;
	}
	public List<ScrCardLimitInfoVO> getCARD_LIMIT() {
		return CARD_LIMIT;
	}
	public void setCARD_LIMIT(List<ScrCardLimitInfoVO> cARD_LIMIT) {
		CARD_LIMIT = cARD_LIMIT;
	}
	public List<ScrCardPointInfoVO> getCARD_POINT() {
		return CARD_POINT;
	}
	public void setCARD_POINT(List<ScrCardPointInfoVO> cARD_POINT) {
		CARD_POINT = cARD_POINT;
	}
	public List<ScrCardApprovalInfoVO> getCARD_APPROVAL() {
		return CARD_APPROVAL;
	}
	public void setCARD_APPROVAL(List<ScrCardApprovalInfoVO> cARD_APPROVAL) {
		CARD_APPROVAL = cARD_APPROVAL;
	}
	public List<ScrCardChargeInfoVO> getCARD_CHARGE() {
		return CARD_CHARGE;
	}
	public void setCARD_CHARGE(List<ScrCardChargeInfoVO> cARD_CHARGE) {
		CARD_CHARGE = cARD_CHARGE;
	}
}