package com.koscom.consume.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PersonConsumeClassVO implements Serializable{
	
	private static final long serialVersionUID = 2681464497100571124L;
	
	private String 	no_person;							// 회원관리번호
	private String 	cd_consume_class;					// 소비항목코드
	private String	type_in_out;						// 수입지출구분
	private String 	cd_class;							// 분류코드
	private String 	nm_class;							// 분류명
	private String 	cd_type;							// 항목코드
	private String 	nm_type;							// 항목명
	private String 	yn_default;							// 기본항목여부
	private String 	yn_use;								// 사용여부
	private String	sort_class;							// 분류 정렬순서
	private String	sort_type;							// 항목 정렬순서
	private String 	id_frt;								// 최초입력아이디
	private Date 	dt_frt;								// 최초입력시간
	private String 	id_lst;								// 최종수정아이디
	private Date 	dt_lst;								// 최종수정시간
	private List<PersonConsumeClassVO> list;			// 업데이트 데이터 저장 list
	
	public PersonConsumeClassVO() {
	}
	public PersonConsumeClassVO(String no_person, String cd_consume_class,
			String type_in_out, String cd_class, String nm_class,
			String cd_type, String nm_type, String yn_default, String yn_use,
			String sort_class, String sort_type, String id_frt, Date dt_frt,
			String id_lst, Date dt_lst, List<PersonConsumeClassVO> list) {
		this.no_person = no_person;
		this.cd_consume_class = cd_consume_class;
		this.type_in_out = type_in_out;
		this.cd_class = cd_class;
		this.nm_class = nm_class;
		this.cd_type = cd_type;
		this.nm_type = nm_type;
		this.yn_default = yn_default;
		this.yn_use = yn_use;
		this.sort_class = sort_class;
		this.sort_type = sort_type;
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
	public String getCd_consume_class() {
		return cd_consume_class;
	}
	public void setCd_consume_class(String cd_consume_class) {
		this.cd_consume_class = cd_consume_class;
	}
	public String getType_in_out() {
		return type_in_out;
	}
	public void setType_in_out(String type_in_out) {
		this.type_in_out = type_in_out;
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
	public String getNm_type() {
		return nm_type;
	}
	public void setNm_type(String nm_type) {
		this.nm_type = nm_type;
	}
	public String getYn_default() {
		return yn_default;
	}
	public void setYn_default(String yn_default) {
		this.yn_default = yn_default;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getSort_class() {
		return sort_class;
	}
	public void setSort_class(String sort_class) {
		this.sort_class = sort_class;
	}
	public String getSort_type() {
		return sort_type;
	}
	public void setSort_type(String sort_type) {
		this.sort_type = sort_type;
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
	public List<PersonConsumeClassVO> getList() {
		return list;
	}
	public void setList(List<PersonConsumeClassVO> list) {
		this.list = list;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}