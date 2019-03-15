package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment035 extends AbstractSegment{ // 대출연체이력정보(개인별)
	private static final long serialVersionUID = 2056743291929520817L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment035.class);

	private final String segID = "035";

	private String[] cnt_delay_before		= new String[12];		//연체건수
	private String[] amt_delay_before		= new String[12];		//연체금액
	private String[] cnt_longest_day_before = new String[12];		//최장연체일수

	private String filler;		// total = 210




	private String cnt_delay_before1;	//1개월전연체건수KCB_035_INFO                 //_1개월전연체건수;
	private String cnt_delay_before2;	//2개월전연체건수KCB_035_INFO                 	//_2개월전연체건수;
	private String cnt_delay_before3;	//3개월전연체건수KCB_035_INFO                 	//_3개월전연체건수;
	private String cnt_delay_before4;	//4개월전연체건수KCB_035_INFO                 	//_4개월전연체건수;
	private String cnt_delay_before5;	//5개월전연체건수KCB_035_INFO                 	//_5개월전연체건수;
	private String cnt_delay_before6;	//6개월전연체건수KCB_035_INFO                 	//_6개월전연체건수;
	private String cnt_delay_before7;	//7개월전연체건수KCB_035_INFO                 	//_7개월전연체건수;
	private String cnt_delay_before8;	//8개월전연체건수KCB_035_INFO                 	//_8개월전연체건수;
	private String cnt_delay_before9;	//9개월전연체건수KCB_035_INFO                 	//_9개월전연체건수;
	private String cnt_delay_before10;	//10개월전연체건수KCB_035_INFO              	//_10개월전연체건수;
	private String cnt_delay_before11;	//11개월전연체건수KCB_035_INFO              	//_11개월전연체건수;
	private String cnt_delay_before12;	//12개월전연체건수KCB_035_INFO              	//_12개월전연체건수;
	private String amt_delay_before1;	//1개월전연체금액KCB_035_INFO                 	//_1개월전연체금액;
	private String amt_delay_before2;	//2개월전연체금액KCB_035_INFO                 	//_2개월전연체금액;
	private String amt_delay_before3;	//3개월전연체금액KCB_035_INFO                 	//_3개월전연체금액;
	private String amt_delay_before4;	//4개월전연체금액KCB_035_INFO                 	//_4개월전연체금액;
	private String amt_delay_before5;	//5개월전연체금액KCB_035_INFO                 	//_5개월전연체금액;
	private String amt_delay_before6;	//6개월전연체금액KCB_035_INFO                 	//_6개월전연체금액;
	private String amt_delay_before7;	//7개월전연체금액KCB_035_INFO                 	//_7개월전연체금액;
	private String amt_delay_before8;	//8개월전연체금액KCB_035_INFO                 	//_8개월전연체금액;
	private String amt_delay_before9;	//9개월전연체금액KCB_035_INFO                 	//_9개월전연체금액;
	private String amt_delay_before10;	//10개월전연체금액KCB_035_INFO              	//_10개월전연체금액;
	private String amt_delay_before11;	//11개월전연체금액KCB_035_INFO              	//_11개월전연체금액;
	private String amt_delay_before12;	//12개월전연체금액KCB_035_INFO              	//_12개월전연체금액;
	private String cnt_longest_day_before1;	//1개월전최장연체일수KCB_035_INFO         	//_1개월전최장연체일수;
	private String cnt_longest_day_before2;	//2개월전최장연체일수KCB_035_INFO         	//_2개월전최장연체일수;
	private String cnt_longest_day_before3;	//3개월전최장연체일수KCB_035_INFO         	//_3개월전최장연체일수;
	private String cnt_longest_day_before4;	//4개월전최장연체일수KCB_035_INFO         	//_4개월전최장연체일수;
	private String cnt_longest_day_before5;	//5개월전최장연체일수KCB_035_INFO         	//_5개월전최장연체일수;
	private String cnt_longest_day_before6;	//6개월전최장연체일수KCB_035_INFO         	//_6개월전최장연체일수;
	private String cnt_longest_day_before7;	//7개월전최장연체일수KCB_035_INFO         	//_7개월전최장연체일수;
	private String cnt_longest_day_before8;	//8개월전최장연체일수KCB_035_INFO         	//_8개월전최장연체일수;
	private String cnt_longest_day_before9;	//9개월전최장연체일수KCB_035_INFO         	//_9개월전최장연체일수;
	private String cnt_longest_day_before10;	//10개월전최장연체일수KCB_035_INFO      	//_10개월전최장연체일수;
	private String cnt_longest_day_before11;	//11개월전최장연체일수KCB_035_INFO      	//_11개월전최장연체일수;
	private String cnt_longest_day_before12;	//12개월전최장연체일수KCB_035_INFO      //_12개월전최장연체일수;

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
        Kcb_Segment035 seq035  = new Kcb_Segment035();

        pos+=len; len=3; // segID
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;				seq035.setCnt_delay_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;              seq035.setCnt_delay_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;              seq035.setCnt_delay_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;              seq035.setCnt_delay_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;              seq035.setCnt_delay_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;              seq035.setCnt_delay_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;              seq035.setCnt_delay_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;              seq035.setCnt_delay_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;              seq035.setCnt_delay_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;              seq035.setCnt_delay_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 10) ;             seq035.setCnt_delay_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=3; seq035.setCnt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 11) ;             seq035.setCnt_delay_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;              seq035.setAmt_delay_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;              seq035.setAmt_delay_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;              seq035.setAmt_delay_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;              seq035.setAmt_delay_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;              seq035.setAmt_delay_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;              seq035.setAmt_delay_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;              seq035.setAmt_delay_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;              seq035.setAmt_delay_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;              seq035.setAmt_delay_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;              seq035.setAmt_delay_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 10) ;             seq035.setAmt_delay_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=9; seq035.setAmt_delay_before(StringUtil.getByte2String(bt,pos,len).trim(), 11) ;             seq035.setAmt_delay_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;        seq035.setCnt_longest_day_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;        seq035.setCnt_longest_day_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;        seq035.setCnt_longest_day_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;        seq035.setCnt_longest_day_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;        seq035.setCnt_longest_day_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;        seq035.setCnt_longest_day_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;        seq035.setCnt_longest_day_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;        seq035.setCnt_longest_day_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;        seq035.setCnt_longest_day_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;        seq035.setCnt_longest_day_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 10) ;       seq035.setCnt_longest_day_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=4; seq035.setCnt_longest_day_before(StringUtil.getByte2String(bt,pos,len).trim(), 11) ;       seq035.setCnt_longest_day_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
        pos+=len; len=15; // filler
        pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

        map.put("retObj", seq035);
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

	public String[] getCnt_delay_before() {
		return cnt_delay_before;
	}

	public String getCnt_delay_before(int i) {
		return cnt_delay_before[i];
	}

	public void setCnt_delay_before(String[] cnt_delay_before) {
		this.cnt_delay_before = cnt_delay_before;
	}

	public void setCnt_delay_before(String cnt_delay_before, int i) {
		this.cnt_delay_before[i] = cnt_delay_before;
	}

	public String[] getAmt_delay_before() {
		return amt_delay_before;
	}

	public String getAmt_delay_before(int i) {
		return amt_delay_before[i];
	}

	public void setAmt_delay_before(String[] amt_delay_before) {
		this.amt_delay_before = amt_delay_before;
	}

	public void setAmt_delay_before(String amt_delay_before, int i) {
		this.amt_delay_before[i] = amt_delay_before;
	}

	public String[] getCnt_longest_day_before() {
		return cnt_longest_day_before;
	}

	public String getCnt_longest_day_before(int i) {
		return cnt_longest_day_before[i];
	}

	public void setCnt_longest_day_before(String[] cnt_longest_day_before) {
		this.cnt_longest_day_before = cnt_longest_day_before;
	}

	public void setCnt_longest_day_before(String cnt_longest_day_before, int i) {
		this.cnt_longest_day_before[i] = cnt_longest_day_before;
	}









	public String getCnt_delay_before1() {
		return cnt_delay_before1;
	}

	public void setCnt_delay_before1(String cnt_delay_before1) {
		this.cnt_delay_before1 = cnt_delay_before1;
	}

	public String getCnt_delay_before2() {
		return cnt_delay_before2;
	}

	public void setCnt_delay_before2(String cnt_delay_before2) {
		this.cnt_delay_before2 = cnt_delay_before2;
	}

	public String getCnt_delay_before3() {
		return cnt_delay_before3;
	}

	public void setCnt_delay_before3(String cnt_delay_before3) {
		this.cnt_delay_before3 = cnt_delay_before3;
	}

	public String getCnt_delay_before4() {
		return cnt_delay_before4;
	}

	public void setCnt_delay_before4(String cnt_delay_before4) {
		this.cnt_delay_before4 = cnt_delay_before4;
	}

	public String getCnt_delay_before5() {
		return cnt_delay_before5;
	}

	public void setCnt_delay_before5(String cnt_delay_before5) {
		this.cnt_delay_before5 = cnt_delay_before5;
	}

	public String getCnt_delay_before6() {
		return cnt_delay_before6;
	}

	public void setCnt_delay_before6(String cnt_delay_before6) {
		this.cnt_delay_before6 = cnt_delay_before6;
	}

	public String getCnt_delay_before7() {
		return cnt_delay_before7;
	}

	public void setCnt_delay_before7(String cnt_delay_before7) {
		this.cnt_delay_before7 = cnt_delay_before7;
	}

	public String getCnt_delay_before8() {
		return cnt_delay_before8;
	}

	public void setCnt_delay_before8(String cnt_delay_before8) {
		this.cnt_delay_before8 = cnt_delay_before8;
	}

	public String getCnt_delay_before9() {
		return cnt_delay_before9;
	}

	public void setCnt_delay_before9(String cnt_delay_before9) {
		this.cnt_delay_before9 = cnt_delay_before9;
	}

	public String getCnt_delay_before10() {
		return cnt_delay_before10;
	}

	public void setCnt_delay_before10(String cnt_delay_before10) {
		this.cnt_delay_before10 = cnt_delay_before10;
	}

	public String getCnt_delay_before11() {
		return cnt_delay_before11;
	}

	public void setCnt_delay_before11(String cnt_delay_before11) {
		this.cnt_delay_before11 = cnt_delay_before11;
	}

	public String getCnt_delay_before12() {
		return cnt_delay_before12;
	}

	public void setCnt_delay_before12(String cnt_delay_before12) {
		this.cnt_delay_before12 = cnt_delay_before12;
	}

	public String getAmt_delay_before1() {
		return amt_delay_before1;
	}

	public void setAmt_delay_before1(String amt_delay_before1) {
		this.amt_delay_before1 = amt_delay_before1;
	}

	public String getAmt_delay_before2() {
		return amt_delay_before2;
	}

	public void setAmt_delay_before2(String amt_delay_before2) {
		this.amt_delay_before2 = amt_delay_before2;
	}

	public String getAmt_delay_before3() {
		return amt_delay_before3;
	}

	public void setAmt_delay_before3(String amt_delay_before3) {
		this.amt_delay_before3 = amt_delay_before3;
	}

	public String getAmt_delay_before4() {
		return amt_delay_before4;
	}

	public void setAmt_delay_before4(String amt_delay_before4) {
		this.amt_delay_before4 = amt_delay_before4;
	}

	public String getAmt_delay_before5() {
		return amt_delay_before5;
	}

	public void setAmt_delay_before5(String amt_delay_before5) {
		this.amt_delay_before5 = amt_delay_before5;
	}

	public String getAmt_delay_before6() {
		return amt_delay_before6;
	}

	public void setAmt_delay_before6(String amt_delay_before6) {
		this.amt_delay_before6 = amt_delay_before6;
	}

	public String getAmt_delay_before7() {
		return amt_delay_before7;
	}

	public void setAmt_delay_before7(String amt_delay_before7) {
		this.amt_delay_before7 = amt_delay_before7;
	}

	public String getAmt_delay_before8() {
		return amt_delay_before8;
	}

	public void setAmt_delay_before8(String amt_delay_before8) {
		this.amt_delay_before8 = amt_delay_before8;
	}

	public String getAmt_delay_before9() {
		return amt_delay_before9;
	}

	public void setAmt_delay_before9(String amt_delay_before9) {
		this.amt_delay_before9 = amt_delay_before9;
	}

	public String getAmt_delay_before10() {
		return amt_delay_before10;
	}

	public void setAmt_delay_before10(String amt_delay_before10) {
		this.amt_delay_before10 = amt_delay_before10;
	}

	public String getAmt_delay_before11() {
		return amt_delay_before11;
	}

	public void setAmt_delay_before11(String amt_delay_before11) {
		this.amt_delay_before11 = amt_delay_before11;
	}

	public String getAmt_delay_before12() {
		return amt_delay_before12;
	}

	public void setAmt_delay_before12(String amt_delay_before12) {
		this.amt_delay_before12 = amt_delay_before12;
	}

	public String getCnt_longest_day_before1() {
		return cnt_longest_day_before1;
	}

	public void setCnt_longest_day_before1(String cnt_longest_day_before1) {
		this.cnt_longest_day_before1 = cnt_longest_day_before1;
	}

	public String getCnt_longest_day_before2() {
		return cnt_longest_day_before2;
	}

	public void setCnt_longest_day_before2(String cnt_longest_day_before2) {
		this.cnt_longest_day_before2 = cnt_longest_day_before2;
	}

	public String getCnt_longest_day_before3() {
		return cnt_longest_day_before3;
	}

	public void setCnt_longest_day_before3(String cnt_longest_day_before3) {
		this.cnt_longest_day_before3 = cnt_longest_day_before3;
	}

	public String getCnt_longest_day_before4() {
		return cnt_longest_day_before4;
	}

	public void setCnt_longest_day_before4(String cnt_longest_day_before4) {
		this.cnt_longest_day_before4 = cnt_longest_day_before4;
	}

	public String getCnt_longest_day_before5() {
		return cnt_longest_day_before5;
	}

	public void setCnt_longest_day_before5(String cnt_longest_day_before5) {
		this.cnt_longest_day_before5 = cnt_longest_day_before5;
	}

	public String getCnt_longest_day_before6() {
		return cnt_longest_day_before6;
	}

	public void setCnt_longest_day_before6(String cnt_longest_day_before6) {
		this.cnt_longest_day_before6 = cnt_longest_day_before6;
	}

	public String getCnt_longest_day_before7() {
		return cnt_longest_day_before7;
	}

	public void setCnt_longest_day_before7(String cnt_longest_day_before7) {
		this.cnt_longest_day_before7 = cnt_longest_day_before7;
	}

	public String getCnt_longest_day_before8() {
		return cnt_longest_day_before8;
	}

	public void setCnt_longest_day_before8(String cnt_longest_day_before8) {
		this.cnt_longest_day_before8 = cnt_longest_day_before8;
	}

	public String getCnt_longest_day_before9() {
		return cnt_longest_day_before9;
	}

	public void setCnt_longest_day_before9(String cnt_longest_day_before9) {
		this.cnt_longest_day_before9 = cnt_longest_day_before9;
	}

	public String getCnt_longest_day_before10() {
		return cnt_longest_day_before10;
	}

	public void setCnt_longest_day_before10(String cnt_longest_day_before10) {
		this.cnt_longest_day_before10 = cnt_longest_day_before10;
	}

	public String getCnt_longest_day_before11() {
		return cnt_longest_day_before11;
	}

	public void setCnt_longest_day_before11(String cnt_longest_day_before11) {
		this.cnt_longest_day_before11 = cnt_longest_day_before11;
	}

	public String getCnt_longest_day_before12() {
		return cnt_longest_day_before12;
	}

	public void setCnt_longest_day_before12(String cnt_longest_day_before12) {
		this.cnt_longest_day_before12 = cnt_longest_day_before12;
	}

}
