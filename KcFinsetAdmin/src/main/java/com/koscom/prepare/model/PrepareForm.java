package com.koscom.prepare.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;
import com.koscom.env.model.CodeInfo;

public class PrepareForm extends SearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4784325137928402303L;

	private String cd_prepare_doc;			// 사전접수 서류함구분
	private String cd_prepare_class;	 	// 사전접수 상세구분
	private String no_prepare;				// 사전접수번호
	private String no_person;				// 개인번호
	private String cd_advertisement;   		// 광고매체
	private String cd_goods_type;			// 상품구분
	private String cd_prepare_doc_box;	 	// 서류함 검색
	private String cd_prepare_class_srch_20;// 상담사유 검색
	private String cd_reject_cause;			// 접수불가
	private String cd_ph_status;			// 휴대폰상태
	private String yn_approval;				// 승인여부
	private String id_prepare;				// 담당자
	private String id_prepare_srch;			// 담당자별 검색
	private String cd_apply_class;			// 접수상태
	private String no_apply;
	private String[] cd_goods_arr;			// 상품선택
	private List<CodeInfo> fields;			// 엑셀 컬럼
	private List<CodeInfo> class_fields;	// 사전접수 상담 상세
	private String cd_fc;			// 금융사
	private String etc_counsel;				// 상담메모
	private String id_agency;				// 매체사 id
	private String id_frt;					// 등록자
	private String id_agency_user;			// 대리점 담당자
	private String cd_agency_path;			// 대리점 유입경로
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getCd_prepare_doc() {
		return cd_prepare_doc;
	}

	public void setCd_prepare_doc(String cd_prepare_doc) {
		this.cd_prepare_doc = cd_prepare_doc;
	}
	
	public String getCd_prepare_class() {
		return cd_prepare_class;
	}

	public void setCd_prepare_class(String cd_prepare_class) {
		this.cd_prepare_class = cd_prepare_class;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getCd_advertisement() {
		return cd_advertisement;
	}

	public void setCd_advertisement(String cd_advertisement) {
		this.cd_advertisement = cd_advertisement;
	}

	public String getCd_goods_type() {
		return cd_goods_type;
	}

	public void setCd_goods_type(String cd_goods_type) {
		this.cd_goods_type = cd_goods_type;
	}
	
	public String getCd_prepare_doc_box() {
		return cd_prepare_doc_box;
	}

	public void setCd_prepare_doc_box(String cd_prepare_doc_box) {
		this.cd_prepare_doc_box = cd_prepare_doc_box;
	}

	public String getCd_prepare_class_srch_20() {
		return cd_prepare_class_srch_20;
	}

	public void setCd_prepare_class_srch_20(String cd_prepare_class_srch_20) {
		this.cd_prepare_class_srch_20 = cd_prepare_class_srch_20;
	}
	
	public String getCd_reject_cause() {
		return cd_reject_cause;
	}

	public void setCd_reject_cause(String cd_reject_cause) {
		this.cd_reject_cause = cd_reject_cause;
	}

	public String getCd_ph_status() {
		return cd_ph_status;
	}

	public void setCd_ph_status(String cd_ph_status) {
		this.cd_ph_status = cd_ph_status;
	}
	
	public String getYn_approval() {
		return yn_approval;
	}

	public void setYn_approval(String yn_approval) {
		this.yn_approval = yn_approval;
	}

	public String getId_prepare() {
		return id_prepare;
	}

	public void setId_prepare(String id_prepare) {
		this.id_prepare = id_prepare;
	}

	public String getId_prepare_srch() {
		return id_prepare_srch;
	}

	public void setId_prepare_srch(String id_prepare_srch) {
		this.id_prepare_srch = id_prepare_srch;
	}
	
	public String getCd_apply_class() {
		return cd_apply_class;
	}

	public void setCd_apply_class(String cd_apply_class) {
		this.cd_apply_class = cd_apply_class;
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}
	
	public String[] getCd_goods_arr() {
		return cd_goods_arr;
	}

	public void setCd_goods_arr(String[] cd_goods_arr) {
		this.cd_goods_arr = cd_goods_arr;
	}

	public List<CodeInfo> getFields() {
		return fields;
	}

	public void setFields(List<CodeInfo> fields) {
		this.fields = fields;
	}

	public List<CodeInfo> getClass_fields() {
		return class_fields;
	}

	public void setClass_fields(List<CodeInfo> class_fields) {
		this.class_fields = class_fields;
	}
	
	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	

	public String getEtc_counsel() {
		return etc_counsel;
	}

	public void setEtc_counsel(String etc_counsel) {
		this.etc_counsel = etc_counsel;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getId_agency_user() {
		return id_agency_user;
	}

	public void setId_agency_user(String id_agency_user) {
		this.id_agency_user = id_agency_user;
	}

	public String getCd_agency_path() {
		return cd_agency_path;
	}

	public void setCd_agency_path(String cd_agency_path) {
		this.cd_agency_path = cd_agency_path;
	}
	
	
}
