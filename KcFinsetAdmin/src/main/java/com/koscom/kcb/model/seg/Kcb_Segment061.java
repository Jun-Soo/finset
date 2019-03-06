package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment061 extends AbstractSegment{

	private static final long serialVersionUID = 1399593765496585186L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment061.class);

	private final String segId = "061";

	private String no_person;

	private String nm_biz_type;			//업종명
	private String nm_trade;		   	//거래기관명
	private String nm_agency;		  	//관리점명
	private String cd_type_deal;		//거래형태코드
	private String ymd_frt_delay;	  	//최초연체기산일자
	private String ymd_delay;		   	//연체기산일자
	private String amt_frt_delay;		//최초연체금액
	private String amt_delay;		  	//연체금액
	private String yn_mat_delay;	 	//만기후연체여부
	private String cd_profit_loss;		//기한이익상실코드
	private String amt_remain;		   	//대출잔액_미도래잔액
	private String ymd_repay;		   	//연체상환일자
	private String amt_repay;		  	//상환금액
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
		Kcb_Segment061 seg061  = new Kcb_Segment061();

		pos+=len; len=3; //segID = StringUtil.getByte2String(bt,pos,len).trim() ;
		pos+=len; len=20; seg061.setNm_biz_type(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=20; seg061.setNm_trade(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=40; seg061.setNm_agency(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;  seg061.setCd_type_deal(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=8;  seg061.setYmd_frt_delay(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=8;  seg061.setYmd_delay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9;  seg061.setAmt_frt_delay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9;  seg061.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;  seg061.setYn_mat_delay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;  seg061.setCd_profit_loss(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9;  seg061.setAmt_remain(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8;  seg061.setYmd_repay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9;  seg061.setAmt_repay(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=24; //filler = StringUtil.getByte2String(bt,pos,len).trim() ;
	//pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len).trim());
		pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

		map.put("retObj", seg061);
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

	public String getCd_type_deal() {
		return cd_type_deal;
	}

	public String getYmd_frt_delay() {
		return ymd_frt_delay;
	}

	public String getYmd_delay() {
		return ymd_delay;
	}

	public String getAmt_frt_delay() {
		return amt_frt_delay;
	}

	public String getAmt_delay() {
		return amt_delay;
	}

	public String getYn_mat_delay() {
		return yn_mat_delay;
	}

	public String getCd_profit_loss() {
		return cd_profit_loss;
	}

	public String getAmt_remain() {
		return amt_remain;
	}

	public String getYmd_repay() {
		return ymd_repay;
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

	public void setCd_type_deal(String cd_type_deal) {
		this.cd_type_deal = cd_type_deal;
	}

	public void setYmd_frt_delay(String ymd_frt_delay) {
		this.ymd_frt_delay = ymd_frt_delay;
	}

	public void setYmd_delay(String ymd_delay) {
		this.ymd_delay = ymd_delay;
	}

	public void setAmt_frt_delay(String amt_frt_delay) {
		this.amt_frt_delay = amt_frt_delay;
	}

	public void setAmt_delay(String amt_delay) {
		this.amt_delay = amt_delay;
	}

	public void setYn_mat_delay(String yn_mat_delay) {
		this.yn_mat_delay = yn_mat_delay;
	}

	public void setCd_profit_loss(String cd_profit_loss) {
		this.cd_profit_loss = cd_profit_loss;
	}

	public void setAmt_remain(String amt_remain) {
		this.amt_remain = amt_remain;
	}

	public void setYmd_repay(String ymd_repay) {
		this.ymd_repay = ymd_repay;
	}

	public void setAmt_repay(String amt_repay) {
		this.amt_repay = amt_repay;
	}

}
