package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ApplyInfo implements Serializable{

	private static final long serialVersionUID = -3240339060666629000L;

	protected String cd_fc;		        //금융사
	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	protected String no_apply;			        //접수번호
	protected String no_prepare;		        //사전접수번호
	protected String no_person;			        //개인번호
	protected String cd_apply_doc_box;	        //서류함상태
	protected String cd_advertisement;	        //광고매체
	//protected String cd_apply_comp;		        //금융사
	protected String cd_goods;			        //상품
	protected String id_apply;			        //신청자
	protected String amt_apply;			        //신청금액
	protected String ymd_apply;			        //신청일자
	protected String amt_approval;		        //승인금액
	protected String ymd_approval;		        //승인일자
	protected String ymd_lst_comp;		        //금융사처리일자
	protected String his_lst_comp;		        //금융사처리일시
	protected String etc_apply_path;	        //접수경로
	protected String cd_collect_path;	        //(적법)최초수집경로
	protected String cd_contact_path;	        //(적법)연락처를 알게 된 경로
	protected String cd_collect_method;	        //(적법)고객 연락방법
	protected String etc_memo;			        //(적법)기타메모
	protected String nm_agency;			        //(적법)제휴사명
	protected String nm_ceo_agency;		        //(적법)대표자이름
	protected String url_homepage_agency;	    //(적법)홈페이지
	protected String nm_writer;				    //(적법)작성자
	protected String memo_to_apply;			    //신청시 메모
	protected String memo_from_apply;			//금융사 응답 메모
	protected String no_agency_person;			//대리점 고객번호

	protected String dt_agency_frt;				//대리점 접수일자

	protected String id_frt;
	protected String dt_frt;
	protected String id_lst;
	protected String dt_lst;

	protected double rto_loan = 0;  		  	//적용금리
	protected int year_term = 0;    			//대출기간
	protected String no_bunch; //순번 - TX_FC_TRANSMIT 조회용

	protected String cd_loan_term; //대출기간
	protected String cd_type_pay; //상환방식

	public String getNo_bunch() {
		return no_bunch;
	}

	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
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

	public double getRto_loan() {
		return rto_loan;
	}

	public void setRto_loan(double rto_loan) {
		this.rto_loan = rto_loan;
	}

	public int getYear_term() {
		return year_term;
	}

	public void setYear_term(int year_term) {
		this.year_term = year_term;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_apply() {
		return no_apply;
	}

	public void setNo_apply(String no_apply) {
		this.no_apply = no_apply;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getCd_apply_doc_box() {
		return cd_apply_doc_box;
	}

	public void setCd_apply_doc_box(String cd_apply_doc_box) {
		this.cd_apply_doc_box = cd_apply_doc_box;
	}

	public String getCd_advertisement() {
		return cd_advertisement;
	}

	public void setCd_advertisement(String cd_advertisement) {
		this.cd_advertisement = cd_advertisement;
	}

//	public String getCd_apply_comp() {
//		return cd_apply_comp;
//	}
//
//	public void setCd_apply_comp(String cd_apply_comp) {
//		this.cd_apply_comp = cd_apply_comp;
//	}

	public String getCd_goods() {
		return cd_goods;
	}

	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}

	public String getId_apply() {
		return id_apply;
	}

	public void setId_apply(String id_apply) {
		this.id_apply = id_apply;
	}

	public String getAmt_apply() {
		return amt_apply;
	}

	public void setAmt_apply(String amt_apply) {
		this.amt_apply = amt_apply;
	}

	public String getYmd_apply() {
		return ymd_apply;
	}

	public void setYmd_apply(String ymd_apply) {
		this.ymd_apply = ymd_apply;
	}

	public String getAmt_approval() {
		return amt_approval;
	}

	public void setAmt_approval(String amt_approval) {
		this.amt_approval = amt_approval;
	}

	public String getYmd_approval() {
		return ymd_approval;
	}

	public void setYmd_approval(String ymd_approval) {
		this.ymd_approval = ymd_approval;
	}

	public String getYmd_lst_comp() {
		return ymd_lst_comp;
	}

	public void setYmd_lst_comp(String ymd_lst_comp) {
		this.ymd_lst_comp = ymd_lst_comp;
	}

	public String getHis_lst_comp() {
		return his_lst_comp;
	}

	public void setHis_lst_comp(String his_lst_comp) {
		this.his_lst_comp = his_lst_comp;
	}

	public String getEtc_apply_path() {
		return etc_apply_path;
	}

	public void setEtc_apply_path(String etc_apply_path) {
		this.etc_apply_path = etc_apply_path;
	}

	public String getCd_collect_path() {
		return cd_collect_path;
	}

	public void setCd_collect_path(String cd_collect_path) {
		this.cd_collect_path = cd_collect_path;
	}

	public String getCd_contact_path() {
		return cd_contact_path;
	}

	public void setCd_contact_path(String cd_contact_path) {
		this.cd_contact_path = cd_contact_path;
	}

	public String getCd_collect_method() {
		return cd_collect_method;
	}

	public void setCd_collect_method(String cd_collect_method) {
		this.cd_collect_method = cd_collect_method;
	}

	public String getEtc_memo() {
		return etc_memo;
	}

	public void setEtc_memo(String etc_memo) {
		this.etc_memo = etc_memo;
	}

	public String getNm_agency() {
		return nm_agency;
	}

	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}

	public String getNm_ceo_agency() {
		return nm_ceo_agency;
	}

	public void setNm_ceo_agency(String nm_ceo_agency) {
		this.nm_ceo_agency = nm_ceo_agency;
	}

	public String getUrl_homepage_agency() {
		return url_homepage_agency;
	}

	public void setUrl_homepage_agency(String url_homepage_agency) {
		this.url_homepage_agency = url_homepage_agency;
	}

	public String getNm_writer() {
		return nm_writer;
	}

	public void setNm_writer(String nm_writer) {
		this.nm_writer = nm_writer;
	}

	public String getMemo_to_apply() {
		return memo_to_apply;
	}

	public void setMemo_to_apply(String memo_to_apply) {
		this.memo_to_apply = memo_to_apply;
	}

	public String getMemo_from_apply() {
		return memo_from_apply;
	}

	public void setMemo_from_apply(String memo_from_apply) {
		this.memo_from_apply = memo_from_apply;
	}

	public String getDt_agency_frt() {
		return dt_agency_frt;
	}

	public void setDt_agency_frt(String dt_agency_frt) {
		this.dt_agency_frt = dt_agency_frt;
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

	public String getNo_agency_person() {
		return no_agency_person;
	}

	public void setNo_agency_person(String no_agency_person) {
		this.no_agency_person = no_agency_person;
	}

}
