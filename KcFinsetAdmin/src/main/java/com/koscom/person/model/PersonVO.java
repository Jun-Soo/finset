package com.koscom.person.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.PersonInfo;
import com.koscom.env.model.CodeInfo;
import com.koscom.util.StringUtil;

public class PersonVO extends PersonInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1372438333901338373L;

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
	//for file transformation
	private byte[] fileArray;
	private String fileName;
	private int fileSize;

	//이벤트푸시 수신여부
	private String yn_eventPush;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getC1_gender() {
		return StringUtil.splitBgn(super.bgn, "G");
	}

	public String getYmd_birth() {
		return StringUtil.splitBgn(super.bgn, "BOD");
	}

	public String getSsn_person_idx1() {
		return StringUtil.splitSsn(super.ssn_person, 1);
	}

	public String getSsn_person_idx2() {
		return StringUtil.splitSsn(super.ssn_person, 2);
	}

	public String getHp_idx1() {
		return StringUtil.splitStr(super.hp, "-", 1);
	}

	public String getHp_idx2() {
		return StringUtil.splitStr(super.hp, "-", 2);
	}

	public String getHp_idx3() {
		return StringUtil.splitStr(super.hp, "-", 3);
	}

	public String getEmail_idx1() {
		return StringUtil.splitStr(super.email, "@", 1);
	}

	public String getEmail_idx2() {
		return StringUtil.splitStr(super.email, "@", 2);
	}

	public String getHp_etc_idx1() {
		return StringUtil.splitStr(super.hp_etc, "-", 1);
	}

	public String getHp_etc_idx2() {
		return StringUtil.splitStr(super.hp_etc, "-", 2);
	}

	public String getHp_etc_idx3() {
		return StringUtil.splitStr(super.hp_etc, "-", 3);
	}

	public String getPh_home_idx1() {
		return StringUtil.splitStr(super.ph_home, "-", 1);
	}

	public String getPh_home_idx2() {
		return StringUtil.splitStr(super.ph_home, "-", 2);
	}

	public String getPh_home_idx3() {
		return StringUtil.splitStr(super.ph_home, "-", 3);
	}

	public String getYm_house_home_idx1() {
		return StringUtil.splitDate(super.ym_house_home, 1);
	}

	public String getYm_house_home_idx2() {
		return StringUtil.splitDate(super.ym_house_home, 2);
	}

	public String getYm_house_reg_idx1() {
		return StringUtil.splitDate(super.ym_house_reg, 1);
	}

	public String getYm_house_reg_idx2() {
		return StringUtil.splitDate(super.ym_house_reg, 2);
	}

	public String getYm_start_comp_idx1() {
		return StringUtil.splitDate(super.ym_start_comp, 1);
	}

	public String getYm_start_comp_idx2() {
		return StringUtil.splitDate(super.ym_start_comp, 2);
	}

	public String getPh_comp_direct_idx1() {
		return StringUtil.splitStr(super.ph_comp_direct, "-", 1);
	}

	public String getPh_comp_direct_idx2() {
		return StringUtil.splitStr(super.ph_comp_direct, "-", 2);
	}

	public String getPh_comp_direct_idx3() {
		return StringUtil.splitStr(super.ph_comp_direct, "-", 3);
	}

	public String getPh_comp_idx1() {
		return StringUtil.splitStr(super.ph_comp, "-", 1);
	}

	public String getPh_comp_idx2() {
		return StringUtil.splitStr(super.ph_comp, "-", 2);
	}

	public String getPh_comp_idx3() {
		return StringUtil.splitStr(super.ph_comp, "-", 3);
	}

	public String getFax_comp_idx1() {
		return StringUtil.splitStr(super.fax_comp, "-", 1);
	}

	public String getFax_comp_idx2() {
		return StringUtil.splitStr(super.fax_comp, "-", 2);
	}

	public String getFax_comp_idx3() {
		return StringUtil.splitStr(super.fax_comp, "-", 3);
	}

	public String getPh_univ_idx1() {
		return StringUtil.splitStr(super.ph_univ, "-", 1);
	}

	public String getPh_univ_idx2() {
		return StringUtil.splitStr(super.ph_univ, "-", 2);
	}

	public String getPh_univ_idx3() {
		return StringUtil.splitStr(super.ph_univ, "-", 3);
	}

	public void setSsn_person_tmp(String[] ssn_person_tmp) {
		super.ssn_person = StringUtil.addChar(ssn_person_tmp, "");
	}

	public void setHp_tmp(String[] hp_tmp) {
		super.hp = StringUtil.addChar(hp_tmp, "-");
	}

	public void setEmail_tmp(String[] email_tmp) {
		super.email = StringUtil.addChar(email_tmp, "@");
	}

	public void setHp_etc_tmp(String[] hp_etc_tmp) {
		super.hp_etc = StringUtil.addChar(hp_etc_tmp, "-");
	}

	public void setPh_home_tmp(String[] ph_home_tmp) {
		super.ph_home = StringUtil.addChar(ph_home_tmp, "-");
	}

	public void setYm_house_home_tmp(String[] ym_house_home_tmp) {
		super.ym_house_home = StringUtil.addChar(ym_house_home_tmp, "");
	}

	public void setYm_house_reg_tmp(String[] ym_house_reg_tmp) {
		super.ym_house_reg = StringUtil.addChar(ym_house_reg_tmp, "");
	}

	public void setYm_start_comp_tmp(String[] ym_start_comp_tmp) {
		super.ym_start_comp = StringUtil.addChar(ym_start_comp_tmp, "");
	}

	public void setPh_comp_direct_tmp(String[] ph_comp_direct_tmp) {
		super.ph_comp_direct = StringUtil.addChar(ph_comp_direct_tmp, "-");
	}

	public void setPh_comp_tmp(String[] ph_comp_tmp) {
		super.ph_comp = StringUtil.addChar(ph_comp_tmp, "-");
	}

	public void setFax_comp_tmp(String[] fax_comp_tmp) {
		super.fax_comp = StringUtil.addChar(fax_comp_tmp, "-");
	}

	public void setPh_univ_tmp(String[] ph_univ_tmp) {
		super.ph_univ = StringUtil.addChar(ph_univ_tmp, "-");
	}

	public void setPost_home_tmp(String[] post_home_tmp) {
		super.post_home = StringUtil.addChar(post_home_tmp, ",");
	}

	public void setPost_reg_tmp(String[] post_reg_tmp) {
		super.post_reg = StringUtil.addChar(post_reg_tmp, ",");
	}

	public void setPost_etc_tmp(String[] post_etc_tmp) {
		super.post_etc = StringUtil.addChar(post_etc_tmp, ",");
	}

	public void setPost_comp_tmp(String[] post_comp_tmp) {
		super.post_comp = StringUtil.addChar(post_comp_tmp, ",");
	}

	public void setPost_univ_tmp(String[] post_univ_tmp) {
		super.post_univ = StringUtil.addChar(post_univ_tmp, ",");
	}

	public String getPost6_home() {
		return StringUtil.splitStr(super.post_home, ",", 1);
	}

	public String getPost5_home() {
		return StringUtil.splitStr(super.post_home, ",", 2);
	}

	public String getPost6_reg() {
		return StringUtil.splitStr(super.post_reg, ",", 1);
	}

	public String getPost5_reg() {
		return StringUtil.splitStr(super.post_reg, ",", 2);
	}

	public String getPost6_etc() {
		return StringUtil.splitStr(super.post_etc, ",", 1);
	}

	public String getPost5_etc() {
		return StringUtil.splitStr(super.post_etc, ",", 2);
	}

	public String getPost6_comp() {
		return StringUtil.splitStr(super.post_comp, ",", 1);
	}

	public String getPost5_comp() {
		return StringUtil.splitStr(super.post_comp, ",", 2);
	}

	public String getPost6_univ() {
		return StringUtil.splitStr(super.post_univ, ",", 1);
	}

	public String getPost5_univ() {
		return StringUtil.splitStr(super.post_univ, ",", 2);
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

	public byte[] getFileArray() {
		return fileArray;
	}

	public void setFileArray(byte[] fileArray) {
		this.fileArray = fileArray;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getYn_eventPush() {
		return yn_eventPush;
	}

	public void setYn_eventPush(String yn_eventPush) {
		this.yn_eventPush = yn_eventPush;
	}


}
