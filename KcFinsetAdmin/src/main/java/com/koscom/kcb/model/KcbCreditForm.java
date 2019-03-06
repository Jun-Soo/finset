package com.koscom.kcb.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.koscom.credit.model.CreditForm;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class KcbCreditForm extends CreditForm{
	private static final long serialVersionUID = 8974464307738743227L;
	private String no_apply; //신청서번호
	private String amt_apply; //대출신청금액
	private String ymd_apply; //신청일자
	private String cd_branch; //모집인등록번호
	private String ssn_person; //주민번호
	private String nm_person; //성명
	private String cd_tlc_hp; //통신사코드
	private String hp; //휴대폰번호
	private String post_home; //자택우편번호
	private String addr1_home; //자택주소1
	private String addr2_home; //자택주소2
	private String ph_home; //자택전화번호
	private String no_biz_comp; //사업자번호
	private String nm_comp; //회사명
	private String post_comp; //회사우편번호
	private String addr1_comp; //회사주소1
	private String addr2_comp; //회사주소2
	private String ph_comp; //직장전화번호
	private String amt_year_income; //연소득
	private String ssn_grt; //보증인_주민번호
	private String nm_grt; //보증인_성명
	private String hp_grt; //보증인_휴대폰번호
	private String nm_comp_grt; //보증인_회사명
	private String post_comp_grt; //보증인_회사우편번호
	private String addr1_comp_grt; //보증인_회사주소1
	private String addr2_comp_grt; //보증인_회사주소2
	private String ph_comp_grt; //보증인_회사전화번호
	
	private String yn_auth_cb;
	private String yn_use_inq_key;
	private String nm_cust;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getAmt_apply() {
		return amt_apply;
	}

	public void setAmt_apply(String amt_apply) {
		this.amt_apply = amt_apply;
	}

	public String getYmd_apply() {
		return ymd_apply;
	}

	public void setYmd_apply(String ymd_apply) {
		this.ymd_apply = ymd_apply;
	}

	public String getCd_branch() {
		return cd_branch;
	}

	public void setCd_branch(String cd_branch) {
		this.cd_branch = cd_branch;
	}

	public String getSsn_person() {
		return ssn_person;
	}

	public void setSsn_person(String ssn_person) {
		this.ssn_person = ssn_person;
	}

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getCd_tlc_hp() {
		return cd_tlc_hp;
	}

	public void setCd_tlc_hp(String cd_tlc_hp) {
		this.cd_tlc_hp = cd_tlc_hp;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getPost_home() {
		return post_home;
	}

	public void setPost_home(String post_home) {
		this.post_home = post_home;
	}

	public String getAddr1_home() {
		return addr1_home;
	}

	public void setAddr1_home(String addr1_home) {
		this.addr1_home = addr1_home;
	}

	public String getAddr2_home() {
		return addr2_home;
	}

	public void setAddr2_home(String addr2_home) {
		this.addr2_home = addr2_home;
	}

	public String getPh_home() {
		return ph_home;
	}

	public void setPh_home(String ph_home) {
		this.ph_home = ph_home;
	}

	public String getNo_biz_comp() {
		return no_biz_comp;
	}

	public void setNo_biz_comp(String no_biz_comp) {
		this.no_biz_comp = no_biz_comp;
	}

	public String getNm_comp() {
		return nm_comp;
	}

	public void setNm_comp(String nm_comp) {
		this.nm_comp = nm_comp;
	}

	public String getPost_comp() {
		return post_comp;
	}

	public void setPost_comp(String post_comp) {
		this.post_comp = post_comp;
	}

	public String getAddr1_comp() {
		return addr1_comp;
	}

	public void setAddr1_comp(String addr1_comp) {
		this.addr1_comp = addr1_comp;
	}

	public String getAddr2_comp() {
		return addr2_comp;
	}

	public void setAddr2_comp(String addr2_comp) {
		this.addr2_comp = addr2_comp;
	}

	public String getPh_comp() {
		return ph_comp;
	}

	public void setPh_comp(String ph_comp) {
		this.ph_comp = ph_comp;
	}

	public String getAmt_year_income() {
		return amt_year_income;
	}

	public void setAmt_year_income(String amt_year_income) {
		this.amt_year_income = amt_year_income;
	}

	public String getSsn_grt() {
		return ssn_grt;
	}

	public void setSsn_grt(String ssn_grt) {
		this.ssn_grt = ssn_grt;
	}

	public String getNm_grt() {
		return nm_grt;
	}

	public void setNm_grt(String nm_grt) {
		this.nm_grt = nm_grt;
	}

	public String getHp_grt() {
		return hp_grt;
	}

	public void setHp_grt(String hp_grt) {
		this.hp_grt = hp_grt;
	}

	public String getNm_comp_grt() {
		return nm_comp_grt;
	}

	public void setNm_comp_grt(String nm_comp_grt) {
		this.nm_comp_grt = nm_comp_grt;
	}

	public String getPost_comp_grt() {
		return post_comp_grt;
	}

	public void setPost_comp_grt(String post_comp_grt) {
		this.post_comp_grt = post_comp_grt;
	}

	public String getAddr1_comp_grt() {
		return addr1_comp_grt;
	}

	public void setAddr1_comp_grt(String addr1_comp_grt) {
		this.addr1_comp_grt = addr1_comp_grt;
	}

	public String getAddr2_comp_grt() {
		return addr2_comp_grt;
	}

	public void setAddr2_comp_grt(String addr2_comp_grt) {
		this.addr2_comp_grt = addr2_comp_grt;
	}

	public String getPh_comp_grt() {
		return ph_comp_grt;
	}

	public void setPh_comp_grt(String ph_comp_grt) {
		this.ph_comp_grt = ph_comp_grt;
	}

	public String getYn_auth_cb() {
		return yn_auth_cb;
	}

	public void setYn_auth_cb(String yn_auth_cb) {
		this.yn_auth_cb = yn_auth_cb;
	}

	public String getYn_use_inq_key() {
		return yn_use_inq_key;
	}

	public void setYn_use_inq_key(String yn_use_inq_key) {
		this.yn_use_inq_key = yn_use_inq_key;
	}

	public String getNm_cust() {
		return nm_cust;
	}

	public void setNm_cust(String nm_cust) {
		this.nm_cust = nm_cust;
	}
	
	
}
