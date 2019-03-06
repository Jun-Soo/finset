/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180724~0827
 * 소비 지출 - 데이터를 가공하고 ConsumeMapper.java를 호출하기 위한 클래스
 */
package com.koscom.consume.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PersonConsumeClassForm;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonTransDetailVO;
import com.koscom.consume.service.ConsumeManager;
import com.koscom.consume.service.ConsumeWebManager;

@Service("consumeWebManager")
public class ConsumeWebManagerImpl implements ConsumeWebManager{

	private static final Logger logger = LoggerFactory.getLogger(ConsumeWebManagerImpl.class);

	@Autowired
	private ConsumeManager consumeManager;

	/**
	 * 소비지출 테이블에 추가
	 * @param consumeVO
	 * @return
	 */
	@Override
	public int createConsumeInfo(ConsumeVO consumeVO) {
		logger.debug("createConsumeInfo");
		return consumeManager.createConsumeInfo(consumeVO);
	}

	/**
	 * 조회 기간 전체의 수입이나 지출의 합을 조회
	 * @param consumeForm
	 * @return
	 */
	@Override
	public int getConsumeInfoAmt(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfoAmt");
		return consumeManager.getConsumeInfoAmt(consumeForm);
	}
	
	/**
	 * 조건 (일자)와 회원 관리번호의 조건 내에서 해당하는 소비지출 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	@Override
	public List<ConsumeVO> listConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("listConsumeInfo");
		return consumeManager.listConsumeInfo(consumeForm);
	}
	
	/**
	 * 회원 관리 번호, 금융사 코드, 승인번호의 조건 내에 해당하는 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	@Override
	public ConsumeVO getConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfo");
		return consumeManager.getConsumeInfo(consumeForm);
	}

	/**
	 * 소비 지출 데이터 변경
	 * @param consumeVO
	 * @return
	 */
	@Override
	public int modifyConsumeInfo(ConsumeVO consumeVO) {
		logger.debug("modifyConsumeInfo");
		return consumeManager.modifyConsumeInfo(consumeVO);
	}

	/**
	 * 소비 지출 데이터 미사용 처리
	 * @param consumeVO
	 * @return
	 */
	@Override
	public int delConsumeInfo(ConsumeVO consumeVO) {
		logger.debug("delConsumeInfo");
		return consumeManager.delConsumeInfo(consumeVO);
	}
	
	/**
	 * 개인 계좌 입출금내역 조회
	 * @param person
	 * @return
	 */
	@Override
	public List<PersonTransDetailVO> listPersonTransDetail(ConsumeForm consumeForm) {
		logger.debug("listPersonTransDetail");
		return consumeManager.listPersonTransDetail(consumeForm);
	}
	
	/**
	 * 소비 분류, 항목 기본생성
	 * @param no_person
	 * @return
	 */
	@Override
	public int createDefaultConsumeClassInfo(String no_person) {
		logger.debug("createDefaultConsumeClassInfo");
		return consumeManager.createDefaultConsumeClassInfo(no_person);
	}
	
	/**
	 * 소비 분류 리스트 조회
	 * @param no_person
	 * @return
	 */
	@Override
	public List<List<PersonConsumeClassVO>> listPersonConsumeClassInfo(String no_person) {
		logger.debug("listPersonConsumeClassInfo");
		return consumeManager.listPersonConsumeClassInfo(no_person);
	}
	
	/**
	 * 수입 분류 리스트 조회
	 * @param no_person
	 * @return
	 */
	@Override
	public List<PersonConsumeClassVO> listPersonIncomeClassInfo(String no_person) {
		logger.debug("listPersonIncomeClassInfo");
		return consumeManager.listPersonIncomeClassInfo(no_person);
	}
	
	/**
	 * 소비 분류 미사용 처리
	 * @return
	 */
	@Override
	public int delPersonConsumeClassInfo(PersonConsumeClassForm personConsumeClassForm) {
		logger.debug("delPersonConsumeClassInfo");
		return consumeManager.delPersonConsumeClassInfo(personConsumeClassForm);
	}
	
	/**
	 * 소비 분류, 항목 생성
	 * @param personConsumeClassVO
	 * @return
	 */
	@Override
	public int createPersonConsumeClassInfo(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("createPersonConsumeClassInfo");
		return consumeManager.createPersonConsumeClassInfo(personConsumeClassVO);
	}
	
	/**
	 * 소비 분류에 해당하는 항목 리스트 조회
	 * @param personConsumeClassForm
	 * @return
	 */
	@Override
	public List<PersonConsumeClassVO> listPersonConsumeTypeInfo(PersonConsumeClassForm personConsumeClassForm) {
		logger.debug("listPersonConsumeTypeInfo");
		return consumeManager.listPersonConsumeTypeInfo(personConsumeClassForm);
	}
	
	/**
	 * 결산 상단에 들어갈 카드, 현금 사용량을 조회
	 * @param consumeForm
	 * @return
	 */
	@Override
	public List<ConsumeVO> summaryStatsConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("summaryStatsConsumeInfo");
		return consumeManager.summaryStatsConsumeInfo(consumeForm);
	}
	
	/**
	 * 결산 하단에 들어갈 분류별 사용량 조회
	 * @param consumeForm
	 * @return
	 */
	@Override
	public List<List<Object>> listStatsConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("listStatsConsumeInfo");
		return consumeManager.listStatsConsumeInfo(consumeForm);
	}
	
	/**
	 * 기간에 들어갈 꺽은선 그래프를 구성하기 위해 분류별, 일별 데이터 조회
	 * @param consumeForm
	 * @return
	 */
	@Override
	public List<List<Object>> listPeriodConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("listPeriodConsumeInfo");
		return consumeManager.listPeriodConsumeInfo(consumeForm);
	}
	
	/**
	 * 소비지출관리T 입출금내역 자동스크래핑데이터 조회
	 * @param String no_person
	 * @return List<ConsumeVO>
	 */
	@Override
	public List<ConsumeVO> listConsumeInfoByScrTransDt(String no_person) {
		logger.debug("listConsumeInfoByScrTransDt");
		return consumeManager.listConsumeInfoByScrTransDt(no_person);
	}

	/**
	 * 소비지출관리T 입출금내역 자동스크래핑데이터 입력
	 * @param consumeVO
	 * @return int
	 */
	@Override
	public int createConsumeInfoByScrTransDt(ConsumeVO consumeVO) {
		logger.debug("createConsumeInfoByScrTransDt");
		return consumeManager.createConsumeInfoByScrTransDt(consumeVO);
	}
}
