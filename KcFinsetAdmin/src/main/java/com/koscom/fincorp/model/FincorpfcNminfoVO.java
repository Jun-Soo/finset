package com.koscom.fincorp.model;

import java.io.Serializable;

public class FincorpfcNminfoVO implements Serializable {

	private static final long serialVersionUID = -5283835109034749303L;

	private String cd_fc;
	private String seq;
	private String nm_nm_fc;
	private String id_frt;
	private String dt_frt;
	private String id_lst;
	private String dt_lst;
	private String nm_yn_use;
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getNm_nm_fc() {
		return nm_nm_fc;
	}
	public void setNm_nm_fc(String nm_nm_fc) {
		this.nm_nm_fc = nm_nm_fc;
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
	public String getNm_yn_use() {
		return nm_yn_use;
	}
	public void setNm_yn_use(String nm_yn_use) {
		this.nm_yn_use = nm_yn_use;
	}
	
	
}