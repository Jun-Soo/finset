package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StockSummaryInfo implements Serializable{
private static final long serialVersionUID = -6952308496195239238L;
	
	protected String no_person= "";	// 사용자일련번호
	protected String accno= "";	//가상계좌번호
	protected String secuno= "";	//증권사번호
	protected long cashavwithdraw;	//출금가능액
	protected long cashbalance;	//현금잔고
	protected long d1;	//D+1잔고
	protected long d2;	//D+2잔고
	protected long loancredit;	//대출/신용금
	protected long proloss;	//유가증권평가손익
	protected long receivable;	// 미수/미납금
	protected long subsmargin;	// 대용증거금
	protected long substitute;	// 대용금
	protected long totalaccval;	//총평가금액
	protected long valattrade;	//유가증권매수금액
	protected long valueatcur;	//유가증권평가금액
	protected String id_frt= "";	//admin
	protected String dt_frt= "";	// admin
	protected String id_lst= "";	// admin
	protected String dt_lst= "";	// admin
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getSecuno() {
		return secuno;
	}
	public void setSecuno(String secuno) {
		this.secuno = secuno;
	}
	public long getCashavwithdraw() {
		return cashavwithdraw;
	}
	public void setCashavwithdraw(long cashavwithdraw) {
		this.cashavwithdraw = cashavwithdraw;
	}
	public long getCashbalance() {
		return cashbalance;
	}
	public void setCashbalance(long cashbalance) {
		this.cashbalance = cashbalance;
	}
	public long getD1() {
		return d1;
	}
	public void setD1(long d1) {
		this.d1 = d1;
	}
	public long getD2() {
		return d2;
	}
	public void setD2(long d2) {
		this.d2 = d2;
	}
	public long getLoancredit() {
		return loancredit;
	}
	public void setLoancredit(long loancredit) {
		this.loancredit = loancredit;
	}
	public long getProloss() {
		return proloss;
	}
	public void setProloss(long proloss) {
		this.proloss = proloss;
	}
	public long getReceivable() {
		return receivable;
	}
	public void setReceivable(long receivable) {
		this.receivable = receivable;
	}
	public long getSubsmargin() {
		return subsmargin;
	}
	public void setSubsmargin(long subsmargin) {
		this.subsmargin = subsmargin;
	}
	public long getSubstitute() {
		return substitute;
	}
	public void setSubstitute(long substitute) {
		this.substitute = substitute;
	}
	public long getTotalaccval() {
		return totalaccval;
	}
	public void setTotalaccval(long totalaccval) {
		this.totalaccval = totalaccval;
	}
	public long getValattrade() {
		return valattrade;
	}
	public void setValattrade(long valattrade) {
		this.valattrade = valattrade;
	}
	public long getValueatcur() {
		return valueatcur;
	}
	public void setValueatcur(long valueatcur) {
		this.valueatcur = valueatcur;
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
}
