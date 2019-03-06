package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AttachInfo implements Serializable{

	private static final long serialVersionUID = 1235021264589688923L;
	
	protected String no_apply; // 신청번호
	protected String seq_attach; // 파일번호
	protected String cd_attach; // 첨부파일구분
	protected String url_attach; // 첨부파일 URL
	protected String nm_ori_file; // 오리지널 파일명
	protected String path_save_file; // 저장된 경로
	protected String nm_save_file; // 저장된 파일명
	protected String etc_attach; // 첨부파일메모
	protected String yn_use; // 첨부파일 사용유무
	protected String yn_display; // 첨부파일 보이기/숨김
	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getSeq_attach() {
		return seq_attach;
	}

	public void setSeq_attach(String seq_attach) {
		this.seq_attach = seq_attach;
	}

	public String getCd_attach() {
		return cd_attach;
	}

	public void setCd_attach(String cd_attach) {
		this.cd_attach = cd_attach;
	}

	public String getUrl_attach() {
		return url_attach;
	}

	public void setUrl_attach(String url_attach) {
		this.url_attach = url_attach;
	}

	public String getNm_ori_file() {
		return nm_ori_file;
	}

	public void setNm_ori_file(String nm_ori_file) {
		this.nm_ori_file = nm_ori_file;
	}

	public String getPath_save_file() {
		return path_save_file;
	}

	public void setPath_save_file(String path_save_file) {
		this.path_save_file = path_save_file;
	}

	public String getNm_save_file() {
		return nm_save_file;
	}

	public void setNm_save_file(String nm_save_file) {
		this.nm_save_file = nm_save_file;
	}

	public String getEtc_attach() {
		return etc_attach;
	}

	public void setEtc_attach(String etc_attach) {
		this.etc_attach = etc_attach;
	}

	public String getYn_use() {
		return yn_use;
	}

	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}

	public String getYn_display() {
		return yn_display;
	}

	public void setYn_display(String yn_display) {
		this.yn_display = yn_display;
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
