package com.koscom.consume.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeForm implements Serializable{
	
	private static final long serialVersionUID = -8962717273270567578L;

	//CONSUME_INFO list, SCR_TRANSACTION_DETAIL 조회용
	private String	no_person;		//회원 관리 번호
	
	private String	dt_from;		//조회 시작일
	private String	dt_to;			//조회 종료일
	
	//스크래핑 데이터 조회용
	private String	tm_from;		//조회 시작 시간 
	
	//CONSUME_INFO get 조회용
	private int		seq_consume;	//일련번호
	
	//CONUSME_INFO stats list 조회용
	private String	ym_trd;			//거래 년월
	
	//CONSUME_INFO 수입,지출 조회
	private String	type_in_out;	//수입,지출 구분 - 01:수입, 02:지출
	
	//CONSUME_INFO 캘린더 list 조회
	private String ymd_trd;			//거래 년월일
	
	public ConsumeForm() {
	}
	public ConsumeForm(String no_person, String dt_from, String dt_to,
			String tm_from, int seq_consume, String ym_trd, String type_in_out,
			String ymd_trd) {
		this.no_person = no_person;
		this.dt_from = dt_from;
		this.dt_to = dt_to;
		this.tm_from = tm_from;
		this.seq_consume = seq_consume;
		this.ym_trd = ym_trd;
		this.type_in_out = type_in_out;
		this.ymd_trd = ymd_trd;
	}

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getDt_from() {
		return dt_from;
	}
	public void setDt_from(String dt_from) {
		this.dt_from = dt_from;
	}
	public String getDt_to() {
		return dt_to;
	}
	public void setDt_to(String dt_to) {
		this.dt_to = dt_to;
	}
	public String getTm_from() {
		return tm_from;
	}
	public void setTm_from(String tm_from) {
		this.tm_from = tm_from;
	}
	public int getSeq_consume() {
		return seq_consume;
	}
	public void setSeq_consume(int seq_consume) {
		this.seq_consume = seq_consume;
	}
	public String getYm_trd() {
		return ym_trd;
	}
	public void setYm_trd(String ym_trd) {
		this.ym_trd = ym_trd;
	}
	public String getType_in_out() {
		return type_in_out;
	}
	public void setType_in_out(String type_in_out) {
		this.type_in_out = type_in_out;
	}
	public String getYmd_trd() {
		return ymd_trd;
	}
	public void setYmd_trd(String ymd_trd) {
		this.ymd_trd = ymd_trd;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
