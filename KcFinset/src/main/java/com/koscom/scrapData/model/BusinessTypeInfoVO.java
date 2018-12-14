package com.koscom.scrapData.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BusinessTypeInfoVO implements Serializable{
	
	private static final long serialVersionUID = 8832890489302033877L;
	
	private String	cd_fc;				//금융사관리번호
	private String	nm_business;		//업종명
	private String	cd_consume_class;	//소비항목코드
	private String	id_frt;
	private Date	dt_frt;
	private String	id_lst;
	private Date	dt_lst;
	
	public BusinessTypeInfoVO() {
	}
	public BusinessTypeInfoVO(String cd_fc, String nm_business,
			String cd_consume_class, String id_frt, Date dt_frt, String id_lst,
			Date dt_lst) {
		this.cd_fc = cd_fc;
		this.nm_business = nm_business;
		this.cd_consume_class = cd_consume_class;
		this.id_frt = id_frt;
		this.dt_frt = dt_frt;
		this.id_lst = id_lst;
		this.dt_lst = dt_lst;
	}
	
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_business() {
		return nm_business;
	}
	public void setNm_business(String nm_business) {
		this.nm_business = nm_business;
	}
	public String getCd_consume_class() {
		return cd_consume_class;
	}
	public void setCd_consume_class(String cd_consume_class) {
		this.cd_consume_class = cd_consume_class;
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
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public Date getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(Date dt_lst) {
		this.dt_lst = dt_lst;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}