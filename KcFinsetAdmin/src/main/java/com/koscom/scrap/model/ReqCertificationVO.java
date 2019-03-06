package com.koscom.scrap.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.domain.TblReqCertification;

public class ReqCertificationVO  extends TblReqCertification implements Serializable {  // com.koscom.scrap.model.Scrap
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5057686121616670388L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
