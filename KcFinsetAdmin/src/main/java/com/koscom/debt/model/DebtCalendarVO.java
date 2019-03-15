package com.koscom.debt.model;

import java.io.Serializable;

public class DebtCalendarVO implements Serializable{
	
	private static final long serialVersionUID = 2222693108938401173L;
	
	/*달력*/
	private String no_manage_info    ;    /*정보관리번호*/
    private String cd_fc             ;    /*금융사코드*/
    private String nm_fc             ;    /*금융사명*/
    private String amt_repay         ;    /*당월 결제금액*/
    private String interest_ymd      ;    /*이자계산일*/
    private String payment_ymd       ;    /*이자납입일*/
    private String pay_yyyy          ;    /*이자납입일 연도*/
    private String pay_mm            ;    /*이자납입일 월*/
    private String pay_dd            ;    /*이자납입일 일*/
    private String pay_type          ;    /*납입종류*/
    private String pay_type_nm       ;    /*납입종류명*/

    public String getNo_manage_info() {
        return no_manage_info;
    }

    public void setNo_manage_info(String no_manage_info) {
        this.no_manage_info = no_manage_info;
    }


    public String getCd_fc() {
        return cd_fc;
    }

    public void setCd_fc(String cd_fc) {
        this.cd_fc = cd_fc;
    }

    public String getNm_fc() {
        return nm_fc;
    }

    public void setNm_fc(String nm_fc) {
        this.nm_fc = nm_fc;
    }

    public String getAmt_repay() {
        return amt_repay;
    }

    public void setAmt_repay(String amt_repay) {
        this.amt_repay = amt_repay;
    }

	public String getInterest_ymd() {
		return interest_ymd;
	}

	public void setInterest_ymd(String interest_ymd) {
		this.interest_ymd = interest_ymd;
	}

	public String getPayment_ymd() {
		return payment_ymd;
	}

	public void setPayment_ymd(String payment_ymd) {
		this.payment_ymd = payment_ymd;
	}

	public String getPay_yyyy() {
		return pay_yyyy;
	}

	public void setPay_yyyy(String pay_yyyy) {
		this.pay_yyyy = pay_yyyy;
	}

	public String getPay_mm() {
		return pay_mm;
	}

	public void setPay_mm(String pay_mm) {
		this.pay_mm = pay_mm;
	}

	public String getPay_dd() {
		return pay_dd;
	}

	public void setPay_dd(String pay_dd) {
		this.pay_dd = pay_dd;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_type_nm() {
		return pay_type_nm;
	}

	public void setPay_type_nm(String pay_type_nm) {
		this.pay_type_nm = pay_type_nm;
	}

}
