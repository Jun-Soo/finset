package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonNiceLoanInfo implements Serializable {
	private static final long serialVersionUID = 2247928136781498464L;
	protected String no_niceloan;
	protected String no_person                       ;
	protected String cd_fin                          ;//업권                  
	protected String occur_institution               ;//발생기관명            
	protected String dt_start                        ;//개설일                
	protected String amt_loan                        ;//대출금액(단위:천원)   
	protected String div_loan                        ;//대출구분              
	protected String dt_change                       ;//변경일                
	protected String dt_max                          ;//만기일                
	protected String type_loan                       ;//대출종류              
	protected String amt_frt                         ;//최초개설금액(단위:천원
	protected String rto_avg_interest                ;//평균예상금리          
	protected String represent_fin                   ;//대표업권              
	protected String div_loan2                       ;//대출구분2             
	protected String est_remain                      ;//잔존예상              
	protected String cnt_use_month                   ;//사용개월수            
	protected String total_predict_loan_month        ;//총예상대출개월        
	protected String amt_pay_month                   ;//월불입금(단위:천원)   
	protected String id_frt                          ;
	protected String dt_frt                          ;
	protected String id_lst                          ;
	protected String dt_lst                          ;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getNo_niceloan() {
		return no_niceloan;
	}
	public void setNo_niceloan(String no_niceloan) {
		this.no_niceloan = no_niceloan;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_fin() {
		return cd_fin;
	}
	public void setCd_fin(String cd_fin) {
		this.cd_fin = cd_fin;
	}
	public String getOccur_institution() {
		return occur_institution;
	}
	public void setOccur_institution(String occur_institution) {
		this.occur_institution = occur_institution;
	}
	public String getDt_start() {
		return dt_start;
	}
	public void setDt_start(String dt_start) {
		this.dt_start = dt_start;
	}
	public String getAmt_loan() {
		return amt_loan;
	}
	public void setAmt_loan(String amt_loan) {
		this.amt_loan = amt_loan;
	}
	public String getDiv_loan() {
		return div_loan;
	}
	public void setDiv_loan(String div_loan) {
		this.div_loan = div_loan;
	}
	public String getDt_change() {
		return dt_change;
	}
	public void setDt_change(String dt_change) {
		this.dt_change = dt_change;
	}
	public String getDt_max() {
		return dt_max;
	}
	public void setDt_max(String dt_max) {
		this.dt_max = dt_max;
	}
	public String getType_loan() {
		return type_loan;
	}
	public void setType_loan(String type_loan) {
		this.type_loan = type_loan;
	}
	public String getAmt_frt() {
		return amt_frt;
	}
	public void setAmt_frt(String amt_frt) {
		this.amt_frt = amt_frt;
	}
	public String getRto_avg_interest() {
		return rto_avg_interest;
	}
	public void setRto_avg_interest(String rto_avg_interest) {
		this.rto_avg_interest = rto_avg_interest;
	}
	public String getRepresent_fin() {
		return represent_fin;
	}
	public void setRepresent_fin(String represent_fin) {
		this.represent_fin = represent_fin;
	}
	public String getDiv_loan2() {
		return div_loan2;
	}
	public void setDiv_loan2(String div_loan2) {
		this.div_loan2 = div_loan2;
	}
	public String getEst_remain() {
		return est_remain;
	}
	public void setEst_remain(String est_remain) {
		this.est_remain = est_remain;
	}
	public String getCnt_use_month() {
		return cnt_use_month;
	}
	public void setCnt_use_month(String cnt_use_month) {
		this.cnt_use_month = cnt_use_month;
	}
	public String getTotal_predict_loan_month() {
		return total_predict_loan_month;
	}
	public void setTotal_predict_loan_month(String total_predict_loan_month) {
		this.total_predict_loan_month = total_predict_loan_month;
	}
	public String getAmt_pay_month() {
		return amt_pay_month;
	}
	public void setAmt_pay_month(String amt_pay_month) {
		this.amt_pay_month = amt_pay_month;
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
}