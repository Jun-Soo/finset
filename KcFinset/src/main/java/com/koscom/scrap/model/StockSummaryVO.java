package com.koscom.scrap.model;

import java.io.Serializable;

public class StockSummaryVO implements Serializable{
	private static final long serialVersionUID = -5886080293745876797L;
	String no_person = "";			//회원관리번호
	String cd_fc = "";				//금융사코드
	String accno = "";				//가상계좌번호
	long seq = 0;					//일련번호
	String cashbalance = "";		//현금잔고
	String d1 = "";					//D+1잔고
	String d2 = "";					//D+2잔고
	String substitute = "";			//대용금
	String receivable = "";			//미수/미납금
	String subsmargin = "";			//대용증거금
	String loancredit = "";			//대출/신용금
	String valattrade = "";			//유가증권매수금액
	String valueatcur = "";			//유가증권평가금액
	String proloss = "";			//유가증권평가손익
	String totalaccval = "";		//총평가금액
	String cashavwithdraw = "";		//출금가능액
	String id_frt = "";				//최초입력아이디
	String dt_frt = "";				//최초입력시간
	String id_lst = "";				//최종수정아이디
	String dt_lst = "";				//최종수정시간
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public String getCashbalance() {
		return cashbalance;
	}
	public void setCashbalance(String cashbalance) {
		this.cashbalance = cashbalance;
	}
	public String getD1() {
		return d1;
	}
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public String getD2() {
		return d2;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	public String getSubstitute() {
		return substitute;
	}
	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}
	public String getReceivable() {
		return receivable;
	}
	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}
	public String getSubsmargin() {
		return subsmargin;
	}
	public void setSubsmargin(String subsmargin) {
		this.subsmargin = subsmargin;
	}
	public String getLoancredit() {
		return loancredit;
	}
	public void setLoancredit(String loancredit) {
		this.loancredit = loancredit;
	}
	public String getValattrade() {
		return valattrade;
	}
	public void setValattrade(String valattrade) {
		this.valattrade = valattrade;
	}
	public String getValueatcur() {
		return valueatcur;
	}
	public void setValueatcur(String valueatcur) {
		this.valueatcur = valueatcur;
	}
	public String getProloss() {
		return proloss;
	}
	public void setProloss(String proloss) {
		this.proloss = proloss;
	}
	public String getTotalaccval() {
		return totalaccval;
	}
	public void setTotalaccval(String totalaccval) {
		this.totalaccval = totalaccval;
	}
	public String getCashavwithdraw() {
		return cashavwithdraw;
	}
	public void setCashavwithdraw(String cashavwithdraw) {
		this.cashavwithdraw = cashavwithdraw;
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