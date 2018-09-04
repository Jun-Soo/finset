package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonTransDetailVO implements Serializable{

	private static final long serialVersionUID = 5967402064922491315L;

	private String 	an;			//게좌번호
	private String 	dt_trd;		//날짜
	private String 	tm_trd;		//시간
	private String 	amt_wdrl;	//출금액
	private String 	amt_dep;	//입금액
	private String 	doc1;		//내용1
	private String 	doc2;		//내용2
	private String 	cd_fc;		//금융사코드
	private String 	nm_fc;		//금융사명
	
	private String 	id_frt;
	private Date	dt_frt;
	private String	id_lst;
	private Date	dt_lst;
	
	public PersonTransDetailVO() {
	}
	public PersonTransDetailVO(String an, String dt_trd, String tm_trd,
			String amt_wdrl, String amt_dep, String doc1, String doc2,
			String cd_fc, String nm_fc, String id_frt, Date dt_frt,
			String id_lst, Date dt_lst) {
		this.an = an;
		this.dt_trd = dt_trd;
		this.tm_trd = tm_trd;
		this.amt_wdrl = amt_wdrl;
		this.amt_dep = amt_dep;
		this.doc1 = doc1;
		this.doc2 = doc2;
		this.cd_fc = cd_fc;
		this.nm_fc = nm_fc;
		this.id_frt = id_frt;
		this.dt_frt = dt_frt;
		this.id_lst = id_lst;
		this.dt_lst = dt_lst;
	}
	
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}
	public String getDt_trd() {
		return dt_trd;
	}
	public void setDt_trd(String dt_trd) {
		this.dt_trd = dt_trd;
	}
	public String getTm_trd() {
		return tm_trd;
	}
	public void setTm_trd(String tm_trd) {
		this.tm_trd = tm_trd;
	}
	public String getAmt_wdrl() {
		return amt_wdrl;
	}
	public void setAmt_wdrl(String amt_wdrl) {
		this.amt_wdrl = amt_wdrl;
	}
	public String getAmt_dep() {
		return amt_dep;
	}
	public void setAmt_dep(String amt_dep) {
		this.amt_dep = amt_dep;
	}
	public String getDoc1() {
		return doc1;
	}
	public void setDoc1(String doc1) {
		this.doc1 = doc1;
	}
	public String getDoc2() {
		return doc2;
	}
	public void setDoc2(String doc2) {
		this.doc2 = doc2;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public Date getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(Date dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public Date getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(Date dt_lst) {
		this.dt_lst = dt_lst;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}