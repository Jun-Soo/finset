package com.koscom.kcb.model;


import java.util.HashMap;

import com.koscom.domain.CreditInfo;

public class KcbCreditInfoVO extends CreditInfo {

	private static final long serialVersionUID = 6192197545025546983L;

	private String 		no_user; 		//이용자번호
	private String 		nm_branch; 		//조회지점명
	private String 		ip; 			//nice ip
	private int 		port; 			//nice port
	private String[] 	cps_codes;
	private String 		yn_use_inq_key; //대체조회키 사용여부
	private String 		yn_identify; 	//개인식별번호 출력여부(주민번호)
	private String 		fk1;
	private String 		fk2;
	private String 		yn_cb;
	private String 		yn_close_menu; 	//메뉴닫힘여부
	private String 		bgn;
	private String 		hp;
	private String 		di;
	private String 		cp;
	private String 		cd_regist;
	private String 		cd_per_corp;		//개인법인구분코드인데 - 코스콤은 특수값 4

	private String 		kcb_seq;
	private String		kcb_id;
	private String 		req_menu_code;
	private String 		req_view_code;
	private String 		yn_craw_test;

	private String 		doc_page1;
	private String 		doc_page2;


	private HashMap<String,String> req_kcb231Data_map; //GRAS 요청데이터 맵


	public String getNo_user() {
		return no_user;
	}
	public void setNo_user(String no_user) {
		this.no_user = no_user;
	}
	public String getNm_branch() {
		return nm_branch;
	}
	public void setNm_branch(String nm_branch) {
		this.nm_branch = nm_branch;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String[] getCps_codes() {
		return cps_codes;
	}
	public void setCps_codes(String[] cps_codes) {
		this.cps_codes = cps_codes;
	}
	public String getYn_use_inq_key() {
		return yn_use_inq_key;
	}
	public void setYn_use_inq_key(String yn_use_inq_key) {
		this.yn_use_inq_key = yn_use_inq_key;
	}
	public HashMap<String, String> getReq_kcb231Data_map() {
		return req_kcb231Data_map;
	}
	public void setReq_kcb231Data_map(HashMap<String, String> req_kcb231Data_map) {
		this.req_kcb231Data_map = req_kcb231Data_map;
	}
	public String getYn_identify() {
		return yn_identify;
	}
	public void setYn_identify(String yn_identify) {
		this.yn_identify = yn_identify;
	}
	public String getFk1() {
		return fk1;
	}
	public void setFk1(String fk1) {
		this.fk1 = fk1;
	}
	public String getFk2() {
		return fk2;
	}
	public void setFk2(String fk2) {
		this.fk2 = fk2;
	}
	public String getYn_cb() {
		return yn_cb;
	}
	public void setYn_cb(String yn_cb) {
		this.yn_cb = yn_cb;
	}
	public String getYn_close_menu() {
		return yn_close_menu;
	}
	public void setYn_close_menu(String yn_close_menu) {
		this.yn_close_menu = yn_close_menu;
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
	public String getDi() {
		return di;
	}
	public void setDi(String di) {
		this.di = di;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getCd_regist() {
		return cd_regist;
	}
	public void setCd_regist(String cd_regist) {
		this.cd_regist = cd_regist;
	}
	public String getCd_per_corp() {
		return cd_per_corp;
	}
	public void setCd_per_corp(String cd_per_corp) {
		this.cd_per_corp = cd_per_corp;
	}
	public String getKcb_seq() {
		return kcb_seq;
	}
	public void setKcb_seq(String kcb_seq) {
		this.kcb_seq = kcb_seq;
	}
	public String getKcb_id() {
		return kcb_id;
	}
	public void setKcb_id(String kcb_id) {
		this.kcb_id = kcb_id;
	}
	public String getReq_menu_code() {
		return req_menu_code;
	}
	public void setReq_menu_code(String req_menu_code) {
		this.req_menu_code = req_menu_code;
	}
	public String getReq_view_code() {
		return req_view_code;
	}
	public void setReq_view_code(String req_view_code) {
		this.req_view_code = req_view_code;
	}
	public String getYn_craw_test() {
		return yn_craw_test;
	}
	public void setYn_craw_test(String yn_craw_test) {
		this.yn_craw_test = yn_craw_test;
	}
	public String getDoc_page1() {
		return doc_page1;
	}
	public void setDoc_page1(String doc_page1) {
		this.doc_page1 = doc_page1;
	}
	public String getDoc_page2() {
		return doc_page2;
	}
	public void setDoc_page2(String doc_page2) {
		this.doc_page2 = doc_page2;
	}


}