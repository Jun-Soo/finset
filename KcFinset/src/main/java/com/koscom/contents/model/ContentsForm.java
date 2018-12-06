package com.koscom.contents.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class ContentsForm extends SearchForm implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -4341721397293956579L;

	//뉴스관리
	private String news_status; //상태

	//소비분류관리
	private String sel_cd_class; //분류명
	private String sel_cd_fc; //카드사

	public String getNews_status() {
		return news_status;
	}
	public void setNews_status(String news_status) {
		this.news_status = news_status;
	}

	public String getSel_cd_class() {
		return sel_cd_class;
	}
	public void setSel_cd_class(String sel_cd_class) {
		this.sel_cd_class = sel_cd_class;
	}
	public String getSel_cd_fc() {
		return sel_cd_fc;
	}
	public void setSel_cd_fc(String sel_cd_fc) {
		this.sel_cd_fc = sel_cd_fc;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
