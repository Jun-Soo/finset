package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblRespIncomeDtl;

public class RespIncomeDtlVO extends TblRespIncomeDtl implements Serializable {  // com.koscom.scrap.model.Scrap
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2634523976369585027L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
