package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Kcb030Info implements Serializable{
	private static final long serialVersionUID = 4277313951904934809L;
	protected String seq_kcb_response= "";	//조회일련번호KCB_030_INFO
	protected String no_person= "";	//회원관리번호KCB_030_INFO
	protected String no_manage_info= "";	//정보관리번호KCB_030_INFO
	protected String nm_biz_type= "";	//업종명KCB_030_INFO
	protected String cd_type_deal= "";	//거래형태코드KCB_030_INFO
	protected String cd_use_fund= "";	//자금용도코드KCB_030_INFO
	protected String yn_credit= "";	//신용여부KCB_030_INFO
	protected String yn_loan= "";	//담보여부KCB_030_INFO
	protected String yn_guarantor= "";	//보증인여부KCB_030_INFO
	protected String amt_contract= "";	//약정금액KCB_030_INFO
	protected String ymd_loan= "";	//대출일자KCB_030_INFO
	protected String ymd_cancel= "";	//해지일자KCB_030_INFO
	protected String cd_cancel= "";	//해지사유코드KCB_030_INFO
	protected String cd_group_loans_proxy= "";	//집단대출대납구분코드KCB_030_INFO
	protected String yn_delay_turn_loan= "";	//연체대환대출여부KCB_030_INFO
	protected String yn_recovery= "";	//신용회복지원여부KCB_030_INFO
	protected String amt_remain= "";	//대출잔액KCB_030_INFO
	protected String amt_month_before1= "";	//1개월전연체금액KCB_030_INFO
	protected String amt_month_before2= "";	//2개월전연체금액KCB_030_INFO
	protected String amt_month_before3= "";	//3개월전연체금액KCB_030_INFO
	protected String amt_month_before4= "";	//4개월전연체금액KCB_030_INFO
	protected String amt_month_before5= "";	//5개월전연체금액KCB_030_INFO
	protected String amt_month_before6= "";	//6개월전연체금액KCB_030_INFO
	protected String amt_month_before7= "";	//7개월전연체금액KCB_030_INFO
	protected String amt_month_before8= "";	//8개월전연체금액KCB_030_INFO
	protected String amt_month_before9= "";	//9개월전연체금액KCB_030_INFO
	protected String amt_month_before10= "";	//10개월전연체금액KCB_030_INFO
	protected String amt_month_before11= "";	//11개월전연체금액KCB_030_INFO
	protected String amt_month_before12= "";	//12개월전연체금액KCB_030_INFO
	protected String cd_account_before1= "";	//1개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before2= "";	//2개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before3= "";	//3개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before4= "";	//4개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before5= "";	//5개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before6= "";	//6개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before7= "";	//7개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before8= "";	//8개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before9= "";	//9개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before10= "";	//10개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before11= "";	//11개월전계좌상태코드KCB_030_INFO
	protected String cd_account_before12= "";	//12개월전계좌상태코드KCB_030_INFO
	protected String filler= "";	//FILLERKCB_030_INFO
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSeq_kcb_response() {
		return seq_kcb_response;
	}
	public void setSeq_kcb_response(String seq_kcb_response) {
		this.seq_kcb_response = seq_kcb_response;
	}
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
	public String getNm_biz_type() {
		return nm_biz_type;
	}
	public void setNm_biz_type(String nm_biz_type) {
		this.nm_biz_type = nm_biz_type;
	}
	public String getCd_type_deal() {
		return cd_type_deal;
	}
	public void setCd_type_deal(String cd_type_deal) {
		this.cd_type_deal = cd_type_deal;
	}
	public String getCd_use_fund() {
		return cd_use_fund;
	}
	public void setCd_use_fund(String cd_use_fund) {
		this.cd_use_fund = cd_use_fund;
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
	public String getAmt_contract() {
		return amt_contract;
	}
	public void setAmt_contract(String amt_contract) {
		this.amt_contract = amt_contract;
	}
	public String getYmd_loan() {
		return ymd_loan;
	}
	public void setYmd_loan(String ymd_loan) {
		this.ymd_loan = ymd_loan;
	}
	public String getYmd_cancel() {
		return ymd_cancel;
	}
	public void setYmd_cancel(String ymd_cancel) {
		this.ymd_cancel = ymd_cancel;
	}
	public String getCd_cancel() {
		return cd_cancel;
	}
	public void setCd_cancel(String cd_cancel) {
		this.cd_cancel = cd_cancel;
	}
	public String getCd_group_loans_proxy() {
		return cd_group_loans_proxy;
	}
	public void setCd_group_loans_proxy(String cd_group_loans_proxy) {
		this.cd_group_loans_proxy = cd_group_loans_proxy;
	}
	public String getYn_delay_turn_loan() {
		return yn_delay_turn_loan;
	}
	public void setYn_delay_turn_loan(String yn_delay_turn_loan) {
		this.yn_delay_turn_loan = yn_delay_turn_loan;
	}
	public String getYn_recovery() {
		return yn_recovery;
	}
	public void setYn_recovery(String yn_recovery) {
		this.yn_recovery = yn_recovery;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getAmt_month_before1() {
		return amt_month_before1;
	}
	public void setAmt_month_before1(String amt_month_before1) {
		this.amt_month_before1 = amt_month_before1;
	}
	public String getAmt_month_before2() {
		return amt_month_before2;
	}
	public void setAmt_month_before2(String amt_month_before2) {
		this.amt_month_before2 = amt_month_before2;
	}
	public String getAmt_month_before3() {
		return amt_month_before3;
	}
	public void setAmt_month_before3(String amt_month_before3) {
		this.amt_month_before3 = amt_month_before3;
	}
	public String getAmt_month_before4() {
		return amt_month_before4;
	}
	public void setAmt_month_before4(String amt_month_before4) {
		this.amt_month_before4 = amt_month_before4;
	}
	public String getAmt_month_before5() {
		return amt_month_before5;
	}
	public void setAmt_month_before5(String amt_month_before5) {
		this.amt_month_before5 = amt_month_before5;
	}
	public String getAmt_month_before6() {
		return amt_month_before6;
	}
	public void setAmt_month_before6(String amt_month_before6) {
		this.amt_month_before6 = amt_month_before6;
	}
	public String getAmt_month_before7() {
		return amt_month_before7;
	}
	public void setAmt_month_before7(String amt_month_before7) {
		this.amt_month_before7 = amt_month_before7;
	}
	public String getAmt_month_before8() {
		return amt_month_before8;
	}
	public void setAmt_month_before8(String amt_month_before8) {
		this.amt_month_before8 = amt_month_before8;
	}
	public String getAmt_month_before9() {
		return amt_month_before9;
	}
	public void setAmt_month_before9(String amt_month_before9) {
		this.amt_month_before9 = amt_month_before9;
	}
	public String getAmt_month_before10() {
		return amt_month_before10;
	}
	public void setAmt_month_before10(String amt_month_before10) {
		this.amt_month_before10 = amt_month_before10;
	}
	public String getAmt_month_before11() {
		return amt_month_before11;
	}
	public void setAmt_month_before11(String amt_month_before11) {
		this.amt_month_before11 = amt_month_before11;
	}
	public String getAmt_month_before12() {
		return amt_month_before12;
	}
	public void setAmt_month_before12(String amt_month_before12) {
		this.amt_month_before12 = amt_month_before12;
	}
	public String getCd_account_before1() {
		return cd_account_before1;
	}
	public void setCd_account_before1(String cd_account_before1) {
		this.cd_account_before1 = cd_account_before1;
	}
	public String getCd_account_before2() {
		return cd_account_before2;
	}
	public void setCd_account_before2(String cd_account_before2) {
		this.cd_account_before2 = cd_account_before2;
	}
	public String getCd_account_before3() {
		return cd_account_before3;
	}
	public void setCd_account_before3(String cd_account_before3) {
		this.cd_account_before3 = cd_account_before3;
	}
	public String getCd_account_before4() {
		return cd_account_before4;
	}
	public void setCd_account_before4(String cd_account_before4) {
		this.cd_account_before4 = cd_account_before4;
	}
	public String getCd_account_before5() {
		return cd_account_before5;
	}
	public void setCd_account_before5(String cd_account_before5) {
		this.cd_account_before5 = cd_account_before5;
	}
	public String getCd_account_before6() {
		return cd_account_before6;
	}
	public void setCd_account_before6(String cd_account_before6) {
		this.cd_account_before6 = cd_account_before6;
	}
	public String getCd_account_before7() {
		return cd_account_before7;
	}
	public void setCd_account_before7(String cd_account_before7) {
		this.cd_account_before7 = cd_account_before7;
	}
	public String getCd_account_before8() {
		return cd_account_before8;
	}
	public void setCd_account_before8(String cd_account_before8) {
		this.cd_account_before8 = cd_account_before8;
	}
	public String getCd_account_before9() {
		return cd_account_before9;
	}
	public void setCd_account_before9(String cd_account_before9) {
		this.cd_account_before9 = cd_account_before9;
	}
	public String getCd_account_before10() {
		return cd_account_before10;
	}
	public void setCd_account_before10(String cd_account_before10) {
		this.cd_account_before10 = cd_account_before10;
	}
	public String getCd_account_before11() {
		return cd_account_before11;
	}
	public void setCd_account_before11(String cd_account_before11) {
		this.cd_account_before11 = cd_account_before11;
	}
	public String getCd_account_before12() {
		return cd_account_before12;
	}
	public void setCd_account_before12(String cd_account_before12) {
		this.cd_account_before12 = cd_account_before12;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
}
