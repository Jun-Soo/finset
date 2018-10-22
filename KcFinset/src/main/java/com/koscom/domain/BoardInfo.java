package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BoardInfo implements Serializable{

	private static final long serialVersionUID = -3806143231559839918L;

	protected String seq;    // 게시물 번호
	protected String seq_group;    // 게시물 원글번호
	protected String seq_level;    // 답글 레벨
	protected String seq_order;    // 답글 순서
	protected String id_board;    // 게시판 ID
	protected String title;    // 게시물 제목
	protected String hit;    // 조회수
	protected String pass_board;    // 게시물 비밀번호
	protected String content;    // 게시물 내용
	protected String url_files1;    // 첨부파일1
	protected String url_files2;    // 첨부파일2
	protected String org_files1;    // 첨부파일1
	protected String org_files2;    // 첨부파일2
	protected String yn_use;    // 사용여부
	protected String yn_popup;    // 팝업 사용여부
	protected String id_frt;    // 등록 ID
	protected String dt_frt;    // 등록일자
	protected String id_lst;    // 수정 ID
	protected String dt_lst;    // 수정일자
	protected String nm_person;    // 작성자
	protected String board_idx;


	/* srchou */
	protected String ymd_post_strt;	// 게시시작일
	protected String ymd_post_end;	// 게시종료일
	protected String post_yn;			// 기간외게시여부
	protected String cd_event_proc;	// 이벤트진행코드(01예정 / 02진행중 / 03종료)
	protected String nm_event_proc; 	//이벤트진행코드명
	protected String nm_img_files;
	private String nm_img_files1;
	private String nm_img_files2;
	private String file_type;
	private int seq_file;
	/* srchou */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq_group() {
		return seq_group;
	}

	public void setSeq_group(String seq_group) {
		this.seq_group = seq_group;
	}

	public String getSeq_level() {
		return seq_level;
	}

	public void setSeq_level(String seq_level) {
		this.seq_level = seq_level;
	}

	public String getSeq_order() {
		return seq_order;
	}

	public void setSeq_order(String seq_order) {
		this.seq_order = seq_order;
	}

	public String getId_board() {
		return id_board;
	}

	public void setId_board(String id_board) {
		this.id_board = id_board;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getPass_board() {
		return pass_board;
	}

	public void setPass_board(String pass_board) {
		this.pass_board = pass_board;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl_files1() {
		return url_files1;
	}

	public void setUrl_files1(String url_files1) {
		this.url_files1 = url_files1;
	}

	public String getUrl_files2() {
		return url_files2;
	}

	public void setUrl_files2(String url_files2) {
		this.url_files2 = url_files2;
	}

	public String getOrg_files1() {
		return org_files1;
	}

	public void setOrg_files1(String org_files1) {
		this.org_files1 = org_files1;
	}

	public String getOrg_files2() {
		return org_files2;
	}

	public void setOrg_files2(String org_files2) {
		this.org_files2 = org_files2;
	}

	public String getYn_use() {
		return yn_use;
	}

	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}

	public String getYn_popup() {
		return yn_popup;
	}

	public void setYn_popup(String yn_popup) {
		this.yn_popup = yn_popup;
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

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}

	public String getYmd_post_strt() {
		return ymd_post_strt;
	}

	public void setYmd_post_strt(String ymd_post_strt) {
		this.ymd_post_strt = ymd_post_strt;
	}

	public String getYmd_post_end() {
		return ymd_post_end;
	}

	public void setYmd_post_end(String ymd_post_end) {
		this.ymd_post_end = ymd_post_end;
	}

	public String getPost_yn() {
		return post_yn;
	}

	public void setPost_yn(String post_yn) {
		this.post_yn = post_yn;
	}

	public String getCd_event_proc() {
		return cd_event_proc;
	}

	public void setCd_event_proc(String cd_event_proc) {
		this.cd_event_proc = cd_event_proc;
	}

	public String getNm_event_proc() {
		return nm_event_proc;
	}

	public void setNm_event_proc(String nm_event_proc) {
		this.nm_event_proc = nm_event_proc;
	}

	public String getNm_img_files() {
		return nm_img_files;
	}

	public void setNm_img_files(String nm_img_files) {
		this.nm_img_files = nm_img_files;
	}

	public String getNm_img_files1() {
		return nm_img_files1;
	}

	public void setNm_img_files1(String nm_img_files1) {
		this.nm_img_files1 = nm_img_files1;
	}

	public String getNm_img_files2() {
		return nm_img_files2;
	}

	public void setNm_img_files2(String nm_img_files2) {
		this.nm_img_files2 = nm_img_files2;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public int getSeq_file() {
		return seq_file;
	}

	public void setSeq_file(int seq_file) {
		this.seq_file = seq_file;
	}

}
