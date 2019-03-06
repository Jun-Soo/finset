package com.koscom.stats.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StatsVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 878595212884334134L;
	private String keyword;
	private String id_apply;
	private String cd_advertisement;
	private String cd_goods_type;
	private String cd_fc;
	private String cd_goods;
	private String id_agency;

	private long cnt_apply;
	private long cnt_approval;
	private long amt_approval;

	private long cnt_approval_500;
	private long cnt_approval_1000;
	private long cnt_approval_1001;
	private long amt_approval_500;
	private long amt_approval_1000;
	private long amt_approval_1001;

	private long cnt_exam;
	private long cnt_hold;
	private long cnt_reject;

	private String id_emp;
	private long cnt_sms;
	private long cnt_sms_success;
	private long cnt_sms_sending;
	private long cnt_sms_fail;
	private String c1_type_msg;

	private String dt_frt;
	private String join_person_cnt;
	private String login_person_cnt;
	private String no_person;
	private String nm_person;
	private String grade_credit;
	private String debt_cnt;
	private String debt_amt_remain;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getId_apply() {
		return id_apply;
	}

	public void setId_apply(String id_apply) {
		this.id_apply = id_apply;
	}

	public String getCd_advertisement() {
		return cd_advertisement;
	}

	public void setCd_advertisement(String cd_advertisement) {
		this.cd_advertisement = cd_advertisement;
	}

	public String getCd_goods_type() {
		return cd_goods_type;
	}

	public void setCd_goods_type(String cd_goods_type) {
		this.cd_goods_type = cd_goods_type;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public long getCnt_apply() {
		return cnt_apply;
	}

	public void setCnt_apply(long cnt_apply) {
		this.cnt_apply = cnt_apply;
	}

	public long getCnt_approval() {
		return cnt_approval;
	}

	public void setCnt_approval(long cnt_approval) {
		this.cnt_approval = cnt_approval;
	}

	public long getAmt_approval() {
		return amt_approval;
	}

	public void setAmt_approval(long amt_approval) {
		this.amt_approval = amt_approval;
	}

	public long getCnt_approval_500() {
		return cnt_approval_500;
	}

	public void setCnt_approval_500(long cnt_approval_500) {
		this.cnt_approval_500 = cnt_approval_500;
	}

	public long getCnt_approval_1000() {
		return cnt_approval_1000;
	}

	public void setCnt_approval_1000(long cnt_approval_1000) {
		this.cnt_approval_1000 = cnt_approval_1000;
	}

	public long getCnt_approval_1001() {
		return cnt_approval_1001;
	}

	public void setCnt_approval_1001(long cnt_approval_1001) {
		this.cnt_approval_1001 = cnt_approval_1001;
	}

	public long getAmt_approval_500() {
		return amt_approval_500;
	}

	public void setAmt_approval_500(long amt_approval_500) {
		this.amt_approval_500 = amt_approval_500;
	}

	public long getAmt_approval_1000() {
		return amt_approval_1000;
	}

	public void setAmt_approval_1000(long amt_approval_1000) {
		this.amt_approval_1000 = amt_approval_1000;
	}

	public long getAmt_approval_1001() {
		return amt_approval_1001;
	}

	public void setAmt_approval_1001(long amt_approval_1001) {
		this.amt_approval_1001 = amt_approval_1001;
	}

	public long getCnt_exam() {
		return cnt_exam;
	}

	public void setCnt_exam(long cnt_exam) {
		this.cnt_exam = cnt_exam;
	}

	public long getCnt_hold() {
		return cnt_hold;
	}

	public void setCnt_hold(long cnt_hold) {
		this.cnt_hold = cnt_hold;
	}

	public long getCnt_reject() {
		return cnt_reject;
	}

	public void setCnt_reject(long cnt_reject) {
		this.cnt_reject = cnt_reject;
	}

	public String getId_emp() {
		return id_emp;
	}

	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}

	public long getCnt_sms() {
		return cnt_sms;
	}

	public void setCnt_sms(long cnt_sms) {
		this.cnt_sms = cnt_sms;
	}

	public long getCnt_sms_success() {
		return cnt_sms_success;
	}

	public void setCnt_sms_success(long cnt_sms_success) {
		this.cnt_sms_success = cnt_sms_success;
	}

	public long getCnt_sms_sending() {
		return cnt_sms_sending;
	}

	public void setCnt_sms_sending(long cnt_sms_sending) {
		this.cnt_sms_sending = cnt_sms_sending;
	}

	public long getCnt_sms_fail() {
		return cnt_sms_fail;
	}

	public void setCnt_sms_fail(long cnt_sms_fail) {
		this.cnt_sms_fail = cnt_sms_fail;
	}

	public String getC1_type_msg() {
		return c1_type_msg;
	}

	public void setC1_type_msg(String c1_type_msg) {
		this.c1_type_msg = c1_type_msg;
	}

	public String getDt_frt() {
		return dt_frt;
	}

	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	public String getJoin_person_cnt() {
		return join_person_cnt;
	}

	public void setJoin_person_cnt(String join_person_cnt) {
		this.join_person_cnt = join_person_cnt;
	}

	public String getLogin_person_cnt() {
		return login_person_cnt;
	}

	public void setLogin_person_cnt(String login_person_cnt) {
		this.login_person_cnt = login_person_cnt;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getGrade_credit() {
		return grade_credit;
	}

	public void setGrade_credit(String grade_credit) {
		this.grade_credit = grade_credit;
	}

	public String getDebt_cnt() {
		return debt_cnt;
	}

	public void setDebt_cnt(String debt_cnt) {
		this.debt_cnt = debt_cnt;
	}

	public String getDebt_amt_remain() {
		return debt_amt_remain;
	}

	public void setDebt_amt_remain(String debt_amt_remain) {
		this.debt_amt_remain = debt_amt_remain;
	}

}
