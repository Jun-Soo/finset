package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.scrap.model.sub.FundAnListVO;

public class ScrReqCardVO implements Serializable{
	private static final long serialVersionUID = 4307617372038776890L;
	String no_person = "";			//회원관리번호
	long   seq_scraping_result = 0;	//스크래핑 일련번호
	String cd_fc = "";				//금융사코드
	String ymd_stt = "";			//조회시작일
	String ymd_end = "";			//조회종료일
	String error_cd = "";			//에러코드
	String error_msg = "";			//에러메세지
	String dt_frt = "";				//등록일시
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
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getYmd_stt() {
		return ymd_stt;
	}
	public void setYmd_stt(String ymd_stt) {
		this.ymd_stt = ymd_stt;
	}
	public String getYmd_end() {
		return ymd_end;
	}
	public void setYmd_end(String ymd_end) {
		this.ymd_end = ymd_end;
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