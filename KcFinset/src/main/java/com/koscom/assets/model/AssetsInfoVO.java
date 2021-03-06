package com.koscom.assets.model;

import java.io.Serializable;
import java.util.List;

public class AssetsInfoVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5166033122507585874L;

	//자산관리T
	private String no_person; //회원번호
    private String cd_assets_class; //자산분류코드
    private String cd_detail_class; //상세분류코드
    private String cd_fc; //금융사코드
    private String no_account; //계좌번호
    private String nm_account; //계좌명
    private String dt_open; //개설일자
    private String dt_expire; //만기일자
    private String amt_balance; //잔액
    private String amt_evaluation; //평가금액
    private String interest; //금리
    private String rate_return; //수익률
    private String real_estate_addr; //부동산주소
    private String nm_model; //자동차모델명
    private String amount_jewelry; //귀금속보유량
    private String etc_assets; //기타자산
    private String memo; //메모
    private String yn_delete; //삭제여부
    private String yn_person_regist; //사용자등록여부
    private String sort; //정렬순서
    private String id_frt; //최초입력아이디
    private String dt_frt; //최초입력시간
    private String id_lst; //최종수정아이디
    private String dt_lst; //최종수정시간
    private String total_balance; //총잔액
    private String cnt_account; //계좌건수


    //입출금내역
    private String total_amt_dep; //입금 총액
    private String total_amt_wdrl; //출금 총액
    private String dt_trd; //거래일자
    private String tm_trd; //거래시간
    private String an; //계좌번호
    private String amt_dep; //입금액
    private String amt_wdrl; //출금액
    private String balance; //거래후잔액
    private String doc; //기재사항
    private String doc1; //기재사항1
    private String doc2; //기재사항2
    private String dealway1; //거래수단1
    private String dealway2; //거래수단2
    private String cd_trns; //거래구분
    private String nm_trns; //거래구분명

    private List<AssetsInfoVO> sortList;
    private String sum_amt_balance; //총잔액
    private String sum_amt_evaluation; //총평가금액
    private String nm_person; //회원명
    private String rk; //공유순서
    private String nm_fc; //금융기관명

    //수입/지출설정
    private String cd_class; //소비 - 카테고리
    private String nm_class; //소비 - 카테고리명
    private String cd_type; //소비 - 카테고리
    private String nm_type; //소비 - 카테고리명
    private String yn_auto; //소비 - 자동적용여부

    //기타메인
    private String nm_code; //코드명
    private String cnt_item; //항목개수

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

	public String getCd_detail_class() {
		return cd_detail_class;
	}

	public void setCd_detail_class(String cd_detail_class) {
		this.cd_detail_class = cd_detail_class;
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

	public String getReal_estate_addr() {
		return real_estate_addr;
	}

	public void setReal_estate_addr(String real_estate_addr) {
		this.real_estate_addr = real_estate_addr;
	}

	public String getNm_model() {
		return nm_model;
	}

	public void setNm_model(String nm_model) {
		this.nm_model = nm_model;
	}

	public String getAmount_jewelry() {
		return amount_jewelry;
	}

	public void setAmount_jewelry(String amount_jewelry) {
		this.amount_jewelry = amount_jewelry;
	}

	public String getEtc_assets() {
		return etc_assets;
	}

	public void setEtc_assets(String etc_assets) {
		this.etc_assets = etc_assets;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getYn_delete() {
		return yn_delete;
	}

	public void setYn_delete(String yn_delete) {
		this.yn_delete = yn_delete;
	}

	public String getYn_person_regist() {
		return yn_person_regist;
	}

	public void setYn_person_regist(String yn_person_regist) {
		this.yn_person_regist = yn_person_regist;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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

	public String getTotal_amt_dep() {
		return total_amt_dep;
	}

	public void setTotal_amt_dep(String total_amt_dep) {
		this.total_amt_dep = total_amt_dep;
	}

	public String getTotal_amt_wdrl() {
		return total_amt_wdrl;
	}

	public void setTotal_amt_wdrl(String total_amt_wdrl) {
		this.total_amt_wdrl = total_amt_wdrl;
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

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
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

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
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

	public String getDealway1() {
		return dealway1;
	}

	public void setDealway1(String dealway1) {
		this.dealway1 = dealway1;
	}

	public String getDealway2() {
		return dealway2;
	}

	public void setDealway2(String dealway2) {
		this.dealway2 = dealway2;
	}

	public String getCd_trns() {
		return cd_trns;
	}

	public void setCd_trns(String cd_trns) {
		this.cd_trns = cd_trns;
	}

	public String getNm_trns() {
		return nm_trns;
	}

	public void setNm_trns(String nm_trns) {
		this.nm_trns = nm_trns;
	}

	public List<AssetsInfoVO> getSortList() {
		return sortList;
	}

	public void setSortList(List<AssetsInfoVO> sortList) {
		this.sortList = sortList;
	}

	public String getSum_amt_balance() {
		return sum_amt_balance;
	}

	public void setSum_amt_balance(String sum_amt_balance) {
		this.sum_amt_balance = sum_amt_balance;
	}

	public String getSum_amt_evaluation() {
		return sum_amt_evaluation;
	}

	public void setSum_amt_evaluation(String sum_amt_evaluation) {
		this.sum_amt_evaluation = sum_amt_evaluation;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getRk() {
		return rk;
	}

	public void setRk(String rk) {
		this.rk = rk;
	}

	public String getNm_fc() {
		return nm_fc;
	}

	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}

	public String getCd_class() {
		return cd_class;
	}

	public void setCd_class(String cd_class) {
		this.cd_class = cd_class;
	}

	public String getNm_class() {
		return nm_class;
	}

	public void setNm_class(String nm_class) {
		this.nm_class = nm_class;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getNm_type() {
		return nm_type;
	}

	public void setNm_type(String nm_type) {
		this.nm_type = nm_type;
	}

	public String getYn_auto() {
		return yn_auto;
	}

	public void setYn_auto(String yn_auto) {
		this.yn_auto = yn_auto;
	}

	public String getNm_code() {
		return nm_code;
	}

	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}

	public String getCnt_item() {
		return cnt_item;
	}

	public void setCnt_item(String cnt_item) {
		this.cnt_item = cnt_item;
	}

	@Override
	public String toString() {
		return "AssetsInfoVO [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
