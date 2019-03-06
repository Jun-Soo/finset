package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblRespHealthQlfctngainloss;

public class RespHealthQlfctngainlossVO extends TblRespHealthQlfctngainloss implements Serializable {  // com.koscom.scrap.model.Scrap
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665770304313231420L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
