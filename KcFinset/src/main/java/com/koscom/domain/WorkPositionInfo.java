package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class WorkPositionInfo implements Serializable {
	private static final long serialVersionUID = -5701923095690921535L;

	protected String cd_fc;				//금융사코드
	protected String cd_job;			//코드
	protected String cd_work_position;	//코드값
	protected String company_seg;		//company_seg
	protected String yn_use;			//사용여부
	protected String id_frt;			//최초등록자
	protected String dt_frt;			//최초생성일
	protected String id_lst;			//최종수정자
	protected String dt_lst;			//최종수정일
	
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getCd_job() {
		return cd_job;
	}
	public void setCd_job(String cd_job) {
		this.cd_job = cd_job;
	}
	public String getCd_work_position() {
		return cd_work_position;
	}
	public void setCd_work_position(String cd_work_position) {
		this.cd_work_position = cd_work_position;
	}
	public String getCompany_seg() {
		return company_seg;
	}
	public void setCompany_seg(String company_seg) {
		this.company_seg = company_seg;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
