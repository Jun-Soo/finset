package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ScrCardChargeInfoVO implements Serializable{
	private static final long serialVersionUID = 6880945056387791093L;
	protected String no_person= "";				//회원고유번호
	protected String cd_fc= "";					//금융사코드
	@SerializedName("CHARGE_YYYYMM")
	protected String charge_yyyymm= "";			//청구년월
	@SerializedName("MONTHLY_CHARGE")
	protected String monthly_charge= "";		//월청구금액
	protected String dt_frt= "";				//등록일시
	protected List<ScrCardChargeDetailVO> CARD_CHARGE_DTL;	//카드청구내역상세
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
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public List<ScrCardChargeDetailVO> getCARD_CHARGE_DTL() {
		return CARD_CHARGE_DTL;
	}
	public void setCARD_CHARGE_DTL(List<ScrCardChargeDetailVO> cARD_CHARGE_DTL) {
		CARD_CHARGE_DTL = cARD_CHARGE_DTL;
	}
}
