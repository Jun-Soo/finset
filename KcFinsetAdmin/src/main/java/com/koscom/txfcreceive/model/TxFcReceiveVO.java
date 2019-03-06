package com.koscom.txfcreceive.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TxFcReceiveInfo;

public class TxFcReceiveVO extends TxFcReceiveInfo implements Serializable {
	private static final long serialVersionUID = 1515891107543727755L;
	//private String year_term;
	private String amt_pmt;
	private String rto_loan;
	private String amt_loan_index;
	private String rto_dti;
	//private String amt_limit;
	private String nm_goods;
	private String yn_use;
	private String cd_goods_type;
	private String c20_goods_comp;
	
	protected String path_fileCi;		// CI파일
	protected String nm_fileCi;			// CI파일이름
	
	public String getPath_fileCi() {
		return path_fileCi;
	}
	public void setPath_fileCi(String path_fileCi) {
		this.path_fileCi = path_fileCi;
	}
	public String getNm_fileCi() {
		return nm_fileCi;
	}
	public void setNm_fileCi(String nm_fileCi) {
		this.nm_fileCi = nm_fileCi;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public int getYear_term() {
		return year_term;
	}
	public void setYear_term(int year_term) {
		this.year_term = year_term;
	}
	public String getAmt_pmt() {
		return amt_pmt;
	}
	public void setAmt_pmt(String amt_pmt) {
		this.amt_pmt = amt_pmt;
	}
	public String getRto_loan() {
		return rto_loan;
	}
	public void setRto_loan(String rto_loan) {
		this.rto_loan = rto_loan;
	}
	public String getAmt_loan_index() {
		return amt_loan_index;
	}
	public void setAmt_loan_index(String amt_loan_index) {
		this.amt_loan_index = amt_loan_index;
	}
	public String getRto_dti() {
		return rto_dti;
	}
	public void setRto_dti(String rto_dti) {
		this.rto_dti = rto_dti;
	}
	public long getAmt_limit() {
		return amt_limit;
	}
	public void setAmt_limit(long amt_limit) {
		this.amt_limit = amt_limit;
	}
	public String getNm_goods() {
		return nm_goods;
	}
	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getCd_goods_type() {
		return cd_goods_type;
	}
	public void setCd_goods_type(String cd_goods_type) {
		this.cd_goods_type = cd_goods_type;
	}
	public String getC20_goods_comp() {
		return c20_goods_comp;
	}
	public void setC20_goods_comp(String c20_goods_comp) {
		this.c20_goods_comp = c20_goods_comp;
	}
}