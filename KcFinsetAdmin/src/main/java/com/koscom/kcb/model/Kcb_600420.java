package com.koscom.kcb.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.credit.service.CreditManager;
import com.koscom.kcb.model.seg.AbstractSegment;
import com.koscom.util.DateUtil;
import com.koscom.util.SocketUtil;
import com.koscom.util.StringUtil;

public class Kcb_600420 extends AbstractKcbInfo {
	private static final long serialVersionUID = -4190990709853986332L;

	private static final Logger log = LoggerFactory.getLogger(Kcb_600420.class);

	//등록정보부(BASE) 변수
	private String reqBirth;					//AN	8	8	O	생년월일            	출생년월일 8자리 숫자값 (YYYYMMDD)
	private String reqSex;						//AN	1	9	O	성별              		1:남, 2:여
	private String reqName;                     //AN	50	59	O   성명
	private String reqCustNo;                 	//AN	20	79	O   관리번호(회원번호)
	private String reqCompanyCode;           	//AN	12	91	O	식별회원사코드
	private String reqDI;                 		//AN	64	155	O	DI
	private String reqFILLER;              		//AN	45	200	O	FILLER

	//등록/응답정보부(A2) 변수
	private String reqsSegmentID;				//AN	3	203	O	Segment 식별자.			상수값 “V2”
	private String reqsRegReason;            	//AN	2	205	O	등록사유코드			01: 발생(신규 제휴 서비스 신청), 02:수정(제휴 서비스 신청내용 수정), 03:해지, 09:URL조회
	private String reqsRegResonDate;       		//AN	14	219	O	등록사유발생일시
	private String reqsEmail;         			//AN	50	269	O	이메일주소
	private String reqsHpNo;					//AN	12	281	O	휴대폰번호
	private String reqsJoinDate;      			//N		8	289 O	가입일자
	private String reqsTermDate;      			//N		8	297 O   해지일자
	private String reqsSmsYn;      				//AN	1	298 O   신용통보 SMS수신여부
	private String reqsEmailYn;					//AN	1	299 O   신용통보 이메일수신여부
	private String reqsServiceCode;      		//AN	3	302 O   서비스코드
	private String reqsTermsYn;      			//AN	1	303 O   회원약관동의여부
	private String reqsAuthYn;      			//AN	1	304 O   본인인증여부
	private String reqsClassCode;      			//AN	10	314 O   업무구분코드			0600420
	private String reqsMenuCode;      			//AN	10	324 O   메뉴구분코드			200:모바일 제휴보고서, 210:제휴가 가공보고서
	private String reqsViewCode;      			//AN	20	344 O   화면구분코드			s07143331300:제휴보고서, s02173986405:대출보고서
	private String reqsIP;	 					//AN	15	359 O   요청IP
	private String reqsReqDomain;      			//AN	50	409 O   요청도메인
	private String resProtocol;      			//AN	10	419		프로토콜
	private String resDomain;      				//AN	50	469 	도메인
	private String resURI;      				//AN	200	669		URI
	private String resParam;      				//AN	100	769		암호화파라미터
	private String resFILLER;      				//AN	31	800		FILLER

	private HashMap<String, AbstractSegment> segMentMap; //세그먼트_객체맵
	private int reqByte;

	//Loop Kcb_Segment

