package com.koscom.finance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FcLoanInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3356582667712561046L;
	private String cd_goods;    //상품코드
	private String yn_loan;     //대출가능여부
	private long amt_limit;   //대출가능한도
	private String rto_loan;    //예상금리
	private int cd_loan_term;    //대출기간
	
	public long getAmt_limit() {
		return amt_limit;
	}

	public int getCd_loan_term() {
		return cd_loan_term;
	}

	public void setCd_loan_term(int cd_loan_term) {
		this.cd_loan_term = cd_loan_term;
	}

	public void setAmt_limit(long amt_limit) {
		this.amt_limit = amt_limit;
	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public String getYn_loan() {
		return yn_loan;
	}

	public void setYn_loan(String yn_loan) {
		this.yn_loan = yn_loan;
	}

	public String getRto_loan() {
		return rto_loan;
	}

	public void setRto_loan(String rto_loan) {
		this.rto_loan = rto_loan;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
