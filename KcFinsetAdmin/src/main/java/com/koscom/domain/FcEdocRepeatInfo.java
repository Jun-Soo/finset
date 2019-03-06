package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FcEdocRepeatInfo implements Serializable{
	private static final long serialVersionUID = 280930568069359849L;
	private String cd_fc = "";		//금융사코드
	private String no_edoc = "";		//전문번호
	private String type_txrx = "";	//전문송수신여부
	private String parent_code_group = "";	//부모항목(카운트항목)
	private String real_code_group = "";		//반복항목
	private String id_frt = "";	//최초입력아이디
	private String dt_frt = "";	//최종입력날짜
	private String id_lst = "";	//최종입력아이디
	private String dt_lst = "";	//최종입력날짜
	private String seq_order = "";
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
	public String getType_txrx() {
		return type_txrx;
	}
	public void setType_txrx(String type_txrx) {
		this.type_txrx = type_txrx;
	}
	public String getParent_code_group() {
		return parent_code_group;
	}
	public void setParent_code_group(String parent_code_group) {
		this.parent_code_group = parent_code_group;
	}
	public String getReal_code_group() {
		return real_code_group;
	}
	public void setReal_code_group(String real_code_group) {
		this.real_code_group = real_code_group;
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
	public String getSeq_order() {
		return seq_order;
	}
	public void setSeq_order(String seq_order) {
		this.seq_order = seq_order;
	}
	
}
