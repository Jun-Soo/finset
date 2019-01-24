package com.koscom.diags.model.analysis;

import java.io.Serializable;

public class AnalysisBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	
	protected String comment;		// factor 분석 설명 
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}



}
