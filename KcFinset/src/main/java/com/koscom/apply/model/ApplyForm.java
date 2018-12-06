package com.koscom.apply.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;
import com.koscom.env.model.CodeInfo;

public class ApplyForm extends SearchForm {

	private static final long serialVersionUID = 8478046251378121121L;
	
	private String no_apply;
	private String no_prepare;
	private String cd_apply_doc_box;	// 서류함구분
	private String cd_apply_doc;	// 접수 서류함구분
	private String cd_advertisement;// 광고매체
	private String cd_goods;		// 상품명
	private String cd_goods_type;	// 상품구분
	private String cd_fc;	// 금융사
	private String id_prepare;		// 담당자
	private String id_prepare_srch;	// 담당자별 검색
	private List<CodeInfo> fields;	// 엑셀 컬럼
	private List<CodeInfo> doc_fields;	// 접수리스트 상태 필드
	private String id_agency;		// 매체사 id
	private String id_agency_user;	// 대리점 담당자
	private String cd_agency_path;	// 대리점 유입경로
	private String yn_agency;		// 대리점여부
	private String cd_apply_doc_box1;	// 서류함구분
	private String cd_apply_doc_box2;	// 서류함구분
	private String cd_apply_doc_box3;	// 서류함구분
	private String no_person;			// 고객번호
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
	public String getCd_apply_doc_box1() {
		return cd_apply_doc_box1;
	}


	public void setCd_apply_doc_box1(String cd_apply_doc_box1) {
		this.cd_apply_doc_box1 = cd_apply_doc_box1;
	}


	public String getCd_apply_doc_box2() {
		return cd_apply_doc_box2;
	}


	public void setCd_apply_doc_box2(String cd_apply_doc_box2) {
		this.cd_apply_doc_box2 = cd_apply_doc_box2;
	}


	public String getCd_apply_doc_box3() {
		return cd_apply_doc_box3;
	}


	public void setCd_apply_doc_box3(String cd_apply_doc_box3) {
		this.cd_apply_doc_box3 = cd_apply_doc_box3;
	}


	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getCd_apply_doc_box() {
		return cd_apply_doc_box;
	}

	public void setCd_apply_doc_box(String cd_apply_doc_box) {
		this.cd_apply_doc_box = cd_apply_doc_box;
	}

	public String getCd_apply_doc() {
		return cd_apply_doc;
	}

	public void setCd_apply_doc(String cd_apply_doc) {
		this.cd_apply_doc = cd_apply_doc;
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

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getId_prepare_srch() {
		return id_prepare_srch;
	}

	public void setId_prepare_srch(String id_prepare_srch) {
		this.id_prepare_srch = id_prepare_srch;
	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	
	public String getId_prepare() {
		return id_prepare;
	}

	public void setId_prepare(String id_prepare) {
		this.id_prepare = id_prepare;
	}

	public List<CodeInfo> getFields() {
		return fields;
	}

	public void setFields(List<CodeInfo> fields) {
		this.fields = fields;
	}

	public List<CodeInfo> getDoc_fields() {
		return doc_fields;
	}

	public void setDoc_fields(List<CodeInfo> doc_fields) {
		this.doc_fields = doc_fields;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
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

	public String getYn_agency() {
		return yn_agency;
	}

	public void setYn_agency(String yn_agency) {
		this.yn_agency = yn_agency;
	}


	public String getNo_person() {
		return no_person;
	}


	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	
}
