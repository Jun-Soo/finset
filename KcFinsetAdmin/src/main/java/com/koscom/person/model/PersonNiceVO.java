package com.koscom.person.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonInfo;
import com.koscom.domain.PersonNiceInfo;
import com.koscom.env.model.CodeInfo;
import com.koscom.util.StringUtil;

public class PersonNiceVO extends PersonNiceInfo implements Serializable {

	private static final long serialVersionUID = 2028411512814914078L;
	private String str_sort;
	private String field_name;
	private String field_value;
	private String no_prepare;
	private String cd_prepare_doc_box;
	private String ymd_frt;
	private String amt_apply;
	private String amt_approval;
	private String cnt_apply;
	private String cnt_approval;
	private String id_prepare;	
	private String cd_prepare_class;
	private String cd_reject_cause;
	private List<CodeInfo> fields;			// 개인정보 수정 컬럼
	private String yn_grt;					// 보증인 여부
	private String id_agency;				// 제휴사 id
	private	String yn_agency;				// 대리점 유무
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getStr_sort() {
		return str_sort;
	}

	public void setStr_sort(String str_sort) {
		this.str_sort = str_sort;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_value() {
		return field_value;
	}

	public void setField_value(String field_value) {
		this.field_value = field_value;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getCd_prepare_doc_box() {
		return cd_prepare_doc_box;
	}

	public void setCd_prepare_doc_box(String cd_prepare_doc_box) {
		this.cd_prepare_doc_box = cd_prepare_doc_box;
	}

	public String getYmd_frt() {
		return ymd_frt;
	}

	public void setYmd_frt(String ymd_frt) {
		this.ymd_frt = ymd_frt;
	}

	public String getAmt_apply() {
		return amt_apply;
	}

	public void setAmt_apply(String amt_apply) {
		this.amt_apply = amt_apply;
	}

	public String getAmt_approval() {
		return amt_approval;
	}

	public void setAmt_approval(String amt_approval) {
		this.amt_approval = amt_approval;
	}

	public String getCnt_apply() {
		return cnt_apply;
	}

	public void setCnt_apply(String cnt_apply) {
		this.cnt_apply = cnt_apply;
	}

	public String getCnt_approval() {
		return cnt_approval;
	}

	public void setCnt_approval(String cnt_approval) {
		this.cnt_approval = cnt_approval;
	}

	public String getId_prepare() {
		return id_prepare;
	}

	public void setId_prepare(String id_prepare) {
		this.id_prepare = id_prepare;
	}

	public String getCd_prepare_class() {
		return cd_prepare_class;
	}

	public void setCd_prepare_class(String cd_prepare_class) {
		this.cd_prepare_class = cd_prepare_class;
	}

	public String getCd_reject_cause() {
		return cd_reject_cause;
	}

	public void setCd_reject_cause(String cd_reject_cause) {
		this.cd_reject_cause = cd_reject_cause;
	}

	public List<CodeInfo> getFields() {
		return fields;
	}

	public void setFields(List<CodeInfo> fields) {
		this.fields = fields;
	}

	public String getYn_grt() {
		return yn_grt;
	}

	public void setYn_grt(String yn_grt) {
		this.yn_grt = yn_grt;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getYn_agency() {
		return yn_agency;
	}

	public void setYn_agency(String yn_agency) {
		this.yn_agency = yn_agency;
	}
}
