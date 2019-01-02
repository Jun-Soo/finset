/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180724~26
 * CONSUME 관련 데이터를 조회하기 위한 조건들이 담긴 데이터
 */
package com.koscom.scrapData.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeDataForm implements Serializable{
	
	private static final long serialVersionUID = -8962717273270567578L;

	//CONSUME_INFO 조회용
	private String no_person;	//회원 관리 번호
	
	private String dt_from;	//조회 시작일
	private String dt_to;	//조회 종료일
	
	private List<Integer> listSeqTranIncome;	// 내용이 급여인 것 중 자동등록된 row의 seq_tran 리스트
	
	public ConsumeDataForm() {
	}
	public ConsumeDataForm(String no_person, String dt_from, String dt_to,
			List<Integer> listSeqTranIncome) {
		this.no_person = no_person;
		this.dt_from = dt_from;
		this.dt_to = dt_to;
		this.listSeqTranIncome = listSeqTranIncome;
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
	public List<Integer> getListSeqTranIncome() {
		return listSeqTranIncome;
	}
	public void setListSeqTranIncome(List<Integer> listSeqTranIncome) {
		this.listSeqTranIncome = listSeqTranIncome;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
