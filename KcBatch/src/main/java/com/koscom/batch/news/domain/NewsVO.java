package com.koscom.batch.news.domain;

import java.util.List;

/**
 * Created by lee on 2017-10-20.
 */

public class NewsVO {

	private static final long serialVersionUID = 7828890049046758009L;

	//네이버 출력API PARAM
	private String lastBuildDate; //datetime, 검색결과 생성시간
	private int total = 0; //integer, 검색결과 문서의 총 개수
	private int start = 0; //integer, 검색 결과 문서 중, 문서의 시작점
	private int display = 0; //integer, 검색된 검색 결과의 개수
	private List<NewsResultVO> items;  //뉴스API Item리스트
	private String errorCode; //에러코드
	private String errorMessage; //에러메세지

	private int seq_search; //검색일련번호
	private String search_query; //검색 문자열
	private int req_display_cnt; //요청출력건수
	private String sort_option; //정렬옵션
	private int result_cnt; //검색결과수 (네이버 출력API PARAM의 total)
	private String result_date; //검색결과 생성시간 (네이버 출력 API PARAM의 lastBuildDate)
	private String err_cd; //에러코드(네이버 출력 API PARAM의 errorCode)
	private String err_msg; //에러메세지(네이버 출력 API PARAM의 errorMessage)
	private String dt_frt; //최초 입력시간

	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public List<NewsResultVO> getItems() {
		return items;
	}
	public void setItems(List<NewsResultVO> items) {
		this.items = items;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getSeq_search() {
		return seq_search;
	}
	public void setSeq_search(int seq_search) {
		this.seq_search = seq_search;
	}
	public String getSearch_query() {
		return search_query;
	}
	public void setSearch_query(String search_query) {
		this.search_query = search_query;
	}
	public int getReq_display_cnt() {
		return req_display_cnt;
	}
	public void setReq_display_cnt(int req_display_cnt) {
		this.req_display_cnt = req_display_cnt;
	}
	public String getSort_option() {
		return sort_option;
	}
	public void setSort_option(String sort_option) {
		this.sort_option = sort_option;
	}
	public int getResult_cnt() {
		return result_cnt;
	}
	public void setResult_cnt(int result_cnt) {
		this.result_cnt = result_cnt;
	}
	public String getResult_date() {
		return result_date;
	}
	public void setResult_date(String result_date) {
		this.result_date = result_date;
	}
	public String getErr_cd() {
		return err_cd;
	}
	public void setErr_cd(String err_cd) {
		this.err_cd = err_cd;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	@Override
	public String toString() {
		return "NewsVO [lastBuildDate=" + lastBuildDate + ", total=" + total + ", start=" + start + ", display="
				+ display + ", items=" + items + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ ", seq_search=" + seq_search + ", search_query=" + search_query + ", req_display_cnt="
				+ req_display_cnt + ", sort_option=" + sort_option + ", result_cnt=" + result_cnt + ", result_date="
				+ result_date + ", err_cd=" + err_cd + ", err_msg=" + err_msg + ", dt_frt=" + dt_frt + "]";
	}


}