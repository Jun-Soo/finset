package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * NPAC 이력 정보 모델입니다.
 * @author ywlee
 *
 */
public class NpacHist implements Serializable{

	private static final long serialVersionUID = -3351413166395267894L;
	
	protected String seq;
	protected String no_apply;
	protected String no_person;
	protected String nm_person;
	protected String ssn_person;
	protected String bgn;
	protected String hp;
	protected String nice_safekey;
	protected String cd_npac_response;
	protected String unique_key;
	protected String agree1_map;
	protected String agree2_map;
	protected String agree3_map;
	protected String agree4_map;
	protected String ci;
	protected String auth_hp;
	protected String auth_type;
	protected String no_cert;
	
	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getSsn_person() {
		return ssn_person;
	}

	public void setSsn_person(String ssn_person) {
		this.ssn_person = ssn_person;
	}

	public String getBgn() {
		return bgn;
	}

	public void setBgn(String bgn) {
		this.bgn = bgn;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getNice_safekey() {
		return nice_safekey;
	}

	public void setNice_safekey(String nice_safekey) {
		this.nice_safekey = nice_safekey;
	}

	public String getCd_npac_response() {
		return cd_npac_response;
	}

	public void setCd_npac_response(String cd_npac_response) {
		this.cd_npac_response = cd_npac_response;
	}

	public String getUnique_key() {
		return unique_key;
	}

	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}

	public String getAgree1_map() {
		return agree1_map;
	}

	public void setAgree1_map(String agree1_map) {
		this.agree1_map = agree1_map;
	}

	public String getAgree2_map() {
		return agree2_map;
	}

	public void setAgree2_map(String agree2_map) {
		this.agree2_map = agree2_map;
	}

	public String getAgree3_map() {
		return agree3_map;
	}

	public void setAgree3_map(String agree3_map) {
		this.agree3_map = agree3_map;
	}

	public String getAgree4_map() {
		return agree4_map;
	}

	public void setAgree4_map(String agree4_map) {
		this.agree4_map = agree4_map;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getAuth_hp() {
		return auth_hp;
	}

	public void setAuth_hp(String auth_hp) {
		this.auth_hp = auth_hp;
	}

	public String getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
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

	public String getNo_cert() {
		return no_cert;
	}

	public void setNo_cert(String no_cert) {
		this.no_cert = no_cert;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	
}
