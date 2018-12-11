package com.koscom.assets.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.comm.model.SearchForm;

public class AssetsForm extends SearchForm implements Serializable{

	private static final long serialVersionUID = -7048381305403047820L;

	private String no_person;
	private String no_account;
	private String type_list;
	//은행 메인
	private List<String> person_share_list;
	//계좌상세
	private String scKeyword;
	private String scTrnsType;
	//입출금내역
	private String scAccount;
	private String yn_share;
	//입출금상세
	private String dt_trd;
	private String tm_trd;

	//증권
	private String scAccType; //분류
	private String orderBy; //정렬
	private String scCompany; //증권사
	private String acc_code; //상품코드

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_account() {
		return no_account;
	}
	public void setNo_account(String no_account) {
		this.no_account = no_account;
	}
	public String getType_list() {
		return type_list;
	}
	public void setType_list(String type_list) {
		this.type_list = type_list;
	}
	public List<String> getPerson_share_list() {
		return person_share_list;
	}
	public void setPerson_share_list(List<String> person_share_list) {
		this.person_share_list = person_share_list;
	}
	public String getScKeyword() {
		return scKeyword;
	}
	public void setScKeyword(String scKeyword) {
		this.scKeyword = scKeyword;
	}
	public String getScTrnsType() {
		return scTrnsType;
	}
	public void setScTrnsType(String scTrnsType) {
		this.scTrnsType = scTrnsType;
	}
	public String getScAccount() {
		return scAccount;
	}
	public void setScAccount(String scAccount) {
		this.scAccount = scAccount;
	}
	public String getYn_share() {
		return yn_share;
	}
	public void setYn_share(String yn_share) {
		this.yn_share = yn_share;
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
	public String getScAccType() {
		return scAccType;
	}
	public void setScAccType(String scAccType) {
		this.scAccType = scAccType;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getScCompany() {
		return scCompany;
	}
	public void setScCompany(String scCompany) {
		this.scCompany = scCompany;
	}
	public String getAcc_code() {
		return acc_code;
	}
	public void setAcc_code(String acc_code) {
		this.acc_code = acc_code;
	}

}
