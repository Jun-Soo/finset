package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BoardManage implements Serializable{
	
	private static final long serialVersionUID = 3662430024150129621L;
	
	protected String id_board;    // 게시판 ID
	protected String nm_board;    // 게시판 이름
	protected String etc_board;    // 게시판 설명
	protected String cd50_board_type;    // 게시판 사용 옵션
	protected String yn_use;    // 사용여부
	protected String id_open_agency;	//오픈 제휴사
	protected String id_frt;    // 등록 ID
	protected String dt_frt;    // 등록일자
	protected String id_lst;    // 수정 ID
	protected String dt_lst;    // 수정일자

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getId_board() {
		return id_board;
	}

	public void setId_board(String id_board) {
		this.id_board = id_board;
	}

	public String getNm_board() {
		return nm_board;
	}

	public void setNm_board(String nm_board) {
		this.nm_board = nm_board;
	}

	public String getEtc_board() {
		return etc_board;
	}

	public void setEtc_board(String etc_board) {
		this.etc_board = etc_board;
	}

	public String getCd50_board_type() {
		return cd50_board_type;
	}

	public void setCd50_board_type(String cd50_board_type) {
		this.cd50_board_type = cd50_board_type;
	}

	public String getYn_use() {
		return yn_use;
	}

	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	
	public String getId_open_agency() {
		return id_open_agency;
	}

	public void setId_open_agency(String id_open_agency) {
		this.id_open_agency = id_open_agency;
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
