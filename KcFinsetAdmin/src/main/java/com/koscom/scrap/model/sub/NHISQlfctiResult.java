package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 자격득실확인서 vo
 * @author HSJ
 *
 */
public class NHISQlfctiResult implements Serializable {
	
	private static final long serialVersionUID = 8016217054628330488L;

	@SerializedName("발급번호")
	private String issueNumber;
	
	@SerializedName("자격득실내역")
	private List<NHISQlfctiResultDetail> nisQlfctiResultDetailList;

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	public List<NHISQlfctiResultDetail> getNisQlfctiResultDetailList() {
		return nisQlfctiResultDetailList;
	}

	public void setNisQlfctiResultDetailList(List<NHISQlfctiResultDetail> nisQlfctiResultDetailList) {
		this.nisQlfctiResultDetailList = nisQlfctiResultDetailList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
