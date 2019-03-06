package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AllowIpInfo implements Serializable {
	private static final long serialVersionUID = -2371640452962956903L;
	private String cd_system;			// 시스템구분
	private String ip;					// IP
	private String reg_exp_ip;			// 패턴 IP
	private String memo;				// 메모
	private String id_frt;				// 최초등록자
	private String dt_frt;				// 최초등록일
	private String id_lst;				// 최종수정자
	private String dt_lst;				// 최종수정일
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_system() {
		return cd_system;
	}

	public void setCd_system(String cd_system) {
		this.cd_system = cd_system;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReg_exp_ip() {
		return reg_exp_ip;
	}

	public void setReg_exp_ip(String reg_exp_ip) {
		this.reg_exp_ip = reg_exp_ip;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
}
