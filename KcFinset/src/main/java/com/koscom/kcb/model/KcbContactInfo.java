package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbContactInfo implements Serializable{
	
	private static final long serialVersionUID = -8133839860647025005L;

	private String no_person;	//회원관리번호
	private String cd_info;		//정보구분 - 01:휴대폰(연락처) / 02:이메일
	private String dt_regist;	//등록일자
	private String hp;			//휴대폰번호
	private String email;		//이메일주소
	
	public KcbContactInfo() {
	}

	public KcbContactInfo(String no_person, String cd_info, String dt_regist,
			String hp, String email) {
		this.no_person = no_person;
		this.cd_info = cd_info;
		this.dt_regist = dt_regist;
		this.hp = hp;
		this.email = email;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getCd_info() {
		return cd_info;
	}

	public void setCd_info(String cd_info) {
		this.cd_info = cd_info;
	}

	public String getDt_regist() {
		return dt_regist;
	}

	public void setDt_regist(String dt_regist) {
		this.dt_regist = dt_regist;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
