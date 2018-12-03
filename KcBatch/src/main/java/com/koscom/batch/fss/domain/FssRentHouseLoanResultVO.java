package com.koscom.batch.fss.domain;

import java.io.Serializable;

public class FssRentHouseLoanResultVO implements Serializable {
	private String prdt_div= "";		//프로젝트분류
	private String err_cd= "";		//응답코드
	private String err_msg= "";		//응답메시지
	private int total_count= 0;		//총 상품건수
	private int max_page_no= 0;		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
	private int now_page_no= 0;		//현재 조회 페이지 번호
	private String id_frt= "";		//ADMIN
	private String dt_frt= "";		//ADMIN
	private String id_lst= "";		//ADMIN
	private String dt_lst= "";		//ADMIN
	public String getPrdt_div() {
		return prdt_div;
	}
	public void setPrdt_div(String prdt_div) {
		this.prdt_div = prdt_div;
	}
	public String getErr_cd() {
		return err_cd;
	}
	public void setErr_cd(String err_cd) {
		this.err_cd = err_cd;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public int getMax_page_no() {
		return max_page_no;
	}
	public void setMax_page_no(int max_page_no) {
		this.max_page_no = max_page_no;
	}
	public int getNow_page_no() {
		return now_page_no;
	}
	public void setNow_page_no(int now_page_no) {
		this.now_page_no = now_page_no;
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
