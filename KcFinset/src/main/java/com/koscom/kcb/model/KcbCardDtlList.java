package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbCardDtlList implements Serializable{

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;			//회원관리번호
	private String no_manage_info;		//정보관리번호
	private String cd_fc;				//금융사코드
	private String type_card;			//카드구분 - 00:알 수 없음, 01:신용, 02:체크, 03:혼합
	private String req_yyyymm;			//기준년월

	private String amt_total;			//총이용금액
	private String amt_lump_sum;		//신용일시불 이용금액
	private String amt_installment;		//신용할부 이용금액
	private String amt_short_card_loan;	//단기카드대출 이용금액
	private String amt_check;			//체크 이용금액
	private String amt_delay;			//연체금액
	private String cd_trade;			//거래상태코드


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
	public String getReq_yyyymm() {
		return req_yyyymm;
	}
	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
	}
	public String getAmt_total() {
		return amt_total;
	}
	public void setAmt_total(String amt_total) {
		this.amt_total = amt_total;
	}
	public String getAmt_lump_sum() {
		return amt_lump_sum;
	}
	public void setAmt_lump_sum(String amt_lump_sum) {
		this.amt_lump_sum = amt_lump_sum;
	}
	public String getAmt_installment() {
		return amt_installment;
	}
	public void setAmt_installment(String amt_installment) {
		this.amt_installment = amt_installment;
	}
	public String getAmt_short_card_loan() {
		return amt_short_card_loan;
	}
	public void setAmt_short_card_loan(String amt_short_card_loan) {
		this.amt_short_card_loan = amt_short_card_loan;
	}
	public String getAmt_check() {
		return amt_check;
	}
	public void setAmt_check(String amt_check) {
		this.amt_check = amt_check;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public String getCd_trade() {
		return cd_trade;
	}
	public void setCd_trade(String cd_trade) {
		this.cd_trade = cd_trade;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}