package com.koscom.kbrealestate.model;

import java.io.Serializable;

public class KbMarketPriceOfferingsVO implements Serializable{
	private static final long serialVersionUID = -8588143292869282937L;
	private String OFFERINGS_TYPE = "";			//매물구분 
	private String TRANSACTION_METHOD = "";			//거래 
	private String CONFIRMED_DATE = "";			//확인일자 
	private String OFFERINGS_NAME = "";			//매물명 
	private String OFFERINGS_AREA_SIZE = "";			//면적 
	private String BUILDING_DONG = "";			//동 
	private String BUILDING_FLOOR = "";			//층 
	private String OFFERINGS_PRICE = "";			//매물가 
	private String TELEPHONE = "";			//연락처 
	public String getOFFERINGS_TYPE() {
		return OFFERINGS_TYPE;
	}
	public void setOFFERINGS_TYPE(String oFFERINGS_TYPE) {
		OFFERINGS_TYPE = oFFERINGS_TYPE;
	}
	public String getTRANSACTION_METHOD() {
		return TRANSACTION_METHOD;
	}
	public void setTRANSACTION_METHOD(String tRANSACTION_METHOD) {
		TRANSACTION_METHOD = tRANSACTION_METHOD;
	}
	public String getCONFIRMED_DATE() {
		return CONFIRMED_DATE;
	}
	public void setCONFIRMED_DATE(String cONFIRMED_DATE) {
		CONFIRMED_DATE = cONFIRMED_DATE;
	}
	public String getOFFERINGS_NAME() {
		return OFFERINGS_NAME;
	}
	public void setOFFERINGS_NAME(String oFFERINGS_NAME) {
		OFFERINGS_NAME = oFFERINGS_NAME;
	}
	public String getOFFERINGS_AREA_SIZE() {
		return OFFERINGS_AREA_SIZE;
	}
	public void setOFFERINGS_AREA_SIZE(String oFFERINGS_AREA_SIZE) {
		OFFERINGS_AREA_SIZE = oFFERINGS_AREA_SIZE;
	}
	public String getBUILDING_DONG() {
		return BUILDING_DONG;
	}
	public void setBUILDING_DONG(String bUILDING_DONG) {
		BUILDING_DONG = bUILDING_DONG;
	}
	public String getBUILDING_FLOOR() {
		return BUILDING_FLOOR;
	}
	public void setBUILDING_FLOOR(String bUILDING_FLOOR) {
		BUILDING_FLOOR = bUILDING_FLOOR;
	}
	public String getOFFERINGS_PRICE() {
		return OFFERINGS_PRICE;
	}
	public void setOFFERINGS_PRICE(String oFFERINGS_PRICE) {
		OFFERINGS_PRICE = oFFERINGS_PRICE;
	}
	public String getTELEPHONE() {
		return TELEPHONE;
	}
	public void setTELEPHONE(String tELEPHONE) {
		TELEPHONE = tELEPHONE;
	}
}