package com.koscom.domain;

import java.io.Serializable;

public class ConditioncreditInfo implements Serializable{
	private static final long serialVersionUID = 4285781550177338772L;
	protected String no_person= "";	//회원고유번호
	protected String cd_apply_type_01= "";	//신청방식	인터넷
	protected String cd_apply_type_02= "";	//신청방식 모바일
	protected String cd_apply_type_03= "";	//신청방식 콜센터
	protected String cd_apply_type_04= "";	//신청방식 영업점
	protected String cd_trade_type_01= "";	//거래방식	건별대출
	protected String cd_trade_type_02= "";	//거래방식 마이너스한도대출
	protected String cd_type_pay_01= "";	//상환방식	만기일시상환
	protected String cd_type_pay_02= "";	//상환방식 원리금균등상환
	protected String cd_type_pay_03= "";	//상환방식 원금균등상환
	protected String cd_term_loan_01= "";	//대출기간	1년~5년
	protected String cd_term_loan_02= "";	//대출기간 5년이상
	protected String cd_term_loan_03= "";	//대출기간	1년~5년
	protected String cd_term_loan_04= "";	//대출기간 5년이상
	protected String cd_time_exec_01= "";	//실행시간	즉시대출
	protected String cd_time_exec_02= "";	//실행시간 당일대출
	protected String yn_send_docu= "";	//서류제출	무서류
	protected String yn_visit= "";	//방문여부	무방문
	protected String yn_erly_rpay_fee= "";	//중도상환수수료	없음
	protected String yn_srch_ratio_limit= "";	//금리/한도 조회	가능
	protected String cd_ratio_type_01= "";	//금리방식	고정금리
	protected String cd_ratio_type_02= "";	//금리방식 변동금리
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
	public String getCd_apply_type_01() {
		return cd_apply_type_01;
	}
	public void setCd_apply_type_01(String cd_apply_type_01) {
		this.cd_apply_type_01 = cd_apply_type_01;
	}
	public String getCd_apply_type_02() {
		return cd_apply_type_02;
	}
	public void setCd_apply_type_02(String cd_apply_type_02) {
		this.cd_apply_type_02 = cd_apply_type_02;
	}
	public String getCd_apply_type_03() {
		return cd_apply_type_03;
	}
	public void setCd_apply_type_03(String cd_apply_type_03) {
		this.cd_apply_type_03 = cd_apply_type_03;
	}
	public String getCd_apply_type_04() {
		return cd_apply_type_04;
	}
	public void setCd_apply_type_04(String cd_apply_type_04) {
		this.cd_apply_type_04 = cd_apply_type_04;
	}
	public String getCd_trade_type_01() {
		return cd_trade_type_01;
	}
	public void setCd_trade_type_01(String cd_trade_type_01) {
		this.cd_trade_type_01 = cd_trade_type_01;
	}
	public String getCd_trade_type_02() {
		return cd_trade_type_02;
	}
	public void setCd_trade_type_02(String cd_trade_type_02) {
		this.cd_trade_type_02 = cd_trade_type_02;
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
	public String getCd_time_exec_01() {
		return cd_time_exec_01;
	}
	public void setCd_time_exec_01(String cd_time_exec_01) {
		this.cd_time_exec_01 = cd_time_exec_01;
	}
	public String getCd_time_exec_02() {
		return cd_time_exec_02;
	}
	public void setCd_time_exec_02(String cd_time_exec_02) {
		this.cd_time_exec_02 = cd_time_exec_02;
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
