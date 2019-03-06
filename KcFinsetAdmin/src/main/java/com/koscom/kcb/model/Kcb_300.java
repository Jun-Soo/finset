package com.koscom.kcb.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.koscom.credit.service.CreditManager;
import com.koscom.debt.service.DebtManager;
import com.koscom.env.model.CodeInfo;
import com.koscom.kcb.model.seg.AbstractSegment;
import com.koscom.kcb.model.seg.Kcb_Segment029;
import com.koscom.kcb.model.seg.Kcb_Segment030;
import com.koscom.kcb.model.seg.Kcb_Segment034;
import com.koscom.kcb.model.seg.Kcb_Segment035;
import com.koscom.kcb.model.seg.Kcb_Segment041;
import com.koscom.kcb.model.seg.Kcb_Segment045;
import com.koscom.kcb.model.seg.Kcb_Segment046;
import com.koscom.kcb.model.seg.Kcb_Segment061;
import com.koscom.kcb.model.seg.Kcb_Segment062;
import com.koscom.kcb.model.seg.Kcb_Segment065;
import com.koscom.kcb.model.seg.Kcb_Segment103;
import com.koscom.kcb.model.seg.Kcb_Segment105;
import com.koscom.util.CodeUtil;
import com.koscom.util.DateUtil;
import com.koscom.util.NumberUtil;
import com.koscom.util.SocketUtil;
import com.koscom.util.StringUtil;

/**
 * 파일명 : Kcb_300.java
 * 기능설명 : KCB 금융CB 보고서 전문 모델
 */
public class Kcb_300 extends AbstractKcbInfo {

	private static final Logger logger = LoggerFactory.getLogger(Kcb_300.class);

	private static final long serialVersionUID = 6394016634815932578L;
	private static final Logger log = LoggerFactory.getLogger(Kcb_300.class);

	//요청정보부 변수
	private String	req인증번호;			//	AN	12
	private String	req재요청횟수;			//	N	2
	private String	req개인or법인구분코드;	//	AN	1	개인/법인 구분코드
	private String	req주민등록번호;		//	N	13
	private String	req조회목적코드;		//	AN	2
	private String	req성명;				//	AN	50
	private String	req조회지점명;			//	AN	20
	private String	req담당자ID;			//	AN	15
	private String  reqScore구분코드;
	private String  req업종코드;
	private String  reqScore이력구분코드;

	private String	req신상정보총수신건수;						//	N	3	cnt_seg_029_rec		Kcb_100
	private String	req신상정보요청건수;						//	N	2	cnt_seg_029_req		Kcb_100
	private String  req대출개설및계좌상태정보총수신건수;		//	N	3	cnt_seg_030_rec		Kcb_100
	private String  req대출개설및계좌상태정보요청건수;			//	N	2	cnt_seg_030_rec		Kcb_100
	private String	req개인별대출이력정보총수신건수;			//	N	3	cnt_seg_034_rec		Kcb_100
	private String	req개인별대출이력정보요청건수;				//	N	2	cnt_seg_034_req		Kcb_100
	private String	req개인별대출연체이력정보총수신건수;		//	N	3	cnt_seg_035_rec		Kcb_100
	private String	req개인별대출연체이력정보요청건수;			//	N	2	cnt_seg_035_req		Kcb_100
	private String	req카드개설및거래상태정보총수신건수;		//	N	3	req카드개설및거래상태정보총수신건수	cnt_seg_041_rec		Kcb_250
	private String	req카드개설및거래상태정보요청건수;			//	N	2	req카드개설및거래상태정보요청건수	cnt_seg_041_rec		Kcb_250
	private String	req개인별카드이력정보총수신건수;			//	N	3	cnt_seg_045_rec		Kcb_100
	private String	req개인별카드이력정보요청건수;				//	N	2	cnt_seg_045_req		Kcb_100
	private String	req개인별카드연체이력정보총수신건수;		//	N	3	cnt_seg_046_rec		Kcb_100
	private String	req개인별카드연체이력정보요청건수;			//	N	2	cnt_seg_046_req		Kcb_100
	private String	req연체정보총수신건수;						//	N	3	cnt_seg_061_req
	private String	req연체정보요청건수;						//	N	2	cnt_seg_061_req
	private String	req대지급정보총수신건수;					//	N	3	cnt_seg_062_req
	private String	req대지급정보요청건수;						//	N	2	cnt_seg_062_req
	private String	req채무불이행정보총수신건수;				//	N	3	cnt_seg_065_req
	private String 	req채무불이행정보요청건수;					//	N	2	cnt_seg_065_req
	private String	reqCPS고정총수신건수;						//	N	3	cnt_seg_103_rec
	private String	reqCPS고정요청건수;							//	N	3	cnt_seg_103_req ==> 3
	private String	reqCB_SCORE이력정보총수신건수;				//	N	3	cnt_seg_105_rec
	private String	reqCB_SCORE이력정보정보요청건수;			//	N	2	cnt_seg_105_req

	//응답정보부
	private String	res인증번호;							//	AN	12
	private String	res정보연속여부;						//	AN	1
	private String	res재요청횟수;							//	N	2
	private String	res개인or법인구분코드;					//	AN	1	개인법인구분
	private String	res주민등록번호;						//	AN	13
	private String	res성명;								//	AN	50
	private String	res정보유무체크비트;					//	AN	10
	private String	res신상정보총건수;						//	AN	3	cnt_seg_029			Kcb_100
	private String	res신상정보총송신건수;					//	N	3	cnt_seg_029_send	Kcb_100
	private String	res신상정보응답건수;					//	N	2	cnt_seg_029_res		Kcb_100
	private String  res대출개설및계좌상태정보총건수;		//	AN	3	cnt_seg_030			Kcb_100
	private String	res대출개설및계좌상태정보총송신건수;	//	N	3	cnt_seg_030_send	Kcb_100
	private String	res대출개설및계좌상태정보응답건수;		//	N	2	cnt_seg_030_res		Kcb_100
	private String	res개인별대출이력정보총건수;			//	AN	3	cnt_seg_034			Kcb_100
	private String	res개인별대출이력정보총송신건수;		//	N	3	cnt_seg_034_send	Kcb_100
	private String	res개인별대출이력정보응답건수;			//	N	2	cnt_seg_034_res		Kcb_100
	private String	res개인별대출연체이력정보총건수;		//	AN	3	cnt_seg_035			Kcb_100
	private String	res개인별대출연체이력정보총송신건수;	//	N	3	cnt_seg_035_send	Kcb_100
	private String	res개인별대출연체이력정보응답건수;		//	N	2	cnt_seg_035_res		Kcb_100
	private String	res카드개설및거래상태정보총건수;		//	AN	3	카드개설및거래상태정보총건수		cnt_seg_041			Kcb_250
	private String	res카드개설및거래상태정보총송신건수;	//	N	3	카드개설및거래상태정보총송신건수	cnt_seg_041			Kcb_250
	private String	res카드개설및거래상태정보응답건수;		//	N	2	카드개설및거래상태정보응답건수		cnt_seg_041			Kcb_250
	private String	res개인별카드이력정보총건수;			//	AN	3	cnt_seg_045			Kcb_100
	private String	res개인별카드이력정보총송신건수;		//	N	3	cnt_seg_045_send	Kcb_100
	private String	res개인별카드이력정보응답건수;			//	N	2	cnt_seg_045_res		Kcb_100
	private String	res개인별카드연체이력정보총건수;		//	AN	3	cnt_seg_046			Kcb_100
	private String	res개인별카드연체이력정보총송신건수;	//	N	3	cnt_seg_046_send	Kcb_100
	private String	res개인별카드연체이력정보응답건수;		//	N	2	cnt_seg_046_res		Kcb_100
	private String	res연체정보총건수;						//	AN	3	cnt_seg_061
	private String	res연체정보총송신건수;					//	N	3	cnt_seg_061
	private String 	res연체정보응답건수;					//	N	2	cnt_seg_061
	private String	res대지급정보총건수;					//	AN	3	cnt_seg_062
	private String	res대지급정보총송신건수;				//	N	3	cnt_seg_062
	private String	res대지급정보응답건수;					//	N	2	cnt_seg_062
	private String	res채무불이행정보총건수;				//	AN	3	cnt_seg_065
	private String	res채무불이행정보총송신건수;			//	N	3	cnt_seg_065
	private String	res채무불이행정보응답건수;				//	N	3	cnt_seg_065
	private String	res고객평점정보총건수;					//	AN	3	cnt_seg_100
	private String	res고객평점정보총송신건수;				//	N	3	cnt_seg_100_send
	private String	res고객평점정보응답건수;				//	N	2	cnt_seg_100_res
	private String	resCPS고정총건수;						//	AN	3	cnt_seg_103
	private String	resCPS고정총송신건수;					//	N	3	cnt_seg_103_send
	private String	resCPS고정응답건수;						//	N	3	cnt_seg_103_res
	private String	resCB_SCORE이력정보총건수;				//	AN	3	cnt_seg_105
	private String	resCB_SCORE이력정보총송신건수;			//	N	3	cnt_seg_105_send
	private String	resCB_SCORE이력정보응답건수;			//	N	2	cnt_seg_105_res

