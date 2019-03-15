package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment046 extends AbstractSegment{ // 카드연체이력정보(개인별)
	private static final long serialVersionUID = -762556987755085752L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment046.class);

	private final String segId = "046";

	private String no_person;
	private String[] cnt_delay		 = new String[12];	//연체건수
	private String[] amt_delay		 = new String[12];	//연체금액
	private String[] cnt_longest_day = new String[12];	//최장연체일수

	private String FILLER;		// total = 210

	// 세그먼트 외 변수
	private String seq_group;
	private String seq;
	private String basicYmd;
	private String callSegIdByMesg;
	private String ymd_frt;
	private String his_frt;





	private String cnt_delay_before1;	//1개월전연체건수KCB_046_INFO               //_1개월전연체건수;
	private String cnt_delay_before2;	//2개월전연체건수KCB_046_INFO               //_2개월전연체건수;
	private String cnt_delay_before3;	//3개월전연체건수KCB_046_INFO               //_3개월전연체건수;
	private String cnt_delay_before4;	//4개월전연체건수KCB_046_INFO               //_4개월전연체건수;
	private String cnt_delay_before5;	//5개월전연체건수KCB_046_INFO               //_5개월전연체건수;
	private String cnt_delay_before6;	//6개월전연체건수KCB_046_INFO               //_6개월전연체건수;
	private String cnt_delay_before7;	//7개월전연체건수KCB_046_INFO               //_7개월전연체건수;
	private String cnt_delay_before8;	//8개월전연체건수KCB_046_INFO               //_8개월전연체건수;
	private String cnt_delay_before9;	//9개월전연체건수KCB_046_INFO               //_9개월전연체건수;
	private String cnt_delay_before10;	//10개월전연체건수KCB_046_INFO              //_10개월전연체건수;
	private String cnt_delay_before11;	//11개월전연체건수KCB_046_INFO              //_11개월전연체건수;
	private String cnt_delay_before12;	//12개월전연체건수KCB_046_INFO              //_12개월전연체건수;

	private String amt_delay_before1;	//1개월전연체금액KCB_046_INFO               //_1개월전연체금액;
	private String amt_delay_before2;	//2개월전연체금액KCB_046_INFO               //_2개월전연체금액;
	private String amt_delay_before3;	//3개월전연체금액KCB_046_INFO               //_3개월전연체금액;
	private String amt_delay_before4;	//4개월전연체금액KCB_046_INFO               //_4개월전연체금액;
	private String amt_delay_before5;	//5개월전연체금액KCB_046_INFO               //_5개월전연체금액;
	private String amt_delay_before6;	//6개월전연체금액KCB_046_INFO               //_6개월전연체금액;
	private String amt_delay_before7;	//7개월전연체금액KCB_046_INFO               //_7개월전연체금액;
	private String amt_delay_before8;	//8개월전연체금액KCB_046_INFO               //_8개월전연체금액;
	private String amt_delay_before9;	//9개월전연체금액KCB_046_INFO               //_9개월전연체금액;
	private String amt_delay_before10;	//10개월전연체금액KCB_046_INFO              //_10개월전연체금액;
	private String amt_delay_before11;	//11개월전연체금액KCB_046_INFO              //_11개월전연체금액;
	private String amt_delay_before12;	//12개월전연체금액KCB_046_INFO              //_12개월전연체금액;

	private String cnt_longest_day_before1;	//1개월전최장연체일수KCB_046_INFO       //_1개월전최장연체일수;
	private String cnt_longest_day_before2;	//2개월전최장연체일수KCB_046_INFO       //_2개월전최장연체일수;
	private String cnt_longest_day_before3;	//3개월전최장연체일수KCB_046_INFO       //_3개월전최장연체일수;
	private String cnt_longest_day_before4;	//4개월전최장연체일수KCB_046_INFO       //_4개월전최장연체일수;
	private String cnt_longest_day_before5;	//5개월전최장연체일수KCB_046_INFO       //_5개월전최장연체일수;
	private String cnt_longest_day_before6;	//6개월전최장연체일수KCB_046_INFO       //_6개월전최장연체일수;
	private String cnt_longest_day_before7;	//7개월전최장연체일수KCB_046_INFO       //_7개월전최장연체일수;
	private String cnt_longest_day_before8;	//8개월전최장연체일수KCB_046_INFO       //_8개월전최장연체일수;
	private String cnt_longest_day_before9;	//9개월전최장연체일수KCB_046_INFO       //_9개월전최장연체일수;
	private String cnt_longest_day_before10;	//10개월전최장연체일수KCB_046_INFO      //_10개월전최장연체일수;
	private String cnt_longest_day_before11;	//11개월전최장연체일수KCB_046_INFO      //_11개월전최장연체일수;
	private String cnt_longest_day_before12;	//12개월전최장연체일수KCB_046_INFO      //_12개월전최장연체일수;



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
        Kcb_Segment046 seq046  = new Kcb_Segment046();

        pos+=len; len=3; // segID
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;        seq046.setCnt_delay_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;        seq046.setCnt_delay_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;        seq046.setCnt_delay_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;        seq046.setCnt_delay_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;        seq046.setCnt_delay_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;        seq046.setCnt_delay_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;        seq046.setCnt_delay_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;        seq046.setCnt_delay_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;        seq046.setCnt_delay_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;        seq046.setCnt_delay_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 10);        seq046.setCnt_delay_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; seq046.setCnt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 11);        seq046.setCnt_delay_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;        seq046.setAmt_delay_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;        seq046.setAmt_delay_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;        seq046.setAmt_delay_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;        seq046.setAmt_delay_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;        seq046.setAmt_delay_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;        seq046.setAmt_delay_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;        seq046.setAmt_delay_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;        seq046.setAmt_delay_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;        seq046.setAmt_delay_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;        seq046.setAmt_delay_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 10);        seq046.setAmt_delay_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq046.setAmt_delay(StringUtil.getByte2String(bt,pos,len).trim(), 11);        seq046.setAmt_delay_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;      seq046.setCnt_longest_day_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;      seq046.setCnt_longest_day_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;      seq046.setCnt_longest_day_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;      seq046.setCnt_longest_day_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;      seq046.setCnt_longest_day_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;      seq046.setCnt_longest_day_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;      seq046.setCnt_longest_day_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;      seq046.setCnt_longest_day_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;      seq046.setCnt_longest_day_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;      seq046.setCnt_longest_day_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 10);      seq046.setCnt_longest_day_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; seq046.setCnt_longest_day(StringUtil.getByte2String(bt,pos,len).trim(), 11);      seq046.setCnt_longest_day_before12(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=15; // filler
        pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

        map.put("retObj", seq046);
        map.put("retStr", str.toString());

		return map;
	}




	public String getFILLER() {
		return FILLER;
	}

	public void setFILLER(String filler) {
		FILLER = filler;
	}

	public String getSeq_group() {
		return seq_group;
	}

	public void setSeq_group(String seq_group) {
		this.seq_group = seq_group;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getBasicYmd() {
		return basicYmd;
	}

	public void setBasicYmd(String basicYmd) {
		this.basicYmd = basicYmd;
	}

	public String getCallSegIdByMesg() {
		return callSegIdByMesg;
	}

	public void setCallSegIdByMesg(String callSegIdByMesg) {
		this.callSegIdByMesg = callSegIdByMesg;
	}

	public String getYmd_frt() {
		return ymd_frt;
	}

	public void setYmd_frt(String ymd_frt) {
		this.ymd_frt = ymd_frt;
	}

	public String getHis_frt() {
		return his_frt;
	}

	public void setHis_frt(String his_frt) {
		this.his_frt = his_frt;
	}

	public String getSegId() {
		return segId;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String[] getCnt_delay() {
		return cnt_delay;
	}

	public void setCnt_delay(String[] cnt_delay) {
		this.cnt_delay = cnt_delay;
	}
	public void setCnt_delay(String cnt_delay, int i) {
		this.cnt_delay[i] = cnt_delay;
	}

	public String[] getAmt_delay() {
		return amt_delay;
	}

	public void setAmt_delay(String[] amt_delay) {
		this.amt_delay = amt_delay;
	}
	public void setAmt_delay(String amt_delay, int i) {
		this.amt_delay[i] = amt_delay;
	}

	public String[] getCnt_longest_day() {
		return cnt_longest_day;
	}

	public void setCnt_longest_day(String[] cnt_longest_day) {
		this.cnt_longest_day = cnt_longest_day;
	}
	public void setCnt_longest_day(String cnt_longest_day, int i) {
		this.cnt_longest_day[i] = cnt_longest_day;
	}






	public String getCnt_delay_before1() {
		return cnt_delay_before1;
	}

	public String getCnt_delay_before2() {
		return cnt_delay_before2;
	}

	public String getCnt_delay_before3() {
		return cnt_delay_before3;
	}

	public String getCnt_delay_before4() {
		return cnt_delay_before4;
	}

	public String getCnt_delay_before5() {
		return cnt_delay_before5;
	}

	public String getCnt_delay_before6() {
		return cnt_delay_before6;
	}

	public String getCnt_delay_before7() {
		return cnt_delay_before7;
	}

	public String getCnt_delay_before8() {
		return cnt_delay_before8;
	}

	public String getCnt_delay_before9() {
		return cnt_delay_before9;
	}

	public String getCnt_delay_before10() {
		return cnt_delay_before10;
	}

	public String getCnt_delay_before11() {
		return cnt_delay_before11;
	}

	public String getCnt_delay_before12() {
		return cnt_delay_before12;
	}

	public String getAmt_delay_before1() {
		return amt_delay_before1;
	}

	public String getAmt_delay_before2() {
		return amt_delay_before2;
	}

	public String getAmt_delay_before3() {
		return amt_delay_before3;
	}

	public String getAmt_delay_before4() {
		return amt_delay_before4;
	}

	public String getAmt_delay_before5() {
		return amt_delay_before5;
	}

	public String getAmt_delay_before6() {
		return amt_delay_before6;
	}

	public String getAmt_delay_before7() {
		return amt_delay_before7;
	}

	public String getAmt_delay_before8() {
		return amt_delay_before8;
	}

	public String getAmt_delay_before9() {
		return amt_delay_before9;
	}

	public String getAmt_delay_before10() {
		return amt_delay_before10;
	}

	public String getAmt_delay_before11() {
		return amt_delay_before11;
	}

	public String getAmt_delay_before12() {
		return amt_delay_before12;
	}

	public String getCnt_longest_day_before1() {
		return cnt_longest_day_before1;
	}

	public String getCnt_longest_day_before2() {
		return cnt_longest_day_before2;
	}

	public String getCnt_longest_day_before3() {
		return cnt_longest_day_before3;
	}

	public String getCnt_longest_day_before4() {
		return cnt_longest_day_before4;
	}

	public String getCnt_longest_day_before5() {
		return cnt_longest_day_before5;
	}

	public String getCnt_longest_day_before6() {
		return cnt_longest_day_before6;
	}

	public String getCnt_longest_day_before7() {
		return cnt_longest_day_before7;
	}

	public String getCnt_longest_day_before8() {
		return cnt_longest_day_before8;
	}

	public String getCnt_longest_day_before9() {
		return cnt_longest_day_before9;
	}

	public String getCnt_longest_day_before10() {
		return cnt_longest_day_before10;
	}

	public String getCnt_longest_day_before11() {
		return cnt_longest_day_before11;
	}

	public String getCnt_longest_day_before12() {
		return cnt_longest_day_before12;
	}

	public void setCnt_delay_before1(String cnt_delay_before1) {
		this.cnt_delay_before1 = cnt_delay_before1;
	}

	public void setCnt_delay_before2(String cnt_delay_before2) {
		this.cnt_delay_before2 = cnt_delay_before2;
	}

	public void setCnt_delay_before3(String cnt_delay_before3) {
		this.cnt_delay_before3 = cnt_delay_before3;
	}

	public void setCnt_delay_before4(String cnt_delay_before4) {
		this.cnt_delay_before4 = cnt_delay_before4;
	}

	public void setCnt_delay_before5(String cnt_delay_before5) {
		this.cnt_delay_before5 = cnt_delay_before5;
	}

	public void setCnt_delay_before6(String cnt_delay_before6) {
		this.cnt_delay_before6 = cnt_delay_before6;
	}

	public void setCnt_delay_before7(String cnt_delay_before7) {
		this.cnt_delay_before7 = cnt_delay_before7;
	}

	public void setCnt_delay_before8(String cnt_delay_before8) {
		this.cnt_delay_before8 = cnt_delay_before8;
	}

	public void setCnt_delay_before9(String cnt_delay_before9) {
		this.cnt_delay_before9 = cnt_delay_before9;
	}

	public void setCnt_delay_before10(String cnt_delay_before10) {
		this.cnt_delay_before10 = cnt_delay_before10;
	}

	public void setCnt_delay_before11(String cnt_delay_before11) {
		this.cnt_delay_before11 = cnt_delay_before11;
	}

	public void setCnt_delay_before12(String cnt_delay_before12) {
		this.cnt_delay_before12 = cnt_delay_before12;
	}

	public void setAmt_delay_before1(String amt_delay_before1) {
		this.amt_delay_before1 = amt_delay_before1;
	}

	public void setAmt_delay_before2(String amt_delay_before2) {
		this.amt_delay_before2 = amt_delay_before2;
	}

	public void setAmt_delay_before3(String amt_delay_before3) {
		this.amt_delay_before3 = amt_delay_before3;
	}

	public void setAmt_delay_before4(String amt_delay_before4) {
		this.amt_delay_before4 = amt_delay_before4;
	}

	public void setAmt_delay_before5(String amt_delay_before5) {
		this.amt_delay_before5 = amt_delay_before5;
	}

	public void setAmt_delay_before6(String amt_delay_before6) {
		this.amt_delay_before6 = amt_delay_before6;
	}

	public void setAmt_delay_before7(String amt_delay_before7) {
		this.amt_delay_before7 = amt_delay_before7;
	}

	public void setAmt_delay_before8(String amt_delay_before8) {
		this.amt_delay_before8 = amt_delay_before8;
	}

	public void setAmt_delay_before9(String amt_delay_before9) {
		this.amt_delay_before9 = amt_delay_before9;
	}

	public void setAmt_delay_before10(String amt_delay_before10) {
		this.amt_delay_before10 = amt_delay_before10;
	}

	public void setAmt_delay_before11(String amt_delay_before11) {
		this.amt_delay_before11 = amt_delay_before11;
	}

	public void setAmt_delay_before12(String amt_delay_before12) {
		this.amt_delay_before12 = amt_delay_before12;
	}

	public void setCnt_longest_day_before1(String cnt_longest_day_before1) {
		this.cnt_longest_day_before1 = cnt_longest_day_before1;
	}

	public void setCnt_longest_day_before2(String cnt_longest_day_before2) {
		this.cnt_longest_day_before2 = cnt_longest_day_before2;
	}

	public void setCnt_longest_day_before3(String cnt_longest_day_before3) {
		this.cnt_longest_day_before3 = cnt_longest_day_before3;
	}

	public void setCnt_longest_day_before4(String cnt_longest_day_before4) {
		this.cnt_longest_day_before4 = cnt_longest_day_before4;
	}

	public void setCnt_longest_day_before5(String cnt_longest_day_before5) {
		this.cnt_longest_day_before5 = cnt_longest_day_before5;
	}

	public void setCnt_longest_day_before6(String cnt_longest_day_before6) {
		this.cnt_longest_day_before6 = cnt_longest_day_before6;
	}

	public void setCnt_longest_day_before7(String cnt_longest_day_before7) {
		this.cnt_longest_day_before7 = cnt_longest_day_before7;
	}

	public void setCnt_longest_day_before8(String cnt_longest_day_before8) {
		this.cnt_longest_day_before8 = cnt_longest_day_before8;
	}

	public void setCnt_longest_day_before9(String cnt_longest_day_before9) {
		this.cnt_longest_day_before9 = cnt_longest_day_before9;
	}

	public void setCnt_longest_day_before10(String cnt_longest_day_before10) {
		this.cnt_longest_day_before10 = cnt_longest_day_before10;
	}

	public void setCnt_longest_day_before11(String cnt_longest_day_before11) {
		this.cnt_longest_day_before11 = cnt_longest_day_before11;
	}

	public void setCnt_longest_day_before12(String cnt_longest_day_before12) {
		this.cnt_longest_day_before12 = cnt_longest_day_before12;
	}

}
