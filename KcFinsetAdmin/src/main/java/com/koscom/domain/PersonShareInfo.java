package com.koscom.domain;

import java.io.Serializable;

public class PersonShareInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 406678605351464944L;

	private String seq_share; //공유요청일련번호
	private String req_no_person; //요청회원관리번호
	private String req_nm_person; //요청회원명
	private String req_hp; //요청회원전화번호
	private String offer_no_person; //정보제공회원관리번호
	private String offer_nm_person; //정보제공회원명
	private String offer_hp; //정보제공회원전화번호
	private String share_status; //공유상태(01 요청, 02 승인(허용), 03 거절, 04 해지)
	private String yn_credit_offer=" "; //신용정보제공여부
	private String yn_debt_offer=" "; //대출정보제공여부
	private String yn_income_offer=" "; //소득정보제공여부
	private String yn_account_offer=" "; //계좌정보제공여부
	private String yn_card_offer=" "; //카드사용정보제공여부
	private String yn_debt_itgt_mngm=" "; //부채통합관리여부
	private String yn_asset_itgt_mngm=" "; //자산통합관리여부
	private String yn_consume_itgt_mngm=" "; //소비통합관리여부
	private String dt_stt_offer; //정보제공시작일
	private String dt_end_offer; //정보제공종료일
	private String id_frt; //최초입력아이디
	private String dt_frt; //최초입력시간
	private String id_lst; //최초수정아이디
	private String dt_lst; //최초수정시간

	private String yn_offer; //정보공유항목 존재여부

	public String getSeq_share() {
		return seq_share;
	}

	public void setSeq_share(String seq_share) {
		this.seq_share = seq_share;
	}

	public String getReq_no_person() {
		return req_no_person;
	}

	public void setReq_no_person(String req_no_person) {
		this.req_no_person = req_no_person;
	}

	public String getReq_nm_person() {
		return req_nm_person;
	}

	public void setReq_nm_person(String req_nm_person) {
		this.req_nm_person = req_nm_person;
	}

	public String getReq_hp() {
		return req_hp;
	}

	public void setReq_hp(String req_hp) {
		this.req_hp = req_hp;
	}

	public String getOffer_no_person() {
		return offer_no_person;
	}

	public void setOffer_no_person(String offer_no_person) {
		this.offer_no_person = offer_no_person;
	}

	public String getOffer_nm_person() {
		return offer_nm_person;
	}

	public void setOffer_nm_person(String offer_nm_person) {
		this.offer_nm_person = offer_nm_person;
	}

	public String getOffer_hp() {
		return offer_hp;
	}

	public void setOffer_hp(String offer_hp) {
		this.offer_hp = offer_hp;
	}

	public String getShare_status() {
		return share_status;
	}

	public void setShare_status(String share_status) {
		this.share_status = share_status;
	}

	public String getYn_credit_offer() {
		return yn_credit_offer;
	}

	public void setYn_credit_offer(String yn_credit_offer) {
		this.yn_credit_offer = yn_credit_offer;
	}

	public String getYn_debt_offer() {
		return yn_debt_offer;
	}

	public void setYn_debt_offer(String yn_debt_offer) {
		this.yn_debt_offer = yn_debt_offer;
	}

	public String getYn_income_offer() {
		return yn_income_offer;
	}

	public void setYn_income_offer(String yn_income_offer) {
		this.yn_income_offer = yn_income_offer;
	}

	public String getYn_account_offer() {
		return yn_account_offer;
	}

	public void setYn_account_offer(String yn_account_offer) {
		this.yn_account_offer = yn_account_offer;
	}

	public String getYn_card_offer() {
		return yn_card_offer;
	}

	public void setYn_card_offer(String yn_card_offer) {
		this.yn_card_offer = yn_card_offer;
	}

	public String getYn_debt_itgt_mngm() {
		return yn_debt_itgt_mngm;
	}

	public void setYn_debt_itgt_mngm(String yn_debt_itgt_mngm) {
		this.yn_debt_itgt_mngm = yn_debt_itgt_mngm;
	}

	public String getYn_asset_itgt_mngm() {
		return yn_asset_itgt_mngm;
	}

	public void setYn_asset_itgt_mngm(String yn_asset_itgt_mngm) {
		this.yn_asset_itgt_mngm = yn_asset_itgt_mngm;
	}

	public String getYn_consume_itgt_mngm() {
		return yn_consume_itgt_mngm;
	}

	public void setYn_consume_itgt_mngm(String yn_consume_itgt_mngm) {
		this.yn_consume_itgt_mngm = yn_consume_itgt_mngm;
	}

	public String getDt_stt_offer() {
		return dt_stt_offer;
	}

	public void setDt_stt_offer(String dt_stt_offer) {
		this.dt_stt_offer = dt_stt_offer;
	}

	public String getDt_end_offer() {
		return dt_end_offer;
	}

	public void setDt_end_offer(String dt_end_offer) {
		this.dt_end_offer = dt_end_offer;
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

	public String getYn_offer() {
		return yn_offer;
	}
	public void setYn_offer(String yn_offer) {
		this.yn_offer = yn_offer;
	}
}
