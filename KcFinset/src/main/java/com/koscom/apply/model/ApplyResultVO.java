package com.koscom.apply.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class ApplyResultVO extends SearchForm {

	private static final long serialVersionUID = 8478046251378121121L;
	
	private String rnum;
	private String cd_fc;
	private String cd_goods;
	private String nm_goods;
	private String no_bunch;
	private String dt_receive;
	private String nm_person;
	private String rto_loan;
	private String amt_limit;
	private String yn_loan;
	private String cd_apply_doc_box;
	private String no_apply;
	private String apply_cnt;
	private String yn_receive;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public String getNm_goods() {
		return nm_goods;
	}

	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}

	public String getNo_bunch() {
		return no_bunch;
	}

	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}

	public String getDt_receive() {
		return dt_receive;
	}

	public void setDt_receive(String dt_receive) {
		this.dt_receive = dt_receive;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getRto_loan() {
		return rto_loan;
	}

	public void setRto_loan(String rto_loan) {
		this.rto_loan = rto_loan;
	}

	public String getAmt_limit() {
		return amt_limit;
	}

	public void setAmt_limit(String amt_limit) {
		this.amt_limit = amt_limit;
	}

	public String getYn_loan() {
		return yn_loan;
	}

	public void setYn_loan(String yn_loan) {
		this.yn_loan = yn_loan;
	}

	public String getCd_apply_doc_box() {
		return cd_apply_doc_box;
	}

	public void setCd_apply_doc_box(String cd_apply_doc_box) {
		this.cd_apply_doc_box = cd_apply_doc_box;
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getApply_cnt() {
		return apply_cnt;
	}

	public void setApply_cnt(String apply_cnt) {
		this.apply_cnt = apply_cnt;
	}

	public String getYn_receive() {
		return yn_receive;
	}

	public void setYn_receive(String yn_receive) {
		this.yn_receive = yn_receive;
	}
	
}
