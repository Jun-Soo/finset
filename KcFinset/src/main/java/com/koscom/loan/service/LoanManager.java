package com.koscom.loan.service;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.util.ReturnClass;

public interface LoanManager {
	
	/**
	 * 즐겨찾기 insert
	 * @param CooconGoodsFavoriteInfo
	 * @return ReturnClass
	 */
	ReturnClass insertLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);

	/**
	 * 즐겨찾기 delete
	 * @param CooconGoodsFavoriteInfo
	 * @return ReturnClass
	 */
	ReturnClass deleteLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);
	
	/**
	 * tx_fc_transmit 정보 select
	 * @param String
	 * @return TxFcTransmitVO
	 */
	TxFcTransmitVO getTxFcTransmitInfoForMsg(String no_bunch);

}
