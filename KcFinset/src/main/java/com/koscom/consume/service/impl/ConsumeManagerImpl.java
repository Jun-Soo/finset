package com.koscom.consume.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.consume.dao.ConsumeMapper;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeVO;
//import com.koscom.consume.model.PersonConsumeClassForm;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.model.PersonTransDetailVO;
//import com.koscom.consume.model.StatsConsumeVO;
import com.koscom.consume.service.ConsumeManager;
import com.koscom.util.DateUtil;

@Service("consumeManager")
public class ConsumeManagerImpl implements ConsumeManager {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeManagerImpl.class);

	@Autowired
	private ConsumeMapper consumeMapper;
	
//	/**
//	 * 소비지출 테이블에 추가
//	 * @param consumeVO
//	 * @return
//	 */
//	@Override
//	public int createConsumeInfo(ConsumeVO consumeVO) {
//		logger.debug("createConsumeInfo");
//		return consumeMapper.createConsumeInfo(consumeVO);
//	}
//
	@Override
	public int getConsumeInfoAmt(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfoAmt");
		return consumeMapper.getConsumeInfoAmt(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> listConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("listConsumeInfo");
		return consumeMapper.listConsumeInfo(consumeForm);
	}
//
//	/**
//	 * 회원 관리 번호, 금융사 코드, 승인번호의 조건 내에 해당하는 데이터 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	@Override
//	public ConsumeVO getConsumeInfo(ConsumeForm consumeForm) {
//		logger.debug("getConsumeInfo");
//		return consumeMapper.getConsumeInfo(consumeForm);
//	}
	
	/**
	 * 소비 지출 데이터 변경
	 * @param consumeVO
	 * @return
	 */
//	@Override
//	public int modifyConsumeInfo(ConsumeVO consumeVO) {
//		logger.debug("modifyConsumeInfo");
//		return consumeMapper.modifyConsumeInfo(consumeVO);
//	}

	/**
	 * 소비 지출 데이터 미사용 처리
	 * @param consumeVO
	 * @return
	 */
//	@Override
//	public int delConsumeInfo(ConsumeVO consumeVO) {
//		logger.debug("delConsumeInfo");
//		return consumeMapper.delConsumeInfo(consumeVO);
//	}
	
	/**
	 * 개인 계좌 입출금내역 조회
	 * @param person
	 * @return
	 */
	@Override
	public List<PersonTransDetailVO> listPersonTransDetail(ConsumeForm consumeForm) {
		logger.debug("listPersonTransDetail");
		return consumeMapper.listPersonTransDetail(consumeForm);
	}
	
	/**
	 * 소비 분류, 항목 기본생성
	 * @param no_person
	 * @return
	 */
	@Override
	public int createDefaultConsumeClassInfo(String no_person) {
		logger.debug("createDefaultConsumeClassInfo");
		return consumeMapper.createDefaultConsumeClassInfo(no_person);
	}
	
	@Override
	public List<List<PersonConsumeClassVO>> listPersonConsumeClassInfo(String no_person) {
		logger.debug("listPersonConsumeClassInfo");
		
		List<List<PersonConsumeClassVO>> returnList = new ArrayList<List<PersonConsumeClassVO>>();
		List<PersonConsumeClassVO> list = consumeMapper.listPersonConsumeClassInfo(no_person);
		String curClass = "";
		List<PersonConsumeClassVO> subList = new ArrayList<PersonConsumeClassVO>();
		for(PersonConsumeClassVO vo: list) {
			if(!curClass.equals(vo.getCd_class())){
				if(!curClass.equals("")){
					returnList.add(subList);
					subList = new ArrayList<PersonConsumeClassVO>();
				}
				curClass = vo.getCd_class();
			}
			subList.add(vo);
		}
		returnList.add(subList);
		return returnList;
	}
	
	/**
	 * 수입 분류 리스트 조회
	 * @param no_person
	 * @return
	 */
	@Override
	public List<PersonConsumeClassVO> listPersonIncomeClassInfo(String no_person) {
		logger.debug("listPersonIncomeClassInfo");
		return consumeMapper.listPersonIncomeClassInfo(no_person);
	}
	
//	/**
//	 * 소비 분류 미사용 처리
//	 * @return
//	 */
//	@Override
//	public int delPersonConsumeClassInfo(PersonConsumeClassForm personConsumeClassForm) {
//		logger.debug("delPersonConsumeClassInfo");
//		return consumeMapper.delPersonConsumeClassInfo(personConsumeClassForm);
//	}
	
//	/**
//	 * 소비 분류, 항목 생성
//	 * @param personConsumeClassVO
//	 * @return
//	 */
//	@Override
//	public int createPersonConsumeClassInfo(PersonConsumeClassVO personConsumeClassVO) {
//		logger.debug("createPersonConsumeClassInfo");
//		return consumeMapper.createPersonConsumeClassInfo(personConsumeClassVO);
//	}
	
//	/**
//	 * 소비 분류에 해당하는 항목 리스트 조회
//	 * @param personConsumeClassForm
//	 * @return
//	 */
//	@Override
//	public List<PersonConsumeClassVO> listPersonConsumeTypeInfo(PersonConsumeClassForm personConsumeClassForm) {
//		logger.debug("listPersonConsumeTypeInfo");
//		return consumeMapper.listPersonConsumeTypeInfo(personConsumeClassForm);
//	}
	
//	/**
//	 * 결산 상단에 들어갈 카드, 현금 사용량을 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	@Override
//	public List<ConsumeVO> summaryStatsConsumeInfo(ConsumeForm consumeForm) {
//		logger.debug("summaryStatsConsumeInfo");
//		return consumeMapper.summaryStatsConsumeInfo(consumeForm);
//	}
//	
//	/**
//	 * 결산 하단에 들어갈 분류별 사용량 조회
//	 * @param consumeForm
//	 * @return
//	 */
//	@Override
//	public List<List<Object>> listStatsConsumeInfo(ConsumeForm consumeForm) {
//		logger.debug("listStatsConsumeInfo");
//		
//    	List<StatsConsumeVO> list = consumeMapper.listStatsConsumeInfo(consumeForm);
//    	List<List<Object>> listStatsConsumeInfo = new ArrayList<List<Object>>();
//    	
//    	List<Object> listTitle = new ArrayList<Object>();
//    	listTitle.add("Task");
//    	listTitle.add("Amt per Month");
//    	
//    	listStatsConsumeInfo.add(listTitle);
//    	
//    	for(int i=1;i<=list.size();i++) {
//    		StatsConsumeVO vo = list.get(i-1);
//    		List<Object> listObj = new ArrayList<Object>();
//    		listObj.add(vo.getNm_class());
//    		listObj.add(Integer.parseInt(vo.getAmt_in_out()));
//    		listStatsConsumeInfo.add(listObj);
//    	}
//		return listStatsConsumeInfo;
//	}
	
	/**
	 * 기간에 들어갈 꺽은선 그래프를 구성하기 위해 분류별, 일별 데이터 조회
	 * @param consumeForm
	 * @return
	 */
//	@Override
//	public List<List<Object>> listPeriodConsumeInfo(ConsumeForm consumeForm) {
//		logger.debug("listPeriodConsumeInfo");
//		
//		List<String> listNmClass = consumeMapper.listPeriodConsumeClassInfo(consumeForm);
//		List<StatsConsumeVO> list = consumeMapper.listPeriodConsumeInfo(consumeForm);
//		
//		if(listNmClass==null) {
//			return null;
//		}
//		
//		List<String> dateList = getFullDaysFromTo(consumeForm);
//		int rowNo = dateList.size();				//List를 생성해서 넣어야 되는 갯수 - 마지막에는 칼럼 명이 들어 갈거!
//		int columnNo = listNmClass.size()+1;	//각 List안에 들어갈 갯수
//		
//		List<List<Object>> listPeriodConsumeVO = new ArrayList<List<Object>>();
//		
//		for(int i=0;i<rowNo;i++) {
//			List<Object> listObj = new ArrayList<Object>();
//			for(int j=0;j<columnNo;j++){
//				if(j==0) {
//					listObj.add(dateList.get(i).substring(4));
//				} else {
//					listObj.add(0);
//				}
//			}
//			listPeriodConsumeVO.add(listObj);
//		}
//		
//		//이제 여기에는 Date를 제외하고는 0으로 세팅된 애들이 있어, 그럼 Date를 기준으로 집어넣어야되. 위치는 어떻게 알아? 
//		for(StatsConsumeVO vo: list) {
//			int dateIdx = dateList.indexOf(vo.getDt_trd());	//해당 날짜의 list안에서의 인덱스
//			int columnIdx = listNmClass.indexOf(vo.getNm_class())+1;		//해당 분류의 내부 list 내의 인덱스
//			listPeriodConsumeVO.get(dateIdx).set(columnIdx, Integer.parseInt(vo.getAmt_in_out()));
//		}
//		
//		List<Object> listColumn = new ArrayList<Object>();
//		listColumn.add("Date");
//		for(String nm_class:listNmClass) {
//			listColumn.add(nm_class);
//		}
//		listPeriodConsumeVO.add(listColumn);
//		
//		return listPeriodConsumeVO;
//	}
//	
//	private List<String> getFullDaysFromTo(ConsumeForm consumeForm) {
//		List<String> dateList = new ArrayList<String>();
//		String dt_from = consumeForm.getDt_from();
//		String dt_to = consumeForm.getDt_to();
//		while(!dt_from.equals(dt_to)) {
//			dateList.add(dt_from);
//			dt_from = DateUtil.addDays(dt_from, 1);
//		}
//		dateList.add(dt_to);
//		return dateList;
//	}
//	
//	/**
//	 * 소비지출관리T 입출금내역 자동스크래핑데이터 조회
//	 * @param String no_person
//	 * @return List<ConsumeVO>
//	 */
//	@Override
//	public List<ConsumeVO> listConsumeInfoByScrTransDt(String no_person) {
//		logger.debug("listConsumeInfoByScrTransDt");
//		return consumeMapper.listConsumeInfoByScrTransDt(no_person);
//	}
//
//	/**
//	 * 소비지출관리T 입출금내역 자동스크래핑데이터 입력
//	 * @param consumeVO
//	 * @return int
//	 */
//	@Override
//	public int createConsumeInfoByScrTransDt(ConsumeVO consumeVO) {
//		logger.debug("createConsumeInfoByScrTransDt");
//		return consumeMapper.createConsumeInfoByScrTransDt(consumeVO);
//	}
//	
	public PersonSetInfoVO getPersonSetInfo(String no_person) {
		logger.debug("getPersonSetInfo");
		return consumeMapper.getPersonSetInfo(no_person);
	}
	
	@Override
	public int modifyPersonSetInfo(PersonSetInfoVO personSetInfoVO) {
		logger.debug("modifyPersonSetInfo");
		return consumeMapper.modifyPersonSetInfo(personSetInfoVO);
	}
}
