package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PrepareInfo implements Serializable {

	private static final long serialVersionUID = 2487233207853138144L;

	protected 	String	no_prepare;				//사전접수번호
	protected 	String	no_person;				//개인번호
	protected 	String	yn_overlap_chk;			//중복검사여부
	protected 	String	id_agency;				//매체사ID
	protected	String	no_agency;				//매체사 접수 고유번호
	protected 	String	cd_prepare_doc_box;		//상태
	protected 	String	cd_prepare_class;		//상세상태
	protected 	String	cd_advertisement;		//광고매체
	protected 	String	cd_goods_type;			//상품구분
	protected 	String	amt_apply;				//신청금액
	protected 	long	cnt_apply;				//접수건
	protected 	long	cnt_approval;			//승인건
	protected 	long	cnt_reject;				//부결건
	protected 	String	amt_approval;			//승인금액
	protected 	String	id_prepare;				//담당자
	protected 	String	cd_reject_cause;		//접수불가사유
	protected 	String	etc_prepare_path;		//사전접수경로
	protected 	String	cd_collect_path;		//(적법)최초수집경로
	protected 	String	cd_contact_path;		//(적법)연락처를 알게 된 경로
	protected 	String	cd_collect_method;		//(적법)고객 연락방법
	protected 	String	etc_memo;				//(적법)기타메모
	protected 	String	nm_agency;				//(적법)제휴사명
	protected 	String	nm_ceo_agency;			//(적법)대표자이름
	protected 	String	url_homepage_agency;	//(적법)홈페이지
	protected 	String	nm_writer;				//(적법)작성자
	protected 	String	memo_from_agency;		//접수시 메모(제휴사의견)
	protected 	String	memo_from_counsel;  	//마지막 상담메모
	protected 	String	cd_used_apply;  		//사용용도
	protected 	String	ip_prepare;  			//접수 아이피
	protected 	String	c6_keyword;				//유입 키워드
	protected 	String	yn_exist_person;		//기존고객 여부
	protected 	String	no_agency_person;		//대리점 고객번호
	protected 	String	id_agency_user;			//대리점 담당자
	protected 	String	cd_agency_path;			//대리점유입경로
	protected   String cd_goods_gubun;    //상품구분 010 : 신용대출(직장인), 011 : 신용대출(사업자), 020 : 담보대출(주택)

	protected 	String  id_frt;
	protected 	String	dt_frt;
	protected 	String  id_lst;
	protected 	String	dt_lst;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCd_goods_gubun() {
		return cd_goods_gubun;
	}

	public void setCd_goods_gubun(String cd_goods_gubun) {
		this.cd_goods_gubun = cd_goods_gubun;
	}

	public String getNo_prepare() {
		return no_prepare;
	}

	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getYn_overlap_chk() {
		return yn_overlap_chk;
	}

	public void setYn_overlap_chk(String yn_overlap_chk) {
		this.yn_overlap_chk = yn_overlap_chk;
	}

	public String getId_agency() {
		return id_agency;
	}

	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}

	public String getNo_agency() {
		return no_agency;
	}

	public void setNo_agency(String no_agency) {
		this.no_agency = no_agency;
	}

	public String getCd_prepare_doc_box() {
		return cd_prepare_doc_box;
	}

	public void setCd_prepare_doc_box(String cd_prepare_doc_box) {
		this.cd_prepare_doc_box = cd_prepare_doc_box;
	}

	public String getCd_prepare_class() {
		return cd_prepare_class;
	}

	public void setCd_prepare_class(String cd_prepare_class) {
		this.cd_prepare_class = cd_prepare_class;
	}

	public String getCd_advertisement() {
		return cd_advertisement;
	}

	public void setCd_advertisement(String cd_advertisement) {
		this.cd_advertisement = cd_advertisement;
	}

	public String getCd_goods_type() {
		return cd_goods_type;
	}

	public void setCd_goods_type(String cd_goods_type) {
		this.cd_goods_type = cd_goods_type;
	}

	public String getAmt_apply() {
		return amt_apply;
	}

	public void setAmt_apply(String amt_apply) {
		this.amt_apply = amt_apply;
	}

	public long getCnt_apply() {
		return cnt_apply;
	}

	public void setCnt_apply(long cnt_apply) {
		this.cnt_apply = cnt_apply;
	}

	public long getCnt_approval() {
		return cnt_approval;
	}

	public void setCnt_approval(long cnt_approval) {
		this.cnt_approval = cnt_approval;
	}

	public long getCnt_reject() {
		return cnt_reject;
	}

	public void setCnt_reject(long cnt_reject) {
		this.cnt_reject = cnt_reject;
	}

	public String getAmt_approval() {
		return amt_approval;
	}

	public void setAmt_approval(String amt_approval) {
		this.amt_approval = amt_approval;
	}

	public String getId_prepare() {
		return id_prepare;
	}

	public void setId_prepare(String id_prepare) {
		this.id_prepare = id_prepare;
	}

	public String getCd_reject_cause() {
		return cd_reject_cause;
	}

	public void setCd_reject_cause(String cd_reject_cause) {
		this.cd_reject_cause = cd_reject_cause;
	}

	public String getEtc_prepare_path() {
		return etc_prepare_path;
	}

	public void setEtc_prepare_path(String etc_prepare_path) {
		this.etc_prepare_path = etc_prepare_path;
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

	public String getMemo_from_agency() {
		return memo_from_agency;
	}

	public void setMemo_from_agency(String memo_from_agency) {
		this.memo_from_agency = memo_from_agency;
	}

	public String getMemo_from_counsel() {
		return memo_from_counsel;
	}

	public void setMemo_from_counsel(String memo_from_counsel) {
		this.memo_from_counsel = memo_from_counsel;
	}

	public String getIp_prepare() {
		return ip_prepare;
	}

	public void setIp_prepare(String ip_prepare) {
		this.ip_prepare = ip_prepare;
	}

	public String getCd_used_apply() {
		return cd_used_apply;
	}

	public void setCd_used_apply(String cd_used_apply) {
		this.cd_used_apply = cd_used_apply;
	}

	public String getC6_keyword() {
		return c6_keyword;
	}

	public void setC6_keyword(String c6_keyword) {
		this.c6_keyword = c6_keyword;
	}

	public String getYn_exist_person() {
		return yn_exist_person;
	}

	public void setYn_exist_person(String yn_exist_person) {
		this.yn_exist_person = yn_exist_person;
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

	public String getId_agency_user() {
		return id_agency_user;
	}

	public void setId_agency_user(String id_agency_user) {
		this.id_agency_user = id_agency_user;
	}

	public String getCd_agency_path() {
		return cd_agency_path;
	}

	public void setCd_agency_path(String cd_agency_path) {
		this.cd_agency_path = cd_agency_path;
	}

}
