package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ScrRespPensionPaymentVO implements Serializable{
	private static final long serialVersionUID = 6451958168174096326L;
	protected String no_person= "";				//회원고유번호
	@SerializedName("CNT_MONTH_PAY")
	protected String cnt_month_pay= "";			//납부한월수
	@SerializedName("AMT_PAY")
	protected String amt_pay= "";				//납부한금액
	@SerializedName("CNT_MONTH_NOT_PAY")
	protected String cnt_month_not_pay= "";		//납부하지않은월수
	@SerializedName("AMT_NOT_PAY")
	protected String amt_not_pay= "";			//납부하지않은금액
	@SerializedName("CNT_MONTH_CANNOT_PAY")
	protected String cnt_month_cannot_pay= "";	//납부할수없는월수
	@SerializedName("AMT_CANNOT_PAY")
	protected String amt_cannot_pay= "";		//납부할수없는금액
	@SerializedName("AMT_PAY_RETURN")
	protected String amt_pay_return= "";		//반납금납부액
	@SerializedName("AMT_PAY_PRIMIUM")
	protected String amt_pay_primium= "";		//추납보험료납부액
	@SerializedName("AMT_EST_PNS_MONTH")
	protected String amt_est_pns_month= "";		//예상연금액월
	@SerializedName("AMT_EST_PNS_YEAR")
	protected String amt_est_pns_year= "";		//예상연금액년
	@SerializedName("START_RECEIPT_YYYYMM")
	protected String start_receipt_yyyymm= "";	//수령시작년월
	@SerializedName("EST_START_MBR_YYYYMM")
	protected String est_start_mbr_yyyymm= "";	//총예상가입시작기간
	@SerializedName("EST_END_MBR_YYYYMM")
	protected String est_end_mbr_yyyymm= "";	//총예상가입종료기간
	@SerializedName("CNT_MONTH_EST_MBR")
	protected String cnt_month_est_mbr= "";		//총예상가입개월
	@SerializedName("AMT_EST_PNS_PAY")
	protected String amt_est_pns_pay= "";		//총예상납부보험료
	protected String dt_frt= "";				//등록일시
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
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
	public String getCnt_month_cannot_pay() {
		return cnt_month_cannot_pay;
	}
	public void setCnt_month_cannot_pay(String cnt_month_cannot_pay) {
		this.cnt_month_cannot_pay = cnt_month_cannot_pay;
	}
	public String getAmt_cannot_pay() {
		return amt_cannot_pay;
	}
	public void setAmt_cannot_pay(String amt_cannot_pay) {
		this.amt_cannot_pay = amt_cannot_pay;
	}
	public String getAmt_pay_return() {
		return amt_pay_return;
	}
	public void setAmt_pay_return(String amt_pay_return) {
		this.amt_pay_return = amt_pay_return;
	}
	public String getAmt_pay_primium() {
		return amt_pay_primium;
	}
	public void setAmt_pay_primium(String amt_pay_primium) {
		this.amt_pay_primium = amt_pay_primium;
	}
	public String getAmt_est_pns_month() {
		return amt_est_pns_month;
	}
	public void setAmt_est_pns_month(String amt_est_pns_month) {
		this.amt_est_pns_month = amt_est_pns_month;
	}
	public String getAmt_est_pns_year() {
		return amt_est_pns_year;
	}
	public void setAmt_est_pns_year(String amt_est_pns_year) {
		this.amt_est_pns_year = amt_est_pns_year;
	}
	public String getStart_receipt_yyyymm() {
		return start_receipt_yyyymm;
	}
	public void setStart_receipt_yyyymm(String start_receipt_yyyymm) {
		this.start_receipt_yyyymm = start_receipt_yyyymm;
	}
	public String getEst_start_mbr_yyyymm() {
		return est_start_mbr_yyyymm;
	}
	public void setEst_start_mbr_yyyymm(String est_start_mbr_yyyymm) {
		this.est_start_mbr_yyyymm = est_start_mbr_yyyymm;
	}
	public String getEst_end_mbr_yyyymm() {
		return est_end_mbr_yyyymm;
	}
	public void setEst_end_mbr_yyyymm(String est_end_mbr_yyyymm) {
		this.est_end_mbr_yyyymm = est_end_mbr_yyyymm;
	}
	public String getCnt_month_est_mbr() {
		return cnt_month_est_mbr;
	}
	public void setCnt_month_est_mbr(String cnt_month_est_mbr) {
		this.cnt_month_est_mbr = cnt_month_est_mbr;
	}
	public String getAmt_est_pns_pay() {
		return amt_est_pns_pay;
	}
	public void setAmt_est_pns_pay(String amt_est_pns_pay) {
		this.amt_est_pns_pay = amt_est_pns_pay;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}
