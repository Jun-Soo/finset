package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment062 extends AbstractSegment{
	private static final long serialVersionUID = 141906920489030205L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment062.class);

	private final String segId = "062";

	private String no_person;
	private String nm_biz_type;			  	//업종명
	private String nm_trade;			   	//거래기관명
	private String nm_agency;			   	//관리점명
	private String ymd_frt_stead_pay;		//대지급발생일자
	private String amt_stead_pay;		  	//대지급금액
	private String ymd_stead_repay;		  	//상환일자
	private String amt_repay;			   	//총상환금액
	private String filler;

	// 모델 출력 스타일 설정
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public HashMap<String, Object> getParseDataByResData(String retStr) throws UnsupportedEncodingException {
		StringBuffer str = new StringBuffer();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pos = 0;
		int len = 0;
		byte[] bt = null;

		bt = retStr.getBytes();
		Kcb_Segment062 seg062  = new Kcb_Segment062();

		pos+=len; len=3; //segID = StringUtil.getByte2String(bt,pos,len).trim() ;
		pos+=len; len=20; seg062.setNm_biz_type(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=20; seg062.setNm_trade(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=40; seg062.setNm_agency(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=8; seg062.setYmd_frt_stead_pay(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=9; seg062.setAmt_stead_pay(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=8; seg062.setYmd_stead_repay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9; seg062.setAmt_repay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=23; //filler = StringUtil.getByte2String(bt,pos,len).trim() ;
		pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

		map.put("retObj", seg062);
		map.put("retStr", str.toString());

		return map;
	}


	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getSegId() {
		return segId;
	}

	public String getNm_biz_type() {
		return nm_biz_type;
	}

	public String getNm_trade() {
		return nm_trade;
	}

	public String getNm_agency() {
		return nm_agency;
	}

	public String getYmd_frt_stead_pay() {
		return ymd_frt_stead_pay;
	}

	public String getAmt_stead_pay() {
		return amt_stead_pay;
	}

	public String getYmd_stead_repay() {
		return ymd_stead_repay;
	}

	public String getAmt_repay() {
		return amt_repay;
	}

	public void setNm_biz_type(String nm_biz_type) {
		this.nm_biz_type = nm_biz_type;
	}

	public void setNm_trade(String nm_trade) {
		this.nm_trade = nm_trade;
	}

	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}

	public void setYmd_frt_stead_pay(String ymd_frt_stead_pay) {
		this.ymd_frt_stead_pay = ymd_frt_stead_pay;
	}

	public void setAmt_stead_pay(String amt_stead_pay) {
		this.amt_stead_pay = amt_stead_pay;
	}

	public void setYmd_stead_repay(String ymd_stead_repay) {
		this.ymd_stead_repay = ymd_stead_repay;
	}

	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}

}
