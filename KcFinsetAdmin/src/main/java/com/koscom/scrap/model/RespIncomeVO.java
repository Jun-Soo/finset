package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblRespIncome;

public class RespIncomeVO extends TblRespIncome implements Serializable {  // com.koscom.scrap.model.Scrap
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5902587701127366916L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
