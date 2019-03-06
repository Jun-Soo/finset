package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.scrap.model.sub.FundAnListVO;

public class ScrReqPensionVO implements Serializable{
	private static final long serialVersionUID = 5456629561552273372L;
	String no_person = "";					//회원관리번호
	long   seq_scraping_result = 0;			//스크래핑 일련번호
	String cd_confirm = "";					//확인서구분
	String inquiry_start_yearmonth = "";	//조회시작연월
	String inquiry_end_yearmonth = "";		//조회종료연월
	String error_cd = "";					//에러코드
	String error_msg = "";					//에러메세지
	String dt_frt = "";						//등록일시
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
	public String getCd_confirm() {
		return cd_confirm;
	}
	public void setCd_confirm(String cd_confirm) {
		this.cd_confirm = cd_confirm;
	}
	public String getInquiry_start_yearmonth() {
		return inquiry_start_yearmonth;
	}
	public void setInquiry_start_yearmonth(String inquiry_start_yearmonth) {
		this.inquiry_start_yearmonth = inquiry_start_yearmonth;
	}
	public String getInquiry_end_yearmonth() {
		return inquiry_end_yearmonth;
	}
	public void setInquiry_end_yearmonth(String inquiry_end_yearmonth) {
		this.inquiry_end_yearmonth = inquiry_end_yearmonth;
	}
	public String getError_cd() {
		return error_cd;
	}
	public void setError_cd(String error_cd) {
		this.error_cd = error_cd;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
}