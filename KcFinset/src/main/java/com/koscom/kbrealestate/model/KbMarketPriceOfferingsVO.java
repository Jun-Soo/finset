package com.koscom.kbrealestate.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class KbMarketPriceOfferingsVO implements Serializable{
	private static final long serialVersionUID = -8588143292869282937L;
	@SerializedName("OFFERINGS_TYPE")
	private String offerings_type;				//매물구분
	@SerializedName("TRANSACTION_METHODD")
	private String transaction_method;			//거래
	@SerializedName("CONFIRMED_DATE")
	private String confirmed_date;				//확인일자
	@SerializedName("OFFERINGS_NAME")
	private String offerings_name;				//매물명
	@SerializedName("OFFERINGS_AREA_SIZE")
	private String offerings_area_size;			//면적
	@SerializedName("BUILDING_DONG")
	private String building_dong;				//동
	@SerializedName("BUILDING_FLOOR")
	private String builidng_floor;				//층
	@SerializedName("OFFERINGS_PRICE")
	private String offerings_price;				//매물가
	@SerializedName("TELEPHONE")
	private String telephone;					//연락처
	public String getOfferings_type() {
		return offerings_type;
	}
	public void setOfferings_type(String offerings_type) {
		this.offerings_type = offerings_type;
	}
	public String getTransaction_method() {
		return transaction_method;
	}
	public void setTransaction_method(String transaction_method) {
		this.transaction_method = transaction_method;
	}
	public String getConfirmed_date() {
		return confirmed_date;
	}
	public void setConfirmed_date(String confirmed_date) {
		this.confirmed_date = confirmed_date;
	}
	public String getOfferings_name() {
		return offerings_name;
	}
	public void setOfferings_name(String offerings_name) {
		this.offerings_name = offerings_name;
	}
	public String getOfferings_area_size() {
		return offerings_area_size;
	}
	public void setOfferings_area_size(String offerings_area_size) {
		this.offerings_area_size = offerings_area_size;
	}
	public String getBuilding_dong() {
		return building_dong;
	}
	public void setBuilding_dong(String building_dong) {
		this.building_dong = building_dong;
	}
	public String getBuilidng_floor() {
		return builidng_floor;
	}
	public void setBuilidng_floor(String builidng_floor) {
		this.builidng_floor = builidng_floor;
	}
	public String getOfferings_price() {
		return offerings_price;
	}
	public void setOfferings_price(String offerings_price) {
		this.offerings_price = offerings_price;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}