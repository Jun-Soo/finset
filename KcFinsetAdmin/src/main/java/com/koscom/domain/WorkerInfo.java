package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
public class WorkerInfo implements Serializable{
	private static final long serialVersionUID = -4772425587339406079L;
	protected String id_emp;				// 사용자ID
	protected String nm_emp;				// 사용자이름
	protected String pass_emp;			// 사용자PASSWD
	protected String cd_status_emp;		// 사용자상태
	protected String ext_emp_direct;		// 전화번호
	protected String hp;					// 휴대폰번호
	protected String cd_template_group;	// 사용자템플릿
	
	protected String id_frt;				// 최초등록자
	protected String dt_frt;				// 최초등록일
	protected String id_lst;				// 최종수정자
	protected String dt_lst;				// 최종수정일
	
	protected String cd_fc;			// 업체아이디
	protected String nm_fc;			// 업체명
	protected String cd_fin;				// 업권코드

	protected String ext_emp;				// 내선번호
	protected String c3_branch;			// 사용자지점
	protected String cd_dept_emp;			// 사용자부서
	protected String authority;				// 권한
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getId_emp() {
		return id_emp;
	}
	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}
	public String getPass_emp() {
		return pass_emp;
	}
	public void setPass_emp(String pass_emp) {
		this.pass_emp = pass_emp;
	}
	public String getNm_emp() {
		return nm_emp;
	}
	public void setNm_emp(String nm_emp) {
		this.nm_emp = nm_emp;
	}
	public String getCd_status_emp() {
		return cd_status_emp;
	}
	public void setCd_status_emp(String cd_status_emp) {
		this.cd_status_emp = cd_status_emp;
	}
	public String getC3_branch() {
		return c3_branch;
	}
	public void setC3_branch(String c3_branch) {
		this.c3_branch = c3_branch;
	}
	public String getCd_dept_emp() {
		return cd_dept_emp;
	}
	public void setCd_dept_emp(String cd_dept_emp) {
		this.cd_dept_emp = cd_dept_emp;
	}
	public String getExt_emp() {
		return ext_emp;
	}
	public void setExt_emp(String ext_emp) {
		this.ext_emp = ext_emp;
	}
	public String getExt_emp_direct() {
		return ext_emp_direct;
	}
	public void setExt_emp_direct(String ext_emp_direct) {
		this.ext_emp_direct = ext_emp_direct;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCd_template_group() {
		return cd_template_group;
	}
	public void setCd_template_group(String cd_template_group) {
		this.cd_template_group = cd_template_group;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getCd_fin() {
		return cd_fin;
	}
	public void setCd_fin(String cd_fin) {
		this.cd_fin = cd_fin;
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
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
}
