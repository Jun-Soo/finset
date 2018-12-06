package com.koscom.goods.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.koscom.domain.GoodsInfo;

public class GoodsVO extends GoodsInfo implements Serializable {
	private static final long serialVersionUID = -5283835109034749303L;
	private String dt_last_apply;		//가장최근 신청한 날짜(해당상품)
	private String no_person;	//
	private String yn_reapply;	//재신청가능여부20161102
	private String id_agency;	//상품 노출 매체사
	private String yn_open;		//노출 여부
	private String amt_apply;	//대출실행금액 MAX - 대출신청시 자동으로 셋팅
	private String year_term;	//대출실행년도 MAX - 대출신청시 자동으로 셋팅
	private String rto_loan;	//대출실행금리 MAX - 대출신청시 자동으로 셋팅
	private int credit_grade;	//상품별로 계산된 신용등급-20161027
	private String yn_favorite;
	private String yn_alliance;

	
	public String getYn_alliance() {
		return yn_alliance;
	}
	public void setYn_alliance(String yn_alliance) {
		this.yn_alliance = yn_alliance;
	}
	public String getYear_term() {
		return year_term;
	}
	public void setYear_term(String year_term) {
		this.year_term = year_term;
	}
	public String getRto_loan() {
		return rto_loan;
	}
	public void setRto_loan(String rto_loan) {
		this.rto_loan = rto_loan;
	}
	public String getAmt_apply() {
		return amt_apply;
	}
	public void setAmt_apply(String amt_apply) {
		this.amt_apply = amt_apply;
	}
	private MultipartFile file1;
	private MultipartFile file2;
	private MultipartFile fileCi;

	public String getId_agency() {
		return id_agency;
	}
	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}
	public String getYn_open() {
		return yn_open;
	}
	public void setYn_open(String yn_open) {
		this.yn_open = yn_open;
	}
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	public MultipartFile getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
	public MultipartFile getFileCi() {
		return fileCi;
	}
	public void setFileCi(MultipartFile fileCi) {
		this.fileCi = fileCi;
	}
	public int getCredit_grade() {
		return credit_grade;
	}
	public void setCredit_grade(int credit_grade) {
		this.credit_grade = credit_grade;
	}
	public String getYn_reapply() {
		return yn_reapply;
	}
	public void setYn_reapply(String yn_reapply) {
		this.yn_reapply = yn_reapply;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getDt_last_apply() {
		return dt_last_apply;
	}
	public void setDt_last_apply(String dt_last_apply) {
		this.dt_last_apply = dt_last_apply;
	}
	public String getYn_favorite() {
		return yn_favorite;
	}
	public void setYn_favorite(String yn_favorite) {
		this.yn_favorite = yn_favorite;
	}
	
}
