package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Kcb041Info implements Serializable{
	private static final long serialVersionUID = 8197226127401047483L;
	protected String seq_kcb_response= "";	//조회일련번호KCB_041_INFO
	protected String no_person= "";	//회원관리번호KCB_041_INFO
	protected String no_manage_info= "";	//정보관리번호KCB_041_INFO
	protected String nm_biz_type= "";	//업종명KCB_041_INFO
	protected String cd_type_deal= "";	//거래형태코드KCB_041_INFO
	protected String yn_credit= "";	//신용여부KCB_041_INFO
	protected String yn_loan= "";	//담보여부KCB_041_INFO
	protected String yn_guarantor= "";	//보증인여부KCB_041_INFO
	protected String yn_revolving= "";	//일부결제금액이월약정(리볼빙)여부KCB_041_INFO
	protected String ymd_open= "";	//개설일자KCB_041_INFO
	protected String ymd_cancel= "";	//해지일자KCB_041_INFO
	protected String cd_cancel= "";	//해지사유코드KCB_041_INFO
	protected String yn_stop= "";	//거래정지여부KCB_041_INFO
	protected String cd_status_member= "";	//회원상태코드KCB_041_INFO
	protected String yn_family_card= "";	//가족카드발급여부KCB_041_INFO
	protected String yn_recovery= "";	//신용회복지원여부KCB_041_INFO
	protected String amt_delay_before1= "";	//1개월전연체금액KCB_041_INFO
	protected String amt_delay_before2= "";	//2개월전연체금액KCB_041_INFO
	protected String amt_delay_before3= "";	//3개월전연체금액KCB_041_INFO
	protected String amt_delay_before4= "";	//4개월전연체금액KCB_041_INFO
	protected String amt_delay_before5= "";	//5개월전연체금액KCB_041_INFO
	protected String amt_delay_before6= "";	//6개월전연체금액KCB_041_INFO
	protected String amt_delay_before7= "";	//7개월전연체금액KCB_041_INFO
	protected String amt_delay_before8= "";	//8개월전연체금액KCB_041_INFO
	protected String amt_delay_before9= "";	//9개월전연체금액KCB_041_INFO
	protected String amt_delay_before10= "";	//10개월전연체금액KCB_041_INFO
	protected String amt_delay_before11= "";	//11개월전연체금액KCB_041_INFO
	protected String amt_delay_before12= "";	//12개월전연체금액KCB_041_INFO
	protected String cd_trade_before1= "";	//1개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before2= "";	//2개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before3= "";	//3개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before4= "";	//4개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before5= "";	//5개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before6= "";	//6개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before7= "";	//7개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before8= "";	//8개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before9= "";	//9개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before10= "";	//10개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before11= "";	//11개월전거래상태코드KCB_041_INFO
	protected String cd_trade_before12= "";	//12개월전거래상태코드KCB_041_INFO
	protected String filler= "";	//FILLERKCB_041_INFO
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
	public String getYn_revolving() {
		return yn_revolving;
	}
	public void setYn_revolving(String yn_revolving) {
		this.yn_revolving = yn_revolving;
	}
	public String getYmd_open() {
		return ymd_open;
	}
	public void setYmd_open(String ymd_open) {
		this.ymd_open = ymd_open;
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
	public String getYn_stop() {
		return yn_stop;
	}
	public void setYn_stop(String yn_stop) {
		this.yn_stop = yn_stop;
	}
	public String getCd_status_member() {
		return cd_status_member;
	}
	public void setCd_status_member(String cd_status_member) {
		this.cd_status_member = cd_status_member;
	}
	public String getYn_family_card() {
		return yn_family_card;
	}
	public void setYn_family_card(String yn_family_card) {
		this.yn_family_card = yn_family_card;
	}
	public String getYn_recovery() {
		return yn_recovery;
	}
	public void setYn_recovery(String yn_recovery) {
		this.yn_recovery = yn_recovery;
	}
	public String getAmt_delay_before1() {
		return amt_delay_before1;
	}
	public void setAmt_delay_before1(String amt_delay_before1) {
		this.amt_delay_before1 = amt_delay_before1;
	}
	public String getAmt_delay_before2() {
		return amt_delay_before2;
	}
	public void setAmt_delay_before2(String amt_delay_before2) {
		this.amt_delay_before2 = amt_delay_before2;
	}
	public String getAmt_delay_before3() {
		return amt_delay_before3;
	}
	public void setAmt_delay_before3(String amt_delay_before3) {
		this.amt_delay_before3 = amt_delay_before3;
	}
	public String getAmt_delay_before4() {
		return amt_delay_before4;
	}
	public void setAmt_delay_before4(String amt_delay_before4) {
		this.amt_delay_before4 = amt_delay_before4;
	}
	public String getAmt_delay_before5() {
		return amt_delay_before5;
	}
	public void setAmt_delay_before5(String amt_delay_before5) {
		this.amt_delay_before5 = amt_delay_before5;
	}
	public String getAmt_delay_before6() {
		return amt_delay_before6;
	}
	public void setAmt_delay_before6(String amt_delay_before6) {
		this.amt_delay_before6 = amt_delay_before6;
	}
	public String getAmt_delay_before7() {
		return amt_delay_before7;
	}
	public void setAmt_delay_before7(String amt_delay_before7) {
		this.amt_delay_before7 = amt_delay_before7;
	}
	public String getAmt_delay_before8() {
		return amt_delay_before8;
	}
	public void setAmt_delay_before8(String amt_delay_before8) {
		this.amt_delay_before8 = amt_delay_before8;
	}
	public String getAmt_delay_before9() {
		return amt_delay_before9;
	}
	public void setAmt_delay_before9(String amt_delay_before9) {
		this.amt_delay_before9 = amt_delay_before9;
	}
	public String getAmt_delay_before10() {
		return amt_delay_before10;
	}
	public void setAmt_delay_before10(String amt_delay_before10) {
		this.amt_delay_before10 = amt_delay_before10;
	}
	public String getAmt_delay_before11() {
		return amt_delay_before11;
	}
	public void setAmt_delay_before11(String amt_delay_before11) {
		this.amt_delay_before11 = amt_delay_before11;
	}
	public String getAmt_delay_before12() {
		return amt_delay_before12;
	}
	public void setAmt_delay_before12(String amt_delay_before12) {
		this.amt_delay_before12 = amt_delay_before12;
	}
	public String getCd_trade_before1() {
		return cd_trade_before1;
	}
	public void setCd_trade_before1(String cd_trade_before1) {
		this.cd_trade_before1 = cd_trade_before1;
	}
	public String getCd_trade_before2() {
		return cd_trade_before2;
	}
	public void setCd_trade_before2(String cd_trade_before2) {
		this.cd_trade_before2 = cd_trade_before2;
	}
	public String getCd_trade_before3() {
		return cd_trade_before3;
	}
	public void setCd_trade_before3(String cd_trade_before3) {
		this.cd_trade_before3 = cd_trade_before3;
	}
	public String getCd_trade_before4() {
		return cd_trade_before4;
	}
	public void setCd_trade_before4(String cd_trade_before4) {
		this.cd_trade_before4 = cd_trade_before4;
	}
	public String getCd_trade_before5() {
		return cd_trade_before5;
	}
	public void setCd_trade_before5(String cd_trade_before5) {
		this.cd_trade_before5 = cd_trade_before5;
	}
	public String getCd_trade_before6() {
		return cd_trade_before6;
	}
	public void setCd_trade_before6(String cd_trade_before6) {
		this.cd_trade_before6 = cd_trade_before6;
	}
	public String getCd_trade_before7() {
		return cd_trade_before7;
	}
	public void setCd_trade_before7(String cd_trade_before7) {
		this.cd_trade_before7 = cd_trade_before7;
	}
	public String getCd_trade_before8() {
		return cd_trade_before8;
	}
	public void setCd_trade_before8(String cd_trade_before8) {
		this.cd_trade_before8 = cd_trade_before8;
	}
	public String getCd_trade_before9() {
		return cd_trade_before9;
	}
	public void setCd_trade_before9(String cd_trade_before9) {
		this.cd_trade_before9 = cd_trade_before9;
	}
	public String getCd_trade_before10() {
		return cd_trade_before10;
	}
	public void setCd_trade_before10(String cd_trade_before10) {
		this.cd_trade_before10 = cd_trade_before10;
	}
	public String getCd_trade_before11() {
		return cd_trade_before11;
	}
	public void setCd_trade_before11(String cd_trade_before11) {
		this.cd_trade_before11 = cd_trade_before11;
	}
	public String getCd_trade_before12() {
		return cd_trade_before12;
	}
	public void setCd_trade_before12(String cd_trade_before12) {
		this.cd_trade_before12 = cd_trade_before12;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
}
