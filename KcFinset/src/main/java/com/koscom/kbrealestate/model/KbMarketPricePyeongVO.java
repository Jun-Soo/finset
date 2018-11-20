package com.koscom.kbrealestate.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class KbMarketPricePyeongVO implements Serializable{
	private static final long serialVersionUID = -5422095412960883697L;
	@SerializedName("FLOOR_PLAN_AREA_SIZE")
	private String floor_plan_area_size;			//면적
	@SerializedName("FLOOR_PLAN_ROOM_NUM")
	private String floor_plan_room_num;				//방수
	@SerializedName("FLOOR_PLAN_BATHROOM_NUM")
	private String floor_plan_bathroom_num;			//욕실수
	@SerializedName("FLOOR_PLAN_AREA_SUPPLY")
	private String floor_plan_area_supply;			//공급면적
	@SerializedName("FLOOR_PLAN_AREA_DEDICATED")
	private String floor_plan_area_dedicated;		//전용면적
	@SerializedName("FLOOR_PLAN_SELL_NUM")
	private String floor_plan_sell_num;				//분양세대수
	@SerializedName("FLOOR_PLAN_ENTRANCE_STRUCTURE")
	private String floor_plan_entrance_structure;	//현관구조 
	
	public String getFloor_plan_area_size() {
		return floor_plan_area_size;
	}
	public void setFloor_plan_area_size(String floor_plan_area_size) {
		this.floor_plan_area_size = floor_plan_area_size;
	}
	public String getFloor_plan_room_num() {
		return floor_plan_room_num;
	}
	public void setFloor_plan_room_num(String floor_plan_room_num) {
		this.floor_plan_room_num = floor_plan_room_num;
	}
	public String getFloor_plan_bathroom_num() {
		return floor_plan_bathroom_num;
	}
	public void setFloor_plan_bathroom_num(String floor_plan_bathroom_num) {
		this.floor_plan_bathroom_num = floor_plan_bathroom_num;
	}
	public String getFloor_plan_area_supply() {
		return floor_plan_area_supply;
	}
	public void setFloor_plan_area_supply(String floor_plan_area_supply) {
		this.floor_plan_area_supply = floor_plan_area_supply;
	}
	public String getFloor_plan_area_dedicated() {
		return floor_plan_area_dedicated;
	}
	public void setFloor_plan_area_dedicated(String floor_plan_area_dedicated) {
		this.floor_plan_area_dedicated = floor_plan_area_dedicated;
	}
	public String getFloor_plan_sell_num() {
		return floor_plan_sell_num;
	}
	public void setFloor_plan_sell_num(String floor_plan_sell_num) {
		this.floor_plan_sell_num = floor_plan_sell_num;
	}
	public String getFloor_plan_entrance_structure() {
		return floor_plan_entrance_structure;
	}
	public void setFloor_plan_entrance_structure(
			String floor_plan_entrance_structure) {
		this.floor_plan_entrance_structure = floor_plan_entrance_structure;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}