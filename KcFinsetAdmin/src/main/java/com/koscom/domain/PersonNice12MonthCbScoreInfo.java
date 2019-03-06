package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNice12MonthCbScoreInfo implements Serializable {
	private static final long serialVersionUID = 4102792014251198572L;
	protected String no_person                       ;
	protected String std_month                       ;
	protected int avg_point                       ;
	protected int grade                       ;
	protected String id_frt                          ;
	protected String dt_frt                          ;
	protected String id_lst                          ;
	protected String dt_lst                          ;
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
	public String getStd_month() {
		return std_month;
	}
	public void setStd_month(String std_month) {
		this.std_month = std_month;
	}
	public int getAvg_point() {
		return avg_point;
	}
	public void setAvg_point(int avg_point) {
		this.avg_point = avg_point;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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