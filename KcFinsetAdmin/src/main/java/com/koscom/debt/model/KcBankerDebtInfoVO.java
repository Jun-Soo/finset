package com.koscom.debt.model;

import java.io.Serializable;

public class KcBankerDebtInfoVO implements Serializable{

	private static final long serialVersionUID = 6322443702391660083L;
	
	private String no_person= "";			    //회원관리번호
	private String no_manage_info= "";	    //정보관리번호
	private String nm_biz_type= "";		    //업권명[ex은행]
	private String nm_biz= "";			    //금융기관명[ex현대저축은행]
	private String cd_fc= "";			    //은행코드
	private String goods_code= "";		    //상품코드
	private String update_date= "";		    //업데이트일자
	private String account_no= "";		    //계좌번호
	private String interest_fixed_yn= "";	    //금리방식
	private String interest= "";			    //고정금리
	private String repayment= "";			    //상환방식	 	1:원리금균등상환, 2:원금균등상환
	private String cd_type_deal= "";		    //거래형태코드	1:분할상환, 2:일시상환, 3:한도대출, 4:장기카드대출(카드론)분할상환, 5:장기카드대출(카드론)일시상환, 6:장기카드대출(카드론)한도대출, A:리스
	private String cd_use_fund= "";		    //자금용도코드
	private String yn_credit= "";			    //신용여부
	private String yn_loan= "";			    //담보여부
	private String yn_guarantor= "";		    //보증인여부
	private String amt_contract= "";		    //약정금액
	private String amt_remain= "";		    //대출잔액
	private String loan_mount= "";		    //거치기간
	private String duration_load= "";		    //잔여기간
	private String amt_total_pni= "";		    //총원리금
	private String amt_total_p= "";		    //총원금
	private String amt_total_i= "";		    //총이자
	private String amt_repay_pni= "";		    //누적상환원리금
	private String amt_repay_p= "";		    //누적상환원금
	private String amt_repay_i= "";		    //누적상환이자
	private String amt_remain_pni= "";	    //잔여원리금
	private String amt_remain_p= "";		    //잔여원금
	private String amt_remain_i= "";		    //잔여이자
	private String rto_pr= "";			    //원금상환비율
	private String amt_repay_pni_year= "";    //원리금상환_년
	private String amt_repay_p_year= "";	    //원금상환_년
	private String amt_repay_i_year= "";	    //이자상환_년
	private String ymd_loan= "";			    //대출일자
	private String inter_pay_day= "";		    //결제일자
	private String ymd_cancel= "";		    //해지일자
	private String ymd_loanend= "";		    //만기일자
	private String cd_cancel= "";			    //해지사유코드
	private String cd_group_loans_proxy= "";	//집단대출대납구분코드
	private String yn_delay_turn_loan= "";	//연체대환대출여부
	private String yn_recovery= "";			//신용회복지원여부
	private String moderate_fee= "";			//중도상환수수료
	private String id_frt= "";				//최초입력아이디
	private String dt_frt= "";				//최초입력시간
	private String id_lst= "";				//최초입력아이디
	private String dt_lst= "";				//최초입력시간
	private String amt_kcb_repay= "";			//KCB누적상환원리금
	private String amt_kcb_delay= "";			//KCB누적연체금
	private String nm_person="";				//고객명
	private String repayment_nm="";			//상환방식명
	private String deal_nm="";				//거래형태코드명
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getNo_manage_info() {
		return no_manage_info;
	}
	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
	}
	public String getNm_biz_type() {
		return nm_biz_type;
	}
	public void setNm_biz_type(String nm_biz_type) {
		this.nm_biz_type = nm_biz_type;
	}
	public String getNm_biz() {
		return nm_biz;
	}
	public void setNm_biz(String nm_biz) {
		this.nm_biz = nm_biz;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getInterest_fixed_yn() {
		return interest_fixed_yn;
	}
	public void setInterest_fixed_yn(String interest_fixed_yn) {
		this.interest_fixed_yn = interest_fixed_yn;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getRepayment() {
		return repayment;
	}
	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}
	public String getCd_type_deal() {
		return cd_type_deal;
	}
	public void setCd_type_deal(String cd_type_deal) {
		this.cd_type_deal = cd_type_deal;
	}
	public String getCd_use_fund() {
		return cd_use_fund;
	}
	public void setCd_use_fund(String cd_use_fund) {
		this.cd_use_fund = cd_use_fund;
	}
	public String getYn_credit() {
		return yn_credit;
	}
	public void setYn_credit(String yn_credit) {
		this.yn_credit = yn_credit;
	}
	public String getYn_loan() {
		return yn_loan;
	}
	public void setYn_loan(String yn_loan) {
		this.yn_loan = yn_loan;
	}
	public String getYn_guarantor() {
		return yn_guarantor;
	}
	public void setYn_guarantor(String yn_guarantor) {
		this.yn_guarantor = yn_guarantor;
	}
	public String getAmt_contract() {
		return amt_contract;
	}
	public void setAmt_contract(String amt_contract) {
		this.amt_contract = amt_contract;
	}
	public String getAmt_remain() {
		return amt_remain;
	}
	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}
	public String getLoan_mount() {
		return loan_mount;
	}
	public void setLoan_mount(String loan_mount) {
		this.loan_mount = loan_mount;
	}
	public String getDuration_load() {
		return duration_load;
	}
	public void setDuration_load(String duration_load) {
		this.duration_load = duration_load;
	}
	public String getAmt_total_pni() {
		return amt_total_pni;
	}
	public void setAmt_total_pni(String amt_total_pni) {
		this.amt_total_pni = amt_total_pni;
	}
	public String getAmt_total_p() {
		return amt_total_p;
	}
	public void setAmt_total_p(String amt_total_p) {
		this.amt_total_p = amt_total_p;
	}
	public String getAmt_total_i() {
		return amt_total_i;
	}
	public void setAmt_total_i(String amt_total_i) {
		this.amt_total_i = amt_total_i;
	}
	public String getAmt_repay_pni() {
		return amt_repay_pni;
	}
	public void setAmt_repay_pni(String amt_repay_pni) {
		this.amt_repay_pni = amt_repay_pni;
	}
	public String getAmt_repay_p() {
		return amt_repay_p;
	}
	public void setAmt_repay_p(String amt_repay_p) {
		this.amt_repay_p = amt_repay_p;
	}
	public String getAmt_repay_i() {
		return amt_repay_i;
	}
	public void setAmt_repay_i(String amt_repay_i) {
		this.amt_repay_i = amt_repay_i;
	}
	public String getAmt_remain_pni() {
		return amt_remain_pni;
	}
	public void setAmt_remain_pni(String amt_remain_pni) {
		this.amt_remain_pni = amt_remain_pni;
	}
	public String getAmt_remain_p() {
		return amt_remain_p;
	}
	public void setAmt_remain_p(String amt_remain_p) {
		this.amt_remain_p = amt_remain_p;
	}
	public String getAmt_remain_i() {
		return amt_remain_i;
	}
	public void setAmt_remain_i(String amt_remain_i) {
		this.amt_remain_i = amt_remain_i;
	}
	public String getRto_pr() {
		return rto_pr;
	}
	public void setRto_pr(String rto_pr) {
		this.rto_pr = rto_pr;
	}
	public String getAmt_repay_pni_year() {
		return amt_repay_pni_year;
	}
	public void setAmt_repay_pni_year(String amt_repay_pni_year) {
		this.amt_repay_pni_year = amt_repay_pni_year;
	}
	public String getAmt_repay_p_year() {
		return amt_repay_p_year;
	}
	public void setAmt_repay_p_year(String amt_repay_p_year) {
		this.amt_repay_p_year = amt_repay_p_year;
	}
	public String getAmt_repay_i_year() {
		return amt_repay_i_year;
	}
	public void setAmt_repay_i_year(String amt_repay_i_year) {
		this.amt_repay_i_year = amt_repay_i_year;
	}
	public String getYmd_loan() {
		return ymd_loan;
	}
	public void setYmd_loan(String ymd_loan) {
		this.ymd_loan = ymd_loan;
	}
	public String getInter_pay_day() {
		return inter_pay_day;
	}
	public void setInter_pay_day(String inter_pay_day) {
		this.inter_pay_day = inter_pay_day;
	}
	public String getYmd_cancel() {
		return ymd_cancel;
	}
	public void setYmd_cancel(String ymd_cancel) {
		this.ymd_cancel = ymd_cancel;
	}
	public String getYmd_loanend() {
		return ymd_loanend;
	}
	public void setYmd_loanend(String ymd_loanend) {
		this.ymd_loanend = ymd_loanend;
	}
	public String getCd_cancel() {
		return cd_cancel;
	}
	public void setCd_cancel(String cd_cancel) {
		this.cd_cancel = cd_cancel;
	}
	public String getCd_group_loans_proxy() {
		return cd_group_loans_proxy;
	}
	public void setCd_group_loans_proxy(String cd_group_loans_proxy) {
		this.cd_group_loans_proxy = cd_group_loans_proxy;
	}
	public String getYn_delay_turn_loan() {
		return yn_delay_turn_loan;
	}
	public void setYn_delay_turn_loan(String yn_delay_turn_loan) {
		this.yn_delay_turn_loan = yn_delay_turn_loan;
	}
	public String getYn_recovery() {
		return yn_recovery;
	}
	public void setYn_recovery(String yn_recovery) {
		this.yn_recovery = yn_recovery;
	}
	public String getModerate_fee() {
		return moderate_fee;
	}
	public void setModerate_fee(String moderate_fee) {
		this.moderate_fee = moderate_fee;
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
	public String getAmt_kcb_repay() {
		return amt_kcb_repay;
	}
	public void setAmt_kcb_repay(String amt_kcb_repay) {
		this.amt_kcb_repay = amt_kcb_repay;
	}
	public String getAmt_kcb_delay() {
		return amt_kcb_delay;
	}
	public void setAmt_kcb_delay(String amt_kcb_delay) {
		this.amt_kcb_delay = amt_kcb_delay;
	}
	public String getNm_person() {
		return nm_person;
	}
	public void setNm_person(String nm_person) {
		this.nm_person = nm_person;
	}
	public String getRepayment_nm() {
		return repayment_nm;
	}
	public void setRepayment_nm(String repayment_nm) {
		this.repayment_nm = repayment_nm;
	}
	public String getDeal_nm() {
		return deal_nm;
	}
	public void setDeal_nm(String deal_nm) {
		this.deal_nm = deal_nm;
	}
	
}
