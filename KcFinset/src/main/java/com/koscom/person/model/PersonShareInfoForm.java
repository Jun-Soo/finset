package com.koscom.person.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class PersonShareInfoForm extends SearchForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1076342545064591404L;

	private String no_person; //회원번호
	private String cd_share; //공유구분(01-신용정보/02-금융정보)
	private String type_list; //list type(요청 - req, 허용 - offer)
	private String share_status; //공유상태

	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getCd_share() {
		return cd_share;
	}
	public void setCd_share(String cd_share) {
		this.cd_share = cd_share;
	}
	public String getType_list() {
		return type_list;
	}
	public void setType_list(String type_list) {
		this.type_list = type_list;
	}
	public String getShare_status() {
		return share_status;
	}
	public void setShare_status(String share_status) {
		this.share_status = share_status;
	}

}