	// 필터링내역부
	private  HashMap<String, String> cps_map = null;
	private String nm_if_sub;
	@Override
	public String getRequest() throws UnsupportedEncodingException {

		StringBuffer sb = new StringBuffer();

		//공통정보부
		sb.append( StringUtil.NUM( ""+reqByte, 			4) );
		sb.append( StringUtil.STR( getIdTx(), 			9) );
		sb.append( StringUtil.STR( getUserNo(), 		8) );
		sb.append( StringUtil.STR( getTcpTypeCode(), 	4) );
		sb.append( StringUtil.STR( getWorkTypeCode(), 	3) );
		sb.append( StringUtil.STR( getTranFlag(), 		1) );
		sb.append( StringUtil.STR( getResCode(), 		4) );
		sb.append( StringUtil.NUM( getKcbTcpMngNo(),	7) ); 		//MMDDhhmmss
		sb.append( StringUtil.NUM( getKcbTcpTranTime(),	14)); 		//MMDDhhmmss
		sb.append( StringUtil.NUM( getComTcpMngNo(),	7) ); 		//0000000
		sb.append( StringUtil.NUM( getComTcpTranTime(),	14)); 		//MMDDhhmmss
		sb.append( StringUtil.STR( getKcbSysInfo(),		16));
		sb.append( StringUtil.STR( getFiller(), 		43));		//total:130
		log.debug("공통정보부 length = " + sb.toString().getBytes("KSC5601").length);

		//등록정보부(BASE)
		sb.append( StringUtil.STR( this.reqBirth,		8) );		//생년월일
		sb.append( StringUtil.STR( this.reqSex,			1) );		//성별   		1:남, 2:여
		sb.append( StringUtil.STR( this.reqName,        50));       //성명
		sb.append( StringUtil.STR( this.reqCustNo,      20));       //관리번호(회원번호)
		sb.append( StringUtil.STR( this.reqCompanyCode, 12));       //식별회원사코드
		sb.append( StringUtil.STR( this.reqDI,          64));
		sb.append( StringUtil.STR( this.reqFILLER,      45));
		log.debug("등록정보부 length = " + sb.toString().getBytes("KSC5601").length);

		//등록/응답정보부(A2) 변수
		sb.append( StringUtil.STR( this.reqsSegmentID,		3)  );			//Segment 식별자.		상수값 “V2”
		sb.append( StringUtil.STR( this.reqsRegReason,      2)  );	      	//등록사유코드			01: 발생(신규 제휴 서비스 신청), 02:수정(제휴 서비스 신청내용 수정), 03:해지, 09:URL조회
		sb.append( StringUtil.NUM( this.reqsRegResonDate,   14) );	   		//등록사유발생일시
		sb.append( StringUtil.STR( this.reqsEmail,         	50) );			//이메일주소
		sb.append( StringUtil.STR( this.reqsHpNo,			12) );			//휴대폰번호
		sb.append( StringUtil.NUM( this.reqsJoinDate,      	8)  );			//가입일자
		sb.append( StringUtil.NUM( this.reqsTermDate,      	8)  );			//해지일자
		sb.append( StringUtil.STR( this.reqsSmsYn,      	1)  );			//신용통보 SMS수신여부
		sb.append( StringUtil.STR( this.reqsEmailYn,		1)  );			//신용통보 이메일수신여부
		sb.append( StringUtil.STR( this.reqsServiceCode,    3)  );	  		//서비스코드
		sb.append( StringUtil.STR( this.reqsTermsYn,      	1)  );			//회원약관동의여부
		sb.append( StringUtil.STR( this.reqsAuthYn,      	1)  );			//본인인증여부
		sb.append( StringUtil.STR( this.reqsClassCode,      10) );			//업무구분코드			0600420
		sb.append( StringUtil.STR( this.reqsMenuCode,      	10) );			//메뉴구분코드			200:모바일 제휴보고서, 210:제휴가 가공보고서
		sb.append( StringUtil.STR( this.reqsViewCode,      	20) );			//화면구분코드			s07143331300:제휴보고서, s02173986405:대출보고서
		sb.append( StringUtil.STR( this.reqsIP,	 			15) );			//요청IP
		sb.append( StringUtil.STR( this.reqsReqDomain,      50) );			//요청도메인
		sb.append( StringUtil.STR( this.resProtocol,      	10) );			//프로토콜
		sb.append( StringUtil.STR( this.resDomain,      	50) );			//도메인
		sb.append( StringUtil.STR( this.resURI,      		200));			//URI
		sb.append( StringUtil.STR( this.resParam,      		100));			//암호화파라미터
		sb.append( StringUtil.STR( this.resFILLER,      	31) );			//FILLER

		log.debug("total length = " + sb.toString().getBytes("KSC5601").length);
		this.kcb600DebugLog();

		return sb.toString();
	}

