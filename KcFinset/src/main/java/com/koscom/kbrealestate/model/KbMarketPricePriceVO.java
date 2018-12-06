package com.koscom.kbrealestate.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class KbMarketPricePriceVO implements Serializable{
	private static final long serialVersionUID = 1862836225276741849L;
	@SerializedName("RENEWAL_DATE")
	private String renewal_date;					//시세갱신일
	@SerializedName("AREA_INDEX")
	private String area_index;						//면적인덱스
	@SerializedName("AREA_SIZE")
	private String area_size;						//면적
	@SerializedName("AREA_NAME")
	private String area_name;						//단지명
	@SerializedName("SALE_LOWEST_AVERAGE")
	private String sale_lowest_average;				//매매가_하위평균가
	@SerializedName("SALE_GENERAL_AVERAGE")
	private String sale_general_average;			//매매가_일반평균가
	@SerializedName("SALE_HIGHEST_AVERAGE")
	private String sale_highest_average;			//매매가_상위평균가
	@SerializedName("RENTAL_LOWEST_AVERAGE")
	private String rental_lowest_average;			//전세가_하위평균가
	@SerializedName("RENTAL_GENERAL_AVERAGE")
	private String rental_general_average;			//전세가_일반평균가
	@SerializedName("RENTAL_HIGHEST_AVERAGE")
	private String rental_highest_average;			//전세가_상위평균가
	@SerializedName("RENTAL_DEPOSIT_AMOUNT")
	private String rental_deposit_amount;			//월세가_보증금
	@SerializedName("RENTAL_PAYMENT_PERIOD")
	private String rental_payment_period;			//월세가_월세 
	@SerializedName("SALE_CHANGE_BEFORWEEK_PRICE")
	private String sale_change_beforweek_price;		//매매가_전주대비변동액
	@SerializedName("RENTAL_CHANGE_BEFORWEEK_PRICE")
	private String rental_change_beforweek_price;	//전세가_전주대비변동액
	
	
	public String getRenewal_date() {
		return renewal_date;
	}

	public void setRenewal_date(String renewal_date) {
		this.renewal_date = renewal_date;
	}

	public String getArea_index() {
		return area_index;
	}

	public void setArea_index(String area_index) {
		this.area_index = area_index;
	}

	public String getArea_size() {
		return area_size;
	}

	public void setArea_size(String area_size) {
		this.area_size = area_size;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getSale_lowest_average() {
		return sale_lowest_average;
	}

	public void setSale_lowest_average(String sale_lowest_average) {
		this.sale_lowest_average = sale_lowest_average;
	}

	public String getSale_general_average() {
		return sale_general_average;
	}

	public void setSale_general_average(String sale_general_average) {
		this.sale_general_average = sale_general_average;
	}

	public String getSale_highest_average() {
		return sale_highest_average;
	}

	public void setSale_highest_average(String sale_highest_average) {
		this.sale_highest_average = sale_highest_average;
	}

	public String getRental_lowest_average() {
		return rental_lowest_average;
	}

	public void setRental_lowest_average(String rental_lowest_average) {
		this.rental_lowest_average = rental_lowest_average;
	}

	public String getRental_general_average() {
		return rental_general_average;
	}

	public void setRental_general_average(String rental_general_average) {
		this.rental_general_average = rental_general_average;
	}

	public String getRental_highest_average() {
		return rental_highest_average;
	}

	public void setRental_highest_average(String rental_highest_average) {
		this.rental_highest_average = rental_highest_average;
	}

	public String getRental_deposit_amount() {
		return rental_deposit_amount;
	}

	public void setRental_deposit_amount(String rental_deposit_amount) {
		this.rental_deposit_amount = rental_deposit_amount;
	}

	public String getRental_payment_period() {
		return rental_payment_period;
	}

	public void setRental_payment_period(String rental_payment_period) {
		this.rental_payment_period = rental_payment_period;
	}

	public String getSale_change_beforweek_price() {
		return sale_change_beforweek_price;
	}

	public void setSale_change_beforweek_price(String sale_change_beforweek_price) {
		this.sale_change_beforweek_price = sale_change_beforweek_price;
	}

	public String getRental_change_beforweek_price() {
		return rental_change_beforweek_price;
	}

	public void setRental_change_beforweek_price(
			String rental_change_beforweek_price) {
		this.rental_change_beforweek_price = rental_change_beforweek_price;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}