package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbOverdueInfo implements Serializable {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;			//회원관리번호

	private String cd_fc;			//금융사 코드
	private String nm_biz_type;		//업종명
	private String nm_trade;		//거래기관명
	private String nm_agency;		//관리점명
	private String cd_type_deal;	//거래형태코드
	private String ymd_frt_delay;	//최초연체기산일자
	private String ymd_delay;		//연체기산일자
	private String amt_frt_delay;	//최초연체금액
	private String amt_delay;		//연체금액
	private String yn_mat_delay;	//만기후연체여부
	private String cd_profit_loss;	//기한이익상실코드
	private String amt_remain;		//대출잔액/미도래잔액
	private String ymd_repay;		//연체상환일자
	private String amt_repay;		//상환금액
	private String id_frt;			//최초입력아이디

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
	public String getNm_biz_type() {
		return nm_biz_type;
	}
	public void setNm_biz_type(String nm_biz_type) {
		this.nm_biz_type = nm_biz_type;
	}
	public String getNm_trade() {
		return nm_trade;
	}
	public void setNm_trade(String nm_trade) {
		this.nm_trade = nm_trade;
	}
	public String getNm_agency() {
		return nm_agency;
	}
	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}
	public String getCd_type_deal() {
		return cd_type_deal;
	}
	public void setCd_type_deal(String cd_type_deal) {
		this.cd_type_deal = cd_type_deal;
	}
	public String getYmd_frt_delay() {
		return ymd_frt_delay;
	}
	public void setYmd_frt_delay(String ymd_frt_delay) {
		this.ymd_frt_delay = ymd_frt_delay;
	}
	public String getYmd_delay() {
		return ymd_delay;
	}
	public void setYmd_delay(String ymd_delay) {
		this.ymd_delay = ymd_delay;
	}
	public String getAmt_frt_delay() {
		return amt_frt_delay;
	}
	public void setAmt_frt_delay(String amt_frt_delay) {
		this.amt_frt_delay = amt_frt_delay;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}
	public String getYn_mat_delay() {
		return yn_mat_delay;
	}
	public void setYn_mat_delay(String yn_mat_delay) {
		this.yn_mat_delay = yn_mat_delay;
	}
	public String getCd_profit_loss() {
		return cd_profit_loss;
	}
	public void setCd_profit_loss(String cd_profit_loss) {
		this.cd_profit_loss = cd_profit_loss;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getYmd_repay() {
		return ymd_repay;
	}
	public void setYmd_repay(String ymd_repay) {
		this.ymd_repay = ymd_repay;
	}
	public String getAmt_repay() {
		return amt_repay;
	}
	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}