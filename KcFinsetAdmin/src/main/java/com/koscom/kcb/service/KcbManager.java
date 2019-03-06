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
	/**
	 * 크롤링 정보 parsing
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	ReturnClass parseCrawling(KcbCreditInfoVO info) throws FinsetException;

	/**
	 * 전문 처리 및 크롤링 실패시 데이터 삭제
	 * @param String noPerson
	 * @return
	 */
	ReturnClass deleteKcbCb(String noPerson);
	
	/**
	 * KCB 비금융정보 요청내역 insert
	 * @param String noPerson
	 * @return
	 */
	ReturnClass createKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO);
	
	/**
	 * KCB 비금융정보 요청내역 조회
	 * @param KcbReqNonfiInfoVO
	 * @return KcbReqNonfiInfoVO
	 */
	List<KcbReqNonfiInfoVO> getKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO);
	
	/**
	 * KCB 비금융정보 요청내역 update
	 * @param KcbReqNonfiInfoVO
	 * @return
	 */
	int updateKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO);
}