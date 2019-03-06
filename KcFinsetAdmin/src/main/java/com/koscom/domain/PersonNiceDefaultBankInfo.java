package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceDefaultBankInfo implements Serializable {
	private static final long serialVersionUID = -15515095858384628L;
	//채무불이행(은행연합회)
	//NICE_DEFAULT_BANK_INFO
	protected String no_nice_default_bank;//
	protected String no_person              ;//
	protected String div_data				;//정보구분
	protected String cd_reason				;//사유코드
	protected String div_reason				;//사유구분
	protected String occur_institution		;//발생기관
	protected String dt_occur				;//발생일
	protected String dt_regist				;//등록일
	protected String dt_lift				;//해제일
	protected String div_lift				;//해제구분
	protected String occur_team				;//발생지점
	protected String amt_regist				;//등록금액
	protected String amt_delay				;//연체금액
	protected String id_frt                          ;//
	protected String dt_frt                          ;//
	protected String id_lst                          ;//
	protected String dt_lst                          ;//
	public String getNo_nice_default_bank() {
		return no_nice_default_bank;
	}
	public void setNo_nice_default_bank(String no_nice_default_bank) {
		this.no_nice_default_bank = no_nice_default_bank;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getDiv_data() {
		return div_data;
	}
	public void setDiv_data(String div_data) {
		this.div_data = div_data;
	}
	public String getCd_reason() {
		return cd_reason;
	}
	public void setCd_reason(String cd_reason) {
		this.cd_reason = cd_reason;
	}
	public String getDiv_reason() {
		return div_reason;
	}
	public void setDiv_reason(String div_reason) {
		this.div_reason = div_reason;
	}
	public String getOccur_institution() {
		return occur_institution;
	}
	public void setOccur_institution(String occur_institution) {
		this.occur_institution = occur_institution;
	}
	public String getDt_occur() {
		return dt_occur;
	}
	public void setDt_occur(String dt_occur) {
		this.dt_occur = dt_occur;
	}
	public String getDt_regist() {
		return dt_regist;
	}
	public void setDt_regist(String dt_regist) {
		this.dt_regist = dt_regist;
	}
	public String getDt_lift() {
		return dt_lift;
	}
	public void setDt_lift(String dt_lift) {
		this.dt_lift = dt_lift;
	}
	public String getDiv_lift() {
		return div_lift;
	}
	public void setDiv_lift(String div_lift) {
		this.div_lift = div_lift;
	}
	public String getOccur_team() {
		return occur_team;
	}
	public void setOccur_team(String occur_team) {
		this.occur_team = occur_team;
	}
	public String getAmt_regist() {
		return amt_regist;
	}
	public void setAmt_regist(String amt_regist) {
		this.amt_regist = amt_regist;
	}
	public String getAmt_delay() {
		return amt_delay;
	}
	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}