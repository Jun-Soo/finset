package com.koscom.batch.kcb.domain;

import java.io.Serializable;

public class KcbInfoVO implements Serializable {
	
	public static final long serialVersionUID = -8912406207356126390L;
	
	public String 			no_person; 		//회원관리번호

	/** 소득금액증명정보 CY01 **/
	public String 			income_division;		//소득구분코드
	public String 			reversion_year;			//귀속년도
	public String 			amt_income;				//기준소득금액
	public String 			amt_total_decision_tax;	//총결정세액
	public String 			corp_nm;				//법인명
	public String 			biz_licence;			//사업자등록번호
	
	/** 건강보험납부정보 CY02 **/
	public String 			pay_yyyy;						//납부년도
	public String 			pay_yyyymm;						//납부년월
	public String 			amt_nt_health_insu;				//건강보험고지보험료
	public String 			amt_nt_lngtrm_cr_ins;			//건강보험납부보험료
	public String 			amt_pay_health_insu;			//장기요양고지보험료
	public String 			amt_pay_longterm_care_insu;		//장기요양납부보험료
	public String 			amt_icnt_health_insu;			//소득건강보험고지보험료
	public String 			amt_icnt_lngtrm_cr_ins;			//소득건강보험납부보험료
	public String 			amt_icpay_health_insu;			//소득장기요양고지보험료
	public String 			amt_icpay_longterm_care_insu;	//소득장기요양납부보험료

	/** 국민연금납부정보 CY03 **/
	public String          start_yyyymm;			//납부기간시작년월
	public String          end_yyyymm;				//납부기간종료년월
	public String          amt_base_income_month;	//기준소득금액
	public String          cnt_month_pay;			//보험료납부월수
	public String          amt_pay;					//보험료납부금액
	public String          cnt_month_not_pay;		//보험료미납부월수
	public String          amt_not_pay;				//보험료미납부금액
	public String          type;					//종별구분코드
	public String          etc;						//비고
	
	public KcbInfoVO() {}

	public KcbInfoVO(String no_person) {
		this.no_person = no_person;
	}

	public String getNo_person() {
		return no_person;
	}

	public String getIncome_division() {
		return income_division;
	}

	public void setIncome_division(String income_division) {
		this.income_division = income_division;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	
	public String getReversion_year() {
		return reversion_year;
	}

	public void setReversion_year(String reversion_year) {
		this.reversion_year = reversion_year;
	}

	public String getAmt_income() {
		return amt_income;
	}

	public void setAmt_income(String amt_income) {
		this.amt_income = amt_income;
	}

	public String getAmt_total_decision_tax() {
		return amt_total_decision_tax;
	}

	public void setAmt_total_decision_tax(String amt_total_decision_tax) {
		this.amt_total_decision_tax = amt_total_decision_tax;
	}

	public String getCorp_nm() {
		return corp_nm;
	}

	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}

	public String getBiz_licence() {
		return biz_licence;
	}

	public void setBiz_licence(String biz_licence) {
		this.biz_licence = biz_licence;
	}

	public String getPay_yyyy() {
		return pay_yyyy;
	}

	public void setPay_yyyy(String pay_yyyy) {
		this.pay_yyyy = pay_yyyy;
	}

	public String getPay_yyyymm() {
		return pay_yyyymm;
	}

	public void setPay_yyyymm(String pay_yyyymm) {
		this.pay_yyyymm = pay_yyyymm;
	}

	public String getAmt_nt_health_insu() {
		return amt_nt_health_insu;
	}

	public void setAmt_nt_health_insu(String amt_nt_health_insu) {
		this.amt_nt_health_insu = amt_nt_health_insu;
	}

	public String getAmt_nt_lngtrm_cr_ins() {
		return amt_nt_lngtrm_cr_ins;
	}

	public void setAmt_nt_lngtrm_cr_ins(String amt_nt_lngtrm_cr_ins) {
		this.amt_nt_lngtrm_cr_ins = amt_nt_lngtrm_cr_ins;
	}

	public String getAmt_pay_health_insu() {
		return amt_pay_health_insu;
	}

	public void setAmt_pay_health_insu(String amt_pay_health_insu) {
		this.amt_pay_health_insu = amt_pay_health_insu;
	}

	public String getAmt_pay_longterm_care_insu() {
		return amt_pay_longterm_care_insu;
	}

	public void setAmt_pay_longterm_care_insu(String amt_pay_longterm_care_insu) {
		this.amt_pay_longterm_care_insu = amt_pay_longterm_care_insu;
	}

	public String getAmt_icnt_health_insu() {
		return amt_icnt_health_insu;
	}

	public void setAmt_icnt_health_insu(String amt_icnt_health_insu) {
		this.amt_icnt_health_insu = amt_icnt_health_insu;
	}

	public String getAmt_icnt_lngtrm_cr_ins() {
		return amt_icnt_lngtrm_cr_ins;
	}

	public void setAmt_icnt_lngtrm_cr_ins(String amt_icnt_lngtrm_cr_ins) {
		this.amt_icnt_lngtrm_cr_ins = amt_icnt_lngtrm_cr_ins;
	}

	public String getAmt_icpay_health_insu() {
		return amt_icpay_health_insu;
	}

	public void setAmt_icpay_health_insu(String amt_icpay_health_insu) {
		this.amt_icpay_health_insu = amt_icpay_health_insu;
	}

	public String getAmt_icpay_longterm_care_insu() {
		return amt_icpay_longterm_care_insu;
	}

	public void setAmt_icpay_longterm_care_insu(String amt_icpay_longterm_care_insu) {
		this.amt_icpay_longterm_care_insu = amt_icpay_longterm_care_insu;
	}

	public String getStart_yyyymm() {
		return start_yyyymm;
	}

	public void setStart_yyyymm(String start_yyyymm) {
		this.start_yyyymm = start_yyyymm;
	}

	public String getEnd_yyyymm() {
		return end_yyyymm;
	}

	public void setEnd_yyyymm(String end_yyyymm) {
		this.end_yyyymm = end_yyyymm;
	}

	public String getAmt_base_income_month() {
		return amt_base_income_month;
	}

	public void setAmt_base_income_month(String amt_base_income_month) {
		this.amt_base_income_month = amt_base_income_month;
	}

	public String getCnt_month_pay() {
		return cnt_month_pay;
	}

	public void setCnt_month_pay(String cnt_month_pay) {
		this.cnt_month_pay = cnt_month_pay;
	}

	public String getAmt_pay() {
		return amt_pay;
	}

	public void setAmt_pay(String amt_pay) {
		this.amt_pay = amt_pay;
	}

	public String getCnt_month_not_pay() {
		return cnt_month_not_pay;
	}

	public void setCnt_month_not_pay(String cnt_month_not_pay) {
		this.cnt_month_not_pay = cnt_month_not_pay;
	}

	public String getAmt_not_pay() {
		return amt_not_pay;
	}

	public void setAmt_not_pay(String amt_not_pay) {
		this.amt_not_pay = amt_not_pay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "KcbInfoVO [no_person=" + no_person
				+ "]";
	}
}
