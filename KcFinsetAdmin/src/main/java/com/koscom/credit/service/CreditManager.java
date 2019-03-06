package com.koscom.credit.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.credit.model.NpacVO;
import com.koscom.debt.model.DebtPersonalInfoVO;
import com.koscom.debt.model.KcbLoanInfoVO;
import com.koscom.domain.CreditInfo;
import com.koscom.kcb.model.CrawlingVO;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.Kcb_300;
import com.koscom.kcb.model.seg.Kcb_Segment041;
import com.koscom.person.model.PersonEtmIncomeInfo;
import com.koscom.util.ReturnClass;

public interface CreditManager {

	/**
	 * Method Desc : 전문SEQ 조회
	 * 2017.12.28
	 * @param
	 * @return	String SEQ
	 */
	String getCreditInfoNextSeq();

	/**
	 * Method Desc : 신용조회 정보 리스트
	 * 2017.12.28
	 * @param info
	 * @return
	 */
	List<CreditInfo> listCreditInfo(CreditInfo info);

	/**
	 * Method Desc :
	 * 2017.12.28
	 * @param	NpacVO npacVO
	 * @return	ReturnClass
	 */
	ReturnClass createNpacHist(NpacVO npacVO);

	/**
	 * Method Desc :
	 * 2017.12.28
	 * @param	String seq
	 * @return	NpacVO
	 */
	NpacVO getNpacHistBySeq(String seq);

	/**
	 * Method Desc : 개인번호 & 전문명으로 조회이력 가져오기
	 * @param info
	 * @return
	 */
	List<CreditInfo> getCreditInfoByNmIf(CreditInfo info);

	/**
	 * Method Desc : 신용조회 정보 단건조회
	 * 2017.12.28
	 * @param seq 신용정보 조회 일련번호
	 * @return
	 */
	CreditInfo getCreditInfo(int seq);

	/**
	 * Method Desc :
	 * 2017.12.28
	 * @param	String seq
	 * @return	NpacVO
	 */
	HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap);

	/**
	 * Method Desc : 소득정보 입력
	 * 2017.12.28
	 * @param info
	 */
	void savePersonIncome(PersonEtmIncomeInfo personEtmIncomeInfo);

	/**
	 * Method Desc : 신용조회 정보 저장
	 * 2017.12.28
	 * @param info
	 */
	void saveCredit(CreditInfo info);

	/**
	 * Method Desc : KCB 회원별 대출정보 저장
	 * 2017.12.28
	 * @param	List<KcbLoanInfoVO> listKcbLoanInfoVO
	 * @return	void
	 */
	void saveKcbLoanInfo(List<KcbLoanInfoVO> listKcbLoanInfoVO);

	/**
	 * Method Desc : KCB 카드개설 정보 및 거래상태정보 저장
	 * 2017.12.28
	 * @param	Kcb_Segment041 kcb_Segment041
	 * @return	void
	 */
	void saveKcbCardInfo(Kcb_Segment041 kcb_Segment041);

	/**
	 * Method Desc : 전문 수신일
	 * 2017.12.28
	 * @param	noPerson
	 * @return	void
	 */
	String selectResDt(String noPerson);



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