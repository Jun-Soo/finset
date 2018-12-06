package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TxFcReceiveInfo implements Serializable{
	private static final long serialVersionUID = -5222363379872053162L;

	protected String no_bunch 			; // 순번
	protected String no_person 			; // 고객번호
	protected String no_prepare     ;//사전접수번호
	protected String cd_fc 				; // 금융사코드
	protected String no_fc_req 			; // 금융기관신청번호
	protected String no_edoc 			; // 전송방법
	protected String cd_goods 			; // 대출상품
	protected String yn_loan 			; // 대출가능
	protected int year_term 			; // 대출기간
	protected String rto_loan 			; // 예상금리
	protected long amt_limit 			; // 한도금액
	protected String etc_field 			; // 예비
	protected String dt_receive 		; // 수신일자
	protected String yn_receive 		; // 접수확인
	protected String hd_cd_partner_code ; // 제휴사코드
	protected String reason 			; // 사유
	protected String id_frt 			; //
	protected String dt_frt 			; //
	protected String id_lst 			; //
	protected String dt_lst 			; //
	protected String cd_status          ; //
	protected String min_dt_frt;		//최초입력시간
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getNo_bunch() {
		return no_bunch;
	}
	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_prepare() {
		return no_prepare;
	}
	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNo_fc_req() {
		return no_fc_req;
	}
	public void setNo_fc_req(String no_fc_req) {
		this.no_fc_req = no_fc_req;
	}
	public String getNo_edoc() {
		return no_edoc;
	}
	public void setNo_edoc(String no_edoc) {
		this.no_edoc = no_edoc;
	}
	public String getCd_goods() {
		return cd_goods;
	}
	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	public String getYn_loan() {
		return yn_loan;
	}
	public void setYn_loan(String yn_loan) {
		this.yn_loan = yn_loan;
	}
	public int getYear_term() {
		return year_term;
	}
	public void setYear_term(int year_term) {
		this.year_term = year_term;
	}
	public String getRto_loan() {
		return rto_loan;
	}
	public void setRto_loan(String rto_loan) {
		this.rto_loan = rto_loan;
	}
	public long getAmt_limit() {
		return amt_limit;
	}
	public void setAmt_limit(long amt_limit) {
		this.amt_limit = amt_limit;
	}
	public String getEtc_field() {
		return etc_field;
	}
	public void setEtc_field(String etc_field) {
		this.etc_field = etc_field;
	}
	public String getDt_receive() {
		return dt_receive;
	}
	public void setDt_receive(String dt_receive) {
		this.dt_receive = dt_receive;
	}
	public String getYn_receive() {
		return yn_receive;
	}
	public void setYn_receive(String yn_receive) {
		this.yn_receive = yn_receive;
	}
	public String getHd_cd_partner_code() {
		return hd_cd_partner_code;
	}
	public void setHd_cd_partner_code(String hd_cd_partner_code) {
		this.hd_cd_partner_code = hd_cd_partner_code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getMin_dt_frt() {
		return min_dt_frt;
	}
	public void setMin_dt_frt(String min_dt_frt) {
		this.min_dt_frt = min_dt_frt;
	}

	public String getCd_status() {
		return cd_status;
	}

	public void setCd_status(String cd_status) {
		this.cd_status = cd_status;
	}
}
