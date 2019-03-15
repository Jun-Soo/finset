package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment065 extends AbstractSegment{

	private static final long serialVersionUID = -7936546292390465967L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment065.class);

	private final String segId = "065";

	private String no_person;
	private String nm_trade;	//거래기관명
	private String nm_agency;	//관리점명
	private String cd_default;	//채무불이행(신용정보사)등록사유코드
	private String ymd_default;	//채무불이행(신용정보사)발생일자
	private String amt_regist;	//등록금액
	private String amt_delay;	//연체금액
	private String ymd_repay;	//상환일자
	private String amt_repay;	//상환금액
	private String filler;

	// 모델 출력 스타일 설정
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public HashMap<String, Object> getParseDataByResData(String retStr)  throws UnsupportedEncodingException {
		StringBuffer str = new StringBuffer();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pos = 0;
		int len = 0;
		byte[] bt = null;

		bt = retStr.getBytes();
		Kcb_Segment065 seg065  = new Kcb_Segment065();

		pos+=len; len=3; //segID = StringUtil.getByte2String(bt,pos,len).trim() ;
		pos+=len; len=20; seg065.setNm_trade(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=40; seg065.setNm_agency(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=4; seg065.setCd_default(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=8; seg065.setYmd_default(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=9; seg065.setAmt_regist(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=9; seg065.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8; seg065.setYmd_repay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9; seg065.setAmt_repay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=20; //filler = StringUtil.getByte2String(bt,pos,len).trim() ;
		pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

		map.put("retObj", seg065);
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

	public String getNm_trade() {
		return nm_trade;
	}

	public String getNm_agency() {
		return nm_agency;
	}

	public String getCd_default() {
		return cd_default;
	}

	public String getYmd_default() {
		return ymd_default;
	}

	public String getAmt_regist() {
		return amt_regist;
	}

	public String getAmt_delay() {
		return amt_delay;
	}

	public String getYmd_repay() {
		return ymd_repay;
	}

	public String getAmt_repay() {
		return amt_repay;
	}

	public void setNm_trade(String nm_trade) {
		this.nm_trade = nm_trade;
	}

	public void setNm_agency(String nm_agency) {
		this.nm_agency = nm_agency;
	}

	public void setCd_default(String cd_default) {
		this.cd_default = cd_default;
	}

	public void setYmd_default(String ymd_default) {
		this.ymd_default = ymd_default;
	}

	public void setAmt_regist(String amt_regist) {
		this.amt_regist = amt_regist;
	}

	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}

	public void setYmd_repay(String ymd_repay) {
		this.ymd_repay = ymd_repay;
	}

	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}


}
