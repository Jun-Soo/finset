package com.koscom.credit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koscom.credit.model.CreditDtlVO;
import com.koscom.domain.CreditInfo;
import com.koscom.kcb.model.CrawlingVO;
import com.koscom.kcb.model.KcbAddrInfo;
import com.koscom.kcb.model.KcbCardDtlList;
import com.koscom.kcb.model.KcbCardInfo;
import com.koscom.kcb.model.KcbContactInfo;
import com.koscom.kcb.model.KcbGuaranteeInfo;
import com.koscom.kcb.model.KcbJobInfo;
import com.koscom.kcb.model.KcbOverdueDefaultInfo;
import com.koscom.kcb.model.KcbOverdueInfo;
import com.koscom.kcb.model.KcbOverduePublicInfo;
import com.koscom.kcb.model.KcbOverdueSteadpayInfo;
import com.koscom.person.model.PersonEtmIncomeInfo;

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
	
	/**
	 * 개인 별 신용등급 저장(KcbManagerImpl)
	 * @param crawlingVO
	 */
	void saveKcbCreditList(CrawlingVO crawlingVO);
	
	/**
	 * 개인 별 신용등급 히스토리 저장(KcbManagerImpl) 
	 * @param crawlingVO
	 */
	void saveKcbCreditHistList(CrawlingVO crawlingVO);
	
	/**
	 * 최근 신용 병동일 조회 (KcbManagerImpl)
	 * @param no_person
	 * @return
	 */
	String selectMaxCreditChange(String no_person);
	
	/**
	 * 개인별 신용 변동정보 저장 (KcbManagerImpl)
	 * @param creditVO
	 */
	void saveKcbCreditChangeInfo(CreditDtlVO creditVO);
	
	/**
	 * 최근 신용정보조회일 조회 (KcbManagerImpl)
	 * @param no_person
	 * @return
	 */
	String selectMaxCreditInquiry(String no_person);
	
	/**
	 * 신용 조회 내역 저장 (KcbManagerImpl)
	 * @param creditDtlVO
	 */
	void saveKcbCreditInquiryInfo(CreditDtlVO creditDtlVO);
	
	/**
	 * 카드별 이력정보 해지일자 업데이트 (KcbManagerImpl)
	 * @param kcbCardInfo
	 */
	void updateKcbCardInfoToday(KcbCardInfo kcbCardInfo);
	
	/**
	 * 카드별 이력정보 저장 (KcbManagerImpl)
	 * @param kcbCardInfo
	 */
	void saveKcbCardInfo(KcbCardInfo kcbCardInfo);
	
	/**
	 * 카드별 이력 상세정보 저장 (KcbManagerImpl)
	 * @param kcbCardDtlList
	 */
	void saveKcbCardDtlList(KcbCardDtlList kcbCardDtlList);
	
	/**
	 * 연체 정보 저장 (KcbManagerImpl)
	 * @param kcbOverdueInfo
	 */
	void saveKcbOverdueInfo(KcbOverdueInfo kcbOverdueInfo);
	
	/**
	 * 대지급 정보 저장 (KcbManageImpl)
	 * @param kcbOverdueSteadpayInfo
	 */
	void saveKcbOverdueSteadpayInfo(KcbOverdueSteadpayInfo kcbOverdueSteadpayInfo);
	
	/**
	 * 채무 불이행 정보 저장 (KcbManagerImpl)
	 * @param kcbOverdueDefaultInfo
	 */
	void saveKcbOverdueDefaultInfo(KcbOverdueDefaultInfo kcbOverdueDefaultInfo);
	
	/**
	 * 공공 정보 저장 (KcbManagerImpl)
	 * @param kcbOverduePublicInfo
	 */
	void saveKcbOverduePublicInfo(KcbOverduePublicInfo kcbOverduePublicInfo);
	
	/**
	 * 크롤링 실패 로그 저장 (KcbManagerImpl)
	 * @param logMap
	 */
	void insertCrawlingLog(Map<String, Object> logMap);
	
	/**
	 * 연대 보증 정보 저장 (KcbManagerImpl)
	 * @param kcbGuaranteeInfo
	 */
	void saveKcbGuaranteeInfo(KcbGuaranteeInfo kcbGuaranteeInfo);
	
	/**
	 * KCB 크롤링 정보 조회 (KcbManagerImpl)
	 * @param no_person
	 */
	int selectKcbCreditInfo(String no_person);
	
	/**
	 * KCB 크롤링 정보 수정 (KcbManagerImpl)
	 * @param vo
	 */
	void updateKcbCreditInfo(CrawlingVO vo);
	
	/**
	 * KCB 크롤링 정보 저장 (KcbManagerImpl)
	 * @param vo
	 */
	void insertKcbCreditInfo(CrawlingVO vo);
	
	/**
	 * 주소 정보 저장 (KcbManagerImpl)
	 * @param kcbAddrInfo
	 * @return
	 */
	void saveKcbAddrInfo(KcbAddrInfo kcbAddrInfo);
	
	/**
	 * 직장 정보 저장 (KcbManagerImpl)
	 * @param kcbJobInfo
	 */
	void saveKcbJobInfo(KcbJobInfo kcbJobInfo);
	
	/**
	 * 소득 정보 저장 (KcbMangerImpl)
	 * @param personEtmIncomeInfo
	 */
	void insertPersonEtmIncomeInfo(PersonEtmIncomeInfo personEtmIncomeInfo);
	
	/**
	 * 연락처 정보 저장 (KcbManagerImpl)
	 * @param kcbContactInfo
	 */
	void saveKcbContactInfo(KcbContactInfo kcbContactInfo);
	
	/**
	 * KCB 가입정보 조회
	 * @param HashMap
	 * @return HashMap
	 */
	HashMap<String, String> getKcbJoinInfo(HashMap<String, String> schMap);
	
	/**
	 * 개인번호 & 전문명으로 조회이력 가져오기
	 * @param CreditInfo
	 * @return CreditInfo
	 */
	List<CreditInfo> getCreditInfoByNmIf(CreditInfo info);
}
