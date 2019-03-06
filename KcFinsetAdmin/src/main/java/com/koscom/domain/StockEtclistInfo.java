package com.koscom.domain;

import java.io.Serializable;

public class StockEtclistInfo implements Serializable{
private static final long serialVersionUID = -7669246316199198179L;
    
	protected String no_person= "";	// 사용자일련번호
	protected String accno= "";	//가상계좌번호
	protected String secuno= "";	//증권사번호
	protected String assetname= "";	//상품명
	protected String assettype= "";	//상품구분자로 값은: BOND(채권), CD, CP, DLS, ELS, STB(사채), RP(미분류), CRP(약정 식RP), RRP(수시RP), WRT(워런트)
	protected double earningrate;	//수익률
	protected long qty;	//수량 또는 비중
	protected long valattrade;	//매수금액
	protected long valueatcur;	//평가금액
	protected String isincode= "";	//종목코드
	protected String tradetype= "";	//잔고구분: NRM(일반/현금), CRD(신용), LOAN(대출), SUM(분류가 불가한 경우 구 분 없이 합산한 경우며 대출잔고는 제 외)
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
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public double getEarningrate() {
		return earningrate;
	}
	public void setEarningrate(double earningrate) {
		this.earningrate = earningrate;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
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
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
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
