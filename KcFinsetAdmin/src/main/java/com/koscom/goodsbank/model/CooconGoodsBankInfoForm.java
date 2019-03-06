package com.koscom.goodsbank.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class CooconGoodsBankInfoForm extends SearchForm implements Serializable {

	private static final long serialVersionUID = 4694112476376527631L;
	//	private static final long serialVersionUID = -7693376532078788852L;

	/* coocon_goods_info */
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
	private String cd_fin;

	/* goodsbank_info */
	private String cd_fc;
	private String nm_fc;
	private String cd_goods_class_l;
	private String cd_goods_class_m;
	private String cd_goods_class_s;
	private String cd_goods_fc;
	private String yn_use;
	private String rto_interest_from;
	private String rto_interest_to;
	private String desc_max_limit;
	private String min_loan_term;
	private String max_loan_term;
	private String renew_max_term;
	private String rto_cancel;
	private String year_commission;
	private String desc_feature;
	private String keyword_list;
	private String cd_apply_type;
	private String cd_type_pay;
	private String cd_trade_type;
	private String yn_send_docu;
	private String cd_target_cust;
	private String cd_ratio_type;
	private String cd_collateral_house_type;
	private String cd_collateral_loan_use;
	private String cd_area;
	private String cd_defer;
	private String cd_object;
	private String yn_post_ranking;
	private String cd_time_exec;
	private String desc_loan;
	private String desc_ratio;
	private String desc_contact;
	private String yn_visit;
	private String yn_erly_rpay_fee;
	private String yn_srch_ratio_limit;

	//20180720 goodsbank_info금융사링크 추가
	private String fc_link;
	//20180731 goodsbank_info이자부과시기 추가
	private String time_interest;

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
	public String getCd_fin() {
		return cd_fin;
	}
	public void setCd_fin(String cd_fin) {
		this.cd_fin = cd_fin;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	public String getCd_goods_class_l() {
		return cd_goods_class_l;
	}
	public void setCd_goods_class_l(String cd_goods_class_l) {
		this.cd_goods_class_l = cd_goods_class_l;
	}
	public String getCd_goods_class_m() {
		return cd_goods_class_m;
	}
	public void setCd_goods_class_m(String cd_goods_class_m) {
		this.cd_goods_class_m = cd_goods_class_m;
	}
	public String getCd_goods_class_s() {
		return cd_goods_class_s;
	}
	public void setCd_goods_class_s(String cd_goods_class_s) {
		this.cd_goods_class_s = cd_goods_class_s;
	}
	public String getCd_goods_fc() {
		return cd_goods_fc;
	}
	public void setCd_goods_fc(String cd_goods_fc) {
		this.cd_goods_fc = cd_goods_fc;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getRto_interest_from() {
		return rto_interest_from;
	}
	public void setRto_interest_from(String rto_interest_from) {
		this.rto_interest_from = rto_interest_from;
	}
	public String getRto_interest_to() {
		return rto_interest_to;
	}
	public void setRto_interest_to(String rto_interest_to) {
		this.rto_interest_to = rto_interest_to;
	}
	public String getDesc_max_limit() {
		return desc_max_limit;
	}
	public void setDesc_max_limit(String desc_max_limit) {
		this.desc_max_limit = desc_max_limit;
	}
	public String getMin_loan_term() {
		return min_loan_term;
	}
	public void setMin_loan_term(String min_loan_term) {
		this.min_loan_term = min_loan_term;
	}
	public String getMax_loan_term() {
		return max_loan_term;
	}
	public void setMax_loan_term(String max_loan_term) {
		this.max_loan_term = max_loan_term;
	}
	public String getRenew_max_term() {
		return renew_max_term;
	}
	public void setRenew_max_term(String renew_max_term) {
		this.renew_max_term = renew_max_term;
	}
	public String getRto_cancel() {
		return rto_cancel;
	}
	public void setRto_cancel(String rto_cancel) {
		this.rto_cancel = rto_cancel;
	}
	public String getYear_commission() {
		return year_commission;
	}
	public void setYear_commission(String year_commission) {
		this.year_commission = year_commission;
	}
	public String getDesc_feature() {
		return desc_feature;
	}
	public void setDesc_feature(String desc_feature) {
		this.desc_feature = desc_feature;
	}
	public String getKeyword_list() {
		return keyword_list;
	}
	public void setKeyword_list(String keyword_list) {
		this.keyword_list = keyword_list;
	}
	public String getCd_apply_type() {
		return cd_apply_type;
	}
	public void setCd_apply_type(String cd_apply_type) {
		this.cd_apply_type = cd_apply_type;
	}
	public String getCd_type_pay() {
		return cd_type_pay;
	}
	public void setCd_type_pay(String cd_type_pay) {
		this.cd_type_pay = cd_type_pay;
	}
	public String getCd_trade_type() {
		return cd_trade_type;
	}
	public void setCd_trade_type(String cd_trade_type) {
		this.cd_trade_type = cd_trade_type;
	}
	public String getYn_send_docu() {
		return yn_send_docu;
	}
	public void setYn_send_docu(String yn_send_docu) {
		this.yn_send_docu = yn_send_docu;
	}
	public String getCd_target_cust() {
		return cd_target_cust;
	}
	public void setCd_target_cust(String cd_target_cust) {
		this.cd_target_cust = cd_target_cust;
	}
	public String getCd_ratio_type() {
		return cd_ratio_type;
	}
	public void setCd_ratio_type(String cd_ratio_type) {
		this.cd_ratio_type = cd_ratio_type;
	}
	public String getCd_collateral_house_type() {
		return cd_collateral_house_type;
	}
	public void setCd_collateral_house_type(String cd_collateral_house_type) {
		this.cd_collateral_house_type = cd_collateral_house_type;
	}
	public String getCd_collateral_loan_use() {
		return cd_collateral_loan_use;
	}
	public void setCd_collateral_loan_use(String cd_collateral_loan_use) {
		this.cd_collateral_loan_use = cd_collateral_loan_use;
	}
	public String getCd_area() {
		return cd_area;
	}
	public void setCd_area(String cd_area) {
		this.cd_area = cd_area;
	}
	public String getCd_defer() {
		return cd_defer;
	}
	public void setCd_defer(String cd_defer) {
		this.cd_defer = cd_defer;
	}
	public String getCd_object() {
		return cd_object;
	}
	public void setCd_object(String cd_object) {
		this.cd_object = cd_object;
	}
	public String getYn_post_ranking() {
		return yn_post_ranking;
	}
	public void setYn_post_ranking(String yn_post_ranking) {
		this.yn_post_ranking = yn_post_ranking;
	}
	public String getCd_time_exec() {
		return cd_time_exec;
	}
	public void setCd_time_exec(String cd_time_exec) {
		this.cd_time_exec = cd_time_exec;
	}
	public String getDesc_loan() {
		return desc_loan;
	}
	public void setDesc_loan(String desc_loan) {
		this.desc_loan = desc_loan;
	}
	public String getDesc_ratio() {
		return desc_ratio;
	}
	public void setDesc_ratio(String desc_ratio) {
		this.desc_ratio = desc_ratio;
	}
	public String getDesc_contact() {
		return desc_contact;
	}
	public void setDesc_contact(String desc_contact) {
		this.desc_contact = desc_contact;
	}
	public String getYn_visit() {
		return yn_visit;
	}
	public void setYn_visit(String yn_visit) {
		this.yn_visit = yn_visit;
	}
	public String getYn_erly_rpay_fee() {
		return yn_erly_rpay_fee;
	}
	public void setYn_erly_rpay_fee(String yn_erly_rpay_fee) {
		this.yn_erly_rpay_fee = yn_erly_rpay_fee;
	}
	public String getYn_srch_ratio_limit() {
		return yn_srch_ratio_limit;
	}
	public void setYn_srch_ratio_limit(String yn_srch_ratio_limit) {
		this.yn_srch_ratio_limit = yn_srch_ratio_limit;
	}
	public String getFc_link() {
		return fc_link;
	}
	public void setFc_link(String fc_link) {
		this.fc_link = fc_link;
	}
	public String getTime_interest() {
		return time_interest;
	}
	public void setTime_interest(String time_interest) {
		this.time_interest = time_interest;
	}

}