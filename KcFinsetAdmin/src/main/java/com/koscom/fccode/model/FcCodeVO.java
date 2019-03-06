package com.koscom.fccode.model;

public class FcCodeVO extends FcCodeInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1783433981732516479L;
	private String yn_code_group;
	private String cd_proc_type;
	private String new_seq_order;
	private String auto_com_txt;

	public String getAuto_com_txt() {
		return auto_com_txt;
	}

	public void setAuto_com_txt(String auto_com_txt) {
		this.auto_com_txt = auto_com_txt;
	}

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
