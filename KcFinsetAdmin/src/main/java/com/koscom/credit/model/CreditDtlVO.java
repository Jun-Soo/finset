package com.koscom.credit.model;

public class CreditDtlVO {

	private static final long serialVersionUID = 6192197545025546983L;

	private String no_person;       // 회원관리번호
	private String cd_change_info;	//정보변동구분
	private String change_contents;	//변동내용
	private String change_item;		//변동항목
	private String cd_fc;			//금융사코드
	private String nm_fc;			//금융사명
	private String dt_info;			//정보등록일
	private String collector;		//수집처

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_change_info() {
		return cd_change_info;
	}
	public void setCd_change_info(String cd_change_info) {
		this.cd_change_info = cd_change_info;
	}
	public String getChange_contents() {
		return change_contents;
	}
	public void setChange_contents(String change_contents) {
		this.change_contents = change_contents;
	}
	public String getChange_item() {
		return change_item;
	}
	public void setChange_item(String change_item) {
		this.change_item = change_item;
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
	public String getDt_info() {
		return dt_info;
	}
	public void setDt_info(String dt_info) {
		this.dt_info = dt_info;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
}