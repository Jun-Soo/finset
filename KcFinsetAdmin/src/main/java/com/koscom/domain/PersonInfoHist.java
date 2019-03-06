package com.koscom.domain;

import java.io.Serializable;

public class PersonInfoHist implements Serializable{

	private static final long serialVersionUID = 848435446352076600L;
	
	/*
	protected String no_person;		//고객번호
	protected String cd_info;		//변경정보 코드
	protected String before;		//변경전 정보
	protected String after;			//변경후 정보
	protected String id_frt;		//변경자
	protected String dt_frt;		//변경일시
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_info() {
		return cd_info;
	}
	public void setCd_info(String cd_info) {
		this.cd_info = cd_info;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
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
	*/
	
	protected String no_person;
	protected String seq;
	protected String nm_person;
	protected String ssn_person;
	protected String pass_person;
	protected String bgn;
	protected String hp;
	protected String kcb_ci;
	protected String kcb_di;
	protected String kcb_cp;
	protected String pbl_cert_ci;
	protected String pbl_cert_di;
	protected String yn_use;
	protected String yn_agree_using;
	protected String dt_agree_using;
	protected String dt_install;
	protected String yn_fingerprint;
	protected String yn_os;
	protected String fcm_token;
	protected String cd_push;
	protected String yn_push;
	protected String email;
	protected String nick;
	protected String yn_logout;
	protected String seq_new_debt_reg;
	protected String path_file1;
	protected String nm_file1;
	protected String stock_token;
	protected String etc_memo;
	protected String amt_etm_income;
	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;
	
	protected String nice_safekey;		/* 확인 필요 나이스 세이프티키*/
	protected String cnt_fail_pwd;		/* 비밀번호 실패건수 */
	protected String cnt_fail_finger;	/* 지문인증 실패건수 */
	
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getPass_person() {
		return pass_person;
	}
	public void setPass_person(String pass_person) {
		this.pass_person = pass_person;
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
	public String getKcb_ci() {
		return kcb_ci;
	}
	public void setKcb_ci(String kcb_ci) {
		this.kcb_ci = kcb_ci;
	}
	public String getKcb_di() {
		return kcb_di;
	}
	public void setKcb_di(String kcb_di) {
		this.kcb_di = kcb_di;
	}
	public String getKcb_cp() {
		return kcb_cp;
	}
	public void setKcb_cp(String kcb_cp) {
		this.kcb_cp = kcb_cp;
	}
	public String getPbl_cert_ci() {
		return pbl_cert_ci;
	}
	public void setPbl_cert_ci(String pbl_cert_ci) {
		this.pbl_cert_ci = pbl_cert_ci;
	}
	public String getPbl_cert_di() {
		return pbl_cert_di;
	}
	public void setPbl_cert_di(String pbl_cert_di) {
		this.pbl_cert_di = pbl_cert_di;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getYn_agree_using() {
		return yn_agree_using;
	}
	public void setYn_agree_using(String yn_agree_using) {
		this.yn_agree_using = yn_agree_using;
	}
	public String getDt_agree_using() {
		return dt_agree_using;
	}
	public void setDt_agree_using(String dt_agree_using) {
		this.dt_agree_using = dt_agree_using;
	}
	public String getDt_install() {
		return dt_install;
	}
	public void setDt_install(String dt_install) {
		this.dt_install = dt_install;
	}
	public String getYn_fingerprint() {
		return yn_fingerprint;
	}
	public void setYn_fingerprint(String yn_fingerprint) {
		this.yn_fingerprint = yn_fingerprint;
	}
	public String getYn_os() {
		return yn_os;
	}
	public void setYn_os(String yn_os) {
		this.yn_os = yn_os;
	}
	public String getFcm_token() {
		return fcm_token;
	}
	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}
	public String getCd_push() {
		return cd_push;
	}
	public void setCd_push(String cd_push) {
		this.cd_push = cd_push;
	}
	public String getYn_push() {
		return yn_push;
	}
	public void setYn_push(String yn_push) {
		this.yn_push = yn_push;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getYn_logout() {
		return yn_logout;
	}
	public void setYn_logout(String yn_logout) {
		this.yn_logout = yn_logout;
	}
	public String getSeq_new_debt_reg() {
		return seq_new_debt_reg;
	}
	public void setSeq_new_debt_reg(String seq_new_debt_reg) {
		this.seq_new_debt_reg = seq_new_debt_reg;
	}
	public String getPath_file1() {
		return path_file1;
	}
	public void setPath_file1(String path_file1) {
		this.path_file1 = path_file1;
	}
	public String getNm_file1() {
		return nm_file1;
	}
	public void setNm_file1(String nm_file1) {
		this.nm_file1 = nm_file1;
	}
	public String getStock_token() {
		return stock_token;
	}
	public void setStock_token(String stock_token) {
		this.stock_token = stock_token;
	}
	public String getEtc_memo() {
		return etc_memo;
	}
	public void setEtc_memo(String etc_memo) {
		this.etc_memo = etc_memo;
	}
	public String getAmt_etm_income() {
		return amt_etm_income;
	}
	public void setAmt_etm_income(String amt_etm_income) {
		this.amt_etm_income = amt_etm_income;
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
	public String getCnt_fail_pwd() {
		return cnt_fail_pwd;
	}
	public void setCnt_fail_pwd(String cnt_fail_pwd) {
		this.cnt_fail_pwd = cnt_fail_pwd;
	}
	public String getCnt_fail_finger() {
		return cnt_fail_finger;
	}
	public void setCnt_fail_finger(String cnt_fail_finger) {
		this.cnt_fail_finger = cnt_fail_finger;
	}
	
}
