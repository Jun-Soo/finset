package com.koscom.kcb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import com.koscom.domain.CreditInfo;
import com.koscom.kcb.model.AbstractKcbInfo;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

public interface KcbManager {
	/**
	 * KCB 신용조회 정보 리스트
	 * @param info
	 * @return
	 */
	List<CreditInfo> listKcbCredit(CreditInfo info);
	/**
	 * KCB 신용조회 상세정보
	 * @param infoVO
	 * @return
	 */
	AbstractKcbInfo viewKcbCreditInfo(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException,FinsetException;
	/**
	 * KCB 신용조회 실행
	 * @param infoVO
	 * @return
	 */
	ReturnClass procKcbCb(KcbCreditInfoVO infoVO)  throws UnsupportedEncodingException, FinsetException,IOException  ;
	/**
	 * CB정보가져오기
	 * @param infoVO
	 * @return
	 */
	ReturnClass getKcbCbInfo(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException,FinsetException,IOException;
	
}