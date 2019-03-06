package com.koscom.scrap.model;

import java.io.Serializable;

public class ReqCarInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4076551264252398082L;

	private String no_person;
	private int seq_car_register;
	private int seq_scraping_result;
	private String worktype;
	private String car_num;
	private String owner_name;
	private String view_yn_securiity;
	private String view_yn_address;
	private String error_cd;
	private String error_msg;
	private String register_dt;
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public int getSeq_car_register() {
		return seq_car_register;
	}
	public void setSeq_car_register(int seq_car_register) {
		this.seq_car_register = seq_car_register;
	}
	public int getSeq_scraping_result() {
		return seq_scraping_result;
	}
	public void setSeq_scraping_result(int seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getView_yn_securiity() {
		return view_yn_securiity;
	}
	public void setView_yn_securiity(String view_yn_securiity) {
		this.view_yn_securiity = view_yn_securiity;
	}
	public String getView_yn_address() {
		return view_yn_address;
	}
	public void setView_yn_address(String view_yn_address) {
		this.view_yn_address = view_yn_address;
	}
	public String getError_cd() {
		return error_cd;
	}
	public void setError_cd(String error_cd) {
		this.error_cd = error_cd;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getRegister_dt() {
		return register_dt;
	}
	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}
	
	
}
