package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbCardInfo implements Serializable{
	private static final long serialVersionUID = 4057778690994570955L;
	
	private String no_person;			//회원관리번호
	private String cd_fc;				//금융사코드
	private String type_card;			//카드 구분 - 01:신용, 02:체크
	private String ymd_open;			//개설일자
	private String amt_limit;			//총한도
	private String amt_ca_limit;		//단기카드대출한도
	private String ymd_cancel;			//해지일자
	private String cd_cancel;			//해지사유코드
	private String yn_stop;				//거래정지여부
	private String cd_status_member;	//회원상태코드
	private String yn_family_card;		//가족카드발급여부
	private String yn_recovery;			//신용회복지원여부
	
	public KcbCardInfo() {
	}

	public KcbCardInfo(String no_person, String cd_fc, String type_card,
			String ymd_open, String amt_limit, String amt_ca_limit,
			String ymd_cancel, String cd_cancel, String yn_stop,
			String cd_status_member, String yn_family_card, String yn_recovery) {
		this.no_person = no_person;
		this.cd_fc = cd_fc;
		this.type_card = type_card;
		this.ymd_open = ymd_open;
		this.amt_limit = amt_limit;
		this.amt_ca_limit = amt_ca_limit;
		this.ymd_cancel = ymd_cancel;
		this.cd_cancel = cd_cancel;
		this.yn_stop = yn_stop;
		this.cd_status_member = cd_status_member;
		this.yn_family_card = yn_family_card;
		this.yn_recovery = yn_recovery;
	}

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

	public String getYmd_open() {
		return ymd_open;
	}

	public void setYmd_open(String ymd_open) {
		this.ymd_open = ymd_open;
	}

	public String getAmt_limit() {
		return amt_limit;
	}

	public void setAmt_limit(String amt_limit) {
		this.amt_limit = amt_limit;
	}

	public String getAmt_ca_limit() {
		return amt_ca_limit;
	}

	public void setAmt_ca_limit(String amt_ca_limit) {
		this.amt_ca_limit = amt_ca_limit;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
