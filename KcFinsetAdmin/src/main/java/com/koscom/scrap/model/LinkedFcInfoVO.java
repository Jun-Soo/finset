package com.koscom.scrap.model;

import java.io.Serializable;

public class LinkedFcInfoVO implements Serializable{
	private static final long serialVersionUID = -4159327904064889977L;
	private String NO_PERSON = "";		//회원고유번호
	private String CN = "";				//공인인증서일련번호
	private String CD_AGENCY = "";		//기관구분
	private String CD_FC = "";			//금융기관코드
	private String NM_FC = "";			//금융기관이름
	private String CD_FIN = "";			//금융기관구분코드
	private String NM_CODE = "";		//금융기관구분이름
	private String CD_COOCON = "";		//쿠콘 금융사 코드
	private String CD_LINK_STAT = "";	//연동상태
	private String RSN_LINK_MESSAGE = "";	//연동결과메세지
	private String YN_LINK = "";		//연동여부
	private String TYPE_LOGIN = "";		//로그인방식
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
	public String getCD_AGENCY() {
		return CD_AGENCY;
	}
	public void setCD_AGENCY(String cD_AGENCY) {
		CD_AGENCY = cD_AGENCY;
	}
	public String getCD_FC() {
		return CD_FC;
	}
	public void setCD_FC(String cD_FC) {
		CD_FC = cD_FC;
	}
	public String getNM_FC() {
		return NM_FC;
	}
	public void setNM_FC(String nM_FC) {
		NM_FC = nM_FC;
	}
	public String getCD_FIN() {
		return CD_FIN;
	}
	public void setCD_FIN(String cD_FIN) {
		CD_FIN = cD_FIN;
	}
	public String getNM_CODE() {
		return NM_CODE;
	}
	public void setNM_CODE(String nM_CODE) {
		NM_CODE = nM_CODE;
	}
	public String getCD_COOCON() {
		return CD_COOCON;
	}
	public void setCD_COOCON(String cD_COOCON) {
		CD_COOCON = cD_COOCON;
	}
	public String getCD_LINK_STAT() {
		return CD_LINK_STAT;
	}
	public void setCD_LINK_STAT(String cD_LINK_STAT) {
		CD_LINK_STAT = cD_LINK_STAT;
	}
	public String getRSN_LINK_MESSAGE() {
		return RSN_LINK_MESSAGE;
	}
	public void setRSN_LINK_MESSAGE(String rSN_LINK_MESSAGE) {
		RSN_LINK_MESSAGE = rSN_LINK_MESSAGE;
	}
	public String getYN_LINK() {
		return YN_LINK;
	}
	public void setYN_LINK(String yN_LINK) {
		YN_LINK = yN_LINK;
	}
	public String getTYPE_LOGIN() {
		return TYPE_LOGIN;
	}
	public void setTYPE_LOGIN(String tYPE_LOGIN) {
		TYPE_LOGIN = tYPE_LOGIN;
	}
}