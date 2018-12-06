package com.koscom.scrap.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ScrCardLimitInfoVO implements Serializable{
	private static final long serialVersionUID = 7819561685744287269L;
	protected String no_person= "";				//회원고유번호
	protected String cd_fc= "";					//금융사코드
	@SerializedName("TYPE_CARD")
	protected String type_card= "";				//카드종류
	@SerializedName("AMT_CARD_LIMIT")
	protected String amt_card_limit= "";		//카드한도금액
	@SerializedName("AMT_CARD_USE")
	protected String amt_card_use= "";			//카드한도사용금액
	@SerializedName("AMT_CARD_RESIDUAL")
	protected String amt_card_residual= "";		//카드한도잔여금액
	@SerializedName("AMT_LUMPSUM_LIMIT")
	protected String amt_lumpsum_limit= "";		//일시불한도금액
	@SerializedName("AMT_LUMPSUM_USE")
	protected String amt_lumpsum_use= "";		//일시불사용금액
	@SerializedName("AMT_LUMPSUM_RESIDUAL")
	protected String amt_lumpsum_residual= "";	//일시불잔여금액
	@SerializedName("AMT_ISTM_LIMIT")
	protected String amt_istm_limit= "";		//할부한도금액
	@SerializedName("AMT_ISTM_USE")
	protected String amt_istm_use= "";			//할부사용금액
	@SerializedName("AMT_ISTM_RESIDUAL")
	protected String amt_istm_residual= "";		//할부잔여금액
	@SerializedName("AMT_OVERSEA_LIMIT")
	protected String amt_oversea_limit= "";		//해외한도금액
	@SerializedName("AMT_OVERSEA_USE")
	protected String amt_oversea_use= "";		//해외사용금액
	@SerializedName("AMT_OVERSEA_RESIDUAL")
	protected String amt_oversea_residual= "";	//해외잔여금액
	@SerializedName("AMT_CASH_LIMIT")
	protected String amt_cash_limit= "";		//현금서비스한도금액
	@SerializedName("AMT_CASH_USE")
	protected String amt_cash_use= "";			//현금서비스사용금액
	@SerializedName("AMT_CASH_RESIDUAL")
	protected String amt_cash_residual= "";		//현금서비스잔여금액
	@SerializedName("CD_CURRENCY")
	protected String cd_currency= "";			//통화코드
	protected String yn_delete= "";				//삭제여부
	protected String dt_frt= "";				//최초입력시간
	protected String dt_lst= "";				//최종수정시간
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getType_card() {
		return type_card;
	}
	public void setType_card(String type_card) {
		this.type_card = type_card;
	}
	public String getAmt_card_limit() {
		return amt_card_limit;
	}
	public void setAmt_card_limit(String amt_card_limit) {
		this.amt_card_limit = amt_card_limit;
	}
	public String getAmt_card_use() {
		return amt_card_use;
	}
	public void setAmt_card_use(String amt_card_use) {
		this.amt_card_use = amt_card_use;
	}
	public String getAmt_card_residual() {
		return amt_card_residual;
	}
	public void setAmt_card_residual(String amt_card_residual) {
		this.amt_card_residual = amt_card_residual;
	}
	public String getAmt_lumpsum_limit() {
		return amt_lumpsum_limit;
	}
	public void setAmt_lumpsum_limit(String amt_lumpsum_limit) {
		this.amt_lumpsum_limit = amt_lumpsum_limit;
	}
	public String getAmt_lumpsum_use() {
		return amt_lumpsum_use;
	}
	public void setAmt_lumpsum_use(String amt_lumpsum_use) {
		this.amt_lumpsum_use = amt_lumpsum_use;
	}
	public String getAmt_lumpsum_residual() {
		return amt_lumpsum_residual;
	}
	public void setAmt_lumpsum_residual(String amt_lumpsum_residual) {
		this.amt_lumpsum_residual = amt_lumpsum_residual;
	}
	public String getAmt_istm_limit() {
		return amt_istm_limit;
	}
	public void setAmt_istm_limit(String amt_istm_limit) {
		this.amt_istm_limit = amt_istm_limit;
	}
	public String getAmt_istm_use() {
		return amt_istm_use;
	}
	public void setAmt_istm_use(String amt_istm_use) {
		this.amt_istm_use = amt_istm_use;
	}
	public String getAmt_istm_residual() {
		return amt_istm_residual;
	}
	public void setAmt_istm_residual(String amt_istm_residual) {
		this.amt_istm_residual = amt_istm_residual;
	}
	public String getAmt_oversea_limit() {
		return amt_oversea_limit;
	}
	public void setAmt_oversea_limit(String amt_oversea_limit) {
		this.amt_oversea_limit = amt_oversea_limit;
	}
	public String getAmt_oversea_use() {
		return amt_oversea_use;
	}
	public void setAmt_oversea_use(String amt_oversea_use) {
		this.amt_oversea_use = amt_oversea_use;
	}
	public String getAmt_oversea_residual() {
		return amt_oversea_residual;
	}
	public void setAmt_oversea_residual(String amt_oversea_residual) {
		this.amt_oversea_residual = amt_oversea_residual;
	}
	public String getAmt_cash_limit() {
		return amt_cash_limit;
	}
	public void setAmt_cash_limit(String amt_cash_limit) {
		this.amt_cash_limit = amt_cash_limit;
	}
	public String getAmt_cash_use() {
		return amt_cash_use;
	}
	public void setAmt_cash_use(String amt_cash_use) {
		this.amt_cash_use = amt_cash_use;
	}
	public String getAmt_cash_residual() {
		return amt_cash_residual;
	}
	public void setAmt_cash_residual(String amt_cash_residual) {
		this.amt_cash_residual = amt_cash_residual;
	}
	public String getCd_currency() {
		return cd_currency;
	}
	public void setCd_currency(String cd_currency) {
		this.cd_currency = cd_currency;
	}
	public String getYn_delete() {
		return yn_delete;
	}
	public void setYn_delete(String yn_delete) {
		this.yn_delete = yn_delete;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
}
