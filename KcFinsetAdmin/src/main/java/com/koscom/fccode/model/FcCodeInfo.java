package com.koscom.fccode.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FcCodeInfo implements Serializable{
	private static final long serialVersionUID = 4502749572364906824L;
	private String no_edoc; // 코드종류
	private String cd_fc; // 금융사코드
	private String code_group; // 코드종류
	private String code_value; // 코드값
	private String nm_code;    // 코드명
	private String etc;        // 비고
	private String yn_use;     // 사용유무
	private String seq_order;  // 우선순위
	private String yn_system_code; // 시스템 코드 구분
	private String id_frt;     // 최초작성자
	private String dt_frt;
	private String id_lst;     // 최종작성자
	private String dt_lst;
	
	private String item_tag; // 코스콤표준코드
	private String item_tag_value; // 코드값
	
	private String type_txrx;//송수신여부
	private String type_data;//데이터유형
	private String nm_col;	//DB필드명
	private String field_length;
	private String parent_code_group; //부모코드명
	private String item_repeat_tag; // 반복부모코드
	private String item_repeat_tag_value; // 코드값
	private String type_attr;//속성
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getSeq_order() {
		return seq_order;
	}
	public void setSeq_order(String seq_order) {
		this.seq_order = seq_order;
	}
	public String getYn_system_code() {
		return yn_system_code;
	}
	public void setYn_system_code(String yn_system_code) {
		this.yn_system_code = yn_system_code;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	public String getType_txrx() {
		return type_txrx;
	}
	public void setType_txrx(String type_txrx) {
		this.type_txrx = type_txrx;
	}
	public String getType_data() {
		return type_data;
	}
	public void setType_data(String type_data) {
		this.type_data = type_data;
	}
	public String getNm_col() {
		return nm_col;
	}
	public void setNm_col(String nm_col) {
		this.nm_col = nm_col;
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
	public String getField_length() {
		return field_length;
	}
	public void setField_length(String field_length) {
		this.field_length = field_length;
	}
	public String getParent_code_group() {
		return parent_code_group;
	}
	public void setParent_code_group(String parent_code_group) {
		this.parent_code_group = parent_code_group;
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
	public String getType_attr() {
		return type_attr;
	}
	public void setType_attr(String type_attr) {
		this.type_attr = type_attr;
	}
	
}
