package com.koscom.finset.model;

import java.io.Serializable;

public class FinsetDenyVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1681865272118449442L;
	String no_person = "";
	String no_prepare = "";
	//String cd_apply_comp = "";
	String cd_fc = "";
	String cd_goods = "";
	String deny_desc = "";
	String id_frt = "";
	String dt_frt = "";
	String id_lst = "";
	String dt_lst = "";
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_prepare() {
		return no_prepare;
	}
	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}
//	public String getCd_apply_comp() {
//		return cd_apply_comp;
//	}
//	public void setCd_apply_comp(String cd_apply_comp) {
//		this.cd_apply_comp = cd_apply_comp;
//	}
	
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getCd_goods() {
		return cd_goods;
	}
	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	public String getDeny_desc() {
		return deny_desc;
	}
	public void setDeny_desc(String deny_desc) {
		this.deny_desc = deny_desc;
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
