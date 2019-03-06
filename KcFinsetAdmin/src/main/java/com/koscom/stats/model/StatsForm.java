package com.koscom.stats.model;

import java.io.Serializable;

import com.koscom.comm.model.SearchForm;

public class StatsForm extends SearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -768165137903275373L;
	
	private String cd_advertisement_srch;
	private String cd_keyword_comp;
	
	public String getCd_advertisement_srch() {
		return cd_advertisement_srch;
	}
	public void setCd_advertisement_srch(String cd_advertisement_srch) {
		this.cd_advertisement_srch = cd_advertisement_srch;
	}
	public String getCd_keyword_comp() {
		return cd_keyword_comp;
	}
	public void setCd_keyword_comp(String cd_keyword_comp) {
		this.cd_keyword_comp = cd_keyword_comp;
	}
	
	

}
