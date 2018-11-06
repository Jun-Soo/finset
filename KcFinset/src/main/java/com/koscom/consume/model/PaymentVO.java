package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PaymentVO implements Serializable{

	private static final long serialVersionUID = 3624567199963252874L;

	//요약 데이터 조회
	private String count_fc;			//카드사 갯수 
	private String sum_charge_yyyymm;	//청구액 합계
	
	//리스트 조회
	private String	no_person;			//회원관리번호
	private String	nm_person;			//회원명
	private String	cd_fc;				//금융사코드
	private String	nm_fc;				//금융사명
	private String	charge_yyyymm;		//청구 년월
	private String	monthly_charge;		//청구액
	private Date	dt_frt;
	
	public PaymentVO() {
	}
	public PaymentVO(String count_fc, String sum_charge_yyyymm,
			String no_person, String nm_person, String cd_fc, String nm_fc,
			String charge_yyyymm, String monthly_charge, Date dt_frt) {
		this.count_fc = count_fc;
		this.sum_charge_yyyymm = sum_charge_yyyymm;
		this.no_person = no_person;
		this.nm_person = nm_person;
		this.cd_fc = cd_fc;
		this.nm_fc = nm_fc;
		this.charge_yyyymm = charge_yyyymm;
		this.monthly_charge = monthly_charge;
		this.dt_frt = dt_frt;
	}

	public String getCount_fc() {
		return count_fc;
	}
	public void setCount_fc(String count_fc) {
		this.count_fc = count_fc;
	}
	public String getSum_charge_yyyymm() {
		return sum_charge_yyyymm;
	}
	public void setSum_charge_yyyymm(String sum_charge_yyyymm) {
		this.sum_charge_yyyymm = sum_charge_yyyymm;
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
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	public String getCharge_yyyymm() {
		return charge_yyyymm;
	}
	public void setCharge_yyyymm(String charge_yyyymm) {
		this.charge_yyyymm = charge_yyyymm;
	}
	public String getMonthly_charge() {
		return monthly_charge;
	}
	public void setMonthly_charge(String monthly_charge) {
		this.monthly_charge = monthly_charge;
	}
	public Date getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(Date dt_frt) {
		this.dt_frt = dt_frt;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}