	private List<CodeInfo> reqCpsCodeList;//cps요청코드들
	private HashMap<String, AbstractSegment> segMentMap; //세그먼트_객체맵
	private int reqByte ;

	//Loop Kcb_Segment
	private List<Kcb_Segment029> listKcb_Segment029;	// 신상정보
	private List<Kcb_Segment030> listKcb_Segment030;	// 대출개설및계좌상태정보
	private List<Kcb_Segment034> listKcb_Segment034;	// 대출이력정보(개인별)
	private List<Kcb_Segment035> listKcb_Segment035;	// 대출연체이력정보(개인별)
	private List<Kcb_Segment041> listKcb_Segment041;	// 카드개설및거래상태정보
	private List<Kcb_Segment045> listKcb_Segment045;	// 카드이력정보(개인별)
	private List<Kcb_Segment046> listKcb_Segment046;	// 카드연체이력정보(개인별)
	private List<Kcb_Segment061> listKcb_Segment061;	// 연체정보
	private List<Kcb_Segment062> listKcb_Segment062;	// 대지급정보
	private List<Kcb_Segment065> listKcb_Segment065;	// 채무불이행정보
	private List<Kcb_Segment103> listKcb_Segment103;	// CPS(고정)
	private List<Kcb_Segment105> listKcb_Segment105;	// CB SCORE 이력정보

	// 필터링내역부
	private String[][] 필터링내역;
	private String 심사강화항목총건수;
	private String 심사강화항목적중건수;
	private String 거절권유항목총건수;
	private String 거절권유항목적중건수;

	private  HashMap<String, String> cps_map = null;
	private String nm_if_sub;

	private String resDt;	//마지막 전문 수신일

