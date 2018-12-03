package com.koscom.comm.model;

import java.io.Serializable;

import com.koscom.util.Pagination;



public class SearchForm extends Pagination implements Serializable{
	
	private static final long serialVersionUID = -3141510500148685235L;
	
	protected String ymd_basic;					// 기준일
	private String sel_dt_kind;					// 일자구분
	private String txt_dt_from;					// 일자 from
	private String txt_dt_to;					// 일자 to
	private String sel_dt_kind1;				// 일자구분1
	private String txt_dt_from1;				// 일자 from1
	private String txt_dt_to1;					// 일자 to1
	private String sel_section;					// 구간검색
	private String txt_section_from;			// 구간검색 from
	private String txt_section_to;				// 구간검색 to
	private String sel_section1;				// 구간검색1
	private String txt_section_from1;			// 구간검색 from1
	private String txt_section_to1;				// 구간검색 to1
	private String sel_section2;				// 구간검색2
	private String txt_section_from2;			// 구간검색 from2
	private String txt_section_to2;				// 구간검색 to2
	private String sel_detail;					// 상세검색 항목 		
	private String txt_detail;					// 상세검색 키워드
	
	private String sel_order_col;				// 정렬조건
	private String sel_order_asc;				// 정렬
	private String sel_order_col1;				// 정렬조건1
	private String sel_order_asc1;				// 정렬1
	private String sel_order_col2;				// 정렬조건2
	private String sel_order_asc2;				// 정렬2
	private String sel_order_col3;				// 정렬조건3
	private String sel_order_asc3;				// 정렬3
	
	public String getYmd_basic() {
		return ymd_basic;
	}
	public void setYmd_basic(String ymd_basic) {
		this.ymd_basic = ymd_basic;
	}
	
	public String getTxt_dt_from() {
		return txt_dt_from;
	}
	public void setTxt_dt_from(String txt_dt_from) {
		this.txt_dt_from = txt_dt_from;
	}
	public String getTxt_dt_to() {
		return txt_dt_to;
	}
	public void setTxt_dt_to(String txt_dt_to) {
		this.txt_dt_to = txt_dt_to;
	}
	
	public String getTxt_dt_from1() {
		return txt_dt_from1;
	}
	public void setTxt_dt_from1(String txt_dt_from1) {
		this.txt_dt_from1 = txt_dt_from1;
	}
	public String getTxt_dt_to1() {
		return txt_dt_to1;
	}
	public void setTxt_dt_to1(String txt_dt_to1) {
		this.txt_dt_to1 = txt_dt_to1;
	}
	public String getSel_section() {
		return sel_section;
	}
	public void setSel_section(String sel_section) {
		this.sel_section = sel_section;
	}
	public String getTxt_section_from() {
		return txt_section_from;
	}
	public void setTxt_section_from(String txt_section_from) {
		this.txt_section_from = txt_section_from;
	}
	public String getTxt_section_to() {
		return txt_section_to;
	}
	public void setTxt_section_to(String txt_section_to) {
		this.txt_section_to = txt_section_to;
	}
	public String getSel_section1() {
		return sel_section1;
	}
	public void setSel_section1(String sel_section1) {
		this.sel_section1 = sel_section1;
	}
	public String getTxt_section_from1() {
		return txt_section_from1;
	}
	public void setTxt_section_from1(String txt_section_from1) {
		this.txt_section_from1 = txt_section_from1;
	}
	public String getTxt_section_to1() {
		return txt_section_to1;
	}
	public void setTxt_section_to1(String txt_section_to1) {
		this.txt_section_to1 = txt_section_to1;
	}
	public String getSel_section2() {
		return sel_section2;
	}
	public void setSel_section2(String sel_section2) {
		this.sel_section2 = sel_section2;
	}
	public String getTxt_section_from2() {
		return txt_section_from2;
	}
	public void setTxt_section_from2(String txt_section_from2) {
		this.txt_section_from2 = txt_section_from2;
	}
	public String getTxt_section_to2() {
		return txt_section_to2;
	}
	public void setTxt_section_to2(String txt_section_to2) {
		this.txt_section_to2 = txt_section_to2;
	}
	public String getSel_detail() {
		return sel_detail;
	}
	public void setSel_detail(String sel_detail) {
		this.sel_detail = sel_detail;
	}
	public String getTxt_detail() {
		return txt_detail;
	}
	public void setTxt_detail(String txt_detail) {
		this.txt_detail = txt_detail;
	}
	
	public String getSel_order_col() {
		return sel_order_col;
	}
	public void setSel_order_col(String sel_order_col) {
		this.sel_order_col = sel_order_col;
	}
	public String getSel_order_asc() {
		return sel_order_asc;
	}
	public void setSel_order_asc(String sel_order_asc) {
		this.sel_order_asc = sel_order_asc;
	}
	public String getSel_order_col1() {
		return sel_order_col1;
	}
	public void setSel_order_col1(String sel_order_col1) {
		this.sel_order_col1 = sel_order_col1;
	}
	public String getSel_order_asc1() {
		return sel_order_asc1;
	}
	public void setSel_order_asc1(String sel_order_asc1) {
		this.sel_order_asc1 = sel_order_asc1;
	}
	public String getSel_order_col2() {
		return sel_order_col2;
	}
	public void setSel_order_col2(String sel_order_col2) {
		this.sel_order_col2 = sel_order_col2;
	}
	public String getSel_order_asc2() {
		return sel_order_asc2;
	}
	public void setSel_order_asc2(String sel_order_asc2) {
		this.sel_order_asc2 = sel_order_asc2;
	}
	public String getSel_order_col3() {
		return sel_order_col3;
	}
	public void setSel_order_col3(String sel_order_col3) {
		this.sel_order_col3 = sel_order_col3;
	}
	public String getSel_order_asc3() {
		return sel_order_asc3;
	}
	public void setSel_order_asc3(String sel_order_asc3) {
		this.sel_order_asc3 = sel_order_asc3;
	}
	public String getSel_dt_kind() {
		return sel_dt_kind;
	}
	public void setSel_dt_kind(String sel_dt_kind) {
		this.sel_dt_kind = sel_dt_kind;
	}
	public String getSel_dt_kind1() {
		return sel_dt_kind1;
	}
	public void setSel_dt_kind1(String sel_dt_kind1) {
		this.sel_dt_kind1 = sel_dt_kind1;
	}
	
	
}
