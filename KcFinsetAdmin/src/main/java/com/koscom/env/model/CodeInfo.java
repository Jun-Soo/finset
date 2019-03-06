package com.koscom.env.model;

import java.io.Serializable;
import java.sql.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * 공통 코드 모델입니다.
 * @author dhkim
 *
 */
public class CodeInfo implements Serializable{
	private static final long serialVersionUID = 2174073307970204217L;
	private int seq;
	private String codeGroup;
	private String codeValue;
	private String nmCode;
	private String ynUse;
	private int seqOrder;
	private String idFrt;
	private Date dtFrt;
	private String idLst;
	private Date dtLst;

	private String code_group; // 코드종류
	private String code_value; // 코드값
	private String nm_code;    // 코드명
	private String etc;        // 비고
	private String yn_use;     // 사용유무
	private String seq_order;  // 우선순위
	private String yn_system_code; // 시스템 코드 구분
	private String id_frt;     // 최초작성자
	private String dt_frt;
	private String id_lst;     // 최종작성자
	private String dt_lst;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCodeGroup() {
		return codeGroup;
	}
	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getNmCode() {
		return nmCode;
	}
	public void setNmCode(String nmCode) {
		this.nmCode = nmCode;
	}
	public String getYnUse() {
		return ynUse;
	}
	public void setYnUse(String ynUse) {
		this.ynUse = ynUse;
	}
	public int getSeqOrder() {
		return seqOrder;
	}
	public void setSeqOrder(int seqOrder) {
		this.seqOrder = seqOrder;
	}
	public String getIdFrt() {
		return idFrt;
	}
	public void setIdFrt(String idFrt) {
		this.idFrt = idFrt;
	}
	public Date getDtFrt() {
		return dtFrt;
	}
	public void setDtFrt(Date dtFrt) {
		this.dtFrt = dtFrt;
	}
	public String getIdLst() {
		return idLst;
	}
	public void setIdLst(String idLst) {
		this.idLst = idLst;
	}
	public Date getDtLst() {
		return dtLst;
	}
	public void setDtLst(Date dtLst) {
		this.dtLst = dtLst;
	}
	public String getCode_group() {
		return code_group;
	}
	public void setCode_group(String code_group) {
		this.code_group = code_group;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getNm_code() {
		return nm_code;
	}
	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getSeq_order() {
		return seq_order;
	}
	public void setSeq_order(String seq_order) {
		this.seq_order = seq_order;
	}
	public String getYn_system_code() {
		return yn_system_code;
	}
	public void setYn_system_code(String yn_system_code) {
		this.yn_system_code = yn_system_code;
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