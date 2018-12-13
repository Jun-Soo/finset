package com.koscom.consume.dao;

import java.util.List;

import com.koscom.consume.model.ConsumeDetailGoalInfoVO;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PaymentForm;
import com.koscom.consume.model.PaymentVO;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoForm;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.model.PersonTransDetailVO;
import com.koscom.domain.PersonInfo;


public interface ConsumeMapper {
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
	List<String> chkScrapCard(String no_person);
	
	/**
	 * 설정된 예산 조회
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> getConsumeGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO);
	
	/**
	 * 해당 월 지출 합계
	 * @param consumeForm
	 * @return
	 */
	String getConsumeInfoAmt(ConsumeForm consumeForm);
	
	/**
	 * 조회 기간 전체의 수입이나 지출의 합을 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listConsumeInfoAmt(ConsumeForm consumeForm);
	
	/**
	 * 일자와 회원 관리번호의 조건 내에서 해당하는 소비지출 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listConsumeInfo(ConsumeForm consumeForm);
	
	/**
	 * 공유된 사용자의 정보 조회
	 * @param no_person
	 * @return
	 */
	List<ConsumeVO> listShareInfoDetail(String no_person);
	
	/**
	 * 개인 계좌 입출금내역 조회
	 * @param person
	 * @return
	 */
	List<PersonTransDetailVO> listPersonTransDetail(ConsumeForm consumeForm);
	
	/**
	 * 소비분류 코드 존재 여부 확인
	 * @param no_person
	 * @return
	 */
	Integer chkPersonConsumeClassInfoExist(String no_person);
	
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
	List<PersonConsumeClassVO> listPersonConsumeClassInfo(String no_person);
	
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
	 * 소비 상세 목표 리스트 조회(분류별)
	 * @param consumeDetailGoalInfoVO
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listDetailGoalClass(String no_person);
	
	/**
	 * 소비 상세 목표 리스트 조회(수단별)
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listDetailGoalMeans(String no_person);
	
	/**
	 * 예산 사용을 위해 전월 지출내역 조회(분류별)
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listPrevMonthClass(String no_person);
	
	/**
	 * 예산 사용을 위해 전월 지출내역 조회(수단별)
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listPrevMonthMeans(String no_person);
	
	/**
	 * 예산 상세 내역 저장(분류별)
	 * @param consumeDetailGoalInfoVO
	 */
	void createDetailGoalClass(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO);
	
	/**
	 * 예산 상세 내역 저장(수단별)
	 * @param consumeDetailGoalInfoVO
	 */
	void createDetailGoalMeans(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO);
	
	/**
	 * 예산 사용을 위해 3개월 평균 지출내역 조회(분류별)
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listAverageClass(String no_person);
	
	/**
	 * 예산 사용을 위해 3개월 평균 지출내역 조회(수단별)
	 * @param no_person
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listAverageMeans(String no_person);
	
	/**
	 * 소비 상세내역 조회
	 * @param consumeForm
	 * @return
	 */
	ConsumeVO getConsumeInfo(ConsumeForm consumeForm);
	
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
	 * 분류 삭제 이후 소비 분류 소트번호 재정의
	 * @param personConsumeClassVO
	 */
	void modifyPersonConsumeClassOtherClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 분류 미사용처리에 따른 소비 업데이트
	 * @param personConsumeClassVO
	 */
	void modifyConsumeInfoClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 
	 * @param personConsumeClassVO
	 */
	void modifyConsumeGoalInfoClass(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 항목 미사용 처리
	 * @param personConsumeClassVO
	 */
	void deletePersonConsumeClassType(PersonConsumeClassVO personConsumeClassVO);

	/**
	 * 항목 삭제 이후 소비 항목 소트번호 재정의
	 * @param personConsumeClassVO
	 */
	void modifyPersonConsumeClassOtherType(PersonConsumeClassVO personConsumeClassVO);
	
	/**
	 * 항목 미사용처리에 따른 소비 업데이트
	 * @param personConsumeClassVO
	 */
	void modifyConsumeInfoType(PersonConsumeClassVO personConsumeClassVO);
	
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
	 * 소비지출내역 생성(계좌)
	 * @param consumeVO
	 */
	void createConsumeInfoAcc(ConsumeVO consumeVO);

	/**
	 * 소비지출내역 생성(계좌 제외)
	 * @param consumeVO
	 */
	void createConsumeInfoOthers(ConsumeVO consumeVO);
	
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
	
	/**
	 * 년 통계 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listSettlementConsumeDataYear(ConsumeForm consumeForm);
	
	/**
	 * 주 통계 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listSettlementConsumeDataWeek(ConsumeForm consumeForm);
	
	/**
	 * 일 통계 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listSettlementConsumeDataDay(ConsumeForm consumeForm);
	
	/**
	 * 통계 범위별 리스트
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> getRangeListforSettlement(ConsumeForm consumeForm);
	
	
	/**
	 * 통계 상세 페이지 리스트
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> getSettlementDetail(ConsumeForm consumeForm);
}
