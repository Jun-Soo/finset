package com.koscom.finance.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TxFcReceiveInfo;
import com.koscom.finset.model.FinsetVO;

public class TxFcReceiveVO extends TxFcReceiveInfo implements Serializable {
	private static final long serialVersionUID = -6530250014031933762L;

	//헤더 전문
	protected String cd_service; //서비스구분

	//1차 전문
	protected int item_cnt      ; //상품건수

	//2차 전문
	protected String sts_loan;//대출진행상태
	protected String amt_approval; // 승인금액
	protected String rto_approval; // 승인금리

	//3차 전문 응답
	protected long amt_pay;        //대출지급금액
	protected double rto_pay;        //대출지급금리
	protected long dt_pay;         //대출지급일자

	//기타
	protected String partner_cd;// 제휴사코드
	protected String etc_field1    ; //예비 필드1
	protected LinkedList<FinsetVO> listGoods; // 신청대출상품리스트(요청)
	protected List<FcLoanInfo> loan_info;     //대출상품 리스트(응답)
	protected byte[] bArrData;                // 헤더를 제외한 DATA부

	//TCP 공통헤더 사이즈
	public static int hd_comm_len = 324; //공통 헤더 사이즈

	//TCP 공통전문 헤더
	protected long hd_length    ; //전문전체길이
 	protected String hd_cd_service; //서비스구분
 	protected long hd_cd_if     ; //전문종별코드
 	protected long hd_cd_result ; //응답코드
 	protected String hd_cd_partner; //제휴사코드
 	protected String hd_err_msg   ; //오류메시지
 	protected long hd_seq       ; //전문일련번호
 	protected long hd_dt_send   ; //전문전송시간
 	protected String hd_filler    ; //FILLER

 	protected String cd_goods_gubun;  // 상품구분
 	protected String nm_fc;           // 금융사명
 	protected String nm_goods;        // 상품명
 	protected String cd_type_interest; //변동/고정
 	protected String cd_type_pay;	//상환방식
 	protected int apply_cnt;	//대출신청개수
	ArrayList<Throwable> listErr;

 	public String getNm_fc(){
 		return nm_fc;
 	}

 	public void setNm_fc(String nm_fc){
 		this.nm_fc = nm_fc;
 	}

 	public String getNm_goods(){
 		return nm_goods;
 	}

 	public void setNm_goods(String nm_goods){
 		this.nm_goods = nm_goods;
 	}

 	public String getCd_goods_gubun(){
 		return cd_goods_gubun;
 	}

 	public void setCd_Goods_gubun(String cd_goods_gubun){
 		this.cd_goods_gubun = cd_goods_gubun;
 	}

	public int getHd_comm_len() {
		return hd_comm_len;
	}

	public String getCd_service() {
		return cd_service;
	}

	public void setCd_service(String cd_service) {
		this.cd_service = cd_service;
	}

	public int getItem_cnt() {
		return item_cnt;
	}

	public void setItem_cnt(int item_cnt) {
		this.item_cnt = item_cnt;
	}

	public String getSts_loan() {
		return sts_loan;
	}

	public void setSts_loan(String sts_loan) {
		this.sts_loan = sts_loan;
	}

	public String getAmt_approval() {
		return amt_approval;
	}

	public void setAmt_approval(String amt_approval) {
		this.amt_approval = amt_approval;
	}

	public String getRto_approval() {
		return rto_approval;
	}

	public void setRto_approval(String rto_approval) {
		this.rto_approval = rto_approval;
	}

	public long getAmt_pay() {
		return amt_pay;
	}

	public void setAmt_pay(long amt_pay) {
		this.amt_pay = amt_pay;
	}

	public double getRto_pay() {
		return rto_pay;
	}

	public void setRto_pay(double rto_pay) {
		this.rto_pay = rto_pay;
	}

	public long getDt_pay() {
		return dt_pay;
	}

	public void setDt_pay(long dt_pay) {
		this.dt_pay = dt_pay;
	}

	public String getPartner_cd() {
		return partner_cd;
	}

	public void setPartner_cd(String partner_cd) {
		this.partner_cd = partner_cd;
	}

	public String getEtc_field1() {
		return etc_field1;
	}

	public void setEtc_field1(String etc_field1) {
		this.etc_field1 = etc_field1;
	}

	public LinkedList<FinsetVO> getListGoods() {
		return listGoods;
	}

	public void setListGoods(LinkedList<FinsetVO> listGoods) {
		this.listGoods = listGoods;
	}

	public List<FcLoanInfo> getLoan_info() {
		return loan_info;
	}

	public void setLoan_info(List<FcLoanInfo> loan_info) {
		this.loan_info = loan_info;
	}

	public byte[] getbArrData() {
		return bArrData;
	}

	public void setbArrData(byte[] bArrData) {
		this.bArrData = bArrData;
	}

	public long getHd_length() {
		return hd_length;
	}

	public void setHd_length(long hd_length) {
		this.hd_length = hd_length;
	}

	public String getHd_cd_service() {
		return hd_cd_service;
	}

	public void setHd_cd_service(String hd_cd_service) {
		this.hd_cd_service = hd_cd_service;
	}

	public long getHd_cd_if() {
		return hd_cd_if;
	}

	public void setHd_cd_if(long hd_cd_if) {
		this.hd_cd_if = hd_cd_if;
	}

	public long getHd_cd_result() {
		return hd_cd_result;
	}

	public void setHd_cd_result(long hd_cd_result) {
		this.hd_cd_result = hd_cd_result;
	}

	public String getHd_cd_partner() {
		return hd_cd_partner;
	}

	public void setHd_cd_partner(String hd_cd_partner) {
		this.hd_cd_partner = hd_cd_partner;
	}

	public String getHd_err_msg() {
		return hd_err_msg;
	}

	public void setHd_err_msg(String hd_err_msg) {
		this.hd_err_msg = hd_err_msg;
	}

	public long getHd_seq() {
		return hd_seq;
	}

	public void setHd_seq(long hd_seq) {
		this.hd_seq = hd_seq;
	}

	public long getHd_dt_send() {
		return hd_dt_send;
	}

	public void setHd_dt_send(long hd_dt_send) {
		this.hd_dt_send = hd_dt_send;
	}

	public String getHd_filler() {
		return hd_filler;
	}

	public void setHd_filler(String hd_filler) {
		this.hd_filler = hd_filler;
	}

	public String getCd_type_interest() {
		return cd_type_interest;
	}

	public void setCd_type_interest(String cd_type_interest) {
		this.cd_type_interest = cd_type_interest;
	}

	public String getCd_type_pay() {
		return cd_type_pay;
	}

	public void setCd_type_pay(String cd_type_pay) {
		this.cd_type_pay = cd_type_pay;
	}

	public int getApply_cnt() {
		return apply_cnt;
	}

	public void setApply_cnt(int apply_cnt) {
		this.apply_cnt = apply_cnt;
	}

	public ArrayList<Throwable> getListErr() {
		return listErr;
	}

	public void setListErr(ArrayList<Throwable> listErr) {
		this.listErr = listErr;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
