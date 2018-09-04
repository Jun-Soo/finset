package com.koscom.debt.model;

import java.io.Serializable;

import com.koscom.kcb.model.seg.Kcb_Segment030;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DebtVO extends Kcb_Segment030 implements Serializable{

    private static final long serialVersionUID = 6623460549376754086L;
    /**
     * 리스트
*/
    private String cd_fc              ;    /*금융사코드*/
    private String path_file1         ;    /*은행 CI*/
    private String amt_remain         ;    /*대출잔액*/
    private String rate_repay         ;    /*원금상환비율*/
    private String rate_mm_repay_i    ;    /*이자상환비율(당월)*/
    private String ymd_loanend        ;    /*만기일*/
    private String term_remain        ;    /*잔여기간*/
    private String term_all           ;    /*총 대출기간*/
    private String amt_repay_i        ;    /*당월 상환이자*/
    private String amt_repay_p        ;    /*당월 상환원금*/
    private String remain_amt_repay_i ;    /*잔여이자(예상)*/
    private String amt_repay_by_year  ;    /*원리금상환(년)*/
    private String amt_repay_p_by_year;    /*원금상환(년)*/
    private String amt_repay_i_by_year;    /*이자상환(년)*/
    private String ever_interest      ;    /*이자*/
    private String repay_per_income   ;    /*원리금 상환/소득*/

    /**
     * 상세
*/
    private String no_manage_info    ;    /*정보관리번호*/
    private String nm_fc             ;    /*금융사명*/
    private String tel				 ;	  /*금융사 전화번호*/
    private String debt_type         ;    /*대출타입*/
    private String cur_mm_amt_repay  ;    /*상환금액(당월)*/
    private String rate_amt_contract ;    /*상환비율(%)*/
    private String type_deal         ;    /*대출구분*/
    private String rep_method        ;    /*상환방식*/
    
    private String yn_credit         ;    /*신용여부*/
    private String yn_loan           ;    /*담보여부*/
    private String ymd_loan          ;    /*개설일*/
    private String loan_mount        ;    /*거치기간*/
    private String inter_pay_day     ;    /*결제일*/
    private String inter_pay_cycle	 ;	  /*이자납입주기*/
    private String amt_contract      ;    /*대출원금*/
    private String interest          ;    /*금리*/
    private String interest_method   ;    /*금리방식*/
    private String cur_month         ;    /*현재월*/
    private String amt_repay         ;    /*당월 결제금액 (완료or예정)*/
    private String moderate_fee      ;    /*중도상환수수료*/
    private String all_amt_repay     ;    /*전체원리금*/
    private String rep_amt_repay     ;    /*상환원리금*/
    private String rem_amt_repay     ;    /*잔여원리금*/
    private String all_amt_repay_i   ;    /*전체이자*/
    private String rep_amt_repay_i   ;    /*상환이자*/
    private String rem_amt_repay_i   ;    /*잔여이자*/
    private String all_amt_repay_p   ;    /*전체원금*/
    private String rep_amt_repay_p   ;    /*상환원금*/
    private String rem_amt_repay_p   ;    /*잔여원금*/

    private String display_yn		 ;	  /*화면 표시 여부 20180612 김휘경*/
    
    @Override
    public String getNo_manage_info() {
        return no_manage_info;
    }

    @Override
    public void setNo_manage_info(String no_manage_info) {
        this.no_manage_info = no_manage_info;
    }

    public String getCur_mm_amt_repay() {
		return cur_mm_amt_repay;
	}

	public void setCur_mm_amt_repay(String cur_mm_amt_repay) {
		this.cur_mm_amt_repay = cur_mm_amt_repay;
	}

	public String getType_deal() {
		return type_deal;
	}

	public void setType_deal(String type_deal) {
		this.type_deal = type_deal;
	}

	public String getRepay_per_income() {
		return repay_per_income;
	}

	public void setRepay_per_income(String repay_per_income) {
		this.repay_per_income = repay_per_income;
	}

	public String getAmt_repay_p() {
		return amt_repay_p;
	}

	public void setAmt_repay_p(String amt_repay_p) {
		this.amt_repay_p = amt_repay_p;
	}

	public String getRate_mm_repay_i() {
		return rate_mm_repay_i;
	}

	public void setRate_mm_repay_i(String rate_mm_repay_i) {
		this.rate_mm_repay_i = rate_mm_repay_i;
	}
    public String getAll_amt_repay() {
        return all_amt_repay;
    }

    public void setAll_amt_repay(String all_amt_repay) {
        this.all_amt_repay = all_amt_repay;
    }

    public String getRep_amt_repay() {
        return rep_amt_repay;
    }

    public void setRep_amt_repay(String rep_amt_repay) {
        this.rep_amt_repay = rep_amt_repay;
    }

    public String getRem_amt_repay() {
        return rem_amt_repay;
    }

    public void setRem_amt_repay(String rem_amt_repay) {
        this.rem_amt_repay = rem_amt_repay;
    }

    public String getAll_amt_repay_i() {
        return all_amt_repay_i;
    }

    public void setAll_amt_repay_i(String all_amt_repay_i) {
        this.all_amt_repay_i = all_amt_repay_i;
    }

    public String getRep_amt_repay_i() {
        return rep_amt_repay_i;
    }

    public void setRep_amt_repay_i(String rep_amt_repay_i) {
        this.rep_amt_repay_i = rep_amt_repay_i;
    }

    public String getRem_amt_repay_i() {
        return rem_amt_repay_i;
    }

    public void setRem_amt_repay_i(String rem_amt_repay_i) {
        this.rem_amt_repay_i = rem_amt_repay_i;
    }

    public String getAll_amt_repay_p() {
        return all_amt_repay_p;
    }

    public void setAll_amt_repay_p(String all_amt_repay_p) {
        this.all_amt_repay_p = all_amt_repay_p;
    }

    public String getRep_amt_repay_p() {
        return rep_amt_repay_p;
    }

    public void setRep_amt_repay_p(String rep_amt_repay_p) {
        this.rep_amt_repay_p = rep_amt_repay_p;
    }

    public String getRem_amt_repay_p() {
        return rem_amt_repay_p;
    }

    public void setRem_amt_repay_p(String rem_amt_repay_p) {
        this.rem_amt_repay_p = rem_amt_repay_p;
    }

    public String getCd_fc() {
        return cd_fc;
    }

    public void setCd_fc(String cd_fc) {
        this.cd_fc = cd_fc;
    }

    public String getPath_file1() {
        return path_file1;
    }

    public void setPath_file1(String path_file1) {
        this.path_file1 = path_file1;
    }

    public String getNm_fc() {
        return nm_fc;
    }

    public void setNm_fc(String nm_fc) {
        this.nm_fc = nm_fc;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRate_amt_contract() {
        return rate_amt_contract;
    }

    public void setRate_amt_contract(String rate_amt_contract) {
        this.rate_amt_contract = rate_amt_contract;
    }

    public String getCur_month() {
        return cur_month;
    }

    public void setCur_month(String cur_month) {
        this.cur_month = cur_month;
    }

    public String getDebt_type() {
        return debt_type;
    }

    public void setDebt_type(String debt_type) {
        this.debt_type = debt_type;
    }

    @Override
    public String getYn_credit() {
        return yn_credit;
    }

    @Override
    public void setYn_credit(String yn_credit) {
        this.yn_credit = yn_credit;
    }

    @Override
    public String getYn_loan() {
        return yn_loan;
    }

    @Override
    public void setYn_loan(String yn_loan) {
        this.yn_loan = yn_loan;
    }

    public String getAmt_repay() {
        return amt_repay;
    }

    public void setAmt_repay(String amt_repay) {
        this.amt_repay = amt_repay;
    }

    @Override
    public String getAmt_remain() {
        return amt_remain;
    }

    @Override
    public void setAmt_remain(String amt_remain) {
        this.amt_remain = amt_remain;
    }

    public String getRate_repay() {
        return rate_repay;
    }

    public void setRate_repay(String rate_repay) {
        this.rate_repay = rate_repay;
    }

    @Override
    public String getYmd_loan() {
        return ymd_loan;
    }

    @Override
    public void setYmd_loan(String ymd_loan) {
        this.ymd_loan = ymd_loan;
    }

    public String getYmd_loanend() {
        return ymd_loanend;
    }

    public void setYmd_loanend(String ymd_loanend) {
        this.ymd_loanend = ymd_loanend;
    }

    public String getLoan_mount() {
        return loan_mount;
    }

    public void setLoan_mount(String loan_mount) {
        this.loan_mount = loan_mount;
    }

    public String getRep_method() {
        return rep_method;
    }

    public void setRep_method(String rep_method) {
        this.rep_method = rep_method;
    }

    public String getInter_pay_day() {
        return inter_pay_day;
    }

    public void setInter_pay_day(String inter_pay_day) {
        this.inter_pay_day = inter_pay_day;
    }

    public String getInter_pay_cycle(){
    	return inter_pay_cycle;
    }
    
    public void setInter_pay_cycle(String inter_pay_cycle){
    	this.inter_pay_cycle = inter_pay_cycle;
    }
    
    @Override
    public String getAmt_contract() {
        return amt_contract;
    }

    @Override
    public void setAmt_contract(String amt_contract) {
        this.amt_contract = amt_contract;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getInterest_method() {
        return interest_method;
    }

    public void setInterest_method(String interest_method) {
        this.interest_method = interest_method;
    }

    public String getTerm_remain() {
        return term_remain;
    }

    public void setTerm_remain(String term_remain) {
        this.term_remain = term_remain;
    }

    public String getTerm_all() {
        return term_all;
    }

    public void setTerm_all(String term_all) {
        this.term_all = term_all;
    }

    public String getAmt_repay_i() {
        return amt_repay_i;
    }

    public void setAmt_repay_i(String amt_repay_i) {
        this.amt_repay_i = amt_repay_i;
    }

    public String getRemain_amt_repay_i() {
        return remain_amt_repay_i;
    }

    public void setRemain_amt_repay_i(String remain_amt_repay_i) {
        this.remain_amt_repay_i = remain_amt_repay_i;
    }

    public String getAmt_repay_by_year() {
        return amt_repay_by_year;
    }

    public void setAmt_repay_by_year(String amt_repay_by_year) {
        this.amt_repay_by_year = amt_repay_by_year;
    }

    public String getAmt_repay_p_by_year() {
        return amt_repay_p_by_year;
    }

    public void setAmt_repay_p_by_year(String amt_repay_p_by_year) {
        this.amt_repay_p_by_year = amt_repay_p_by_year;
    }

    public String getAmt_repay_i_by_year() {
        return amt_repay_i_by_year;
    }

    public void setAmt_repay_i_by_year(String amt_repay_i_by_year) {
        this.amt_repay_i_by_year = amt_repay_i_by_year;
    }

    public String getEver_interest() {
        return ever_interest;
    }

    public void setEver_interest(String ever_interest) {
        this.ever_interest = ever_interest;
    }

    public String getModerate_fee() {
        return moderate_fee;
    }

    public void setModerate_fee(String moderate_fee) {
        this.moderate_fee = moderate_fee;
    }
    
    public String getDisplay_yn() {
        return display_yn;
    }

    public void setDisplay_yn(String display_yn) {
        this.display_yn = display_yn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
