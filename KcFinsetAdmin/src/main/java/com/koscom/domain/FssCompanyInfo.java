package com.koscom.domain;

import java.io.Serializable;
import java.util.List;

import com.koscom.fss.model.FssCompanyOptionVO;
import com.koscom.fss.model.FssCompanyProductVO;
import com.koscom.fss.model.FssCompanyResultVO;

public class FssCompanyInfo implements Serializable{
	private static final long serialVersionUID = 1669841558243921486L;
	private List<FssCompanyProductVO> baseList;  //대출상품 리스트
	private List<FssCompanyOptionVO> optionList;  //대출상품 리스트
	public List<FssCompanyProductVO> getBaseList() {
		return baseList;
	}
	public void setBaseList(List<FssCompanyProductVO> baseList) {
		this.baseList = baseList;
	}
	public List<FssCompanyOptionVO> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<FssCompanyOptionVO> optionList) {
		this.optionList = optionList;
	}
	protected String err_cd= "";		//응답코드
	protected String err_msg= "";		//응답메시지
	protected int total_count= 0;		//총 상품건수
	protected int max_page_no= 0;		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
	protected int now_page_no= 0;		//현재 조회 페이지 번호
	protected String id_frt= "";		//ADMIN
	protected String dt_frt= "";		//ADMIN
	protected String id_lst= "";		//ADMIN
	protected String dt_lst= "";		//ADMIN
	public String getErr_cd() {
		return err_cd;
	}
	public void setErr_cd(String err_cd) {
		this.err_cd = err_cd;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public int getMax_page_no() {
		return max_page_no;
	}
	public void setMax_page_no(int max_page_no) {
		this.max_page_no = max_page_no;
	}
	public int getNow_page_no() {
		return now_page_no;
	}
	public void setNow_page_no(int now_page_no) {
		this.now_page_no = now_page_no;
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
	protected String dcls_month= "";		//**공시 제출월 [YYYYMM]
	protected String fin_co_no= "";		//금융회사코드
	protected String kor_co_nm= "";		//금융회사 명
	protected String dcls_chrg_man= "";		//공시담당자
	protected String homp_url= "";		//홈페이지주소
	protected String cal_tel= "";		//콜센터전화번호
	public String getDcls_month() {
		return dcls_month;
	}
	public void setDcls_month(String dcls_month) {
		this.dcls_month = dcls_month;
	}
	public String getFin_co_no() {
		return fin_co_no;
	}
	public void setFin_co_no(String fin_co_no) {
		this.fin_co_no = fin_co_no;
	}
	public String getKor_co_nm() {
		return kor_co_nm;
	}
	public void setKor_co_nm(String kor_co_nm) {
		this.kor_co_nm = kor_co_nm;
	}
	public String getDcls_chrg_man() {
		return dcls_chrg_man;
	}
	public void setDcls_chrg_man(String dcls_chrg_man) {
		this.dcls_chrg_man = dcls_chrg_man;
	}
	public String getHomp_url() {
		return homp_url;
	}
	public void setHomp_url(String homp_url) {
		this.homp_url = homp_url;
	}
	public String getCal_tel() {
		return cal_tel;
	}
	public void setCal_tel(String cal_tel) {
		this.cal_tel = cal_tel;
	}
	protected String fin_prdt_cd= "";		//금융상품코드
	protected String crdt_lend_rate_type= "";		//금리구분 코드
	protected String crdt_lend_rate_type_nm= "";		//금리구분
	protected double crdt_grad_1;		//은행: 1~2 등급 [소수점 2자리] 비은행: 1~3 등급 [소수점 2자리]
	protected double crdt_grad_4;		//은행: 3~4 등급 [소수점 2자리] 비은행: 4 등급 [소수점 2자리]
	protected double crdt_grad_5;		//은행: 5~6 등급 [소수점 2자리] 비은행: 5 등급 [소수점 2자리]
	protected double crdt_grad_6;		//은행: 7~8 등급 [소수점 2자리] 비은행: 6 등급 [소수점 2자리]
	protected double crdt_grad_10;		//은행: 9~10 등급 [소수점 2자리] 비은행: 7~10 등급 [소수점 2자리]
	protected double crdt_grad_avg;		//평균 금리 [소수점 2자리]
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getCrdt_lend_rate_type() {
		return crdt_lend_rate_type;
	}
	public void setCrdt_lend_rate_type(String crdt_lend_rate_type) {
		this.crdt_lend_rate_type = crdt_lend_rate_type;
	}
	public String getCrdt_lend_rate_type_nm() {
		return crdt_lend_rate_type_nm;
	}
	public void setCrdt_lend_rate_type_nm(String crdt_lend_rate_type_nm) {
		this.crdt_lend_rate_type_nm = crdt_lend_rate_type_nm;
	}
	public double getCrdt_grad_1() {
		return crdt_grad_1;
	}
	public void setCrdt_grad_1(double crdt_grad_1) {
		this.crdt_grad_1 = crdt_grad_1;
	}
	public double getCrdt_grad_4() {
		return crdt_grad_4;
	}
	public void setCrdt_grad_4(double crdt_grad_4) {
		this.crdt_grad_4 = crdt_grad_4;
	}
	public double getCrdt_grad_5() {
		return crdt_grad_5;
	}
	public void setCrdt_grad_5(double crdt_grad_5) {
		this.crdt_grad_5 = crdt_grad_5;
	}
	public double getCrdt_grad_6() {
		return crdt_grad_6;
	}
	public void setCrdt_grad_6(double crdt_grad_6) {
		this.crdt_grad_6 = crdt_grad_6;
	}
	public double getCrdt_grad_10() {
		return crdt_grad_10;
	}
	public void setCrdt_grad_10(double crdt_grad_10) {
		this.crdt_grad_10 = crdt_grad_10;
	}
	public double getCrdt_grad_avg() {
		return crdt_grad_avg;
	}
	public void setCrdt_grad_avg(double crdt_grad_avg) {
		this.crdt_grad_avg = crdt_grad_avg;
	}
}