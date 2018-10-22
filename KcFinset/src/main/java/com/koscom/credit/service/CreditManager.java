package com.koscom.credit.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.domain.CreditInfo;

public interface CreditManager {

	/**
	 * 전문SEQ 조회
	 * @return String SEQ
	 */
	String getCreditInfoNextSeq();

	/**
	 * 신용관리 메인_기본정보
	 * @param no_person
	 * @return CreditInfo
	 */
	CreditInfo getCreditMainBaseInfo(String no_person);

	/**
	 * 신용관리 메인_연체금액
	 * @param no_person
	 * @return CreditInfo
	 */
	CreditInfo getCreditOverdueSumAmt(String no_person);

	/**
	 * 신용관리 메인_건수정보
	 * @param no_person
	 * @return CreditInfo
	 */
	CreditInfo getCreditMainCntInfo(String no_person);

	/**
	 * 신용관리 메인_변동내역
	 * @param no_person
	 * @return CreditInfo
	 */
	CreditInfo getCreditMainGradeChangeInfo(String no_person);

	/**
	 * kcb전문 송수신 이력조회
	 * @param	HashMap<String, String> searchMap
	 * @return	HashMap<String, String>
	 */
	HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap);

	/**
	 * 신용관리_신용등급상세
	 * @param no_person, creditInfo
	 * @return List<CreditInfo>
	 */
	List<CreditInfo> getCreditDetailGradeChartList(String no_person);
	List<CreditInfo> getCreditDetailGradeInquiryList(String no_person);
	List<CreditInfo> getCreditDetailGradeChangeList(CreditInfo creditInfo);

	/**
	 * 신용관리_카드현황,연체현황,연대보증현황
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
	CreditInfo getCreditDetailDEBTSum(String no_person);
	List<CreditInfo> getCreditDetailDEBTList(String no_person);

	/**
	 * 개인번호 & 전문명으로 조회이력 가져오기
	 * @param info
	 * @return
	 */
	List<CreditInfo> getCreditInfoByNmIf(CreditInfo info);
}