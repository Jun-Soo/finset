package com.koscom.kbrealestate.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KbDongAptVO implements Serializable{
	private static final long serialVersionUID = -2318059038706060975L;
	
	private String	result_cd;		//조회 결과 코드값
	private String	result_mg;		//조회 결과 메시지
	private int		total_count;	//결과건수
	private String	building_type;	//1 : 아파트, 2 : 오피스텔
	private String	region1_code;	//대지역코드
	private String	region1_name;	//대지역명
	private String	region2_code;	//중지역코드
	private String	region2_name;	//중지역명
	private String	region3_code;	//소지역코드
	private String	region3_name;	//소지역명
	private String	ldong_code;		//법정동코드
	private String	apartment;		//아파트코드-물건식별자
	private String	apartment_name;	//단지명
	private String	auto_com_txt;
	
	public KbDongAptVO() {
	}
	public KbDongAptVO(String result_cd, String result_mg, int total_count,
			String building_type, String region1_code, String region1_name,
			String region2_code, String region2_name, String region3_code,
			String region3_name, String ldong_code, String apartment,
			String apartment_name, String auto_com_txt) {
		this.result_cd = result_cd;
		this.result_mg = result_mg;
		this.total_count = total_count;
		this.building_type = building_type;
		this.region1_code = region1_code;
		this.region1_name = region1_name;
		this.region2_code = region2_code;
		this.region2_name = region2_name;
		this.region3_code = region3_code;
		this.region3_name = region3_name;
		this.ldong_code = ldong_code;
		this.apartment = apartment;
		this.apartment_name = apartment_name;
		this.auto_com_txt = auto_com_txt;
	}
	public String getResult_cd() {
		return result_cd;
	}
	public void setResult_cd(String result_cd) {
		this.result_cd = result_cd;
	}
	public String getResult_mg() {
		return result_mg;
	}
	public void setResult_mg(String result_mg) {
		this.result_mg = result_mg;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public String getBuilding_type() {
		return building_type;
	}
	public void setBuilding_type(String building_type) {
		this.building_type = building_type;
	}
	public String getRegion1_code() {
		return region1_code;
	}
	public void setRegion1_code(String region1_code) {
		this.region1_code = region1_code;
	}
	public String getRegion1_name() {
		return region1_name;
	}
	public void setRegion1_name(String region1_name) {
		this.region1_name = region1_name;
	}
	public String getRegion2_code() {
		return region2_code;
	}
	public void setRegion2_code(String region2_code) {
		this.region2_code = region2_code;
	}
	public String getRegion2_name() {
		return region2_name;
	}
	public void setRegion2_name(String region2_name) {
		this.region2_name = region2_name;
	}
	public String getRegion3_code() {
		return region3_code;
	}
	public void setRegion3_code(String region3_code) {
		this.region3_code = region3_code;
	}
	public String getRegion3_name() {
		return region3_name;
	}
	public void setRegion3_name(String region3_name) {
		this.region3_name = region3_name;
	}
	public String getLdong_code() {
		return ldong_code;
	}
	public void setLdong_code(String ldong_code) {
		this.ldong_code = ldong_code;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getApartment_name() {
		return apartment_name;
	}
	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}
	public String getAuto_com_txt() {
		return auto_com_txt;
	}
	public void setAuto_com_txt(String auto_com_txt) {
		this.auto_com_txt = auto_com_txt;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}