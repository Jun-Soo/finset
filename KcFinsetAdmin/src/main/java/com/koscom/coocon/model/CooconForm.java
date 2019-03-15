package com.koscom.coocon.model;

import java.io.Serializable;

public class CooconForm implements Serializable {

	private static final long serialVersionUID = 4694112476376527631L;
	//	private static final long serialVersionUID = -7693376532078788852L;
	
	private String cd_type_fc;
	private String cd_non_goods;
	
	public String getCd_type_fc() {
		return cd_type_fc;
	}
	public void setCd_type_fc(String cd_type_fc) {
		this.cd_type_fc = cd_type_fc;
	}
	public String getCd_non_goods() {
		return cd_non_goods;
	}
	public void setCd_non_goods(String cd_non_goods) {
		this.cd_non_goods = cd_non_goods;
	}
	
	

}