package com.koscom.finset.model;

import java.io.Serializable;

import com.koscom.domain.FinsetInfo;

public class FinsetVO extends FinsetInfo implements Serializable {
	private static final long serialVersionUID = 107828774700056798L;
	public static final String NOT_CD_JOB_TO_APPLY = "접수가능한 직군이 아님";
	public static final String NOT_EMPLOLY_TYPE_TO_APPLY = "접수가능한 재직형태 아님";

	public static final String NOT_EXIST_PERSON_YEAR_INCOME = "개인 정보 불량-연봉정보없음";
//	public static final String NOT_EXIST_YEAR_INCOME = "개인정보 연소득 입력 없음-연소득비교불가";
	public static final String NOT_EXIST_PERSON_BGN = "개인정보 생년월일 입력 없음-나이비교불가";
	public static final String NOT_EXIST_PERSON_ENTER_DATE = "개인정보 입사일 입력 없음-재직기간비교불가";
	public static final String NOT_EXIST_PERSON_CD_JOB = "개인정보 직군 입력 없음-직군비교불가";
	public static final String NOT_EXIST_PERSON_EMPLOLY_TYPE = "개인정보 재직형태 입력 없음-재직형태비교불가";

	public static final String NOT_EXIST_NICE_CREDIT_GRADE = "나이스 정보 불량-신용등급정보없음";
	public static final String NOT_EXIST_NICE_AMT_CASH_SERVICE = "나이스 정보 불량-현금서비스 금액없음";
	public static final String NOT_EXIST_NICE_AMT_LEND = "나이스 정보 불량-대부서비스 금액없음";

	public static final String FAULTY_MUE = "상품 정보 불량-MUE셋팅정보이상";

	public static final String FAULTY_GOODS_RTO_LOAN = "상품 정보 불량-금리정보이상";
	public static final String FAULTY_GOODS_CUTOFF_DTI = "상품 정보 불량-cutoff DTI셋팅정보이상";
	public static final String FAULTY_GOODS_AMT_LIMIT_MIN = "상품 정보 불량-대출실행최저값 셋팅정보이상";

	public static final String LACK_MUE = "MUE 0이하";

	public static final String LACK_GOODS_RTO_LOAN = "금리 0이하";
	public static final String LACK_GOODS_CUTOFF_DTI = "CUTOFF DTI 0이하";
	public static final String LACK_GOODS_AMT_LIMIT_MIN = "대출실행최저값 0이하";

	public static final String LACK_NICE_CB = "신용등급 미달";
	public static final String LACK_NICE_PI = "PI등급 (채무건전성등급) 미달";
	public static final String LACK_NICE_SP = "SP등급 (서브프라임 등급/대부등급) 미달";
	public static final String LACK_NICE_DEP = "DEP등급 (채무건전성 등급) 미달";

	public static final String LACK_PERSON_JOB_TERM = "재직기간 미달";
	public static final String LACK_PERSON_YEAR_INCOME = "연소득 미달";
	public static final String LACK_PERSON_AGE = "나이 미달";
	public static final String EXCEED_PERSON_AGE = "나이 초과";

	public static final String EXCEED_NICE_CNT_CASH_SERVICE = "2차필터-현금서비스건수 초과";
	public static final String EXCEED_NICE_AMT_CASH_SERVICE = "2차필터-현금서비스금액 초과";
	public static final String EXCEED_NICE_CNT_LEND = "2차필터-현금서비스건수 초과";
	public static final String EXCEED_NICE_AMT_LEND = "2차필터-현금서비스금액 초과";

	public static final String NOT_EXIST_NICE_CB_GRADE_LAST_6_MONTH = "2차필터-최근 6개월 신용등급 자료없거나 미비-신용등급하락비교불가";
	public static final String DROP_NICE_CB_GRADE_LAST_6_MONTH = "2차필터-최근 6개월 신용등급 하락";

	public static final String NOT_EXIST_GOODS_INFO = "상품정보가 없음";
	public static final String NOT_EXIST_PERSON_INFO = "개인정보가 없음";
	public static final String NOT_EXIST_NICE_INFO = "NICE정보가 없음";
	public static final String NOT_EXIST_PREPARE_INFO = "사전심사정보가 없음";

	public static final String NOT_EXIST_MONTH_INCOME = "급여정보가 없음";
	public static final String NOT_EXIST_CB_GRADE = "신용등급 정보가 없음";
	public static final String NOT_SEARCH_BY_50 = "대출가능한 한도금액 범위를 벗어났습니다.";

	private String path_file1;		// 첨부파일1

	public String getPath_file1() {
		return path_file1;
	}
	public void setPath_file1(String path_file1) {
		this.path_file1 = path_file1;
	}

	//String no_person = "";
	//String no_prepare = "";
	//String cd_fc = "";
	//String cd_goods = "";
//	String id_frt = "";
//	String dt_frt = "";
//	String id_lst = "";
//	String dt_lst = "";
	int credit_grade = 0;
	/*
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	*/
	/*
	public String getNo_prepare() {
		return no_prepare;
	}
	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}
	*/
	/*
	public String getCd_goods() {
		return cd_goods;
	}
	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	*/
	/*
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
	*/
	public int getCredit_grade() {
		return credit_grade;
	}
	public void setCredit_grade(int credit_grade) {
		this.credit_grade = credit_grade;
	}
	/*
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	*/
}
