package com.koscom.news.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.comm.model.SearchForm;

public class NewsForm extends SearchForm implements Serializable {

	private static final long serialVersionUID = 5518753684460986461L;

	private String orderBy; //정렬순서
	private List<String> scKeyword; //검색키워드

	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public List<String> getScKeyword() {
		return scKeyword;
	}
	public void setScKeyword(List<String> scKeyword) {
		this.scKeyword = scKeyword;
	}



}
