package com.koscom.domain;

import java.io.Serializable;

public class FssCompanyOptionInfo implements Serializable{
	private static final long serialVersionUID = 3333666538011040309L;
	protected String dcls_month= "";		//공시 제출월 [YYYYMM]
	protected String fin_co_no= "";		//금융회사코드
	protected String area_cd= "";		//지역구분
	protected String area_nm= "";		//지역이름
	protected String exis_yn= "";		//점포소재여부
	protected String id_frt= "";		//ADMIN
	protected String dt_frt= "";		//ADMIN
	protected String id_lst= "";		//ADMIN
	protected String dt_lst= "";		//ADMIN
	public String getDcls_month() {
		return dcls_month;
	}
	public void setDcls_month(String dcls_month) {
		this.dcls_month = dcls_month;
	}
	public String getFin_co_no() {
		return fin_co_no;
	}
	public void setFin_co_no(String fin_co_no) {
		this.fin_co_no = fin_co_no;
	}
	public String getArea_cd() {
		return area_cd;
	}
	public void setArea_cd(String area_cd) {
		this.area_cd = area_cd;
	}
	public String getArea_nm() {
		return area_nm;
	}
	public void setArea_nm(String area_nm) {
		this.area_nm = area_nm;
	}
	public String getExis_yn() {
		return exis_yn;
	}
	public void setExis_yn(String exis_yn) {
		this.exis_yn = exis_yn;
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
