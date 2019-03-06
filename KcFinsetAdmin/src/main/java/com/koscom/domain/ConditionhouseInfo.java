package com.koscom.domain;

import java.io.Serializable;

public class ConditionhouseInfo implements Serializable{
	private static final long serialVersionUID = -1055143960227793160L;
	protected String no_person= "";	//회원고유번호
	protected String cd_collateral_house_type_01= "";	//주택종류	아파트
	protected String cd_collateral_house_type_02= "";	//주택종류 오피스텔
	protected String cd_collateral_house_type_03= "";	//주택종류 주택/연립/빌라
	protected String cd_collateral_loan_use_01= "";	//자금용도	신규주택구입
	protected String cd_collateral_loan_use_02= "";	//자금용도 기대출상환
	protected String cd_collateral_loan_use_03= "";	//자금용도 자금조달
	protected String cd_area_01= "";	//주택면적(전용면적)	85m2 미만
	protected String cd_area_02= "";	//주택면적(전용면적) 85m2~125m2
	protected String cd_area_03= "";	//주택면적(전용면적) 125m2초과
	protected String cd_ratio_type_01= "";	//금리방식	고정금리
	protected String cd_ratio_type_02= "";	//금리방식 변동금리
	protected String cd_ratio_type_03= "";	//금리방식 변동금리
	protected String cd_term_loan_01= "";	//대출기간	~1년
	protected String cd_term_loan_02= "";	//대출기간 2~10년
	protected String cd_term_loan_03= "";	//대출기간 10~30년
	protected String cd_term_loan_04= "";	//대출기간 31년~
	protected String cd_type_pay_01= "";	//상환방식	원리금분할상환
	protected String cd_type_pay_02= "";	//상환방식 원금분할상환
	protected String cd_type_pay_03= "";	//상환방식 만기일시상환
	protected String cd_defer_01= "";	//거치방식	거치형
	protected String cd_defer_02= "";	//거치방식 비거치형
	protected String cd_object_01= "";	//대상구분	개인
	protected String cd_object_02= "";	//대상구분 개인사업자
	protected String yn_post_ranking= "";	//후순위가능여부	후순위 가능
	protected String id_frt= "";	//admin
	protected String dt_frt= "";	//admin
	protected String id_lst= "";	//admin
	protected String dt_lst= "";	//admin
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_collateral_house_type_01() {
		return cd_collateral_house_type_01;
	}
	public void setCd_collateral_house_type_01(String cd_collateral_house_type_01) {
		this.cd_collateral_house_type_01 = cd_collateral_house_type_01;
	}
	public String getCd_collateral_house_type_02() {
		return cd_collateral_house_type_02;
	}
	public void setCd_collateral_house_type_02(String cd_collateral_house_type_02) {
		this.cd_collateral_house_type_02 = cd_collateral_house_type_02;
	}
	public String getCd_collateral_house_type_03() {
		return cd_collateral_house_type_03;
	}
	public void setCd_collateral_house_type_03(String cd_collateral_house_type_03) {
		this.cd_collateral_house_type_03 = cd_collateral_house_type_03;
	}
	public String getCd_collateral_loan_use_01() {
		return cd_collateral_loan_use_01;
	}
	public void setCd_collateral_loan_use_01(String cd_collateral_loan_use_01) {
		this.cd_collateral_loan_use_01 = cd_collateral_loan_use_01;
	}
	public String getCd_collateral_loan_use_02() {
		return cd_collateral_loan_use_02;
	}
	public void setCd_collateral_loan_use_02(String cd_collateral_loan_use_02) {
		this.cd_collateral_loan_use_02 = cd_collateral_loan_use_02;
	}
	public String getCd_collateral_loan_use_03() {
		return cd_collateral_loan_use_03;
	}
	public void setCd_collateral_loan_use_03(String cd_collateral_loan_use_03) {
		this.cd_collateral_loan_use_03 = cd_collateral_loan_use_03;
	}
	public String getCd_area_01() {
		return cd_area_01;
	}
	public void setCd_area_01(String cd_area_01) {
		this.cd_area_01 = cd_area_01;
	}
	public String getCd_area_02() {
		return cd_area_02;
	}
	public void setCd_area_02(String cd_area_02) {
		this.cd_area_02 = cd_area_02;
	}
	public String getCd_area_03() {
		return cd_area_03;
	}
	public void setCd_area_03(String cd_area_03) {
		this.cd_area_03 = cd_area_03;
	}
	public String getCd_ratio_type_01() {
		return cd_ratio_type_01;
	}
	public void setCd_ratio_type_01(String cd_ratio_type_01) {
		this.cd_ratio_type_01 = cd_ratio_type_01;
	}
	public String getCd_ratio_type_02() {
		return cd_ratio_type_02;
	}
	public void setCd_ratio_type_02(String cd_ratio_type_02) {
		this.cd_ratio_type_02 = cd_ratio_type_02;
	}
	public String getCd_ratio_type_03() {
		return cd_ratio_type_03;
	}
	public void setCd_ratio_type_03(String cd_ratio_type_03) {
		this.cd_ratio_type_03 = cd_ratio_type_03;
	}
	public String getCd_term_loan_01() {
		return cd_term_loan_01;
	}
	public void setCd_term_loan_01(String cd_term_loan_01) {
		this.cd_term_loan_01 = cd_term_loan_01;
	}
	public String getCd_term_loan_02() {
		return cd_term_loan_02;
	}
	public void setCd_term_loan_02(String cd_term_loan_02) {
		this.cd_term_loan_02 = cd_term_loan_02;
	}
	public String getCd_term_loan_03() {
		return cd_term_loan_03;
	}
	public void setCd_term_loan_03(String cd_term_loan_03) {
		this.cd_term_loan_03 = cd_term_loan_03;
	}
	public String getCd_term_loan_04() {
		return cd_term_loan_04;
	}
	public void setCd_term_loan_04(String cd_term_loan_04) {
		this.cd_term_loan_04 = cd_term_loan_04;
	}
	public String getCd_type_pay_01() {
		return cd_type_pay_01;
	}
	public void setCd_type_pay_01(String cd_type_pay_01) {
		this.cd_type_pay_01 = cd_type_pay_01;
	}
	public String getCd_type_pay_02() {
		return cd_type_pay_02;
	}
	public void setCd_type_pay_02(String cd_type_pay_02) {
		this.cd_type_pay_02 = cd_type_pay_02;
	}
	public String getCd_type_pay_03() {
		return cd_type_pay_03;
	}
	public void setCd_type_pay_03(String cd_type_pay_03) {
		this.cd_type_pay_03 = cd_type_pay_03;
	}
	public String getCd_defer_01() {
		return cd_defer_01;
	}
	public void setCd_defer_01(String cd_defer_01) {
		this.cd_defer_01 = cd_defer_01;
	}
	public String getCd_defer_02() {
		return cd_defer_02;
	}
	public void setCd_defer_02(String cd_defer_02) {
		this.cd_defer_02 = cd_defer_02;
	}
	public String getCd_object_01() {
		return cd_object_01;
	}
	public void setCd_object_01(String cd_object_01) {
		this.cd_object_01 = cd_object_01;
	}
	public String getCd_object_02() {
		return cd_object_02;
	}
	public void setCd_object_02(String cd_object_02) {
		this.cd_object_02 = cd_object_02;
	}
	public String getYn_post_ranking() {
		return yn_post_ranking;
	}
	public void setYn_post_ranking(String yn_post_ranking) {
		this.yn_post_ranking = yn_post_ranking;
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
