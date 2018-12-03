package com.koscom.batch.fss.domain;

import java.io.Serializable;
import java.util.List;

//import com.koscom.batch.fss.domain.FssMortgageLoanOptionVO;
//import com.koscom.batch.fss.domain.FssMortgageLoanProductVO;
import com.koscom.batch.fss.domain.FssMortgageLoanOptionVO;
import com.koscom.batch.fss.domain.FssMortgageLoanProductVO;

public class FssMortgageLoanInfo implements Serializable{
	private static final long serialVersionUID = 7017554532233445601L;
	private List<FssMortgageLoanProductVO> baseList;  //대출상품 리스트
	private List<FssMortgageLoanOptionVO> optionList;  //대출상품 리스트
	public List<FssMortgageLoanProductVO> getBaseList() {
		return baseList;
	}
	public void setBaseList(List<FssMortgageLoanProductVO> baseList) {
		this.baseList = baseList;
	}
	public List<FssMortgageLoanOptionVO> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<FssMortgageLoanOptionVO> optionList) {
		this.optionList = optionList;
	}
	protected String prdt_div= "";		//프로젝트분류
	protected String err_cd= "";		//응답코드
	protected String err_msg= "";		//응답메시지
	protected int total_count= 0;		//총 상품건수
	protected int max_page_no= 0;		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
	protected int now_page_no= 0;		//현재 조회 페이지 번호
	protected String id_frt= "";		//ADMIN
	protected String dt_frt= "";		//ADMIN
	protected String id_lst= "";		//ADMIN
	protected String dt_lst= "";		//ADMIN
	public String getPrdt_div() {
		return prdt_div;
	}
	public void setPrdt_div(String prdt_div) {
		this.prdt_div = prdt_div;
	}
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
	protected String dcls_month= "";		//공시 제출월 [YYYYMM]
	protected String fin_co_no= "";		//금융회사코드
	protected String fin_prdt_cd= "";		//금융상품코드
	protected String mrtg_type= "";		//담보유형 코드
	protected String mrtg_type_nm= "";		//담보유형
	protected String rpay_type= "";		//대출상환유형 코드
	protected String rpay_type_nm= "";		//대출상환유형
	protected String lend_rate_type= "";		//대출금리유형 코드
	protected String lend_rate_type_nm= "";		//대출금리유형
	protected String lend_rate_min= "";		//대출금리_최저 [소수점 2자리]
	protected String lend_rate_max= "";		//대출금리_최고 [소수점 2자리]
	protected String lend_rate_avg= "";		//전월 취급 평균금리 [소수점 2자리]
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
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getMrtg_type() {
		return mrtg_type;
	}
	public void setMrtg_type(String mrtg_type) {
		this.mrtg_type = mrtg_type;
	}
	public String getMrtg_type_nm() {
		return mrtg_type_nm;
	}
	public void setMrtg_type_nm(String mrtg_type_nm) {
		this.mrtg_type_nm = mrtg_type_nm;
	}
	public String getRpay_type() {
		return rpay_type;
	}
	public void setRpay_type(String rpay_type) {
		this.rpay_type = rpay_type;
	}
	public String getRpay_type_nm() {
		return rpay_type_nm;
	}
	public void setRpay_type_nm(String rpay_type_nm) {
		this.rpay_type_nm = rpay_type_nm;
	}
	public String getLend_rate_type() {
		return lend_rate_type;
	}
	public void setLend_rate_type(String lend_rate_type) {
		this.lend_rate_type = lend_rate_type;
	}
	public String getLend_rate_type_nm() {
		return lend_rate_type_nm;
	}
	public void setLend_rate_type_nm(String lend_rate_type_nm) {
		this.lend_rate_type_nm = lend_rate_type_nm;
	}
	public String getLend_rate_min() {
		return lend_rate_min;
	}
	public void setLend_rate_min(String lend_rate_min) {
		this.lend_rate_min = lend_rate_min;
	}
	public String getLend_rate_max() {
		return lend_rate_max;
	}
	public void setLend_rate_max(String lend_rate_max) {
		this.lend_rate_max = lend_rate_max;
	}
	public String getLend_rate_avg() {
		return lend_rate_avg;
	}
	public void setLend_rate_avg(String lend_rate_avg) {
		this.lend_rate_avg = lend_rate_avg;
	}
	protected String kor_co_nm= "";		//금융회사 명
	protected String fin_prdt_nm= "";		//금융상품명
	protected String join_way= "";		//가입방법
	protected String loan_inci_expn= "";		//대출 부대비용
	protected String erly_rpay_fee= "";		//중도상환 수수료
	protected String dly_rate= "";		//연체 이자율
	protected String loan_lmt= "";		//대출한도
	protected String dcls_strt_day= "";		//공시 시작일
	protected String dcls_end_day= "";		//공시 종료일
	protected String fin_co_subm_day= "";		//금융회사 제출일 [YYYYMMDDHH24MI]
	public String getKor_co_nm() {
		return kor_co_nm;
	}
	public void setKor_co_nm(String kor_co_nm) {
		this.kor_co_nm = kor_co_nm;
	}
	public String getFin_prdt_nm() {
		return fin_prdt_nm;
	}
	public void setFin_prdt_nm(String fin_prdt_nm) {
		this.fin_prdt_nm = fin_prdt_nm;
	}
	public String getJoin_way() {
		return join_way;
	}
	public void setJoin_way(String join_way) {
		this.join_way = join_way;
	}
	public String getLoan_inci_expn() {
		return loan_inci_expn;
	}
	public void setLoan_inci_expn(String loan_inci_expn) {
		this.loan_inci_expn = loan_inci_expn;
	}
	public String getErly_rpay_fee() {
		return erly_rpay_fee;
	}
	public void setErly_rpay_fee(String erly_rpay_fee) {
		this.erly_rpay_fee = erly_rpay_fee;
	}
	public String getDly_rate() {
		return dly_rate;
	}
	public void setDly_rate(String dly_rate) {
		this.dly_rate = dly_rate;
	}
	public String getLoan_lmt() {
		return loan_lmt;
	}
	public void setLoan_lmt(String loan_lmt) {
		this.loan_lmt = loan_lmt;
	}
	public String getDcls_strt_day() {
		return dcls_strt_day;
	}
	public void setDcls_strt_day(String dcls_strt_day) {
		this.dcls_strt_day = dcls_strt_day;
	}
	public String getDcls_end_day() {
		return dcls_end_day;
	}
	public void setDcls_end_day(String dcls_end_day) {
		this.dcls_end_day = dcls_end_day;
	}
	public String getFin_co_subm_day() {
		return fin_co_subm_day;
	}
	public void setFin_co_subm_day(String fin_co_subm_day) {
		this.fin_co_subm_day = fin_co_subm_day;
	}
}