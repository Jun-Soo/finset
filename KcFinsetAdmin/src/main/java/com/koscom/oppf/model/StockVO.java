package com.koscom.oppf.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.StockListInfo;

public class StockVO extends StockListInfo implements Serializable{
	private static final long serialVersionUID = 2488963308203998702L;
	private long total_assets_amt; //총 자산
	private List<AssetsInfo> listAssets; 
	private AssetsInfo assetsInfoFund;  //펀드 정보
	private AssetsInfo assetsInfoEquity;//주식 정보
	private AssetsInfo assetsInfoEtc;   //기타 정보
	
	public AssetsInfo getAssetsInfoFund() {
		return assetsInfoFund;
	}

	public void setAssetsInfoFund(AssetsInfo assetsInfoFund) {
		this.assetsInfoFund = assetsInfoFund;
	}

	public AssetsInfo getAssetsInfoEquity() {
		return assetsInfoEquity;
	}

	public void setAssetsInfoEquity(AssetsInfo assetsInfoEquity) {
		this.assetsInfoEquity = assetsInfoEquity;
	}

	public AssetsInfo getAssetsInfoEtc() {
		return assetsInfoEtc;
	}

	public void setAssetsInfoEtc(AssetsInfo assetsInfoEtc) {
		this.assetsInfoEtc = assetsInfoEtc;
	}

	public List<AssetsInfo> getListAssets() {
		return listAssets;
	}

	public void setListAssets(List<AssetsInfo> listAssets) {
		this.listAssets = listAssets;
	}

	public long getTotal_assets_amt() {
		return total_assets_amt;
	}

	public void setTotal_assets_amt(long total_assets_amt) {
		this.total_assets_amt = total_assets_amt;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
