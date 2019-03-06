package com.koscom.domain;

import java.io.Serializable;

public class PersonShareMessageInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -300525729666321664L;

	private String seq_share; //공유요청일련번호
	private String type_message; //요청메세지타입( 01 - SMS / 02 - PUSH )
	private String cd_message; //메세지구분( 01 - 공유(재)요청 / 02 - 정보 업데이트)
	private String req_status; //요청상태( 01 - 요청 / 02 - 재요청 / 03 - 응답)
	private String dt_req; //요청일
	private String req_message; //요청메세지
	private String res_message; //응답메세지
	private String id_frt; //최초입력아이디
	private String dt_frt; //최초입력시간
	private String id_lst; //최종수정아이디
	private String dt_lst; //최종수정시간

	public String getSeq_share() {
		return seq_share;
	}
	public void setSeq_share(String seq_share) {
		this.seq_share = seq_share;
	}
	public String getType_message() {
		return type_message;
	}
	public void setType_message(String type_message) {
		this.type_message = type_message;
	}
	public String getCd_message() {
		return cd_message;
	}
	public void setCd_message(String cd_message) {
		this.cd_message = cd_message;
	}
	public String getReq_status() {
		return req_status;
	}
	public void setReq_status(String req_status) {
		this.req_status = req_status;
	}
	public String getDt_req() {
		return dt_req;
	}
	public void setDt_req(String dt_req) {
		this.dt_req = dt_req;
	}
	public String getReq_message() {
		return req_message;
	}
	public void setReq_message(String req_message) {
		this.req_message = req_message;
	}
	public String getRes_message() {
		return res_message;
	}
	public void setRes_message(String res_message) {
		this.res_message = res_message;
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

}
