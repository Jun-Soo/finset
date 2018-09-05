package com.koscom.assets.model;

import java.io.Serializable;

public class AssetsInfoVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5166033122507585874L;

	//자산관리T
	private String no_person; //회원번호
    private String cd_assets_class; //자산분류코드(01 입출금 / 02 예금 / 03 적금 / 04 펀드 / 05 외환 / 06 증권 / 07 연금 / 08 보험 / 09 부동산)
    private String cd_fc; //금융사코드
    private String no_account; //계좌번호
    private String nm_account; //계좌명
    private String dt_open; //개설일자
    private String dt_expire; //만기일자
    private String amt_balance; //잔액
    private String amt_evaluation; //평가금액
    private String interest; //금리
    private String rate_return; //수익률
    private String yn_person_regist; //사용자등록여부
    private String id_frt; //최초입력아이디
    private String dt_frt; //최초입력시간
    private String id_lst; //최종수정아이디
    private String dt_lst; //최종수정시간
    private String total_balance; //총잔액
    private String cnt_account; //계좌건수

    //입출금내역
    private String dt_trd; //거래일자
    private String an; //계좌번호
    private String nm_an; //계좌명
    private String amt_dep; //입금액
    private String amt_wdrl; //출금액
    private String balance; //거래후잔액
    private String total_dep; //입금 총액
    private String total_wdrl; //출금 총액

	public String getNo_person() {
		return no_person;
	}


	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}


	public String getCd_assets_class() {
		return cd_assets_class;
	}


	public void setCd_assets_class(String cd_assets_class) {
		this.cd_assets_class = cd_assets_class;
	}


	public String getCd_fc() {
		return cd_fc;
	}


	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}


	public String getNo_account() {
		return no_account;
	}


	public void setNo_account(String no_account) {
		this.no_account = no_account;
	}


	public String getNm_account() {
		return nm_account;
	}


	public void setNm_account(String nm_account) {
		this.nm_account = nm_account;
	}


	public String getDt_open() {
		return dt_open;
	}


	public void setDt_open(String dt_open) {
		this.dt_open = dt_open;
	}


	public String getDt_expire() {
		return dt_expire;
	}


	public void setDt_expire(String dt_expire) {
		this.dt_expire = dt_expire;
	}


	public String getAmt_balance() {
		return amt_balance;
	}


	public void setAmt_balance(String amt_balance) {
		this.amt_balance = amt_balance;
	}


	public String getAmt_evaluation() {
		return amt_evaluation;
	}


	public void setAmt_evaluation(String amt_evaluation) {
		this.amt_evaluation = amt_evaluation;
	}


	public String getInterest() {
		return interest;
	}


	public void setInterest(String interest) {
		this.interest = interest;
	}


	public String getRate_return() {
		return rate_return;
	}


	public void setRate_return(String rate_return) {
		this.rate_return = rate_return;
	}


	public String getYn_person_regist() {
		return yn_person_regist;
	}


	public void setYn_person_regist(String yn_person_regist) {
		this.yn_person_regist = yn_person_regist;
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

	public String getTotal_balance() {
		return total_balance;
	}


	public void setTotal_balance(String total_balance) {
		this.total_balance = total_balance;
	}


	public String getCnt_account() {
		return cnt_account;
	}


	public void setCnt_account(String cnt_account) {
		this.cnt_account = cnt_account;
	}


	public String getDt_trd() {
		return dt_trd;
	}


	public void setDt_trd(String dt_trd) {
		this.dt_trd = dt_trd;
	}


	public String getAn() {
		return an;
	}


	public void setAn(String an) {
		this.an = an;
	}


	public String getNm_an() {
		return nm_an;
	}


	public void setNm_an(String nm_an) {
		this.nm_an = nm_an;
	}


	public String getAmt_dep() {
		return amt_dep;
	}


	public void setAmt_dep(String amt_dep) {
		this.amt_dep = amt_dep;
	}


	public String getAmt_wdrl() {
		return amt_wdrl;
	}


	public void setAmt_wdrl(String amt_wdrl) {
		this.amt_wdrl = amt_wdrl;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getTotal_dep() {
		return total_dep;
	}


	public void setTotal_dep(String total_dep) {
		this.total_dep = total_dep;
	}


	public String getTotal_wdrl() {
		return total_wdrl;
	}


	public void setTotal_wdrl(String total_wdrl) {
		this.total_wdrl = total_wdrl;
	}


	@Override
	public String toString() {
		return "AssetsInfoVO [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