	public String getReq인증번호() {
		return req인증번호;
	}
	public void setReq인증번호(String req인증번호) {
		this.req인증번호 = req인증번호;
	}
	public String getReq재요청횟수() {
		return req재요청횟수;
	}
	public void setReq재요청횟수(String req재요청횟수) {
		this.req재요청횟수 = req재요청횟수;
	}
	public String getReq개인or법인구분코드() {
		return req개인or법인구분코드;
	}
	public void setReq개인or법인구분코드(String req개인or법인구분코드) {
		this.req개인or법인구분코드 = req개인or법인구분코드;
	}
	public String getReq주민등록번호() {
		return req주민등록번호;
	}
	public void setReq주민등록번호(String req주민등록번호) {
		this.req주민등록번호 = req주민등록번호;
	}
	public String getReq조회목적코드() {
		return req조회목적코드;
	}
	public void setReq조회목적코드(String req조회목적코드) {
		this.req조회목적코드 = req조회목적코드;
	}
	public String getReq성명() {
		return req성명;
	}
	public void setReq성명(String req성명) {
		this.req성명 = req성명;
	}
	public String getReq조회지점명() {
		return req조회지점명;
	}
	public void setReq조회지점명(String req조회지점명) {
		this.req조회지점명 = req조회지점명;
	}
	public String getReq담당자ID() {
		return req담당자ID;
	}
	public void setReq담당자ID(String req담당자id) {
		req담당자ID = req담당자id;
	}
	public String getRes인증번호() {
		return res인증번호;
	}
	public void setRes인증번호(String res인증번호) {
		this.res인증번호 = res인증번호;
	}
	public String getRes정보연속여부() {
		return res정보연속여부;
	}
	public void setRes정보연속여부(String res정보연속여부) {
		this.res정보연속여부 = res정보연속여부;
	}
	public String getRes재요청횟수() {
		return res재요청횟수;
	}
	public void setRes재요청횟수(String res재요청횟수) {
		this.res재요청횟수 = res재요청횟수;
	}
	public String getRes주민등록번호() {
		return res주민등록번호;
	}
	public void setRes주민등록번호(String res주민등록번호) {
		this.res주민등록번호 = res주민등록번호;
	}
	public String getRes성명() {
		return res성명;
	}
	public void setRes성명(String res성명) {
		this.res성명 = res성명;
	}
	public String getRes정보유무체크비트() {
		return res정보유무체크비트;
	}
	public void setRes정보유무체크비트(String res정보유무체크비트) {
		this.res정보유무체크비트 = res정보유무체크비트;
	}
	public HashMap<String, AbstractSegment> getSegMentMap() {
		return segMentMap;
	}
	public void setSegMentMap(HashMap<String, AbstractSegment> segMentMap) {
		this.segMentMap = segMentMap;
	}
	public String getReq신상정보총수신건수() {
		return req신상정보총수신건수;
	}
	public void setReq신상정보총수신건수(String req신상정보총수신건수) {
		this.req신상정보총수신건수 = req신상정보총수신건수;
	}
	public String getReq신상정보요청건수() {
		return req신상정보요청건수;
	}
	public void setReq신상정보요청건수(String req신상정보요청건수) {
		this.req신상정보요청건수 = req신상정보요청건수;
	}
	public String getReq대출개설및계좌상태정보총수신건수() {
		return req대출개설및계좌상태정보총수신건수;
	}
	public void setReq대출개설및계좌상태정보총수신건수(String req대출개설및계좌상태정보총수신건수) {
		this.req대출개설및계좌상태정보총수신건수 = req대출개설및계좌상태정보총수신건수;
	}
	public String getReq대출개설및계좌상태정보요청건수() {
		return req대출개설및계좌상태정보요청건수;
	}
	public void setReq대출개설및계좌상태정보요청건수(String req대출개설및계좌상태정보요청건수) {
		this.req대출개설및계좌상태정보요청건수 = req대출개설및계좌상태정보요청건수;
	}
	public String getReq개인별대출이력정보총수신건수() {
		return req개인별대출이력정보총수신건수;
	}
	public void setReq개인별대출이력정보총수신건수(String req개인별대출이력정보총수신건수) {
		this.req개인별대출이력정보총수신건수 = req개인별대출이력정보총수신건수;
	}
	public String getReq개인별대출이력정보요청건수() {
		return req개인별대출이력정보요청건수;
	}
	public void setReq개인별대출이력정보요청건수(String req개인별대출이력정보요청건수) {
		this.req개인별대출이력정보요청건수 = req개인별대출이력정보요청건수;
	}
	public String getReq개인별대출연체이력정보총수신건수() {
		return req개인별대출연체이력정보총수신건수;
	}
	public void setReq개인별대출연체이력정보총수신건수(String req개인별대출연체이력정보총수신건수) {
		this.req개인별대출연체이력정보총수신건수 = req개인별대출연체이력정보총수신건수;
	}
	public String getReq개인별대출연체이력정보요청건수() {
		return req개인별대출연체이력정보요청건수;
	}
	public void setReq개인별대출연체이력정보요청건수(String req개인별대출연체이력정보요청건수) {
		this.req개인별대출연체이력정보요청건수 = req개인별대출연체이력정보요청건수;
	}
	public String getReq카드개설및거래상태정보총수신건수() {
		return req카드개설및거래상태정보총수신건수;
	}
	public void setReq카드개설및거래상태정보총수신건수(String req카드개설및거래상태정보총수신건수) {
		this.req카드개설및거래상태정보총수신건수 = req카드개설및거래상태정보총수신건수;
	}
	public String getReq카드개설및거래상태정보요청건수() {
		return req카드개설및거래상태정보요청건수;
	}
	public void setReq카드개설및거래상태정보요청건수(String req카드개설및거래상태정보요청건수) {
		this.req카드개설및거래상태정보요청건수 = req카드개설및거래상태정보요청건수;
	}
	public String getReq개인별카드이력정보총수신건수() {
		return req개인별카드이력정보총수신건수;
	}
	public void setReq개인별카드이력정보총수신건수(String req개인별카드이력정보총수신건수) {
		this.req개인별카드이력정보총수신건수 = req개인별카드이력정보총수신건수;
	}
	public String getReq개인별카드이력정보요청건수() {
		return req개인별카드이력정보요청건수;
	}
	public void setReq개인별카드이력정보요청건수(String req개인별카드이력정보요청건수) {
		this.req개인별카드이력정보요청건수 = req개인별카드이력정보요청건수;
	}
	public String getReq개인별카드연체이력정보총수신건수() {
		return req개인별카드연체이력정보총수신건수;
	}
	public void setReq개인별카드연체이력정보총수신건수(String req개인별카드연체이력정보총수신건수) {
		this.req개인별카드연체이력정보총수신건수 = req개인별카드연체이력정보총수신건수;
	}
	public String getReq개인별카드연체이력정보요청건수() {
		return req개인별카드연체이력정보요청건수;
	}
	public void setReq개인별카드연체이력정보요청건수(String req개인별카드연체이력정보요청건수) {
		this.req개인별카드연체이력정보요청건수 = req개인별카드연체이력정보요청건수;
	}
	public String getReq연체정보총수신건수() {
		return req연체정보총수신건수;
	}
	public void setReq연체정보총수신건수(String req연체정보총수신건수) {
		this.req연체정보총수신건수 = req연체정보총수신건수;
	}
	public String getReq연체정보요청건수() {
		return req연체정보요청건수;
	}
	public void setReq연체정보요청건수(String req연체정보요청건수) {
		this.req연체정보요청건수 = req연체정보요청건수;
	}
	public String getReq대지급정보총수신건수() {
		return req대지급정보총수신건수;
	}
	public void setReq대지급정보총수신건수(String req대지급정보총수신건수) {
		this.req대지급정보총수신건수 = req대지급정보총수신건수;
	}
	public String getReq대지급정보요청건수() {
		return req대지급정보요청건수;
	}
	public void setReq대지급정보요청건수(String req대지급정보요청건수) {
		this.req대지급정보요청건수 = req대지급정보요청건수;
	}
	public String getReq채무불이행정보총수신건수() {
		return req채무불이행정보총수신건수;
	}
	public void setReq채무불이행정보총수신건수(String req채무불이행정보총수신건수) {
		this.req채무불이행정보총수신건수 = req채무불이행정보총수신건수;
	}
	public String getReq채무불이행정보요청건수() {
		return req채무불이행정보요청건수;
	}
	public void setReq채무불이행정보요청건수(String req채무불이행정보요청건수) {
		this.req채무불이행정보요청건수 = req채무불이행정보요청건수;
	}
	public String getReqCPS고정총수신건수() {
		return reqCPS고정총수신건수;
	}
	public void setReqCPS고정총수신건수(String reqCPS고정총수신건수) {
		this.reqCPS고정총수신건수 = reqCPS고정총수신건수;
	}
	public String getReqCPS고정요청건수() {
		return reqCPS고정요청건수;
	}
	public void setReqCPS고정요청건수(String reqCPS고정요청건수) {
		this.reqCPS고정요청건수 = reqCPS고정요청건수;
	}
	public String getReqCB_SCORE이력정보총수신건수() {
		return reqCB_SCORE이력정보총수신건수;
	}
	public void setReqCB_SCORE이력정보총수신건수(String reqCB_SCORE이력정보총수신건수) {
		this.reqCB_SCORE이력정보총수신건수 = reqCB_SCORE이력정보총수신건수;
	}
	public String getReqCB_SCORE이력정보정보요청건수() {
		return reqCB_SCORE이력정보정보요청건수;
	}
	public void setReqCB_SCORE이력정보정보요청건수(String reqCB_SCORE이력정보정보요청건수) {
		this.reqCB_SCORE이력정보정보요청건수 = reqCB_SCORE이력정보정보요청건수;
	}
	public String getRes개인or법인구분코드() {
		return res개인or법인구분코드;
	}
	public void setRes개인or법인구분코드(String res개인or법인구분코드) {
		this.res개인or법인구분코드 = res개인or법인구분코드;
	}
	public String getRes신상정보총건수() {
		return res신상정보총건수;
	}
	public void setRes신상정보총건수(String res신상정보총건수) {
		this.res신상정보총건수 = res신상정보총건수;
	}
	public String getRes신상정보총송신건수() {
		return res신상정보총송신건수;
	}
	public void setRes신상정보총송신건수(String res신상정보총송신건수) {
		this.res신상정보총송신건수 = res신상정보총송신건수;
	}
	public String getRes신상정보응답건수() {
		return res신상정보응답건수;
	}
	public void setRes신상정보응답건수(String res신상정보응답건수) {
		this.res신상정보응답건수 = res신상정보응답건수;
	}
	public String getRes대출개설및계좌상태정보총건수() {
		return res대출개설및계좌상태정보총건수;
	}
	public void setRes대출개설및계좌상태정보총건수(String res대출개설및계좌상태정보총건수) {
		this.res대출개설및계좌상태정보총건수 = res대출개설및계좌상태정보총건수;
	}
	public String getRes대출개설및계좌상태정보총송신건수() {
		return res대출개설및계좌상태정보총송신건수;
	}
	public void setRes대출개설및계좌상태정보총송신건수(String res대출개설및계좌상태정보총송신건수) {
		this.res대출개설및계좌상태정보총송신건수 = res대출개설및계좌상태정보총송신건수;
	}
	public String getRes대출개설및계좌상태정보응답건수() {
		return res대출개설및계좌상태정보응답건수;
	}
	public void setRes대출개설및계좌상태정보응답건수(String res대출개설및계좌상태정보응답건수) {
		this.res대출개설및계좌상태정보응답건수 = res대출개설및계좌상태정보응답건수;
	}
	public String getRes개인별대출이력정보총건수() {
		return res개인별대출이력정보총건수;
	}
	public void setRes개인별대출이력정보총건수(String res개인별대출이력정보총건수) {
		this.res개인별대출이력정보총건수 = res개인별대출이력정보총건수;
	}
	public String getRes개인별대출이력정보총송신건수() {
		return res개인별대출이력정보총송신건수;
	}
	public void setRes개인별대출이력정보총송신건수(String res개인별대출이력정보총송신건수) {
		this.res개인별대출이력정보총송신건수 = res개인별대출이력정보총송신건수;
	}
	public String getRes개인별대출이력정보응답건수() {
		return res개인별대출이력정보응답건수;
	}
	public void setRes개인별대출이력정보응답건수(String res개인별대출이력정보응답건수) {
		this.res개인별대출이력정보응답건수 = res개인별대출이력정보응답건수;
	}
	public String getRes개인별대출연체이력정보총건수() {
		return res개인별대출연체이력정보총건수;
	}
	public void setRes개인별대출연체이력정보총건수(String res개인별대출연체이력정보총건수) {
		this.res개인별대출연체이력정보총건수 = res개인별대출연체이력정보총건수;
	}
	public String getRes개인별대출연체이력정보총송신건수() {
		return res개인별대출연체이력정보총송신건수;
	}
	public void setRes개인별대출연체이력정보총송신건수(String res개인별대출연체이력정보총송신건수) {
		this.res개인별대출연체이력정보총송신건수 = res개인별대출연체이력정보총송신건수;
	}
	public String getRes개인별대출연체이력정보응답건수() {
		return res개인별대출연체이력정보응답건수;
	}
	public void setRes개인별대출연체이력정보응답건수(String res개인별대출연체이력정보응답건수) {
		this.res개인별대출연체이력정보응답건수 = res개인별대출연체이력정보응답건수;
	}
	public String getRes카드개설및거래상태정보총건수() {
		return res카드개설및거래상태정보총건수;
	}
	public void setRes카드개설및거래상태정보총건수(String res카드개설및거래상태정보총건수) {
		this.res카드개설및거래상태정보총건수 = res카드개설및거래상태정보총건수;
	}
	public String getRes카드개설및거래상태정보총송신건수() {
		return res카드개설및거래상태정보총송신건수;
	}
	public void setRes카드개설및거래상태정보총송신건수(String res카드개설및거래상태정보총송신건수) {
		this.res카드개설및거래상태정보총송신건수 = res카드개설및거래상태정보총송신건수;
	}
	public String getRes카드개설및거래상태정보응답건수() {
		return res카드개설및거래상태정보응답건수;
	}
	public void setRes카드개설및거래상태정보응답건수(String res카드개설및거래상태정보응답건수) {
		this.res카드개설및거래상태정보응답건수 = res카드개설및거래상태정보응답건수;
	}
	public String getRes개인별카드이력정보총건수() {
		return res개인별카드이력정보총건수;
	}
	public void setRes개인별카드이력정보총건수(String res개인별카드이력정보총건수) {
		this.res개인별카드이력정보총건수 = res개인별카드이력정보총건수;
	}
	public String getRes개인별카드이력정보총송신건수() {
		return res개인별카드이력정보총송신건수;
	}
	public void setRes개인별카드이력정보총송신건수(String res개인별카드이력정보총송신건수) {
		this.res개인별카드이력정보총송신건수 = res개인별카드이력정보총송신건수;
	}
	public String getRes개인별카드이력정보응답건수() {
		return res개인별카드이력정보응답건수;
	}
	public void setRes개인별카드이력정보응답건수(String res개인별카드이력정보응답건수) {
		this.res개인별카드이력정보응답건수 = res개인별카드이력정보응답건수;
	}
	public String getRes개인별카드연체이력정보총건수() {
		return res개인별카드연체이력정보총건수;
	}
	public void setRes개인별카드연체이력정보총건수(String res개인별카드연체이력정보총건수) {
		this.res개인별카드연체이력정보총건수 = res개인별카드연체이력정보총건수;
	}
	public String getRes개인별카드연체이력정보총송신건수() {
		return res개인별카드연체이력정보총송신건수;
	}
	public void setRes개인별카드연체이력정보총송신건수(String res개인별카드연체이력정보총송신건수) {
		this.res개인별카드연체이력정보총송신건수 = res개인별카드연체이력정보총송신건수;
	}
	public String getRes개인별카드연체이력정보응답건수() {
		return res개인별카드연체이력정보응답건수;
	}
	public void setRes개인별카드연체이력정보응답건수(String res개인별카드연체이력정보응답건수) {
		this.res개인별카드연체이력정보응답건수 = res개인별카드연체이력정보응답건수;
	}
	public String getRes연체정보총건수() {
		return res연체정보총건수;
	}
	public void setRes연체정보총건수(String res연체정보총건수) {
		this.res연체정보총건수 = res연체정보총건수;
	}
	public String getRes연체정보총송신건수() {
		return res연체정보총송신건수;
	}
	public void setRes연체정보총송신건수(String res연체정보총송신건수) {
		this.res연체정보총송신건수 = res연체정보총송신건수;
	}
	public String getRes연체정보응답건수() {
		return res연체정보응답건수;
	}
	public void setRes연체정보응답건수(String res연체정보응답건수) {
		this.res연체정보응답건수 = res연체정보응답건수;
	}
	public String getRes대지급정보총건수() {
		return res대지급정보총건수;
	}
	public void setRes대지급정보총건수(String res대지급정보총건수) {
		this.res대지급정보총건수 = res대지급정보총건수;
	}
	public String getRes대지급정보총송신건수() {
		return res대지급정보총송신건수;
	}
	public void setRes대지급정보총송신건수(String res대지급정보총송신건수) {
		this.res대지급정보총송신건수 = res대지급정보총송신건수;
	}
	public String getRes대지급정보응답건수() {
		return res대지급정보응답건수;
	}
	public void setRes대지급정보응답건수(String res대지급정보응답건수) {
		this.res대지급정보응답건수 = res대지급정보응답건수;
	}
	public String getRes채무불이행정보총건수() {
		return res채무불이행정보총건수;
	}
	public void setRes채무불이행정보총건수(String res채무불이행정보총건수) {
		this.res채무불이행정보총건수 = res채무불이행정보총건수;
	}
	public String getRes채무불이행정보총송신건수() {
		return res채무불이행정보총송신건수;
	}
	public void setRes채무불이행정보총송신건수(String res채무불이행정보총송신건수) {
		this.res채무불이행정보총송신건수 = res채무불이행정보총송신건수;
	}
	public String getRes채무불이행정보응답건수() {
		return res채무불이행정보응답건수;
	}
	public void setRes채무불이행정보응답건수(String res채무불이행정보응답건수) {
		this.res채무불이행정보응답건수 = res채무불이행정보응답건수;
	}
	public String getRes고객평점정보총건수() {
		return res고객평점정보총건수;
	}
	public void setRes고객평점정보총건수(String res고객평점정보총건수) {
		this.res고객평점정보총건수 = res고객평점정보총건수;
	}
	public String getRes고객평점정보총송신건수() {
		return res고객평점정보총송신건수;
	}
	public void setRes고객평점정보총송신건수(String res고객평점정보총송신건수) {
		this.res고객평점정보총송신건수 = res고객평점정보총송신건수;
	}
	public String getRes고객평점정보응답건수() {
		return res고객평점정보응답건수;
	}
	public void setRes고객평점정보응답건수(String res고객평점정보응답건수) {
		this.res고객평점정보응답건수 = res고객평점정보응답건수;
	}
	public String getResCPS고정총건수() {
		return resCPS고정총건수;
	}
	public void setResCPS고정총건수(String resCPS고정총건수) {
		this.resCPS고정총건수 = resCPS고정총건수;
	}
	public String getResCPS고정총송신건수() {
		return resCPS고정총송신건수;
	}
	public void setResCPS고정총송신건수(String resCPS고정총송신건수) {
		this.resCPS고정총송신건수 = resCPS고정총송신건수;
	}
	public String getResCPS고정응답건수() {
		return resCPS고정응답건수;
	}
	public void setResCPS고정응답건수(String resCPS고정응답건수) {
		this.resCPS고정응답건수 = resCPS고정응답건수;
	}
	public String getResCB_SCORE이력정보총건수() {
		return resCB_SCORE이력정보총건수;
	}
	public void setResCB_SCORE이력정보총건수(String resCB_SCORE이력정보총건수) {
		this.resCB_SCORE이력정보총건수 = resCB_SCORE이력정보총건수;
	}
	public String getResCB_SCORE이력정보총송신건수() {
		return resCB_SCORE이력정보총송신건수;
	}
	public void setResCB_SCORE이력정보총송신건수(String resCB_SCORE이력정보총송신건수) {
		this.resCB_SCORE이력정보총송신건수 = resCB_SCORE이력정보총송신건수;
	}
	public String getResCB_SCORE이력정보응답건수() {
		return resCB_SCORE이력정보응답건수;
	}
	public void setResCB_SCORE이력정보응답건수(String resCB_SCORE이력정보응답건수) {
		this.resCB_SCORE이력정보응답건수 = resCB_SCORE이력정보응답건수;
	}
	public List<CodeInfo> getReqCpsCodeList() {
		return reqCpsCodeList;
	}
	public void setReqCpsCodeList(List<CodeInfo> reqCpsCodeList) {
		this.reqCpsCodeList = reqCpsCodeList;
	}
	public List<Kcb_Segment029> getListKcb_Segment029() {
		return listKcb_Segment029;
	}
	public void setListKcb_Segment029(List<Kcb_Segment029> listKcb_Segment029) {
		this.listKcb_Segment029 = listKcb_Segment029;
	}
	public List<Kcb_Segment030> getListKcb_Segment030() {
		return listKcb_Segment030;
	}
	public void setListKcb_Segment030(List<Kcb_Segment030> listKcb_Segment030) {
		this.listKcb_Segment030 = listKcb_Segment030;
	}
	public List<Kcb_Segment034> getListKcb_Segment034() {
		return listKcb_Segment034;
	}
	public void setListKcb_Segment034(List<Kcb_Segment034> listKcb_Segment034) {
		this.listKcb_Segment034 = listKcb_Segment034;
	}
	public List<Kcb_Segment035> getListKcb_Segment035() {
		return listKcb_Segment035;
	}
	public void setListKcb_Segment035(List<Kcb_Segment035> listKcb_Segment035) {
		this.listKcb_Segment035 = listKcb_Segment035;
	}
	public List<Kcb_Segment041> getListKcb_Segment041() {
		return listKcb_Segment041;
	}
	public void setListKcb_Segment041(List<Kcb_Segment041> listKcb_Segment041) {
		this.listKcb_Segment041 = listKcb_Segment041;
	}
	public List<Kcb_Segment045> getListKcb_Segment045() {
		return listKcb_Segment045;
	}
	public void setListKcb_Segment045(List<Kcb_Segment045> listKcb_Segment045) {
		this.listKcb_Segment045 = listKcb_Segment045;
	}
	public List<Kcb_Segment046> getListKcb_Segment046() {
		return listKcb_Segment046;
	}
	public void setListKcb_Segment046(List<Kcb_Segment046> listKcb_Segment046) {
		this.listKcb_Segment046 = listKcb_Segment046;
	}
	public List<Kcb_Segment061> getListKcb_Segment061() {
		return listKcb_Segment061;
	}
	public void setListKcb_Segment061(List<Kcb_Segment061> listKcb_Segment061) {
		this.listKcb_Segment061 = listKcb_Segment061;
	}
	public List<Kcb_Segment062> getListKcb_Segment062() {
		return listKcb_Segment062;
	}
	public void setListKcb_Segment062(List<Kcb_Segment062> listKcb_Segment062) {
		this.listKcb_Segment062 = listKcb_Segment062;
	}
	public List<Kcb_Segment065> getListKcb_Segment065() {
		return listKcb_Segment065;
	}
	public void setListKcb_Segment065(List<Kcb_Segment065> listKcb_Segment065) {
		this.listKcb_Segment065 = listKcb_Segment065;
	}
	public List<Kcb_Segment103> getListKcb_Segment103() {
		return listKcb_Segment103;
	}
	public void setListKcb_Segment103(List<Kcb_Segment103> listKcb_Segment103) {
		this.listKcb_Segment103 = listKcb_Segment103;
	}
	public List<Kcb_Segment105> getListKcb_Segment105() {
		return listKcb_Segment105;
	}
	public void setListKcb_Segment105(List<Kcb_Segment105> listKcb_Segment105) {
		this.listKcb_Segment105 = listKcb_Segment105;
	}
	public String getReqScore구분코드() {
		return reqScore구분코드;
	}
	public void setReqScore구분코드(String reqScore구분코드) {
		this.reqScore구분코드 = reqScore구분코드;
	}
	public String getReq업종코드() {
		return req업종코드;
	}
	public void setReq업종코드(String req업종코드) {
		this.req업종코드 = req업종코드;
	}
	public String getReqScore이력구분코드() {
		return reqScore이력구분코드;
	}
	public void setReqScore이력구분코드(String reqScore이력구분코드) {
		this.reqScore이력구분코드 = reqScore이력구분코드;
	}
	public int getReqByte() {
		return reqByte;
	}
	public void setReqByte(int reqByte) {
		this.reqByte = reqByte;
	}
	public String[][] get필터링내역() {
		return 필터링내역;
	}
	public void set필터링내역(String[][] 필터링내역) {
		this.필터링내역 = 필터링내역;
	}
	public String get심사강화항목총건수() {
		return 심사강화항목총건수;
	}
	public void set심사강화항목총건수(String 심사강화항목총건수) {
		this.심사강화항목총건수 = 심사강화항목총건수;
	}
	public String get심사강화항목적중건수() {
		return 심사강화항목적중건수;
	}
	public void set심사강화항목적중건수(String 심사강화항목적중건수) {
		this.심사강화항목적중건수 = 심사강화항목적중건수;
	}
	public String get거절권유항목총건수() {
		return 거절권유항목총건수;
	}
	public void set거절권유항목총건수(String 거절권유항목총건수) {
		this.거절권유항목총건수 = 거절권유항목총건수;
	}
	public String get거절권유항목적중건수() {
		return 거절권유항목적중건수;
	}
	public void set거절권유항목적중건수(String 거절권유항목적중건수) {
		this.거절권유항목적중건수 = 거절권유항목적중건수;
	}
	public String getNm_if_sub() {
		return nm_if_sub;
	}
	public void setNm_if_sub(String nm_if_sub) {
		this.nm_if_sub = nm_if_sub;
	}
	public HashMap<String, String> getCps_map() {
		return cps_map;
	}
	public void setCps_map(HashMap<String, String> cps_map) {
		this.cps_map = cps_map;
	}
	public String getResDt() {
		return resDt;
	}
	public void setResDt(String resDt) {
		this.resDt = resDt;
	}
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/
	/*****************************************************************************************************************************/


