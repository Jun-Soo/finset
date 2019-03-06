package com.koscom.coocon.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class CooconChangeVO extends SearchForm implements Serializable {

	private static final long serialVersionUID = 6760177255996162404L;
//	private static final long serialVersionUID = -5283835109034749303L;
	
	private String cd_coocon_goods;
	private String nm_coocon_goods;
	private String yn_reg;
	private String status;
	private String yn_goods;
	private String yn_target_cust;
	private String yn_collateral;
	private String yn_limit;
	private String yn_yield_rate;
	private String yn_prefer_yield;
	private String yn_term;
	private String yn_repaymethod;
	private String yn_contractfee;
	private String yn_paper;
	private String yn_overdue;
	private String yn_repayment_fee;
	private String yn_loan_cost;
	private String yn_loan_period;
	private String yn_loan_provide;
	private String yn_goods_inquiry;
	private String yn_etc;
	private String id_frt;
	private String dt_frt;
	private String id_lst;
	private String dt_lst;
	
	private String cd_type_fc;
	private String nm_goods;
	
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
	public String getYn_reg() {
		return yn_reg;
	}
	public void setYn_reg(String yn_reg) {
		this.yn_reg = yn_reg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYn_goods() {
		return yn_goods;
	}
	public void setYn_goods(String yn_goods) {
		this.yn_goods = yn_goods;
	}
	public String getYn_target_cust() {
		return yn_target_cust;
	}
	public void setYn_target_cust(String yn_target_cust) {
		this.yn_target_cust = yn_target_cust;
	}
	public String getYn_collateral() {
		return yn_collateral;
	}
	public void setYn_collateral(String yn_collateral) {
		this.yn_collateral = yn_collateral;
	}
	public String getYn_limit() {
		return yn_limit;
	}
	public void setYn_limit(String yn_limit) {
		this.yn_limit = yn_limit;
	}
	public String getYn_yield_rate() {
		return yn_yield_rate;
	}
	public void setYn_yield_rate(String yn_yield_rate) {
		this.yn_yield_rate = yn_yield_rate;
	}
	public String getYn_prefer_yield() {
		return yn_prefer_yield;
	}
	public void setYn_prefer_yield(String yn_prefer_yield) {
		this.yn_prefer_yield = yn_prefer_yield;
	}
	public String getYn_term() {
		return yn_term;
	}
	public void setYn_term(String yn_term) {
		this.yn_term = yn_term;
	}
	public String getYn_repaymethod() {
		return yn_repaymethod;
	}
	public void setYn_repaymethod(String yn_repaymethod) {
		this.yn_repaymethod = yn_repaymethod;
	}
	public String getYn_contractfee() {
		return yn_contractfee;
	}
	public void setYn_contractfee(String yn_contractfee) {
		this.yn_contractfee = yn_contractfee;
	}
	public String getYn_paper() {
		return yn_paper;
	}
	public void setYn_paper(String yn_paper) {
		this.yn_paper = yn_paper;
	}
	public String getYn_overdue() {
		return yn_overdue;
	}
	public void setYn_overdue(String yn_overdue) {
		this.yn_overdue = yn_overdue;
	}
	public String getYn_repayment_fee() {
		return yn_repayment_fee;
	}
	public void setYn_repayment_fee(String yn_repayment_fee) {
		this.yn_repayment_fee = yn_repayment_fee;
	}
	public String getYn_loan_cost() {
		return yn_loan_cost;
	}
	public void setYn_loan_cost(String yn_loan_cost) {
		this.yn_loan_cost = yn_loan_cost;
	}
	public String getYn_loan_period() {
		return yn_loan_period;
	}
	public void setYn_loan_period(String yn_loan_period) {
		this.yn_loan_period = yn_loan_period;
	}
	public String getYn_loan_provide() {
		return yn_loan_provide;
	}
	public void setYn_loan_provide(String yn_loan_provide) {
		this.yn_loan_provide = yn_loan_provide;
	}
	public String getYn_goods_inquiry() {
		return yn_goods_inquiry;
	}
	public void setYn_goods_inquiry(String yn_goods_inquiry) {
		this.yn_goods_inquiry = yn_goods_inquiry;
	}
	public String getYn_etc() {
		return yn_etc;
	}
	public void setYn_etc(String yn_etc) {
		this.yn_etc = yn_etc;
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
	
	public String getCd_type_fc() {
		return cd_type_fc;
	}
	public void setCd_type_fc(String cd_type_fc) {
		this.cd_type_fc = cd_type_fc;
	}
	public String getNm_goods() {
		return nm_goods;
	}
	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}
	public String toString(){
		String result =
				"\ncd_coocon_goods		=" +cd_coocon_goods
				+"\nnm_coocon_goods		=" +nm_coocon_goods
				+"\nyn_reg				=" +yn_reg
				+"\nstatus				=" +status
				+"\nyn_goods			=" +yn_goods
				+"\nyn_target_cust		=" +yn_target_cust
				+"\nyn_collateral		=" +yn_collateral
				+"\nyn_limit			=" +yn_limit
				+"\nyn_yield_rate		=" +yn_yield_rate
				+"\nyn_prefer_yield		=" +yn_prefer_yield
				+"\nyn_term				=" +yn_term
				+"\nyn_repaymethod		=" +yn_repaymethod
				+"\nyn_contractfee		=" +yn_contractfee
				+"\nyn_paper			=" +yn_paper
				+"\nyn_overdue			=" +yn_overdue
				+"\nyn_repayment_fee	=" +yn_repayment_fee
				+"\nyn_loan_cost		=" +yn_loan_cost
				+"\nyn_loan_period		=" +yn_loan_period
				+"\nyn_loan_provide		=" +yn_loan_provide
				+"\nyn_goods_inquiry	=" +yn_goods_inquiry
				+"\nyn_etc				=" +yn_etc
				+"\nid_frt				=" +id_frt
				+"\ndt_frt				=" +dt_frt
				+"\nid_lst				=" +id_lst
				+"\ndt_lst				=" +dt_lst
				;
		return result;
	}
		
}