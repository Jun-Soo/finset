package com.koscom.coocon.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class CooconAPIinfoVO extends SearchForm implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;
//	private static final long serialVersionUID = -5283835109034749303L;
	
	private String cd_coocon_goods;
	private String nm_coocon_goods;
	private String gubun;
	private String org_type;
	private String cd_org;
	private String cd_fc;
	private String yn_use;
	private String id_frt;
	private String dt_frt;
	private String id_lst;
	private String dt_lst;
	private String cd_fin;
	private String nm_fc;
	
	public String getCd_coocon_goods() {
		return cd_coocon_goods;
	}
	public void setCd_coocon_goods(String cd_coocon_goods) {
		this.cd_coocon_goods = cd_coocon_goods;
	}
	public String getNm_coocon_goods() {
		return nm_coocon_goods;
	}
	public void setNm_coocon_goods(String nm_coocon_goods) {
		this.nm_coocon_goods = nm_coocon_goods;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getCd_org() {
		return cd_org;
	}
	public void setCd_org(String cd_org) {
		this.cd_org = cd_org;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_user(String yn_use) {
		this.yn_use = yn_use;
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
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	
}