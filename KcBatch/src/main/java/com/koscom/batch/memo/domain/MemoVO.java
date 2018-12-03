/*
 * 메모 데이터를 가질 VO객체
 * 작성자: 김휘경
 * 작성일: 20180604~20180608
 */

package com.koscom.batch.memo.domain;

import java.io.Serializable;

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
	
	public MemoVO() {
	}

	public MemoVO(String no_person, int seq_memo_info, String no_manage_info,
			String memo_text, MultipartFile file, String alarm_date,
			String alarm_time) {
		this.no_person = no_person;
		this.seq_memo_info = seq_memo_info;
		this.no_manage_info = no_manage_info;
		this.memo_text = memo_text;
		this.file = file;
		this.alarm_date = alarm_date;
		this.alarm_time = alarm_time;
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

	@Override
	public String toString() {
		return "MemoVO [no_person=" + no_person + ", seq_memo_info="
				+ seq_memo_info + ", no_manage_info=" + no_manage_info
				+ ", memo_text=" + memo_text + ", file=" + file
				+ ", alarm_date=" + alarm_date + ", alarm_time=" + alarm_time
				+ "]";
	}
}
