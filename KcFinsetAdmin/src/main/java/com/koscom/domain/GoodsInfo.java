package com.koscom.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GoodsInfo implements Serializable{
	private static final long serialVersionUID = -3465354945781988019L;
	private int cnt_reapply_month;	//재신청기간 20161102추가
	private String cd_div_goods;		//나중에 추가한 대분류 AC, 오토, 환승 분류
	private String c20_goods_comp;	//
	private String id_request;		//
	private String url_request;		//
	private String nm_request_form;	//
	private String etc;				//
	private String seq_order;			//
	private String summary;			// 요약설명
	private String content;			// 상품설명
	private String path_file1;		// 첨부파일1
	private String nm_file1;			// 첨부파일1이름
	private String path_file2;		// 첨부파일2
	private String nm_file2;			// 첨부파일2이름
	//20160819 - CI로고 올리기
	private String path_fileCi;		// CI파일
	private String nm_fileCi;			// CI파일이름
	private String yn_first;			// 선순위 여부
	private String yn_check;			// 선인증 여부
	private String yn_auto;			// 자동접수 여부
	private String cd50_goods_job;	// 상품 직군
	private String no_bunch;	// 상품 직군

	private String id_frt;
	private String dt_frt;
	private String id_lst;
	private String dt_lst;


	//20170629 상품쪽 추가 사항 start

	private String cd_fc;  				//금융사코드
	private String nm_fc;  				//금융사명
	private String nm_goods; 			//상품명
	private String cd_goods; 			//상품코드
	private String yn_use; 				//판매여부
	private String yn_zero_ratio;		//제로금리표시여부
	private String cd_goods_class_l; 	//대분류
	private String cd_goods_class_m; 	//중분류
	private String cd_goods_class_s; 	//소분류
	private String cd_goods_type; 		//상품종류
	private Integer	 cd_loan_term; 			//대출기간
	private String cd_type_interest; 	//금리적용방식 CD_RATIO_TYPE
	private Double rto_interest_from; 	//대출금리
	private Double rto_interest_to; 	//대출금리
	private Integer amt_limit; 			 //한도
	private String cd_type_pay; 		 //상환방식
	private String cd_event; 			 //이벤트
	private String yn_online; 			 //비대면	여부
	private String yn_proc_nopapers; 	 //무서류 진행 여부
	private String desc_trade_clause; 	 //금융거래약관
	private String desc_goods;			 //상세
	private String desc_loan; 			 //대출대상상세
	private String desc_limit; 			 //대출한도상세
	private String desc_paymethod; 		 //대출기간
	private String desc_papers;          //구비서류
	private Integer age_loan_from; 			 //대출 가능 연령
	private Integer age_loan_to; 			//대출 가능 연령
	private String amt_apply_from; 		//신청금액(만원)
	private String amt_apply_to; 		//신청금액(만원)
	private Integer no_month_apply_from; 	//신청기간(개월)
	private Integer no_month_apply_to; 		//신청기간(개월)
	private String cd_type_income; 		//소득형태
	private String cd_employ_type; 		//고용형태
	private String cd_loan_use; 		//자금용도
	private String amt_year_income; 	//연소득
	private String ou_year_income; 		//연소득 이상 이하
	private String amt_year_sale; 		//연매출액(사업자)
	private String ou_sale_income; 		//연매출액 이상 이하
	private String ymd_start_comp; 		//입사일자
	private Integer no_job_year; 			//근속 연수
	private String cd_live_type; 		//주거소유형태
	private String cd_house_type; 		//주거종류
	private String yn_proof_income; 	//소득증빙여부
	private String yn_4insu; 			//4대 보험 가입여부(3개월 이상 납입)
	private String yn_bad_credit; 		//은행연합회 신용관리정보(구 신용불량정보) 등재자
	private String yn_delay_current; 	//연체 여부(현재)
	private Integer day_delay_6month; 	//연체 여부(최근6개월)
	private Integer day_delay_12month; 	//연체 여부(최근 1년)
	private Integer grade_kcb; 				//신용등급(KCB)
	private Integer grade_nice; 			//신용등급(NICE)
	private Double dti_grade1; 			//DTI 1등급
	private Double dti_grade2; 			//DTI 2등급
	private Double dti_grade3; 			//DTI 3등급
	private Double dti_grade4; 			//DTI 4등급
	private Double dti_grade5; 			//DTI 5등급
	private Double dti_grade6; 			//DTI 6등급
	private Double dti_grade7; 			//DTI 7등급
	private Double dti_grade8; 			//DTI 8등급
	private Double dti_grade9; 			//DTI 9등급
	private Double dti_grade10; 		//DTI 10등급
	private Integer grade_ltv; 			//ltv
	private Integer grade_dti; 			//dti
	private String cd_sex; 				//성별
	private String cd_owncar_type;		//담보소유형태
	private String cd_car_type; 			//담보종류
	private Integer cnt_cash_service; 		//현금서비스 (건)
	private Integer amt_cash_service; 	//현금서비스(만원)
	private Integer amt_carprice_from;   //차량가격(만원)
	private Integer amt_carprice_to;	//차량가격(만원)
	private Integer no_caryear_from;		//차량연식(년)
	private Integer no_caryear_to;		//차량연식(년)


	//20170627 수수료율 추가
	private Integer amt_limit_min;			//최저 한도
	private Integer amt_limit_max;			//최대 한도
	private Double rto_interest_min;		//최저 금리
	private Double rto_interest_max;		//최대 금리
	private Double rto_commission;		//수수료율
	private Integer grade;				//등급

	//CFS 신용 체크박스
	protected String chk_age_loan;
	protected String chk_amt_apply;
	protected String chk_no_month_apply;
	protected String chk_cd_type_income;
	protected String chk_cd_employ_type;
	protected String chk_cd_loan_use;
	protected String chk_amt_year_income;
	protected String chk_amt_year_sale;
	protected String chk_ymd_start_comp;
	protected String chk_no_job_year;
	protected String chk_cd_live_type;
	protected String chk_cd_house_type;
	protected String chk_yn_proof_income;
	protected String chk_yn_4insu;
	protected String chk_yn_bad_credit;
	protected String chk_yn_delay_current;
	protected String chk_day_delay_6month;
	protected String chk_day_delay_12month;
	protected String chk_grade_kcb;
	protected String chk_grade_nice;
	protected String chk_dti_grade;
	protected String chk_grade_ltv;
	protected String chk_grade_dti;
	protected String chk_cd_sex; //체크박스 성별
	protected String chk_cash_service; //현금서비스(건)
	protected String chk_cd_owncar_type; // 담보소유형태차종류
	protected String chk_cd_car_type; // 담보종류
	protected String chk_amt_carprice;//차량가격
	protected String chk_no_caryear; //차량연식

	protected String content_interest; //이자금리
	protected String prefer_interest; //우대금리
	protected String desc_feature= "";	//(요약)상품특징
	protected String keyword_list= "";	//키워드

	//20170629 상품쪽 추가 사항 end

	//조건검색

	private String cd_apply_type;  //신청방식
	private String cd_trade_type; //거래방식
	//	private String cd_type_pay;
	private String cd_time_exec;  //실행시간
	private String cd_ratio_type; //금리방식
	private String cd_category_goods; //상품군 분류
	private String cd_collateral_house_type; //주택종류
	private String cd_collateral_loan_use; //자금용도
	private String cd_area;                 //면적
	private String cd_defer;                //처기방식
	private String cd_object;				//대상구분
	private String yn_send_docu;			//서류제출
	private String yn_visit;				//방문여부
	private String yn_erly_rpay_fee;		//중도상환수수류
	private String yn_srch_ratio_limit;     //금리/한도조회
	private String yn_post_ranking;   		//후순위가능여부

	//20180720 goodsbank_info금융사링크 추가
	private String fc_link;
	private String desc_contact; //goodsbank_info 연락처
	private String desc_etc; //goodsbank_info 기타유의사항
	//20180731 goodsbank_info이자부과시기 추가
	private String desc_overdue_interest; //연체이자상세
	private String desc_loan_cost; //대출비용상세
	private String time_interest; //이자부과시기

	public String getContent_interest() {
		return content_interest;
	}
	public void setContent_interest(String content_interest) {
		this.content_interest = content_interest;
	}
	public String getPrefer_interest() {
		return prefer_interest;
	}
	public void setPrefer_interest(String prefer_interest) {
		this.prefer_interest = prefer_interest;
	}
	private String desc_repaymethod; 		 //상환방식
	private String desc_commission;          //중도상환수수료
	private String desc_inquiry; 			 //상품문의

	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getNm_fc() {
		return nm_fc;
	}

	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}

	public String getCd_goods_class_l() {
		return cd_goods_class_l;
	}
	public void setCd_goods_class_l(String cd_goods_class_l) {
		this.cd_goods_class_l = cd_goods_class_l;
	}
	public String getCd_goods_class_m() {
		return cd_goods_class_m;
	}
	public void setCd_goods_class_m(String cd_goods_class_m) {
		this.cd_goods_class_m = cd_goods_class_m;
	}
	public String getCd_goods_class_s() {
		return cd_goods_class_s;
	}
	public void setCd_goods_class_s(String cd_goods_class_s) {
		this.cd_goods_class_s = cd_goods_class_s;
	}
	public Integer getCd_loan_term() {
		return cd_loan_term;
	}
	public void setCd_loan_term(Integer cd_loan_term) {
		this.cd_loan_term = cd_loan_term;
	}
	public String getCd_type_interest() {
		return cd_type_interest;
	}
	public void setCd_type_interest(String cd_type_interest) {
		this.cd_type_interest = cd_type_interest;
	}
	public Double getRto_interest_from() {
		return rto_interest_from;
	}
	public void setRto_interest_from(Double rto_interest_from) {
		this.rto_interest_from = rto_interest_from;
	}
	public Double getRto_interest_to() {
		return rto_interest_to;
	}
	public void setRto_interest_to(Double rto_interest_to) {
		this.rto_interest_to = rto_interest_to;
	}
	public Integer getAmt_limit() {
		return amt_limit;
	}
	public void setAmt_limit(Integer amt_limit) {
		this.amt_limit = amt_limit;
	}
	public String getCd_type_pay() {
		return cd_type_pay;
	}
	public void setCd_type_pay(String cd_type_pay) {
		this.cd_type_pay = cd_type_pay;
	}
	public String getCd_event() {
		return cd_event;
	}
	public void setCd_event(String cd_event) {
		this.cd_event = cd_event;
	}
	public String getYn_online() {
		return yn_online;
	}
	public void setYn_online(String yn_online) {
		this.yn_online = yn_online;
	}
	public String getYn_proc_nopapers() {
		return yn_proc_nopapers;
	}
	public void setYn_proc_nopapers(String yn_proc_nopapers) {
		this.yn_proc_nopapers = yn_proc_nopapers;
	}
	public String getDesc_trade_clause() {
		return desc_trade_clause;
	}
	public void setDesc_trade_clause(String desc_trade_clause) {
		this.desc_trade_clause = desc_trade_clause;
	}
	public String getDesc_goods() {
		return desc_goods;
	}
	public void setDesc_goods(String desc_goods) {
		this.desc_goods = desc_goods;
	}
	public String getDesc_loan() {
		return desc_loan;
	}
	public void setDesc_loan(String desc_loan) {
		this.desc_loan = desc_loan;
	}
	public String getDesc_limit() {
		return desc_limit;
	}
	public void setDesc_limit(String desc_limit) {
		this.desc_limit = desc_limit;
	}
	public String getDesc_paymethod() {
		return desc_paymethod;
	}
	public void setDesc_paymethod(String desc_paymethod) {
		this.desc_paymethod = desc_paymethod;
	}
	public String getDesc_papers() {
		return desc_papers;
	}
	public void setDesc_papers(String desc_papers) {
		this.desc_papers = desc_papers;
	}
	public Integer getAge_loan_from() {
		return age_loan_from;
	}
	public void setAge_loan_from(Integer age_loan_from) {
		this.age_loan_from = age_loan_from;
	}
	public Integer getAge_loan_to() {
		return age_loan_to;
	}
	public void setAge_loan_to(Integer age_loan_to) {
		this.age_loan_to = age_loan_to;
	}
	public String getAmt_apply_from() {
		return amt_apply_from;
	}
	public void setAmt_apply_from(String amt_apply_from) {
		this.amt_apply_from = amt_apply_from;
	}
	public String getAmt_apply_to() {
		return amt_apply_to;
	}
	public void setAmt_apply_to(String amt_apply_to) {
		this.amt_apply_to = amt_apply_to;
	}
	public Integer getNo_month_apply_from() {
		return no_month_apply_from;
	}
	public void setNo_month_apply_from(Integer no_month_apply_from) {
		this.no_month_apply_from = no_month_apply_from;
	}
	public Integer getNo_month_apply_to() {
		return no_month_apply_to;
	}
	public void setNo_month_apply_to(Integer no_month_apply_to) {
		this.no_month_apply_to = no_month_apply_to;
	}
	public String getCd_type_income() {
		return cd_type_income;
	}
	public void setCd_type_income(String cd_type_income) {
		this.cd_type_income = cd_type_income;
	}
	public String getCd_loan_use() {
		return cd_loan_use;
	}
	public void setCd_loan_use(String cd_loan_use) {
		this.cd_loan_use = cd_loan_use;
	}
	public String getAmt_year_income() {
		return amt_year_income;
	}
	public void setAmt_year_income(String amt_year_income) {
		this.amt_year_income = amt_year_income;
	}
	public String getOu_year_income() {
		return ou_year_income;
	}
	public void setOu_year_income(String ou_year_income) {
		this.ou_year_income = ou_year_income;
	}
	public String getAmt_year_sale() {
		return amt_year_sale;
	}
	public void setAmt_year_sale(String amt_year_sale) {
		this.amt_year_sale = amt_year_sale;
	}
	public String getOu_sale_income() {
		return ou_sale_income;
	}
	public void setOu_sale_income(String ou_sale_income) {
		this.ou_sale_income = ou_sale_income;
	}
	public String getYmd_start_comp() {
		return ymd_start_comp;
	}
	public void setYmd_start_comp(String ymd_start_comp) {
		this.ymd_start_comp = ymd_start_comp;
	}
	public Integer getNo_job_year() {
		return no_job_year;
	}
	public void setNo_job_year(Integer no_job_year) {
		this.no_job_year = no_job_year;
	}
	public String getCd_live_type() {
		return cd_live_type;
	}
	public void setCd_live_type(String cd_live_type) {
		this.cd_live_type = cd_live_type;
	}
	public String getCd_house_type() {
		return cd_house_type;
	}
	public void setCd_house_type(String cd_house_type) {
		this.cd_house_type = cd_house_type;
	}
	public String getYn_proof_income() {
		return yn_proof_income;
	}
	public void setYn_proof_income(String yn_proof_income) {
		this.yn_proof_income = yn_proof_income;
	}
	public String getYn_4insu() {
		return yn_4insu;
	}
	public void setYn_4insu(String yn_4insu) {
		this.yn_4insu = yn_4insu;
	}
	public String getYn_bad_credit() {
		return yn_bad_credit;
	}
	public void setYn_bad_credit(String yn_bad_credit) {
		this.yn_bad_credit = yn_bad_credit;
	}
	public String getYn_delay_current() {
		return yn_delay_current;
	}
	public void setYn_delay_current(String yn_delay_current) {
		this.yn_delay_current = yn_delay_current;
	}
	public Integer getDay_delay_6month() {
		return day_delay_6month;
	}
	public void setDay_delay_6month(Integer day_delay_6month) {
		this.day_delay_6month = day_delay_6month;
	}
	public Integer getDay_delay_12month() {
		return day_delay_12month;
	}
	public void setDay_delay_12month(Integer day_delay_12month) {
		this.day_delay_12month = day_delay_12month;
	}
	public Integer getGrade_kcb() {
		return grade_kcb;
	}
	public void setGrade_kcb(Integer grade_kcb) {
		this.grade_kcb = grade_kcb;
	}
	public Integer getGrade_nice() {
		return grade_nice;
	}
	public void setGrade_nice(Integer grade_nice) {
		this.grade_nice = grade_nice;
	}
	public Double getDti_grade1() {
		return dti_grade1;
	}
	public void setDti_grade1(Double dti_grade1) {
		this.dti_grade1 = dti_grade1;
	}
	public Double getDti_grade2() {
		return dti_grade2;
	}
	public void setDti_grade2(Double dti_grade2) {
		this.dti_grade2 = dti_grade2;
	}
	public Double getDti_grade3() {
		return dti_grade3;
	}
	public void setDti_grade3(Double dti_grade3) {
		this.dti_grade3 = dti_grade3;
	}
	public Double getDti_grade4() {
		return dti_grade4;
	}
	public void setDti_grade4(Double dti_grade4) {
		this.dti_grade4 = dti_grade4;
	}
	public Double getDti_grade5() {
		return dti_grade5;
	}
	public void setDti_grade5(Double dti_grade5) {
		this.dti_grade5 = dti_grade5;
	}
	public Double getDti_grade6() {
		return dti_grade6;
	}
	public void setDti_grade6(Double dti_grade6) {
		this.dti_grade6 = dti_grade6;
	}
	public Double getDti_grade7() {
		return dti_grade7;
	}
	public void setDti_grade7(Double dti_grade7) {
		this.dti_grade7 = dti_grade7;
	}
	public Double getDti_grade8() {
		return dti_grade8;
	}
	public void setDti_grade8(Double dti_grade8) {
		this.dti_grade8 = dti_grade8;
	}
	public Double getDti_grade9() {
		return dti_grade9;
	}
	public void setDti_grade9(Double dti_grade9) {
		this.dti_grade9 = dti_grade9;
	}
	public Double getDti_grade10() {
		return dti_grade10;
	}
	public void setDti_grade10(Double dti_grade10) {
		this.dti_grade10 = dti_grade10;
	}
	public Integer getGrade_ltv() {
		return grade_ltv;
	}
	public Integer getGrade_dti() {
		return grade_dti;
	}
	public void setGrade_ltv(Integer grade_ltv) {
		this.grade_ltv = grade_ltv;
	}
	public void setGrade_dti(Integer grade_dti) {
		this.grade_dti = grade_dti;
	}
	public String getCd_sex() {
		return cd_sex;
	}
	public String getCd_owncar_type() {
		return cd_owncar_type;
	}
	public String getCd_car_type() {
		return cd_car_type;
	}
	public Integer getAmt_carprice_from() {
		return amt_carprice_from;
	}
	public Integer getAmt_carprice_to() {
		return amt_carprice_to;
	}
	public Integer getNo_caryear_from() {
		return no_caryear_from;
	}
	public Integer getNo_caryear_to() {
		return no_caryear_to;
	}
	public void setCd_sex(String cd_sex) {
		this.cd_sex = cd_sex;
	}
	public void setCd_owncar_type(String cd_owncar_type) {
		this.cd_owncar_type = cd_owncar_type;
	}
	public void setCd_car_type(String cd_car_type) {
		this.cd_car_type = cd_car_type;
	}
	public void setCnt_cash_service(Integer cnt_cash_service) {
		this.cnt_cash_service = cnt_cash_service;
	}
	public void setAmt_cash_service(Integer amt_cash_service) {
		this.amt_cash_service = amt_cash_service;
	}
	public void setAmt_carprice_from(Integer amt_carprice_from) {
		this.amt_carprice_from = amt_carprice_from;
	}
	public void setAmt_carprice_to(Integer amt_carprice_to) {
		this.amt_carprice_to = amt_carprice_to;
	}
	public void setNo_caryear_from(Integer no_caryear_from) {
		this.no_caryear_from = no_caryear_from;
	}
	public void setNo_caryear_to(Integer no_caryear_to) {
		this.no_caryear_to = no_caryear_to;
	}
	public Integer getAmt_limit_min() {
		return amt_limit_min;
	}
	public void setAmt_limit_min(Integer amt_limit_min) {
		this.amt_limit_min = amt_limit_min;
	}
	public Integer getAmt_limit_max() {
		return amt_limit_max;
	}
	public void setAmt_limit_max(Integer amt_limit_max) {
		this.amt_limit_max = amt_limit_max;
	}
	public Double getRto_interest_min() {
		return rto_interest_min;
	}
	public void setRto_interest_min(Double rto_interest_min) {
		this.rto_interest_min = rto_interest_min;
	}
	public Double getRto_interest_max() {
		return rto_interest_max;
	}
	public void setRto_interest_max(Double rto_interest_max) {
		this.rto_interest_max = rto_interest_max;
	}
	public Double getRto_commission() {
		return rto_commission;
	}
	public void setRto_commission(Double rto_commission) {
		this.rto_commission = rto_commission;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getChk_age_loan() {
		return chk_age_loan;
	}
	public void setChk_age_loan(String chk_age_loan) {
		this.chk_age_loan = chk_age_loan;
	}
	public String getChk_amt_apply() {
		return chk_amt_apply;
	}
	public void setChk_amt_apply(String chk_amt_apply) {
		this.chk_amt_apply = chk_amt_apply;
	}
	public String getChk_no_month_apply() {
		return chk_no_month_apply;
	}
	public void setChk_no_month_apply(String chk_no_month_apply) {
		this.chk_no_month_apply = chk_no_month_apply;
	}
	public String getChk_cd_type_income() {
		return chk_cd_type_income;
	}
	public void setChk_cd_type_income(String chk_cd_type_income) {
		this.chk_cd_type_income = chk_cd_type_income;
	}
	public String getChk_cd_employ_type() {
		return chk_cd_employ_type;
	}
	public void setChk_cd_employ_type(String chk_cd_employ_type) {
		this.chk_cd_employ_type = chk_cd_employ_type;
	}
	public String getChk_cd_loan_use() {
		return chk_cd_loan_use;
	}
	public void setChk_cd_loan_use(String chk_cd_loan_use) {
		this.chk_cd_loan_use = chk_cd_loan_use;
	}
	public String getChk_amt_year_income() {
		return chk_amt_year_income;
	}
	public void setChk_amt_year_income(String chk_amt_year_income) {
		this.chk_amt_year_income = chk_amt_year_income;
	}
	public String getChk_amt_year_sale() {
		return chk_amt_year_sale;
	}
	public void setChk_amt_year_sale(String chk_amt_year_sale) {
		this.chk_amt_year_sale = chk_amt_year_sale;
	}
	public String getChk_ymd_start_comp() {
		return chk_ymd_start_comp;
	}
	public void setChk_ymd_start_comp(String chk_ymd_start_comp) {
		this.chk_ymd_start_comp = chk_ymd_start_comp;
	}
	public String getChk_no_job_year() {
		return chk_no_job_year;
	}
	public void setChk_no_job_year(String chk_no_job_year) {
		this.chk_no_job_year = chk_no_job_year;
	}
	public String getChk_cd_live_type() {
		return chk_cd_live_type;
	}
	public void setChk_cd_live_type(String chk_cd_live_type) {
		this.chk_cd_live_type = chk_cd_live_type;
	}
	public String getChk_cd_house_type() {
		return chk_cd_house_type;
	}
	public void setChk_cd_house_type(String chk_cd_house_type) {
		this.chk_cd_house_type = chk_cd_house_type;
	}
	public String getChk_yn_proof_income() {
		return chk_yn_proof_income;
	}
	public void setChk_yn_proof_income(String chk_yn_proof_income) {
		this.chk_yn_proof_income = chk_yn_proof_income;
	}
	public String getChk_yn_4insu() {
		return chk_yn_4insu;
	}
	public void setChk_yn_4insu(String chk_yn_4insu) {
		this.chk_yn_4insu = chk_yn_4insu;
	}
	public String getChk_yn_bad_credit() {
		return chk_yn_bad_credit;
	}
	public void setChk_yn_bad_credit(String chk_yn_bad_credit) {
		this.chk_yn_bad_credit = chk_yn_bad_credit;
	}
	public String getChk_yn_delay_current() {
		return chk_yn_delay_current;
	}
	public void setChk_yn_delay_current(String chk_yn_delay_current) {
		this.chk_yn_delay_current = chk_yn_delay_current;
	}
	public String getChk_day_delay_6month() {
		return chk_day_delay_6month;
	}
	public void setChk_day_delay_6month(String chk_day_delay_6month) {
		this.chk_day_delay_6month = chk_day_delay_6month;
	}
	public String getChk_day_delay_12month() {
		return chk_day_delay_12month;
	}
	public void setChk_day_delay_12month(String chk_day_delay_12month) {
		this.chk_day_delay_12month = chk_day_delay_12month;
	}
	public String getChk_grade_kcb() {
		return chk_grade_kcb;
	}
	public void setChk_grade_kcb(String chk_grade_kcb) {
		this.chk_grade_kcb = chk_grade_kcb;
	}
	public String getChk_grade_nice() {
		return chk_grade_nice;
	}
	public void setChk_grade_nice(String chk_grade_nice) {
		this.chk_grade_nice = chk_grade_nice;
	}
	public String getChk_dti_grade() {
		return chk_dti_grade;
	}
	public void setChk_dti_grade(String chk_dti_grade) {
		this.chk_dti_grade = chk_dti_grade;
	}
	public String getChk_grade_ltv() {
		return chk_grade_ltv;
	}
	public String getChk_grade_dti() {
		return chk_grade_dti;
	}
	public void setChk_grade_ltv(String chk_grade_ltv) {
		this.chk_grade_ltv = chk_grade_ltv;
	}
	public void setChk_grade_dti(String chk_grade_dti) {
		this.chk_grade_dti = chk_grade_dti;
	}

	public String getChk_cd_sex() {
		return chk_cd_sex;
	}
	public String getChk_cash_service() {
		return chk_cash_service;
	}
	public String getChk_cd_owncar_type() {
		return chk_cd_owncar_type;
	}
	public String getChk_cd_car_type() {
		return chk_cd_car_type;
	}
	public Integer getCnt_cash_service() {
		return cnt_cash_service;
	}
	public Integer getAmt_cash_service() {
		return amt_cash_service;
	}
	public String getChk_amt_carprice() {
		return chk_amt_carprice;
	}
	public String getChk_no_caryear() {
		return chk_no_caryear;
	}
	public void setChk_cd_sex(String chk_cd_sex) {
		this.chk_cd_sex = chk_cd_sex;
	}
	public void setChk_cash_service(String chk_cash_service) {
		this.chk_cash_service = chk_cash_service;
	}
	public void setChk_cd_owncar_type(String chk_cd_owncar_type) {
		this.chk_cd_owncar_type = chk_cd_owncar_type;
	}
	public void setChk_cd_car_type(String chk_cd_car_type) {
		this.chk_cd_car_type = chk_cd_car_type;
	}
	public void setChk_amt_carprice(String chk_amt_carprice) {
		this.chk_amt_carprice = chk_amt_carprice;
	}
	public void setChk_no_caryear(String chk_no_caryear) {
		this.chk_no_caryear = chk_no_caryear;
	}

	//20170629 상품쪽 추가 사항 end


	public String getPath_fileCi() {
		return path_fileCi;
	}
	public void setPath_fileCi(String path_fileCi) {
		this.path_fileCi = path_fileCi;
	}
	public String getNm_fileCi() {
		return nm_fileCi;
	}
	public void setNm_fileCi(String nm_fileCi) {
		this.nm_fileCi = nm_fileCi;
	}
	//20160719 - 코스콤 상품관련 필터 조건 추가
	private int score_cb;  				//신용등급
	private double rto_cutoff_dti;  		//대출한도
	private String amt_limit_loan_min;  	//대출한도
	private String amt_limit_loan_max;  	//대출한도
	private String rto_loan_grade;  		//대출금리
	private int grade_pi;  				//PI등급 (채무건전성등급)
	private int grade_sp; 				//SP등급 (서브프라임 등급/대부등급)
	private int grade_dep;  				//DEP등급 (채무건전성 등급)
	private String method_repay   ; 		//상환방식
	private String method_mid_repay;  	//중도상환수수료공식
	private double rto_mid_repay;  	//중도상환수수료율

	private int rto_score_cb;  				//신용등급비율
	private int rto_grade_sp;  				//SP등급비율
	private int divisor_grade;  				//등급나눗수(제수)

	public int getDivisor_grade() {
		return divisor_grade;
	}
	public void setDivisor_grade(int divisor_grade) {
		this.divisor_grade = divisor_grade;
	}
	public int getRto_score_cb() {
		return rto_score_cb;
	}
	public void setRto_score_cb(int rto_score_cb) {
		this.rto_score_cb = rto_score_cb;
	}
	public int getRto_grade_sp() {
		return rto_grade_sp;
	}
	public void setRto_grade_sp(int rto_grade_sp) {
		this.rto_grade_sp = rto_grade_sp;
	}
	public double getRto_mid_repay() {
		return rto_mid_repay;
	}
	public void setRto_mid_repay(double rto_mid_repay) {
		this.rto_mid_repay = rto_mid_repay;
	}
	private int age_min; 					//나이
	private int age_max; 					//나이
	private String age_desc;  			//나이설명
	private String day_payment; 			//상환날짜
	private int job_term_month; 			//재직기간월
	private String amt_limit_income; 		//소득제한금액
	private double rto_year_income_debt;  //연소득채무비율

	private double rto_loan1 = 0;          //금리
	private double rto_loan2 = 0;
	private double rto_loan3 = 0;
	private double rto_loan4 = 0;
	private double rto_loan5 = 0;
	private double rto_loan6 = 0;
	private double rto_loan7 = 0;
	private double rto_loan8 = 0;
	private double rto_loan9 = 0;
	private double rto_loan10 = 0;

	private String no_prepare;          	//

	private int min_year_income;			//최저 연봉
	private int max_year_income;			//최대 연봉
	private int grade_year_income;		//연봉 등급
	private int grade_mue;				//mue 등급
	private double mue;					//mue값

	//2차필터용 상품정보
	private int cnt_exceed_2nd;				//	2차필터-1번: 기대출 과다자 (2금융권)	2금융권 5건			nonmonetary
	private int cnt_exceed_all_cnt_loan;		//	2차필터-2번: 기대출 과다자 (총대출건수)	 총대출건 8건(담보제외)
	private int cnt_exceed_saving_bank;		//	2차필터-3번: 기대출 과다자 (저축은행건수)	"저축은행 3건부결 	1건이 햇살론일 경우 승인"
	private int cnt_pay_guarantee;		//	지급보증인정건수

	private int grade_last6month;				//	2차필터-4번: 최근 6개월 신용등급 하락	6개월이내  8등급 있었으면 부결


	private String cd_chg_factor;	//신용등급변동표의 예측치를 위한 ....... 은행, 카드, 저축은행 구분코드

	//20160929 2차필터용 채움
	//상품마다 넣고싶으면 넣은듯 한데 특정 포맷이 없음!!!!!!!!!!!!!
	private int cnt_arrears_days_in1month;	//	2차필터-7번: 연체 이력 체크	1개월 이내 5일 이상 연체  ==> 5면 5일 , 10이면 10일이상 연체   ======> 연체일수 정보 없음!!!!!
	private int cnt_arrears_btw3monn6mon;	//	2차필터-8번: 연체 이력 체크	3~6개월 연체 이력 =====> 이필터 쓸건지 말건지

	private String yn_arrears_public = "N";	//	2차필터-9번: 연체 이력 체크	공공정보 연체기록(개인회생,신용회복,파산) ===========> 현재 불가-공공연체? -공공정보는 어디서 끌어오려하나??
	private String yn_arrears_tel = "N";		//	2차필터-10번: 통신연체 유무	통신연체기록 ===========> 현재 불가-통신? - 통신사는 어디서 끌어오려하나??

	private int cnt_lend;						//	2차필터-11번: 대부사용이력(건수)	2건이상 거절
	private String amt_lend;					//	2차필터-12번: 대부사용이력(금액)	1000만원 이상 거절

	private int cnt_recent_2nd_in6mon;			//	2차필터-13번: 최근 대출 이력(저축은행)	6개월이내 2금융권 대출 개설 2건 이상시 감액
	private int cnt_recent_all_in12mon;	//	2차필터-14번: 최근 대출 이력(전체 대출)	12개월내 주택담보제외 총 건수 6건이상 부결
	private int cnt_insameym;					//	2차필터-15번: 통대환 및 부분대환 이력자	동년 동월 대출 3건이상 발생하면 거절

	public int getCnt_arrears_days_in1month() {
		return cnt_arrears_days_in1month;
	}

	public void setCnt_arrears_days_in1month(int cnt_arrears_days_in1month) {
		this.cnt_arrears_days_in1month = cnt_arrears_days_in1month;
	}

	public int getCnt_arrears_btw3monn6mon() {
		return cnt_arrears_btw3monn6mon;
	}

	public void setCnt_arrears_btw3monn6mon(int cnt_arrears_btw3monn6mon) {
		this.cnt_arrears_btw3monn6mon = cnt_arrears_btw3monn6mon;
	}

	public int getCnt_recent_2nd_in6mon() {
		return cnt_recent_2nd_in6mon;
	}

	public void setCnt_recent_2nd_in6mon(int cnt_recent_2nd_in6mon) {
		this.cnt_recent_2nd_in6mon = cnt_recent_2nd_in6mon;
	}

	public int getCnt_recent_all_in12mon() {
		return cnt_recent_all_in12mon;
	}

	public void setCnt_recent_all_in12mon(int cnt_recent_all_in12mon) {
		this.cnt_recent_all_in12mon = cnt_recent_all_in12mon;
	}

	public int getCnt_insameym() {
		return cnt_insameym;
	}

	public void setCnt_insameym(int cnt_insameym) {
		this.cnt_insameym = cnt_insameym;
	}

	public String getYn_arrears_public() {
		return yn_arrears_public;
	}

	public void setYn_arrears_public(String yn_arrears_public) {
		this.yn_arrears_public = yn_arrears_public;
	}

	public String getYn_arrears_tel() {
		return yn_arrears_tel;
	}

	public void setYn_arrears_tel(String yn_arrears_tel) {
		this.yn_arrears_tel = yn_arrears_tel;
	}

	public int getCnt_lend() {
		return cnt_lend;
	}

	public void setCnt_lend(int cnt_lend) {
		this.cnt_lend = cnt_lend;
	}

	public String getAmt_lend() {
		return amt_lend;
	}

	public void setAmt_lend(String amt_lend) {
		this.amt_lend = amt_lend;
	}

	public String getCd_chg_factor() {
		return cd_chg_factor;
	}

	public void setCd_chg_factor(String cd_chg_factor) {
		this.cd_chg_factor = cd_chg_factor;
	}

	public int getMin_year_income() {
		return min_year_income;
	}

	public void setMin_year_income(int min_year_income) {
		this.min_year_income = min_year_income;
	}

	public int getMax_year_income() {
		return max_year_income;
	}

	public void setMax_year_income(int max_year_income) {
		this.max_year_income = max_year_income;
	}

	public int getGrade_year_income() {
		return grade_year_income;
	}

	public void setGrade_year_income(int grade_year_income) {
		this.grade_year_income = grade_year_income;
	}

	public int getGrade_mue() {
		return grade_mue;
	}

	public void setGrade_mue(int grade_mue) {
		this.grade_mue = grade_mue;
	}

	public double getMue() {
		return mue;
	}

	public void setMue(double mue) {
		this.mue = mue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

//	public String getCd_apply_comp() {
//		return cd_apply_comp;
//	}
//
//	public void setCd_apply_comp(String cd_apply_comp) {
//		this.cd_apply_comp = cd_apply_comp;
//	}

//	public String getCd_fc() {
//		return cd_fc;
//	}
//
//	public void setCd_fc(String cd_fc) {
//		this.cd_fc = cd_fc;
//	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public String getCd_goods_type() {
		return cd_goods_type;
	}

	public void setCd_goods_type(String cd_goods_type) {
		this.cd_goods_type = cd_goods_type;
	}

	public String getNm_goods() {
		return nm_goods;
	}

	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}

	public String getC20_goods_comp() {
		return c20_goods_comp;
	}

	public void setC20_goods_comp(String c20_goods_comp) {
		this.c20_goods_comp = c20_goods_comp;
	}

	public String getId_request() {
		return id_request;
	}

	public void setId_request(String id_request) {
		this.id_request = id_request;
	}

	public String getUrl_request() {
		return url_request;
	}

	public void setUrl_request(String url_request) {
		this.url_request = url_request;
	}

	public String getNm_request_form() {
		return nm_request_form;
	}

	public void setNm_request_form(String nm_request_form) {
		this.nm_request_form = nm_request_form;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getSeq_order() {
		return seq_order;
	}

	public void setSeq_order(String seq_order) {
		this.seq_order = seq_order;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getPath_file2() {
		return path_file2;
	}

	public void setPath_file2(String path_file2) {
		this.path_file2 = path_file2;
	}

	public String getNm_file2() {
		return nm_file2;
	}

	public void setNm_file2(String nm_file2) {
		this.nm_file2 = nm_file2;
	}

	public String getYn_first() {
		return yn_first;
	}

	public void setYn_first(String yn_first) {
		this.yn_first = yn_first;
	}

	public String getYn_check() {
		return yn_check;
	}

	public void setYn_check(String yn_check) {
		this.yn_check = yn_check;
	}

	public String getYn_use() {
		return yn_use;
	}

	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}

	public String getYn_zero_ratio() {
		return yn_zero_ratio;
	}
	public void setYn_zero_ratio(String yn_zero_ratio) {
		this.yn_zero_ratio = yn_zero_ratio;
	}

	public String getYn_auto() {
		return yn_auto;
	}

	public void setYn_auto(String yn_auto) {
		this.yn_auto = yn_auto;
	}

	public String getCd50_goods_job() {
		return cd50_goods_job;
	}

	public void setCd50_goods_job(String cd50_goods_job) {
		this.cd50_goods_job = cd50_goods_job;
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

	public int getScore_cb() {
		return score_cb;
	}

	public void setScore_cb(int score_cb) {
		this.score_cb = score_cb;
	}

	public double getRto_cutoff_dti() {
		return rto_cutoff_dti;
	}

	public void setRto_cutoff_dti(double rto_cutoff_dti) {
		this.rto_cutoff_dti = rto_cutoff_dti;
	}

	public String getAmt_limit_loan_min() {
		return amt_limit_loan_min;
	}

	public void setAmt_limit_loan_min(String amt_limit_loan_min) {
		this.amt_limit_loan_min = amt_limit_loan_min;
	}

	public String getAmt_limit_loan_max() {
		return amt_limit_loan_max;
	}

	public void setAmt_limit_loan_max(String amt_limit_loan_max) {
		this.amt_limit_loan_max = amt_limit_loan_max;
	}

	public String getRto_loan_grade() {
		return rto_loan_grade;
	}

	public void setRto_loan_grade(String rto_loan_grade) {
		this.rto_loan_grade = rto_loan_grade;
	}

	public int getGrade_pi() {
		return grade_pi;
	}

	public void setGrade_pi(int grade_pi) {
		this.grade_pi = grade_pi;
	}

	public int getGrade_sp() {
		return grade_sp;
	}

	public void setGrade_sp(int grade_sp) {
		this.grade_sp = grade_sp;
	}

	public int getGrade_dep() {
		return grade_dep;
	}

	public void setGrade_dep(int grade_dep) {
		this.grade_dep = grade_dep;
	}

	public String getMethod_repay() {
		return method_repay;
	}

	public void setMethod_repay(String method_repay) {
		this.method_repay = method_repay;
	}

	public String getMethod_mid_repay() {
		return method_mid_repay;
	}

	public void setMethod_mid_repay(String method_mid_repay) {
		this.method_mid_repay = method_mid_repay;
	}

	public int getAge_min() {
		return age_min;
	}

	public void setAge_min(int age_min) {
		this.age_min = age_min;
	}

	public int getAge_max() {
		return age_max;
	}

	public void setAge_max(int age_max) {
		this.age_max = age_max;
	}

	public String getAge_desc() {
		return age_desc;
	}

	public void setAge_desc(String age_desc) {
		this.age_desc = age_desc;
	}

	public String getDay_payment() {
		return day_payment;
	}

	public void setDay_payment(String day_payment) {
		this.day_payment = day_payment;
	}

	public int getJob_term_month() {
		return job_term_month;
	}

	public void setJob_term_month(int job_term_month) {
		this.job_term_month = job_term_month;
	}

	public String getCd_employ_type() {
		return cd_employ_type;
	}

	public void setCd_employ_type(String cd_employ_type) {
		this.cd_employ_type = cd_employ_type;
	}

	public String getAmt_limit_income() {
		return amt_limit_income;
	}

	public void setAmt_limit_income(String amt_limit_income) {
		this.amt_limit_income = amt_limit_income;
	}

	public double getRto_year_income_debt() {
		return rto_year_income_debt;
	}

	public void setRto_year_income_debt(double rto_year_income_debt) {
		this.rto_year_income_debt = rto_year_income_debt;
	}

	public double getRto_loan1() {
		return rto_loan1;
	}

	public void setRto_loan1(double rto_loan1) {
		this.rto_loan1 = rto_loan1;
	}

	public double getRto_loan2() {
		return rto_loan2;
	}

	public void setRto_loan2(double rto_loan2) {
		this.rto_loan2 = rto_loan2;
	}

	public double getRto_loan3() {
		return rto_loan3;
	}

	public void setRto_loan3(double rto_loan3) {
		this.rto_loan3 = rto_loan3;
	}

	public double getRto_loan4() {
		return rto_loan4;
	}

	public void setRto_loan4(double rto_loan4) {
		this.rto_loan4 = rto_loan4;
	}

	public double getRto_loan5() {
		return rto_loan5;
	}

	public void setRto_loan5(double rto_loan5) {
		this.rto_loan5 = rto_loan5;
	}

	public double getRto_loan6() {
		return rto_loan6;
	}

	public void setRto_loan6(double rto_loan6) {
		this.rto_loan6 = rto_loan6;
	}

	public double getRto_loan7() {
		return rto_loan7;
	}

	public void setRto_loan7(double rto_loan7) {
		this.rto_loan7 = rto_loan7;
	}

	public double getRto_loan8() {
		return rto_loan8;
	}

	public void setRto_loan8(double rto_loan8) {
		this.rto_loan8 = rto_loan8;
	}

	public double getRto_loan9() {
		return rto_loan9;
	}

	public void setRto_loan9(double rto_loan9) {
		this.rto_loan9 = rto_loan9;
	}

	public double getRto_loan10() {
		return rto_loan10;
	}

	public void setRto_loan10(double rto_loan10) {
		this.rto_loan10 = rto_loan10;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public int getCnt_exceed_2nd() {
		return cnt_exceed_2nd;
	}

	public void setCnt_exceed_2nd(int cnt_exceed_2nd) {
		this.cnt_exceed_2nd = cnt_exceed_2nd;
	}

	public int getCnt_exceed_all_cnt_loan() {
		return cnt_exceed_all_cnt_loan;
	}

	public void setCnt_exceed_all_cnt_loan(int cnt_exceed_all_cnt_loan) {
		this.cnt_exceed_all_cnt_loan = cnt_exceed_all_cnt_loan;
	}

	public int getCnt_exceed_saving_bank() {
		return cnt_exceed_saving_bank;
	}

	public void setCnt_exceed_saving_bank(int cnt_exceed_saving_bank) {
		this.cnt_exceed_saving_bank = cnt_exceed_saving_bank;
	}

	public int getGrade_last6month() {
		return grade_last6month;
	}

	public void setGrade_last6month(int grade_last6month) {
		this.grade_last6month = grade_last6month;
	}


	public int getCnt_pay_guarantee() {
		return cnt_pay_guarantee;
	}

	public void setCnt_pay_guarantee(int cnt_pay_guarantee) {
		this.cnt_pay_guarantee = cnt_pay_guarantee;
	}

	public int getCnt_reapply_month() {
		return cnt_reapply_month;
	}

	public void setCnt_reapply_month(int cnt_reapply_month) {
		this.cnt_reapply_month = cnt_reapply_month;
	}

	public String getCd_div_goods() {
		return cd_div_goods;
	}

	public void setCd_div_goods(String cd_div_goods) {
		this.cd_div_goods = cd_div_goods;
	}
	public String getDesc_repaymethod() {
		return desc_repaymethod;
	}
	public void setDesc_repaymethod(String desc_repaymethod) {
		this.desc_repaymethod = desc_repaymethod;
	}
	public String getDesc_commission() {
		return desc_commission;
	}
	public void setDesc_commission(String desc_commission) {
		this.desc_commission = desc_commission;
	}
	public String getDesc_inquiry() {
		return desc_inquiry;
	}
	public void setDesc_inquiry(String desc_inquiry) {
		this.desc_inquiry = desc_inquiry;
	}
	public String getDesc_feature() {
		return desc_feature;
	}
	public void setDesc_feature(String desc_feature) {
		this.desc_feature = desc_feature;
	}

	public String getKeyword_list() {
		return keyword_list;
	}

	public void setKeyword_list(String keyword_list) {
		this.keyword_list = keyword_list;
	}

	public String getCd_apply_type() {
		return cd_apply_type;
	}
	public void setCd_apply_type(String cd_apply_type) {
		this.cd_apply_type = cd_apply_type;
	}
	public String getCd_trade_type() {
		return cd_trade_type;
	}
	public void setCd_trade_type(String cd_trade_type) {
		this.cd_trade_type = cd_trade_type;
	}
	public String getCd_time_exec() {
		return cd_time_exec;
	}
	public void setCd_time_exec(String cd_time_exec) {
		this.cd_time_exec = cd_time_exec;
	}
	public String getCd_ratio_type() {
		return cd_ratio_type;
	}
	public void setCd_ratio_type(String cd_ratio_type) {
		this.cd_ratio_type = cd_ratio_type;
	}
	public String getCd_category_goods() {
		return cd_category_goods;
	}
	public void setCd_category_goods(String cd_category_goods) {
		this.cd_category_goods = cd_category_goods;
	}
	public String getCd_collateral_house_type() {
		return cd_collateral_house_type;
	}
	public void setCd_collateral_house_type(String cd_collateral_house_type) {
		this.cd_collateral_house_type = cd_collateral_house_type;
	}
	public String getCd_collateral_loan_use() {
		return cd_collateral_loan_use;
	}
	public void setCd_collateral_loan_use(String cd_collateral_loan_use) {
		this.cd_collateral_loan_use = cd_collateral_loan_use;
	}
	public String getCd_area() {
		return cd_area;
	}
	public void setCd_area(String cd_area) {
		this.cd_area = cd_area;
	}
	public String getCd_defer() {
		return cd_defer;
	}
	public void setCd_defer(String cd_defer) {
		this.cd_defer = cd_defer;
	}
	public String getCd_object() {
		return cd_object;
	}
	public void setCd_object(String cd_object) {
		this.cd_object = cd_object;
	}
	public String getYn_send_docu() {
		return yn_send_docu;
	}
	public void setYn_send_docu(String yn_send_docu) {
		this.yn_send_docu = yn_send_docu;
	}
	public String getYn_visit() {
		return yn_visit;
	}
	public void setYn_visit(String yn_visit) {
		this.yn_visit = yn_visit;
	}
	public String getYn_erly_rpay_fee() {
		return yn_erly_rpay_fee;
	}
	public void setYn_erly_rpay_fee(String yn_erly_rpay_fee) {
		this.yn_erly_rpay_fee = yn_erly_rpay_fee;
	}
	public String getYn_srch_ratio_limit() {
		return yn_srch_ratio_limit;
	}
	public void setYn_srch_ratio_limit(String yn_srch_ratio_limit) {
		this.yn_srch_ratio_limit = yn_srch_ratio_limit;
	}
	public String getYn_post_ranking() {
		return yn_post_ranking;
	}
	public void setYn_post_ranking(String yn_post_ranking) {
		this.yn_post_ranking = yn_post_ranking;
	}

	public String getNo_bunch() {
		return no_bunch;
	}

	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
	public String getFc_link() {
		return fc_link;
	}
	public void setFc_link(String fc_link) {
		this.fc_link = fc_link;
	}
	public String getDesc_contact() {
		return desc_contact;
	}
	public void setDesc_contact(String desc_contact) {
		this.desc_contact = desc_contact;
	}
	public String getDesc_etc() {
		return desc_etc;
	}
	public void setDesc_etc(String desc_etc) {
		this.desc_etc = desc_etc;
	}
	public String getDesc_overdue_interest() {
		return desc_overdue_interest;
	}
	public void setDesc_overdue_interest(String desc_overdue_interest) {
		this.desc_overdue_interest = desc_overdue_interest;
	}
	public String getDesc_loan_cost() {
		return desc_loan_cost;
	}
	public void setDesc_loan_cost(String desc_loan_cost) {
		this.desc_loan_cost = desc_loan_cost;
	}
	public String getTime_interest() {
		return time_interest;
	}
	public void setTime_interest(String time_interest) {
		this.time_interest = time_interest;
	}
}