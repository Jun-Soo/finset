package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PushInfo implements Serializable {

	private static final long serialVersionUID = 8225189203706876786L;

	private long seq_push;		// seq
	private String title;		// 제목
	private String body;		// 내용
	private String link_addr;   // 링크주소
	private String id_frt;		// 작성자 아이디
	private String dt_frt;	    // 작성일
	private long rnum;
	private String push_gubun;	// 분류 전체발송,개별발송
	private String no_person;	// 회원번호
	private String nm_person;	// 회원명
	private String push_divcd;	// 발송구분
	private String[] array_no_person; //발송 회원리스트
	private String yn_os;
	private String yn_push;
	private String yn_send;
	private String yn_display;
	private String hp;
	
	private String id_lst = "";	//최종입력아이디
	private String dt_lst = "";	//최종입력날짜

	public long getSeq_push() {
		return seq_push;
	}

	public void setSeq_push(long seq_push) {
		this.seq_push = seq_push;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public long getRnum() {
		return rnum;
	}

	public void setRnum(long rnum) {
		this.rnum = rnum;
	}

	public String getPush_gubun() {
		return push_gubun;
	}

	public void setPush_gubun(String push_gubun) {
		this.push_gubun = push_gubun;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getPush_divcd() {
		return push_divcd;
	}

	public void setPush_divcd(String push_divcd) {
		this.push_divcd = push_divcd;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String[] getArray_no_person() {
		return array_no_person;
	}

	public void setArray_no_person(String[] array_no_person) {
		this.array_no_person = array_no_person;
	}

	public String getYn_os() {
		return yn_os;
	}

	public void setYn_os(String yn_os) {
		this.yn_os = yn_os;
	}

	public String getYn_push() {
		return yn_push;
	}

	public void setYn_push(String yn_push) {
		this.yn_push = yn_push;
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

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
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
