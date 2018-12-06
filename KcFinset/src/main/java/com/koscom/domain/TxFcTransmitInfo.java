package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class TxFcTransmitInfo implements Serializable{

	/**
	 * 금융사 송신 전문 저장
	 */
	private static final long serialVersionUID = 1281063781056949939L;

	protected String addr1_home                   ;//자택주소
	protected String addr2_home                   ;//자택주소(상세주소)
	protected long   amt_rent_monthly_home        ;//월세금액
	protected long   amt_wanted                   ;//대출희망금액
	protected long   amt_year_income              ;//연소득금액
	protected long   amt_year_income_biz          ;//연수입
	protected String cd_army                   	  ;//병역사항
	protected String cd_certify_person            ;//본인인증구분
	protected String cd_duty_comp                 ;//직업직위
	protected String cd_employee_type             ;//고용형태
	protected String cd_goods                     ;//상품코드
	protected String cd_house_type                ;//주거종류
	protected String cd_hp_co                     ;//통신사
	protected String cd_income_proof              ;//소득증빙 가능코드
	protected String cd_job_class_l               ;//직업구분
	protected String cd_job_class_m               ;//직업구분
	protected String cd_job_class_s               ;//직업구분
	protected String cd_job_comp                  ;//직무구분
	protected String cd_job_proof                 ;//재직증빙코드
	protected String cd_live_area                 ;//거주지역
	protected String cd_live_type_home            ;//주거소유형태
	protected String cd_loan_term                 ;//대출기간
	protected String cd_loan_use                  ;//자금용도
	protected String cd_marry                     ;//결혼구분
	protected String cd_medi_insu_comp            ;//건강보험코드
	protected String cd_onoff                     ;//온오프라인구분
	protected String cd_sex                       ;//성별구분
	protected String cnt_house_home               ;//현거주지개월수
	protected String dt_frt                       ;//최초입력시간
	protected String dt_lst                       ;//최종수정시간
	protected String dt_send                      ;//전문전송시간
	protected String email                        ;//이메일
	protected String etc_field1                   ;//예비 필드1
	protected String hp1                          ;//휴대폰1
	protected String hp2                          ;//휴대폰2
	protected String hp3                          ;//휴대폰3
	protected String id_frt                       ;//최초입력아이디
	protected String id_lst                       ;//최종수정아이디
	protected String job_term_month               ;//재직기간
	protected String kcb_di                       ;//KCB인증값
	protected String nm_comp                      ;//회사명
	protected String nm_fc                        ;//금융사명
	protected String nm_person                    ;//고객명
	protected String no_biz_comp                  ;//직장사업자번호
	protected String no_comp_seq                  ;//Company Seg
	protected String no_fc_req                    ;//금융기관신청번호
	protected String no_manage_home               ;//자택건물관리번호
	protected String no_person                    ;//사용자고유번호
	protected String no_bunch                     ; //순번
	protected String ph1_comp                     ;//자택전화1
	protected String ph1_home                     ;//자택전화1
	protected String ph2_comp                     ;//자택전화2
	protected String ph2_home                     ;//자택전화2
	protected String ph3_comp                     ;//자택전화3
	protected String ph3_home                     ;//자택전화3
	protected String post_home                    ;//자택주소(우편번호)
	protected String ssn_person                   ;//주민등록번호
	protected String ymd_birth                    ;//생년월일
	protected String ymd_house_home               ;//현거주지전입일
	protected String ymd_start_biz                ;//개입일(입사일)
	protected String ymd_start_comp               ;//입사일자
	protected String yn_4insu                     ;//4대보험여부
	protected String yn_dualwork                  ;//맞벌이여부

	protected String jb_dt_join; //입사년월
	protected String jb_tp_comppriv;//기업구분
	protected String jb_tp_listing; //상장여부
	protected String jb_tp_eprmdy;  //기업주체구분
	protected String jb_tp_compsize; //기업규모
	protected String jb_tp_etlipc; //외부감사여부
	protected String jb_tp_compchulja; //상호출자제한집단(그룹사) 여부
	protected String jb_tp_mediinsure; //의료보험가입여부

	//차량 담보 대출
	protected String cd_car_maker_comp;        //제조사
	protected String cd_car_model;             //모델명
	protected String nm_car;                   //차량명
	protected String cd_car_model2;            //세부모델
	protected String dt_car_purchase;          //구입일자
	protected String car_no;                   //차량번호
	protected String cd_car_own;               //차량소유형태
	protected String cd_car;                   //차량구분
	protected String cd_car_use;               //차량용도
	protected String cd_car_transmission;      //변속기
	protected String no_car_distance_driven;   //주행거리
	protected String yn_car_seizure;           //압류여부
	protected String yn_car_wet;               //전손침수차량여부
	protected long amt_car_self_insured;     //자차사고금액
	protected String yn_car_insu;              //종합보험가입여부
	protected String yn_car_direct_import;     //직수입차량여부
	protected String no_elapsed_year;          //차량경과년수

	//부동산 담보 대출
	protected String region_1;						//주소1(시,도)
	protected String region_2;						//주소2(동)
	protected String region_3;						//주소3(번지)
	protected String building_no;					//아파트동
	protected String room_no;						//호수
	protected String security_floor;				//담보물건층수
	protected String security_addr;					//담보물건주소
	protected String security_exclusive_area;		//담보물건면적

	protected String loan_code;	//대출 구분 코드 // 01 : 직장인 신용대출 02 : 자영업자 신용대출

	protected String yn_loan_mount;	//거치여부
	protected String cd_loan_mount; //거치기간
	protected String cd_type_pay; 	//상환방식
	protected String amt_year_sale; 	//연매출액
	protected String yn_loan_already;
	protected String yn_user_end;

	protected String cd_cancelation = "0";	//실행취소 구분(취소:1, 유지:0)
	protected String errorMsg;	//에러메세지


	public String getJb_dt_join() {
		return jb_dt_join;
	}


	public void setJb_dt_join(String jb_dt_join) {
		this.jb_dt_join = jb_dt_join;
	}


	public String getJb_tp_comppriv() {
		return jb_tp_comppriv;
	}


	public void setJb_tp_comppriv(String jb_tp_comppriv) {
		this.jb_tp_comppriv = jb_tp_comppriv;
	}


	public String getJb_tp_listing() {
		return jb_tp_listing;
	}


	public void setJb_tp_listing(String jb_tp_listing) {
		this.jb_tp_listing = jb_tp_listing;
	}


	public String getJb_tp_eprmdy() {
		return jb_tp_eprmdy;
	}


	public void setJb_tp_eprmdy(String jb_tp_eprmdy) {
		this.jb_tp_eprmdy = jb_tp_eprmdy;
	}


	public String getJb_tp_compsize() {
		return jb_tp_compsize;
	}


	public void setJb_tp_compsize(String jb_tp_compsize) {
		this.jb_tp_compsize = jb_tp_compsize;
	}


	public String getJb_tp_etlipc() {
		return jb_tp_etlipc;
	}


	public void setJb_tp_etlipc(String jb_tp_etlipc) {
		this.jb_tp_etlipc = jb_tp_etlipc;
	}


	public String getJb_tp_compchulja() {
		return jb_tp_compchulja;
	}


	public void setJb_tp_compchulja(String jb_tp_compchulja) {
		this.jb_tp_compchulja = jb_tp_compchulja;
	}


	public String getJb_tp_mediinsure() {
		return jb_tp_mediinsure;
	}


	public void setJb_tp_mediinsure(String jb_tp_mediinsure) {
		this.jb_tp_mediinsure = jb_tp_mediinsure;
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


	public long getAmt_rent_monthly_home() {
		return amt_rent_monthly_home;
	}


	public void setAmt_rent_monthly_home(long amt_rent_monthly_home) {
		this.amt_rent_monthly_home = amt_rent_monthly_home;
	}


	public long getAmt_wanted() {
		return amt_wanted;
	}


	public void setAmt_wanted(long amt_wanted) {
		this.amt_wanted = amt_wanted;
	}


	public long getAmt_year_income() {
		return amt_year_income;
	}


	public void setAmt_year_income(long amt_year_income) {
		this.amt_year_income = amt_year_income;
	}


	public long getAmt_year_income_biz() {
		return amt_year_income_biz;
	}


	public void setAmt_year_income_biz(long amt_year_income_biz) {
		this.amt_year_income_biz = amt_year_income_biz;
	}


	public String getCd_army() {
		return cd_army;
	}


	public void setCd_army(String cd_army) {
		this.cd_army = cd_army;
	}


	public String getCd_certify_person() {
		return cd_certify_person;
	}


	public void setCd_certify_person(String cd_certify_person) {
		this.cd_certify_person = cd_certify_person;
	}


	public String getCd_duty_comp() {
		return cd_duty_comp;
	}


	public void setCd_duty_comp(String cd_duty_comp) {
		this.cd_duty_comp = cd_duty_comp;
	}


	public String getCd_employee_type() {
		return cd_employee_type;
	}


	public void setCd_employee_type(String cd_employee_type) {
		this.cd_employee_type = cd_employee_type;
	}


	public String getCd_goods() {
		return cd_goods;
	}


	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}


	public String getCd_house_type() {
		return cd_house_type;
	}


	public void setCd_house_type(String cd_house_type) {
		this.cd_house_type = cd_house_type;
	}


	public String getCd_hp_co() {
		return cd_hp_co;
	}


	public void setCd_hp_co(String cd_hp_co) {
		this.cd_hp_co = cd_hp_co;
	}


	public String getCd_income_proof() {
		return cd_income_proof;
	}


	public void setCd_income_proof(String cd_income_proof) {
		this.cd_income_proof = cd_income_proof;
	}


	public String getCd_job_class_l() {
		return cd_job_class_l;
	}


	public void setCd_job_class_l(String cd_job_class_l) {
		this.cd_job_class_l = cd_job_class_l;
	}


	public String getCd_job_class_m() {
		return cd_job_class_m;
	}


	public void setCd_job_class_m(String cd_job_class_m) {
		this.cd_job_class_m = cd_job_class_m;
	}


	public String getCd_job_class_s() {
		return cd_job_class_s;
	}


	public void setCd_job_class_s(String cd_job_class_s) {
		this.cd_job_class_s = cd_job_class_s;
	}


	public String getCd_job_comp() {
		return cd_job_comp;
	}


	public void setCd_job_comp(String cd_job_comp) {
		this.cd_job_comp = cd_job_comp;
	}


	public String getCd_job_proof() {
		return cd_job_proof;
	}


	public void setCd_job_proof(String cd_job_proof) {
		this.cd_job_proof = cd_job_proof;
	}


	public String getCd_live_area() {
		return cd_live_area;
	}


	public void setCd_live_area(String cd_live_area) {
		this.cd_live_area = cd_live_area;
	}


	public String getCd_live_type_home() {
		return cd_live_type_home;
	}


	public void setCd_live_type_home(String cd_live_type_home) {
		this.cd_live_type_home = cd_live_type_home;
	}


	public String getCd_loan_term() {
		return cd_loan_term;
	}


	public void setCd_loan_term(String cd_loan_term) {
		this.cd_loan_term = cd_loan_term;
	}


	public String getCd_loan_use() {
		return cd_loan_use;
	}


	public void setCd_loan_use(String cd_loan_use) {
		this.cd_loan_use = cd_loan_use;
	}


	public String getCd_marry() {
		return cd_marry;
	}


	public void setCd_marry(String cd_marry) {
		this.cd_marry = cd_marry;
	}


	public String getCd_medi_insu_comp() {
		return cd_medi_insu_comp;
	}


	public void setCd_medi_insu_comp(String cd_medi_insu_comp) {
		this.cd_medi_insu_comp = cd_medi_insu_comp;
	}


	public String getCd_onoff() {
		return cd_onoff;
	}


	public void setCd_onoff(String cd_onoff) {
		this.cd_onoff = cd_onoff;
	}


	public String getCd_sex() {
		return cd_sex;
	}


	public void setCd_sex(String cd_sex) {
		this.cd_sex = cd_sex;
	}


	public String getCnt_house_home() {
		return cnt_house_home;
	}


	public void setCnt_house_home(String cnt_house_home) {
		this.cnt_house_home = cnt_house_home;
	}


	public String getDt_frt() {
		return dt_frt;
	}


	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}


	public String getDt_lst() {
		return dt_lst;
	}


	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}


	public String getDt_send() {
		return dt_send;
	}


	public void setDt_send(String dt_send) {
		this.dt_send = dt_send;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEtc_field1() {
		return etc_field1;
	}


	public void setEtc_field1(String etc_field1) {
		this.etc_field1 = etc_field1;
	}


	public String getHp1() {
		return hp1;
	}


	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}


	public String getHp2() {
		return hp2;
	}


	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}


	public String getHp3() {
		return hp3;
	}


	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}


	public String getId_frt() {
		return id_frt;
	}


	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}


	public String getId_lst() {
		return id_lst;
	}


	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}


	public String getJob_term_month() {
		return job_term_month;
	}


	public void setJob_term_month(String job_term_month) {
		this.job_term_month = job_term_month;
	}


	public String getKcb_di() {
		return kcb_di;
	}


	public void setKcb_di(String kcb_di) {
		this.kcb_di = kcb_di;
	}


	public String getNm_comp() {
		return nm_comp;
	}


	public void setNm_comp(String nm_comp) {
		this.nm_comp = nm_comp;
	}


	public String getNm_fc() {
		return nm_fc;
	}


	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}


	public String getNm_person() {
		return nm_person;
	}


	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}


	public String getNo_biz_comp() {
		return no_biz_comp;
	}


	public void setNo_biz_comp(String no_biz_comp) {
		this.no_biz_comp = no_biz_comp;
	}


	public String getNo_comp_seq() {
		return no_comp_seq;
	}


	public void setNo_comp_seq(String no_comp_seq) {
		this.no_comp_seq = no_comp_seq;
	}


	public String getNo_fc_req() {
		return no_fc_req;
	}


	public void setNo_fc_req(String no_fc_req) {
		this.no_fc_req = no_fc_req;
	}


	public String getNo_manage_home() {
		return no_manage_home;
	}


	public void setNo_manage_home(String no_manage_home) {
		this.no_manage_home = no_manage_home;
	}


	public String getNo_person() {
		return no_person;
	}


	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}


	public String getNo_bunch() {
		return no_bunch;
	}


	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
	public String getPh1_comp() {
		return ph1_comp;
	}


	public void setPh1_comp(String ph1_comp) {
		this.ph1_comp = ph1_comp;
	}


	public String getPh1_home() {
		return ph1_home;
	}


	public void setPh1_home(String ph1_home) {
		this.ph1_home = ph1_home;
	}


	public String getPh2_comp() {
		return ph2_comp;
	}


	public void setPh2_comp(String ph2_comp) {
		this.ph2_comp = ph2_comp;
	}


	public String getPh2_home() {
		return ph2_home;
	}


	public void setPh2_home(String ph2_home) {
		this.ph2_home = ph2_home;
	}


	public String getPh3_comp() {
		return ph3_comp;
	}


	public void setPh3_comp(String ph3_comp) {
		this.ph3_comp = ph3_comp;
	}


	public String getPh3_home() {
		return ph3_home;
	}


	public void setPh3_home(String ph3_home) {
		this.ph3_home = ph3_home;
	}


	public String getPost_home() {
		return post_home;
	}


	public void setPost_home(String post_home) {
		this.post_home = post_home;
	}


	public String getSsn_person() {
		return ssn_person;
	}


	public void setSsn_person(String ssn_person) {
		this.ssn_person = ssn_person;
	}


	public String getYmd_birth() {
		return ymd_birth;
	}


	public void setYmd_birth(String ymd_birth) {
		this.ymd_birth = ymd_birth;
	}


	public String getYmd_house_home() {
		return ymd_house_home;
	}


	public void setYmd_house_home(String ymd_house_home) {
		this.ymd_house_home = ymd_house_home;
	}


	public String getYmd_start_biz() {
		return ymd_start_biz;
	}


	public void setYmd_start_biz(String ymd_start_biz) {
		this.ymd_start_biz = ymd_start_biz;
	}


	public String getYmd_start_comp() {
		return ymd_start_comp;
	}


	public void setYmd_start_comp(String ymd_start_comp) {
		this.ymd_start_comp = ymd_start_comp;
	}


	public String getYn_4insu() {
		return yn_4insu;
	}


	public void setYn_4insu(String yn_4insu) {
		this.yn_4insu = yn_4insu;
	}


	public String getYn_dualwork() {
		return yn_dualwork;
	}


	public void setYn_dualwork(String yn_dualwork) {
		this.yn_dualwork = yn_dualwork;
	}


	public String getCd_car_maker_comp() {
		return cd_car_maker_comp;
	}


	public void setCd_car_maker_comp(String cd_car_maker_comp) {
		this.cd_car_maker_comp = cd_car_maker_comp;
	}


	public String getCd_car_model() {
		return cd_car_model;
	}


	public void setCd_car_model(String cd_car_model) {
		this.cd_car_model = cd_car_model;
	}


	public String getNm_car() {
		return nm_car;
	}


	public void setNm_car(String nm_car) {
		this.nm_car = nm_car;
	}


	public String getCd_car_model2() {
		return cd_car_model2;
	}


	public void setCd_car_model2(String cd_car_model2) {
		this.cd_car_model2 = cd_car_model2;
	}


	public String getDt_car_purchase() {
		return dt_car_purchase;
	}


	public void setDt_car_purchase(String dt_car_purchase) {
		this.dt_car_purchase = dt_car_purchase;
	}


	public String getCar_no() {
		return car_no;
	}


	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}


	public String getCd_car_own() {
		return cd_car_own;
	}


	public void setCd_car_own(String cd_car_own) {
		this.cd_car_own = cd_car_own;
	}


	public String getCd_car() {
		return cd_car;
	}


	public void setCd_car(String cd_car) {
		this.cd_car = cd_car;
	}


	public String getCd_car_use() {
		return cd_car_use;
	}


	public void setCd_car_use(String cd_car_use) {
		this.cd_car_use = cd_car_use;
	}


	public String getCd_car_transmission() {
		return cd_car_transmission;
	}


	public void setCd_car_transmission(String cd_car_transmission) {
		this.cd_car_transmission = cd_car_transmission;
	}


	public String getNo_car_distance_driven() {
		return no_car_distance_driven;
	}


	public void setNo_car_distance_driven(String no_car_distance_driven) {
		this.no_car_distance_driven = no_car_distance_driven;
	}


	public String getYn_car_seizure() {
		return yn_car_seizure;
	}


	public void setYn_car_seizure(String yn_car_seizure) {
		this.yn_car_seizure = yn_car_seizure;
	}


	public String getYn_car_wet() {
		return yn_car_wet;
	}


	public void setYn_car_wet(String yn_car_wet) {
		this.yn_car_wet = yn_car_wet;
	}


	public long getAmt_car_self_insured() {
		return amt_car_self_insured;
	}


	public void setAmt_car_self_insured(long amt_car_self_insured) {
		this.amt_car_self_insured = amt_car_self_insured;
	}


	public String getYn_car_insu() {
		return yn_car_insu;
	}


	public void setYn_car_insu(String yn_car_insu) {
		this.yn_car_insu = yn_car_insu;
	}


	public String getYn_car_direct_import() {
		return yn_car_direct_import;
	}


	public void setYn_car_direct_import(String yn_car_direct_import) {
		this.yn_car_direct_import = yn_car_direct_import;
	}


	public String getNo_elapsed_year() {
		return no_elapsed_year;
	}


	public void setNo_elapsed_year(String no_elapsed_year) {
		this.no_elapsed_year = no_elapsed_year;
	}


	public String getRegion_1() {
		return region_1;
	}


	public void setRegion_1(String region_1) {
		this.region_1 = region_1;
	}


	public String getRegion_2() {
		return region_2;
	}


	public void setRegion_2(String region_2) {
		this.region_2 = region_2;
	}


	public String getRegion_3() {
		return region_3;
	}


	public void setRegion_3(String region_3) {
		this.region_3 = region_3;
	}


	public String getBuilding_no() {
		return building_no;
	}


	public void setBuilding_no(String building_no) {
		this.building_no = building_no;
	}


	public String getRoom_no() {
		return room_no;
	}


	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}


	public String getSecurity_floor() {
		return security_floor;
	}


	public void setSecurity_floor(String security_floor) {
		this.security_floor = security_floor;
	}


	public String getSecurity_addr() {
		return security_addr;
	}


	public void setSecurity_addr(String security_addr) {
		this.security_addr = security_addr;
	}


	public String getSecurity_exclusive_area() {
		return security_exclusive_area;
	}


	public void setSecurity_exclusive_area(String security_exclusive_area) {
		this.security_exclusive_area = security_exclusive_area;
	}


	public String getLoan_code() {
		return loan_code;
	}


	public void setLoan_code(String loan_code) {
		this.loan_code = loan_code;
	}


	public String getYn_loan_mount() {
		return yn_loan_mount;
	}


	public void setYn_loan_mount(String yn_loan_mount) {
		this.yn_loan_mount = yn_loan_mount;
	}


	public String getCd_loan_mount() {
		return cd_loan_mount;
	}


	public void setCd_loan_mount(String cd_loan_mount) {
		this.cd_loan_mount = cd_loan_mount;
	}


	public String getCd_type_pay() {
		return cd_type_pay;
	}


	public void setCd_type_pay(String cd_type_pay) {
		this.cd_type_pay = cd_type_pay;
	}


	public String getAmt_year_sale() {
		return amt_year_sale;
	}


	public void setAmt_year_sale(String amt_year_sale) {
		this.amt_year_sale = amt_year_sale;
	}

	public String getYn_loan_already() {
		return yn_loan_already;
	}


	public void setYn_loan_already(String yn_loan_already) {
		this.yn_loan_already = yn_loan_already;
	}


	public String getYn_user_end() {
		return yn_user_end;
	}


	public void setYn_user_end(String yn_user_end) {
		this.yn_user_end = yn_user_end;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}


	public String getCd_cancelation() {
		return cd_cancelation;
	}


	public void setCd_cancelation(String cd_cancelation) {
		this.cd_cancelation = cd_cancelation;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
