package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public class PersonInfo implements Serializable {

	private static final long serialVersionUID = -1538460879578027218L;
	@JsonIgnore
	private MultipartFile file1;

	protected String path_file1; 			//로그파일 경로
	protected String nm_file1; 				//로그파일 명

	protected 	String	nick;				//nick
	protected 	String	yn_logout;			//yn_logout
	protected 	String	yn_agree_using;		//이용약관동의
	protected 	String	dt_agree_using;		//이용약관동의날짜
	protected 	String 	yn_use;
	protected 	String	no_person;				//개인번호
	protected 	String	nm_person;				//이름
	protected 	String	ssn_person;				//주민번호
	protected 	String	pass_person;			//비밀번호
	protected 	String	bgn;					//생년월일+성별
	protected 	String	telComCd;				//통신사
	protected 	String	hp;						//휴대폰
	protected 	String	nm_hp_owner;			//휴대폰명의
	protected 	String	rel_hp_owner;			//휴대폰명의관계
	protected 	String	cd_hp_co;				//통신회사
	protected 	String	cd_status_hp;			//휴대폰 상태
	protected 	String	cd_marry;				//결혼구분
	protected 	String	email;					//이메일
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
	protected 	String	kcb_ci;					//kcb본인인증키
	protected 	String	kcb_cp;					//kcb본인인증키cp
	protected 	String	kcb_di;					//kcb본인인증키
	protected 	String	etc_memo;				//메모
	protected 	String	cd_tel ;				//통신사구분

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
	protected 	String 	cnt_fail_pwd; 			//비밀번호 틀린횟수
	protected 	String 	cnt_fail_finger; 		//지문 틀린횟수
	protected 	String	yn_fingerprint;			//지문여부
	protected   String  yn_os;
	protected 	String	seq; 					//push_setting seq값
	protected	String	type_push; 				//push_setting type
	protected   String  yn_push; 				//전체push설정여부
	protected   String  cd_push; 				//push 코드값
	protected	String	item_push;				//푸쉬 항목명
	protected	String	stat_push; 				//개별 push yn여부
	protected   String  fcm_token;
	protected 	String	stock_token;			//증권api용 토큰
	
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
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
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
	public String getTelComCd() {
		return telComCd;
	}
	public void setTelComCd(String telComCd) {
		this.telComCd = telComCd;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getNm_hp_owner() {
		return nm_hp_owner;
	}
	public void setNm_hp_owner(String nm_hp_owner) {
		this.nm_hp_owner = nm_hp_owner;
	}
	public String getRel_hp_owner() {
		return rel_hp_owner;
	}
	public void setRel_hp_owner(String rel_hp_owner) {
		this.rel_hp_owner = rel_hp_owner;
	}
	public String getCd_hp_co() {
		return cd_hp_co;
	}
	public void setCd_hp_co(String cd_hp_co) {
		this.cd_hp_co = cd_hp_co;
	}
	public String getCd_status_hp() {
		return cd_status_hp;
	}
	public void setCd_status_hp(String cd_status_hp) {
		this.cd_status_hp = cd_status_hp;
	}
	public String getCd_marry() {
		return cd_marry;
	}
	public void setCd_marry(String cd_marry) {
		this.cd_marry = cd_marry;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getYn_recv_email() {
		return yn_recv_email;
	}
	public void setYn_recv_email(String yn_recv_email) {
		this.yn_recv_email = yn_recv_email;
	}
	public String getC3_bank() {
		return c3_bank;
	}
	public void setC3_bank(String c3_bank) {
		this.c3_bank = c3_bank;
	}
	public String getAccount_bank() {
		return account_bank;
	}
	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}
	public String getYn_secret() {
		return yn_secret;
	}
	public void setYn_secret(String yn_secret) {
		this.yn_secret = yn_secret;
	}
	public String getYn_vehicle_own() {
		return yn_vehicle_own;
	}
	public void setYn_vehicle_own(String yn_vehicle_own) {
		this.yn_vehicle_own = yn_vehicle_own;
	}
	public String getEtc_vehicles() {
		return etc_vehicles;
	}
	public void setEtc_vehicles(String etc_vehicles) {
		this.etc_vehicles = etc_vehicles;
	}
	public String getCd_army() {
		return cd_army;
	}
	public void setCd_army(String cd_army) {
		this.cd_army = cd_army;
	}
	public String getNm_specialty() {
		return nm_specialty;
	}
	public void setNm_specialty(String nm_specialty) {
		this.nm_specialty = nm_specialty;
	}
	public String getCd30_hobby() {
		return cd30_hobby;
	}
	public void setCd30_hobby(String cd30_hobby) {
		this.cd30_hobby = cd30_hobby;
	}
	public String getPh_home() {
		return ph_home;
	}
	public void setPh_home(String ph_home) {
		this.ph_home = ph_home;
	}
	public String getCd_ph_co_home() {
		return cd_ph_co_home;
	}
	public void setCd_ph_co_home(String cd_ph_co_home) {
		this.cd_ph_co_home = cd_ph_co_home;
	}
	public String getCd_status_ph_home() {
		return cd_status_ph_home;
	}
	public void setCd_status_ph_home(String cd_status_ph_home) {
		this.cd_status_ph_home = cd_status_ph_home;
	}
	public String getNm_ph_owner() {
		return nm_ph_owner;
	}
	public void setNm_ph_owner(String nm_ph_owner) {
		this.nm_ph_owner = nm_ph_owner;
	}
	public String getRel_ph_owner() {
		return rel_ph_owner;
	}
	public void setRel_ph_owner(String rel_ph_owner) {
		this.rel_ph_owner = rel_ph_owner;
	}
	public String getCd_addr_home() {
		return cd_addr_home;
	}
	public void setCd_addr_home(String cd_addr_home) {
		this.cd_addr_home = cd_addr_home;
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
	public String getCd_status_addr_home() {
		return cd_status_addr_home;
	}
	public void setCd_status_addr_home(String cd_status_addr_home) {
		this.cd_status_addr_home = cd_status_addr_home;
	}
	public String getCd_house_type_home() {
		return cd_house_type_home;
	}
	public void setCd_house_type_home(String cd_house_type_home) {
		this.cd_house_type_home = cd_house_type_home;
	}
	public String getYm_house_home() {
		return ym_house_home;
	}
	public void setYm_house_home(String ym_house_home) {
		this.ym_house_home = ym_house_home;
	}
	public String getCd_live_type_home() {
		return cd_live_type_home;
	}
	public void setCd_live_type_home(String cd_live_type_home) {
		this.cd_live_type_home = cd_live_type_home;
	}
	public String getAmt_rent_deposit_home() {
		return amt_rent_deposit_home;
	}
	public void setAmt_rent_deposit_home(String amt_rent_deposit_home) {
		this.amt_rent_deposit_home = amt_rent_deposit_home;
	}
	public String getAmt_rent_monthly_home() {
		return amt_rent_monthly_home;
	}
	public void setAmt_rent_monthly_home(String amt_rent_monthly_home) {
		this.amt_rent_monthly_home = amt_rent_monthly_home;
	}
	public String getNm_owner_home() {
		return nm_owner_home;
	}
	public void setNm_owner_home(String nm_owner_home) {
		this.nm_owner_home = nm_owner_home;
	}
	public String getRel_owner_home() {
		return rel_owner_home;
	}
	public void setRel_owner_home(String rel_owner_home) {
		this.rel_owner_home = rel_owner_home;
	}
	public String getYn_addr_equal() {
		return yn_addr_equal;
	}
	public void setYn_addr_equal(String yn_addr_equal) {
		this.yn_addr_equal = yn_addr_equal;
	}
	public String getCd_addr_reg() {
		return cd_addr_reg;
	}
	public void setCd_addr_reg(String cd_addr_reg) {
		this.cd_addr_reg = cd_addr_reg;
	}
	public String getPost_reg() {
		return post_reg;
	}
	public void setPost_reg(String post_reg) {
		this.post_reg = post_reg;
	}
	public String getAddr1_reg() {
		return addr1_reg;
	}
	public void setAddr1_reg(String addr1_reg) {
		this.addr1_reg = addr1_reg;
	}
	public String getAddr2_reg() {
		return addr2_reg;
	}
	public void setAddr2_reg(String addr2_reg) {
		this.addr2_reg = addr2_reg;
	}
	public String getCd_status_addr_reg() {
		return cd_status_addr_reg;
	}
	public void setCd_status_addr_reg(String cd_status_addr_reg) {
		this.cd_status_addr_reg = cd_status_addr_reg;
	}
	public String getCd_house_type_reg() {
		return cd_house_type_reg;
	}
	public void setCd_house_type_reg(String cd_house_type_reg) {
		this.cd_house_type_reg = cd_house_type_reg;
	}
	public String getYm_house_reg() {
		return ym_house_reg;
	}
	public void setYm_house_reg(String ym_house_reg) {
		this.ym_house_reg = ym_house_reg;
	}
	public String getCd_live_type_reg() {
		return cd_live_type_reg;
	}
	public void setCd_live_type_reg(String cd_live_type_reg) {
		this.cd_live_type_reg = cd_live_type_reg;
	}
	public String getAmt_rent_deposit_reg() {
		return amt_rent_deposit_reg;
	}
	public void setAmt_rent_deposit_reg(String amt_rent_deposit_reg) {
		this.amt_rent_deposit_reg = amt_rent_deposit_reg;
	}
	public String getAmt_rent_monthly_reg() {
		return amt_rent_monthly_reg;
	}
	public void setAmt_rent_monthly_reg(String amt_rent_monthly_reg) {
		this.amt_rent_monthly_reg = amt_rent_monthly_reg;
	}
	public String getNm_owner_reg() {
		return nm_owner_reg;
	}
	public void setNm_owner_reg(String nm_owner_reg) {
		this.nm_owner_reg = nm_owner_reg;
	}
	public String getRel_owner_reg() {
		return rel_owner_reg;
	}
	public void setRel_owner_reg(String rel_owner_reg) {
		this.rel_owner_reg = rel_owner_reg;
	}
	public String getCd50_live_together() {
		return cd50_live_together;
	}
	public void setCd50_live_together(String cd50_live_together) {
		this.cd50_live_together = cd50_live_together;
	}
	public String getCd_job_class() {
		return cd_job_class;
	}
	public void setCd_job_class(String cd_job_class) {
		this.cd_job_class = cd_job_class;
	}
	public String getNm_comp() {
		return nm_comp;
	}
	public void setNm_comp(String nm_comp) {
		this.nm_comp = nm_comp;
	}
	public String getCd_job_term() {
		return cd_job_term;
	}
	public void setCd_job_term(String cd_job_term) {
		this.cd_job_term = cd_job_term;
	}
	public String getCd_duty_comp() {
		return cd_duty_comp;
	}
	public void setCd_duty_comp(String cd_duty_comp) {
		this.cd_duty_comp = cd_duty_comp;
	}
	public String getCd_job_comp() {
		return cd_job_comp;
	}
	public void setCd_job_comp(String cd_job_comp) {
		this.cd_job_comp = cd_job_comp;
	}
	public String getCd_industry() {
		return cd_industry;
	}
	public void setCd_industry(String cd_industry) {
		this.cd_industry = cd_industry;
	}
	public String getNm_industry_detail() {
		return nm_industry_detail;
	}
	public void setNm_industry_detail(String nm_industry_detail) {
		this.nm_industry_detail = nm_industry_detail;
	}
	public String getCd_addr_comp() {
		return cd_addr_comp;
	}
	public void setCd_addr_comp(String cd_addr_comp) {
		this.cd_addr_comp = cd_addr_comp;
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
	public String getCd_status_addr_comp() {
		return cd_status_addr_comp;
	}
	public void setCd_status_addr_comp(String cd_status_addr_comp) {
		this.cd_status_addr_comp = cd_status_addr_comp;
	}
	public String getPh_comp() {
		return ph_comp;
	}
	public void setPh_comp(String ph_comp) {
		this.ph_comp = ph_comp;
	}
	public String getCd_status_ph_comp() {
		return cd_status_ph_comp;
	}
	public void setCd_status_ph_comp(String cd_status_ph_comp) {
		this.cd_status_ph_comp = cd_status_ph_comp;
	}
	public String getPh_comp_direct() {
		return ph_comp_direct;
	}
	public void setPh_comp_direct(String ph_comp_direct) {
		this.ph_comp_direct = ph_comp_direct;
	}
	public String getFax_comp() {
		return fax_comp;
	}
	public void setFax_comp(String fax_comp) {
		this.fax_comp = fax_comp;
	}
	public String getNm_duty_comp() {
		return nm_duty_comp;
	}
	public void setNm_duty_comp(String nm_duty_comp) {
		this.nm_duty_comp = nm_duty_comp;
	}
	public String getNm_part_comp() {
		return nm_part_comp;
	}
	public void setNm_part_comp(String nm_part_comp) {
		this.nm_part_comp = nm_part_comp;
	}
	public String getYm_start_comp() {
		return ym_start_comp;
	}
	public void setYm_start_comp(String ym_start_comp) {
		this.ym_start_comp = ym_start_comp;
	}
	public String getYn_medi_insu_comp() {
		return yn_medi_insu_comp;
	}
	public void setYn_medi_insu_comp(String yn_medi_insu_comp) {
		this.yn_medi_insu_comp = yn_medi_insu_comp;
	}
	public String getCd_scale_comp() {
		return cd_scale_comp;
	}
	public void setCd_scale_comp(String cd_scale_comp) {
		this.cd_scale_comp = cd_scale_comp;
	}
	public String getCnt_worker() {
		return cnt_worker;
	}
	public void setCnt_worker(String cnt_worker) {
		this.cnt_worker = cnt_worker;
	}
	public String getNo_biz_licence() {
		return no_biz_licence;
	}
	public void setNo_biz_licence(String no_biz_licence) {
		this.no_biz_licence = no_biz_licence;
	}
	public String getCd_employ_type() {
		return cd_employ_type;
	}
	public void setCd_employ_type(String cd_employ_type) {
		this.cd_employ_type = cd_employ_type;
	}
	public String getCd_dd_income() {
		return cd_dd_income;
	}
	public void setCd_dd_income(String cd_dd_income) {
		this.cd_dd_income = cd_dd_income;
	}
	public String getAmt_monthly_income() {
		return amt_monthly_income;
	}
	public void setAmt_monthly_income(String amt_monthly_income) {
		this.amt_monthly_income = amt_monthly_income;
	}
	public String getAmt_year_income() {
		return amt_year_income;
	}
	public void setAmt_year_income(String amt_year_income) {
		this.amt_year_income = amt_year_income;
	}
	public String getEtc_job() {
		return etc_job;
	}
	public void setEtc_job(String etc_job) {
		this.etc_job = etc_job;
	}
	public String getNm_univ() {
		return nm_univ;
	}
	public void setNm_univ(String nm_univ) {
		this.nm_univ = nm_univ;
	}
	public String getCd_attend_univ() {
		return cd_attend_univ;
	}
	public void setCd_attend_univ(String cd_attend_univ) {
		this.cd_attend_univ = cd_attend_univ;
	}
	public String getCd_grade_univ() {
		return cd_grade_univ;
	}
	public void setCd_grade_univ(String cd_grade_univ) {
		this.cd_grade_univ = cd_grade_univ;
	}
	public String getNo_univ() {
		return no_univ;
	}
	public void setNo_univ(String no_univ) {
		this.no_univ = no_univ;
	}
	public String getYyyy_enter_univ() {
		return yyyy_enter_univ;
	}
	public void setYyyy_enter_univ(String yyyy_enter_univ) {
		this.yyyy_enter_univ = yyyy_enter_univ;
	}
	public String getNm_part_univ() {
		return nm_part_univ;
	}
	public void setNm_part_univ(String nm_part_univ) {
		this.nm_part_univ = nm_part_univ;
	}
	public String getCd_addr_univ() {
		return cd_addr_univ;
	}
	public void setCd_addr_univ(String cd_addr_univ) {
		this.cd_addr_univ = cd_addr_univ;
	}
	public String getPh_univ() {
		return ph_univ;
	}
	public void setPh_univ(String ph_univ) {
		this.ph_univ = ph_univ;
	}
	public String getCd_status_ph_univ() {
		return cd_status_ph_univ;
	}
	public void setCd_status_ph_univ(String cd_status_ph_univ) {
		this.cd_status_ph_univ = cd_status_ph_univ;
	}
	public String getPost_univ() {
		return post_univ;
	}
	public void setPost_univ(String post_univ) {
		this.post_univ = post_univ;
	}
	public String getAddr1_univ() {
		return addr1_univ;
	}
	public void setAddr1_univ(String addr1_univ) {
		this.addr1_univ = addr1_univ;
	}
	public String getAddr2_univ() {
		return addr2_univ;
	}
	public void setAddr2_univ(String addr2_univ) {
		this.addr2_univ = addr2_univ;
	}
	public String getCd_status_addr_univ() {
		return cd_status_addr_univ;
	}
	public void setCd_status_addr_univ(String cd_status_addr_univ) {
		this.cd_status_addr_univ = cd_status_addr_univ;
	}
	public String getCd_addr_etc() {
		return cd_addr_etc;
	}
	public void setCd_addr_etc(String cd_addr_etc) {
		this.cd_addr_etc = cd_addr_etc;
	}
	public String getPost_etc() {
		return post_etc;
	}
	public void setPost_etc(String post_etc) {
		this.post_etc = post_etc;
	}
	public String getAddr1_etc() {
		return addr1_etc;
	}
	public void setAddr1_etc(String addr1_etc) {
		this.addr1_etc = addr1_etc;
	}
	public String getAddr2_etc() {
		return addr2_etc;
	}
	public void setAddr2_etc(String addr2_etc) {
		this.addr2_etc = addr2_etc;
	}
	public String getHp_etc() {
		return hp_etc;
	}
	public void setHp_etc(String hp_etc) {
		this.hp_etc = hp_etc;
	}
	public String getCd_hp_co_etc() {
		return cd_hp_co_etc;
	}
	public void setCd_hp_co_etc(String cd_hp_co_etc) {
		this.cd_hp_co_etc = cd_hp_co_etc;
	}
	public String getCd_status_hp_etc() {
		return cd_status_hp_etc;
	}
	public void setCd_status_hp_etc(String cd_status_hp_etc) {
		this.cd_status_hp_etc = cd_status_hp_etc;
	}
	public String getCd50_agree_item() {
		return cd50_agree_item;
	}
	public void setCd50_agree_item(String cd50_agree_item) {
		this.cd50_agree_item = cd50_agree_item;
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
	public String getKcb_cp() {
		return kcb_cp;
	}
	public void setKcb_cp(String kcb_cp) {
		this.kcb_cp = kcb_cp;
	}
	public String getKcb_di() {
		return kcb_di;
	}
	public void setKcb_di(String kcb_di) {
		this.kcb_di = kcb_di;
	}
	public String getEtc_memo() {
		return etc_memo;
	}
	public void setEtc_memo(String etc_memo) {
		this.etc_memo = etc_memo;
	}
	public String getCd_tel() {
		return cd_tel;
	}
	public void setCd_tel(String cd_tel) {
		this.cd_tel = cd_tel;
	}
	public String getProof_income() {
		return proof_income;
	}
	public void setProof_income(String proof_income) {
		this.proof_income = proof_income;
	}
	public String getYn_4insu() {
		return yn_4insu;
	}
	public void setYn_4insu(String yn_4insu) {
		this.yn_4insu = yn_4insu;
	}
	public String getAmt_min_living_cost() {
		return amt_min_living_cost;
	}
	public void setAmt_min_living_cost(String amt_min_living_cost) {
		this.amt_min_living_cost = amt_min_living_cost;
	}
	public String getCnt_live_together() {
		return cnt_live_together;
	}
	public void setCnt_live_together(String cnt_live_together) {
		this.cnt_live_together = cnt_live_together;
	}
	public String getCd_cnt_child() {
		return cd_cnt_child;
	}
	public void setCd_cnt_child(String cd_cnt_child) {
		this.cd_cnt_child = cd_cnt_child;
	}
	public String getComp_social() {
		return comp_social;
	}
	public void setComp_social(String comp_social) {
		this.comp_social = comp_social;
	}
	public String getId_social() {
		return id_social;
	}
	public void setId_social(String id_social) {
		this.id_social = id_social;
	}
	public String getToken_social() {
		return token_social;
	}
	public void setToken_social(String token_social) {
		this.token_social = token_social;
	}
	public String getDt_agree_social() {
		return dt_agree_social;
	}
	public void setDt_agree_social(String dt_agree_social) {
		this.dt_agree_social = dt_agree_social;
	}
	public String getNm_social() {
		return nm_social;
	}
	public void setNm_social(String nm_social) {
		this.nm_social = nm_social;
	}
	public String getCnt_fail_mode() {
		return cnt_fail_mode;
	}
	public void setCnt_fail_mode(String cnt_fail_mode) {
		this.cnt_fail_mode = cnt_fail_mode;
	}
	public int getCnt_fail() {
		return cnt_fail;
	}
	public void setCnt_fail(int cnt_fail) {
		this.cnt_fail = cnt_fail;
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
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getType_push() {
		return type_push;
	}
	public void setType_push(String type_push) {
		this.type_push = type_push;
	}
	public String getYn_push() {
		return yn_push;
	}
	public void setYn_push(String yn_push) {
		this.yn_push = yn_push;
	}
	public String getCd_push() {
		return cd_push;
	}
	public void setCd_push(String cd_push) {
		this.cd_push = cd_push;
	}
	public String getItem_push() {
		return item_push;
	}
	public void setItem_push(String item_push) {
		this.item_push = item_push;
	}
	public String getStat_push() {
		return stat_push;
	}
	public void setStat_push(String stat_push) {
		this.stat_push = stat_push;
	}
	public String getFcm_token() {
		return fcm_token;
	}
	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}
	public String getStock_token() {
		return stock_token;
	}
	public void setStock_token(String stock_token) {
		this.stock_token = stock_token;
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
