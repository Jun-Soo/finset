package com.koscom.worker.model;

import java.io.Serializable;

import com.koscom.domain.WorkerInfo;
import com.koscom.util.StringUtil;

public class WorkerVO extends WorkerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7797989826707882618L;

	private String pass_old; // 현재비밀번호
	private String pass_new; // 새 비밀번호
	private String pass_re_new; // 새 비밀번호 확인
	
	public void setExt_emp_direct_tmp(String[] ext_emp_direct_tmp) {
		super.ext_emp_direct = StringUtil.addChar(ext_emp_direct_tmp, "-");
	}
	
	public String getExt_direct_idx1() {
		return StringUtil.splitStr(super.ext_emp_direct, "-", 1);
	}

	public String getExt_direct_idx2() {
		return StringUtil.splitStr(super.ext_emp_direct, "-", 2);
	}

	public String getExt_direct_idx3() {
		return StringUtil.splitStr(super.ext_emp_direct, "-", 3);
	}

	public String getHp_idx1() {
		return StringUtil.splitStr(super.hp, "-", 1);
	}

	public String getHp_idx2() {
		return StringUtil.splitStr(super.hp, "-", 2);
	}

	public String getHp_idx3() {
		return StringUtil.splitStr(super.hp, "-", 3);
	}
	
	public void setHp_tmp(String[] hp_tmp) {
		super.hp = StringUtil.addChar(hp_tmp, "-");
	}
	
	public String getPass_old() {
		return pass_old;
	}

	public void setPass_old(String pass_old) {
		this.pass_old = pass_old;
	}

	public String getPass_new() {
		return pass_new;
	}

	public void setPass_new(String pass_new) {
		this.pass_new = pass_new;
	}

	public String getPass_re_new() {
		return pass_re_new;
	}

	public void setPass_re_new(String pass_re_new) {
		this.pass_re_new = pass_re_new;
	}
	
}
