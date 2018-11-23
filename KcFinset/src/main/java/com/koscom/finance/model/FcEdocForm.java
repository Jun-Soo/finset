package com.koscom.finance.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class FcEdocForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6551965408202260406L;
	
	private String field_cd;        //전문필드코드
	private String field_nm;        //전문필드명
	private String field_len;       //전문필드길이
	private String field_tag;       //전문필드태그
	private String field_type;      //전문필드타입
	private String field_db_nm;     //전문필드DB명
	private String repeat_yn;       //반복여부
	private String field_type_attr; //전문필드데이터타입 //AN/N
	
	public String getField_type_attr() {
		return field_type_attr;
	}

	public void setField_type_attr(String field_type_attr) {
		this.field_type_attr = field_type_attr;
	}

	public String getField_cd() {
		return field_cd;
	}

	public void setField_cd(String field_cd) {
		this.field_cd = field_cd;
	}

	public String getField_nm() {
		return field_nm;
	}

	public void setField_nm(String field_nm) {
		this.field_nm = field_nm;
	}

	public String getField_len() {
		return field_len;
	}

	public void setField_len(String field_len) {
		this.field_len = field_len;
	}
	
	public String getField_tag(){
		return field_tag;
	}
	
	public void setField_tag(String field_tag){
		this.field_tag = field_tag;
	}

	public String getField_type() {
		return field_type;
	}

	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	
	public String getField_db_nm() {
		return field_db_nm;
	}

	public void setField_db_nm(String field_db_nm) {
		this.field_db_nm = field_db_nm;
	}
	
	public String getRepeat_yn() {
		return repeat_yn;
	}

	public void setRepeat_yn(String repeat_yn) {
		this.repeat_yn = repeat_yn;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}