	@Override
	public void parseResponse(String recvMsg) throws UnsupportedEncodingException {

		String noHeaderMsg = getStringByNoHeader(recvMsg); //공통 & 개별응답부 파싱후 나머지 응답정보부 리턴

		//응답정보부 파싱처리
		if(noHeaderMsg.trim().length() > 0)	parseResponseInfo(noHeaderMsg);
	}

	/**
	 * Method Desc : 응답반복부 정보 파싱
	 * 2015 .8 .19 sclee
	 * @param msg
	 */
	private void parseResponseInfo(String pMsg) throws UnsupportedEncodingException  {
		String msg = pMsg;
		HashMap<String, Object> map = new HashMap<String, Object>();
		initSegment(); //응답정보부(비트맵) 리스트,맵 객체 초기화생성

		byte[] bt = msg.getBytes();
		String gubn = StringUtil.getByte2String(bt, 0, 3).trim(); 	//정보구분 2자리 파싱
		AbstractSegment info = segMentMap.get(gubn); 				//정보구분에 매핑되는 객체 맵에서 가져오기

		if(info != null) {
			int cnt = 1;

			do {
				map = info.getParseDataByResData(msg); 		//해당 비트맵 응답정보 파싱후 처리결과 맵객체 리턴
				addSegmentList(gubn , map.get("retObj")); 	//파싱데이터 set후 리턴된 비트맵객체 해당 객체 리스트에 add

				if( StringUtil.isEmpty(String.valueOf(map.get("retStr")))) break; // 더이상 응답문자열 없으면 break

				msg  = String.valueOf(map.get("retStr")); 						//파싱후 남은 문자열 msg에 overwrite
				gubn = StringUtil.getByte2String(msg.getBytes(), 0, 3).trim(); 	//정보구분 다시 파싱
				info = segMentMap.get(gubn); 									//해당 비트맵 응답정보 파싱후 처리결과 맵객체 리턴
				cnt++;
				if(cnt > 1000) break; //무한루프방지용
			} while(info != null);
		}
	}


	private void initSegment() {
		segMentMap = new HashMap<String, AbstractSegment>();
//		segMentMap.put("029",new Kcb_Segment029());
//		this.listKcb_Segment029 = new ArrayList<Kcb_Segment029>();
	}


	/**
	 * 해당 응답정보(세그먼트) 객체 리스트에 셋
	 * @param gubn
	 * @param obj
	 */
	private  void addSegmentList(String gubn , Object obj) {

//		int id = StringUtil.isNull(gubn) ? 0 : Integer.valueOf(gubn);
//		switch (id){
//			case 29: this.listKcb_Segment029.add((Kcb_Segment029)obj);
//				break;
//		}
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
		pos+=len; len=43; 	setFiller(StringUtil.getByte2String(bt,pos,len).trim());
	}

