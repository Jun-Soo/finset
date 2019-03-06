package com.koscom.debt.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class DebtSummaryVO implements Serializable{

	private static final long serialVersionUID = -1349961038409036747L;

    private String dt_std              ; /*기준일자*/
    private String cnt_loan            ; /*대출건수*/
    private String cur_mon_mid_rpy     ; /*당월 상환금액*/
    private String cur_mon_mid_rpy_p   ; /*당월 상환원금*/
    private String cur_mon_mid_rpy_i   ; /*당월 상환이자*/
    private String amt_remain          ; /*대출잔액*/
    private String rate_amt_contract   ; /*상환비율(%)*/
    private String min_ymd_loan        ; /*min 개설일*/
    private String max_ymd_loanend     ; /*max 만기일*/
    private String all_rest_term       ; /*상환기간*/
    private String rest_term           ; /*만기상환까지 잔여기간*/
    private String rest_time           ; /*만기상환까지 잔여시간*/
    private String loan_term           ; /*대출기간*/
    private String ever_interest       ; /* 금리(평균) (%)*/
    private String amt_repay           ; /*원리금 상환(년)*/
    private String amt_etm_income      ; /*소득(년)*/
    private String rate_repay          ; /*이자상환비율(%)*/
    private String cnt                 ; /*건수*/
    private String repay_pni_per_income; /*원리금상환/소득(%)(년)*/
    private String cur_month           ; /*현재월*/
    private String dutation_now_rate   ; /*전체기간 대비 잔여기간 율*/

    public String getLoan_term() {
		return loan_term;
	}

	public void setLoan_term(String loan_term) {
		this.loan_term = loan_term;
	}

	public String getDt_std() {
		return dt_std;
	}

	public void setDt_std(String dt_std) {
		this.dt_std = dt_std;
	}

	public String getCnt_loan() {
		return cnt_loan;
	}

	public void setCnt_loan(String cnt_loan) {
		this.cnt_loan = cnt_loan;
	}

	public String getCur_mon_mid_rpy() {
		return cur_mon_mid_rpy;
	}

	public void setCur_mon_mid_rpy(String cur_mon_mid_rpy) {
		this.cur_mon_mid_rpy = cur_mon_mid_rpy;
	}

	public String getAmt_remain() {
		return amt_remain;
	}

	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}

	public String getRate_amt_contract() {
		return rate_amt_contract;
	}

	public void setRate_amt_contract(String rate_amt_contract) {
		this.rate_amt_contract = rate_amt_contract;
	}

	public String getMin_ymd_loan() {
		return min_ymd_loan;
	}

	public void setMin_ymd_loan(String min_ymd_loan) {
		this.min_ymd_loan = min_ymd_loan;
	}

	public String getMax_ymd_loanend() {
		return max_ymd_loanend;
	}

	public void setMax_ymd_loanend(String max_ymd_loanend) {
		this.max_ymd_loanend = max_ymd_loanend;
	}

	public String getAll_rest_term() {
		return all_rest_term;
	}

	public void setAll_rest_term(String all_rest_term) {
		this.all_rest_term = all_rest_term;
	}

	public String getRest_term() {
		return rest_term;
	}

	public void setRest_term(String rest_term) {
		this.rest_term = rest_term;
	}

	public String getRest_time() {
		return rest_time;
	}

	public void setRest_time(String rest_time) {
		this.rest_time = rest_time;
	}

	public String getCur_mon_mid_rpy_p() {
		return cur_mon_mid_rpy_p;
	}

	public void setCur_mon_mid_rpy_p(String cur_mon_mid_rpy_p) {
		this.cur_mon_mid_rpy_p = cur_mon_mid_rpy_p;
	}

	public String getCur_mon_mid_rpy_i() {
		return cur_mon_mid_rpy_i;
	}

	public void setCur_mon_mid_rpy_i(String cur_mon_mid_rpy_i) {
		this.cur_mon_mid_rpy_i = cur_mon_mid_rpy_i;
	}

	public String getEver_interest() {
		return ever_interest;
	}

	public void setEver_interest(String ever_interest) {
		this.ever_interest = ever_interest;
	}

	public String getAmt_repay() {
		return amt_repay;
	}

	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}

	public String getAmt_etm_income() {
		return amt_etm_income;
	}

	public void setAmt_etm_income(String amt_etm_income) {
		this.amt_etm_income = amt_etm_income;
	}

	public String getRate_repay() {
		return rate_repay;
	}

	public void setRate_repay(String rate_repay) {
		this.rate_repay = rate_repay;
	}

	public String getRepay_pni_per_income() {
		return repay_pni_per_income;
	}

	public void setRepay_pni_per_income(String repay_pni_per_income) {
		this.repay_pni_per_income = repay_pni_per_income;
	}

	public String getCur_month() {
		return cur_month;
	}

	public void setCur_month(String cur_month) {
		this.cur_month = cur_month;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getDutation_now_rate() {
		return dutation_now_rate;
	}

	public void setDutation_now_rate(String dutation_now_rate) {
		this.dutation_now_rate = dutation_now_rate;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
