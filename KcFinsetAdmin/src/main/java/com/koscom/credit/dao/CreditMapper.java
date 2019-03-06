package com.koscom.credit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koscom.credit.model.CreditDtlVO;
import com.koscom.credit.model.NpacVO;
import com.koscom.debt.model.DebtPersonalInfoVO;
import com.koscom.debt.model.DebtPersonalRepayListVO;
import com.koscom.debt.model.KcbLoanInfoVO;
import com.koscom.domain.CreditInfo;
import com.koscom.kcb.model.CrawlingVO;
import com.koscom.kcb.model.KcbAddrInfo;
import com.koscom.kcb.model.KcbCardDtlList;
import com.koscom.kcb.model.KcbCardInfo;
import com.koscom.kcb.model.KcbCardListInfo;
import com.koscom.kcb.model.KcbContactInfo;
import com.koscom.kcb.model.KcbGuaranteeInfo;
import com.koscom.kcb.model.KcbJobInfo;
import com.koscom.kcb.model.KcbOverdueDefaultInfo;
import com.koscom.kcb.model.KcbOverdueInfo;
import com.koscom.kcb.model.KcbOverduePublicInfo;
import com.koscom.kcb.model.KcbOverdueSteadpayInfo;
import com.koscom.kcb.model.seg.Kcb_Segment030;
import com.koscom.kcb.model.seg.Kcb_Segment041;
import com.koscom.kcb.model.seg.Kcb_Segment061;
import com.koscom.kcb.model.seg.Kcb_Segment062;
import com.koscom.kcb.model.seg.Kcb_Segment065;
import com.koscom.person.model.PersonEtmIncomeInfo;

public interface CreditMapper {
	void createNpacHist(NpacVO npacVO);
	NpacVO getNpacHistBySeq(String seq);

	/**
	 * Method Desc : 개인번호 & 전문명으로 조회이력 가져오기
	 * @param noPerson
	 * @param nmIf
	 * @return
	 */
	List<CreditInfo> getCreditInfoByNmIf(CreditInfo info);

	/**
	 * Method Desc : 신용조회 결과 상세화면
	 * @param seq
	 * @return
	 */
	CreditInfo findBySeqOrderBySeqDesc(int seq);

	void saveCredit(CreditInfo info);
//	List<CreditInfo> findByNoInqKeyAndNmIfAndCdCbCompOrderBySeqDesc(String noInqKey, String nmIf, String cdCbComp, Sort sort);
//	List<CreditInfo> findByNoPersonAndNmCustAndNmIfAndNmIfSubAndCdCbCompOrderBySeqDesc(String noPerson, String nmCust, String nmIf, String nmIfSub, String cdCbComp, Sort sort);
//	List<CreditInfo> findByNoPersonAndNmCustAndNmIfAndCdCbCompOrderBySeqDesc(String noPerson, String nmCust, String nmIf, String cdCbComp, Sort sort);
//	List<CreditInfo> findByNoPersonAndNmCustAndCdCbCompOrderBySeqDesc(String noPerson, String nmCust, String cdCbComp, Sort sort);
//	List<CreditInfo> findByNoPersonAndNmIfAndNmIfSubAndCdCbCompOrderBySeqDesc(String noPerson, String nmIf, String nmIfSub, String cdCbComp, Sort sort);
//	List<CreditInfo> findByNoPersonAndNmIfAndCdCbCompOrderBySeqDesc(String noPerson, String nmIf, String cdCbComp,Sort sort);

	HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap);
	HashMap<String, String> getKcbJoinInfo(HashMap<String, String> schMap);
	
	String getCreditInfoNextSeq();

	void insetKcb030Info(Kcb_Segment030 kcb_Segment030);

	//소득 정보 입력
	void insertPersonEtmIncomeInfo(PersonEtmIncomeInfo personEtmIncomeInfo);

	//소득 정보 업데이트
	void updatePersonIncome(PersonEtmIncomeInfo personEtmIncomeInfo);

	//개인별 부채 정보 저장 - 만기까지의 빈 부채 내역 입력
	void saveEmptyRepayList(DebtPersonalInfoVO debtPersonalInfoVO);

	//개인별 신용정보 저장
	void saveKcbCreditInfo(CrawlingVO crawlingVO);

	//개인별 신용등급 저장
	void saveKcbCreditList(CrawlingVO crawlingVO);
	void saveKcbCreditHistList(CrawlingVO crawlingVO);

	//신용변동 최근변동일 조회
	String selectMaxCreditChange(String no_person);

	//개인별 신용 변동정보 저장
	void saveKcbCreditChangeInfo(CreditDtlVO creditVO);

	//신용조회내역 저장
	void saveKcbCreditInquiryInfo(CreditDtlVO creditDtlVO);

	//카드별 이력정보 해지일자 업데이트 - 추가 20180682 김휘경 - 해지 일자를 확인해야 하기 때문에 전체적으로 업데이트를 해준다
	void updateKcbCardInfoToday(KcbCardInfo kcbCardInfo);
	
	//카드별 이력정보 - 수정 20180626 김휘경 - 300전문에서 600420전문으로 변화함에 따라 데이터가 달라짐(Kcb_Segment041->KcbCardInfo)
	void saveKcbCardInfo(KcbCardInfo kcbCardInfo);
	
	//카드별 이력상세정보
	void saveKcbCardDtlList(KcbCardDtlList saveKcbCardInfo);
	
	//카드별 이력 중 type_card가 00일 경우 체크해서 넣어주어야 한다
	void saveKcbCardDelListZero(KcbCardDtlList saveKcbCardInfo);

	//회원별 카드이력정보
	void saveKcbCardListInfo(KcbCardListInfo saveKcbCardListInfo);

	//연체정보 만료일자 오늘로 세팅
	void updateDtDeleteOverdueInfo(String no_person);
	
	//연체정보 저장
	void saveKcbOverdueInfo(KcbOverdueInfo kcbOverdueInfo);

	//대지급정보 만료일자 오늘로 세팅
	void updateDtDeleteOverdueSteadpayInfo(String no_person);
	
	//대지급정보 저장
	void saveKcbOverdueSteadpayInfo(KcbOverdueSteadpayInfo kcbOverdueSteadpayInfo);

	//채무불이행정보 만료일자 오늘로 세팅
	void updateDtDeleteOverdueDefaultInfo(String no_person);
	
	//채무불이행정보 저장
	void saveKcbOverdueDefaultInfo(KcbOverdueDefaultInfo kcbOverdueDefaultInfo);

	//공공정보 만료일자 오늘로 세팅
	void updateDtDeleteOverduePublicInfo(String no_person);
	
	//공공정보 저장 - 20180702 김휘경 추가
	void saveKcbOverduePublicInfo(KcbOverduePublicInfo kcbOverduePublicInfo);
	
	//연대보증정보 만료일자 오늘로 세팅
	void updateDtDeleteGuaranteeInfo(String no_person);
	
	//연대보증정보 저장 - 20180702 김휘경 추가
	void saveKcbGuaranteeInfo(KcbGuaranteeInfo kcbGuaranteeInfo);
	
	//KCB 전문 수신일 조회
	String selectResDt(String noPerson);

	//신용정보조회 최근조회일
	String selectMaxCreditInquiry(String no_person);

	/**
	 * KCB 크롤링 정보 저장
	 * @param no_person
	 * @return
	 */
	int selectKcbCreditInfo(String no_person);
	void updateKcbCreditInfo(CrawlingVO vo);
	void insertKcbCreditInfo(CrawlingVO vo);

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
	 * 신용관리_카드현황, 연체현황, 연대보증현황
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

	/**
	 * 전문 처리 및 크롤링 실패시 데이터 삭제
	 * @param String noPerson
	 * @return
	 */
	void deleteKcbCb(String noPerson);
	
	/**
	 * 크롤링 실패 로그
	 * @param Map logMap
	 * @return
	 */
	void insertCrawlingLog(Map<String, Object> logMap);

	
	//20180702 김휘경 ParsePage2 추가부분
	/**
	 * 주소 정보 저장
	 * @param kcbAddrInfo
	 */
	void saveKcbAddrInfo(KcbAddrInfo kcbAddrInfo);
	
	/**
	 * 직장 정보 저장
	 * @param kcbJobInfo
	 */
	void saveKcbJobInfo(KcbJobInfo kcbJobInfo);
	
	/**
	 * 연락처 정보 저장
	 * @param kcbContactInfo
	 */
	void saveKcbContactInfo(KcbContactInfo kcbContactInfo);
	
}
