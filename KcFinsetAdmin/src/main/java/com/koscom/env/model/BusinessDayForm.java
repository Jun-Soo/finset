package com.koscom.env.model;

import java.io.Serializable;

import com.koscom.domain.BusinessDay;

public class BusinessDayForm extends BusinessDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2650323171121262213L;

	private String year;
	private String[] arr_ymd;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String[] getArr_ymd() {
		return arr_ymd;
	}
	public void setArr_ymd(String[] arr_ymd) {
		this.arr_ymd = arr_ymd;
	}
	
}
