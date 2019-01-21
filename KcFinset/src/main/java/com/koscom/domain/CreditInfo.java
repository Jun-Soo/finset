package com.koscom.domain;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CreditInfo implements Serializable {
	private static final long serialVersionUID = -6034445190332657238L;
	private int seq; 			//일련번호
	private int seqGroup; 		//그룹일련번호
	private String noPerson; 	//금융사 개인식별번호
	private String nmCust; 		//성명
	private String cdCbComp; 	//신용정보사코드
	private String nmIf; 		//전문구분코드
	private String nmIfSub; 	//전문상세명
	private String cdCbCause; 	//조회사유코드
	private String cdAgreeCause;//조회동의코드
	private String cdCbResponse;//응답코드
	private String msgRequest; 	//요청전문
	private String msgResponse; //응답전문
	private String noInqKey; 	//신용조회 대체키
	private String idFrt;
	private String dtFrt;
	private String kcbURI;
	private String kcbID;

	//client_신용관리메인
	private String req_yyyymm;
	private String rating_credit="";
	private String grade_credit="";
	private String percentage="";
	private String rating_diff="";
	private String cnt_credit_ref_1y;
	private String cnt_credit_change;
	private String cnt_normal_info;
	private String cnt_overdue_info;
	private String cnt_card;
	private String cnt_loan;
	private String cnt_overdue;
	private String cnt_guarantee;

	//연체 총금액
	private int koi_sum_amt; //연체정보_연체잔액sum
	private int kosi_sum_amt; //대지급정보_대지급금액sum
	private int kodi_sum_amt; //채무불이행정보(기타연체)_연체금액sum
	private int kopi_sum_amt; //공공정보(기타연체)_등록금액sum
	private int kfdi_sum_amt; //금융질서문란정보(기타연체)_연체금액sum

	//client_신용관리 신용등급상세
	private String chart_title;
	private String chart_list_param;
	private String dt_info;
	private String cd_fc;
	private String nm_fc;
	private String change_contents;
	private String collector;
	private String cdChangeInfo;
	private String mm_cnt;
	private String year_cnt;

	//client_신용관리 대출현황
	private String debt_cnt;
	private String sum_amt_remain;
	private String sum_amt_contract;
	private String no_manage_info;
	private String ymd_loan;
	private String amt_remain;
	private String amt_contract;
	private String yn_credit;
	private String yn_loan;
	private String cd_debt;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getSeqGroup() {
		return seqGroup;
	}

	public void setSeqGroup(Integer seqGroup) {
		this.seqGroup = seqGroup;
	}

	public String getNoPerson() {
		return noPerson;
	}

	public void setNoPerson(String noPerson) {
		this.noPerson = noPerson;
	}

	public String getNmCust() {
		return nmCust;
	}

	public void setNmCust(String nmCust) {
		this.nmCust = nmCust;
	}

	public String getCdCbComp() {
		return cdCbComp;
	}

	public void setCdCbComp(String cdCbComp) {
		this.cdCbComp = cdCbComp;
	}

	public String getNmIf() {
		return nmIf;
	}

	public void setNmIf(String nmIf) {
		this.nmIf = nmIf;
	}


	public String getCdCbCause() {
		return cdCbCause;
	}

	public void setCdCbCause(String cdCbCause) {
		this.cdCbCause = cdCbCause;
	}

	public String getCdAgreeCause() {
		return cdAgreeCause;
	}

	public void setCdAgreeCause(String cdAgreeCause) {
		this.cdAgreeCause = cdAgreeCause;
	}

	public String getCdCbResponse() {
		return cdCbResponse;
	}

	public void setCdCbResponse(String cdCbResponse) {
		this.cdCbResponse = cdCbResponse;
	}

	public String getMsgRequest() {
		return msgRequest;
	}

	public void setMsgRequest(String msgRequest) {
		this.msgRequest = msgRequest;
	}

	public String getMsgResponse() {
		return msgResponse;
	}

	public void setMsgResponse(String msgResponse) {
		this.msgResponse = msgResponse;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setSeqGroup(int seqGroup) {
		this.seqGroup = seqGroup;
	}

	public String getIdFrt() {
		return idFrt;
	}

	public void setIdFrt(String idFrt) {
		this.idFrt = idFrt;
	}

	public String getDtFrt() {
		return dtFrt;
	}

	public void setDtFrt(String dtFrt) {
		this.dtFrt = dtFrt;
	}

	public String getNoInqKey() {
		return noInqKey;
	}

	public void setNoInqKey(String noInqKey) {
		this.noInqKey = noInqKey;
	}

	public String getNmIfSub() {
		return nmIfSub;
	}

	public void setNmIfSub(String nmIfSub) {
		this.nmIfSub = nmIfSub;
	}

	public String getKcbURI() {
		return kcbURI;
	}

	public void setKcbURI(String kcbURI) {
		this.kcbURI = kcbURI;
	}

	public String getKcbID() {
		return kcbID;
	}

	public void setKcbID(String kcbID) {
		this.kcbID = kcbID;
	}

	public String getReq_yyyymm() {
		return req_yyyymm;
	}

	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
	}

	public String getRating_credit() {
		return rating_credit;
	}

	public void setRating_credit(String rating_credit) {
		this.rating_credit = rating_credit;
	}

	public String getGrade_credit() {
		return grade_credit;
	}

	public void setGrade_credit(String grade_credit) {
		this.grade_credit = grade_credit;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getRating_diff() {
		return rating_diff;
	}

	public void setRating_diff(String rating_diff) {
		this.rating_diff = rating_diff;
	}

	public String getCnt_credit_ref_1y() {
		return cnt_credit_ref_1y;
	}

	public void setCnt_credit_ref_1y(String cnt_credit_ref_1y) {
		this.cnt_credit_ref_1y = cnt_credit_ref_1y;
	}

	public String getCnt_credit_change() {
		return cnt_credit_change;
	}

	public void setCnt_credit_change(String cnt_credit_change) {
		this.cnt_credit_change = cnt_credit_change;
	}

	public String getCnt_normal_info() {
		return cnt_normal_info;
	}

	public void setCnt_normal_info(String cnt_normal_info) {
		this.cnt_normal_info = cnt_normal_info;
	}

	public String getCnt_overdue_info() {
		return cnt_overdue_info;
	}

	public void setCnt_overdue_info(String cnt_overdue_info) {
		this.cnt_overdue_info = cnt_overdue_info;
	}

	public String getCnt_card() {
		return cnt_card;
	}

	public void setCnt_card(String cnt_card) {
		this.cnt_card = cnt_card;
	}

	public String getCnt_loan() {
		return cnt_loan;
	}

	public void setCnt_loan(String cnt_loan) {
		this.cnt_loan = cnt_loan;
	}

	public String getCnt_overdue() {
		return cnt_overdue;
	}

	public void setCnt_overdue(String cnt_overdue) {
		this.cnt_overdue = cnt_overdue;
	}

	public String getCnt_guarantee() {
		return cnt_guarantee;
	}

	public void setCnt_guarantee(String cnt_guarantee) {
		this.cnt_guarantee = cnt_guarantee;
	}

	public int getKoi_sum_amt() {
		return koi_sum_amt;
	}

	public void setKoi_sum_amt(int koi_sum_amt) {
		this.koi_sum_amt = koi_sum_amt;
	}

	public int getKosi_sum_amt() {
		return kosi_sum_amt;
	}

	public void setKosi_sum_amt(int kosi_sum_amt) {
		this.kosi_sum_amt = kosi_sum_amt;
	}

	public int getKodi_sum_amt() {
		return kodi_sum_amt;
	}

	public void setKodi_sum_amt(int kodi_sum_amt) {
		this.kodi_sum_amt = kodi_sum_amt;
	}

	public int getKopi_sum_amt() {
		return kopi_sum_amt;
	}

	public void setKopi_sum_amt(int kopi_sum_amt) {
		this.kopi_sum_amt = kopi_sum_amt;
	}

	public int getKfdi_sum_amt() {
		return kfdi_sum_amt;
	}

	public void setKfdi_sum_amt(int kfdi_sum_amt) {
		this.kfdi_sum_amt = kfdi_sum_amt;
	}

	public String getChart_title() {
		return chart_title;
	}

	public void setChart_title(String chart_title) {
		this.chart_title = chart_title;
	}

	public String getChart_list_param() {
		return chart_list_param;
	}

	public void setChart_list_param(String chart_list_param) {
		this.chart_list_param = chart_list_param;
	}

	public String getDt_info() {
		return dt_info;
	}

	public void setDt_info(String dt_info) {
		this.dt_info = dt_info;
	}

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

	public String getChange_contents() {
		return change_contents;
	}

	public void setChange_contents(String change_contents) {
		this.change_contents = change_contents;
	}

	public String getCollector() {
		return collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
	}

	public String getCdChangeInfo() {
		return cdChangeInfo;
	}

	public void setCdChangeInfo(String cdChangeInfo) {
		this.cdChangeInfo = cdChangeInfo;
	}

	public String getMm_cnt() {
		return mm_cnt;
	}

	public void setMm_cnt(String mm_cnt) {
		this.mm_cnt = mm_cnt;
	}

	public String getYear_cnt() {
		return year_cnt;
	}

	public void setYear_cnt(String year_cnt) {
		this.year_cnt = year_cnt;
	}

	public String getDebt_cnt() {
		return debt_cnt;
	}

	public void setDebt_cnt(String debt_cnt) {
		this.debt_cnt = debt_cnt;
	}

	public String getSum_amt_remain() {
		return sum_amt_remain;
	}

	public void setSum_amt_remain(String sum_amt_remain) {
		this.sum_amt_remain = sum_amt_remain;
	}

	public String getSum_amt_contract() {
		return sum_amt_contract;
	}

	public void setSum_amt_contract(String sum_amt_contract) {
		this.sum_amt_contract = sum_amt_contract;
	}

	public String getNo_manage_info() {
		return no_manage_info;
	}

	public void setNo_manage_info(String no_manage_info) {
		this.no_manage_info = no_manage_info;
	}

	public String getYmd_loan() {
		return ymd_loan;
	}

	public void setYmd_loan(String ymd_loan) {
		this.ymd_loan = ymd_loan;
	}

	public String getAmt_remain() {
		return amt_remain;
	}

	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}

	public String getAmt_contract() {
		return amt_contract;
	}

	public void setAmt_contract(String amt_contract) {
		this.amt_contract = amt_contract;
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

	public String getCd_debt() {
		return cd_debt;
	}

	public void setCd_debt(String cd_debt) {
		this.cd_debt = cd_debt;
	}

}
