package com.koscom.debt.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 최근12개월 상환내역
 */
public class DebtDetail12RepVO implements Serializable{

    private static final long serialVersionUID = -8150626461527211168L;

    private String req_yyyymm  ; /* 기준월            */
    private String dt_st       ; /* 기준월일            */
    private String cd_state    ; /* 계좌상태	    */
    private String amt_repay   ; /* 상환금액(원금+이자) */
    private String amt_repay_p ; /* 상환금액(원금)      */
    private String amt_repay_i ; /* 상환금액(이자)      */

    public String getReq_yyyymm() {
        return req_yyyymm;
    }

    public void setReq_yyyymm(String req_yyyymm) {
        this.req_yyyymm = req_yyyymm;
    }

    public String getDt_st() {
        return dt_st;
    }

    public void setDt_st(String dt_st) {
        this.dt_st = dt_st;
    }

    public String getCd_state() {
        return cd_state;
    }

    public void setCd_state(String cd_state) {
        this.cd_state = cd_state;
    }

    public String getAmt_repay() {
        return amt_repay;
    }

    public void setAmt_repay(String amt_repay) {
        this.amt_repay = amt_repay;
    }

    public String getAmt_repay_p() {
        return amt_repay_p;
    }

    public void setAmt_repay_p(String amt_repay_p) {
        this.amt_repay_p = amt_repay_p;
    }

    public String getAmt_repay_i() {
        return amt_repay_i;
    }

    public void setAmt_repay_i(String amt_repay_i) {
        this.amt_repay_i = amt_repay_i;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
