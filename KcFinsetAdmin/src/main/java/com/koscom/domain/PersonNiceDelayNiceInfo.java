package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceDelayNiceInfo implements Serializable {
	private static final long serialVersionUID = 7056732667108731430L;
	//연체정보(NICE)
	//NICE_DELAY_NICE_INFO
	protected String no_nice_delay_nice;//
	protected String no_person              ;//
	protected String div_goods				;//상품구분
	protected String div_delay				;//연체구분
	protected String occur_institution		;//발생기관명
	protected String occur_team				;//발생지점명
	protected String dt_frt_delay			;//최초연체발생일
	protected String cnt_occur_delay_dt			;//연체발생일 수
	protected String div_regist				;//등록구분
	protected String amt_frt_delay			;//최초연체금액
	protected String amt_delay				;//연체금액
	protected String amt_remain				;//잔액
	protected String amt_limit_loan			;//한도
	protected String id_frt                          ;//
	protected String dt_frt                          ;//
	protected String id_lst                          ;//
	protected String dt_lst                          ;//
	public String getNo_nice_delay_nice() {
		return no_nice_delay_nice;
	}
	public void setNo_nice_delay_nice(String no_nice_delay_nice) {
		this.no_nice_delay_nice = no_nice_delay_nice;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getDiv_goods() {
		return div_goods;
	}
	public void setDiv_goods(String div_goods) {
		this.div_goods = div_goods;
	}
	public String getDiv_delay() {
		return div_delay;
	}
	public void setDiv_delay(String div_delay) {
		this.div_delay = div_delay;
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
	public String getDt_frt_delay() {
		return dt_frt_delay;
	}
	public void setDt_frt_delay(String dt_frt_delay) {
		this.dt_frt_delay = dt_frt_delay;
	}
	public String getCnt_occur_delay_dt() {
		return cnt_occur_delay_dt;
	}
	public void setCnt_occur_delay_dt(String cnt_occur_delay_dt) {
		this.cnt_occur_delay_dt = cnt_occur_delay_dt;
	}
	public String getDiv_regist() {
		return div_regist;
	}
	public void setDiv_regist(String div_regist) {
		this.div_regist = div_regist;
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
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getAmt_limit_loan() {
		return amt_limit_loan;
	}
	public void setAmt_limit_loan(String amt_limit_loan) {
		this.amt_limit_loan = amt_limit_loan;
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