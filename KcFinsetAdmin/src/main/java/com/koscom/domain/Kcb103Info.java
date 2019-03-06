package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Kcb103Info implements Serializable{
	private static final long serialVersionUID = 6545484228211526270L;
	protected String seq_kcb_response= "";	//조회일련번호KCB_103_INFO
	protected String no_person= "";	//회원관리번호KCB_103_INFO
	protected String segment_id= "";	//Segment IDKCB_103_INFO
	protected String cd_profile= "";	//프로파일 코드KCB_103_INFO
	protected String result_profile= "";	//프로파일 결과값KCB_103_INFO
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSeq_kcb_response() {
		return seq_kcb_response;
	}
	public void setSeq_kcb_response(String seq_kcb_response) {
		this.seq_kcb_response = seq_kcb_response;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getSegment_id() {
		return segment_id;
	}
	public void setSegment_id(String segment_id) {
		this.segment_id = segment_id;
	}
	public String getCd_profile() {
		return cd_profile;
	}
	public void setCd_profile(String cd_profile) {
		this.cd_profile = cd_profile;
	}
	public String getResult_profile() {
		return result_profile;
	}
	public void setResult_profile(String result_profile) {
		this.result_profile = result_profile;
	}
}
