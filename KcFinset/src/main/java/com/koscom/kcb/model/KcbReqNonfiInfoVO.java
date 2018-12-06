package com.koscom.kcb.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class KcbReqNonfiInfoVO implements Serializable {
	private static final long serialVersionUID = 52054652175416114L;
	private long   seq_kcb_reg = 0;				//등록일련번호
	private String cd_req = "";					//요청구분
	private String no_person = "";				//회원관리번호
	private long   seq_scraping_result = 0;		//스크래핑 일련번호
	private String status = "";					//상태
	private String dt_reg = "";					//등록일자
	private String dt_req = "";					//요청일자
	private String dt_send = "";				//전송완료일자
	private String cd_err = "";					//오류응답코드
	private String bit_err = "";				//오류체크비트
	private String id_frt = "";					//최초입력아이디
	private String dt_frt = "";					//최초입력시간
	private String id_lst = "";					//최종수정아이디
	private String dt_lst = "";					//최종수정시간
	public long getSeq_kcb_reg() {
		return seq_kcb_reg;
	}
	public void setSeq_kcb_reg(long seq_kcb_reg) {
		this.seq_kcb_reg = seq_kcb_reg;
	}
	public String getCd_req() {
		return cd_req;
	}
	public void setCd_req(String cd_req) {
		this.cd_req = cd_req;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public long getSeq_scraping_result() {
		return seq_scraping_result;
	}
	public void setSeq_scraping_result(long seq_scraping_result) {
		this.seq_scraping_result = seq_scraping_result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDt_reg() {
		return dt_reg;
	}
	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}
	public String getDt_req() {
		return dt_req;
	}
	public void setDt_req(String dt_req) {
		this.dt_req = dt_req;
	}
	public String getDt_send() {
		return dt_send;
	}
	public void setDt_send(String dt_send) {
		this.dt_send = dt_send;
	}
	public String getCd_err() {
		return cd_err;
	}
	public void setCd_err(String cd_err) {
		this.cd_err = cd_err;
	}
	public String getBit_err() {
		return bit_err;
	}
	public void setBit_err(String bit_err) {
		this.bit_err = bit_err;
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