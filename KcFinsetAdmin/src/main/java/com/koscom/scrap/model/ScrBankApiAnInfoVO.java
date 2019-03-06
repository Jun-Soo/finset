package com.koscom.scrap.model;

import java.io.Serializable;

public class ScrBankApiAnInfoVO implements Serializable{
	private static final long serialVersionUID = 8386564702491580492L;
	protected String no_person= "";				//회원고유번호
	protected String an= "";					//계좌번호
	protected String cd_fc= "";					//금융사코드
	protected String type_an= "";				//예금종류
	protected String nm_an= "";					//예금명
	protected String cd_currency= "";			//통화코드
	protected String principal= "";				//투자원금
	protected String amt_available= "";			//출금가능금액
	protected String current_balance= "";		//현재잔액
	protected String loan_balance= "";			//대출잔액
	protected String loan_ceiling= "";			//대출한도액
	protected String dt_new= "";				//신규일자
	protected String dt_end= "";				//만기일자
	protected String interest_rate= "";			//금리
	protected String profit_rate= "";			//수익율
	protected String few_days= "";				//최종이수일자
	protected String interest_date= "";			//이자납입일
	protected String yn_use= "";				//사용여부
	protected String dt_frt= "";				//최초입력시간
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getType_an() {
		return type_an;
	}
	public void setType_an(String type_an) {
		this.type_an = type_an;
	}
	public String getNm_an() {
		return nm_an;
	}
	public void setNm_an(String nm_an) {
		this.nm_an = nm_an;
	}
	public String getCd_currency() {
		return cd_currency;
	}
	public void setCd_currency(String cd_currency) {
		this.cd_currency = cd_currency;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getAmt_available() {
		return amt_available;
	}
	public void setAmt_available(String amt_available) {
		this.amt_available = amt_available;
	}
	public String getCurrent_balance() {
		return current_balance;
	}
	public void setCurrent_balance(String current_balance) {
		this.current_balance = current_balance;
	}
	public String getLoan_balance() {
		return loan_balance;
	}
	public void setLoan_balance(String loan_balance) {
		this.loan_balance = loan_balance;
	}
	public String getLoan_ceiling() {
		return loan_ceiling;
	}
	public void setLoan_ceiling(String loan_ceiling) {
		this.loan_ceiling = loan_ceiling;
	}
	public String getDt_new() {
		return dt_new;
	}
	public void setDt_new(String dt_new) {
		this.dt_new = dt_new;
	}
	public String getDt_end() {
		return dt_end;
	}
	public void setDt_end(String dt_end) {
		this.dt_end = dt_end;
	}
	public String getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
	public String getProfit_rate() {
		return profit_rate;
	}
	public void setProfit_rate(String profit_rate) {
		this.profit_rate = profit_rate;
	}
	public String getFew_days() {
		return few_days;
	}
	public void setFew_days(String few_days) {
		this.few_days = few_days;
	}
	public String getInterest_date() {
		return interest_date;
	}
	public void setInterest_date(String interest_date) {
		this.interest_date = interest_date;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
