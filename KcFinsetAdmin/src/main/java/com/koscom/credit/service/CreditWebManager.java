package com.koscom.credit.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.credit.model.NpacVO;
import com.koscom.domain.CreditInfo;
import com.koscom.util.ReturnClass;

public interface CreditWebManager {
	ReturnClass createNpacHist(NpacVO npacVO);
	NpacVO getNpacHistBySeq(String seq);

	/**
	 * Method Desc : 개인번호 & 전문명으로 조회이력 가져오기
	 * @param CreditInfo
	 * @return
	 */
	List<CreditInfo> getCreditInfoByNmIf(CreditInfo info);

	/**
	 * Method Desc : 신용조회 정보 단건조회
	 * 2016.2.10 sclee
	 * @param seq 신용정보 조회 일련번호
	 * @return
	 */
	CreditInfo getCreditInfo(int seq);

	/**
	 * Method Desc : 신용조회 정보 저장
	 * 2016.2.10 sclee
	 * @param info
	 */
	void saveCredit(CreditInfo info);
	HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap);

	/**
	 * 신용관리 메인_기본정보
	 * @param no_person
	 * @return
	 */
	CreditInfo getCreditMainBaseInfo(String no_person);

	/**
	 * 신용관리 메인_건수정보
	 * @param no_person
	 * @return
	 */
	CreditInfo getCreditMainCntInfo(String no_person);

	/**
	 * 신용관리_신용등급상세
	 * @param no_person, creditInfo
	 * @return
	 */
	List<CreditInfo> getCreditDetailGradeChartList(String no_person);
	List<CreditInfo> getCreditDetailGradeInquiryList(String no_person);
	List<CreditInfo> getCreditDetailGradeChangeList(CreditInfo creditInfo);

	/**
	 * 신용관리_카드현황,연체현황,연대보증현황
	 * @param no_person
	 * @return
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

}
