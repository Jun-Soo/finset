package com.koscom.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SmsHistory {
	protected String seq_sms;                // 순서
	protected String no_person;              // 고객번호
	protected String nm_person;              // 고객명
	protected String ph_sender;              // 발신번호
	protected String ph_receiver;            // 수신번호
	protected String c1_type_msg;            // SMS / MMS 저장 구분
	protected String content_before;         // 최초 전송문자열
	protected String content;                // 내용
	protected String c4_trans_result;        // 전송결과
	protected String yn_send_reservation;    // 예약
	protected String dt_send;                // 전송일자
	protected String yn_view;                // 
	                                         
	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSeq_sms() {
		return seq_sms;
	}
	public void setSeq_sms(String seq_sms) {
		this.seq_sms = seq_sms;
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
	public String getPh_sender() {
		return ph_sender;
	}
	public void setPh_sender(String ph_sender) {
		this.ph_sender = ph_sender;
	}
	public String getPh_receiver() {
		return ph_receiver;
	}
	public void setPh_receiver(String ph_receiver) {
		this.ph_receiver = ph_receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getC4_trans_result() {
		return c4_trans_result;
	}
	public void setC4_trans_result(String c4_trans_result) {
		this.c4_trans_result = c4_trans_result;
	}
	public String getYn_send_reservation() {
		return yn_send_reservation;
	}
	public void setYn_send_reservation(String yn_send_reservation) {
		this.yn_send_reservation = yn_send_reservation;
	}
	public String getDt_send() {
		return dt_send;
	}
	public void setDt_send(String dt_send) {
		this.dt_send = dt_send;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}
	public String getYn_view() {
		return yn_view;
	}
	public void setYn_view(String yn_view) {
		this.yn_view = yn_view;
	}
	public String getC1_type_msg() {
		return c1_type_msg;
	}
	public void setC1_type_msg(String c1_type_msg) {
		this.c1_type_msg = c1_type_msg;
	}
	public String getContent_before() {
		return content_before;
	}
	public void setContent_before(String content_before) {
		this.content_before = content_before;
	}
	
}
