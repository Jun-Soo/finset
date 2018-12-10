package com.koscom.goods.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.comm.model.SearchForm;

public class GoodsForm extends SearchForm implements Serializable {
    private static final long serialVersionUID = -7693376532078788852L;
    private String no_person;
    private String cd_div_goods;
    private String cd_goods_type;
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
    private String loan_code;
    private String yn_alliance;

    private String cd_goods_class; 		//마이페이지 관심상품 - 신용대출/담보대출 구분 
    private String cd_goods_class_l; 	//대분류
    private String cd_goods_class_m; 	//중분류
    private String[] cd_goods_array_m; 	//중분류[]
    private String cd_goods_class_s; 	//소분류
    
    private String cd_ratio_type; 	//금리방식
	private String cd_type_pay; 	//상환방식
	
	private String[] cd_ratio_type_array;	//금리방식
	private String[] cd_type_pay_array; 	//상환방식
	
    private String cd_goods_alliance; //제휴상품코드
    private String curTab; //제휴상품코드
    private String orderby; //소트순서
    private String st; //시작플래그

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    public String getCd_goods_alliance(){
        return cd_goods_alliance;
    }
    public void setCd_goods_alliance(String cd_goods_alliance){
        this.cd_goods_alliance = cd_goods_alliance;
    }
    public String getNo_person() {
        return no_person;
    }
    public void setNo_person(String no_person) {
        this.no_person = no_person;
    }
    public String getCd_div_goods() {
        return cd_div_goods;
    }
    public void setCd_div_goods(String cd_div_goods) {
        this.cd_div_goods = cd_div_goods;
    }
    public String getCd_goods_type() {
        return cd_goods_type;
    }
    public void setCd_goods_type(String cd_goods_type) {
        this.cd_goods_type = cd_goods_type;
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
    public String getLoan_code() {
        return loan_code;
    }
    public void setLoan_code(String loan_code) {
        this.loan_code = loan_code;
    }
    public String getYn_alliance() {
        return yn_alliance;
    }
    public void setYn_alliance(String yn_alliance) {
        this.yn_alliance = yn_alliance;
    }
    public String getCd_goods_class() {
		return cd_goods_class;
	}
	public void setCd_goods_class(String cd_goods_class) {
		this.cd_goods_class = cd_goods_class;
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
    public String[] getCd_goods_array_m() {
		return cd_goods_array_m;
	}
	public void setCd_goods_array_m(String[] cd_goods_array_m) {
		this.cd_goods_array_m = cd_goods_array_m;
	}
	public String getCd_goods_class_s() {
        return cd_goods_class_s;
    }
    public void setCd_goods_class_s(String cd_goods_class_s) {
        this.cd_goods_class_s = cd_goods_class_s;
    }

    public String getCurTab() {
        return curTab;
    }

    public void setCurTab(String curTab) {
        this.curTab = curTab;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
    
    public String getCd_ratio_type() {
		return cd_ratio_type;
	}
	
	public void setCd_ratio_type(String cd_ratio_type) {
		this.cd_ratio_type = cd_ratio_type;
	}
	
	public String getCd_type_pay() {
		return cd_type_pay;
	}
	
	public void setCd_type_pay(String cd_type_pay) {
		this.cd_type_pay = cd_type_pay;
	}
	
	public String[] getCd_ratio_type_array() {
		return cd_ratio_type_array;
	}
	
	public void setCd_ratio_type_array(String[] cd_ratio_type_array) {
		this.cd_ratio_type_array = cd_ratio_type_array;
	}
	
	public String[] getCd_type_pay_array() {
		return cd_type_pay_array;
	}
	
	public void setCd_type_pay_array(String[] cd_type_pay_array) {
		this.cd_type_pay_array = cd_type_pay_array;
	}
}
