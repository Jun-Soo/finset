package com.koscom.consume.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeForm implements Serializable{
	
	private static final long serialVersionUID = -8962717273270567578L;

	private String			no_person;				//회원 관리 번호
	private List<String> 	no_person_list;			//회원 관리 번호 리스트
	private String			cd_fc;					//금융사관리번호
	private String			no_card;				//카드(계좌) 번호
	private int				seq_consume;			//일련번호
	private String			yn_pay_installment;		//할부분할여부
	private String			no_approval;			//승인번호
	private String			ym_trd;					//거래 년월
	private String			type_in_out;			//수입,지출 구분 - 01:수입, 02:지출
	private String			ymd_trd;				//거래 년월일
	private String			y_trd;					//거래년
	private String			contents;				//내용
	private int				seq_tran;				//입출금일련번호
	
	private String dt_from; 		//소비 통계 날짜 조회 범위 
	private String dt_to;
	private String orderType;		//소비 통계 정렬 타입
	private String listType;		//소비 통계 정렬 타입
	private String chartType; 		//chart 타입
	
	public ConsumeForm() {
	}
	public ConsumeForm(String no_person, List<String> no_person_list,
			String cd_fc, String no_card, int seq_consume,
			String yn_pay_installment, String no_approval, String ym_trd,
			String type_in_out, String ymd_trd, String y_trd, String contents,
			int seq_tran, String dt_from, String dt_to, String orderType,
			String listType, String chartType) {
		this.no_person = no_person;
		this.no_person_list = no_person_list;
		this.cd_fc = cd_fc;
		this.no_card = no_card;
		this.seq_consume = seq_consume;
		this.yn_pay_installment = yn_pay_installment;
		this.no_approval = no_approval;
		this.ym_trd = ym_trd;
		this.type_in_out = type_in_out;
		this.ymd_trd = ymd_trd;
		this.y_trd = y_trd;
		this.contents = contents;
		this.seq_tran = seq_tran;
		this.dt_from = dt_from;
		this.dt_to = dt_to;
		this.orderType = orderType;
		this.listType = listType;
		this.chartType = chartType;
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
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
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
	public String getYn_pay_installment() {
		return yn_pay_installment;
	}
	public void setYn_pay_installment(String yn_pay_installment) {
		this.yn_pay_installment = yn_pay_installment;
	}
	public String getNo_approval() {
		return no_approval;
	}
	public void setNo_approval(String no_approval) {
		this.no_approval = no_approval;
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
	public int getSeq_tran() {
		return seq_tran;
	}
	public void setSeq_tran(int seq_tran) {
		this.seq_tran = seq_tran;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
