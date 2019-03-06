package com.koscom.debt.model;


import java.util.HashMap;
import java.util.List;

import com.koscom.domain.CreditInfo;

public class CrawlingLoanVO {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;       // 회원관리번호
	private String no_manage_info;  // 정보관리번호
	private String nm_biz;			// 상품명
	private String cd_type_deal;	// 거래형태코드 (대출형태)
	private String ymd_loan;		// 대출일자 (개설일자)
	private String ymd_loanend;		// 만기일자
	private String amt_contract; 	// 약정금액
	private String amt_remain; 		// 대출잔액
	private String cd_fc;

	private String yn_credit;		//신용여부
	private String yn_loan;			//담보여부
	private String yn_guarantor;	//보증인여부

	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;


	private List<CrawlingLoanDtlVO> crawlingLoanDtlList;	//계좌별 대출 월별내역

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_manage_info() {
		return no_manage_info;
	}
	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
	}
	public String getNm_biz() {
		return nm_biz;
	}
	public void setNm_biz(String nm_biz) {
		this.nm_biz = nm_biz;
	}
	public String getCd_type_deal() {
		return cd_type_deal;
	}
	public void setCd_type_deal(String cd_type_deal) {
		this.cd_type_deal = cd_type_deal;
	}
	public String getYmd_loan() {
		return ymd_loan;
	}
	public void setYmd_loan(String ymd_loan) {
		this.ymd_loan = ymd_loan;
	}
	public String getYmd_loanend() {
		return ymd_loanend;
	}
	public void setYmd_loanend(String ymd_loanend) {
		this.ymd_loanend = ymd_loanend;
	}
	public String getAmt_contract() {
		return amt_contract;
	}
	public void setAmt_contract(String amt_contract) {
		this.amt_contract = amt_contract;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public List<CrawlingLoanDtlVO> getCrawlingLoanDtlList() {
		return crawlingLoanDtlList;
	}
	public void setCrawlingLoanDtlList(List<CrawlingLoanDtlVO> crawlingLoanDtlList) {
		this.crawlingLoanDtlList = crawlingLoanDtlList;
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
	public String getYn_credit() {
		return yn_credit;
	}
	public void setYn_credit(String yn_credit) {
		this.yn_credit = yn_credit;
	}
	public String getYn_loan() {
		return yn_loan;
	}
	public void setYn_loan(String yn_loan) {
		this.yn_loan = yn_loan;
	}
	public String getYn_guarantor() {
		return yn_guarantor;
	}
	public void setYn_guarantor(String yn_guarantor) {
		this.yn_guarantor = yn_guarantor;
	}


}