package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblRespCertFinancestatdtl;

public class RespCertFinancestatdtlVO extends TblRespCertFinancestatdtl implements Serializable {  // com.koscom.scrap.model.Scrap
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7442316842898981727L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
