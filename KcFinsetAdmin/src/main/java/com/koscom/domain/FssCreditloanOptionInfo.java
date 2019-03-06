package com.koscom.domain;

import java.io.Serializable;

public class FssCreditloanOptionInfo implements Serializable{
	private static final long serialVersionUID = -8283904534870003627L;
	protected String dcls_month= "";		//공시 제출월 [YYYYMM]
	protected String fin_co_no= "";		//금융회사코드
	protected String fin_prdt_cd= "";		//금융상품코드
	protected String crdt_lend_rate_type= "";		//금리구분 코드
	protected String crdt_lend_rate_type_nm= "";		//금리구분
	protected double crdt_grad_1;		//은행: 1~2 등급 [소수점 2자리] 비은행: 1~3 등급 [소수점 2자리]
	protected double crdt_grad_4;		//은행: 3~4 등급 [소수점 2자리] 비은행: 4 등급 [소수점 2자리]
	protected double crdt_grad_5;		//은행: 5~6 등급 [소수점 2자리] 비은행: 5 등급 [소수점 2자리]
	protected double crdt_grad_6;		//은행: 7~8 등급 [소수점 2자리] 비은행: 6 등급 [소수점 2자리]
	protected double crdt_grad_10;		//은행: 9~10 등급 [소수점 2자리] 비은행: 7~10 등급 [소수점 2자리]
	protected double crdt_grad_avg;		//평균 금리 [소수점 2자리]
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
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getCrdt_lend_rate_type() {
		return crdt_lend_rate_type;
	}
	public void setCrdt_lend_rate_type(String crdt_lend_rate_type) {
		this.crdt_lend_rate_type = crdt_lend_rate_type;
	}
	public String getCrdt_lend_rate_type_nm() {
		return crdt_lend_rate_type_nm;
	}
	public void setCrdt_lend_rate_type_nm(String crdt_lend_rate_type_nm) {
		this.crdt_lend_rate_type_nm = crdt_lend_rate_type_nm;
	}
	public double getCrdt_grad_1() {
		return crdt_grad_1;
	}
	public void setCrdt_grad_1(double crdt_grad_1) {
		this.crdt_grad_1 = crdt_grad_1;
	}
	public double getCrdt_grad_4() {
		return crdt_grad_4;
	}
	public void setCrdt_grad_4(double crdt_grad_4) {
		this.crdt_grad_4 = crdt_grad_4;
	}
	public double getCrdt_grad_5() {
		return crdt_grad_5;
	}
	public void setCrdt_grad_5(double crdt_grad_5) {
		this.crdt_grad_5 = crdt_grad_5;
	}
	public double getCrdt_grad_6() {
		return crdt_grad_6;
	}
	public void setCrdt_grad_6(double crdt_grad_6) {
		this.crdt_grad_6 = crdt_grad_6;
	}
	public double getCrdt_grad_10() {
		return crdt_grad_10;
	}
	public void setCrdt_grad_10(double crdt_grad_10) {
		this.crdt_grad_10 = crdt_grad_10;
	}
	public double getCrdt_grad_avg() {
		return crdt_grad_avg;
	}
	public void setCrdt_grad_avg(double crdt_grad_avg) {
		this.crdt_grad_avg = crdt_grad_avg;
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