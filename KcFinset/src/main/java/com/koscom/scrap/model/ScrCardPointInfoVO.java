package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrCardPointInfoVO implements Serializable{
	private static final long serialVersionUID = 6288744685044631306L;
	protected String no_person= "";				//회원고유번호
	protected String cd_fc= "";					//금융사코드
	@SerializedName("TYPE_POINT")
	protected String type_point= "";			//포인트구분
	@SerializedName("HOLD_POINT")
	protected String hold_point= "";			//보유포인트
	protected String dt_frt= "";				//최초입력시간
	protected String dt_lst= "";				//최종수정시간
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getType_point() {
		return type_point;
	}
	public void setType_point(String type_point) {
		this.type_point = type_point;
	}
	public String getHold_point() {
		return hold_point;
	}
	public void setHold_point(String hold_point) {
		this.hold_point = hold_point;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
}
