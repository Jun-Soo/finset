package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

public class FcLinkInfoVO implements Serializable{
	private static final long serialVersionUID = -4159327904064889977L;
	private String NO_PERSON = "";			//회원고유번호
	private String CN = "";					//공인인증서일련번호
	private String CD_AGENCY = "";			//기관구분
	private String CD_FC = "";				//금융기관코드
	private String CD_LINK_STAT = "";		//연동상태
	private String YN_LINK = "";			//연동여뷰
	private String TYPE_LOGIN = "";			//로그인방식
	private String LINK_DATE = "";			//연동일자
	private String RSN_LINK_MESSAGE = "";	//연동결과메세지
	private String ID_FRT = "";				//최초입력아이디
	private String DT_FRT = "";				//최초입력시간
	private String ID_LST = "";				//최종수정아이디
	private String DT_LST = "";				//최종수정시간
	private String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패
	private String ERROR_MESSAGE = "";		//결과메시지
	private List<FcLinkInfoVO> list;		//금융사 연계 관리
	
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
	public String getCD_LINK_STAT() {
		return CD_LINK_STAT;
	}
	public void setCD_LINK_STAT(String cD_LINK_STAT) {
		CD_LINK_STAT = cD_LINK_STAT;
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
	public String getLINK_DATE() {
		return LINK_DATE;
	}
	public void setLINK_DATE(String lINK_DATE) {
		LINK_DATE = lINK_DATE;
	}
	public String getRSN_LINK_MESSAGE() {
		return RSN_LINK_MESSAGE;
	}
	public void setRSN_LINK_MESSAGE(String rSN_LINK_MESSAGE) {
		RSN_LINK_MESSAGE = rSN_LINK_MESSAGE;
	}
	public String getID_FRT() {
		return ID_FRT;
	}
	public void setID_FRT(String iD_FRT) {
		ID_FRT = iD_FRT;
	}
	public String getDT_FRT() {
		return DT_FRT;
	}
	public void setDT_FRT(String dT_FRT) {
		DT_FRT = dT_FRT;
	}
	public String getID_LST() {
		return ID_LST;
	}
	public void setID_LST(String iD_LST) {
		ID_LST = iD_LST;
	}
	public String getDT_LST() {
		return DT_LST;
	}
	public void setDT_LST(String dT_LST) {
		DT_LST = dT_LST;
	}
	public List<FcLinkInfoVO> getList() {
		return list;
	}
	public void setList(List<FcLinkInfoVO> list) {
		this.list = list;
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
}