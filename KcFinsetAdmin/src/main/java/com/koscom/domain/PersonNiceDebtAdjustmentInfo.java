package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceDebtAdjustmentInfo implements Serializable {
	private static final long serialVersionUID = 3839391109690091428L;
	//채무조정 및 상환정보
	//NICE_DEBT_ADJUSTMENT_INFO
	protected String no_nice_debt_adjustment;//
	protected String no_person                       ;//
	protected String occur_institution		;//발생기관명
	protected String status_account			;//계좌상태
	protected String dt_start				;//개설일
	protected String amt_all_frt			;//개설총금액
	protected String amt_remain				;//잔액
	protected String dt_frt_delay			;//최초연체발생일
	protected String cnt_occur_delay_dt			;//연체발생일 수
	protected String cnt_all_pay			;//총납입회차
	protected String cnt_real_pay			;//실납입회차
	protected String cnt_delay				;//연체회차
	protected String amt_delay				;//연체금액
	protected String yn_reduce_debt			;//채무감면유무
	protected String id_frt                          ;//
	protected String dt_frt                          ;//
	protected String id_lst                          ;//
	protected String dt_lst                          ;//	
	public String getNo_nice_debt_adjustment() {
		return no_nice_debt_adjustment;
	}
	public void setNo_nice_debt_adjustment(String no_nice_debt_adjustment) {
		this.no_nice_debt_adjustment = no_nice_debt_adjustment;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getOccur_institution() {
		return occur_institution;
	}
	public void setOccur_institution(String occur_institution) {
		this.occur_institution = occur_institution;
	}
	public String getStatus_account() {
		return status_account;
	}
	public void setStatus_account(String status_account) {
		this.status_account = status_account;
	}
	public String getDt_start() {
		return dt_start;
	}
	public void setDt_start(String dt_start) {
		this.dt_start = dt_start;
	}
	public String getAmt_all_frt() {
		return amt_all_frt;
	}
	public void setAmt_all_frt(String amt_all_frt) {
		this.amt_all_frt = amt_all_frt;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getDt_frt_delay() {
		return dt_frt_delay;
	}
	public void setDt_frt_delay(String dt_frt_delay) {
		this.dt_frt_delay = dt_frt_delay;
	}
	public String getCnt_occur_delay_dt() {
		return cnt_occur_delay_dt;
	}
	public void setCnt_occur_delay_dt(String cnt_occur_delay_dt) {
		this.cnt_occur_delay_dt = cnt_occur_delay_dt;
	}
	public String getCnt_all_pay() {
		return cnt_all_pay;
	}
	public void setCnt_all_pay(String cnt_all_pay) {
		this.cnt_all_pay = cnt_all_pay;
	}
	public String getCnt_real_pay() {
		return cnt_real_pay;
	}
	public void setCnt_real_pay(String cnt_real_pay) {
		this.cnt_real_pay = cnt_real_pay;
	}
	public String getCnt_delay() {
		return cnt_delay;
	}
	public void setCnt_delay(String cnt_delay) {
		this.cnt_delay = cnt_delay;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public String getYn_reduce_debt() {
		return yn_reduce_debt;
	}
	public void setYn_reduce_debt(String yn_reduce_debt) {
		this.yn_reduce_debt = yn_reduce_debt;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}