package com.koscom.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CommissionInfo implements Serializable{
	private static final long serialVersionUID = 1362949360338205823L;
	private String no_apply; 			//승인번호
	private Double rto_exp_loan; 		//예상수수료율
	private String amt_exp_commission; 	//예상수수료
	private Double rto_real_loan; 		//지급수수료율
	private String amt_real_commission; //지급수수료
	private String yn_exe; 				//집행여부	
	private String dt_exe; 				//집행일
	private String memo; 				//메모

	private String id_frt;				
	private String dt_frt;
	private String id_lst;
	private String dt_lst;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_apply() {
		return no_apply;
	}

	public Double getRto_exp_loan() {
		return rto_exp_loan;
	}

	public String getAmt_exp_commission() {
		return amt_exp_commission;
	}

	public Double getRto_real_loan() {
		return rto_real_loan;
	}

	public String getAmt_real_commission() {
		return amt_real_commission;
	}

	public String getYn_exe() {
		return yn_exe;
	}

	public String getDt_exe() {
		return dt_exe;
	}

	public String getMemo() {
		return memo;
	}

	public String getId_frt() {
		return id_frt;
	}

	public String getDt_frt() {
		return dt_frt;
	}

	public String getId_lst() {
		return id_lst;
	}

	public String getDt_lst() {
		return dt_lst;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public void setRto_exp_loan(Double rto_exp_loan) {
		this.rto_exp_loan = rto_exp_loan;
	}

	public void setAmt_exp_commission(String amt_exp_commission) {
		this.amt_exp_commission = amt_exp_commission;
	}

	public void setRto_real_loan(Double rto_real_loan) {
		this.rto_real_loan = rto_real_loan;
	}

	public void setAmt_real_commission(String amt_real_commission) {
		this.amt_real_commission = amt_real_commission;
	}

	public void setYn_exe(String yn_exe) {
		this.yn_exe = yn_exe;
	}

	public void setDt_exe(String dt_exe) {
		this.dt_exe = dt_exe;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}

	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}

	
}