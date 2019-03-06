package com.koscom.fccode.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class FcCodeForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = 4172735039536716671L;
	private String cd_fc; // 금융사코드
	private String no_edoc; // 코드종류
	
	private String code_group;
	private String code_value;
	private String nm_code;
	private String yn_code_group;
	private String type_txrx;
	
	private String id_frt;
	
	private String item_tag; // 코스콤표준코드
	private String item_tag_value; // 코드값

	private String item_repeat_tag; // 반복부모코드
	private String item_repeat_tag_value; // 코드값
	
	public String getItem_tag() {
		return item_tag;
	}
	public void setItem_tag(String item_tag) {
		this.item_tag = item_tag;
	}
	public String getItem_tag_value() {
		return item_tag_value;
	}
	public void setItem_tag_value(String item_tag_value) {
		this.item_tag_value = item_tag_value;
	}
	public String getCode_group() {
		return code_group;
	}
	public void setCode_group(String code_group) {
		this.code_group = code_group;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getNm_code() {
		return nm_code;
	}
	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}
	public String getYn_code_group() {
		return yn_code_group;
	}
	public void setYn_code_group(String yn_code_group) {
		this.yn_code_group = yn_code_group;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getNo_edoc() {
		return no_edoc;
	}
	public void setNo_edoc(String no_edoc) {
		this.no_edoc = no_edoc;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getType_txrx() {
		return type_txrx;
	}
	public void setType_txrx(String type_txrx) {
		this.type_txrx = type_txrx;
	}
	public String getItem_repeat_tag() {
		return item_repeat_tag;
	}
	public String getItem_repeat_tag_value() {
		return item_repeat_tag_value;
	}
	public void setItem_repeat_tag(String item_repeat_tag) {
		this.item_repeat_tag = item_repeat_tag;
	}
	public void setItem_repeat_tag_value(String item_repeat_tag_value) {
		this.item_repeat_tag_value = item_repeat_tag_value;
	}
	
}
