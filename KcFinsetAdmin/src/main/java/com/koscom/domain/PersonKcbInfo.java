package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonKcbInfo implements Serializable {
	private static final long serialVersionUID = 6710876465703623680L;
	protected String no_person ;
	protected String score_cb  ;
	protected String avg_credit;
	protected String avg_avg   ;
	protected String grade_avg ;
	protected String id_frt           ;
	protected String dt_frt           ;
	protected String id_lst           ;
	protected String dt_lst           ; 
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getScore_cb() {
		return score_cb;
	}
	public void setScore_cb(String score_cb) {
		this.score_cb = score_cb;
	}
	public String getAvg_credit() {
		return avg_credit;
	}
	public void setAvg_credit(String avg_credit) {
		this.avg_credit = avg_credit;
	}
	public String getAvg_avg() {
		return avg_avg;
	}
	public void setAvg_avg(String avg_avg) {
		this.avg_avg = avg_avg;
	}
	public String getGrade_avg() {
		return grade_avg;
	}
	public void setGrade_avg(String grade_avg) {
		this.grade_avg = grade_avg;
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
