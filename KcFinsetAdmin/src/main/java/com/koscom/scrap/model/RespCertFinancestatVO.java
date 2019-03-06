package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblRespCertFinancestat;

public class RespCertFinancestatVO  extends TblRespCertFinancestat implements Serializable {  // com.koscom.scrap.model.Scrap
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2672519198217396623L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
