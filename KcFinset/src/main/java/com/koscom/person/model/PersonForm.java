package com.koscom.person.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.comm.model.SearchForm;
import com.koscom.env.model.CodeInfo;

public class PersonForm extends SearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1058836774327541178L;
	
	private	String no_person;
	private	String nm_person;
	private	String ymd_birth;
	private	String c1_gender;
	private	String hp;
	private	String division;
	private	String no_prepare;
	private	String yn_grt;				  	// 신청인 찾기인지 보증인 찾기인지를 구분
	private	String yn_modal;    			// 고객정보 수정 시 팝업과 모달 구분
	private List<CodeInfo> fields;			// 엑셀 컬럼
	private String yn_personal;				// 고객 정보수정이력 전체/해당고객 구분
	private String id_agency;			 	// 매체사 id
	
	private	String id_social;
	private	String comp_social;
	private	String frm_nm;	//고객찾기에서 쓸 form이름
	private String txt_detail;
	private String yn_reload;
	
	private String amt_added_loan;

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getYmd_birth() {
		return ymd_birth;
	}

	public void setYmd_birth(String ymd_birth) {
		this.ymd_birth = ymd_birth;
	}

	public String getC1_gender() {
		return c1_gender;
	}

	public void setC1_gender(String c1_gender) {
		this.c1_gender = c1_gender;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getYn_grt() {
		return yn_grt;
	}

	public void setYn_grt(String yn_grt) {
		this.yn_grt = yn_grt;
	}

	public String getYn_modal() {
		return yn_modal;
	}

	public void setYn_modal(String yn_modal) {
		this.yn_modal = yn_modal;
	}

	public List<CodeInfo> getFields() {
		return fields;
	}

	public void setFields(List<CodeInfo> fields) {
		this.fields = fields;
	}

	public String getYn_personal() {
		return yn_personal;
	}

	public void setYn_personal(String yn_personal) {
		this.yn_personal = yn_personal;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getId_social() {
		return id_social;
	}

	public void setId_social(String id_social) {
		this.id_social = id_social;
	}

	public String getComp_social() {
		return comp_social;
	}

	public void setComp_social(String comp_social) {
		this.comp_social = comp_social;
	}

	public String getFrm_nm() {
		return frm_nm;
	}

	public void setFrm_nm(String frm_nm) {
		this.frm_nm = frm_nm;
	}

	public String getTxt_detail() {
		return txt_detail;
	}

	public void setTxt_detail(String txt_detail) {
		this.txt_detail = txt_detail;
	}

	public String getYn_reload() {
		return yn_reload;
	}

	public void setYn_reload(String yn_reload) {
		this.yn_reload = yn_reload;
	}

	public String getAmt_added_loan() {
		return amt_added_loan;
	}

	public void setAmt_added_loan(String amt_added_loan) {
		this.amt_added_loan = amt_added_loan;
	}
}