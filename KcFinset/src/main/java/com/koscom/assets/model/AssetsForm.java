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
	private String cd_detail_class;
	//입출금내역
	private String scAccount;
	private String yn_share;
	//입출금상세
	private String seq_tran;

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
	public String getCd_detail_class() {
		return cd_detail_class;
	}
	public void setCd_detail_class(String cd_detail_class) {
		this.cd_detail_class = cd_detail_class;
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
	public String getSeq_tran() {
		return seq_tran;
	}
	public void setSeq_tran(String seq_tran) {
		this.seq_tran = seq_tran;
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
