package com.koscom.kbrealestate.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class KbRealEstateForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = -1868786104179083571L;
	private String building_type;	//1 : 아파트, 2 : 오피스텔
	private String region1_code;	//대지역코드
	private String region2_code;	//중지역코드
	private String region3_code;	//소지역코드
	private String apartment;		//아파트코드-물건식별자
	private String price_type;
	
	public KbRealEstateForm() {
	}
	public KbRealEstateForm(String building_type, String region1_code,
			String region2_code, String region3_code, String apartment,
			String price_type) {
		this.building_type = building_type;
		this.region1_code = region1_code;
		this.region2_code = region2_code;
		this.region3_code = region3_code;
		this.apartment = apartment;
		this.price_type = price_type;
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
	public String getRegion2_code() {
		return region2_code;
	}
	public void setRegion2_code(String region2_code) {
		this.region2_code = region2_code;
	}
	public String getRegion3_code() {
		return region3_code;
	}
	public void setRegion3_code(String region3_code) {
		this.region3_code = region3_code;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getPrice_type() {
		return price_type;
	}
	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
