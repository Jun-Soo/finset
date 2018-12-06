/*
 * 20180702 김휘경
 * 기타 연체 정보 중 공공정보를 저장하는 객체
 */

package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbOverduePublicInfo implements Serializable {

	private static final long serialVersionUID = 205221733282067526L;
	
	private String no_person;		//회원관리번호
	private String cd_fc;			//금융사 코드
	private String nm_agency;		//관리점명
	private String cd_default;		//등록사유
	private String ymd_default;		//연체등록일자
	private String amt_regist;		//등록금액
	private String no_case;			//사건번호

	public KcbOverduePublicInfo() {
	}
	
	public KcbOverduePublicInfo(String no_person, String cd_fc,
			String nm_agency, String cd_default, String ymd_default,
			String amt_regist, String no_case) {
		this.no_person = no_person;
		this.cd_fc = cd_fc;
		this.nm_agency = nm_agency;
		this.cd_default = cd_default;
		this.ymd_default = ymd_default;
		this.amt_regist = amt_regist;
		this.no_case = no_case;
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

	public String getNm_agency() {
		return nm_agency;
	}

	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}

	public String getCd_default() {
		return cd_default;
	}

	public void setCd_default(String cd_default) {
		this.cd_default = cd_default;
	}

	public String getYmd_default() {
		return ymd_default;
	}

	public void setYmd_default(String ymd_default) {
		this.ymd_default = ymd_default;
	}

	public String getAmt_regist() {
		return amt_regist;
	}

	public void setAmt_regist(String amt_regist) {
		this.amt_regist = amt_regist;
	}

	public String getNo_case() {
		return no_case;
	}

	public void setNo_case(String no_case) {
		this.no_case = no_case;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}