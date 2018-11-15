package com.koscom.scrapData.model;

import java.io.Serializable;

public class AssetsInfoDataVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5166033122507585874L;

	//금융사 계좌정보T
	private String no_person; //회원관리번호
	private String an; //계좌번호
	private String cd_fc; //금융사코드
	private String type_an; //계좌종류(01:입출금계좌 / 02:예적금계좌 /03:대출계좌 / 04:펀드계좌 /05:외화계좌)
	private String nm_an; //계좌명
	private String cd_currency; //통화코드
	private String principal; //투자원금
	private String amt_available; //출금가능금액
	private String current_balance; //현재잔액
	private String loan_balance; //대출잔액
	private String loan_ceiling; //대출한도액
	private String dt_new; //신규일자
	private String dt_end; //만기일자
	private String interest_rate; //금리
	private String profit_rate; //수익율
	private String few_days; //최종이수일자
	private String interest_date; //이자납입일
	private String yn_delete; //삭제여부
	private String yn_use; //사용여부
	private String dt_frt; //최초입력시간

	//자산관리T
    private String cd_assets_class; //자산분류코드(01 입출금 / 02 예금 / 03 적금 / 04 펀드 / 05 외환 / 06 증권 / 07 연금 / 08 보험 / 09 부동산)
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
    private String id_lst; //최종수정아이디
    private String dt_lst; //최종수정시간

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getType_an() {
		return type_an;
	}

	public void setType_an(String type_an) {
		this.type_an = type_an;
	}

	public String getNm_an() {
		return nm_an;
	}

	public void setNm_an(String nm_an) {
		this.nm_an = nm_an;
	}

	public String getCd_currency() {
		return cd_currency;
	}

	public void setCd_currency(String cd_currency) {
		this.cd_currency = cd_currency;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getAmt_available() {
		return amt_available;
	}

	public void setAmt_available(String amt_available) {
		this.amt_available = amt_available;
	}

	public String getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(String current_balance) {
		this.current_balance = current_balance;
	}

	public String getLoan_balance() {
		return loan_balance;
	}

	public void setLoan_balance(String loan_balance) {
		this.loan_balance = loan_balance;
	}

	public String getLoan_ceiling() {
		return loan_ceiling;
	}

	public void setLoan_ceiling(String loan_ceiling) {
		this.loan_ceiling = loan_ceiling;
	}

	public String getDt_new() {
		return dt_new;
	}

	public void setDt_new(String dt_new) {
		this.dt_new = dt_new;
	}

	public String getDt_end() {
		return dt_end;
	}

	public void setDt_end(String dt_end) {
		this.dt_end = dt_end;
	}

	public String getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}

	public String getProfit_rate() {
		return profit_rate;
	}

	public void setProfit_rate(String profit_rate) {
		this.profit_rate = profit_rate;
	}

	public String getFew_days() {
		return few_days;
	}

	public void setFew_days(String few_days) {
		this.few_days = few_days;
	}

	public String getInterest_date() {
		return interest_date;
	}

	public void setInterest_date(String interest_date) {
		this.interest_date = interest_date;
	}

	public String getYn_delete() {
		return yn_delete;
	}

	public void setYn_delete(String yn_delete) {
		this.yn_delete = yn_delete;
	}

	public String getYn_use() {
		return yn_use;
	}

	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}

	public String getDt_frt() {
		return dt_frt;
	}

	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	public String getCd_assets_class() {
		return cd_assets_class;
	}

	public void setCd_assets_class(String cd_assets_class) {
		this.cd_assets_class = cd_assets_class;
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

	@Override
	public String toString() {
		return "AssetsInfoVO [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
