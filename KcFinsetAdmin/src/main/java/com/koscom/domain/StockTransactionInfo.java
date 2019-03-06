package com.koscom.domain;

import java.io.Serializable;

public class StockTransactionInfo implements Serializable{
private static final long serialVersionUID = 2765273239683209207L;
	
	protected String no_person= "";	// 사용자일련번호
	protected String accno= "";	//가상계좌번호
	protected String secuno= "";	//증권사번호
	protected long changeamt;	//금액증감 (매도/매수/이체에 따른 금액변동)
	protected long changeqty;	//수량증감 (매도/매수량, 이체 시는 0)
	protected String isincode= "";	//종목코드 (입출금은 CASH로 표기)
	protected double qty;	//잔고수량 (거래 후 잔량)
	protected String transdate= "";	//거래일자
	protected String transtype= "";	//거래구분이며 값은:  BID(매 도), ASK(매수), DEP(이체입금), WID(이체출금)
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
	public long getChangeamt() {
		return changeamt;
	}
	public void setChangeamt(long changeamt) {
		this.changeamt = changeamt;
	}
	public long getChangeqty() {
		return changeqty;
	}
	public void setChangeqty(long changeqty) {
		this.changeqty = changeqty;
	}
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public String getTransdate() {
		return transdate;
	}
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
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
