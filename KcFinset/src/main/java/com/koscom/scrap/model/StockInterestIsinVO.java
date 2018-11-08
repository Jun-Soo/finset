package com.koscom.scrap.model;

import java.io.Serializable;

public class StockInterestIsinVO implements Serializable{
	private static final long serialVersionUID = 5392441454941664164L;
	String no_person = "";			//회원관리번호
	String cd_fc = "";				//금융사코드
	String accno = "";				//가상계좌번호
	String group_name = "";			//관심종목그룹명
	String list_isincode = "";		//종목코드리스트
	String id_frt = "";				//최초입력아이디
	String dt_frt = "";				//최초입력시간
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getList_isincode() {
		return list_isincode;
	}
	public void setList_isincode(String list_isincode) {
		this.list_isincode = list_isincode;
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
}