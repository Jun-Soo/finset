package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment105 extends AbstractSegment { // CB SCORE 이력정보
	private static final long serialVersionUID = -4459853250357170973L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment105.class);

	private final String segID = "105";
	private String	cd_score;	// 스코어구분코드KCB_105_INFO                    	//스코어구분코드;
	private String	filler;		// total = 100

	private String[] score_credit_before = new String[12];
	private String[] grade_credit_before = new String[12];




	private String	score_credit_before1;	//1개월전 신용평점KCB_105_INFO      	//_1개월전신용평점;
	private String	score_credit_before2;	//2개월전 신용평점KCB_105_INFO      	//_2개월전신용평점;
	private String	score_credit_before3;	//3개월전 신용평점KCB_105_INFO      	//_3개월전신용평점;
	private String	score_credit_before4;	//4개월전 신용평점KCB_105_INFO      	//_4개월전신용평점;
	private String	score_credit_before5;	//5개월전 신용평점KCB_105_INFO      	//_5개월전신용평점;
	private String	score_credit_before6;	//6개월전 신용평점KCB_105_INFO      	//_6개월전신용평점;
	private String	score_credit_before7;	//7개월전 신용평점KCB_105_INFO      	//_7개월전신용평점;
	private String	score_credit_before8;	//8개월전 신용평점KCB_105_INFO      	//_8개월전신용평점;
	private String	score_credit_before9;	//9개월전 신용평점KCB_105_INFO      	//_9개월전신용평점;
	private String	score_credit_before10;	//10개월전 신용평점KCB_105_INFO   	//_10개월전신용평점;
	private String	score_credit_before11;	//11개월전 신용평점KCB_105_INFO   	//_11개월전신용평점;
	private String	score_credit_before12;	//12개월전 신용평점KCB_105_INFO   	//_12개월전신용평점;
	private String	grade_credit_before1;	//1개월전 신용등급KCB_105_INFO      	//_1개월전신용등급;
	private String	grade_credit_before2;	//2개월전 신용등급KCB_105_INFO      	//_2개월전신용등급;
	private String	grade_credit_before3;	//3개월전 신용등급KCB_105_INFO      	//_3개월전신용등급;
	private String	grade_credit_before4;	//4개월전 신용등급KCB_105_INFO      	//_4개월전신용등급;
	private String	grade_credit_before5;	//5개월전 신용등급KCB_105_INFO      	//_5개월전신용등급;
	private String	grade_credit_before6;	//6개월전 신용등급KCB_105_INFO      	//_6개월전신용등급;
	private String	grade_credit_before7;	//7개월전 신용등급KCB_105_INFO      	//_7개월전신용등급;
	private String	grade_credit_before8;	//8개월전 신용등급KCB_105_INFO      	//_8개월전신용등급;
	private String	grade_credit_before9;	//9개월전 신용등급KCB_105_INFO      	//_9개월전신용등급;
	private String	grade_credit_before10;	//10개월전 신용등급KCB_105_INFO   	//_10개월전신용등급;
	private String	grade_credit_before11;	//11개월전 신용등급KCB_105_INFO   	//_11개월전신용등급;
	private String	grade_credit_before12;	//12개월전 신용등급KCB_105_INFO   	//_12개월전신용등급;

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
		Kcb_Segment105 seq105 = new Kcb_Segment105();

		pos+=len; len=	3;  // segID
		pos+=len; len=	9;	seq105.setCd_score(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 0);         seq105.setScore_credit_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 1);         seq105.setScore_credit_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 2);         seq105.setScore_credit_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 3);         seq105.setScore_credit_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 4);         seq105.setScore_credit_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 5);         seq105.setScore_credit_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 6);         seq105.setScore_credit_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 7);         seq105.setScore_credit_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 8);         seq105.setScore_credit_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 9);         seq105.setScore_credit_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 10);        seq105.setScore_credit_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	4;	seq105.setScore_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 11);        seq105.setScore_credit_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 0);         seq105.setGrade_credit_before1(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 1);         seq105.setGrade_credit_before2(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 2);         seq105.setGrade_credit_before3(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 3);         seq105.setGrade_credit_before4(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 4);         seq105.setGrade_credit_before5(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 5);         seq105.setGrade_credit_before6(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 6);         seq105.setGrade_credit_before7(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 7);         seq105.setGrade_credit_before8(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 8);         seq105.setGrade_credit_before9(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 9);         seq105.setGrade_credit_before10(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 10);        seq105.setGrade_credit_before11(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	2;	seq105.setGrade_credit_before(StringUtil.getByte2String(bt,pos,len).trim(), 11);        seq105.setGrade_credit_before12(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	16; // filler
		pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len));

		map.put("retObj", seq105);
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

	public String setCd_score(String cd_score) {
		return cd_score;
	}

	public String getCd_score() {
		return cd_score;
	}

	public String[] getScore_credit_before() {
		return score_credit_before;
	}

	public String getScore_credit_before(int i) {
		return score_credit_before[i];
	}

	public void setScore_credit_before(String[] score_credit_before) {
		this.score_credit_before = score_credit_before;
	}

	public void setScore_credit_before(String score_credit_before, int i) {
		this.score_credit_before[i] = score_credit_before;
	}

	public String[] getGrade_credit_before() {
		return grade_credit_before;
	}

	public String getGrade_credit_before(int i) {
		return grade_credit_before[i];
	}

	public void setGrade_credit_before(String[] grade_credit_before) {
		this.grade_credit_before = grade_credit_before;
	}

	public void setGrade_credit_before(String grade_credit_before, int i) {
		this.grade_credit_before[i] = grade_credit_before;
	}








	public String getScore_credit_before1() {
		return score_credit_before1;
	}

	public void setScore_credit_before1(String score_credit_before1) {
		this.score_credit_before1 = score_credit_before1;
	}

	public String getScore_credit_before2() {
		return score_credit_before2;
	}

	public void setScore_credit_before2(String score_credit_before2) {
		this.score_credit_before2 = score_credit_before2;
	}

	public String getScore_credit_before3() {
		return score_credit_before3;
	}

	public void setScore_credit_before3(String score_credit_before3) {
		this.score_credit_before3 = score_credit_before3;
	}

	public String getScore_credit_before4() {
		return score_credit_before4;
	}

	public void setScore_credit_before4(String score_credit_before4) {
		this.score_credit_before4 = score_credit_before4;
	}

	public String getScore_credit_before5() {
		return score_credit_before5;
	}

	public void setScore_credit_before5(String score_credit_before5) {
		this.score_credit_before5 = score_credit_before5;
	}

	public String getScore_credit_before6() {
		return score_credit_before6;
	}

	public void setScore_credit_before6(String score_credit_before6) {
		this.score_credit_before6 = score_credit_before6;
	}

	public String getScore_credit_before7() {
		return score_credit_before7;
	}

	public void setScore_credit_before7(String score_credit_before7) {
		this.score_credit_before7 = score_credit_before7;
	}

	public String getScore_credit_before8() {
		return score_credit_before8;
	}

	public void setScore_credit_before8(String score_credit_before8) {
		this.score_credit_before8 = score_credit_before8;
	}

	public String getScore_credit_before9() {
		return score_credit_before9;
	}

	public void setScore_credit_before9(String score_credit_before9) {
		this.score_credit_before9 = score_credit_before9;
	}

	public String getScore_credit_before10() {
		return score_credit_before10;
	}

	public void setScore_credit_before10(String score_credit_before10) {
		this.score_credit_before10 = score_credit_before10;
	}

	public String getScore_credit_before11() {
		return score_credit_before11;
	}

	public void setScore_credit_before11(String score_credit_before11) {
		this.score_credit_before11 = score_credit_before11;
	}

	public String getScore_credit_before12() {
		return score_credit_before12;
	}

	public void setScore_credit_before12(String score_credit_before12) {
		this.score_credit_before12 = score_credit_before12;
	}

	public String getGrade_credit_before1() {
		return grade_credit_before1;
	}

	public void setGrade_credit_before1(String grade_credit_before1) {
		this.grade_credit_before1 = grade_credit_before1;
	}

	public String getGrade_credit_before2() {
		return grade_credit_before2;
	}

	public void setGrade_credit_before2(String grade_credit_before2) {
		this.grade_credit_before2 = grade_credit_before2;
	}

	public String getGrade_credit_before3() {
		return grade_credit_before3;
	}

	public void setGrade_credit_before3(String grade_credit_before3) {
		this.grade_credit_before3 = grade_credit_before3;
	}

	public String getGrade_credit_before4() {
		return grade_credit_before4;
	}

	public void setGrade_credit_before4(String grade_credit_before4) {
		this.grade_credit_before4 = grade_credit_before4;
	}

	public String getGrade_credit_before5() {
		return grade_credit_before5;
	}

	public void setGrade_credit_before5(String grade_credit_before5) {
		this.grade_credit_before5 = grade_credit_before5;
	}

	public String getGrade_credit_before6() {
		return grade_credit_before6;
	}

	public void setGrade_credit_before6(String grade_credit_before6) {
		this.grade_credit_before6 = grade_credit_before6;
	}

	public String getGrade_credit_before7() {
		return grade_credit_before7;
	}

	public void setGrade_credit_before7(String grade_credit_before7) {
		this.grade_credit_before7 = grade_credit_before7;
	}

	public String getGrade_credit_before8() {
		return grade_credit_before8;
	}

	public void setGrade_credit_before8(String grade_credit_before8) {
		this.grade_credit_before8 = grade_credit_before8;
	}

	public String getGrade_credit_before9() {
		return grade_credit_before9;
	}

	public void setGrade_credit_before9(String grade_credit_before9) {
		this.grade_credit_before9 = grade_credit_before9;
	}

	public String getGrade_credit_before10() {
		return grade_credit_before10;
	}

	public void setGrade_credit_before10(String grade_credit_before10) {
		this.grade_credit_before10 = grade_credit_before10;
	}

	public String getGrade_credit_before11() {
		return grade_credit_before11;
	}

	public void setGrade_credit_before11(String grade_credit_before11) {
		this.grade_credit_before11 = grade_credit_before11;
	}

	public String getGrade_credit_before12() {
		return grade_credit_before12;
	}

	public void setGrade_credit_before12(String grade_credit_before12) {
		this.grade_credit_before12 = grade_credit_before12;
	}



}
