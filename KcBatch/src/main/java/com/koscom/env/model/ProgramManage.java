package com.koscom.env.model;

import java.io.Serializable;

import com.koscom.util.StringUtil;
public class ProgramManage implements Serializable{
	private static final long serialVersionUID = 9146710127347945260L;
	private String id_program;
	private String nm_program;
	private String cd_system;
	private String cd_work;
	private String cd_program_group;
	private String nm_action;
	private int seq_order;
	
	public ProgramManage() { }
	
	public ProgramManage(String nm_program,String cd_system,String cd_work,String cd_program_group,String nm_action,int seq_order) {
		this.id_program = cd_system+cd_work+cd_program_group+StringUtil.leftPad(""+seq_order, 2, '0');
		this.nm_program = nm_program;
		this.cd_system = cd_system;
		this.cd_work = cd_work;
		this.cd_program_group = cd_program_group;
		this.nm_action = nm_action;
		this.seq_order = seq_order;
	}
	
	public String getId_program() {
		return id_program;
	}

	public void setId_program(String id_program) {
		this.id_program = id_program;
	}

	public String getNm_program() {
		return nm_program;
	}

	public void setNm_program(String nm_program) {
		this.nm_program = nm_program;
	}

	public String getCd_system() {
		return cd_system;
	}

	public void setCd_system(String cd_system) {
		this.cd_system = cd_system;
	}

	public String getCd_work() {
		return cd_work;
	}

	public void setCd_work(String cd_work) {
		this.cd_work = cd_work;
	}

	public String getCd_program_group() {
		return cd_program_group;
	}

	public void setCd_program_group(String cd_program_group) {
		this.cd_program_group = cd_program_group;
	}

	public String getNm_action() {
		return nm_action;
	}

	public void setNm_action(String nm_action) {
		this.nm_action = nm_action;
	}

	public int getSeq_order() {
		return seq_order;
	}

	public void setSeq_order(int seq_order) {
		this.seq_order = seq_order;
	}

}
