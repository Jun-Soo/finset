package com.koscom.batch.fss.domain;

import java.io.Serializable;
import java.util.List;

public class FssCompanyProductVO implements Serializable {
	private String dcls_month= "";		//**공시 제출월 [YYYYMM]
	private String fin_co_no= "";		//금융회사코드
	private String kor_co_nm= "";		//금융회사 명
	private String dcls_chrg_man= "";		//공시담당자
	private String homp_url= "";		//홈페이지주소
	private String cal_tel= "";		//콜센터전화번호
	private String id_frt= "";		//ADMIN
	private String dt_frt= "";		//ADMIN
	private String id_lst= "";		//ADMIN
	private String dt_lst= "";		//ADMIN
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
	public String getKor_co_nm() {
		return kor_co_nm;
	}
	public void setKor_co_nm(String kor_co_nm) {
		this.kor_co_nm = kor_co_nm;
	}
	public String getDcls_chrg_man() {
		return dcls_chrg_man;
	}
	public void setDcls_chrg_man(String dcls_chrg_man) {
		this.dcls_chrg_man = dcls_chrg_man;
	}
	public String getHomp_url() {
		return homp_url;
	}
	public void setHomp_url(String homp_url) {
		this.homp_url = homp_url;
	}
	public String getCal_tel() {
		return cal_tel;
	}
	public void setCal_tel(String cal_tel) {
		this.cal_tel = cal_tel;
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
