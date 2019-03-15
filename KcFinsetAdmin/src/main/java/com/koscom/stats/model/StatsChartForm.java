package com.koscom.stats.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class StatsChartForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String txt_dt_from;					// 일자 from
	private String txt_dt_to;					// 일자 to
	
	public String getTxt_dt_from() {
		return txt_dt_from;
	}
	public void setTxt_dt_from(String txt_dt_from) {
		this.txt_dt_from = txt_dt_from;
	}
	public String getTxt_dt_to() {
		return txt_dt_to;
	}
	public void setTxt_dt_to(String txt_dt_to) {
		this.txt_dt_to = txt_dt_to;
	}

}
