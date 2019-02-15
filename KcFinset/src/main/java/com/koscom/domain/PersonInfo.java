package com.koscom.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PersonInfo implements Serializable {

	private static final long serialVersionUID = -1538460879578027218L;
	private MultipartFile file1;

	protected String no_person;		
	protected String nm_person;		
	protected String ssn_person;
	protected String pass_person;		
	protected String bgn;				
	protected String hp;				
	protected String cd_tel;			
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
	protected Long seq_login;	
	protected String path_file1;			
	protected String nm_file1;			
	protected String stock_token;			
	protected String etc_memo;			
	protected String amt_etm_income;		
	protected String cnt_fail_pwd;		
	protected String cnt_fail_finger;		

	
	/*
	protected 	String	telComCd;				//통신사
	protected 	String	nm_hp_owner;			//휴대폰명의
	protected 	String	rel_hp_owner;			//휴대폰명의관계
	protected 	String	cd_hp_co;				//통신회사
	protected 	String	cd_status_hp;			//휴대폰 상태
	protected 	String	cd_marry;				//결혼구분
	protected 	String	yn_recv_email;			//이메일수신여부
	protected 	String	c3_bank;				//고객은행
	protected 	String	account_bank;			//은행계좌
	protected 	String	yn_secret;				//비밀여부
	protected 	String	yn_vehicle_own;			//차량소유여부
	protected 	String	etc_vehicles;			//차량정보
	protected 	String	cd_army;				//병역구분
	protected 	String	nm_specialty;			//특기
	protected 	String	cd30_hobby;				//취미
	protected 	String	ph_home;				//자택전화
	protected 	String	cd_ph_co_home;			//자택통신회사
	protected 	String	cd_status_ph_home;		//자택전화상태
	protected 	String	nm_ph_owner;			//자택전화명의
	protected 	String	rel_ph_owner;			//자택전화명의관계
	protected 	String	cd_addr_home;			//자택주소구분
	protected 	String	post_home;				//자택우편번호
	protected 	String	addr1_home;				//자택주소1
	protected 	String	addr2_home;				//자택주소2
	protected 	String	cd_status_addr_home;	//자택주소상태
	protected 	String	cd_house_type_home;		//자택주거종류
	protected 	String	ym_house_home;			//자택주거시작년월
	protected 	String	cd_live_type_home;		//자택주거형태
	protected 	String	amt_rent_deposit_home;	//자택전세금
	protected 	String	amt_rent_monthly_home;	//자택월세금
	protected 	String	nm_owner_home;			//자택명의
	protected 	String	rel_owner_home;			//자택명의관계
	protected 	String	yn_addr_equal;			//주소동일여부
	protected 	String	cd_addr_reg;			//등본주소구분
	protected 	String	post_reg;				//등본우편번호
	protected 	String	addr1_reg;				//등본주소1
	protected 	String	addr2_reg;				//등본주소2
	protected 	String	cd_status_addr_reg;		//등본주소상태
	protected 	String	cd_house_type_reg;		//등본주거종류
	protected 	String	ym_house_reg;			//등본주소시작년월
	protected 	String	cd_live_type_reg;		//등본주거형태
	protected 	String	amt_rent_deposit_reg;	//등본전세금
	protected 	String	amt_rent_monthly_reg;	//등본월세금
	protected 	String	nm_owner_reg;			//등본명의
	protected 	String	rel_owner_reg;			//등본명의관계
	protected 	String	cd50_live_together;		//동거인정보
	protected 	String	cd_job_class;			//직업구분
	protected 	String	nm_comp;				//회사명
	protected 	String	cd_job_term;			//입사기간구분
	protected 	String	cd_duty_comp;			//직위구분
	protected 	String	cd_job_comp;			//직무구분
	protected 	String	cd_industry;			//업종구분
	protected 	String	nm_industry_detail;		//업종상세
	protected 	String	cd_addr_comp;			//회사주소구분
	protected 	String	post_comp;				//회사우편번호
	protected 	String	addr1_comp;				//회사주소1
	protected 	String	addr2_comp;				//회사주소2
	protected 	String	cd_status_addr_comp;	//회사주소상태
	protected 	String	ph_comp;				//회사전화
	protected 	String	cd_status_ph_comp;		//회사전화상태
	protected 	String	ph_comp_direct;			//회사직통전화
	protected 	String	fax_comp;				//회사팩스
	protected 	String	nm_duty_comp;			//직책명
	protected 	String	nm_part_comp;			//부서명
	protected 	String	ym_start_comp;			//입사년월
	protected 	String	yn_medi_insu_comp;		//직장의료보험유무
	protected 	String	cd_scale_comp;			//회사규모
	protected 	String	cnt_worker;				//직원수
	protected 	String	no_biz_licence;			//사업자등록번호
	protected 	String	cd_employ_type;			//고용형태
	protected 	String	cd_dd_income;			//급여일
	protected 	String	amt_monthly_income;		//월수입
	protected 	String	amt_year_income;		//연수입
	protected 	String	etc_job;				//직업정보
	protected 	String	nm_univ;				//학교명
	protected 	String	cd_attend_univ;			//재학상태
	protected 	String	cd_grade_univ;			//학년
	protected 	String	no_univ;				//학번
	protected 	String	yyyy_enter_univ;		//입학년도
	protected 	String	nm_part_univ;			//학과명
	protected 	String	cd_addr_univ;			//학교주소구분
	protected 	String	ph_univ;				//학교전화
	protected 	String	cd_status_ph_univ;		//학교전화상태
	protected 	String	post_univ;				//학교우편번호
	protected 	String	addr1_univ;				//학교주소1
	protected 	String	addr2_univ;				//학교주소2
	protected 	String	cd_status_addr_univ;	//학교주소상태
	protected 	String	cd_addr_etc;			//기타주소구분
	protected 	String	post_etc;				//기타주소우편번호
	protected 	String	addr1_etc;				//기타주소1
	protected 	String	addr2_etc;				//기타주소2
	protected 	String	hp_etc;					//기타핸드폰
	protected 	String	cd_hp_co_etc;			//기타핸드폰통신회사
	protected 	String	cd_status_hp_etc;		//기타핸드폰상태
	protected 	String	cd50_agree_item;		//필수,선택적 동의
	protected 	String	nice_safekey;			//나이스본인인증키

	protected 	String	proof_income;			//소득증빙자료
	protected 	String	yn_4insu; 				//4대보험
	protected 	String	amt_min_living_cost;	//최저생계비
	protected 	String	cnt_live_together; 		//동거가족수
	protected 	String	cd_cnt_child;			//자녀수
	protected 	String	comp_social;			//소셜업체
	protected 	String	id_social;				//소셜ID
	protected 	String	token_social;			//토큰
	protected 	String	dt_agree_social;		//동의날짜
	protected 	String	nm_social;				//소셜이름
	protected 	String	cnt_fail_mode;			//누적카운트 mode
	protected	int		cnt_fail; 				//비밀번호/지문 틀린횟수 insert parameter
	protected 	String	seq; 					//push_setting seq값
	protected	String	type_push; 				//push_setting type
	protected	String	item_push;				//푸쉬 항목명
	protected	String	stat_push; 				//개별 push yn여부
	*/
	protected 	String  id_frt;
	protected 	String	dt_frt;
	protected 	String  id_lst;
	protected 	String	dt_lst;
	
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
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
	public String getCd_tel() {
		return cd_tel;
	}
	public void setCd_tel(String cd_tel) {
		this.cd_tel = cd_tel;
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
	public Long getSeq_login() {
		return seq_login;
	}
	public void setSeq_login(Long seq_login) {
		this.seq_login = seq_login;
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
