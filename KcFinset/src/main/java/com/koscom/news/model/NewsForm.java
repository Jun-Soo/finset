package com.koscom.news.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class NewsForm extends SearchForm implements Serializable {

	private static final long serialVersionUID = 5518753684460986461L;

	private String orderby; //정렬순서
	private String scKeyword; //검색키워드

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getScKeyword() {
		return scKeyword;
	}

	public void setScKeyword(String scKeyword) {
		this.scKeyword = scKeyword;
	}



}
