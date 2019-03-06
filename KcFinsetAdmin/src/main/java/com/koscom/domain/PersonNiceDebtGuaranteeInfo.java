package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceDebtGuaranteeInfo implements Serializable {
	private static final long serialVersionUID = 7898059457735841977L;
	//채무보증정보(은행연합회)
	//NICE_DEBT_GUARANTEE_INFO
	protected String no_nice_debt_guarantee;//
	protected String no_person              ;//
	protected String occur_institution		;//발생기관명
	protected String dt_occur				;//발생일
	protected String amt_guarantee			;//보증금액
	protected String dt_change				;//변경일
	protected String id_frt                          ;//
	protected String dt_frt                          ;//
	protected String id_lst                          ;//
	protected String dt_lst                          ;//
	public String getNo_nice_debt_guarantee() {
		return no_nice_debt_guarantee;
	}
	public void setNo_nice_debt_guarantee(String no_nice_debt_guarantee) {
		this.no_nice_debt_guarantee = no_nice_debt_guarantee;
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
	public String getDt_occur() {
		return dt_occur;
	}
	public void setDt_occur(String dt_occur) {
		this.dt_occur = dt_occur;
	}
	public String getAmt_guarantee() {
		return amt_guarantee;
	}
	public void setAmt_guarantee(String amt_guarantee) {
		this.amt_guarantee = amt_guarantee;
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