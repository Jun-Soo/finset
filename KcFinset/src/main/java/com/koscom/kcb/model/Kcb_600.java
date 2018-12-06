package com.koscom.kcb.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.DateUtil;
import com.koscom.util.SocketUtil;
import com.koscom.util.StringUtil;

public class Kcb_600 extends AbstractKcbInfo {
	private static final long serialVersionUID = -4190990709853986332L;

	private static final Logger log = LoggerFactory.getLogger(Kcb_600.class);

	//요청정보부 변수
	private String reqSegmentID;			//AN	3	3	O	1) (No.1) Segment ID : Segment 식별자. 상수값 “V2”
	private String req등록사유코드;			//AN	2	5	O	1: 발생 신규 고객ID 등록&업데이트,   5 : 고객 ID 삭제
	private String req등록번호구분코드;		//AN	1	6	O	1: 회원사 내부 고객번호 등록 시,2:KCB ID (KCB ID 발급 시)
	private String req등록번호;				//AN	40	46	O
	private String req등록방식구분코드;		//AN	1	47	O	1: 성명+생년월일+성별+휴대폰번호, 2: 성명+생년월일+성별+주소, 3: 성명+생년월일+성별+이메일, 4: DI, 5: 주민등록번호(외국인등록번호), 6: 성명+생년월일+성별+출생등록지
	private String req성명;					//AN	50	97	O
	private String req생년월일;				//AN	8	105	O	출생년월일 8자리 숫자값 (YYYYMMDD)
	private String req성별코드;				//AN	1	106	O	1:남, 2:여
	private String req휴대전화식별번호;		//AN	3	109	O	010 1234 5678 (인증시 필수아님)
	private String req휴대전화국번호;		//AN	4	113	O	(인증시 필수아님)
	private String req휴대전화개별번호;		//AN	4	117	O	(인증시 필수아님)
	private String req주소;					//AN	200	317	O	(인증시 필수아님)
	private String req이메일;				//AN	50	367	O	(인증시 필수아님)
	private String req고유식별정보;			//AN	100	467	O	(No.10)에서 ‘01’인 경우에만 입력 : DI값 (64byte) + CP코드(12byte)
	private String req기준일자;				//AN	8	475	O
	private String req출생등록지코드;		//AN	2	477	O	
	private String reqFILLER;				//AN	323	800	O

	//응답정보부
	private String resSegmentID;			//AN	3
	private String res등록사유코드;			//AN	2	01: 신규 고객 ID 등록 및 고객 ID에 대한 정보가 변경되어 업데이트 할 경우에 입력  05: 고객 ID 삭제할 경우에 입력
	private String res등록번호구분코드;		//AN	1	1: 회원사 고객 ID 2: KCB ID
	private String res등록번호;				//AN	40 	고객 ID 등록 요청한 경우, 회원사가 입력한 고객 ID 응답 KCB ID발급 요청하여 정상적으로 발급 된 경우, 발급된 KCB ID 응답
	private String res등록방식구분코드;		//AN	1	1: 성명+생년월일+성별+휴대폰번호, 2: 성명+생년월일+성별+주소, 3: 성명+생년월일+성별+이메일, 4: DI, 5: 주민등록번호(외국인등록번호), 6: 성명+생년월일+성별+출생등록지
	private String res성명;					//AN	50 	예) 홍길동
	private String res생년월일;				//AN	8  	예) 19721230
	private String res성별코드;				//AN	1  	1: 남자 2: 여자
	private String res휴대전화식별번호;		//AN	3
	private String res휴대전화국번호;		//AN	4
	private String res휴대전화개별번호;		//AN	4
	private String res주소;					//AN	200
	private String res이메일;				//AN	50
	private String res고유식별정보;			//AN	100	DI값 (64byte) + CP코드(12byte)
	private String res기준일자;				//AN	8
	private String res출생등록지코드;		//AN	2
	private String resFILLER;				//AN	323
	private String res오류응답코드;         //AN	4  	오류가 발생한 경우, 각 case 별로 응답코드 전송 예) L101 : Format 에러
	private String res오류체크비트;         //AN	26	어떤 항목에서 오류가 발생했는지 각 항목 위치의 오류 값을 전송함

