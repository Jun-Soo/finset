package com.koscom.kcb.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbFinDisorderInfo implements Serializable {
	
	private static final long serialVersionUID = -413845979846704349L;

	private String	no_person;
	private String	cd_fc;
	private String	nm_agency;
	private String	cd_default;
	private String	ymd_default;
	private String	amt_regist;
	private String	amt_delay;
	private String	dt_release;
	private String	cd_release;
	private String	no_case;
	private String	dt_delete;
	private String	id_frt;
	private Date	dt_frt;
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_agency() {
		return nm_agency;
	}
	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}
	public String getCd_default() {
		return cd_default;
	}
	public void setCd_default(String cd_default) {
		this.cd_default = cd_default;
	}
	public String getYmd_default() {
		return ymd_default;
	}
	public void setYmd_default(String ymd_default) {
		this.ymd_default = ymd_default;
	}
	public String getAmt_regist() {
		return amt_regist;
	}
	public void setAmt_regist(String amt_regist) {
		this.amt_regist = amt_regist;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public String getDt_release() {
		return dt_release;
	}
	public void setDt_release(String dt_release) {
		this.dt_release = dt_release;
	}
	public String getCd_release() {
		return cd_release;
	}
	public void setCd_release(String cd_release) {
		this.cd_release = cd_release;
	}
	public String getNo_case() {
		return no_case;
	}
	public void setNo_case(String no_case) {
		this.no_case = no_case;
	}
	public String getDt_delete() {
		return dt_delete;
	}
	public void setDt_delete(String dt_delete) {
		this.dt_delete = dt_delete;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public Date getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(Date dt_frt) {
		this.dt_frt = dt_frt;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}