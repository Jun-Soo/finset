package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblRespCertVat;

public class RespCertVatVO extends TblRespCertVat implements Serializable {  // com.koscom.scrap.model.Scrap
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 855332527131622095L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
