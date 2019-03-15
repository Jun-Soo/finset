package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbAddrInfo implements Serializable{
	
	private static final long serialVersionUID = -4733342841142893204L;

	private String no_person;		//회원관리번호
	private String cd_addr;			//주소구분 - 자택:01 / 실거주지:02 / 직장:03
	private String dt_regist;		//등록일자
	private String post_home;		//우편번호
	private String addr1_home;		//주소상
	private String addr2_home;		//주소하
	private String tel_home;		//전화번호
	private String type_addr;		//주소지구분
	
	public KcbAddrInfo() {
	}

	public KcbAddrInfo(String no_person, String cd_addr, String dt_regist,
			String post_home, String addr1_home, String addr2_home,
			String tel_home, String type_addr) {
		this.no_person = no_person;
		this.cd_addr = cd_addr;
		this.dt_regist = dt_regist;
		this.post_home = post_home;
		this.addr1_home = addr1_home;
		this.addr2_home = addr2_home;
		this.tel_home = tel_home;
		this.type_addr = type_addr;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getCd_addr() {
		return cd_addr;
	}

	public void setCd_addr(String cd_addr) {
		this.cd_addr = cd_addr;
	}

	public String getDt_regist() {
		return dt_regist;
	}

	public void setDt_regist(String dt_regist) {
		this.dt_regist = dt_regist;
	}

	public String getPost_home() {
		return post_home;
	}

	public void setPost_home(String post_home) {
		this.post_home = post_home;
	}

	public String getAddr1_home() {
		return addr1_home;
	}

	public void setAddr1_home(String addr1_home) {
		this.addr1_home = addr1_home;
	}

	public String getAddr2_home() {
		return addr2_home;
	}

	public void setAddr2_home(String addr2_home) {
		this.addr2_home = addr2_home;
	}

	public String getTel_home() {
		return tel_home;
	}

	public void setTel_home(String tel_home) {
		this.tel_home = tel_home;
	}

	public String getType_addr() {
		return type_addr;
	}

	public void setType_addr(String type_addr) {
		this.type_addr = type_addr;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
