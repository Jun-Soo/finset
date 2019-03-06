package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblReqHealth;

public class ReqHealthVO extends TblReqHealth implements Serializable {  // com.koscom.scrap.model.Scrap
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977888605547265172L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