	private HashMap<String, AbstractSegment> segMentMap; //세그먼트_객체맵
	private int reqByte ;

	//Loop Kcb_Segment

	// 필터링내역부
	private  HashMap<String, String> cps_map = null;
	private String nm_if_sub;
	@Override
	public String getRequest() {

		StringBuffer sb = new StringBuffer();
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
		sb.append( StringUtil.STR( getRefAgreeCode(), 	1 ));
		sb.append( StringUtil.STR( getFiller(), 		42));		//total:134

		sb.append( StringUtil.STR( reqSegmentID,3));
		sb.append( StringUtil.STR( req등록사유코드,2));
		sb.append( StringUtil.STR( req등록번호구분코드,1));
		sb.append( StringUtil.STR( req등록번호,40));
		sb.append( StringUtil.STR( req등록방식구분코드,1));
		sb.append( StringUtil.STR( req성명,50));
		sb.append( StringUtil.STR( req생년월일,8));
		sb.append( StringUtil.STR( req성별코드,1));
		sb.append( StringUtil.STR( req휴대전화식별번호,3));
		sb.append( StringUtil.STR( req휴대전화국번호,4));
		sb.append( StringUtil.STR( req휴대전화개별번호,4));
		sb.append( StringUtil.STR( req주소,200));
		sb.append( StringUtil.STR( req이메일,50));
		sb.append( StringUtil.STR( req고유식별정보,100));
		sb.append( StringUtil.STR( req기준일자,8));
		sb.append( StringUtil.STR( req출생등록지코드,2));
		sb.append( StringUtil.STR( reqFILLER,323));
		return sb.toString();
	}
	@Override
	public void parseResponse(String recvMsg) throws UnsupportedEncodingException {
		String noHeaderMsg = getStringByNoHeader(recvMsg); //공통 & 개별응답부 파싱후 나머지 응답정보부 리턴
		//응답정보부 파싱처리
		parseResponseInfo(noHeaderMsg);

	}
	/**
	 * Method Desc : 응답반복부 정보 파싱
	 * 2015 .8 .19 sclee
	 * @param pMsg
	 */
	private void parseResponseInfo(String pMsg) throws UnsupportedEncodingException {
		String msg = pMsg;
		HashMap<String, Object> map = new HashMap<String, Object>();
		initSegment(); //응답정보부(비트맵) 리스트,맵 객체 초기화생성

		byte[] bt = msg.getBytes();
		String gubn = StringUtil.getByte2String(bt, 0, 3).trim(); //정보구분 2자리 파싱
		AbstractSegment info = segMentMap.get(gubn); //정보구분에 매핑되는 객체 맵에서 가져오기
		if(info != null) {
			int cnt = 1;
			do {
				map = info.getParseDataByResData(msg); //해당 비트맵 응답정보 파싱후 처리결과 맵객체 리턴
				addSegmentList(gubn , map.get("retObj")); //파싱데이터 set후 리턴된 비트맵객체 해당 객체 리스트에 add
				if( StringUtil.isEmpty(String.valueOf(map.get("retStr")))) break; //더이상 응답문자열 없으면 break
				msg = String.valueOf(map.get("retStr")); //파싱후 남은 문자열 msg에 overwrite
				gubn = StringUtil.getByte2String(msg.getBytes(), 0, 3).trim(); //정보구분 다시 파싱
				info = segMentMap.get(gubn); //해당 비트맵 응답정보 파싱후 처리결과 맵객체 리턴
				cnt++;
				if(cnt > 1000) break; //무한루프방지용
			} while(info != null);
		}
	}

	private void initSegment() {
		segMentMap = new HashMap<String, AbstractSegment>();
	}
	
