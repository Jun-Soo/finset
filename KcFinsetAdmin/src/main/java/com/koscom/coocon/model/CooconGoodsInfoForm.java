package com.koscom.coocon.model;

import java.io.Serializable;

public class CooconGoodsInfoForm implements Serializable {

	private static final long serialVersionUID = 4694112476376527631L;
	//	private static final long serialVersionUID = -7693376532078788852L;
	
	private String cd_coocon_goods;
	private String nm_coocon_goods;
	private String seq;
	public String getCd_coocon_goods() {
		return cd_coocon_goods;
	}
	public void setCd_coocon_goods(String cd_coocon_goods) {
		this.cd_coocon_goods = cd_coocon_goods;
	}
	public String getNm_coocon_goods() {
		return nm_coocon_goods;
	}
	public void setNm_coocon_goods(String nm_coocon_goods) {
		this.nm_coocon_goods = nm_coocon_goods;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

}