	@Override
	public String getRequest() {

		StringBuffer sb = new StringBuffer();

		//공통정보부
		sb.append( StringUtil.NUM( ""+reqByte, 			4));
		sb.append( StringUtil.STR( getIdTx(), 			9));
		sb.append( StringUtil.STR( getUserNo(), 		8));
		sb.append( StringUtil.STR( getTcpTypeCode(), 	4));
		sb.append( StringUtil.STR( getWorkTypeCode(), 	3));
		sb.append( StringUtil.STR( getTranFlag(), 		1));
		sb.append( StringUtil.STR( getResCode(), 		4));
		sb.append( StringUtil.NUM( getKcbTcpMngNo(),	7)); 		//MMDDhhmmss
		sb.append( StringUtil.NUM( getKcbTcpTranTime(),	14)); 		//MMDDhhmmss
		sb.append( StringUtil.NUM( getComTcpMngNo(),	7)); 		//0000000
		sb.append( StringUtil.NUM( getComTcpTranTime(),	14)); 		//MMDDhhmmss
		sb.append( StringUtil.STR( getKcbSysInfo(),		16));
		sb.append( StringUtil.STR( getRefAgreeCode(), 	1));
		sb.append( StringUtil.STR( getFiller(), 		42));		//total:134

		sb.append( StringUtil.STR( req인증번호, 12 ) );
		sb.append( StringUtil.NUM( req재요청횟수, 2 ) );
		sb.append( StringUtil.STR( req개인or법인구분코드, 1 ) );
		sb.append( StringUtil.NUM( req주민등록번호, 13 ) );
		sb.append( StringUtil.STR( req조회목적코드, 2 ) );
		sb.append( StringUtil.STR( req성명, 50 ) );
		sb.append( StringUtil.STR( req조회지점명, 20 ) );
		sb.append( StringUtil.STR( req담당자ID, 15 ) );

		//029 029;	// 신상정보
		sb.append( StringUtil.NUM( this.req신상정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req신상정보요청건수, 2 ) );
		//030030;	// 대출개설및계좌상태정보
		sb.append( StringUtil.NUM( this.req대출개설및계좌상태정보총수신건수, 3));
		sb.append( StringUtil.NUM( this.req대출개설및계좌상태정보요청건수, 2));
		//034 034;	// 대출이력정보(개인별)
		sb.append( StringUtil.NUM( this.req개인별대출이력정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req개인별대출이력정보요청건수, 2 ) );
		//035 035;	// 대출연체이력정보(개인별)
		sb.append( StringUtil.NUM( this.req개인별대출연체이력정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req개인별대출연체이력정보요청건수, 2 ) );
		//041 041;	// 카드개설및거래상태정보
		sb.append( StringUtil.NUM( this.req카드개설및거래상태정보총수신건수, 3));
		sb.append( StringUtil.NUM( this.req카드개설및거래상태정보요청건수, 2));
		//045 045;	// 카드이력정보(개인별)
		sb.append( StringUtil.NUM( this.req개인별카드이력정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req개인별카드이력정보요청건수, 2 ) );
		//046 046;	// 카드연체이력정보(개인별)
		sb.append( StringUtil.NUM( this.req개인별카드연체이력정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req개인별카드연체이력정보요청건수, 2 ) );
		//061 061;	// 연체정보
		sb.append( StringUtil.NUM( this.req연체정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req연체정보요청건수, 2 ) );
		//062 062;	// 대지급정보
		sb.append( StringUtil.NUM( this.req대지급정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req대지급정보요청건수, 2 ) );
		//065 065;	// 채무불이행정보
		sb.append( StringUtil.NUM( this.req채무불이행정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.req채무불이행정보요청건수, 2 ) );
		//103 103;	// CPS(고정)
		sb.append( StringUtil.NUM( this.reqCPS고정총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.reqCPS고정요청건수, 3 ) );
		//105 105;	// CB SCORE 이력정보
		sb.append( StringUtil.NUM( this.reqCB_SCORE이력정보총수신건수, 3 ) );
		sb.append( StringUtil.NUM( this.reqCB_SCORE이력정보정보요청건수, 2 ) );

		if(this.reqCpsCodeList != null){
			for ( CodeInfo code : this.reqCpsCodeList ){
				sb.append( StringUtil.STR( "103", 3 ) ); // Segment ID
				sb.append( StringUtil.STR( code.getCode_value(), 9 ) ); // CPS 코드
//				sb.append( StringUtil.STR( code.getCodeValue(), 9 ) ); // CPS 코드
			}
		}
		if(NumberUtil.parseInt(this.reqCB_SCORE이력정보정보요청건수) > 0){
			sb.append( StringUtil.STR( "105", 3 ) );		// Segment ID
			sb.append( StringUtil.STR( this.reqScore이력구분코드, 9 ) );	// Score 구분코드(Ver2.5)
		}
		return sb.toString();
	}

