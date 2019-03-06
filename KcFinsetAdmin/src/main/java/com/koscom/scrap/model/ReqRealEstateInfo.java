package com.koscom.scrap.model;

import java.io.Serializable;

public class ReqRealEstateInfo implements Serializable {

	private static final long serialVersionUID = 2767830286330119951L;

	private String no_person;
	private int seq_realstate_register;
	private int seq_scraping_result;
	private String search_gubun;
	private String real_estate_gubun;
	private String region_1;
	private String region_2;
	private String region_3;
	private String building_name;
	private String building_no;
	private String room_no;
	private String street_region;
	private String street_name;
	private String street_no;
	private String real_estate_no;
	private String error_cd;
	private String error_msg;
	private String register_dt;
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public int getSeq_realstate_register() {
		return seq_realstate_register;
	}
	public void setSeq_realstate_register(int seq_realstate_register) {
		this.seq_realstate_register = seq_realstate_register;
	}
	public int getSeq_scraping_result() {
		return seq_scraping_result;
	}
	public void setSeq_scraping_result(int seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}
	public String getSearch_gubun() {
		return search_gubun;
	}
	public void setSearch_gubun(String search_gubun) {
		this.search_gubun = search_gubun;
	}
	public String getReal_estate_gubun() {
		return real_estate_gubun;
	}
	public void setReal_estate_gubun(String real_estate_gubun) {
		this.real_estate_gubun = real_estate_gubun;
	}
	public String getRegion_1() {
		return region_1;
	}
	public void setRegion_1(String region_1) {
		this.region_1 = region_1;
	}
	public String getRegion_2() {
		return region_2;
	}
	public void setRegion_2(String region_2) {
		this.region_2 = region_2;
	}
	public String getRegion_3() {
		return region_3;
	}
	public void setRegion_3(String region_3) {
		this.region_3 = region_3;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getBuilding_no() {
		return building_no;
	}
	public void setBuilding_no(String building_no) {
		this.building_no = building_no;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getStreet_region() {
		return street_region;
	}
	public void setStreet_region(String street_region) {
		this.street_region = street_region;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	public String getStreet_no() {
		return street_no;
	}
	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}
	public String getReal_estate_no() {
		return real_estate_no;
	}
	public void setReal_estate_no(String real_estate_no) {
		this.real_estate_no = real_estate_no;
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
