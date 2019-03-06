package com.koscom.kbrealestate.model;

import java.io.Serializable;

public class KbMarketPriceComplexVO implements Serializable{
	private static final long serialVersionUID = -3502454080345315901L;
	private String TOTAL_HOUSEHOLD = "";			//총세대수 
	private String TOTAL_BUILDING = "";			//총동수 
	private String MOVE_IN_DATE = "";			//입주년월 
	private String CONSTRUCTION_COMPANY = "";			//건설업체 
	private String AREA_REGION = "";			//지번주소 
	private String AREA_STREET_NAME = "";			//도로명주소 
	private String HEATING_METHOD = "";			//난방방식 
	private String HEATING_FUEL = "";			//난방연료 
	private String TOTAL_PARKING_NUM = "";			//총주차대수 
	private String HOUSE_PARKING_NUM = "";			//세대당주차대수 
	private String VOLUME_RATIO = "";			//용적률 
	private String COVERAGE_RATIO = "";			//건폐율 
	private String FLOOR_HIGHEST = "";			//최고층 
	private String FLOOR_LOWEST = "";			//최저층 
	public String getTOTAL_HOUSEHOLD() {
		return TOTAL_HOUSEHOLD;
	}
	public void setTOTAL_HOUSEHOLD(String tOTAL_HOUSEHOLD) {
		TOTAL_HOUSEHOLD = tOTAL_HOUSEHOLD;
	}
	public String getTOTAL_BUILDING() {
		return TOTAL_BUILDING;
	}
	public void setTOTAL_BUILDING(String tOTAL_BUILDING) {
		TOTAL_BUILDING = tOTAL_BUILDING;
	}
	public String getMOVE_IN_DATE() {
		return MOVE_IN_DATE;
	}
	public void setMOVE_IN_DATE(String mOVE_IN_DATE) {
		MOVE_IN_DATE = mOVE_IN_DATE;
	}
	public String getCONSTRUCTION_COMPANY() {
		return CONSTRUCTION_COMPANY;
	}
	public void setCONSTRUCTION_COMPANY(String cONSTRUCTION_COMPANY) {
		CONSTRUCTION_COMPANY = cONSTRUCTION_COMPANY;
	}
	public String getAREA_REGION() {
		return AREA_REGION;
	}
	public void setAREA_REGION(String aREA_REGION) {
		AREA_REGION = aREA_REGION;
	}
	public String getAREA_STREET_NAME() {
		return AREA_STREET_NAME;
	}
	public void setAREA_STREET_NAME(String aREA_STREET_NAME) {
		AREA_STREET_NAME = aREA_STREET_NAME;
	}
	public String getHEATING_METHOD() {
		return HEATING_METHOD;
	}
	public void setHEATING_METHOD(String hEATING_METHOD) {
		HEATING_METHOD = hEATING_METHOD;
	}
	public String getHEATING_FUEL() {
		return HEATING_FUEL;
	}
	public void setHEATING_FUEL(String hEATING_FUEL) {
		HEATING_FUEL = hEATING_FUEL;
	}
	public String getTOTAL_PARKING_NUM() {
		return TOTAL_PARKING_NUM;
	}
	public void setTOTAL_PARKING_NUM(String tOTAL_PARKING_NUM) {
		TOTAL_PARKING_NUM = tOTAL_PARKING_NUM;
	}
	public String getHOUSE_PARKING_NUM() {
		return HOUSE_PARKING_NUM;
	}
	public void setHOUSE_PARKING_NUM(String hOUSE_PARKING_NUM) {
		HOUSE_PARKING_NUM = hOUSE_PARKING_NUM;
	}
	public String getVOLUME_RATIO() {
		return VOLUME_RATIO;
	}
	public void setVOLUME_RATIO(String vOLUME_RATIO) {
		VOLUME_RATIO = vOLUME_RATIO;
	}
	public String getCOVERAGE_RATIO() {
		return COVERAGE_RATIO;
	}
	public void setCOVERAGE_RATIO(String cOVERAGE_RATIO) {
		COVERAGE_RATIO = cOVERAGE_RATIO;
	}
	public String getFLOOR_HIGHEST() {
		return FLOOR_HIGHEST;
	}
	public void setFLOOR_HIGHEST(String fLOOR_HIGHEST) {
		FLOOR_HIGHEST = fLOOR_HIGHEST;
	}
	public String getFLOOR_LOWEST() {
		return FLOOR_LOWEST;
	}
	public void setFLOOR_LOWEST(String fLOOR_LOWEST) {
		FLOOR_LOWEST = fLOOR_LOWEST;
	}
}