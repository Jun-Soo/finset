package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TblRespHealthQlfctndtl implements Serializable{

	
	/**
	 * 자격확인서상세
	 */
	private static final long serialVersionUID = 5253461465427894812L;

	protected int    order_qualification_confirm ; //순서_자격확인
	protected String qlfctn_cnfrm_qlfctn_gn_d ; //자격확인자격취득일
	protected String qlfctn_cnfrm_qlfctn_ls_d ; //자격확인자격상실일
	protected String qlfctn_cnfrm_slr_stp_cs_trm ; //자격확인급여정지사유기간
	protected String register_dt ; //등록일시
	protected int    seq_health_insu ; //일련번호_건강보험
	
	public int getOrder_qualification_confirm() {
		return order_qualification_confirm;
	}

	public void setOrder_qualification_confirm(int order_qualification_confirm) {
		this.order_qualification_confirm = order_qualification_confirm;
	}

	public String getQlfctn_cnfrm_qlfctn_gn_d() {
		return qlfctn_cnfrm_qlfctn_gn_d;
	}

	public void setQlfctn_cnfrm_qlfctn_gn_d(String qlfctn_cnfrm_qlfctn_gn_d) {
		this.qlfctn_cnfrm_qlfctn_gn_d = qlfctn_cnfrm_qlfctn_gn_d;
	}

	public String getQlfctn_cnfrm_qlfctn_ls_d() {
		return qlfctn_cnfrm_qlfctn_ls_d;
	}

	public void setQlfctn_cnfrm_qlfctn_ls_d(String qlfctn_cnfrm_qlfctn_ls_d) {
		this.qlfctn_cnfrm_qlfctn_ls_d = qlfctn_cnfrm_qlfctn_ls_d;
	}

	public String getQlfctn_cnfrm_slr_stp_cs_trm() {
		return qlfctn_cnfrm_slr_stp_cs_trm;
	}

	public void setQlfctn_cnfrm_slr_stp_cs_trm(String qlfctn_cnfrm_slr_stp_cs_trm) {
		this.qlfctn_cnfrm_slr_stp_cs_trm = qlfctn_cnfrm_slr_stp_cs_trm;
	}

	public String getRegister_dt() {
		return register_dt;
	}

	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}

	public int getSeq_health_insu() {
		return seq_health_insu;
	}

	public void setSeq_health_insu(int seq_health_insu) {
		this.seq_health_insu = seq_health_insu;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
