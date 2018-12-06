package com.koscom.finance.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TxFcTransmitInfo;
import com.koscom.goods.model.GoodsVO;

public class TxFcTransmitVO extends TxFcTransmitInfo implements Serializable {

	private static final long serialVersionUID = 6905318214464793402L;

	/**
	 * DB INSERT, UPDATE, DELETE, SELECT RESULTList
	 */

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
	protected String no_prepare    ; //FILLER

	protected String user_code; //사용자코드
	protected String cd_job_class;
	protected String partner_cd;// 제휴사코드

	protected String income_info;	//담보대출시 소득정보 구분 01 : 직장인  02 : 사업자
	protected String cd_agree; 	//약관동의
	protected String no_apply; // 접수번호
	protected String type_flex; //전송 방법 - 01 : TCP, 02 : JSON
	protected String cd_fc;

	///*현대저축은행 추가 START
	protected String cd_service; //서비스구분
	protected String jb_tp_majorinsure; //4대보험가입여부
	//현대저축은행 추가 END */
	protected List<GoodsVO> listGoods; //대출신청상품정보

	protected String no_edoc;//전문번호

	protected String cd_occupational_detail; //직군상세  TODO 추후 수정할수도 있음
	protected String cd_occupational; //직군  TODO 추후 수정할수도 있음
	protected String cd_worker_position; //직위  TODO 추후 수정할수도 있음

	protected String cd_work_position; 	//페퍼 최종 직업 코드
	protected String company_seg;		//company_seg

	public String getCd_work_position(){
		return cd_work_position;
	}

	public void setCd_work_position(String cd_work_position){
		this.cd_work_position = cd_work_position;
	}

	public String getCompany_seg(){
		return company_seg;
	}

	public void setCompany_seg(String company_seg){
		this.company_seg = company_seg;
	}

	public String getCd_service(){
		return cd_service;
	}

	public void setCd_service(String cd_service){
		this.cd_service = cd_service;
	}

	public String getNo_edoc(){
		return no_edoc;
	}

	public void setNo_edoc(String no_edoc){
		this.no_edoc = no_edoc;
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

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getCd_job_class() {
		return cd_job_class;
	}

	public void setCd_job_class(String cd_job_class) {
		this.cd_job_class = cd_job_class;
	}

	public String getPartner_cd() {
		return partner_cd;
	}

	public void setPartner_cd(String partner_cd) {
		this.partner_cd = partner_cd;
	}

	public String getIncome_info() {
		return income_info;
	}

	public void setIncome_info(String income_info) {
		this.income_info = income_info;
	}

	public String getCd_agree() {
		return cd_agree;
	}

	public void setCd_agree(String cd_agree) {
		this.cd_agree = cd_agree;
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getType_flex() {
		return type_flex;
	}

	public void setType_flex(String type_flex) {
		this.type_flex = type_flex;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getJb_tp_majorinsure() {
		return jb_tp_majorinsure;
	}

	public void setJb_tp_majorinsure(String jb_tp_majorinsure) {
		this.jb_tp_majorinsure = jb_tp_majorinsure;
	}

	public List<GoodsVO> getListGoods() {
		return listGoods;
	}

	public void setListGoods(List<GoodsVO> listGoods) {
		this.listGoods = listGoods;
	}

	public String getCd_occupational_detail() {
		return cd_occupational_detail;
	}

	public void setCd_occupational_detail(String cd_occupational_detail) {
		this.cd_occupational_detail = cd_occupational_detail;
	}

	public String getCd_occupational() {
		return cd_occupational;
	}

	public void setCd_occupational(String cd_occupational) {
		this.cd_occupational = cd_occupational;
	}

	public String getCd_worker_position() {
		return cd_worker_position;
	}

	public void setCd_worker_position(String cd_worker_position) {
		this.cd_worker_position = cd_worker_position;
	}

	public static int getHd_comm_len() {
		return hd_comm_len;
	}

	public static void setHd_comm_len(int hd_comm_len) {
		TxFcTransmitVO.hd_comm_len = hd_comm_len;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}