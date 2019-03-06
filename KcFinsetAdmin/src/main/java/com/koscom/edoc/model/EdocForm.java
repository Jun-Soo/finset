package com.koscom.edoc.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class EdocForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = 3689611148324286603L;
	private String seq_edoc; // 금융사코드
	private String cd_fc; // 금융사코드
	private String no_edoc; // 전문번호
	private String nm_edoc; // 전문명
	private String yn_code_group;
	private String id_frt;
	private String type_txrx;
	
	public String getType_txrx(){
		return type_txrx;
	}
	public void setType_txrx(String type_txrx){
		this.type_txrx = type_txrx;
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
	public String getYn_code_group() {
		return yn_code_group;
	}
	public void setYn_code_group(String yn_code_group) {
		this.yn_code_group = yn_code_group;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	@Override
	public String toString() {
		return "EdocForm{" +
				"seq_edoc='" + seq_edoc + '\'' +
				", cd_fc='" + cd_fc + '\'' +
				", no_edoc='" + no_edoc + '\'' +
				", nm_edoc='" + nm_edoc + '\'' +
				", yn_code_group='" + yn_code_group + '\'' +
				", id_frt='" + id_frt + '\'' +
				", type_txrx='" + type_txrx + '\'' +
				", ymd_basic='" + ymd_basic + '\'' +
				'}';
	}
}
