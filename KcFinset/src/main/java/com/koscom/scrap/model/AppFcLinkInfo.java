package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

public class AppFcLinkInfo implements Serializable{
	private static final long serialVersionUID = -7291582058918933296L;
	protected String NO_PERSON = "";				//회원관리번호
	protected String CN = "";						//회원관리번호
	protected String CERTIFICATE_AGENCY = "";		//인증서기관
	protected String DT_EXPIRE = "";				//만료일자
	protected FcLinkInfoVO			NTS_LINK_INFO;	//은행 연동 정보
	protected List<FcLinkInfoVO>	BANK_LINK_INFO;	//은행 연동 정보
	protected List<FcLinkInfoVO>	CARD_LINK_INFO;	//카드 연동 정보
	
	public String getNO_PERSON() {
		return NO_PERSON;
	}
	public void setNO_PERSON(String nO_PERSON) {
		NO_PERSON = nO_PERSON;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getCERTIFICATE_AGENCY() {
		return CERTIFICATE_AGENCY;
	}
	public void setCERTIFICATE_AGENCY(String cERTIFICATE_AGENCY) {
		CERTIFICATE_AGENCY = cERTIFICATE_AGENCY;
	}
	public String getDT_EXPIRE() {
		return DT_EXPIRE;
	}
	public void setDT_EXPIRE(String dT_EXPIRE) {
		DT_EXPIRE = dT_EXPIRE;
	}
	public FcLinkInfoVO getNTS_LINK_INFO() {
		return NTS_LINK_INFO;
	}
	public void setNTS_LINK_INFO(FcLinkInfoVO nTS_LINK_INFO) {
		NTS_LINK_INFO = nTS_LINK_INFO;
	}
	public List<FcLinkInfoVO> getBANK_LINK_INFO() {
		return BANK_LINK_INFO;
	}
	public void setBANK_LINK_INFO(List<FcLinkInfoVO> bANK_LINK_INFO) {
		BANK_LINK_INFO = bANK_LINK_INFO;
	}
	public List<FcLinkInfoVO> getCARD_LINK_INFO() {
		return CARD_LINK_INFO;
	}
	public void setCARD_LINK_INFO(List<FcLinkInfoVO> cARD_LINK_INFO) {
		CARD_LINK_INFO = cARD_LINK_INFO;
	}
}