package com.koscom.coocongoodsbank.model;

import com.koscom.comm.model.SearchForm;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class GoodsbankForm extends SearchForm implements Serializable {
	private static final long serialVersionUID = -7693376532078788852L;
	private String no_person;
	private String cd_div_goodsbank;
	private String cd_goodsbank_type;
	private String tab_type;
	private String id_agency;
	private String yn_open;
	private String yn_use;
	//20170629 상품 추가 수정적용 사항
	private String cd_fc;
	private String nm_fc;
	private String cd_goods;
	private String nm_goods;
	private String code_group;
	private String code_value;
	private String nm_code;
	private String etc;
	private String no_bunch;
	private String amt_limit;
	private String no_prepare;
	private String yn_alliance;
	private String cd_fin;
	
	private String cd_goods_class_l; 	//대분류
	private String cd_goods_class_m; 	//중분류
	private String cd_goods_class_s; 	//소분류
	
	private String cd_type_fc;
	
	public String getCd_goods() {
		return cd_goods;
	}
	public void setCd_goods(String cd_goods) {
		this.cd_goods = cd_goods;
	}
	public String getNm_goods() {
		return nm_goods;
	}
	public void setNm_goods(String nm_goods) {
		this.nm_goods = nm_goods;
	}
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
	public String getCd_div_goodsbank() {
		return cd_div_goodsbank;
	}
	public void setCd_div_goodsbank(String cd_div_goodsbank) {
		this.cd_div_goodsbank = cd_div_goodsbank;
	}
	public String getCd_goodsbank_type() {
		return cd_goodsbank_type;
	}
	public void setCd_goodsbank_type(String cd_goodsbank_type) {
		this.cd_goodsbank_type = cd_goodsbank_type;
	}
	public String getTab_type() {
		return tab_type;
	}
	public void setTab_type(String tab_type) {
		this.tab_type = tab_type;
	}
	public String getId_agency() {
		return id_agency;
	}
	public void setId_agency(String id_agency) {
		this.id_agency = id_agency;
	}
	public String getYn_open() {
		return yn_open;
	}
	public void setYn_open(String yn_open) {
		this.yn_open = yn_open;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	public String getCode_group() {
		return code_group;
	}
	public void setCode_group(String code_group) {
		this.code_group = code_group;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getNm_code() {
		return nm_code;
	}
	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getNo_bunch() {
		return no_bunch;
	}
	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
	public String getAmt_limit() {
		return amt_limit;
	}
	public void setAmt_limit(String amt_limit) {
		this.amt_limit = amt_limit;
	}
	public String getNo_prepare() {
		return no_prepare;
	}
	public void setNo_prepare(String no_prepare) {
		this.no_prepare = no_prepare;
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
	public String getCd_goods_class_s() {
		return cd_goods_class_s;
	}
	public void setCd_goods_class_s(String cd_goods_class_s) {
		this.cd_goods_class_s = cd_goods_class_s;
	}
	public String getCd_fin() {
		return cd_fin;
	}
	public void setCd_fin(String cd_fin) {
		this.cd_fin = cd_fin;
	}
	public String getCd_type_fc() {
		return cd_type_fc;
	}
	public void setCd_type_fc(String cd_type_fc) {
		this.cd_type_fc = cd_type_fc;
	}
	
}