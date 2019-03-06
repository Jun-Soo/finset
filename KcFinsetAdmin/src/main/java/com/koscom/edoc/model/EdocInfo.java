package com.koscom.edoc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EdocInfo implements Serializable{
	private static final long serialVersionUID = 4459212251605020066L;
	private String seq_edoc; // 전문번호
	private String cd_fc; // 금융사코드
	private String no_edoc; // 전문번호
	private String nm_edoc; // 전문명
	private String type_txrx;//송수신구분
	private String type_flex;//	
	private String etc;        // 비고
	private String seq_order;  // 우선순위
	private String yn_use;     // 사용유무
	private String id_frt;     // 최초작성자
	private String dt_frt;
	private String id_lst;     // 최종작성자
	private String dt_lst;
	private String url_edoc;   //전문URL
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSeq_edoc() {
		return seq_edoc;
	}
	public void setSeq_edoc(String seq_edoc) {
		this.seq_edoc = seq_edoc;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNo_edoc() {
		return no_edoc;
	}
	public void setNo_edoc(String no_edoc) {
		this.no_edoc = no_edoc;
	}
	public String getNm_edoc() {
		return nm_edoc;
	}
	public void setNm_edoc(String nm_edoc) {
		this.nm_edoc = nm_edoc;
	}
	public String getType_txrx() {
		return type_txrx;
	}
	public void setType_txrx(String type_txrx) {
		this.type_txrx = type_txrx;
	}
	public String getType_flex() {
		return type_flex;
	}
	public void setType_flex(String type_flex) {
		this.type_flex = type_flex;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getSeq_order() {
		return seq_order;
	}
	public void setSeq_order(String seq_order) {
		this.seq_order = seq_order;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
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
	public String getUrl_edoc() {
		return url_edoc;
	}
	public void setUrl_edoc(String url_edoc) {
		this.url_edoc = url_edoc;
	}
}
