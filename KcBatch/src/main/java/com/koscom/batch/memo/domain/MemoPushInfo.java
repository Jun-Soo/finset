/*
 * 작성자: 김휘경
 * 일자: 20180618~20180619
 * 설명: MemoVO 를 상속받으며, 메모 푸시를 위해 필요한 내용들이 추가된 클래스
 */
package com.koscom.batch.memo.domain;

public class MemoPushInfo extends MemoVO{
	private static final long serialVersionUID = -3387975337173205901L;
	
	//push를 보내기 위한 변수들
	private String yn_os; //운영체제 종류(1:안드로이드, 2:IOS)
	private String cd_push; //소리/진동 상태값
	private String fcm_token; //무엇?
	
	//push를 보낸 값을 히스토리 성으로 저장하기 위한 변수들
	private int seq_push; //push 시퀀스
	private String push_divcd;
	private String title; //제목
	private String body; //실제 보내질 메세지
	private String link_addr; //링크 주소
	private String yn_send; //보내졌는지 확인하는 변수? 현재는 무조건 Y
	private String yn_display; //표시되는지 확인하는 변수? 현재는 무조건 Y
	private String id_frt; //최조 작성자 id(no_person)
	private String id_lst; //마지막 수정자 id(no_person)
	
	public MemoPushInfo() {
	}

	public MemoPushInfo(String yn_os, String cd_push, String fcm_token,
			int seq_push, String push_divcd, String title, String body,
			String link_addr, String yn_send, String yn_display, String id_frt,
			String id_lst) {
		this.yn_os = yn_os;
		this.cd_push = cd_push;
		this.fcm_token = fcm_token;
		this.seq_push = seq_push;
		this.push_divcd = push_divcd;
		this.title = title;
		this.body = body;
		this.link_addr = link_addr;
		this.yn_send = yn_send;
		this.yn_display = yn_display;
		this.id_frt = id_frt;
		this.id_lst = id_lst;
	}



	public String getYn_os() {
		return yn_os;
	}

	public void setYn_os(String yn_os) {
		this.yn_os = yn_os;
	}

	public String getCd_push() {
		return cd_push;
	}

	public void setCd_push(String cd_push) {
		this.cd_push = cd_push;
	}

	public String getFcm_token() {
		return fcm_token;
	}

	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}

	public int getSeq_push() {
		return seq_push;
	}

	public void setSeq_push(int seq_push) {
		this.seq_push = seq_push;
	}

	public String getPush_divcd() {
		return push_divcd;
	}

	public void setPush_divcd(String push_divcd) {
		this.push_divcd = push_divcd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getLink_addr() {
		return link_addr;
	}

	public void setLink_addr(String link_addr) {
		this.link_addr = link_addr;
	}

	public String getYn_send() {
		return yn_send;
	}

	public void setYn_send(String yn_send) {
		this.yn_send = yn_send;
	}

	public String getYn_display() {
		return yn_display;
	}

	public void setYn_display(String yn_display) {
		this.yn_display = yn_display;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getId_lst() {
		return id_lst;
	}

	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}

	@Override
	public String toString() {
		return "MemoPushInfo [yn_os=" + yn_os + ", cd_push=" + cd_push
				+ ", fcm_token=" + fcm_token + ", seq_push=" + seq_push
				+ ", push_divcd=" + push_divcd + ", title=" + title + ", body="
				+ body + ", link_addr=" + link_addr + ", yn_send=" + yn_send
				+ ", yn_display=" + yn_display + ", id_frt=" + id_frt
				+ ", id_lst=" + id_lst + ", getNo_person()=" + getNo_person()
				+ ", getSeq_memo_info()=" + getSeq_memo_info()
				+ ", getNo_manage_info()=" + getNo_manage_info()
				+ ", getMemo_text()=" + getMemo_text() + ", getFile()="
				+ getFile() + ", getAlarm_date()=" + getAlarm_date()
				+ ", getAlarm_time()=" + getAlarm_time() + ", toString()="
				+ "]";
	}}
