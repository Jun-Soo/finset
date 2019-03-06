package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceDefaultNiceInfo implements Serializable {
	private static final long serialVersionUID = 8653109224895291310L;
	//채무불이행(NICE)
	//NICE_DEFAULT_NICE_INFO
	protected String no_nice_default_nice;//
	protected String no_person              ;//
	protected String regist_reason			;//등록사유
	protected String occur_institution		;//발생기관
	protected String occur_team				;//발생지점
	protected String dt_occur				;//발생일
	protected String dt_offer				;//제공일
	protected String amt_delay				;//연체금액
	protected String id_frt                          ;//
	protected String dt_frt                          ;//
	protected String id_lst                          ;//
	protected String dt_lst                          ;//
	public String getNo_nice_default_nice() {
		return no_nice_default_nice;
	}
	public void setNo_nice_default_nice(String no_nice_default_nice) {
		this.no_nice_default_nice = no_nice_default_nice;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getRegist_reason() {
		return regist_reason;
	}
	public void setRegist_reason(String regist_reason) {
		this.regist_reason = regist_reason;
	}
	public String getOccur_institution() {
		return occur_institution;
	}
	public void setOccur_institution(String occur_institution) {
		this.occur_institution = occur_institution;
	}
	public String getOccur_team() {
		return occur_team;
	}
	public void setOccur_team(String occur_team) {
		this.occur_team = occur_team;
	}
	public String getDt_occur() {
		return dt_occur;
	}
	public void setDt_occur(String dt_occur) {
		this.dt_occur = dt_occur;
	}
	public String getDt_offer() {
		return dt_offer;
	}
	public void setDt_offer(String dt_offer) {
		this.dt_offer = dt_offer;
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