	@Override
	public void transferNiceCb(KcbCreditInfoVO info) throws UnsupportedEncodingException,IOException {

		SocketUtil socket = new SocketUtil();
		StringBuffer response = new StringBuffer(); //응답전문 담을 buffer

		String 	ip 			= info.getIp();
		int 	port 		= info.getPort();
		String sendString 	= getRequest(); //요청전문 생성

		info.setMsgRequest(sendString);
		log.info("최초요청전문["+sendString+"]");

		//1. 전문 송/수신
		String recvMsg = socket.request(ip, port , sendString, this.HEAD_SIZE);
//			String recvMsg = "0930ACSEV    S00470000610420C0000532484720171121164357000258420171121164400SE1I416434323150                                           198511191박준수                                            P000000040          P18760000000MC0GCCqGSIb3DQIJAyEAf63Rpmu9w6Rm92vNN+9lfNC1OT30mbn8eSfCPeveW2c=                                             A2 0120171121164400                                                  01026882453 2017112100000000NN101YY0600420   200       s07143331300        219.255.136.242koscom.finset.co.kr                               http://   taffiliate.allcredit.co.kr:7077                   /S004701A01/do.jams?s=s07143331300&EPARAM=                                                                                                                                                              2SauLiPzThJQNI3DL0i3NIIwQPDHYyc+AUL5K7Jslck=                                                                                       ";
		log.info("최초응답전문["+recvMsg+"]");

		//2. 응답전문 parsing
		if(StringUtil.isEmpty(recvMsg)) return; //응답전문 없으면 더이상 진행x

		parseHeader(recvMsg); //응답전문 공통부 파싱(오류응답코드인경우 전문이 짤려서 오기때문에 전체파싱 에러나는것을 방지하기 위해)

		//응답코드가 정상이 아닌경우
		if(!"0000".equals(getResCode())) {
			log.error("[오류응답코드] ("+getResCode()+")");
			info.setCdCbResponse(getResCode()); //조회정보 객체에 응답코드 셋
			info.setMsgResponse(recvMsg); //조회정보 객체에 응답전문 셋
			return;
		}

		response.append(recvMsg); //최초응답전문 append
		parseResponse(recvMsg); //개별응답부 파싱

		//URI 정보 return
		info.setKcbURI(getResProtocol() + getResDomain() + getResURI() + URLEncoder.encode(getResParam()));
		log.info("[URI정보] " + info.getKcbURI());

		recvMsg = response.toString().replaceAll("\0", "");
		parseHeader(recvMsg);

		info.setCdCbResponse(getResCode()); //조회정보 객체에 응답코드 셋
		info.setMsgResponse(recvMsg); 		//조회정보 객체에 응답전문 셋
	}

	@Override
	public void setInitData(KcbCreditInfoVO info) {

		//공통정보부(BASE)
		this.idTx 				= "ACSEV";
		this.userNo 			= "S0047000";		//info.getNo_user(); //회원사코드
		this.tcpTypeCode 		= "0600";
		this.workTypeCode 		= "420";
		this.tranFlag 			= "B";
		this.comTcpMngNo		= info.getKcb_seq();
		this.comTcpTranTime		= DateUtil.getCurrentYMD()+DateUtil.getCurrentHIS();
		this.kcbSysInfo			= "PZ1I115010876820";

		//등록정보부(BASE)
		this.reqBirth 			= StringUtil.splitBgn(info.getBgn(), "BOD");//"199002171";
		this.reqSex 			= StringUtil.splitBgn(info.getBgn(), "G");
		this.reqName 			= info.getNmCust();
		this.reqCustNo			= info.getNoPerson();	//관리번호
		this.reqCompanyCode		= "P18760000000";
		this.reqDI		 		= info.getDi();
		this.reqFILLER 			= "";

		//등록/응답정보부(A2)
		this.reqsSegmentID			= "A2";					//상수값 “V2”
		this.reqsRegReason 		 	= info.getCd_regist();	//01: 발생(신규 제휴 서비스 신청), 02:수정(제휴 서비스 신청내용 수정), 03:해지, 09:URL조회
		this.reqsRegResonDate		= DateUtil.getCurrentYMD()+DateUtil.getCurrentHIS();
		this.reqsHpNo	 			= info.getHp();
		this.reqsJoinDate			= info.getCd_regist().equals("01") ? DateUtil.getCurrentYMD() : "";
		this.reqsTermDate			= info.getCd_regist().equals("03") ? DateUtil.getCurrentYMD() : "";
		this.reqsSmsYn				= "N";
		this.reqsEmailYn			= "N";
		this.reqsServiceCode		= "101";
		this.reqsTermsYn			= "Y";
		this.reqsAuthYn				= "Y";
		this.reqsClassCode			= "0600420";
		this.reqsMenuCode			= info.getReq_menu_code();
		this.reqsViewCode			= info.getReq_view_code();
		this.reqsIP					= info.getIp();
		this.reqsReqDomain			= "koscom.finset.co.kr";
		this.reqByte 				= 930;
	}

