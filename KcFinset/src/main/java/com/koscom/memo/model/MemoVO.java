package com.koscom.memo.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public class MemoVO implements Serializable{
	private static final long serialVersionUID = -8912406207356126390L;
	
	private String 			no_person; //회원관리번호
	private int				seq_memo_info; //메모 시퀀스
	private String 			no_manage_info; //정보관리번호
	private String 			memo_text; //텍스트 메모
	
	@JsonIgnore
	private MultipartFile 	file; //메모 이미지
	
	private String 			alarm_date; //알람일
	private String 			alarm_time; //알람시간
	
	private String			id_frt;	//최초작성자
	private String			dt_ftr; //최초작성일
	private String			id_lst;	//최근 수정자
	private String			dt_lst; //최근 수정일
	
	public MemoVO() {
	}

	public MemoVO(String no_person, int seq_memo_info, String no_manage_info,
			String memo_text, MultipartFile file, String alarm_date,
			String alarm_time, String id_frt, String dt_ftr, String id_lst,
			String dt_lst) {
		this.no_person = no_person;
		this.seq_memo_info = seq_memo_info;
		this.no_manage_info = no_manage_info;
		this.memo_text = memo_text;
		this.file = file;
		this.alarm_date = alarm_date;
		this.alarm_time = alarm_time;
		this.id_frt = id_frt;
		this.dt_ftr = dt_ftr;
		this.id_lst = id_lst;
		this.dt_lst = dt_lst;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public int getSeq_memo_info() {
		return seq_memo_info;
	}

	public void setSeq_memo_info(int seq_memo_info) {
		this.seq_memo_info = seq_memo_info;
	}

	public String getNo_manage_info() {
		return no_manage_info;
	}

	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
	}

	public String getMemo_text() {
		return memo_text;
	}

	public void setMemo_text(String memo_text) {
		this.memo_text = memo_text;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getAlarm_date() {
		return alarm_date;
	}

	public void setAlarm_date(String alarm_date) {
		this.alarm_date = alarm_date;
	}

	public String getAlarm_time() {
		return alarm_time;
	}

	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getDt_ftr() {
		return dt_ftr;
	}

	public void setDt_ftr(String dt_ftr) {
		this.dt_ftr = dt_ftr;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
