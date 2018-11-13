package com.koscom.scrap.model;

import java.io.Serializable;

public class StockEtclistVO implements Serializable{
	private static final long serialVersionUID = 5392441454941664164L;
	String no_person = "";			//회원관리번호
	String cd_fc = "";				//금융사코드
	String accno = "";				//가상계좌번호
	long seq = 0;					//일련번호
	String assettype = "";			//상품구분자
	String assetname = "";			//상품명
	String isincode = "";			//종목코드
	String qty = "";				//수량 또는 비중
	String tradetype = "";			//잔고구분
	String valattrade = "";			//매수금액
	String valatcur = "";			//평가금액
	String earningrate = "";		//수익률
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
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public String getValattrade() {
		return valattrade;
	}
	public void setValattrade(String valattrade) {
		this.valattrade = valattrade;
	}
	public String getValatcur() {
		return valatcur;
	}
	public void setValatcur(String valatcur) {
		this.valatcur = valatcur;
	}
	public String getEarningrate() {
		return earningrate;
	}
	public void setEarningrate(String earningrate) {
		this.earningrate = earningrate;
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