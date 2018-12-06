package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ScrRespPensionPaymentdtlVO implements Serializable{
	private static final long serialVersionUID = -2435120131374038421L;
	protected String no_person= "";				//회원고유번호
	@SerializedName("START_YYYYMM")
	protected String start_yyyymm= "";			//시작년월
	@SerializedName("END_YYYYMM")
	protected String end_yyyymm= "";			//종료년월
	@SerializedName("AMT_BASE_INCOME_MONTH")
	protected String amt_base_income_month= "";	//기준소득월액
	@SerializedName("CNT_MONTH_PAY")
	protected String cnt_month_pay= "";			//납부한보험료월수
	@SerializedName("AMT_PAY")
	protected String amt_pay= "";				//납부한보험료금액
	@SerializedName("CNT_MONTH_NOT_PAY")
	protected String cnt_month_not_pay= "";		//납부하지않은월수
	@SerializedName("AMT_NOT_PAY")
	protected String amt_not_pay= "";			//납부하지않은금액
	@SerializedName("TYPE")
	protected String type= "";					//종별
	@SerializedName("ETC")
	protected String etc= "";					//비고
	protected String dt_frt= "";				//등록일시
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
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
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
