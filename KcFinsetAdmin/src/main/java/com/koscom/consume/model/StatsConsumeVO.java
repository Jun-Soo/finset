/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180801
 * 소비지출 통계 데이터
 */

package com.koscom.consume.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class StatsConsumeVO implements Serializable{

	private static final long serialVersionUID = 5801170615302081425L;

	//일반 통계용
	private String cd_class;		//분류코드
	private String nm_class;		//분류명
	private String amt_in_out;		//금액
	
	//기간 통계용
	private String dt_trd;			//거래일자
	
	public StatsConsumeVO() {
	}
	public StatsConsumeVO(String cd_class, String nm_class, String amt_in_out,
			String dt_trd) {
		this.cd_class = cd_class;
		this.nm_class = nm_class;
		this.amt_in_out = amt_in_out;
		this.dt_trd = dt_trd;
	}
	public String getCd_class() {
		return cd_class;
	}
	public void setCd_class(String cd_class) {
		this.cd_class = cd_class;
	}
	public String getNm_class() {
		return nm_class;
	}
	public void setNm_class(String nm_class) {
		this.nm_class = nm_class;
	}
	public String getAmt_in_out() {
		return amt_in_out;
	}
	public void setAmt_in_out(String amt_in_out) {
		this.amt_in_out = amt_in_out;
	}
	public String getDt_trd() {
		return dt_trd;
	}
	public void setDt_trd(String dt_trd) {
		this.dt_trd = dt_trd;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
