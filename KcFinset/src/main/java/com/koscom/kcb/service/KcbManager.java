package com.koscom.kcb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

public interface KcbManager {
	
	/**
	 * Kcb 크롤링 호출
	 * @param request
	 * @return String
	 * @throws IOException 
	 * @throws FinsetException 
	 */
	String getKcbCrawling(PersonVO personVO) throws FinsetException, IOException;
	
	/**
	 * KCB 신용조회 실행
	 * @param infoVO
	 * @return ReturnClass
	 * @throws Exception 
	 */
	ReturnClass procKcbCb(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException, FinsetException, IOException, Exception;
	
	/**
	 * Method Desc : KCB 0600402 메뉴구분 210 대출보고서 크롤링
	 * 2017.11.24
	 * @param info 
	 * @param kcbURL
	 * @return ReturnClass
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public ReturnClass urlCrawling(KcbCreditInfoVO info) throws UnsupportedEncodingException, FinsetException, IOException;
	
	/**
	 * 크롤링 정보 parsing
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	//ReturnClass parseCrawling(KcbCreditInfoVO info) throws FinsetException;

	/**
	 * 전문 처리 및 크롤링 실패시 데이터 삭제
	 * @param String noPerson
	 * @return
	 */
	ReturnClass deleteKcbCb(String noPerson);
	
}