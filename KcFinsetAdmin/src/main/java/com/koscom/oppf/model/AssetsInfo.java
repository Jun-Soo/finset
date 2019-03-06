package com.koscom.oppf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AssetsInfo implements Serializable{
	private static final long serialVersionUID = -6262009169258245396L;
	protected long total_evaluation_Amt;    //총평가금액
	protected long total_purchase_amt;      //총매수금액
	protected double rto_assets_proportion; //자산별 비중
	protected double rto_assets_tevenue;    //자산별 수익률
	protected int accno_cnt;    //계좌건수
	
	public int getAccno_cnt() {
		return accno_cnt;
	}
	public void setAccno_cnt(int accno_cnt) {
		this.accno_cnt = accno_cnt;
	}
	public long getTotal_evaluation_Amt() {
		return total_evaluation_Amt;
	}
	public void setTotal_evaluation_Amt(long total_evaluation_Amt) {
		this.total_evaluation_Amt = total_evaluation_Amt;
	}
	public long getTotal_purchase_amt() {
		return total_purchase_amt;
	}
	public void setTotal_purchase_amt(long total_purchase_amt) {
		this.total_purchase_amt = total_purchase_amt;
	}
	public double getRto_assets_proportion() {
		return rto_assets_proportion;
	}
	public void setRto_assets_proportion(double rto_assets_proportion) {
		this.rto_assets_proportion = rto_assets_proportion;
	}
	public double getRto_assets_tevenue() {
		return rto_assets_tevenue;
	}
	public void setRto_assets_tevenue(double rto_assets_tevenue) {
		this.rto_assets_tevenue = rto_assets_tevenue;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
