package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class NHISPaymentResultDetailSub implements Serializable {	

	private static final long serialVersionUID = -7115624251901431281L;

	private int SEQ_HEALTH_INSU;	// 일련번호_건강보험
	
	private int ORDER_PAY_CONFIRM;	// 순서
	
	private int ORDER_PAY_DETAIL;	// 순서_납부상세

	@SerializedName("납부년월")
	private String PAY_YEARMONTH; 						// 납부연월
	
	@SerializedName("납부내역_고지금액_건강보험료")
	private String NOTICE_AMT_HEALTH_INSU_AMT; 			// 고지금액건강보험료
	
	@SerializedName("납부내역_고지금액_장기요양보험료")
	private String NTC_AMT_LNGTRM_CR_INS_AMT; 			// 고지금액장기요양보험료
	
	@SerializedName("납부내역_납부금액_건강보험료")
	private String PAY_AMT_HEALTH_INSU_AMT; 			// 납부금액건강보험료
	
	@SerializedName("납부내역_납부금액_장기요양보험료")
	private String PAY_AMT_LONGTERM_CARE_INSU_AMT; 		// 납부금액장기요양보험료
	
	private String REGISTER_DT; // 등록일시

	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}

	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
	}

	public int getORDER_PAY_CONFIRM() {
		return ORDER_PAY_CONFIRM;
	}

	public void setORDER_PAY_CONFIRM(int oRDER_PAY_CONFIRM) {
		ORDER_PAY_CONFIRM = oRDER_PAY_CONFIRM;
	}

	public int getORDER_PAY_DETAIL() {
		return ORDER_PAY_DETAIL;
	}

	public void setORDER_PAY_DETAIL(int oRDER_PAY_DETAIL) {
		ORDER_PAY_DETAIL = oRDER_PAY_DETAIL;
	}

	public String getPAY_YEARMONTH() {
		return PAY_YEARMONTH;
	}

	public void setPAY_YEARMONTH(String pAY_YEARMONTH) {
		PAY_YEARMONTH = pAY_YEARMONTH;
	}

	public String getNOTICE_AMT_HEALTH_INSU_AMT() {
		return NOTICE_AMT_HEALTH_INSU_AMT;
	}

	public void setNOTICE_AMT_HEALTH_INSU_AMT(String nOTICE_AMT_HEALTH_INSU_AMT) {
		NOTICE_AMT_HEALTH_INSU_AMT = nOTICE_AMT_HEALTH_INSU_AMT;
	}

	public String getNTC_AMT_LNGTRM_CR_INS_AMT() {
		return NTC_AMT_LNGTRM_CR_INS_AMT;
	}

	public void setNTC_AMT_LNGTRM_CR_INS_AMT(String nTC_AMT_LNGTRM_CR_INS_AMT) {
		NTC_AMT_LNGTRM_CR_INS_AMT = nTC_AMT_LNGTRM_CR_INS_AMT;
	}

	public String getPAY_AMT_HEALTH_INSU_AMT() {
		return PAY_AMT_HEALTH_INSU_AMT;
	}

	public void setPAY_AMT_HEALTH_INSU_AMT(String pAY_AMT_HEALTH_INSU_AMT) {
		PAY_AMT_HEALTH_INSU_AMT = pAY_AMT_HEALTH_INSU_AMT;
	}

	public String getPAY_AMT_LONGTERM_CARE_INSU_AMT() {
		return PAY_AMT_LONGTERM_CARE_INSU_AMT;
	}

	public void setPAY_AMT_LONGTERM_CARE_INSU_AMT(String pAY_AMT_LONGTERM_CARE_INSU_AMT) {
		PAY_AMT_LONGTERM_CARE_INSU_AMT = pAY_AMT_LONGTERM_CARE_INSU_AMT;
	}

	public String getREGISTER_DT() {
		return REGISTER_DT;
	}

	public void setREGISTER_DT(String rEGISTER_DT) {
		REGISTER_DT = rEGISTER_DT;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