	@Override
	public void parseResponse(String recvMsg)  throws UnsupportedEncodingException {
		String noHeaderMsg = getStringByNoHeader(recvMsg); //공통 & 개별응답부 파싱후 나머지 응답정보부 리턴

		//응답정보부 파싱처리
		if(noHeaderMsg.trim().length() > 0) {
			parseResponseInfo(noHeaderMsg);
        }
	}

    /**
     * Method Desc : 응답반복부 정보 파싱
     * 2015 .8 .19 sclee
     * @param pMsg
     */
	private void parseResponseInfo(String pMsg) throws UnsupportedEncodingException {
        String msg = pMsg;
		HashMap<String, Object> map = new HashMap<String, Object>();

		initSegment(); //응답정보부(비트맵) 맵 객체 초기화생성

        byte[] bt 	= msg.getBytes();
        String gubn = StringUtil.getByte2String(bt, 0, 3).trim(); 	//정보구분 3자리 파싱
        AbstractSegment info = segMentMap.get(gubn); 				//정보구분에 매핑되는 객체 맵에서 가져오기

        if(info != null) {
            int cnt = 1;

            do {

            	logger.debug("MSG ==== " + msg);
                //해당 비트맵 응답정보 파싱후 처리결과 맵객체 리턴
                map = info.getParseDataByResData(msg);
                logger.debug("PARSING ==== " + map.get("retObj").toString());

                //파싱데이터 set후 리턴된 비트맵객체 해당 객체 리스트에 add
                addSegmentList(gubn , map.get("retObj"));

                //더이상 응답문자열 없으면 break
                if( StringUtil.isEmpty(String.valueOf(map.get("retStr")))) break;


                msg  = String.valueOf(map.get("retStr"));						//파싱후 남은 문자열 msg에 overwrite
                gubn = StringUtil.getByte2String(msg.getBytes(), 0, 3).trim();  //정보구분 다시 파싱
                info = segMentMap.get(gubn); 									//해당 비트맵 응답정보 파싱후 처리결과 맵객체 리턴

                cnt++;
                if(cnt > 1000)
                    break; //무한루프방지용
            } while (info != null);
        }

        if(listKcb_Segment103 != null && listKcb_Segment103.size() > 0){
            cps_map = new HashMap<String, String>();
            setCpsFiltering();
        }

	}

