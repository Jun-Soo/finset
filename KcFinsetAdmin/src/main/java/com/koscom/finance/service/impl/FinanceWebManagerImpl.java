package com.koscom.finance.service.impl;

import com.koscom.apply.model.ApplyVO;
import com.koscom.goods.model.GoodsVO;
import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.apply.model.ApplyForm;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finance.service.FinanceManager;
import com.koscom.finance.service.FinanceWebManager;
import com.koscom.util.ReturnClass;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

@Service("financeWebManager")
public class FinanceWebManagerImpl implements FinanceWebManager {
	@Autowired
	FinanceManager financeManager;
	@Override
	public ReturnClass reqFinanceInfo(TxFcTransmitVO txFcReceiveVO) throws IOException, FinsetException, FinsetMessageException {
		return financeManager.reqFinanceInfo(txFcReceiveVO);
	}
	@Override
	public void setFinsetForReady(TxFcTransmitVO txFcReceiveVO){
		financeManager.setFinsetForReady(txFcReceiveVO);
	}
	@Override
	public void setFinsetForFail(TxFcReceiveVO txFcReceiveVO, TxFcTransmitVO fcMsgSendVO){
		financeManager.setFinsetForFail(txFcReceiveVO, fcMsgSendVO);
	}
	@Override
	public TxFcReceiveVO reqFcPersonInfo(TxFcTransmitVO txFcReceiveVO,ApplyVO applyVO)  throws IOException, ParseException, FinsetException, FinsetMessageException {
		return financeManager.reqFcPersonInfo(txFcReceiveVO, applyVO);
	}
	@Override
	public TxFcReceiveVO reqFcLoanSts(TxFcTransmitVO txFcReceiveVO)  throws IOException, ParseException, FinsetException, FinsetMessageException{
		return financeManager.reqFcLoanSts(txFcReceiveVO);
	}
	@Override
	public ReturnClass getLoanProgSts(ApplyForm applyForm) throws IOException, ParseException, FinsetException, FinsetMessageException {
		return financeManager.getLoanProgSts(applyForm);
	}
}
