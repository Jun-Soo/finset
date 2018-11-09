package com.koscom.domain;

import java.io.Serializable;

public class PersonShareInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 406678605351464944L;

	private String seq_share; //공유요청일련번호
	private String cd_share; //정보공유구분(01신용정보, 02금융정보)
	private String req_no_person; //요청회원관리번호
	private String req_nm_person; //요청회원명
	private String req_hp; //요청회원전화번호
	private String offer_no_person; //정보제공회원관리번호
	private String offer_nm_person; //정보제공회원명
	private String offer_hp; //정보제공회원전화번호
	private String share_status; //공유상태(01요청, 02승인(허용), 03거절, 04해지(공유취소,공유종료), 05요청취소)
	private String relations; //관계
	private String yn_credit_info; //신용정보여부
	private String yn_debt_info; //부채정보여부
	private String yn_income_info; //소득정보여부
	private String yn_asset_info; //자산정보여부
	private String yn_consume_info; //소비정보여부
	private String dt_stt_offer; //정보제공시작일
	private String dt_end_offer; //정보제공종료일
	private String id_frt; //최초입력아이디
	private String dt_frt; //최초입력시간
	private String id_lst; //최초수정아이디
	private String dt_lst; //최초수정시간

	//계좌list param
	private String rnum; //idx
	private String yn_share; //공유여부
	//자산
	private String no_account; //계좌번호
	private String nm_account; //계좌명
	//소비
	private String nm_code; //코드명
	private String code_value; //코드값
	private String no_card; //카드번호
	private String nm_card; //카드명
	//부채
	private String no_manage_info; //정보관리번호
	private String cd_fc; //금융사코드
	private String nm_fc; //금융사명
	private String debt_type; //대출종류
	private String amt_remain; //잔액

	public String getSeq_share() {
		return seq_share;
	}
	public void setSeq_share(String seq_share) {
		this.seq_share = seq_share;
	}
	public String getCd_share() {
		return cd_share;
	}
	public void setCd_share(String cd_share) {
		this.cd_share = cd_share;
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
	public String getRelations() {
		return relations;
	}
	public void setRelations(String relations) {
		this.relations = relations;
	}
	public String getYn_credit_info() {
		return yn_credit_info;
	}
	public void setYn_credit_info(String yn_credit_info) {
		this.yn_credit_info = yn_credit_info;
	}
	public String getYn_debt_info() {
		return yn_debt_info;
	}
	public void setYn_debt_info(String yn_debt_info) {
		this.yn_debt_info = yn_debt_info;
	}
	public String getYn_income_info() {
		return yn_income_info;
	}
	public void setYn_income_info(String yn_income_info) {
		this.yn_income_info = yn_income_info;
	}
	public String getYn_asset_info() {
		return yn_asset_info;
	}
	public void setYn_asset_info(String yn_asset_info) {
		this.yn_asset_info = yn_asset_info;
	}
	public String getYn_consume_info() {
		return yn_consume_info;
	}
	public void setYn_consume_info(String yn_consume_info) {
		this.yn_consume_info = yn_consume_info;
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
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getYn_share() {
		return yn_share;
	}
	public void setYn_share(String yn_share) {
		this.yn_share = yn_share;
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
	public String getNm_code() {
		return nm_code;
	}
	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
	}
	public String getNm_card() {
		return nm_card;
	}
	public void setNm_card(String nm_card) {
		this.nm_card = nm_card;
	}
	public String getNo_manage_info() {
		return no_manage_info;
	}
	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
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
	public String getDebt_type() {
		return debt_type;
	}
	public void setDebt_type(String debt_type) {
		this.debt_type = debt_type;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}


}
