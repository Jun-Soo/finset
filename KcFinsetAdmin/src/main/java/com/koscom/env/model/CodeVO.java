package com.koscom.env.model;

public class CodeVO extends CodeInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7432280198203235147L;
	private String yn_code_group;
	private String cd_proc_type;
	private String new_seq_order;

	public String getYn_code_group() {
		return yn_code_group;
	}

	public void setYn_code_group(String yn_code_group) {
		this.yn_code_group = yn_code_group;
	}

	public String getCd_proc_type() {
		return cd_proc_type;
	}

	public void setCd_proc_type(String cd_proc_type) {
		this.cd_proc_type = cd_proc_type;
	}

	public String getNew_seq_order() {
		return new_seq_order;
	}

	public void setNew_seq_order(String new_seq_order) {
		this.new_seq_order = new_seq_order;
	}
	
}
