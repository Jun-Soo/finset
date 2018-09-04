package com.koscom.consume.service;

import java.util.List;

import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.model.PersonTransDetailVO;

public interface ConsumeManager {
//	
//	/**
//	 * 소비지출 테이블에 추가
//	 * @param consumeVO
//	 * @return
//	 */
//	int createConsumeInfo(ConsumeVO consumeVO);
//	
	/**
	 * 조회 기간 전체의 수입이나 지출의 합을 조회
	 * @param consumeForm
	 * @return
	 */
	int getConsumeInfoAmt(ConsumeForm consumeForm);
	
	/**
	 * 조건 (일자)와 회원 관리번호의 조건 내에서 해당하는 소비지출 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	List<ConsumeVO> listConsumeInfo(ConsumeForm consumeForm);
//	
//	/**
//	 * 회원 관리 번호, 금융사 코드, 승인번호의 조건 내에 해당하는 데이터 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	ConsumeVO getConsumeInfo(ConsumeForm consumeForm);
//	
//	/**
//	 * 소비 지출 데이터 변경
//	 * @param consumeVO
//	 * @return
//	 */
//	int modifyConsumeInfo(ConsumeVO consumeVO);
//	
//	/**
//	 * 소비 지출 데이터 미사용 처리
//	 * @param consumeVO
//	 * @return
//	 */
//	int delConsumeInfo(ConsumeVO consumeVO);
//	
//	/**
//	 * 개인 계좌 입출금내역 조회
//	 * @param person
//	 * @return
//	 */
//	List<PersonTransDetailVO> listPersonTransDetail(ConsumeForm consumeForm);
//	
//	/**
//	 * 소비 분류, 항목 기본생성
//	 * @param no_person
//	 * @return
//	 */
//	int createDefaultConsumeClassInfo(String no_person);
//	
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
//	
//	/**
//	 * 소비 분류 미사용 처리
//	 * @return
//	 */
//	int delPersonConsumeClassInfo(PersonConsumeClassForm personConsumeClassForm);
//	
//	/**
//	 * 소비 분류, 항목 생성
//	 * @param personConsumeClassVO
//	 * @return
//	 */
//	int createPersonConsumeClassInfo(PersonConsumeClassVO personConsumeClassVO);
//	
//	/**
//	 * 소비 분류에 해당하는 항목 리스트 조회
//	 * @param personConsumeClassForm
//	 * @return
//	 */
//	List<PersonConsumeClassVO> listPersonConsumeTypeInfo(PersonConsumeClassForm personConsumeClassForm);
//	
//	/**
//	 * 결산 상단에 들어갈 카드, 현금 사용량을 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	List<ConsumeVO> summaryStatsConsumeInfo(ConsumeForm consumeForm);
//	
//	/**
//	 * 결산 하단에 들어갈 분류별 사용량 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	List<List<Object>> listStatsConsumeInfo(ConsumeForm consumeForm);
//	
//	/**
//	 * 기간에 들어갈 꺽은선 그래프를 구성하기 위해 분류별, 일별 데이터 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	List<List<Object>> listPeriodConsumeInfo(ConsumeForm consumeForm);
//	
//	/**
//	 * 소비지출관리T 입출금내역 자동스크래핑데이터 조회
//	 * @param String no_person
//	 * @return List<ConsumeVO>
//	 */
//	List<ConsumeVO> listConsumeInfoByScrTransDt(String no_person);
//
//	/**
//	 * 소비지출관리T 입출금내역 자동스크래핑데이터 입력
//	 * @param consumeVO
//	 * @return int
//	 */
//	int createConsumeInfoByScrTransDt(ConsumeVO consumeVO);
//	
//	/**
//	 * 개인 설정 조회
//	 * @param no_person
//	 * @return
//	 */
//	PersonSetInfoVO getPersonSetInfo(String no_person);
//	
//	/**
//	 * 개인 설정 변경
//	 * @param personSetInfoVO
//	 * @return
//	 */
//	int modifyPersonSetInfo(PersonSetInfoVO personSetInfoVO);
}
