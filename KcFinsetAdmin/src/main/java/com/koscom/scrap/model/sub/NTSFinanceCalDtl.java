package com.koscom.scrap.model.sub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class NTSFinanceCalDtl implements Serializable {
	
	private static final long serialVersionUID = 4874494323051733696L;

	@SerializedName("매출액")
	private String a;
	
	@SerializedName("매출원가")
	private String b;
	
	@SerializedName("매출총이익")
	private String c;
	
	@SerializedName("영업손익")
	private String d;
	
	@SerializedName("영업외수익")
	private String e;
	
	@SerializedName("영업외비용")
	private String f;
	
	@SerializedName("당기손순익")
	private String g;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
