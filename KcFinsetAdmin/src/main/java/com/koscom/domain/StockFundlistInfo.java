package com.koscom.domain;

import java.io.Serializable;

public class StockFundlistInfo implements Serializable{
private static final long serialVersionUID = -7669841745596361794L;
    
	protected String no_person= "";	// 사용자일련번호
	protected String accno= "";	//가상계좌번호
	protected String secuno= "";	//증권사번호
	protected double earningrate;	//수익률
	protected String firstdatebuy= "";	//최초매수일
	protected String fundcode= "";	//펀드표준코드
	protected String fundname= "";	//펀드이름
	protected String lastdatebuy= "";	//최종매수일
	protected String maturity= "";	//만기일
	protected long proloss;	//평가손익
	protected long qty;	//수량 또는 비중
	protected long valatcur;	//평가금액
	protected long valattrade;	//매수금액
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
	public double getEarningrate() {
		return earningrate;
	}
	public void setEarningrate(double earningrate) {
		this.earningrate = earningrate;
	}
	public String getFirstdatebuy() {
		return firstdatebuy;
	}
	public void setFirstdatebuy(String firstdatebuy) {
		this.firstdatebuy = firstdatebuy;
	}
	public String getFundcode() {
		return fundcode;
	}
	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}
	public String getFundname() {
		return fundname;
	}
	public void setFundname(String fundname) {
		this.fundname = fundname;
	}
	public String getLastdatebuy() {
		return lastdatebuy;
	}
	public void setLastdatebuy(String lastdatebuy) {
		this.lastdatebuy = lastdatebuy;
	}
	public String getMaturity() {
		return maturity;
	}
	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}
	public long getProloss() {
		return proloss;
	}
	public void setProloss(long proloss) {
		this.proloss = proloss;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	public long getValatcur() {
		return valatcur;
	}
	public void setValatcur(long valatcur) {
		this.valatcur = valatcur;
	}
	public long getValattrade() {
		return valattrade;
	}
	public void setValattrade(long valattrade) {
		this.valattrade = valattrade;
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
