package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FcLinkInfoVO implements Serializable{
	private static final long serialVersionUID = -4159327904064889977L;
	@SerializedName("NO_PERSON")
	private String no_person = "";			//회원고유번호
	@SerializedName("CN")
	private String cn = "";					//공인인증서일련번호
	@SerializedName("CD_AGENCY")
	private String cd_agency = "";			//기관구분
	@SerializedName("CD_FC")
	private String cd_fc = "";				//금융기관코드
	@SerializedName("CD_LINK_STAT")
	private String cd_link_stat = "";		//연동상태
	@SerializedName("YN_LINK")
	private String yn_link = "";			//연동여뷰
	@SerializedName("TYPE_LOGIN")
	private String type_login = "";			//로그인방식
	@SerializedName("LINK_DATE")
	private String link_date = "";			//연동일자
	@SerializedName("RSN_LINK_MESSAGE")
	private String rsn_link_message = "";	//연동결과메세지
	private String id_frt = "";				//최초입력아이디
	private String dt_frt = "";				//최초입력시간
	private String id_lst = "";				//최종수정아이디
	private String st_lst = "";				//최종수정시간
	@SerializedName("ERROR_CODE")
	private String error_code = "";			//결과코드(00000000) 성공 나머지 실패
	@SerializedName("ERROR_MESSAGE")
	private String error_message = "";		//결과메시지
	private List<FcLinkInfoVO> list;		//금융사 연계 관리
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getCd_agency() {
		return cd_agency;
	}
	public void setCd_agency(String cd_agency) {
		this.cd_agency = cd_agency;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getCd_link_stat() {
		return cd_link_stat;
	}
	public void setCd_link_stat(String cd_link_stat) {
		this.cd_link_stat = cd_link_stat;
	}
	public String getYn_link() {
		return yn_link;
	}
	public void setYn_link(String yn_link) {
		this.yn_link = yn_link;
	}
	public String getType_login() {
		return type_login;
	}
	public void setType_login(String type_login) {
		this.type_login = type_login;
	}
	public String getLink_date() {
		return link_date;
	}
	public void setLink_date(String link_date) {
		this.link_date = link_date;
	}
	public String getRsn_link_message() {
		return rsn_link_message;
	}
	public void setRsn_link_message(String rsn_link_message) {
		this.rsn_link_message = rsn_link_message;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getSt_lst() {
		return st_lst;
	}
	public void setSt_lst(String st_lst) {
		this.st_lst = st_lst;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public List<FcLinkInfoVO> getList() {
		return list;
	}
	public void setList(List<FcLinkInfoVO> list) {
		this.list = list;
	}
}