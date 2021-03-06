package com.koscom.consume.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeForm implements Serializable{
	
	private static final long serialVersionUID = -8962717273270567578L;

	//CONSUME_INFO list, SCR_TRANSACTION_DETAIL 조회용
	private String	no_person;				//회원 관리 번호
	private List<String> no_person_list;	//회원 관리 번호 리스트
	private String	no_card;				//카드(계좌) 번호
	
	//CONSUME_INFO get 조회용
	private int		seq_consume;			//일련번호
	
	//CONUSME_INFO list 조회용
	private String	ym_trd;					//거래 년월
	
	//CONSUME_INFO 수입,지출 조회
	private String	type_in_out;			//수입,지출 구분 - 01:수입, 02:지출
	
	//CONSUME_INFO 캘린더 list 조회
	private String ymd_trd;					//거래 년월일
	
	//CONSUME_INFO 년 캘린더 list 조회
	private String y_trd;					//거래년
	
	//CONSUME_INFO 이력 상세 조회
	private String contents;				//내용
	
	public ConsumeForm() {
	}
	public ConsumeForm(String no_person, List<String> no_person_list,
			String no_card, int seq_consume, String ym_trd, String type_in_out,
			String ymd_trd, String y_trd, String contents) {
		this.no_person = no_person;
		this.no_person_list = no_person_list;
		this.no_card = no_card;
		this.seq_consume = seq_consume;
		this.ym_trd = ym_trd;
		this.type_in_out = type_in_out;
		this.ymd_trd = ymd_trd;
		this.y_trd = y_trd;
		this.contents = contents;
	}
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public List<String> getNo_person_list() {
		return no_person_list;
	}
	public void setNo_person_list(List<String> no_person_list) {
		this.no_person_list = no_person_list;
	}
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
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
	public String getY_trd() {
		return y_trd;
	}
	public void setY_trd(String y_trd) {
		this.y_trd = y_trd;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
