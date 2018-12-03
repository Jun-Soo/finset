package com.koscom.batch.news.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class NewsResultVO  implements Serializable {

	private static final long serialVersionUID = 239351275809276415L;

	//네이버 출력API PARAM
	private String title; //검색 결과 문서의 title
	private String originallink; //검색 결과 문서의 제공 언론사 하이퍼텍스트 link
	private String link; //검색 결과 문서의 제공 네이버 하이퍼텍스트 link
	private String description; //검색 결과 문서의 내용을 요약한 패시지 정보, 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
	private String pubDate; //datetime, 검색 결과 문서가 네이버에 제공된 시간

	private int seq_news; //기사일련번호
	private int seq_search; //검색일련번호
	private String pub_date; //네이버등록시간
	private String news_company; //뉴스원
	private String local_link_text; //연계버튼메세지
	private String local_link; //연계링크
	private String contents; //기사내용
	private String news_status; //상태
	private String keyword; //키워드(검색용)
	private String yn_use; //사용여부
	private String dt_wrt; //작성시간
	private String dt_frt; //최초입력시간

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginallink() {
		return originallink;
	}
	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public int getSeq_news() {
		return seq_news;
	}
	public void setSeq_news(int seq_news) {
		this.seq_news = seq_news;
	}
	public int getSeq_search() {
		return seq_search;
	}
	public void setSeq_search(int seq_search) {
		this.seq_search = seq_search;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getNews_company() {
		return news_company;
	}
	public void setNews_company(String news_company) {
		this.news_company = news_company;
	}
	public String getLocal_link_text() {
		return local_link_text;
	}
	public void setLocal_link_text(String local_link_text) {
		this.local_link_text = local_link_text;
	}
	public String getLocal_link() {
		return local_link;
	}
	public void setLocal_link(String local_link) {
		this.local_link = local_link;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getNews_status() {
		return news_status;
	}
	public void setNews_status(String news_status) {
		this.news_status = news_status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getDt_wrt() {
		return dt_wrt;
	}
	public void setDt_wrt(String dt_wrt) {
		this.dt_wrt = dt_wrt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}


}