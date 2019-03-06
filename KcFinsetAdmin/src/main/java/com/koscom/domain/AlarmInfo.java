package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AlarmInfo implements Serializable {

	private static final long serialVersionUID = -2511666424650935454L;
	
	private String seq_alarm;			// 
	private String id_alarm_target;		// 알람대상자
	private String content;				// 알람내용
	private String ymd_send;			// 알람날짜
	private String his_send;			// 알람시간
	private String yn_confirm;			// 알람확인여부
	private String yn_alarm;			// 알람여부
	private String no_person;			// 고객번호
	private String id_frt;				// 최초등록자
	private String dt_frt;				// 최초등록일
	private String id_lst;				// 최종수정자
	private String dt_lst;				// 최종수정일
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getSeq_alarm() {
		return seq_alarm;
	}

	public void setSeq_alarm(String seq_alarm) {
		this.seq_alarm = seq_alarm;
	}

	public String getId_alarm_target() {
		return id_alarm_target;
	}

	public void setId_alarm_target(String id_alarm_target) {
		this.id_alarm_target = id_alarm_target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getYmd_send() {
		return ymd_send;
	}

	public void setYmd_send(String ymd_send) {
		this.ymd_send = ymd_send;
	}

	public String getHis_send() {
		return his_send;
	}

	public void setHis_send(String his_send) {
		this.his_send = his_send;
	}

	public String getYn_confirm() {
		return yn_confirm;
	}

	public void setYn_confirm(String yn_confirm) {
		this.yn_confirm = yn_confirm;
	}

	public String getYn_alarm() {
		return yn_alarm;
	}

	public void setYn_alarm(String yn_alarm) {
		this.yn_alarm = yn_alarm;
	}
	
	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
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
	
}
