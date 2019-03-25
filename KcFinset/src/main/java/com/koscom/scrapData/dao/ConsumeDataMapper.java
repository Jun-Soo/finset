/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * 소비 지출 - ConsumeDataMapper.xml을 호출하기 위한 인터페이스
 */
package com.koscom.scrapData.dao;

import java.util.List;
import java.util.Map;

import com.koscom.scrapData.model.BusinessTypeInfoVO;
import com.koscom.scrapData.model.ConsumeDataForm;
import com.koscom.scrapData.model.ConsumeDataVO;


public interface ConsumeDataMapper {
	
	/**
	 * 소비지출 테이블 최종 입력시간 조회(현금영수증)
	 * @return
	 */
	String getDtFromCashReceipt(String no_person);
	
	/**
	 * 소비지출 테이블 최종 입력시간 조회(현금영수증)
	 * @return
	 */
	String getDtFromTransaction(String no_person);
	
	/**
	 * 소비지출 데이터를 확보하기 위해  카드 승인 스크래핑 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<Map<String, String>> listScrCardApprovalInfo(ConsumeDataForm consumeForm);
	
	/**
	 * 소비지출 데이터를 확보하기 위해 현금영수증 스크래핑 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<Map<String, String>> listScrRespCashReceipt(ConsumeDataForm consumeForm);
	
	/**
	 * 자동 수입, 지출 등록된 계좌 입출금 데이터 조회(자동등록)
	 * @param consumeForm
	 * @return
	 */
	List<Map<String, String>> listScrTransactionDetailAuto(ConsumeDataForm consumeForm);

	/**
	 * 자동 수입, 지출 등록된 계좌 입출금 데이터 조회(급여)
	 * @param consumeForm
	 * @return
	 */
	List<Map<String, String>> listScrTransactionDetailIncome(ConsumeDataForm consumeForm);
	
	/**
	 * 소비지출 테이블에 추가
	 * @param consumeVO
	 * @return
	 */
	int createConsumeInfo(ConsumeDataVO consumeVO);

	/**
	 * BUSINESS_TYPE_INFO에 해당하는 업종 명이 없을 경우 신규로 등록
	 * @param businessTypeInfoVO
	 * @return
	 */
	int createBusinessTypeInfo(BusinessTypeInfoVO businessTypeInfoVO);
}
