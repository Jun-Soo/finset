package com.koscom.env.model;

import java.io.Serializable;

import com.koscom.util.StringUtil;

public class ApprovalManage implements Serializable{
	private static final long serialVersionUID = -7039159903023829546L;
	private String id_appr;
	private String nm_appr;
	private String nm_action;
	private String cd_work;
	private String cd_approval_group;
	private int seq_order;
	
	public ApprovalManage() {}
	
	public ApprovalManage(String nm_appr, String cd_work, String cd_program_group, String nm_action, int seq_order) {
		this.id_appr = cd_work+cd_program_group+StringUtil.leftPad(""+seq_order, 2, '0');
		this.nm_appr = nm_appr;
		this.nm_action = nm_action;
		this.cd_work = cd_work;
		this.cd_approval_group = cd_program_group;
		this.seq_order = seq_order;
	}

	public String getId_appr() {
		return id_appr;
	}

	public void setId_appr(String id_appr) {
		this.id_appr = id_appr;
	}

	public String getNm_appr() {
		return nm_appr;
	}

	public void setNm_appr(String nm_appr) {
		this.nm_appr = nm_appr;
	}

	public String getNm_action() {
		return nm_action;
	}

	public void setNm_action(String nm_action) {
		this.nm_action = nm_action;
	}

	public String getCd_work() {
		return cd_work;
	}

	public void setCd_work(String cd_work) {
		this.cd_work = cd_work;
	}

	public String getCd_approval_group() {
		return cd_approval_group;
	}

	public void setCd_approval_group(String cd_approval_group) {
		this.cd_approval_group = cd_approval_group;
	}

	public int getSeq_order() {
		return seq_order;
	}

	public void setSeq_order(int seq_order) {
		this.seq_order = seq_order;
	}

}
