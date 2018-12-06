package com.koscom.contents.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ContentsVO  implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1080220102800367260L;

	//뉴스관리
	private int seq_news; //기사일련번호
	private int seq_search; //검색일련번호
	private String search_query; //키워드
	private String title; //기사제목
	private String link; //기사링크
	private String description; //기사요약
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
	private int seq_file; //파일번호
	private String file_type; //파일종류(01-썸네일)
	private String nm_img_files; //이미지파일이름
	private String id_frt; //최초입력아이디
	private String id_lst; //최종수정아이디
	private String dt_lst; //최종수정시간
	private MultipartFile imgfiles;	//파일첨부
	private byte[] img_files;
	private String seq_thum_file; //썸네일파일 번호

	//소비분류관리
	private String cd_consume_class;
	private String cd_class;
	private String nm_class;
	private String cd_type;
	private String nm_type;
	private String cd_fc;
	private String nm_fc;
	private String cd_business;
	private String nm_business;
	private String cd_proc_type;

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
	public String getSearch_query() {
		return search_query;
	}
	public void setSearch_query(String search_query) {
		this.search_query = search_query;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getSeq_file() {
		return seq_file;
	}
	public void setSeq_file(int seq_file) {
		this.seq_file = seq_file;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getNm_img_files() {
		return nm_img_files;
	}
	public void setNm_img_files(String nm_img_files) {
		this.nm_img_files = nm_img_files;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
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
	public MultipartFile getImgfiles() {
		return imgfiles;
	}
	public void setImgfiles(MultipartFile imgfiles) {
		this.imgfiles = imgfiles;
	}
	public byte[] getImg_files() {
		return img_files;
	}
	public void setImg_files(byte[] img_files) {
		this.img_files = img_files;
	}
	public String getSeq_thum_file() {
		return seq_thum_file;
	}
	public void setSeq_thum_file(String seq_thum_file) {
		this.seq_thum_file = seq_thum_file;
	}
	public String getCd_consume_class() {
		return cd_consume_class;
	}
	public void setCd_consume_class(String cd_consume_class) {
		this.cd_consume_class = cd_consume_class;
	}
	public String getCd_class() {
		return cd_class;
	}
	public void setCd_class(String cd_class) {
		this.cd_class = cd_class;
	}
	public String getNm_class() {
		return nm_class;
	}
	public void setNm_class(String nm_class) {
		this.nm_class = nm_class;
	}
	public String getCd_type() {
		return cd_type;
	}
	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}
	public String getNm_type() {
		return nm_type;
	}
	public void setNm_type(String nm_type) {
		this.nm_type = nm_type;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	public String getCd_business() {
		return cd_business;
	}
	public void setCd_business(String cd_business) {
		this.cd_business = cd_business;
	}
	public String getNm_business() {
		return nm_business;
	}
	public void setNm_business(String nm_business) {
		this.nm_business = nm_business;
	}
	public String getCd_proc_type() {
		return cd_proc_type;
	}
	public void setCd_proc_type(String cd_proc_type) {
		this.cd_proc_type = cd_proc_type;
	}


}