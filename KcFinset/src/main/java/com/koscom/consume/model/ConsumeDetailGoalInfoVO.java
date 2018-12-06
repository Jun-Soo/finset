package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConsumeDetailGoalInfoVO implements Serializable{
	
	private static final long serialVersionUID = -2473734196250963548L;
	
	private String	no_person;		//회원관리번호
	private String	req_yyyymm;		//기준년월
	private String	cd_set;			//설정구분
	private String	cd_class;		//분류코드
	private String	nm_class;		//분류명
	private String	cd_type;		//항목코드
	private String	cd_fc;			//금융사코드
	private String	nm_card;		//카드명
	private String	no_card;		//카드번호
	private String	amt_budget;		//예산금액
	private String	amt_expense;	//지출금액
	private String	id_frt;
	private Date	dt_frt;
	private String	id_lst;
	private Date	dt_lst;
	
	//데이터터를 전달 받을 리스트
	private List<ConsumeDetailGoalInfoVO> list;
	
	public ConsumeDetailGoalInfoVO() {}
	public ConsumeDetailGoalInfoVO(String no_person, String req_yyyymm,
			String cd_set, String cd_class, String nm_class, String cd_type,
			String cd_fc, String nm_card, String no_card, String amt_budget,
			String amt_expense, String id_frt, Date dt_frt, String id_lst,
			Date dt_lst, List<ConsumeDetailGoalInfoVO> list) {
		this.no_person = no_person;
		this.req_yyyymm = req_yyyymm;
		this.cd_set = cd_set;
		this.cd_class = cd_class;
		this.nm_class = nm_class;
		this.cd_type = cd_type;
		this.cd_fc = cd_fc;
		this.nm_card = nm_card;
		this.no_card = no_card;
		this.amt_budget = amt_budget;
		this.amt_expense = amt_expense;
		this.id_frt = id_frt;
		this.dt_frt = dt_frt;
		this.id_lst = id_lst;
		this.dt_lst = dt_lst;
		this.list = list;
	}
	
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getReq_yyyymm() {
		return req_yyyymm;
	}
	public void setReq_yyyymm(String req_yyyymm) {
		this.req_yyyymm = req_yyyymm;
	}
	public String getCd_set() {
		return cd_set;
	}
	public void setCd_set(String cd_set) {
		this.cd_set = cd_set;
	}
	public String getCd_class() {
		return cd_class;
	}
	public void setCd_class(String cd_class) {
		this.cd_class = cd_class;
	}
	public String getNm_class() {
		return nm_class;
	}
	public void setNm_class(String nm_class) {
		this.nm_class = nm_class;
	}
	public String getCd_type() {
		return cd_type;
	}
	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_card() {
		return nm_card;
	}
	public void setNm_card(String nm_card) {
		this.nm_card = nm_card;
	}
	public String getNo_card() {
		return no_card;
	}
	public void setNo_card(String no_card) {
		this.no_card = no_card;
	}
	public String getAmt_budget() {
		return amt_budget;
	}
	public void setAmt_budget(String amt_budget) {
		this.amt_budget = amt_budget;
	}
	public String getAmt_expense() {
		return amt_expense;
	}
	public void setAmt_expense(String amt_expense) {
		this.amt_expense = amt_expense;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public Date getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(Date dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public Date getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(Date dt_lst) {
		this.dt_lst = dt_lst;
	}
	public List<ConsumeDetailGoalInfoVO> getList() {
		return list;
	}
	public void setList(List<ConsumeDetailGoalInfoVO> list) {
		this.list = list;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
}