	@Override
	public String getStringByNoHeader(String recvMsg) {

		StringBuffer noHeader = new StringBuffer();
		int pos = 0;
		int len = 0;

		byte[] bt = recvMsg.getBytes();
		//공통
		pos+=len; len=4;	setTcplength(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=9;	setIdTx(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8;	setUserNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4;	setTcpTypeCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;	setWorkTypeCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;	setTranFlag(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4;	setResCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=7;	setKcbTcpMngNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=14;	setKcbTcpTranTime(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=7;	setComTcpMngNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=14;	setComTcpTranTime(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=16;	setKcbSysInfo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=43;	setFiller(StringUtil.getByte2String(bt,pos,len).trim());

		//등록정보부(BASE)
		pos+=len; len=8;	setReqBirth(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;	setReqSex(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50;	setReqName(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=20;	setReqCustNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=12;	setReqCompanyCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=64;	setReqDI(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=45;	setReqFILLER(StringUtil.getByte2String(bt,pos,len).trim());

		//등록/응답정보부(A2) 변수
		pos+=len; len=3;    setReqsSegmentID(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2;    setReqsRegReason(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=14;   setReqsRegResonDate(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50;   setReqsEmail(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=12;   setReqsHpNo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8;    setReqsJoinDate(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8;    setReqsTermDate(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;    setReqsSmsYn(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;    setReqsEmailYn(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3;    setReqsServiceCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;    setReqsTermsYn(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1;    setReqsAuthYn(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=10;   setReqsClassCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=10;   setReqsMenuCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=20;   setReqsViewCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=15;   setReqsIP(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50;   setReqsReqDomain(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=10;   setResProtocol(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50;   setResDomain(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=200;  setResURI(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=100;  setResParam(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=31;  	setResFILLER(StringUtil.getByte2String(bt,pos,len).trim());

		pos+=len; len=bt.length-pos;
		noHeader.append(StringUtil.getByte2String(bt,pos,len));

		return noHeader.toString();
	}



	public String getReqBirth() {
		return reqBirth;
	}


	public void setReqBirth(String reqBirth) {
		this.reqBirth = reqBirth;
	}


	public String getReqSex() {
		return reqSex;
	}


	public void setReqSex(String reqSex) {
		this.reqSex = reqSex;
	}


	public String getReqName() {
		return reqName;
	}


	public void setReqName(String reqName) {
		this.reqName = reqName;
	}


	public String getReqCustNo() {
		return reqCustNo;
	}


	public void setReqCustNo(String reqCustNo) {
		this.reqCustNo = reqCustNo;
	}


	public String getReqCompanyCode() {
		return reqCompanyCode;
	}


	public void setReqCompanyCode(String reqCompanyCode) {
		this.reqCompanyCode = reqCompanyCode;
	}


	public String getReqDI() {
		return reqDI;
	}


	public void setReqDI(String reqDI) {
		this.reqDI = reqDI;
	}


	public String getReqFILLER() {
		return reqFILLER;
	}


	public void setReqFILLER(String reqFILLER) {
		this.reqFILLER = reqFILLER;
	}


	public String getReqsSegmentID() {
		return reqsSegmentID;
	}


	public void setReqsSegmentID(String reqsSegmentID) {
		this.reqsSegmentID = reqsSegmentID;
	}


	public String getReqsRegReason() {
		return reqsRegReason;
	}


	public void setReqsRegReason(String reqsRegReason) {
		this.reqsRegReason = reqsRegReason;
	}


	public String getReqsRegResonDate() {
		return reqsRegResonDate;
	}


	public void setReqsRegResonDate(String reqsRegResonDate) {
		this.reqsRegResonDate = reqsRegResonDate;
	}


	public String getReqsEmail() {
		return reqsEmail;
	}


	public void setReqsEmail(String reqsEmail) {
		this.reqsEmail = reqsEmail;
	}


	public String getReqsHpNo() {
		return reqsHpNo;
	}


	public void setReqsHpNo(String reqsHpNo) {
		this.reqsHpNo = reqsHpNo;
	}


	public String getReqsJoinDate() {
		return reqsJoinDate;
	}


	public void setReqsJoinDate(String reqsJoinDate) {
		this.reqsJoinDate = reqsJoinDate;
	}


	public String getReqsTermDate() {
		return reqsTermDate;
	}


	public void setReqsTermDate(String reqsTermDate) {
		this.reqsTermDate = reqsTermDate;
	}


	public String getReqsSmsYn() {
		return reqsSmsYn;
	}


	public void setReqsSmsYn(String reqsSmsYn) {
		this.reqsSmsYn = reqsSmsYn;
	}


	public String getReqsEmailYn() {
		return reqsEmailYn;
	}


	public void setReqsEmailYn(String reqsEmailYn) {
		this.reqsEmailYn = reqsEmailYn;
	}


	public String getReqsServiceCode() {
		return reqsServiceCode;
	}


	public void setReqsServiceCode(String reqsServiceCode) {
		this.reqsServiceCode = reqsServiceCode;
	}


	public String getReqsTermsYn() {
		return reqsTermsYn;
	}


	public void setReqsTermsYn(String reqsTermsYn) {
		this.reqsTermsYn = reqsTermsYn;
	}


	public String getReqsAuthYn() {
		return reqsAuthYn;
	}


	public void setReqsAuthYn(String reqsAuthYn) {
		this.reqsAuthYn = reqsAuthYn;
	}


	public String getReqsClassCode() {
		return reqsClassCode;
	}


	public void setReqsClassCode(String reqsClassCode) {
		this.reqsClassCode = reqsClassCode;
	}


	public String getReqsMenuCode() {
		return reqsMenuCode;
	}


	public void setReqsMenuCode(String reqsMenuCode) {
		this.reqsMenuCode = reqsMenuCode;
	}


	public String getReqsViewCode() {
		return reqsViewCode;
	}


	public void setReqsViewCode(String reqsViewCode) {
		this.reqsViewCode = reqsViewCode;
	}


	public String getReqsIP() {
		return reqsIP;
	}


	public void setReqsIP(String reqsIP) {
		this.reqsIP = reqsIP;
	}


	public String getReqsReqDomain() {
		return reqsReqDomain;
	}


	public void setReqsReqDomain(String reqsReqDomain) {
		this.reqsReqDomain = reqsReqDomain;
	}


	public String getResProtocol() {
		return resProtocol;
	}


	public void setResProtocol(String resProtocol) {
		this.resProtocol = resProtocol;
	}


	public String getResDomain() {
		return resDomain;
	}


	public void setResDomain(String resDomain) {
		this.resDomain = resDomain;
	}


	public String getResURI() {
		return resURI;
	}


	public void setResURI(String resURI) {
		this.resURI = resURI;
	}


	public String getResParam() {
		return resParam;
	}


	public void setResParam(String resParam) {
		this.resParam = resParam;
	}


	public String getReqsFILLER() {
		return resFILLER;
	}


	public void setResFILLER(String resFILLER) {
		this.resFILLER = resFILLER;
	}


	public HashMap<String, AbstractSegment> getSegMentMap() {
		return segMentMap;
	}


	public void setSegMentMap(HashMap<String, AbstractSegment> segMentMap) {
		this.segMentMap = segMentMap;
	}


	public int getReqByte() {
		return reqByte;
	}


	public void setReqByte(int reqByte) {
		this.reqByte = reqByte;
	}


	public HashMap<String, String> getCps_map() {
		return cps_map;
	}


	public void setCps_map(HashMap<String, String> cps_map) {
		this.cps_map = cps_map;
	}


	public String getNm_if_sub() {
		return nm_if_sub;
	}


	public void setNm_if_sub(String nm_if_sub) {
		this.nm_if_sub = nm_if_sub;
	}


	public void kcb600DebugLog() {

		//공통정보부
		log.debug( StringUtil.NUM( ""+reqByte, 			4) );
		log.debug( StringUtil.STR( getIdTx(), 			9) );
		log.debug( StringUtil.STR( getUserNo(), 		8) );
		log.debug( StringUtil.STR( getTcpTypeCode(), 	4) );
		log.debug( StringUtil.STR( getWorkTypeCode(), 	3) );
		log.debug( StringUtil.STR( getTranFlag(), 		1) );
		log.debug( StringUtil.STR( getResCode(), 		4) );
		log.debug( StringUtil.NUM( getKcbTcpMngNo(),	7) ); 		//0000000
		log.debug( StringUtil.NUM( getKcbTcpTranTime(),	14)); 		//MMDDhhmmss
		log.debug( StringUtil.NUM( getComTcpMngNo(),	7) ); 		//0000000
		log.debug( StringUtil.NUM( getComTcpTranTime(),	14)); 		//MMDDhhmmss
		log.debug( StringUtil.STR( getKcbSysInfo(),		16));
		log.debug( StringUtil.STR( getFiller(), 		43));		//total:134


		//등록정보부(BASE)
		log.debug( StringUtil.STR( this.reqBirth,		8) );		//생년월일
		log.debug( StringUtil.STR( this.reqSex,			1) );		//성별   		1:남, 2:여
		log.debug( StringUtil.STR( this.reqName,        50));       //성명
		log.debug( StringUtil.STR( this.reqCustNo,      20));       //관리번호(회원번호)
		log.debug( StringUtil.STR( this.reqCompanyCode, 12));       //식별회원사코드
		log.debug( StringUtil.STR( this.reqDI,          64));
		log.debug( StringUtil.STR( this.reqFILLER,      45));

		//등록/응답정보부(A2) 변수
		log.debug( StringUtil.STR( this.reqsSegmentID,		3)  );			//Segment 식별자.		상수값 “V2”
		log.debug( StringUtil.STR( this.reqsRegReason,      2)  );	      	//등록사유코드			01: 발생(신규 제휴 서비스 신청), 02:수정(제휴 서비스 신청내용 수정), 03:해지, 09:URL조회
		log.debug( StringUtil.STR( this.reqsRegResonDate,   14) );	   		//등록사유발생일시
		log.debug( StringUtil.STR( this.reqsEmail,         	50) );			//이메일주소
		log.debug( StringUtil.STR( this.reqsHpNo,			12) );			//휴대폰번호
		log.debug( StringUtil.NUM( this.reqsJoinDate,      	8)  );			//가입일자
		log.debug( StringUtil.NUM( this.reqsTermDate,      	8)  );			//해지일자
		log.debug( StringUtil.STR( this.reqsSmsYn,      	1)  );			//신용통보 SMS수신여부
		log.debug( StringUtil.STR( this.reqsEmailYn,		1)  );			//신용통보 이메일수신여부
		log.debug( StringUtil.STR( this.reqsServiceCode,    3)  );	  		//서비스코드
		log.debug( StringUtil.STR( this.reqsTermsYn,      	1)  );			//회원약관동의여부
		log.debug( StringUtil.STR( this.reqsAuthYn,      	1)  );			//본인인증여부
		log.debug( StringUtil.STR( this.reqsClassCode,      10) );			//업무구분코드			0600420
		log.debug( StringUtil.STR( this.reqsMenuCode,      	10) );			//메뉴구분코드			200:모바일 제휴보고서, 210:제휴가 가공보고서
		log.debug( StringUtil.STR( this.reqsViewCode,      	20) );			//화면구분코드			s07143331300:제휴보고서, s02173986405:대출보고서
		log.debug( StringUtil.STR( this.reqsIP,	 			15) );			//요청IP
		log.debug( StringUtil.STR( this.reqsReqDomain,      50) );			//요청도메인
		log.debug( StringUtil.STR( this.resProtocol,      	10) );			//프로토콜
		log.debug( StringUtil.STR( this.resDomain,      	50) );			//도메인
		log.debug( StringUtil.STR( this.resURI,      		200));			//URI
		log.debug( StringUtil.STR( this.resParam,      		100));			//암호화파라미터
		log.debug( StringUtil.STR( this.resFILLER,      	31) );			//FILLER
	}

	@Override
	public void saveDetailInfo(KcbCreditInfoVO info) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setInitDataAgainRequest(KcbCreditInfoVO info) {
		// TODO Auto-generated method stub

	}


	@Override
	public void crawlingKcb(KcbCreditInfoVO info) {
		// TODO Auto-generated method stub

	}
}
