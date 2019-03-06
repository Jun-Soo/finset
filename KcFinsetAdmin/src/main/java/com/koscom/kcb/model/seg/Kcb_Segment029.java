package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.koscom.util.FinsetException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kcb_Segment029 extends AbstractSegment{
    private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment029.class);

    private static final long serialVersionUID = 5170632292832179885L;
	private final String segID = "029";
	private String 	dt_regist_home;	//자택주소등록일자KCB_029_INFO      		//자택주소등록일자;
	private String 	post_home;	//자택우편번호KCB_029_INFO            		//자택우편번호;
	private String 	addr1_home;	//자택주소상KCB_029_INFO             		//자택우편번호주소;
	private String 	addr2_home;	//자택주소하KCB_029_INFO             		//자택상세주소;
	private String 	tel_home;	//자택전화번호KCB_029_INFO              		//자택전화번호;
	private String 	dt_regist_real;	//실거주지주소등록일자KCB_029_INFO    		//실거주지주소등록일자;
	private String 	post_real;	//실거주지우편번호KCB_029_INFO          		//실거주지우편번호;
	private String 	addr1_real;	//실거주지주소상KCB_029_INFO           		//실거주지우편번호주소;
	private String 	addr2_real;	//실거주지주소하KCB_029_INFO           		//실거주지상세주소;
	private String 	tel_real;	//실거주지전화번호KCB_029_INFO            	//실거주지전화번호;
	private String 	dt_regist_comp;	//직장주소등록일자KCB_029_INFO      		//직장주소등록일자;
	private String 	post_comp;	//직장우편번호KCB_029_INFO            		//직장우편번호;
	private String 	addr1_comp;	//직장주소상KCB_029_INFO             		//직장우편번호주소;
	private String 	addr2_comp;	//직장주소하KCB_029_INFO             		//직장상세주소;
	private String 	tel_comp;	//직장전화번호KCB_029_INFO              		//직장전화번호;
	private String 	tel_ext_comp;	//직장전화내선번호KCB_029_INFO        	//직장전화내선번호;
	private String 	nm_comp;	//직장명KCB_029_INFO                 		//직장명;
	private String 	nm_dept;	//부서명KCB_029_INFO                 		//부서명;
	private String 	ym_start_comp;	//현직장입사년월KCB_029_INFO       		//현직장입사년월;
	private String 	ymd_year_income;	//연소득등록일자KCB_029_INFO     		//연소득등록일자;
	private String 	amt_year_income;	//연소득KCB_029_INFO         		//연소득;
	private String 	ymd_property_tax;	//재산세등록일자KCB_029_INFO     		//재산세등록일자;
	private String 	amt_property_tax;	//재산세KCB_029_INFO         		//재산세;
	private String 	ymd_hp;	//휴대폰번호등록일자KCB_029_INFO             		//휴대폰번호등록일자;
	private String 	hp;	//휴대폰번호KCB_029_INFO                     		//휴대폰번호;
	private String 	cd_addr_home;	//자택주소구분KCB_029_INFO          		//자택주소구분;
	private String 	cd_real_home;	//실거주지주소구분KCB_029_INFO        		//실거주지주소구분;
	private String 	cd_addr_comp;	//직장주소구분KCB_029_INFO          		//직장주소구분;
	private String 	ymd_email;	//이메일주소등록일자KCB_029_INFO         		//이메일주소등록일자;
	private String 	email;	//이메일주소KCB_029_INFO                 		//이메일주소;
	private String 	filler;	//FILLERKCB_029_INFO                		//filler;		// total = 903

	// 모델 출력 스타일 설정
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public HashMap<String, Object> getParseDataByResData(String retStr) throws UnsupportedEncodingException  {
		StringBuffer str = new StringBuffer();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pos = 0;
		int len = 0;

		byte[] bt = null;
        bt = retStr.getBytes();
        Kcb_Segment029 seq029  = new Kcb_Segment029();

        pos+=len; len=3; // segID
        pos+=len; len=8; seq029.setDt_regist_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=6; seq029.setPost_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=100; seq029.setAddr1_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=100; seq029.setAddr2_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=12; seq029.setTel_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; seq029.setDt_regist_real(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=6; seq029.setPost_real(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=100; seq029.setAddr1_real(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=100; seq029.setAddr2_real(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=12; seq029.setTel_real(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; seq029.setDt_regist_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=6; seq029.setPost_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=100; seq029.setAddr1_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=100; seq029.setAddr2_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=12; seq029.setTel_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq029.setTel_ext_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=50; seq029.setNm_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=50; seq029.setNm_dept(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=6; seq029.setYm_start_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; seq029.setYmd_year_income(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq029.setAmt_year_income(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; seq029.setYmd_property_tax(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq029.setAmt_property_tax(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; seq029.setYmd_hp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=12; seq029.setHp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=1; seq029.setCd_addr_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=1; seq029.setCd_real_home(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=1; seq029.setCd_addr_comp(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; seq029.setYmd_email(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=50; seq029.setEmail(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=14; // filler
        pos+=len; len=bt.length-pos;
        str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

        map.put("retObj", seq029);
        map.put("retStr", str.toString());


		return map;
	}


	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getSegID() {
		return segID;
	}

	public String getDt_regist_home() {
		return dt_regist_home;
	}

	public void setDt_regist_home(String dt_regist_home) {
		this.dt_regist_home = dt_regist_home;
	}

	public String getPost_home() {
		return post_home;
	}

	public void setPost_home(String post_home) {
		this.post_home = post_home;
	}

	public String getAddr1_home() {
		return addr1_home;
	}

	public void setAddr1_home(String addr1_home) {
		this.addr1_home = addr1_home;
	}

	public String getAddr2_home() {
		return addr2_home;
	}

	public void setAddr2_home(String addr2_home) {
		this.addr2_home = addr2_home;
	}

	public String getTel_home() {
		return tel_home;
	}

	public void setTel_home(String tel_home) {
		this.tel_home = tel_home;
	}

	public String getDt_regist_real() {
		return dt_regist_real;
	}

	public void setDt_regist_real(String dt_regist_real) {
		this.dt_regist_real = dt_regist_real;
	}

	public String getPost_real() {
		return post_real;
	}

	public void setPost_real(String post_real) {
		this.post_real = post_real;
	}

	public String getAddr1_real() {
		return addr1_real;
	}

	public void setAddr1_real(String addr1_real) {
		this.addr1_real = addr1_real;
	}

	public String getAddr2_real() {
		return addr2_real;
	}

	public void setAddr2_real(String addr2_real) {
		this.addr2_real = addr2_real;
	}

	public String getTel_real() {
		return tel_real;
	}

	public void setTel_real(String tel_real) {
		this.tel_real = tel_real;
	}

	public String getDt_regist_comp() {
		return dt_regist_comp;
	}

	public void setDt_regist_comp(String dt_regist_comp) {
		this.dt_regist_comp = dt_regist_comp;
	}

	public String getPost_comp() {
		return post_comp;
	}

	public void setPost_comp(String post_comp) {
		this.post_comp = post_comp;
	}

	public String getAddr1_comp() {
		return addr1_comp;
	}

	public void setAddr1_comp(String addr1_comp) {
		this.addr1_comp = addr1_comp;
	}

	public String getAddr2_comp() {
		return addr2_comp;
	}

	public void setAddr2_comp(String addr2_comp) {
		this.addr2_comp = addr2_comp;
	}

	public String getTel_comp() {
		return tel_comp;
	}

	public void setTel_comp(String tel_comp) {
		this.tel_comp = tel_comp;
	}

	public String getTel_ext_comp() {
		return tel_ext_comp;
	}

	public void setTel_ext_comp(String tel_ext_comp) {
		this.tel_ext_comp = tel_ext_comp;
	}

	public String getNm_comp() {
		return nm_comp;
	}

	public void setNm_comp(String nm_comp) {
		this.nm_comp = nm_comp;
	}

	public String getNm_dept() {
		return nm_dept;
	}

	public void setNm_dept(String nm_dept) {
		this.nm_dept = nm_dept;
	}

	public String getYm_start_comp() {
		return ym_start_comp;
	}

	public void setYm_start_comp(String ym_start_comp) {
		this.ym_start_comp = ym_start_comp;
	}

	public String getYmd_year_income() {
		return ymd_year_income;
	}

	public void setYmd_year_income(String ymd_year_income) {
		this.ymd_year_income = ymd_year_income;
	}

	public String getAmt_year_income() {
		return amt_year_income;
	}

	public void setAmt_year_income(String amt_year_income) {
		this.amt_year_income = amt_year_income;
	}

	public String getYmd_property_tax() {
		return ymd_property_tax;
	}

	public void setYmd_property_tax(String ymd_property_tax) {
		this.ymd_property_tax = ymd_property_tax;
	}

	public String getAmt_property_tax() {
		return amt_property_tax;
	}

	public void setAmt_property_tax(String amt_property_tax) {
		this.amt_property_tax = amt_property_tax;
	}

	public String getYmd_hp() {
		return ymd_hp;
	}

	public void setYmd_hp(String ymd_hp) {
		this.ymd_hp = ymd_hp;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCd_addr_home() {
		return cd_addr_home;
	}

	public void setCd_addr_home(String cd_addr_home) {
		this.cd_addr_home = cd_addr_home;
	}

	public String getCd_real_home() {
		return cd_real_home;
	}

	public void setCd_real_home(String cd_real_home) {
		this.cd_real_home = cd_real_home;
	}

	public String getCd_addr_comp() {
		return cd_addr_comp;
	}

	public void setCd_addr_comp(String cd_addr_comp) {
		this.cd_addr_comp = cd_addr_comp;
	}

	public String getYmd_email() {
		return ymd_email;
	}

	public void setYmd_email(String ymd_email) {
		this.ymd_email = ymd_email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
