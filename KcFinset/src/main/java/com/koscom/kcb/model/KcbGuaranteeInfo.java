package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbGuaranteeInfo implements Serializable {
	
	private static final long serialVersionUID = -397631577437083860L;

	private String no_person;			//회원관리번호
	private String cd_fc;				//금융사코드
	private String cd_guarantee;		//보증종류
	private String dt_guar_agree;		//보증약정일자
	private String amt_guar_limit;		//보증한도금액
	private String amt_guar_object;		//보증대상금액
	
	public KcbGuaranteeInfo() {
	}

	public KcbGuaranteeInfo(String no_person, String cd_fc,
			String cd_guarantee, String dt_guar_agree, String amt_guar_limit,
			String amt_guar_object) {
		this.no_person = no_person;
		this.cd_fc = cd_fc;
		this.cd_guarantee = cd_guarantee;
		this.dt_guar_agree = dt_guar_agree;
		this.amt_guar_limit = amt_guar_limit;
		this.amt_guar_object = amt_guar_object;
	}
	
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

	public String getCd_guarantee() {
		return cd_guarantee;
	}

	public void setCd_guarantee(String cd_guarantee) {
		this.cd_guarantee = cd_guarantee;
	}

	public String getDt_guar_agree() {
		return dt_guar_agree;
	}

	public void setDt_guar_agree(String dt_guar_agree) {
		this.dt_guar_agree = dt_guar_agree;
	}

	public String getAmt_guar_limit() {
		return amt_guar_limit;
	}

	public void setAmt_guar_limit(String amt_guar_limit) {
		this.amt_guar_limit = amt_guar_limit;
	}

	public String getAmt_guar_object() {
		return amt_guar_object;
	}

	public void setAmt_guar_object(String amt_guar_object) {
		this.amt_guar_object = amt_guar_object;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}