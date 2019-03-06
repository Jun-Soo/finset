package com.koscom.oppf.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.StockEquitylistInfo;
import com.koscom.domain.StockEtclistInfo;
import com.koscom.domain.StockFundlistInfo;
import com.koscom.domain.StockListInfo;

public class StockEtcVO extends StockEtclistInfo implements Serializable{
	private static final long serialVersionUID = 3429373006714428377L;
	private long revenue; //수익
	private long rto_revenue; //수익률
	private String secunm; // 증권사명
	
	public String getSecunm() {
		return secunm;
	}

	public void setSecunm(String secunm) {
		this.secunm = secunm;
	}

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public long getRto_revenue() {
		return rto_revenue;
	}

	public void setRto_revenue(long rto_revenue) {
		this.rto_revenue = rto_revenue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
