package com.koscom.stdcode.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 공통 코드 모델입니다.
 * @author dhkim
 *
 */
public class StdCodeInfo implements Serializable{
	
	private static final long serialVersionUID = -5496262475055313574L;
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
	
	private String type_txrx;//송수신여부
	private String type_data;//데이터유형
	private String nm_col;	//DB필드명
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
	
}