	/**
	 * 해당 응답정보(세그먼트) 객체 리스트에 셋
	 * @param gubn
	 * @param obj
	 */
	private  void addSegmentList(String gubn , Object obj) {
		int id = StringUtil.isNull(gubn) ? 0 : Integer.valueOf(gubn);
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
		pos+=len; len=1; 	setRefAgreeCode(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=42; 	setFiller(StringUtil.getByte2String(bt,pos,len).trim());

	}
	
	@Override
	public void transferNiceCb(KcbCreditInfoVO info) throws UnsupportedEncodingException,IOException {
		
		SocketUtil 		socket 		= new SocketUtil();
		StringBuffer 	response 	= new StringBuffer(); //응답전문 담을 buffer
		String 			ip 			= info.getIp();
		int 			port 		= info.getPort();
		String 			sendString 	= getRequest(); //요청전문 생성

		info.setMsgRequest(sendString);
		log.info("최초요청전문["+sendString+"]");
		
		//전문 송/수신
		String recvMsg = socket.request(ip, port , sendString, this.HEAD_SIZE);
		
		log.info("최초응답전문["+recvMsg+"]");
		if(StringUtil.isEmpty(recvMsg)) return; //응답전문 없으면 더이상 진행x
		parseHeader(recvMsg); //응답전문 공통부 파싱(오류응답코드인경우 전문이 짤려서 오기때문에 전체파싱 에러나는것을 방지하기 위해)
		
		//응답코드가 정상이 아닌경우
		if(!"0000".equals(getResCode())) {
			log.info("[오류응답코드] ("+getResCode()+")");
			info.setCdCbResponse(getResCode()); //조회정보 객체에 응답코드 셋
			info.setMsgResponse(recvMsg); //조회정보 객체에 응답전문 셋
			return;
		}
		
		response.append(recvMsg); 	//최초응답전문 append
		parseResponse(recvMsg); 	//개별응답부 파싱
		recvMsg = response.toString().replaceAll("\0", "");
		parseHeader(recvMsg);
		
		info.setCdCbResponse(getResCode()); //조회정보 객체에 응답코드 셋
		info.setMsgResponse(recvMsg); 		//조회정보 객체에 응답전문 셋
	}
	
	@Override
	public void setInitData(KcbCreditInfoVO info) {

		//공통부
		this.idTx 			= "SEV";
		this.userNo 		= "S0047000"; 	//회원사코드
		this.tcpTypeCode 	= "0600";		//전문종별코드
		this.workTypeCode 	= "615";		//업무구분코드
		this.tranFlag 		= "B";
		this.comTcpTranTime = DateUtil.getCurrentYMD()+DateUtil.getCurrentHIS();
		this.refAgreeCode 	= "1";//info.getCdAgreeCause();

		//요청정보부(최초요청시)
		this.reqSegmentID 			= "V3";					//Segment ID: Segment 식별자. 상수값 “V3”
		this.req등록사유코드 		= info.getCd_regist();	//1: 발생 신규 고객ID 등록&업데이트, 5: 고객 ID 삭제
		this.req등록번호구분코드 	= "1";					//1: 회원사 내부 고객번호 등록 시,2:KCB ID (KCB ID 발급 시)
		this.req등록번호 			= info.getNoPerson();	
		this.req등록방식구분코드	= "4";					//1: 성명+생년월일+성별+휴대폰번호, 2: 성명+생년월일+성별+주소, 3: 성명+생년월일+성별+이메일, 4: DI, 5: 주민등록번호(외국인등록번호), 6: 성명+생년월일+성별+출생등록지
		this.req성명 				= info.getNmCust();
		this.req생년월일 			= StringUtil.splitBgn(info.getBgn(), "BOD"); //"199002171";
		this.req성별코드 			= StringUtil.splitBgn(info.getBgn(), "G");
		this.req휴대전화식별번호 	= info.getHp().substring(0, 3);
		
		if(info.getHp().length() == 11)	{
			this.req휴대전화국번호 	= info.getHp().substring(3, 7);
			this.req휴대전화개별번호= info.getHp().substring(7);
		} else {
			this.req휴대전화국번호 	= info.getHp().substring(3, 6);
			this.req휴대전화개별번호= info.getHp().substring(6);
		}
		this.req주소 			= "";
		this.req이메일			= "";
		this.req고유식별정보 	= info.getDi() + info.getCp();//"MC0GCCqGSIb3DQIJAyEADniMJwqIRNEVo00SLmKvQyxz3v6DrzRmzpRVpPbMrg8=P18760000000";			//(No.10)에서 ‘01’인 경우에만 입력 : DI값 (64byte) + CP코드(12byte)
		this.req기준일자 		= DateUtil.getCurrentYMD();
		this.req출생등록지코드		= "";
		this.reqFILLER 		= "";
		this.reqByte = 930;	//130 + 800 공통 + 등록정보부
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
		pos+=len; len=1;	setKcbSysInfo(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=42;	setFiller(StringUtil.getByte2String(bt,pos,len).trim());

		pos+=len; len=3  ;	setResSegmentID(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2  ;	setRes등록사유코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1  ;	setRes등록번호구분코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=40 ;	setRes등록번호(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1  ;	setRes등록방식구분코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50 ;	setRes성명(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8  ;	setRes생년월일(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=1  ;	setRes성별코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=3  ;	setRes휴대전화식별번호(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4  ;	setRes휴대전화국번호(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4  ;	setRes휴대전화개별번호(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=200;	setRes주소(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=50 ;	setRes이메일(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=100;	setRes고유식별정보(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=8  ;	setRes기준일자(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=2  ;	setRes출생등록지코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=323;	setResFILLER(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=4  ;	setRes오류응답코드(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=26 ;	setRes오류체크비트(StringUtil.getByte2String(bt,pos,len).trim());
		pos+=len; len=bt.length-pos; noHeader.append(StringUtil.getByte2String(bt,pos,len)) ;

		return noHeader.toString();
	}
	public String getReqSegmentID() {
		return reqSegmentID;
	}
	public void setReqSegmentID(String reqSegmentID) {
		this.reqSegmentID = reqSegmentID;
	}
	public String getReq등록사유코드() {
		return req등록사유코드;
	}
	public void setReq등록사유코드(String req등록사유코드) {
		this.req등록사유코드 = req등록사유코드;
	}

	public String getReq등록번호구분코드() {
		return req등록번호구분코드;
	}
	public void setReq등록번호구분코드(String req등록번호구분코드) {
		this.req등록번호구분코드 = req등록번호구분코드;
	}
	public String getReq등록번호() {
		return req등록번호;
	}
	public void setReq등록번호(String req등록번호) {
		this.req등록번호 = req등록번호;
	}
	public String getReq등록방식구분코드() {
		return req등록방식구분코드;
	}
	public void setReq등록방식구분코드(String req등록방식구분코드) {
		this.req등록방식구분코드 = req등록방식구분코드;
	}
	public String getReq성명() {
		return req성명;
	}
	public void setReq성명(String req성명) {
		this.req성명 = req성명;
	}
	public String getReq생년월일() {
		return req생년월일;
	}
	public void setReq생년월일(String req생년월일) {
		this.req생년월일 = req생년월일;
	}
	public String getReq성별코드() {
		return req성별코드;
	}
	public void setReq성별코드(String req성별코드) {
		this.req성별코드 = req성별코드;
	}
	public String getReq휴대전화식별번호() {
		return req휴대전화식별번호;
	}
	public void setReq휴대전화식별번호(String req휴대전화식별번호) {
		this.req휴대전화식별번호 = req휴대전화식별번호;
	}
	public String getReq휴대전화국번호() {
		return req휴대전화국번호;
	}
	public void setReq휴대전화국번호(String req휴대전화국번호) {
		this.req휴대전화국번호 = req휴대전화국번호;
	}
	public String getReq휴대전화개별번호() {
		return req휴대전화개별번호;
	}
	public void setReq휴대전화개별번호(String req휴대전화개별번호) {
		this.req휴대전화개별번호 = req휴대전화개별번호;
	}
	public String getReq주소() {
		return req주소;
	}
	public void setReq주소(String req주소) {
		this.req주소 = req주소;
	}
	public String getReq이메일() {
		return req이메일;
	}
	public void setReq이메일(String req이메일) {
		this.req이메일 = req이메일;
	}
	public String getReq고유식별정보() {
		return req고유식별정보;
	}
	public void setReq고유식별정보(String req고유식별정보) {
		this.req고유식별정보 = req고유식별정보;
	}
	public String getReq기준일자() {
		return req기준일자;
	}
	public void setReq기준일자(String req기준일자) {
		this.req기준일자 = req기준일자;
	}
	public String getReq출생등록지코드() {
		return req출생등록지코드;
	}
	public void setReq출생등록지코드(String req출생등록지코드) {
		this.req출생등록지코드 = req출생등록지코드;
	}
	public String getReqFILLER() {
		return reqFILLER;
	}
	public void setReqFILLER(String reqFILLER) {
		this.reqFILLER = reqFILLER;
	}
	public String getResSegmentID() {
		return resSegmentID;
	}
	public void setResSegmentID(String resSegmentID) {
		this.resSegmentID = resSegmentID;
	}
	public String getRes등록사유코드() {
		return res등록사유코드;
	}
	public void setRes등록사유코드(String res등록사유코드) {
		this.res등록사유코드 = res등록사유코드;
	}
	public String getRes등록번호구분코드() {
		return res등록번호구분코드;
	}
	public void setRes등록번호구분코드(String res등록번호구분코드) {
		this.res등록번호구분코드 = res등록번호구분코드;
	}
	public String getRes등록번호() {
		return res등록번호;
	}
	public void setRes등록번호(String res등록번호) {
		this.res등록번호 = res등록번호;
	}
	public String getRes등록방식구분코드() {
		return res등록방식구분코드;
	}
	public void setRes등록방식구분코드(String res등록방식구분코드) {
		this.res등록방식구분코드 = res등록방식구분코드;
	}
	public String getRes성명() {
		return res성명;
	}
	public void setRes성명(String res성명) {
		this.res성명 = res성명;
	}
	public String getRes생년월일() {
		return res생년월일;
	}
	public void setRes생년월일(String res생년월일) {
		this.res생년월일 = res생년월일;
	}
	public String getRes성별코드() {
		return res성별코드;
	}
	public void setRes성별코드(String res성별코드) {
		this.res성별코드 = res성별코드;
	}
	public String getRes휴대전화식별번호() {
		return res휴대전화식별번호;
	}
	public void setRes휴대전화식별번호(String res휴대전화식별번호) {
		this.res휴대전화식별번호 = res휴대전화식별번호;
	}
	public String getRes휴대전화국번호() {
		return res휴대전화국번호;
	}
	public void setRes휴대전화국번호(String res휴대전화국번호) {
		this.res휴대전화국번호 = res휴대전화국번호;
	}
	public String getRes휴대전화개별번호() {
		return res휴대전화개별번호;
	}
	public void setRes휴대전화개별번호(String res휴대전화개별번호) {
		this.res휴대전화개별번호 = res휴대전화개별번호;
	}
	public String getRes주소() {
		return res주소;
	}
	public void setRes주소(String res주소) {
		this.res주소 = res주소;
	}
	public String getRes이메일() {
		return res이메일;
	}
	public void setRes이메일(String res이메일) {
		this.res이메일 = res이메일;
	}
	public String getRes고유식별정보() {
		return res고유식별정보;
	}
	public void setRes고유식별정보(String res고유식별정보) {
		this.res고유식별정보 = res고유식별정보;
	}
	public String getRes기준일자() {
		return res기준일자;
	}
	public void setRes기준일자(String res기준일자) {
		this.res기준일자 = res기준일자;
	}
	public String getRes출생등록지코드() {
		return res출생등록지코드;
	}
	public void setRes출생등록지코드(String res출생등록지코드) {
		this.res출생등록지코드 = res출생등록지코드;
	}
	public String getResFILLER() {
		return resFILLER;
	}
	public void setResFILLER(String resFILLER) {
		this.resFILLER = resFILLER;
	}
	public String getRes오류응답코드() {
		return res오류응답코드;
	}
	public void setRes오류응답코드(String res오류응답코드) {
		this.res오류응답코드 = res오류응답코드;
	}
	public String getRes오류체크비트() {
		return res오류체크비트;
	}
	public void setRes오류체크비트(String res오류체크비트) {
		this.res오류체크비트 = res오류체크비트;
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