	private void setCpsFiltering() {

		if(cps_map == null){
			cps_map = new HashMap<String, String>();
		}

		필터링내역 = new String[listKcb_Segment103.size()][6];

		int i = 0;
		int cnt_exam_hard = 0;

		for(Kcb_Segment103 seg103 : listKcb_Segment103){
			필터링내역[i][0] = "심사강화";
			필터링내역[i][1] = seg103.getCd_profile();	//프로파일코드
			필터링내역[i][2] = "0";
			필터링내역[i][3] = "0";
			필터링내역[i][4] = seg103.getResult_profile();  //프로파일결과
			필터링내역[i][5] = "N";		//적중여부

			cnt_exam_hard++;
			i++;

			cps_map.put(seg103.getCd_profile(), seg103.getResult_profile());
		}
		this.심사강화항목총건수 = cnt_exam_hard+"";
	}

	private void initSegment() {

		segMentMap = new HashMap<String, AbstractSegment>();
		segMentMap.put("029",new Kcb_Segment029());
		segMentMap.put("030",new Kcb_Segment030());
		segMentMap.put("034",new Kcb_Segment034());
		segMentMap.put("035",new Kcb_Segment035());
		segMentMap.put("041",new Kcb_Segment041());
		segMentMap.put("045",new Kcb_Segment045());
		segMentMap.put("046",new Kcb_Segment046());
		segMentMap.put("061",new Kcb_Segment061());
		segMentMap.put("062",new Kcb_Segment062());
		segMentMap.put("065",new Kcb_Segment065());
		segMentMap.put("103",new Kcb_Segment103());
		segMentMap.put("105",new Kcb_Segment105());

	}

	private void initListSegment() {

		this.listKcb_Segment029 = new ArrayList<Kcb_Segment029>();
		this.listKcb_Segment030 = new ArrayList<Kcb_Segment030>();
		this.listKcb_Segment034 = new ArrayList<Kcb_Segment034>();
		this.listKcb_Segment035 = new ArrayList<Kcb_Segment035>();
		this.listKcb_Segment041 = new ArrayList<Kcb_Segment041>();
		this.listKcb_Segment045 = new ArrayList<Kcb_Segment045>();
		this.listKcb_Segment046 = new ArrayList<Kcb_Segment046>();
		this.listKcb_Segment061 = new ArrayList<Kcb_Segment061>();
		this.listKcb_Segment062 = new ArrayList<Kcb_Segment062>();
		this.listKcb_Segment065 = new ArrayList<Kcb_Segment065>();
		this.listKcb_Segment103 = new ArrayList<Kcb_Segment103>();
		this.listKcb_Segment105 = new ArrayList<Kcb_Segment105>();
	}
	/**
	 * 해당 응답정보(세그먼트) 객체 리스트에 셋
	 * @param gubn
	 * @param obj
	 */
	private  void addSegmentList(String gubn , Object obj) {

		logger.debug("SEG ==== " + gubn);
		logger.debug("OBJ ==== " + obj.toString());

		int id = StringUtil.isNull(gubn) ? 0 : Integer.valueOf(gubn);
		switch (id){
			case 29: this.listKcb_Segment029.add((Kcb_Segment029)obj);
				break;
			case 30: this.listKcb_Segment030.add((Kcb_Segment030)obj);
				break;
			case 34: this.listKcb_Segment034.add((Kcb_Segment034)obj);
				break;
			case 35: this.listKcb_Segment035.add((Kcb_Segment035)obj);
				break;
			case 41: this.listKcb_Segment041.add((Kcb_Segment041)obj);
				break;
			case 45: this.listKcb_Segment045.add((Kcb_Segment045)obj);
				break;
			case 46: this.listKcb_Segment046.add((Kcb_Segment046)obj);
				break;
			case 61: this.listKcb_Segment061.add((Kcb_Segment061)obj);
				break;
			case 62: this.listKcb_Segment062.add((Kcb_Segment062)obj);
				break;
			case 65: this.listKcb_Segment065.add((Kcb_Segment065)obj);
				break;
			case 103: this.listKcb_Segment103.add((Kcb_Segment103)obj);
				break;
			case 105: this.listKcb_Segment105.add((Kcb_Segment105)obj);
				break;
		}
	}

