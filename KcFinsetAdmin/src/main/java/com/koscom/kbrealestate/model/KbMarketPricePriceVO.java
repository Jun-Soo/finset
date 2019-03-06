package com.koscom.kbrealestate.model;

import java.io.Serializable;

public class KbMarketPricePriceVO implements Serializable{
	private static final long serialVersionUID = 1862836225276741849L;
	private String RENEWAL_DATE = "";			//시세갱신일 
	private String AREA_INDEX = "";			//면적인덱스 
	private String AREA_SIZE = "";			//면적 
	private String AREA_NAME = "";			//단지명 
	private String SALE_LOWEST_AVERAGE = "";			//매매가_하위평균가 
	private String SALE_GENERAL_AVERAGE = "";			//매매가_일반평균가 
	private String SALE_HIGHEST_AVERAGE = "";			//매매가_상위평균가 
	private String RENTAL_LOWEST_AVERAGE = "";			//전세가_하위평균가 
	private String RENTAL_GENERAL_AVERAGE = "";			//전세가_일반평균가 
	private String RENTAL_HIGHEST_AVERAGE = "";			//전세가_상위평균가 
	private String RENTAL_DEPOSIT_AMOUNT = "";			//월세가_보증금 
	private String RENTAL_PAYMENT_PERIOD = "";			//월세가_월세 
	private String SALE_CHANGE_BEFORWEEK_PRICE = "";			//매매가_전주대비변동액
	private String RENTAL_CHANGE_BEFORWEEK_PRICE = "";			//전세가_전주대비변동액
	public String getRENEWAL_DATE() {
		return RENEWAL_DATE;
	}
	public void setRENEWAL_DATE(String rENEWAL_DATE) {
		RENEWAL_DATE = rENEWAL_DATE;
	}
	public String getAREA_INDEX() {
		return AREA_INDEX;
	}
	public void setAREA_INDEX(String aREA_INDEX) {
		AREA_INDEX = aREA_INDEX;
	}
	public String getAREA_SIZE() {
		return AREA_SIZE;
	}
	public void setAREA_SIZE(String aREA_SIZE) {
		AREA_SIZE = aREA_SIZE;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	public String getSALE_LOWEST_AVERAGE() {
		return SALE_LOWEST_AVERAGE;
	}
	public void setSALE_LOWEST_AVERAGE(String sALE_LOWEST_AVERAGE) {
		SALE_LOWEST_AVERAGE = sALE_LOWEST_AVERAGE;
	}
	public String getSALE_GENERAL_AVERAGE() {
		return SALE_GENERAL_AVERAGE;
	}
	public void setSALE_GENERAL_AVERAGE(String sALE_GENERAL_AVERAGE) {
		SALE_GENERAL_AVERAGE = sALE_GENERAL_AVERAGE;
	}
	public String getSALE_HIGHEST_AVERAGE() {
		return SALE_HIGHEST_AVERAGE;
	}
	public void setSALE_HIGHEST_AVERAGE(String sALE_HIGHEST_AVERAGE) {
		SALE_HIGHEST_AVERAGE = sALE_HIGHEST_AVERAGE;
	}
	public String getRENTAL_LOWEST_AVERAGE() {
		return RENTAL_LOWEST_AVERAGE;
	}
	public void setRENTAL_LOWEST_AVERAGE(String rENTAL_LOWEST_AVERAGE) {
		RENTAL_LOWEST_AVERAGE = rENTAL_LOWEST_AVERAGE;
	}
	public String getRENTAL_GENERAL_AVERAGE() {
		return RENTAL_GENERAL_AVERAGE;
	}
	public void setRENTAL_GENERAL_AVERAGE(String rENTAL_GENERAL_AVERAGE) {
		RENTAL_GENERAL_AVERAGE = rENTAL_GENERAL_AVERAGE;
	}
	public String getRENTAL_HIGHEST_AVERAGE() {
		return RENTAL_HIGHEST_AVERAGE;
	}
	public void setRENTAL_HIGHEST_AVERAGE(String rENTAL_HIGHEST_AVERAGE) {
		RENTAL_HIGHEST_AVERAGE = rENTAL_HIGHEST_AVERAGE;
	}
	public String getRENTAL_DEPOSIT_AMOUNT() {
		return RENTAL_DEPOSIT_AMOUNT;
	}
	public void setRENTAL_DEPOSIT_AMOUNT(String rENTAL_DEPOSIT_AMOUNT) {
		RENTAL_DEPOSIT_AMOUNT = rENTAL_DEPOSIT_AMOUNT;
	}
	public String getRENTAL_PAYMENT_PERIOD() {
		return RENTAL_PAYMENT_PERIOD;
	}
	public void setRENTAL_PAYMENT_PERIOD(String rENTAL_PAYMENT_PERIOD) {
		RENTAL_PAYMENT_PERIOD = rENTAL_PAYMENT_PERIOD;
	}
	public String getSALE_CHANGE_BEFORWEEK_PRICE() {
		return SALE_CHANGE_BEFORWEEK_PRICE;
	}
	public void setSALE_CHANGE_BEFORWEEK_PRICE(String sALE_CHANGE_BEFORWEEK_PRICE) {
		SALE_CHANGE_BEFORWEEK_PRICE = sALE_CHANGE_BEFORWEEK_PRICE;
	}
	public String getRENTAL_CHANGE_BEFORWEEK_PRICE() {
		return RENTAL_CHANGE_BEFORWEEK_PRICE;
	}
	public void setRENTAL_CHANGE_BEFORWEEK_PRICE(String rENTAL_CHANGE_BEFORWEEK_PRICE) {
		RENTAL_CHANGE_BEFORWEEK_PRICE = rENTAL_CHANGE_BEFORWEEK_PRICE;
	}
}