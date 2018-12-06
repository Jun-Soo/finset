package com.koscom.finset.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class FinsetForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = -8040454361801538194L;
	String no_bunch = ""; //순번
	String no_person = "";
	String no_prepare = "";
	String cd_fc = "";
	String cd_goods = "";
	String ssn_person = "";
	String orderby = "";

	private String cd_goods_gubun;          // 상품구분
	private String cd_goods_gubun2;          // 상품구분

	public String getCd_goods_gubun(){
		return cd_goods_gubun;
	}

	public String getCd_goods_gubun2() {
		return cd_goods_gubun2;
	}

	public void setCd_goods_gubun2(String cd_goods_gubun2) {
		this.cd_goods_gubun2 = cd_goods_gubun2;
	}

	public void setCd_goods_gubun(String cd_goods_gubun){
		this.cd_goods_gubun = cd_goods_gubun;
	}

	public String getNo_bunch() {
		return no_bunch;
	}
	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
	public String getCd_goods() {
		return cd_goods;
	}
	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_prepare() {
		return no_prepare;
	}
	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getSsn_person() {
		return ssn_person;
	}

	public void setSsn_person(String ssn_person) {
		this.ssn_person = ssn_person;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}