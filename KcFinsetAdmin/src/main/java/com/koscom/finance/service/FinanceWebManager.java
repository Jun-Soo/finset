package com.koscom.finance.service;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.goods.model.GoodsVO;
import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;
import com.koscom.util.ReturnClass;

import java.io.IOException;
import java.text.ParseException;

public interface FinanceWebManager {
	ReturnClass reqFinanceInfo(TxFcTransmitVO txFcTransmitVO) throws IOException, FinsetException, FinsetMessageException;
	void setFinsetForReady(TxFcTransmitVO txFcTransmitVO);
	void setFinsetForFail(TxFcReceiveVO txFcReceiveVO, TxFcTransmitVO fcMsgSendVO) ;//1차 전문
	TxFcReceiveVO reqFcPersonInfo(TxFcTransmitVO txFcTransmitVO,ApplyVO applyVO) throws IOException, ParseException, FinsetException, FinsetMessageException;//이지론 전문 2차
	TxFcReceiveVO reqFcLoanSts(TxFcTransmitVO txFcTransmitVO) throws IOException, ParseException, FinsetException, FinsetMessageException;//이지론 전문 3차
	ReturnClass getLoanProgSts(ApplyForm applyForm) throws IOException, ParseException, FinsetException, FinsetMessageException; //대출진행상황 확인
}
