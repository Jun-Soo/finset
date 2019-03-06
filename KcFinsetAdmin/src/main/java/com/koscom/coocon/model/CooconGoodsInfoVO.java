package com.koscom.coocon.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class CooconGoodsInfoVO extends SearchForm implements Serializable {

	private static final long serialVersionUID = 4694112476376527631L;
	//	private static final long serialVersionUID = -7693376532078788852L;
	
	private String cd_coocon_goods;
	private String nm_coocon_goods;
	private String seq;
	private String cd_non_goods;
	private String cd_type_fc;
	private String cd_org;
	private String cd_type_goods;
	private String cd_type_req;
	private String nm_goods;
	private String desc_goods;
	private String desc_target_cust;
	private String desc_collateral;
	private String desc_limit;
	private String desc_yield_rate;
	private String desc_prefer_yield;
	private String desc_term;
	private String desc_repaymethod;
	private String desc_contractfee;
	private String desc_papers;
	private String desc_overdue_interest;
	private String desc_repayment_fee;
	private String desc_loan_cost;
	private String desc_loan_period;
	private String desc_loan_provide;
	private String desc_goods_inquiry;
	private String desc_etc;
	private String id_frt;
	private String dt_frt;
	private String id_lst;
	private String dt_lst;
	public String getCd_coocon_goods() {
		return cd_coocon_goods;
	}
	public void setCd_coocon_goods(String cd_coocon_goods) {
		this.cd_coocon_goods = cd_coocon_goods;
	}
	public String getNm_coocon_goods() {
		return nm_coocon_goods;
	}
	public void setNm_coocon_goods(String nm_coocon_goods) {
		this.nm_coocon_goods = nm_coocon_goods;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCd_non_goods() {
		return cd_non_goods;
	}
	public void setCd_non_goods(String cd_non_goods) {
		this.cd_non_goods = cd_non_goods;
	}
	public String getCd_type_fc() {
		return cd_type_fc;
	}
	public void setCd_type_fc(String cd_type_fc) {
		this.cd_type_fc = cd_type_fc;
	}
	public String getCd_org() {
		return cd_org;
	}
	public void setCd_org(String cd_org) {
		this.cd_org = cd_org;
	}
	public String getCd_type_goods() {
		return cd_type_goods;
	}
	public void setCd_type_goods(String cd_type_goods) {
		this.cd_type_goods = cd_type_goods;
	}
	public String getCd_type_req() {
		return cd_type_req;
	}
	public void setCd_type_req(String cd_type_req) {
		this.cd_type_req = cd_type_req;
	}
	public String getNm_goods() {
		return nm_goods;
	}
	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}
	public String getDesc_goods() {
		return desc_goods;
	}
	public void setDesc_goods(String desc_goods) {
		this.desc_goods = desc_goods;
	}
	public String getDesc_target_cust() {
		return desc_target_cust;
	}
	public void setDesc_target_cust(String desc_target_cust) {
		this.desc_target_cust = desc_target_cust;
	}
	public String getDesc_collateral() {
		return desc_collateral;
	}
	public void setDesc_collateral(String desc_collateral) {
		this.desc_collateral = desc_collateral;
	}
	public String getDesc_limit() {
		return desc_limit;
	}
	public void setDesc_limit(String desc_limit) {
		this.desc_limit = desc_limit;
	}
	public String getDesc_yield_rate() {
		return desc_yield_rate;
	}
	public void setDesc_yield_rate(String desc_yield_rate) {
		this.desc_yield_rate = desc_yield_rate;
	}
	public String getDesc_prefer_yield() {
		return desc_prefer_yield;
	}
	public void setDesc_prefer_yield(String desc_prefer_yield) {
		this.desc_prefer_yield = desc_prefer_yield;
	}
	public String getDesc_term() {
		return desc_term;
	}
	public void setDesc_term(String desc_term) {
		this.desc_term = desc_term;
	}
	public String getDesc_repaymethod() {
		return desc_repaymethod;
	}
	public void setDesc_repaymethod(String desc_repaymethod) {
		this.desc_repaymethod = desc_repaymethod;
	}
	public String getDesc_contractfee() {
		return desc_contractfee;
	}
	public void setDesc_contractfee(String desc_contractfee) {
		this.desc_contractfee = desc_contractfee;
	}
	public String getDesc_papers() {
		return desc_papers;
	}
	public void setDesc_papers(String desc_papers) {
		this.desc_papers = desc_papers;
	}
	public String getDesc_overdue_interest() {
		return desc_overdue_interest;
	}
	public void setDesc_overdue_interest(String desc_overdue_interest) {
		this.desc_overdue_interest = desc_overdue_interest;
	}
	public String getDesc_repayment_fee() {
		return desc_repayment_fee;
	}
	public void setDesc_repayment_fee(String desc_repayment_fee) {
		this.desc_repayment_fee = desc_repayment_fee;
	}
	public String getDesc_loan_cost() {
		return desc_loan_cost;
	}
	public void setDesc_loan_cost(String desc_loan_cost) {
		this.desc_loan_cost = desc_loan_cost;
	}
	public String getDesc_loan_period() {
		return desc_loan_period;
	}
	public void setDesc_loan_period(String desc_loan_period) {
		this.desc_loan_period = desc_loan_period;
	}
	public String getDesc_loan_provide() {
		return desc_loan_provide;
	}
	public void setDesc_loan_provide(String desc_loan_provide) {
		this.desc_loan_provide = desc_loan_provide;
	}
	public String getDesc_goods_inquiry() {
		return desc_goods_inquiry;
	}
	public void setDesc_goods_inquiry(String desc_goods_inquiry) {
		this.desc_goods_inquiry = desc_goods_inquiry;
	}
	public String getDesc_etc() {
		return desc_etc;
	}
	public void setDesc_etc(String desc_etc) {
		this.desc_etc = desc_etc;
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