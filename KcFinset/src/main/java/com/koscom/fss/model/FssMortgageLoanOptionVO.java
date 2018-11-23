package com.koscom.fss.model;

import java.io.Serializable;

public class FssMortgageLoanOptionVO implements Serializable {
	private String dcls_month= "";		//공시 제출월 [YYYYMM]
	private String fin_co_no= "";		//금융회사코드
	private String fin_prdt_cd= "";		//금융상품코드
	private String mrtg_type= "";		//담보유형 코드
	private String mrtg_type_nm= "";		//담보유형
	private String rpay_type= "";		//대출상환유형 코드
	private String rpay_type_nm= "";		//대출상환유형
	private String lend_rate_type= "";		//대출금리유형 코드
	private String lend_rate_type_nm= "";		//대출금리유형
	private String lend_rate_min= "";		//대출금리_최저 [소수점 2자리]
	private String lend_rate_max= "";		//대출금리_최고 [소수점 2자리]
	private String lend_rate_avg= "";		//전월 취급 평균금리 [소수점 2자리]
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
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getMrtg_type() {
		return mrtg_type;
	}
	public void setMrtg_type(String mrtg_type) {
		this.mrtg_type = mrtg_type;
	}
	public String getMrtg_type_nm() {
		return mrtg_type_nm;
	}
	public void setMrtg_type_nm(String mrtg_type_nm) {
		this.mrtg_type_nm = mrtg_type_nm;
	}
	public String getRpay_type() {
		return rpay_type;
	}
	public void setRpay_type(String rpay_type) {
		this.rpay_type = rpay_type;
	}
	public String getRpay_type_nm() {
		return rpay_type_nm;
	}
	public void setRpay_type_nm(String rpay_type_nm) {
		this.rpay_type_nm = rpay_type_nm;
	}
	public String getLend_rate_type() {
		return lend_rate_type;
	}
	public void setLend_rate_type(String lend_rate_type) {
		this.lend_rate_type = lend_rate_type;
	}
	public String getLend_rate_type_nm() {
		return lend_rate_type_nm;
	}
	public void setLend_rate_type_nm(String lend_rate_type_nm) {
		this.lend_rate_type_nm = lend_rate_type_nm;
	}
	public String getLend_rate_min() {
		return lend_rate_min;
	}
	public void setLend_rate_min(String lend_rate_min) {
		this.lend_rate_min = lend_rate_min;
	}
	public String getLend_rate_max() {
		return lend_rate_max;
	}
	public void setLend_rate_max(String lend_rate_max) {
		this.lend_rate_max = lend_rate_max;
	}
	public String getLend_rate_avg() {
		return lend_rate_avg;
	}
	public void setLend_rate_avg(String lend_rate_avg) {
		this.lend_rate_avg = lend_rate_avg;
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
