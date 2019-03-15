package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment034 extends AbstractSegment{ // 대출이력정보(개인별)
	private static final long serialVersionUID = -3737210492683795007L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment034.class);

	private final String segID = "034";

	private String no_person;

	private String[] cnt_loan_before = new String[12];
	private String[] amt_loan_before = new String[12];
	private String filler;		// total = 160



	private String cnt_loan_before1;	//1개월전대출건수KCB_034_INFO 		//_1개월전대출건수;
	private String cnt_loan_before2;	//2개월전대출건수KCB_034_INFO 		//_2개월전대출건수;
	private String cnt_loan_before3;	//3개월전대출건수KCB_034_INFO 		//_3개월전대출건수;
	private String cnt_loan_before4;	//4개월전대출건수KCB_034_INFO 		//_4개월전대출건수;
	private String cnt_loan_before5;	//5개월전대출건수KCB_034_INFO 		//_5개월전대출건수;
	private String cnt_loan_before6;	//6개월전대출건수KCB_034_INFO 		//_6개월전대출건수;
	private String cnt_loan_before7;	//7개월전대출건수KCB_034_INFO 		//_7개월전대출건수;
	private String cnt_loan_before8;	//8개월전대출건수KCB_034_INFO 		//_8개월전대출건수;
	private String cnt_loan_before9;	//9개월전대출건수KCB_034_INFO 		//_9개월전대출건수;
	private String cnt_loan_before10;	//10개월전대출건수KCB_034_INFO		//_10개월전대출건수;
	private String cnt_loan_before11;	//11개월전대출건수KCB_034_INFO		//_11개월전대출건수;
	private String cnt_loan_before12;	//12개월전대출건수KCB_034_INFO		//_12개월전대출건수;
	private String amt_loan_before1;	//1개월전대출금액KCB_034_INFO 		//_1개월전대출금액;
	private String amt_loan_before2;	//2개월전대출금액KCB_034_INFO 		//_2개월전대출금액;
	private String amt_loan_before3;	//3개월전대출금액KCB_034_INFO 		//_3개월전대출금액;
	private String amt_loan_before4;	//4개월전대출금액KCB_034_INFO 		//_4개월전대출금액;
	private String amt_loan_before5;	//5개월전대출금액KCB_034_INFO 		//_5개월전대출금액;
	private String amt_loan_before6;	//6개월전대출금액KCB_034_INFO 		//_6개월전대출금액;
	private String amt_loan_before7;	//7개월전대출금액KCB_034_INFO 		//_7개월전대출금액;
	private String amt_loan_before8;	//8개월전대출금액KCB_034_INFO 		//_8개월전대출금액;
	private String amt_loan_before9;	//9개월전대출금액KCB_034_INFO 		//_9개월전대출금액;
	private String amt_loan_before10;	//10개월전대출금액KCB_034_INFO		//_10개월전대출금액;
	private String amt_loan_before11;	//11개월전대출금액KCB_034_INFO		//_11개월전대출금액;
	private String amt_loan_before12;	//12개월전대출금액KCB_034_INFO		//_12개월전대출금액;



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

			byte[] bt = retStr.getBytes();

				Kcb_Segment034 seq034  = new Kcb_Segment034();

				pos+=len; len=3; // segID
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;    seq034.setCnt_loan_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;    seq034.setCnt_loan_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;    seq034.setCnt_loan_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;    seq034.setCnt_loan_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;    seq034.setCnt_loan_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;    seq034.setCnt_loan_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;    seq034.setCnt_loan_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;    seq034.setCnt_loan_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;    seq034.setCnt_loan_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;    seq034.setCnt_loan_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 10) ;   seq034.setCnt_loan_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=3; seq034.setCnt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 11) ;   seq034.setCnt_loan_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;    seq034.setAmt_loan_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;    seq034.setAmt_loan_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;    seq034.setAmt_loan_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;    seq034.setAmt_loan_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;    seq034.setAmt_loan_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;    seq034.setAmt_loan_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;    seq034.setAmt_loan_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;    seq034.setAmt_loan_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;    seq034.setAmt_loan_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;    seq034.setAmt_loan_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 10) ;   seq034.setAmt_loan_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=9; seq034.setAmt_loan_before(StringUtil.getByte2String(bt,pos,len).trim(), 11) ;   seq034.setAmt_loan_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
				pos+=len; len=13; // filler
				pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

			map.put("retObj", seq034);
			map.put("retStr", str.toString());



		return map;
	}



	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String getSegID() {
		return segID;
	}

	public String[] getCnt_loan_before() {
		return cnt_loan_before;
	}

	public String getCnt_loan_before(int i) {
		return cnt_loan_before[i];
	}

	public void setCnt_loan_before(String[] cnt_loan_before) {
		this.cnt_loan_before = cnt_loan_before;
	}

	public void setCnt_loan_before(String cnt_loan_before, int i) {
		this.cnt_loan_before[i] = cnt_loan_before;
	}

	public String[] getAmt_loan_before() {
		return amt_loan_before;
	}

	public String getAmt_loan_before(int i) {
		return amt_loan_before[i];
	}

	public void setAmt_loan_before(String[] amt_loan_before) {
		this.amt_loan_before = amt_loan_before;
	}

	public void setAmt_loan_before(String amt_loan_before, int i) {
		this.amt_loan_before[i] = amt_loan_before;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}






	public String getCnt_loan_before1() {
		return cnt_loan_before1;
	}

	public void setCnt_loan_before1(String cnt_loan_before1) {
		this.cnt_loan_before1 = cnt_loan_before1;
	}

	public String getCnt_loan_before2() {
		return cnt_loan_before2;
	}

	public void setCnt_loan_before2(String cnt_loan_before2) {
		this.cnt_loan_before2 = cnt_loan_before2;
	}

	public String getCnt_loan_before3() {
		return cnt_loan_before3;
	}

	public void setCnt_loan_before3(String cnt_loan_before3) {
		this.cnt_loan_before3 = cnt_loan_before3;
	}

	public String getCnt_loan_before4() {
		return cnt_loan_before4;
	}

	public void setCnt_loan_before4(String cnt_loan_before4) {
		this.cnt_loan_before4 = cnt_loan_before4;
	}

	public String getCnt_loan_before5() {
		return cnt_loan_before5;
	}

	public void setCnt_loan_before5(String cnt_loan_before5) {
		this.cnt_loan_before5 = cnt_loan_before5;
	}

	public String getCnt_loan_before6() {
		return cnt_loan_before6;
	}

	public void setCnt_loan_before6(String cnt_loan_before6) {
		this.cnt_loan_before6 = cnt_loan_before6;
	}

	public String getCnt_loan_before7() {
		return cnt_loan_before7;
	}

	public void setCnt_loan_before7(String cnt_loan_before7) {
		this.cnt_loan_before7 = cnt_loan_before7;
	}

	public String getCnt_loan_before8() {
		return cnt_loan_before8;
	}

	public void setCnt_loan_before8(String cnt_loan_before8) {
		this.cnt_loan_before8 = cnt_loan_before8;
	}

	public String getCnt_loan_before9() {
		return cnt_loan_before9;
	}

	public void setCnt_loan_before9(String cnt_loan_before9) {
		this.cnt_loan_before9 = cnt_loan_before9;
	}

	public String getCnt_loan_before10() {
		return cnt_loan_before10;
	}

	public void setCnt_loan_before10(String cnt_loan_before10) {
		this.cnt_loan_before10 = cnt_loan_before10;
	}

	public String getCnt_loan_before11() {
		return cnt_loan_before11;
	}

	public void setCnt_loan_before11(String cnt_loan_before11) {
		this.cnt_loan_before11 = cnt_loan_before11;
	}

	public String getCnt_loan_before12() {
		return cnt_loan_before12;
	}

	public void setCnt_loan_before12(String cnt_loan_before12) {
		this.cnt_loan_before12 = cnt_loan_before12;
	}

	public String getAmt_loan_before1() {
		return amt_loan_before1;
	}

	public void setAmt_loan_before1(String amt_loan_before1) {
		this.amt_loan_before1 = amt_loan_before1;
	}

	public String getAmt_loan_before2() {
		return amt_loan_before2;
	}

	public void setAmt_loan_before2(String amt_loan_before2) {
		this.amt_loan_before2 = amt_loan_before2;
	}

	public String getAmt_loan_before3() {
		return amt_loan_before3;
	}

	public void setAmt_loan_before3(String amt_loan_before3) {
		this.amt_loan_before3 = amt_loan_before3;
	}

	public String getAmt_loan_before4() {
		return amt_loan_before4;
	}

	public void setAmt_loan_before4(String amt_loan_before4) {
		this.amt_loan_before4 = amt_loan_before4;
	}

	public String getAmt_loan_before5() {
		return amt_loan_before5;
	}

	public void setAmt_loan_before5(String amt_loan_before5) {
		this.amt_loan_before5 = amt_loan_before5;
	}

	public String getAmt_loan_before6() {
		return amt_loan_before6;
	}

	public void setAmt_loan_before6(String amt_loan_before6) {
		this.amt_loan_before6 = amt_loan_before6;
	}

	public String getAmt_loan_before7() {
		return amt_loan_before7;
	}

	public void setAmt_loan_before7(String amt_loan_before7) {
		this.amt_loan_before7 = amt_loan_before7;
	}

	public String getAmt_loan_before8() {
		return amt_loan_before8;
	}

	public void setAmt_loan_before8(String amt_loan_before8) {
		this.amt_loan_before8 = amt_loan_before8;
	}

	public String getAmt_loan_before9() {
		return amt_loan_before9;
	}

	public void setAmt_loan_before9(String amt_loan_before9) {
		this.amt_loan_before9 = amt_loan_before9;
	}

	public String getAmt_loan_before10() {
		return amt_loan_before10;
	}

	public void setAmt_loan_before10(String amt_loan_before10) {
		this.amt_loan_before10 = amt_loan_before10;
	}

	public String getAmt_loan_before11() {
		return amt_loan_before11;
	}

	public void setAmt_loan_before11(String amt_loan_before11) {
		this.amt_loan_before11 = amt_loan_before11;
	}

	public String getAmt_loan_before12() {
		return amt_loan_before12;
	}

	public void setAmt_loan_before12(String amt_loan_before12) {
		this.amt_loan_before12 = amt_loan_before12;
	}
}
