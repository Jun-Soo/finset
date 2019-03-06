package com.koscom.scrap.model.sub;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

/**
 * 납부확인서 vo
 * @author HSJ
 *
 */
public class NHISPaymentResult implements Serializable {

	private static final long serialVersionUID = -2562250282508788970L;
	
	@SerializedName("납부내역")
	private List<NHISPaymentResultDetail> nhisResultList;
	
	public List<NHISPaymentResultDetail> getNhisResultList() {
		return nhisResultList;
	}

	public void setNhisResultList(List<NHISPaymentResultDetail> nhisResultList) {
		this.nhisResultList = nhisResultList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
