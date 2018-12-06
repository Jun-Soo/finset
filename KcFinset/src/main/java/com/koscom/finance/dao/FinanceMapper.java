package com.koscom.finance.dao;

import java.util.List;

import com.koscom.finance.model.FcWorkPositionForm;
import com.koscom.finance.model.FcWorkPositionVO;
import com.koscom.finance.model.FinanceMsgVO;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.model.TxFcTransmitVO;

public interface FinanceMapper {
	/**
	 * 게시판 관리 리스트 조회
	 * @param boardForm
	 * @return
	 */
	List<FinanceMsgVO> listFinanceRecv(TxFcReceiveVO txFcReceiveVO);
	/**
	 * 게시판 관리 리스트 조회
	 * @param boardForm
	 * @return
	 */
	List<FinanceMsgVO> listFinanceSend(TxFcTransmitVO financeMsgSendVO);
	int listFinanceCount(TxFcTransmitVO financeMsgSendVO);
	void insertTxFcTransmit(TxFcTransmitVO financeMsgSendVO);
	void insertTxFcGoods(TxFcTransmitVO financeMsgSendVO);
	void insertTxFcReceive(TxFcReceiveVO txFcReceiveVO);
	String getNoApplyFromFcReq(TxFcReceiveVO txFcReceiveVO);  	
	
	/**
	 * 전문일련번호 생성 조회
	 * @param 
	 * @return String
	 */
	int getSeqEdocNo();
	
	/**
	 * 직업직위코드 조회
	 * @param fcWorkPositionForm
	 * @return FcWorkPositionVO
	 */
	FcWorkPositionVO getFcWorkPosition(FcWorkPositionForm fcWorkPositionForm);
}
