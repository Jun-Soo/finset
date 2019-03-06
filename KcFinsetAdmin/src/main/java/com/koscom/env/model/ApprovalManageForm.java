package com.koscom.env.model;

import java.io.Serializable;

public class ApprovalManageForm extends ApprovalManage implements Serializable {
	private static final long serialVersionUID = 5483566270305454382L;
	private String id_emp;
	private String id_appr_list;
	private String cd_template_group;
	private String yn_template;
	private String yn_change_template;
	private String id_frt = "";	//최초입력아이디
	private String dt_frt = "";	//최종입력날짜
	private String id_lst = "";	//최종입력아이디
	private String dt_lst = "";	//최종입력날짜st;
	
	public String getId_emp() {
		return id_emp;
	}
	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}
	public String getId_appr_list() {
		return id_appr_list;
	}
	public void setId_appr_list(String id_appr_list) {
		this.id_appr_list = id_appr_list;
	}
	public String getCd_template_group() {
		return cd_template_group;
	}
	public void setCd_template_group(String cd_template_group) {
		this.cd_template_group = cd_template_group;
	}
	public String getYn_template() {
		return yn_template;
	}
	public void setYn_template(String yn_template) {
		this.yn_template = yn_template;
	}
	public String getYn_change_template() {
		return yn_change_template;
	}
	public void setYn_change_template(String yn_change_template) {
		this.yn_change_template = yn_change_template;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	
}
