package com.koscom.finance.service;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;
import com.koscom.util.ReturnClass;

import java.io.IOException;
import java.text.ParseException;

public interface FinanceManager {
	ReturnClass reqFinanceInfo(TxFcTransmitVO txFcTransmitVO) throws FinsetException, IOException, FinsetMessageException;//1차 전문
	void setFinsetForReady(TxFcTransmitVO txFcTransmitVO) ;//1차 전문
	void setFinsetForFail(TxFcReceiveVO txFcReceiveVO, TxFcTransmitVO fcMsgSendVO);
	TxFcReceiveVO reqFcPersonInfo(TxFcTransmitVO txFcTransmitVO, ApplyVO applyVO) throws  ParseException, IOException, FinsetException, FinsetMessageException;//2차전문
	TxFcReceiveVO reqFcLoanSts(TxFcTransmitVO txFcTransmitVO) throws ParseException,FinsetException,FinsetMessageException,IOException;//3차전문
	ReturnClass getLoanProgSts(ApplyForm applyForm) throws IOException, ParseException, FinsetException, FinsetMessageException; //대출진행상황 확인
	boolean reqStatusNotiProcess(TxFcReceiveVO txFcRecvVO)  throws IOException, ParseException,FinsetException;	//3차전문:실시간 조회 처리
	void parseRecvMsg(String no_bunch, byte[] bArrRecvData) throws FinsetException, FinsetMessageException;
}
