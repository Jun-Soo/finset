package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CooconGoodsFavoriteInfo implements Serializable {

	private static final long serialVersionUID = 4703129298064500909L;

	private String no_person;       // 고객번호
	private String cd_fc;           // 금융사
	private String cd_non_goods; // 상품번호
	private String yn_alliance;     // 체크여부
	private String cd_goods_class_l;     // 체크여부
	private String cd_goods_class_m;     // 체크여부
	private String id_frt;				// 최초등록자
	private String dt_frt;				// 최초등록일
	private String id_lst;				// 최종수정자
	private String dt_lst;				// 최종수정일

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getCd_fc() {
		return cd_fc;
	}

	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}

	public String getCd_non_goods() {
		return cd_non_goods;
	}

	public void setCd_non_goods(String cd_non_goods) {
		this.cd_non_goods = cd_non_goods;
	}

	public String getYn_alliance() {
		return yn_alliance;
	}

	public void setYn_alliance(String yn_alliance) {
		this.yn_alliance = yn_alliance;
	}

	public String getCd_goods_class_l() {
		return cd_goods_class_l;
	}

	public void setCd_goods_class_l(String cd_goods_class_l) {
		this.cd_goods_class_l = cd_goods_class_l;
	}

	public String getCd_goods_class_m() {
		return cd_goods_class_m;
	}

	public void setCd_goods_class_m(String cd_goods_class_m) {
		this.cd_goods_class_m = cd_goods_class_m;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId_frt() {
		return id_frt;
	}

	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}

	public String getDt_frt() {
		return dt_frt;
	}

	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}

	public String getId_lst() {
		return id_lst;
	}

	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}

	public String getDt_lst() {
		return dt_lst;
	}

	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}


}
