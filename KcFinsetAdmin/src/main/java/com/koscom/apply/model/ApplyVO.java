package com.koscom.apply.model;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.koscom.domain.ApplyInfo;
import com.koscom.util.StringUtil;

public class ApplyVO extends ApplyInfo implements Serializable {

	private static final long serialVersionUID = -5572610355561458646L;

	private String nm_person;			//이름
	private String bgn;					//생년월일
	private String hp;					//휴대폰번호
	private String cd_job_class;		//직업구분
	private String id_prepare;			//담당자
	private String id_agency_user;		//대리점 담당자
	private String memo_apply;			//메모
	private String cnt_apply_doc;
	private String cd_apply_doc_box_before;
	private String cd_prepare_doc_box;
	private String cd_agency_path;		//대리점 유입경로
	private String id_agency;			//매체사 id

	private String[] cd_goods_arr;
	private MultipartFile[] file;
	private MultipartFile[] file2;
	private String[] flg_file_arr;
	private String[] flg_file2_arr;
	private MultipartFile file_common;
	private String yn_commonFile;
	private String path_common_file;
	private String url_common_file;
	private String nm_common_file;
	private String url_attach_file1;
	private String url_attach_file2;
	private String url_attach_file3;

	private String[] cd_collect_path_arr;
	private String[] cd_contact_path_arr;
	private String[] cd_collect_method_arr;
	private String[] etc_memo_arr;
	private String[] nm_agency_arr;
	private String[] nm_ceo_agency_arr;
	private String[] url_homepage_agency_arr;
	private String[] nm_writer_arr;

	private String ymd_lst;
	private String his_lst;

	private String yn_agency;	//대리점 구분

	private String cd_chg_factor;	//대리점 구분
	private String nm_if;	//전문 번호?

	private String no_fc_req;
	private String all_year_term;
	private String all_rto_loan;
	private String all_amt_limit;
	private String min_dt_frt;

	private String deny_desc; // 거절 사유

	private String cd_loan_term;
	private String cd_type_pay;
	private String cd_type_interest;
	private String fc_telno;

	public String getDeny_desc() {
		return deny_desc;
	}

	public void setDeny_desc(String deny_desc) {
		this.deny_desc = deny_desc;
	}

	public String getNm_if() {
		return nm_if;
	}

	public void setNm_if(String nm_if) {
		this.nm_if = nm_if;
	}

	private HttpServletRequest request;

	/**
	 * 서류함 구분
	 * 0 00 가접수
	 * 0 01 접수대기
	 * 1 10 접수
       2 20 심사
     * 4 40 가승인
       5 50 승인
       6 60 지급
       7 70 거절
	   8 80 보류
       10 99 휴지통(이탈(취소))
	 */
	public static String CD_APPLY_DOC_BOX_00 = "00";
	public static String CD_APPLY_DOC_BOX_01 = "01";
	public static String CD_APPLY_DOC_BOX_10 = "10";
	public static String CD_APPLY_DOC_BOX_20 = "20";
	public static String CD_APPLY_DOC_BOX_30 = "30";
	public static String CD_APPLY_DOC_BOX_40 = "40";
	public static String CD_APPLY_DOC_BOX_50 = "50";
	public static String CD_APPLY_DOC_BOX_60 = "60";
	public static String CD_APPLY_DOC_BOX_70 = "70";
	public static String CD_APPLY_DOC_BOX_80 = "80";
	public static String CD_APPLY_DOC_BOX_99 = "99";