	@Override
	public void parseHeader(String recvMsg) {

		int pos = 0;
		int len = 0;

        byte[] bt = recvMsg.getBytes();
        pos+=len; len=4; 	setTcplength(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; 	setIdTx(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=8; 	setUserNo(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; 	setTcpTypeCode(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=3; 	setWorkTypeCode(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=1; 	setTranFlag(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=4; 	setResCode(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=7; 	setKcbTcpMngNo(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=14; 	setKcbTcpTranTime(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=7; 	setComTcpMngNo(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=14; 	setComTcpTranTime(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=16; 	setKcbSysInfo(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=1; 	setRefAgreeCode(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=42; 	setFiller(StringUtil.getByte2String(bt,pos,len).trim());

	}

	@Override
	public void transferNiceCb(KcbCreditInfoVO info) throws UnsupportedEncodingException,IOException {

		SocketUtil 		socket 		= new SocketUtil();
		StringBuffer 	response 	= new StringBuffer(); //응답전문 담을 buffer

        String 	ip 			= info.getIp();
        int 	port 		= info.getPort();
        String sendString 	= getRequest(); //요청전문 생성

        log.info("최초요청전문["+sendString+"]");
        info.setMsgRequest(sendString);
        //StringUtil.saveSbToFile(sendString, DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+"최초요청전문_0.txt");

        //전문 송/수신
		String recvMsg = socket.request(ip, port, sendString, this.HEAD_SIZE);

        log.info("최초응답전문["+recvMsg+"]");
//        StringUtil.saveSbToFile(recvMsg, DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+info.getNoPerson()+"_0.txt");

        //응답전문 없으면 더이상 진행x
        if(StringUtil.isEmpty(recvMsg)) return;

        /***
         * 응답전문 공통부 파싱
         * 오류응답 코드인 경우 전문이 짤려서 오기때문에 전체파싱 에러 발생
         */
        parseHeader(recvMsg);

        //응답코드가 정상이 아닌경우
        if(!"0000".equals(getResCode())) {
            log.info("[오류응답코드] ("+getResCode()+")");
            info.setCdCbResponse(getResCode()); 	//조회정보 객체에 응답코드 셋
            info.setMsgResponse(recvMsg); 			//조회정보 객체에 응답전문 셋
            return;
        }

        response.append(recvMsg); 	//최초응답전문 append

        initListSegment(); 			//응답정보부(비트맵) 리스트 객체 초기화생성
        parseResponse(recvMsg); 	//개별응답부 파싱

        //정보연속여부가 'Y'인경우 재요청처리 해서     남은 응답전문 받아야됨
        if("Y".equals(getRes정보연속여부())) {

            int cnt = 1;
            while(true) {

                //재요청정보 셋팅
                setInitDataAgainRequest(info);

                //요청전문
                sendString = getRequest();
                //StringUtil.saveSbToFile(sendString, DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+"재요청전문_"+cnt+".txt");

                //응답전문
                recvMsg = socket.request(ip, port , sendString, this.HEAD_SIZE);
//                StringUtil.saveSbToFile(recvMsg, DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+info.getNoPerson()+"_"+ cnt +".txt");
                log.info("연속 응답전문 ("+cnt+") === ["+recvMsg.toString()+"]");

                //응답전문 없으면 더이상 진행x
                if(StringUtil.isEmpty(recvMsg))	break;

                //공통부 & 개별응답부 파싱후 나머지 응답정보부만 append
                response.append(getStringByNoHeader(recvMsg));

                //응답전문 parsing
                parseResponse(recvMsg);
                cnt++;

                //정보연속여부가 'Y'가 아니면 break
                if( !"Y".equals(getRes정보연속여부())) break;
            }

            log.info("최종응답전문["+response.toString()+"]");
//            StringUtil.saveSbToFile(response.toString(), DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+StringUtil.getRandomString(10)+ "_"+info.getNoPerson()+"_"+ cnt +".txt");
        }

        recvMsg = response.toString().replaceAll("\0", "");
        parseHeader(recvMsg);

        //DEBUG
        //recvMsg 파일에 저장 : kcb socket을 통해서 받은 문자열(받을 때 euckr 인코딩 적용) -> 파일에 저장
//        StringUtil.saveSbToFile(recvMsg, DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+"kcb_res_socket_data_ "+info.getNoPerson()+".txt");

        info.setCdCbResponse(getResCode()); //조회정보 객체에 응답코드 셋
        info.setMsgResponse(recvMsg); 		//조회정보 객체에 응답전문 셋
	}

	@Override
	public void setInitData(KcbCreditInfoVO info) {

		//공통부
		this.idTx 			= "SEV";
		this.userNo 		= "S0047001";	//info.getNo_user(); //회원사코드
		this.tcpTypeCode 	= "0300";
		this.workTypeCode 	= "304";
		this.tranFlag 		= "B";
		this.comTcpTranTime = DateUtil.getCurrentYMD()+DateUtil.getCurrentHIS();
		this.refAgreeCode 	= "1";			//info.getCdAgreeCause();

		//요청정보부(최초요청시)
		this.req인증번호 			= "000000000000";
		this.req재요청횟수 			= "0";
		this.req개인or법인구분코드 	= "4";
		this.req주민등록번호 		= StringUtil.addSpace(StringUtil.nullToString(info.getNoPerson()), 13 - StringUtil.nullToString(info.getNoPerson()).length());
		this.req조회목적코드 		= "77";//info.getCdCbCause();
		this.req성명 				= info.getNmCust();
		this.req조회지점명 			= StringUtil.isEmpty(info.getNm_branch()) ? "본점" : info.getNm_branch();
		this.req담당자ID 			= StringUtil.isEmpty(info.getIdFrt()) ? "SYSTEM" : info.getIdFrt();
		this.req신상정보요청건수 	= "99";				//029;	// 신상정보
		this.req대출개설및계좌상태정보요청건수 	= "99";	//030;	// 대출개설및계좌상태정보
		this.req개인별대출이력정보요청건수 		= "99";	//034;	// 대출이력정보(개인별)
		this.req개인별대출연체이력정보요청건수 	= "99";	//035;	// 대출연체이력정보(개인별)
		this.req카드개설및거래상태정보요청건수 	= "99";	//041;	// 카드개설및거래상태정보
		this.req개인별카드이력정보요청건수 		= "99";	//045;	// 카드이력정보(개인별)
		this.req개인별카드연체이력정보요청건수 	= "99";	//046;	// 카드연체이력정보(개인별)
		this.req연체정보요청건수 				= "99";	//061;	// 연체정보
		this.req대지급정보요청건수 				= "99";	//062;	// 대지급정보
		this.req채무불이행정보요청건수 			= "99";	//065;	// 채무불이행정보
		this.reqByte = 306;
		CodeUtil codeUtil = CodeUtil.getInstance();
		if (codeUtil != null) {
			this.reqCpsCodeList = codeUtil.getCodeList("cd_cps_codes"); //CPS코드 리스트 조회
            if(this.reqCpsCodeList != null){
                this.reqCPS고정요청건수 = this.reqCpsCodeList.size() + "";
                reqByte += (reqCpsCodeList.size() * 12);
            }
            String nm = codeUtil.getNvlCodeName("_CONF_KCB", "YN_105_SEG", "Y");
            nm = (nm !=null)? nm :"";
            if("Y".equals(nm)) {
                this.reqScore이력구분코드 = codeUtil.getNvlCodeName("_CONF_KCB", "INFO_SEG_105", "KKS000010");
                this.reqCB_SCORE이력정보정보요청건수 = "01";
                reqByte += 12;
            }
		}
	}

	@Override
	public void setInitDataAgainRequest(KcbCreditInfoVO info) {

		this.tcpTypeCode 	= "0500";
		this.tranFlag 		= "B";
		this.comTcpMngNo 	= "";
		this.comTcpTranTime = DateUtil.getCurrentYMD()+DateUtil.getCurrentHIS();

		//요청정보부
		this.req인증번호 							= res인증번호;
		this.req재요청횟수 							= (NumberUtil.parseInt(res재요청횟수) +1) + "";
		this.req신상정보총수신건수 					= getRes신상정보총송신건수();					//029	신상정보
		this.req신상정보요청건수 					= getRequestCnt(getRes신상정보총건수(), getRes신상정보총송신건수());
		this.req대출개설및계좌상태정보총수신건수 	= getRes대출개설및계좌상태정보총송신건수();		//030	대출개설 및 계좌상태정보
		this.req대출개설및계좌상태정보요청건수 		= getRequestCnt(getRes대출개설및계좌상태정보총건수(), getRes대출개설및계좌상태정보총송신건수());
		this.req개인별대출이력정보총수신건수 		= getRes개인별대출이력정보총송신건수();			//034	대출이력정보(개인별)
		this.req개인별대출이력정보요청건수 			= getRequestCnt(getRes개인별대출이력정보총건수(), getRes개인별대출이력정보총송신건수());
		this.req개인별대출연체이력정보총수신건수 	= getRes개인별대출연체이력정보총송신건수();		//035	대출연체이력정보(개인별)
		this.req개인별대출연체이력정보요청건수 		= getRequestCnt(getRes개인별대출연체이력정보총건수(), getRes개인별대출연체이력정보총송신건수());
		this.req카드개설및거래상태정보총수신건수 	= getRes카드개설및거래상태정보총송신건수();		//041	카드개설 및 거래상태정보
		this.req카드개설및거래상태정보요청건수 		= getRequestCnt(getRes카드개설및거래상태정보총건수(), getRes카드개설및거래상태정보총송신건수());
		this.req개인별카드이력정보총수신건수		= getRes개인별카드이력정보총송신건수();			//045	카드이력정보(개인별)
		this.req개인별카드이력정보요청건수 			= getRequestCnt(getRes개인별카드이력정보총건수(), getRes개인별카드이력정보총송신건수());
		this.req개인별카드연체이력정보총수신건수 	= getRes개인별카드연체이력정보총송신건수();		//046	카드연체이력정보(개인별)
		this.req개인별카드연체이력정보요청건수 		= getRequestCnt(getRes개인별카드연체이력정보총건수(), getRes개인별카드연체이력정보총송신건수());
		this.req연체정보총수신건수 					= getRes연체정보총송신건수();					//061	연체정보
		this.req연체정보요청건수 					= getRequestCnt(getRes연체정보총건수(), getRes연체정보총송신건수());
		this.req대지급정보총수신건수 				= getRes대지급정보총송신건수();					//062	대지급정보
		this.req대지급정보요청건수 					= getRequestCnt(getRes대지급정보총건수(), getRes대지급정보총송신건수());
		this.req채무불이행정보총수신건수 			= getRes채무불이행정보총송신건수();				//065	채무불이행정보(신용정보사)
		this.req채무불이행정보요청건수 				= getRequestCnt(getRes채무불이행정보총건수(), getRes채무불이행정보총송신건수());
		this.reqByte = 306;

		// 103 세그먼트 요청건수및 전문길이 설정
		logger.debug("620라인: getResCPS고정총송신건수():" + getResCPS고정총송신건수());

		// 103 신용요약정보_온라인
		this.reqCPS고정총수신건수 = getResCPS고정총송신건수();

		if (NumberUtil.parseInt(getResCPS고정총건수()) == NumberUtil.parseInt(getResCPS고정총송신건수())){

			this.reqCPS고정요청건수 = "000";
			this.reqCpsCodeList = null;
		} else {

			logger.debug("620라인: getResCPS고정총송신건수(): IF문 안");
			List<CodeInfo> re신용요약cpsList = new ArrayList<CodeInfo>();
			for(CodeInfo code : reqCpsCodeList) {
				logger.debug("620라인: getResCPS고정총송신건수(): IF문 안:코드:" + code.getCode_value());
				if(StringUtil.isEmpty(code.getCode_value())) code.setCode_value("000000000");
				boolean isExist = false;
				for(Kcb_Segment103 kcb103 : listKcb_Segment103) {
					logger.debug("620라인: getResCPS고정총송신건수(): IF문  안:프로필: " + kcb103.getCd_profile());
					if(code.getCode_value().equals(kcb103.getCd_profile())){
						isExist = true;
						break;
					}
				}
				if(isExist) continue;
				else re신용요약cpsList.add(code);
			}
			reqCpsCodeList = re신용요약cpsList;
			this.reqCPS고정요청건수 = getRequestCnt(getResCPS고정총건수() , getResCPS고정총송신건수());
			reqByte += (reqCpsCodeList.size() * 12);
		}

		//105	스코어 12개월 이력정보
		this.reqCB_SCORE이력정보총수신건수 = getResCB_SCORE이력정보총송신건수();
		this.reqCB_SCORE이력정보정보요청건수 = !"Y".equals(CodeUtil.getInstance().getNvlCodeName("_CONF_KCB", "YN_105_SEG", "Y")) ? "00" : getRequestCnt(getResCB_SCORE이력정보총건수(), getResCB_SCORE이력정보총송신건수());

		if(NumberUtil.parseInt(this.reqCB_SCORE이력정보정보요청건수) > 0) reqByte += 12;

	}

	@Override
	public String getStringByNoHeader(String recvMsg) throws UnsupportedEncodingException {

		StringBuffer noHeader = new StringBuffer();
		int pos = 0;
		int len = 0;

		byte[] bt = recvMsg.getBytes();
		pos+=len; len=4; 	setTcplength(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9; 	setIdTx(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8; 	setUserNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4; 	setTcpTypeCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3; 	setWorkTypeCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1; 	setTranFlag(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4; 	setResCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=7; 	setKcbTcpMngNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=14; 	setKcbTcpTranTime(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=7; 	setComTcpMngNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=14; 	setComTcpTranTime(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=16; 	setKcbSysInfo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1; 	setRefAgreeCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=42; 	setFiller(StringUtil.getByte2String(bt,pos,len).trim());

		pos+=len; len=12;	setRes인증번호(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;	setRes정보연속여부(StringUtil.getByte2String(bt,pos,len).trim()); // 정보연속여부(범용)
		pos+=len; len=2;	setRes재요청횟수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;	setRes개인or법인구분코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=13;	setRes주민등록번호(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50;	setRes성명(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=10;	setRes정보유무체크비트(StringUtil.getByte2String(bt,pos,len).trim());

		pos+=len; len=3;	setRes신상정보총건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes신상정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes신상정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes대출개설및계좌상태정보총건수(StringUtil.getByte2String(bt,pos,len).trim());		//030
		pos+=len; len=3;	setRes대출개설및계좌상태정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes대출개설및계좌상태정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes개인별대출이력정보총건수(StringUtil.getByte2String(bt,pos,len).trim());			//034
		pos+=len; len=3;	setRes개인별대출이력정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes개인별대출이력정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes개인별대출연체이력정보총건수(StringUtil.getByte2String(bt,pos,len).trim());		//035
		pos+=len; len=3;	setRes개인별대출연체이력정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes개인별대출연체이력정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes카드개설및거래상태정보총건수(StringUtil.getByte2String(bt,pos,len).trim());		//041
		pos+=len; len=3;	setRes카드개설및거래상태정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes카드개설및거래상태정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes개인별카드이력정보총건수(StringUtil.getByte2String(bt,pos,len).trim());			//045
		pos+=len; len=3;	setRes개인별카드이력정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes개인별카드이력정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes개인별카드연체이력정보총건수(StringUtil.getByte2String(bt,pos,len).trim());		//046
		pos+=len; len=3;	setRes개인별카드연체이력정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes개인별카드연체이력정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes연체정보총건수(StringUtil.getByte2String(bt,pos,len).trim());						//061
		pos+=len; len=3;	setRes연체정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes연체정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes대지급정보총건수(StringUtil.getByte2String(bt,pos,len).trim());					//062
		pos+=len; len=3;	setRes대지급정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes대지급정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setRes채무불이행정보총건수(StringUtil.getByte2String(bt,pos,len).trim());				//065
		pos+=len; len=3;	setRes채무불이행정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setRes채무불이행정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setResCPS고정총건수(StringUtil.getByte2String(bt,pos,len).trim());						//103
		pos+=len; len=3;	setResCPS고정총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setResCPS고정응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setResCB_SCORE이력정보총건수(StringUtil.getByte2String(bt,pos,len).trim());				//105
		pos+=len; len=3;	setResCB_SCORE이력정보총송신건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;	setResCB_SCORE이력정보응답건수(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len = bt.length - pos;
		noHeader.append(StringUtil.getByte2String(bt,pos,len)) ;


		return noHeader.toString();
	}

	@Override
	public void saveDetailInfo(KcbCreditInfoVO info) {

	}

	@Override
	public void crawlingKcb(KcbCreditInfoVO info) {

	}




	/**
	 * 총건수와 총송신건수를 비교해 같으면 00을 리턴 다르면 총건수에서 총송신건수를 뺸 값을 리턴.
	 * @param total_cnt 총건수
	 * @param recv_cnt 총송신건수
	 * @return
	 */
	private String getRequestCnt(String total_cnt , String recv_cnt) {

		if(NumberUtil.parseInt(total_cnt) == NumberUtil.parseInt(recv_cnt))
    		return "00";
    	else
    		return String.valueOf(NumberUtil.parseInt(total_cnt) - NumberUtil.parseInt(recv_cnt));
	}
}
