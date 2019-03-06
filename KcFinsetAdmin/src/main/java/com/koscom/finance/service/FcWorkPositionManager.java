package com.koscom.finance.service;

import com.koscom.finance.model.FcWorkPositionForm;
import com.koscom.finance.model.FcWorkPositionVO;
import com.koscom.finance.model.TxFcTransmitVO;

public interface FcWorkPositionManager {
	
	/**
	 * 직업직위코드 조회
	 * @param fcWorkPositionForm
	 * @return FcWorkPositionVO
	 */
	FcWorkPositionVO getFcWorkPosition(FcWorkPositionForm fcWorkPositionForm);
	
	/**
	 * 직업직위코드 조회
	 * @param fcWorkPositionForm
	 * @return FcWorkPositionVO
	 */
	FcWorkPositionVO getPepperWorkPosition(TxFcTransmitVO txFcTransmitVO);
}