	public String getNm_person() {
		return nm_person;
	}

	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}

	public String getBgn() {
		return bgn;
	}

	public void setBgn(String bgn) {
		this.bgn = bgn;
	}

	public String getC1_gender() {
		return StringUtil.splitBgn(this.bgn, "G");
	}

	public String getYmd_birth() {
		return StringUtil.splitBgn(this.bgn, "B");
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCd_job_class() {
		return cd_job_class;
	}

	public void setCd_job_class(String cd_job_class) {
		this.cd_job_class = cd_job_class;
	}

	public String getId_prepare() {
		return id_prepare;
	}

	public String getId_agency_user() {
		return id_agency_user;
	}

	public void setId_agency_user(String id_agency_user) {
		this.id_agency_user = id_agency_user;
	}

	public void setId_prepare(String id_prepare) {
		this.id_prepare = id_prepare;
	}

	public String getCnt_apply_doc() {
		return cnt_apply_doc;
	}

	public void setCnt_apply_doc(String cnt_apply_doc) {
		this.cnt_apply_doc = cnt_apply_doc;
	}

	public String getCd_apply_doc_box_before() {
		return cd_apply_doc_box_before;
	}

	public void setCd_apply_doc_box_before(String cd_apply_doc_box_before) {
		this.cd_apply_doc_box_before = cd_apply_doc_box_before;
	}

	public String getCd_prepare_doc_box() {
		return cd_prepare_doc_box;
	}

	public void setCd_prepare_doc_box(String cd_prepare_doc_box) {
		this.cd_prepare_doc_box = cd_prepare_doc_box;
	}

	public String getCd_agency_path() {
		return cd_agency_path;
	}

	public void setCd_agency_path(String cd_agency_path) {
		this.cd_agency_path = cd_agency_path;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String[] getCd_goods_arr() {
		return cd_goods_arr;
	}

	public void setCd_goods_arr(String[] cd_goods_arr) {
		this.cd_goods_arr = cd_goods_arr;
	}

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}


	public MultipartFile[] getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile[] file2) {
		this.file2 = file2;
	}

	public String[] getFlg_file_arr() {
		return flg_file_arr;
	}

	public void setFlg_file_arr(String[] flg_file_arr) {
		this.flg_file_arr = flg_file_arr;
	}

	public String[] getFlg_file2_arr() {
		return flg_file2_arr;
	}

	public void setFlg_file2_arr(String[] flg_file2_arr) {
		this.flg_file2_arr = flg_file2_arr;
	}

	public MultipartFile getFile_common() {
		return file_common;
	}

	public void setFile_common(MultipartFile file_common) {
		this.file_common = file_common;
	}

	public String getYn_commonFile() {
		return yn_commonFile;
	}

	public void setYn_commonFile(String yn_commonFile) {
		this.yn_commonFile = yn_commonFile;
	}

	public String getPath_common_file() {
		return path_common_file;
	}

	public void setPath_common_file(String path_common_file) {
		this.path_common_file = path_common_file;
	}

	public String getUrl_common_file() {
		return url_common_file;
	}

	public void setUrl_common_file(String url_common_file) {
		this.url_common_file = url_common_file;
	}

	public String getNm_common_file() {
		return nm_common_file;
	}

	public void setNm_common_file(String nm_common_file) {
		this.nm_common_file = nm_common_file;
	}

	public String getMemo_apply() {
		return memo_apply;
	}

	public void setMemo_apply(String memo_apply) {
		this.memo_apply = memo_apply;
	}

	public String getUrl_attach_file1() {
		return url_attach_file1;
	}

	public void setUrl_attach_file1(String url_attach_file1) {
		this.url_attach_file1 = url_attach_file1;
	}

	public String getUrl_attach_file2() {
		return url_attach_file2;
	}

	public void setUrl_attach_file2(String url_attach_file2) {
		this.url_attach_file2 = url_attach_file2;
	}

	public String getUrl_attach_file3() {
		return url_attach_file3;
	}

	public void setUrl_attach_file3(String url_attach_file3) {
		this.url_attach_file3 = url_attach_file3;
	}

	public String[] getCd_collect_path_arr() {
		return cd_collect_path_arr;
	}

	public void setCd_collect_path_arr(String[] cd_collect_path_arr) {
		this.cd_collect_path_arr = cd_collect_path_arr;
	}

	public String[] getCd_contact_path_arr() {
		return cd_contact_path_arr;
	}

	public void setCd_contact_path_arr(String[] cd_contact_path_arr) {
		this.cd_contact_path_arr = cd_contact_path_arr;
	}

	public String[] getCd_collect_method_arr() {
		return cd_collect_method_arr;
	}

	public void setCd_collect_method_arr(String[] cd_collect_method_arr) {
		this.cd_collect_method_arr = cd_collect_method_arr;
	}

	public String[] getEtc_memo_arr() {
		return etc_memo_arr;
	}

	public void setEtc_memo_arr(String[] etc_memo_arr) {
		this.etc_memo_arr = etc_memo_arr;
	}

	public String[] getNm_agency_arr() {
		return nm_agency_arr;
	}

	public void setNm_agency_arr(String[] nm_agency_arr) {
		this.nm_agency_arr = nm_agency_arr;
	}

	public String[] getNm_ceo_agency_arr() {
		return nm_ceo_agency_arr;
	}

	public void setNm_ceo_agency_arr(String[] nm_ceo_agency_arr) {
		this.nm_ceo_agency_arr = nm_ceo_agency_arr;
	}

	public String[] getUrl_homepage_agency_arr() {
		return url_homepage_agency_arr;
	}

	public void setUrl_homepage_agency_arr(String[] url_homepage_agency_arr) {
		this.url_homepage_agency_arr = url_homepage_agency_arr;
	}

	public String[] getNm_writer_arr() {
		return nm_writer_arr;
	}

	public void setNm_writer_arr(String[] nm_writer_arr) {
		this.nm_writer_arr = nm_writer_arr;
	}

	public String getYmd_lst() {
		return ymd_lst;
	}

	public void setYmd_lst(String ymd_lst) {
		this.ymd_lst = ymd_lst;
	}

	public String getHis_lst() {
		return his_lst;
	}

	public void setHis_lst(String his_lst) {
		this.his_lst = his_lst;
	}

	public String getYn_agency() {
		return yn_agency;
	}

	public void setYn_agency(String yn_agency) {
		this.yn_agency = yn_agency;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected String cd_goods;			//UNIQUE INDEX
	protected String cd_goods_type;		//
	protected String nm_goods;			//
	protected String c20_goods_comp;	//
	protected String id_request;		//
	protected String url_request;		//
	protected String nm_request_form;	//
	protected String etc;				//
	protected String seq_order;			//
	protected String summary;			// 요약설명
	protected String content;			// 상품설명
	protected String path_file1;		// 첨부파일1
	protected String nm_file1;			// 첨부파일1이름
	protected String path_file2;		// 첨부파일2
	protected String nm_file2;			// 첨부파일2이름
	//20160819 - CI로고 올리기
	protected String path_fileCi;		// CI파일
	protected String nm_fileCi;			// CI파일이름
	protected String yn_first;			// 선순위 여부
	protected String yn_check;			// 선인증 여부
	protected String yn_use;			//
	protected String yn_auto;			// 자동접수 여부
	protected String cd50_goods_job;	// 상품 직군

	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;

	//20160719 - 코스콤 상품관련 필터 조건 추가
 	protected int score_cb;  				//신용등급
 	protected double rto_cutoff_dti;  		//대출한도
 	protected String amt_limit_loan_min;  	//대출한도
 	protected String amt_limit_loan_max;  	//대출한도
 	protected String rto_loan_grade;  		//대출금리
	protected int grade_pi;  				//PI등급 (채무건전성등급)
	protected int grade_sp; 				//SP등급 (서브프라임 등급/대부등급)
	protected int grade_dep;  				//DEP등급 (채무건전성 등급)
	protected String method_repay   ; 		//상환방식
	protected String method_mid_repay;  	//중도상환수수료공식
	protected int age_min; 					//나이
	protected int age_max; 					//나이
	protected String age_desc;  			//나이설명
	protected String day_payment; 			//상환날짜
	protected int job_term_month; 			//재직기간월
	protected String cd_employ_type; 		//재직형태
	protected String amt_limit_income; 		//소득제한금액
	protected double rto_year_income_debt;  //연소득채무비율

	protected double rto_loan1 = 0;          //금리
	protected double rto_loan2 = 0;
	protected double rto_loan3 = 0;
	protected double rto_loan4 = 0;
	protected double rto_loan5 = 0;
	protected double rto_loan6 = 0;
	protected double rto_loan7 = 0;
	protected double rto_loan8 = 0;
	protected double rto_loan9 = 0;
	protected double rto_loan10 = 0;

	protected String no_prepare;          	//

	protected int min_year_income;			//최저 연봉
	protected int max_year_income;			//최대 연봉
	protected int grade_year_income;		//연봉 등급
	protected int grade_mue;				//mue 등급
	protected double mue;					//mue값

	//2차필터용 상품정보
	protected int cnt_exceed_2nd;				//	기대출 과다자 (2금융권)	2금융권 5건			nonmonetary
	protected int cnt_exceed_all_cnt_loan;		//	기대출 과다자 (총대출건수)	 총대출건 8건(담보제외)
	protected int cnt_exceed_saving_bank;				//	기대출 과다자 (저축은행건수)	"저축은행 3건부결 	1건이 햇살론일 경우 승인"

	protected int grade_last6month;				//	최근 6개월 신용등급 하락	6개월이내  8등급 있었으면 부결

	protected int cnt_cash_service;				//	현금서비스 건수	5건
	protected String amt_cash_service;				//	현금서비스 금액	15,000

	private Double rto_interest_from; 	//대출금리
	private Double rto_interest_to; 	//대출금리

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

	public int getCnt_cash_service() {
		return cnt_cash_service;
	}

	public void setCnt_cash_service(int cnt_cash_service) {
		this.cnt_cash_service = cnt_cash_service;
	}

	public String getAmt_cash_service() {
		return amt_cash_service;
	}

	public void setAmt_cash_service(String amt_cash_service) {
		this.amt_cash_service = amt_cash_service;
	}

	public String getCd_chg_factor() {
		return cd_chg_factor;
	}

	public void setCd_chg_factor(String cd_chg_factor) {
		this.cd_chg_factor = cd_chg_factor;
	}

	public String getNo_fc_req() {
		return no_fc_req;
	}

	public String getAll_year_term() {
		return all_year_term;
	}

	public String getAll_rto_loan() {
		return all_rto_loan;
	}

	public String getAll_amt_limit() {
		return all_amt_limit;
	}

	public void setNo_fc_req(String no_fc_req) {
		this.no_fc_req = no_fc_req;
	}

	public void setAll_year_term(String all_year_term) {
		this.all_year_term = all_year_term;
	}

	public void setAll_rto_loan(String all_rto_loan) {
		this.all_rto_loan = all_rto_loan;
	}

	public void setAll_amt_limit(String all_amt_limit) {
		this.all_amt_limit = all_amt_limit;
	}

	public String getMin_dt_frt() {
		return min_dt_frt;
	}

	public void setMin_dt_frt(String min_dt_frt) {
		this.min_dt_frt = min_dt_frt;
	}

	public String getCd_loan_term() {
		return cd_loan_term;
	}

	public void setCd_loan_term(String cd_loan_term) {
		this.cd_loan_term = cd_loan_term;
	}

	public String getCd_type_pay() {
		return cd_type_pay;
	}

	public void setCd_type_pay(String cd_type_pay) {
		this.cd_type_pay = cd_type_pay;
	}

	public String getCd_type_interest() {
		return cd_type_interest;
	}

	public void setCd_type_interest(String cd_type_interest) {
		this.cd_type_interest = cd_type_interest;
	}

	public String getFc_telno() {
		return fc_telno;
	}

	public void setFc_telno(String fc_telno) {
		this.fc_telno = fc_telno;
	}
}
