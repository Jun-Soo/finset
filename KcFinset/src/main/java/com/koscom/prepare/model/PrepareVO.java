package com.koscom.prepare.model;

import java.io.Serializable;

import com.koscom.domain.PrepareInfo;
import com.koscom.util.StringUtil;

public class PrepareVO extends PrepareInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7987209890679554553L;
	
	private String nm_person;	        //이름
	private String bgn;			        //생년월일
	private String hp;			        //휴대폰번호
	private String cd_job_class;		//직업구분
	private String ssn_person;			//주민번호
	private String cd_advertisement;	//광고매체
	private String ymd_frt_short;
	private String cnt_prepare_doc;
	private String cd_prepare_doc_box_before;
	private String yn_batch;			//일괄수정여부 

	/**
	 * 서류함 구분
	 * 0 01 가접수
	   1 10 분배대기 
       2 20 상담
       5 50 접수
       6 60 접수불가
       7 70 보류
       9 90 휴지통
       10 99 삭제
	 */
	public static String CD_PREPARE_DOC_BOX_01 = "01";
	public static String CD_PREPARE_DOC_BOX_10 = "10";
	public static String CD_PREPARE_DOC_BOX_20 = "20";
	public static String CD_PREPARE_DOC_BOX_50 = "50";
	public static String CD_PREPARE_DOC_BOX_60 = "60";
	public static String CD_PREPARE_DOC_BOX_70 = "70";
	public static String CD_PREPARE_DOC_BOX_90 = "90";
	public static String CD_PREPARE_DOC_BOX_99 = "99";
	
	/**
	 * 기존고객 분배설정
	 * 01 : 자동분배
	 * 02 : 기존상담사
	 * 03 : 분배안함
	 */
	public static final String CD_EXIST_DIV_01 = "01";
	public static final String CD_EXIST_DIV_02 = "02";
	public static final String CD_EXIST_DIV_03 = "03";
	
	public String getC1_gender() {
		return StringUtil.splitBgn(this.bgn, "G");
	}
	
	public String getYmd_birth() {
		return StringUtil.splitBgn(this.bgn, "B");
	}
	
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

	public String getYmd_frt_short() {
		return ymd_frt_short;
	}

	public void setYmd_frt_short(String ymd_frt_short) {
		this.ymd_frt_short = ymd_frt_short;
	}

	public String getSsn_person() {
		return ssn_person;
	}

	public void setSsn_person(String ssn_person) {
		this.ssn_person = ssn_person;
	}

	public String getCd_advertisement() {
		return cd_advertisement;
	}

	public void setCd_advertisement(String cd_advertisement) {
		this.cd_advertisement = cd_advertisement;
	}

	public String getCnt_prepare_doc() {
		return cnt_prepare_doc;
	}

	public void setCnt_prepare_doc(String cnt_prepare_doc) {
		this.cnt_prepare_doc = cnt_prepare_doc;
	}

	public String getCd_prepare_doc_box_before() {
		return cd_prepare_doc_box_before;
	}

	public void setCd_prepare_doc_box_before(String cd_prepare_doc_box_before) {
		this.cd_prepare_doc_box_before = cd_prepare_doc_box_before;
	}

	public String getYn_batch() {
		return yn_batch;
	}

	public void setYn_batch(String yn_batch) {
		this.yn_batch = yn_batch;
	}

}
