package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.scrap.model.sub.FundAnListVO;

public class ScrRsltScrapVO implements Serializable{
	private static final long serialVersionUID = -8657077777122454683L;
	String no_person = "";		//회원관리번호
	long   seq_scraping_result = 0;	//스크래핑 일련번호
	String cd_scrap = "";		//스크래핑 구분
	String rslt_scraping = "";	//스크래핑 결과
	String no_biz_comp = "";	//사업자번호
	String dr_frt = "";			//등록일시
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
	public String getCd_scrap() {
		return cd_scrap;
	}
	public void setCd_scrap(String cd_scrap) {
		this.cd_scrap = cd_scrap;
	}
	public String getRslt_scraping() {
		return rslt_scraping;
	}
	public void setRslt_scraping(String rslt_scraping) {
		this.rslt_scraping = rslt_scraping;
	}
	public String getNo_biz_comp() {
		return no_biz_comp;
	}
	public void setNo_biz_comp(String no_biz_comp) {
		this.no_biz_comp = no_biz_comp;
	}
	public String getDr_frt() {
		return dr_frt;
	}
	public void setDr_frt(String dr_frt) {
		this.dr_frt = dr_frt;
	}
}