package com.koscom.domain;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CustInfo implements Serializable{
	private static final long serialVersionUID = -3331970875867185862L;
	private 	String	no_cust;			//고객번호
	private 	String	nm_cust;			//고객명 (이름,사업장명)
	private 	String	bgn;				//생년월일+성별
	private 	String	c3_area;			//지역구분
	private 	String	cd_identity;		//식별구분 ( 개인 , 사업자..)
	private 	String	no_identity;		//식별번호 (주민번호,사업자번호..)
	private 	String	cd_rank;				//랭크
	private 	String	cd_rank_sub;			//SUB랭크
	private 	String	cd50_cust_prop;			//고객속성
	private 	String	yn_upload_cb;			//CB업로드유무
	private 	String  id_frt;
	private 	Date	dt_frt;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_cust() {
		return no_cust;
	}

	public void setNo_cust(String no_cust) {
		this.no_cust = no_cust;
	}

	public String getNm_cust() {
		return nm_cust;
	}

	public void setNm_cust(String nm_cust) {
		this.nm_cust = nm_cust;
	}

	public String getBgn() {
		return bgn;
	}

	public void setBgn(String bgn) {
		this.bgn = bgn;
	}

	public String getC3_area() {
		return c3_area;
	}

	public void setC3_area(String c3_area) {
		this.c3_area = c3_area;
	}

	public String getCd_identity() {
		return cd_identity;
	}

	public void setCd_identity(String cd_identity) {
		this.cd_identity = cd_identity;
	}

	public String getNo_identity() {
		return no_identity;
	}

	public void setNo_identity(String no_identity) {
		this.no_identity = no_identity;
	}

	public String getCd_rank() {
		return cd_rank;
	}

	public void setCd_rank(String cd_rank) {
		this.cd_rank = cd_rank;
	}

	public String getCd_rank_sub() {
		return cd_rank_sub;
	}

	public void setCd_rank_sub(String cd_rank_sub) {
		this.cd_rank_sub = cd_rank_sub;
	}

	public String getCd50_cust_prop() {
		return cd50_cust_prop;
	}

	public void setCd50_cust_prop(String cd50_cust_prop) {
		this.cd50_cust_prop = cd50_cust_prop;
	}

	public String getYn_upload_cb() {
		return yn_upload_cb;
	}

	public void setYn_upload_cb(String yn_upload_cb) {
		this.yn_upload_cb = yn_upload_cb;
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
	
}
