package com.koscom.fss.model;

import java.io.Serializable;
import java.util.List;

public class FssCreditLoanProductVO implements Serializable {
	private String dcls_month= "";		//공시 제출월 [YYYYMM]
	private String fin_co_no= "";		//금융회사코드
	private String kor_co_nm= "";		//금융회사 명
	private String fin_prdt_cd= "";		//금융상품코드
	private String fin_prdt_nm= "";		//금융상품명
	private String join_way= "";		//가입방법
	private String crdt_prdt_type= "";		//대출종류 코드
	private String crdt_prdt_type_nm= "";		//대출종류명
	private String cd_name= "";		//CB 회사명
	private String dcls_strt_day= "";		//공시 시작일
	private String dcls_end_day= "";		//공시 종료일
	private String fin_co_subm_day= "";		//금융회사 제출일 [YYYYMMDDHH24MI]
	private String id_frt= "";		//ADMIN
	private String dt_frt= "";		//ADMIN
	private String id_lst= "";		//ADMIN
	private String dt_lst= "";		//ADMIN
	public String getDcls_month() {
		return dcls_month;
	}
	public void setDcls_month(String dcls_month) {
		this.dcls_month = dcls_month;
	}
	public String getFin_co_no() {
		return fin_co_no;
	}
	public void setFin_co_no(String fin_co_no) {
		this.fin_co_no = fin_co_no;
	}
	public String getKor_co_nm() {
		return kor_co_nm;
	}
	public void setKor_co_nm(String kor_co_nm) {
		this.kor_co_nm = kor_co_nm;
	}
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getFin_prdt_nm() {
		return fin_prdt_nm;
	}
	public void setFin_prdt_nm(String fin_prdt_nm) {
		this.fin_prdt_nm = fin_prdt_nm;
	}
	public String getJoin_way() {
		return join_way;
	}
	public void setJoin_way(String join_way) {
		this.join_way = join_way;
	}
	public String getCrdt_prdt_type() {
		return crdt_prdt_type;
	}
	public void setCrdt_prdt_type(String crdt_prdt_type) {
		this.crdt_prdt_type = crdt_prdt_type;
	}
	public String getCrdt_prdt_type_nm() {
		return crdt_prdt_type_nm;
	}
	public void setCrdt_prdt_type_nm(String crdt_prdt_type_nm) {
		this.crdt_prdt_type_nm = crdt_prdt_type_nm;
	}
	public String getCd_name() {
		return cd_name;
	}
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}
	public String getDcls_strt_day() {
		return dcls_strt_day;
	}
	public void setDcls_strt_day(String dcls_strt_day) {
		this.dcls_strt_day = dcls_strt_day;
	}
	public String getDcls_end_day() {
		return dcls_end_day;
	}
	public void setDcls_end_day(String dcls_end_day) {
		this.dcls_end_day = dcls_end_day;
	}
	public String getFin_co_subm_day() {
		return fin_co_subm_day;
	}
	public void setFin_co_subm_day(String fin_co_subm_day) {
		this.fin_co_subm_day = fin_co_subm_day;
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
}
