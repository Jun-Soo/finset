package com.koscom.consume.service;

import java.util.List;
import java.util.Map;

import com.koscom.consume.model.ConsumeDetailGoalInfoVO;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
import com.koscom.consume.model.PersonSetInfoForm;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PaymentForm;
import com.koscom.consume.model.PaymentVO;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.model.PersonTransDetailVO;
import com.koscom.domain.PersonInfo;

public interface ConsumeManager {
	/**
	 * 공유된 사용자와 본인 정보를 조회
	 * @param no_person
	 * @return
	 */
	List<PersonInfo> listConsumeSharePersonInfo(String no_person);
	
	/**
	 * 스크래핑 연계 여부 조회
	 * @param no_person
	 * @return
	 */
	boolean chkScrapCard(String no_person);
	
	/**
	 * 배너에 뿌려진 예산 확인
	 * @param no_person
	 * @return
	 */
	ConsumeGoalInfoVO getConsumeGoalData(String no_person, String ym);
	
	/**
	 * 조회 기간 전체의 수입이나 지출의 합을 조회
	 * @param consumeForm
	 * @return
	 */
	Map<String, String> listConsumeInfoAmt(ConsumeForm consumeForm);
	
	/**
	 * 조건 (일자)와 회원 관리번호의 조건 내에서 해당하는 소비지출 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<List<ConsumeVO>> listConsumeInfo(ConsumeForm consumeForm);
	
	/**
	 * 개인 계좌 입출금내역 조회
	 * @param person
	 * @return
	 */
	List<List<PersonTransDetailVO>> listPersonTransDetail(ConsumeForm consumeForm);
	
	/**
	 * 소비 분류, 항목 기본생성
	 * @param no_person
	 * @return
	 */
	int createDefaultConsumeClassInfo(String no_person);
	
	/**
	 * 소비 분류 리스트 조회
	 * @param no_person
	 * @return
	 */
	List<List<PersonConsumeClassVO>> listPersonConsumeClassInfo(String no_person);
	
	/**
	 * 수입 분류 리스트 조회
	 * @param no_person
	 * @return
	 */
	List<PersonConsumeClassVO> listPersonIncomeClassInfo(String no_person);

	/**
	 * 개인 설정 조회
	 * @param no_person
	 * @return
	 */
	PersonSetInfoVO getPersonSetInfo(String no_person);
	
	/**
	 * 개인 설정 변경
	 * @param personSetInfoVO
	 * @return
	 */
	int modifyPersonSetInfo(PersonSetInfoVO personSetInfoVO);
	
	/**
	 * 소비 목표 등록
	 * @param consumeGoalInfoVO
	 * @return
	 */
	int createGoal(ConsumeGoalInfoVO consumeGoalInfoVO);
	
	/**
	 * 소비 목표 조회
	 * @param consumeGoalInfoVO
	 * @return
	 */
	ConsumeGoalInfoVO getGoal(ConsumeGoalInfoVO consumeGoalInfoVO);
	
	/**
	 * 공통 캘린더 합계 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listCalendarConsumeData(ConsumeForm consumeForm);
	
	/**
	 * 공통 캘린더 리스트 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listDetailCalendarConsumeData(ConsumeForm consumeForm);
	
	/**
	 * 청구내역 요약 조회
	 * @param paymentForm
	 * @return
	 */
	PaymentVO getPaymentSummary(PaymentForm paymentForm);
	
	/**
	 * 청구내역 리스트 조회
	 * @param paymentForm
	 * @return
	 */
	List<PaymentVO> listPayment(PaymentForm paymentForm);

	/**
	 * 할부 분할 여부 업데이트
	 * @param consumeSettingForm
	 */
	void modifyYn_installment(PersonSetInfoForm personSetInfoForm);
	
	/**
	 * 기준일 업데이트
	 * @param consumeSettingForm
	 */
	void modifyDt_basic(PersonSetInfoForm personSetInfoForm);
	
	/**
	 * 소비 상세 목표 리스트 조회
	 * @param consumeDetailGoalInfoVO
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listDetailGoal(String no_person, String cd_set);
	
	/**
	 * 예산 사용을 위해 전월 지출내역 조회
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listPrevMonthConsume(String no_person, String cd_set);
	
	/**
	 * 예산 상세 내역 저장
	 * @param consumeDetailGoalInfoVO
	 */
	void createDetailGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO, String cd_set);
	
	/**
	 * 소비 상세내역 조회
	 * @param consumeForm
	 * @return
	 */
	ConsumeVO getConsumeInfo(ConsumeForm consumeForm);
	
	/**
	 * 예산 사용을 위해 3개월 평균 지출내역 조회
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listAverageConsume(String no_person, String cd_set);
	
	/**
	 * 소비 분류 정렬순서 업데이트
	 * @param personConsumeClassVO
	 */
	void modifyPersonSortClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 소비 항목 정렬순서 업데이트
	 * @param personConsumeClassVO
	 */
	void modifyPersonSortType(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 분류 미사용 처리
	 * @param personConsumeClassVO
	 */
	void deletePersonConsumeClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 항목 미사용 처리
	 * @param personConsumeClassVO
	 */
	void deletePersonConsumeClassType(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 분류명 변경
	 * @param personConsumeClassVO
	 */
	void modifyPersonConsumeClassNmClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 항목명 변경
	 * @param personConsumeClassVO
	 */
	void modifyPersonConsumeClassNmType(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 소비 분류 추가
	 * @param personConsumeClassVO
	 */
	void createPersonConsumeClassClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 소비 항목 추가
	 * @param personConsumeClassVO
	 */
	void createPersonConsumeClassType(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 수입 분류 추가
	 * @param personConsumeClassVO
	 */
	void createPersonConsumeClassIncome(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 소비 정보 업데이트
	 * @param consumeVO
	 */
	void modifyConsumeInfo(ConsumeVO consumeVO);
	
	/**
	 * 소비 삭제
	 * @param consumeForm
	 */
	void deleteConsumeInfo(ConsumeForm consumeForm);
	
	/**
	 * 소비지출내역 생성
	 * @param consumeVO
	 */
	void createConsumeInfo(ConsumeVO consumeVO);
	
	/**
	 * 배너 데이터 조회
	 * @param consumeVO
	 * @return
	 */
	int getBannerData(ConsumeVO consumeVO);
	
	/**
	 * 현금, 스크래핑된 계좌, 카드 조회 
	 * @param no_person
	 * @return
	 */
	List<ConsumeVO> listMeansConsume(String no_person);
	
	/**
	 * 년 달력 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listCalendarConsumeDataYear(ConsumeForm consumeForm);
	
	/**
	 * 월별 수입,지출상세 리스트 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listConsumeAnalyzeMonth(ConsumeForm consumeForm);
	
	/**
	 * 일별 수입,지출상세 리스트 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listConsumeAnalyzeDay(ConsumeForm consumeForm);
}
