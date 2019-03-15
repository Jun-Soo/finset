package com.koscom.goodsbank.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class GoodsStatsVO extends SearchForm  implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;
//	private static final long serialVersionUID = -5283835109034749303L;
	
	private String cd_fc;
	private String gubun;
	private String cd_coocon_goods;
	private String cnt_goods;
	private String new_goods;
	private String nochange_goods;
	private String change_goods;
	private String delete_goods;
	private String dt_frt;
	private String dt_lst;
	
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getCd_coocon_goods() {
		return cd_coocon_goods;
	}
	public void setCd_coocon_goods(String cd_coocon_goods) {
		this.cd_coocon_goods = cd_coocon_goods;
	}
	public String getCnt_goods() {
		return cnt_goods;
	}
	public void setCnt_goods(String cnt_goods) {
		this.cnt_goods = cnt_goods;
	}
	
	public String getNochange_goods() {
		return nochange_goods;
	}
	public void setNochange_goods(String nochange_goods) {
		this.nochange_goods = nochange_goods;
	}
	public String getNew_goods() {
		return new_goods;
	}
	public void setNew_goods(String new_goods) {
		this.new_goods = new_goods;
	}
	public String getChange_goods() {
		return change_goods;
	}
	public void setChange_goods(String change_goods) {
		this.change_goods = change_goods;
	}
	public String getDelete_goods() {
		return delete_goods;
	}
	public void setDelete_goods(String delete_goods) {
		this.delete_goods = delete_goods;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	
}