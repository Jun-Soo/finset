package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespCertFinancestatdtl implements Serializable{

	
	/**
	 * 민원증명통합조회결과_표준재무제표증명상세
	 */
	private static final long serialVersionUID = -1724886368940723268L;

	protected int    assets_total_amt ; //자산총금액
	protected int    capital_amt ; //자본금액
	protected int    capital_total_amt ; //자본총금액
	protected int    debt_capital_total_sum ; //부채자본총합계
	protected int    debt_total_amt ; //부채총금액
	protected int    order_financialstat ; //순서_재무제표
	protected String register_dt ; //등록일시
	protected String result_division ; //결과구분
	protected String reversion_year ; //귀속연도
	protected int    seq_req ; //일련번호_요청
	protected int    thisterm_netincome ; //당기순이익
		
	public int getAssets_total_amt() {
		return assets_total_amt;
	}



	public void setAssets_total_amt(int assets_total_amt) {
		this.assets_total_amt = assets_total_amt;
	}



	public int getCapital_amt() {
		return capital_amt;
	}



	public void setCapital_amt(int capital_amt) {
		this.capital_amt = capital_amt;
	}



	public int getCapital_total_amt() {
		return capital_total_amt;
	}



	public void setCapital_total_amt(int capital_total_amt) {
		this.capital_total_amt = capital_total_amt;
	}



	public int getDebt_capital_total_sum() {
		return debt_capital_total_sum;
	}



	public void setDebt_capital_total_sum(int debt_capital_total_sum) {
		this.debt_capital_total_sum = debt_capital_total_sum;
	}



	public int getDebt_total_amt() {
		return debt_total_amt;
	}



	public void setDebt_total_amt(int debt_total_amt) {
		this.debt_total_amt = debt_total_amt;
	}



	public int getOrder_financialstat() {
		return order_financialstat;
	}



	public void setOrder_financialstat(int order_financialstat) {
		this.order_financialstat = order_financialstat;
	}



	public String getRegister_dt() {
		return register_dt;
	}



	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}



	public String getResult_division() {
		return result_division;
	}



	public void setResult_division(String result_division) {
		this.result_division = result_division;
	}



	public String getReversion_year() {
		return reversion_year;
	}



	public void setReversion_year(String reversion_year) {
		this.reversion_year = reversion_year;
	}



	public int getSeq_req() {
		return seq_req;
	}



	public void setSeq_req(int seq_req) {
		this.seq_req = seq_req;
	}



	public int getThisterm_netincome() {
		return thisterm_netincome;
	}



	public void setThisterm_netincome(int thisterm_netincome) {
		this.thisterm_netincome = thisterm_netincome;
	}



	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
