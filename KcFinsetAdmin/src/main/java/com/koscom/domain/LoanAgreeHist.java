package com.koscom.domain;

import java.io.Serializable;

public class LoanAgreeHist implements Serializable{
	private static final long serialVersionUID = 6275802925922383562L;
	private String no_person;	//고객번호
	private String cd_agree;	//동의종류코드
	private String dt_agree;	//동의일자
	
	private String id_frt = "";	//최초입력아이디
	private String dt_frt = "";	//최종입력날짜
	private String id_lst = "";	//최종입력아이디
	private String dt_lst = "";	//최종입력날짜
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_agree() {
		return cd_agree;
	}
	public void setCd_agree(String cd_agree) {
		this.cd_agree = cd_agree;
	}
	public String getDt_agree() {
		return dt_agree;
	}
	public void setDt_agree(String dt_agree) {
		this.dt_agree = dt_agree;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
}