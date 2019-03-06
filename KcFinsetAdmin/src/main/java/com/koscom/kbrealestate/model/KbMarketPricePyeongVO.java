package com.koscom.kbrealestate.model;

import java.io.Serializable;

public class KbMarketPricePyeongVO implements Serializable{
	private static final long serialVersionUID = -5422095412960883697L;
	private String FLOOR_PLAN_AREA_SIZE = "";			//면적 
	private String FLOOR_PLAN_ROOM_NUM = "";			//방수 
	private String FLOOR_PLAN_BATHROOM_NUM = "";			//욕실수 
	private String FLOOR_PLAN_AREA_SUPPLY = "";			//공급면적 
	private String FLOOR_PLAN_AREA_DEDICATED = "";			//전용면적 
	private String FLOOR_PLAN_SELL_NUM = "";			//분양세대수 
	private String FLOOR_PLAN_ENTRANCE_STRUCTURE = "";			//현관구조 
	public String getFLOOR_PLAN_AREA_SIZE() {
		return FLOOR_PLAN_AREA_SIZE;
	}
	public void setFLOOR_PLAN_AREA_SIZE(String fLOOR_PLAN_AREA_SIZE) {
		FLOOR_PLAN_AREA_SIZE = fLOOR_PLAN_AREA_SIZE;
	}
	public String getFLOOR_PLAN_ROOM_NUM() {
		return FLOOR_PLAN_ROOM_NUM;
	}
	public void setFLOOR_PLAN_ROOM_NUM(String fLOOR_PLAN_ROOM_NUM) {
		FLOOR_PLAN_ROOM_NUM = fLOOR_PLAN_ROOM_NUM;
	}
	public String getFLOOR_PLAN_BATHROOM_NUM() {
		return FLOOR_PLAN_BATHROOM_NUM;
	}
	public void setFLOOR_PLAN_BATHROOM_NUM(String fLOOR_PLAN_BATHROOM_NUM) {
		FLOOR_PLAN_BATHROOM_NUM = fLOOR_PLAN_BATHROOM_NUM;
	}
	public String getFLOOR_PLAN_AREA_SUPPLY() {
		return FLOOR_PLAN_AREA_SUPPLY;
	}
	public void setFLOOR_PLAN_AREA_SUPPLY(String fLOOR_PLAN_AREA_SUPPLY) {
		FLOOR_PLAN_AREA_SUPPLY = fLOOR_PLAN_AREA_SUPPLY;
	}
	public String getFLOOR_PLAN_AREA_DEDICATED() {
		return FLOOR_PLAN_AREA_DEDICATED;
	}
	public void setFLOOR_PLAN_AREA_DEDICATED(String fLOOR_PLAN_AREA_DEDICATED) {
		FLOOR_PLAN_AREA_DEDICATED = fLOOR_PLAN_AREA_DEDICATED;
	}
	public String getFLOOR_PLAN_SELL_NUM() {
		return FLOOR_PLAN_SELL_NUM;
	}
	public void setFLOOR_PLAN_SELL_NUM(String fLOOR_PLAN_SELL_NUM) {
		FLOOR_PLAN_SELL_NUM = fLOOR_PLAN_SELL_NUM;
	}
	public String getFLOOR_PLAN_ENTRANCE_STRUCTURE() {
		return FLOOR_PLAN_ENTRANCE_STRUCTURE;
	}
	public void setFLOOR_PLAN_ENTRANCE_STRUCTURE(String fLOOR_PLAN_ENTRANCE_STRUCTURE) {
		FLOOR_PLAN_ENTRANCE_STRUCTURE = fLOOR_PLAN_ENTRANCE_STRUCTURE;
	}
}