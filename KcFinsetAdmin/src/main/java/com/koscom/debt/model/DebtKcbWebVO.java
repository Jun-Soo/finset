package com.koscom.debt.model;

import java.io.Serializable;

public class DebtKcbWebVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5415901828410069592L;

	private int	seq_req;					//요청 시퀀스 (부채정보 만의 시퀀스)
	
	private String no_manage = "";			//관리번호 (300번 전문 030 segment관리번호)
	private String no_person = "";			
	private String nm_biz = "";				//기관명
	private String nm_loan_type = "";		//대출형태	(일시상환,,)
	private String ymd_loan = "";			//개설일자
	private String ymd_loadend = "";		//만기일자
	private String amt_contract = "";		//약정금액
	private String nm_loan_gubun = "";		//거래구분 (보증,담보,신용)
	
	private String ym_use = "";				//이용년월	YYYYMM
	private String amt_repay ="";			//상환금액
	private String amt_remain = ""; 		//대출잔액
	private String amt_delay = "";			//연체금액
	private String nm_account_status = "";	//계좌상태	(정상)

	private String id_frt= "";				//ADMIN
	private String dt_frt= "";				//ADMIN
	private String id_lst= "";				//ADMIN
	private String dt_lst= "";				//ADMIN
	
	public int getSeq_req() {
		return seq_req;
	}
	public String getNo_person() {
		return no_person;
	}
	public String getNm_biz() {
		return nm_biz;
	}
	public String getNm_loan_type() {
		return nm_loan_type;
	}
	public String getYmd_loan() {
		return ymd_loan;
	}
	public String getYmd_loadend() {
		return ymd_loadend;
	}
	public String getAmt_contract() {
		return amt_contract;
	}
	public String getNm_loan_gubun() {
		return nm_loan_gubun;
	}
	public String getYm_use() {
		return ym_use;
	}
	public String getAmt_repay() {
		return amt_repay;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public String getNm_account_status() {
		return nm_account_status;
	}
	public String getId_frt() {
		return id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setSeq_req(int no_bunch) {
		this.seq_req = seq_req;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public void setNm_biz(String nm_biz) {
		this.nm_biz = nm_biz;
	}
	public void setNm_loan_type(String nm_loan_type) {
		this.nm_loan_type = nm_loan_type;
	}
	public void setYmd_loan(String ymd_loan) {
		this.ymd_loan = ymd_loan;
	}
	public void setYmd_loadend(String ymd_loadend) {
		this.ymd_loadend = ymd_loadend;
	}
	public void setAmt_contract(String amt_contract) {
		this.amt_contract = amt_contract;
	}
	public void setNm_loan_gubun(String nm_loan_gubun) {
		this.nm_loan_gubun = nm_loan_gubun;
	}
	public void setYm_use(String ym_use) {
		this.ym_use = ym_use;
	}
	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public void setNm_account_status(String nm_account_status) {
		this.nm_account_status = nm_account_status;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	public String getNo_manage() {
		return no_manage;
	}
	public void setNo_manage(String no_manage) {
		this.no_manage = no_manage;
	}

}
