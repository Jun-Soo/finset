package com.koscom.credit.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.domain.CreditInfo;

public interface CreditMapper {

	/**
	 * Method Desc : 전문SEQ 조회
	 * 2017.12.28
	 * @return String SEQ
	 */
	String getCreditInfoNextSeq();

	/**
	 * KCB 전문 수신일 조회
	 * @param noPerson
	 * @return String
	 */
	String selectResDt(String noPerson);

	/**
	 * 전문 송수신 결과 저장
	 * @param CreditInfo info
	 */
	void saveCredit(CreditInfo info);

	/**
	 * 신용관리 메인_기본정보
	 * @param no_person
	 * @return CreditInfo
	 */
	CreditInfo getCreditMainBaseInfo(String no_person);

	/**
	 * 신용관리 메인_건수정보
	 * @param no_person
	 * @return CreditInfo
	 */
	CreditInfo getCreditMainCntInfo(String no_person);

	/**
	 * 신용관리_신용등급상세
	 * @param no_person, creditInfo
	 * @return List<CreditInfo>
	 */
	List<CreditInfo> getCreditDetailGradeChartList(String no_person);
	List<CreditInfo> getCreditDetailGradeInquiryList(String no_person);
	List<CreditInfo> getCreditDetailGradeChangeList(CreditInfo creditInfo);

	/**
	 * 신용관리_카드현황, 연체현황, 연대보증현황
	 * @param no_person
	 * @return HashMap<String, String>
	 */
	HashMap<String, String> getCreditDetailJsonInfo(String no_person);

	/**
	 * 신용관리_대출현황
	 * @param no_person
	 * @return
	 */
	CreditInfo getCreditDetailDEBTCreditSum(String no_person);
	List<CreditInfo> getCreditDetailDEBTCreditList(String no_person);
	CreditInfo getCreditDetailDEBTLoanSum(String no_person);
	List<CreditInfo> getCreditDetailDEBTLoanList(String no_person);

	/**
	 * Method Desc : kcb전문 송수신 이력조회
	 * 2018.08.31
	 * @param	HashMap<String, String> searchMap
	 * @return	HashMap<String, String>
	 */
	HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap);

	/**
	 * 전문 처리 및 크롤링 실패시 데이터 삭제
	 * @param String noPerson
	 */
	void deleteKcbCb(String noPerson);
}
