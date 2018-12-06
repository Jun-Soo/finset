package com.koscom.kbrealestate.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class KbMarketPriceComplexVO implements Serializable{
	private static final long serialVersionUID = -3502454080345315901L;
	
	@SerializedName("TOTAL_HOUSEHOLD")
	private String total_household;			//총세대수
	@SerializedName("TOTAL_BUILDING")
	private String total_building;			//총동수
	@SerializedName("MOVE_IN_DATE")
	private String move_in_date;			//입주년월
	@SerializedName("CONSTRUCTION_COMPANY")
	private String construction_company;	//건설업체
	@SerializedName("AREA_REGION")
	private String area_region;				//지번주소
	@SerializedName("AREA_STREET_NAME")
	private String area_street_name;		//도로명주소
	@SerializedName("HEATING_METHOD")
	private String heating_method;			//난방방식
	@SerializedName("HEATING_FUEL")
	private String heating_fuel;			//난방연료
	@SerializedName("TOTAL_PARKING_NUM")
	private String total_parking_num;		//총주차대수
	@SerializedName("HOUSE_PARKING_NUM")
	private String house_parking_num;		//세대당주차대수
	@SerializedName("VOLUME_RATIO")
	private String volume_ratio;			//용적률
	@SerializedName("COVERAGE_RATIO")
	private String coverage_ratio;			//건폐율
	@SerializedName("FLOOR_HIGHEST")
	private String floor_highest;			//최고층
	@SerializedName("FLOOR_LOWEST")
	private String floor_lowest;			//최저층 
	
	public String getTotal_household() {
		return total_household;
	}
	public void setTotal_household(String total_household) {
		this.total_household = total_household;
	}
	public String getTotal_building() {
		return total_building;
	}
	public void setTotal_building(String total_building) {
		this.total_building = total_building;
	}
	public String getMove_in_date() {
		return move_in_date;
	}
	public void setMove_in_date(String move_in_date) {
		this.move_in_date = move_in_date;
	}
	public String getConstruction_company() {
		return construction_company;
	}
	public void setConstruction_company(String construction_company) {
		this.construction_company = construction_company;
	}
	public String getArea_region() {
		return area_region;
	}
	public void setArea_region(String area_region) {
		this.area_region = area_region;
	}
	public String getArea_street_name() {
		return area_street_name;
	}
	public void setArea_street_name(String area_street_name) {
		this.area_street_name = area_street_name;
	}
	public String getHeating_method() {
		return heating_method;
	}
	public void setHeating_method(String heating_method) {
		this.heating_method = heating_method;
	}
	public String getHeating_fuel() {
		return heating_fuel;
	}
	public void setHeating_fuel(String heating_fuel) {
		this.heating_fuel = heating_fuel;
	}
	public String getTotal_parking_num() {
		return total_parking_num;
	}
	public void setTotal_parking_num(String total_parking_num) {
		this.total_parking_num = total_parking_num;
	}
	public String getHouse_parking_num() {
		return house_parking_num;
	}
	public void setHouse_parking_num(String house_parking_num) {
		this.house_parking_num = house_parking_num;
	}
	public String getVolume_ratio() {
		return volume_ratio;
	}
	public void setVolume_ratio(String volume_ratio) {
		this.volume_ratio = volume_ratio;
	}
	public String getCoverage_ratio() {
		return coverage_ratio;
	}
	public void setCoverage_ratio(String coverage_ratio) {
		this.coverage_ratio = coverage_ratio;
	}
	public String getFloor_highest() {
		return floor_highest;
	}
	public void setFloor_highest(String floor_highest) {
		this.floor_highest = floor_highest;
	}
	public String getFloor_lowest() {
		return floor_lowest;
	}
	public void setFloor_lowest(String floor_lowest) {
		this.floor_lowest = floor_lowest;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}