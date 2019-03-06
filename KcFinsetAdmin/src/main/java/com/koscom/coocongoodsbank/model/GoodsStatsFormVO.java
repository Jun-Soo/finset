package com.koscom.coocongoodsbank.model;

import com.koscom.comm.model.SearchForm;

import java.io.Serializable;

public class GoodsStatsFormVO extends SearchForm implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;
//	private static final long serialVersionUID = -5283835109034749303L;
	
	private String cd_fc;
	private String cd_coocon_goods;
	private String gubun;
	
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getCd_coocon_goods() {
		return cd_coocon_goods;
	}
	public void setCd_coocon_goods(String cd_coocon_goods) {
		this.cd_coocon_goods = cd_coocon_goods;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	
	
}