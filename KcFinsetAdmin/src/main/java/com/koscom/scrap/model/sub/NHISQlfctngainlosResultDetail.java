package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 자격확인서 vo
 * @author HSJ
 *
 */
public class NHISQlfctngainlosResultDetail implements Serializable {

	private static final long serialVersionUID = 8942179820317914740L;

	private int SEQ_HEALTH_INSU;					// 일련번호_건강보험
	
	private int ORDER_QUALIFICATION_CONFIRM;		// 순서_자격확인
	
	@SerializedName("자격취득일")
	private String QLFCTN_CNFRM_QLFCTN_GN_D;	// 자격확인자격취득일
	
	@SerializedName("자격상실일")
	private String QLFCTN_CNFRM_QLFCTN_LS_D;	// 자격확인자격상실일
	
	@SerializedName("급여정지사유_기간")
	private String QLFCTN_CNFRM_SLR_STP_CS_TRM;	// 자격확인급여정지사유기간

	public int getSEQ_HEALTH_INSU() {
		return SEQ_HEALTH_INSU;
	}

	public void setSEQ_HEALTH_INSU(int sEQ_HEALTH_INSU) {
		SEQ_HEALTH_INSU = sEQ_HEALTH_INSU;
	}

	public int getORDER_QUALIFICATION_CONFIRM() {
		return ORDER_QUALIFICATION_CONFIRM;
	}

	public void setORDER_QUALIFICATION_CONFIRM(int oRDER_QUALIFICATION_CONFIRM) {
		ORDER_QUALIFICATION_CONFIRM = oRDER_QUALIFICATION_CONFIRM;
	}

	public String getQLFCTN_CNFRM_QLFCTN_GN_D() {
		return QLFCTN_CNFRM_QLFCTN_GN_D;
	}

	public void setQLFCTN_CNFRM_QLFCTN_GN_D(String qLFCTN_CNFRM_QLFCTN_GN_D) {
		QLFCTN_CNFRM_QLFCTN_GN_D = qLFCTN_CNFRM_QLFCTN_GN_D;
	}

	public String getQLFCTN_CNFRM_QLFCTN_LS_D() {
		return QLFCTN_CNFRM_QLFCTN_LS_D;
	}

	public void setQLFCTN_CNFRM_QLFCTN_LS_D(String qLFCTN_CNFRM_QLFCTN_LS_D) {
		QLFCTN_CNFRM_QLFCTN_LS_D = qLFCTN_CNFRM_QLFCTN_LS_D;
	}

	public String getQLFCTN_CNFRM_SLR_STP_CS_TRM() {
		return QLFCTN_CNFRM_SLR_STP_CS_TRM;
	}

	public void setQLFCTN_CNFRM_SLR_STP_CS_TRM(String qLFCTN_CNFRM_SLR_STP_CS_TRM) {
		QLFCTN_CNFRM_SLR_STP_CS_TRM = qLFCTN_CNFRM_SLR_STP_CS_TRM;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}


}
