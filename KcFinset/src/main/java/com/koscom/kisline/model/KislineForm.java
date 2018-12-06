package com.koscom.kisline.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class KislineForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = -2917809559694086001L;
	private String uid = "";	//	필수	기관의 사용자 ID입력			koscomco (고정)		
	private String nm = "";	//	선택	검색을 원하는 기업명			최소 3개 중에 하나는 입력해야함		
	private String bizno = "";	//	선택	검색을 원하는 사업자번호					
	private String crpno = "";	//	선택	검색을 원하는 법인번호					
	private String prn_rst_cnt = "";	//	선택	검색결과 출력 건수			최대 20		
	private String pge_st_no = "";	//	선택	검색결과 페이지 번호			최대 50
//	private String uid = "";	//	필수	기관의 사용자 ID입력			koscomco (고정)		
	private String kiscode = "";	//	선택	조회 대상 기업의 KISCODE			"최소 2개 중에 하나는 입력해야함. 	→ KISCODE를 알고 있는 경우 KISCODE를 우선으로 할 것"		
//	private String bizno = "";	//	선택	조회 대상 기업의 사업자번호					
	private String no_bunch = "";			
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getBizno() {
		return bizno;
	}
	public void setBizno(String bizno) {
		this.bizno = bizno;
	}
	public String getCrpno() {
		return crpno;
	}
	public void setCrpno(String crpno) {
		this.crpno = crpno;
	}
	public String getPrn_rst_cnt() {
		return prn_rst_cnt;
	}
	public void setPrn_rst_cnt(String prn_rst_cnt) {
		this.prn_rst_cnt = prn_rst_cnt;
	}
	public String getPge_st_no() {
		return pge_st_no;
	}
	public void setPge_st_no(String pge_st_no) {
		this.pge_st_no = pge_st_no;
	}
	public String getKiscode() {
		return kiscode;
	}
	public void setKiscode(String kiscode) {
		this.kiscode = kiscode;
	}
	public String getNo_bunch() {
		return no_bunch;
	}
	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
}
