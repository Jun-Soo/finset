package com.koscom.consume.dao;

import java.util.List;

import com.koscom.consume.model.ConsumeDetailGoalInfoVO;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PaymentForm;
import com.koscom.consume.model.PaymentVO;
import com.koscom.consume.model.PersonConsumeClassVO;
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
	 * 개인 계좌 입출금내역 조회
	 * @param person
	 * @return
	 */
	List<PersonTransDetailVO> listPersonTransDetail(ConsumeForm consumeForm);
	
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
	 * 소비 상세 목표 등록
	 * @param consumeDetailGoalInfoVO
	 * @return
	 */
	int createDetailGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO);
	
	/**
	 * 소비 상세 목표 리스트 조회
	 * @param consumeDetailGoalInfoVO
	 * @return
	 */
	List<ConsumeDetailGoalInfoVO> listDetailGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO);
	
	/**
	 * 공통 캘린더 합계 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> getCalendarConsumeData(ConsumeForm consumeForm);
	
	/**
	 * 공통 캘린더 리스트 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listCalendarConsumeData(ConsumeForm consumeForm);
	
	/**
	 * 청구내역 리스트 조회
	 * @param paymentForm
	 * @return
	 */
	List<PaymentVO> listPayment(PaymentForm paymentForm);
}
