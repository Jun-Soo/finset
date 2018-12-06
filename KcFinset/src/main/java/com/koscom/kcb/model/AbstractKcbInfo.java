package com.koscom.kcb.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.koscom.util.FinsetException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.koscom.credit.service.CreditManager;



/**
 * KCB 신용조회 전문 공통 추상클래스
 * @author sclee
 *
 */
public abstract class AbstractKcbInfo implements Serializable {
	private static final long serialVersionUID = 2229722143065955153L;

	public int HEAD_SIZE = 4; //헤더 Tcplength 사이즈

	protected String tcplength;			// Binary	4	송수신 Tcplength(TCP/IP)
	protected String idTx;				// AN		9	Transaction Code
	protected String userNo;			// AN		8	회원사코드
	protected String tcpTypeCode;		// AN		4	전문종별코드
	protected String workTypeCode;		// AN		3	업무구분코드
	protected String tranFlag;			// A		1	송수신 Flag
	protected String resCode;			// AN		4	응답코드
	protected String kcbTcpMngNo;		// N		7	KCB 전문 관리번호
	protected String kcbTcpTranTime;	// N		14	KCB 전문 전송시간
	protected String comTcpMngNo;		// N		7	회원사 전문 관리번호
	protected String comTcpTranTime;	// N		14	회원사 전문전송 시간
	protected String kcbSysInfo;		// AN		16	KCB System 정보
	protected String refAgreeCode;		// AN		1	조회동의여부
	protected String filler;			// AN		42	FILLER	(300전문 : 42, 600전문 : 43)

	protected String kcbURI;			// AN		16	kcbURI

	public String getTcplength() {
		return tcplength;
	}

	public void setTcplength(String tcplength) {
		this.tcplength = tcplength;
	}

	public String getIdTx() {
		return idTx;
	}

	public void setIdTx(String idTx) {
		this.idTx = idTx;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getTcpTypeCode() {
		return tcpTypeCode;
	}

	public void setTcpTypeCode(String tcpTypeCode) {
		this.tcpTypeCode = tcpTypeCode;
	}

	public String getWorkTypeCode() {
		return workTypeCode;
	}

	public void setWorkTypeCode(String workTypeCode) {
		this.workTypeCode = workTypeCode;
	}

	public String getTranFlag() {
		return tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getKcbTcpMngNo() {
		return kcbTcpMngNo;
	}

	public void setKcbTcpMngNo(String kcbTcpMngNo) {
		this.kcbTcpMngNo = kcbTcpMngNo;
	}

	public String getKcbTcpTranTime() {
		return kcbTcpTranTime;
	}

	public void setKcbTcpTranTime(String kcbTcpTranTime) {
		this.kcbTcpTranTime = kcbTcpTranTime;
	}

	public String getComTcpMngNo() {
		return comTcpMngNo;
	}

	public void setComTcpMngNo(String comTcpMngNo) {
		this.comTcpMngNo = comTcpMngNo;
	}

	public String getComTcpTranTime() {
		return comTcpTranTime;
	}

	public void setComTcpTranTime(String comTcpTranTime) {
		this.comTcpTranTime = comTcpTranTime;
	}

	public String getKcbSysInfo() {
		return kcbSysInfo;
	}

	public void setKcbSysInfo(String kcbSysInfo) {
		this.kcbSysInfo = kcbSysInfo;
	}

	public String getRefAgreeCode() {
		return refAgreeCode;
	}

	public void setRefAgreeCode(String refAgreeCode) {
		this.refAgreeCode = refAgreeCode;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getKcbURI() {
		return kcbURI;
	}

	public void setKcbURI(String kcbURI) {
		this.kcbURI = kcbURI;
	}



	// 모델외 변수
	protected String yn_continue;

	// 요청
	public abstract String getRequest() throws UnsupportedEncodingException;

	// 응답전문 파싱
	public abstract void parseResponse(String recvMsg) throws UnsupportedEncodingException;

	// 응답전문 파싱
	public abstract void parseHeader(String recvMsg);

	// 전문송수신
	public abstract void transferNiceCb(KcbCreditInfoVO info) throws UnsupportedEncodingException,IOException;
	public abstract void saveDetailInfo(KcbCreditInfoVO info);

	// 초기 기본정보 셋팅
	public abstract void setInitData(KcbCreditInfoVO info);

	// 재요청 정보 셋팅
	public abstract void setInitDataAgainRequest(KcbCreditInfoVO info);

	// 응답전문 헤더제외한 세그먼트 정보만 반환
	public abstract String getStringByNoHeader(String recvMsg) throws UnsupportedEncodingException;

	// 모델 출력 스타일 설정
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	// KCB Crawling (300 전문 저장 로직 사용)
	public abstract void crawlingKcb(KcbCreditInfoVO info);
}
