package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceCashServiceInfo implements Serializable {
	private static final long serialVersionUID = -4852301265875567947L;
	//현금서비스정보(은행연합회)
	//NICE_CASH_SERVICE_INFO
	protected String no_nice_cash_service;//
	protected String no_person				;//
	protected String occur_institution		;//발생기관명
	protected String occur_team				;//발생지점명
	protected String dt_occur				;//발생일
	protected String amt_cash_service		;//금액
	protected String dt_change				;//변경일
	protected String id_frt                          ;//
	protected String dt_frt                          ;//
	protected String id_lst                          ;//
	protected String dt_lst                          ;//
	public String getNo_nice_cash_service() {
		return no_nice_cash_service;
	}
	public void setNo_nice_cash_service(String no_nice_cash_service) {
		this.no_nice_cash_service = no_nice_cash_service;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
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
	public String getAmt_cash_service() {
		return amt_cash_service;
	}
	public void setAmt_cash_service(String amt_cash_service) {
		this.amt_cash_service = amt_cash_service;
	}
	public String getDt_change() {
		return dt_change;
	}
	public void setDt_change(String dt_change) {
		this.dt_change = dt_change;
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