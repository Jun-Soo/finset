package com.koscom.scrap.model;

import java.io.Serializable;

public class StockListVO implements Serializable{
	private static final long serialVersionUID = -8186614225954420276L;
	String no_person = "";			//회원관리번호
	String cd_fc = "";				//금융사코드
	String accno = "";				//가상계좌번호
	String real_accno = "";			//실계좌번호
	String state = "";				//상태
	String acc_type = "";			//계좌종류
	String alias = "";				//가상계좌Alias
	String yn_delete = "";			//삭제여부
	String id_frt = "";				//최초입력아이디
	String dt_frt = "";				//최초입력시간
	String id_lst = "";				//최종수정아이디
	String dt_lst = "";				//최종수정시간
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
	public String getReal_accno() {
		return real_accno;
	}
	public void setReal_accno(String real_accno) {
		this.real_accno = real_accno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getYn_delete() {
		return yn_delete;
	}
	public void setYn_delete(String yn_delete) {
		this.yn_delete = yn_delete;
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
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}

}