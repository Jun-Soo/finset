/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * 소비 지출 - ConsumeDataMapper.xml을 호출하기 위한 인터페이스
 */
package com.koscom.scrapData.dao;

import java.util.List;
import java.util.Map;

import com.koscom.scrapData.model.ConsumeDataForm;
import com.koscom.scrapData.model.ConsumeDataVO;
import com.koscom.contents.model.ContentsVO;


public interface ConsumeDataMapper {
	
	/**
	 * 최종으로 입력된 데이터의 시간을 조회
	 * @return
	 */
	String getTmFromConsumeInfo(String no_person);
	
	/**
	 * 소비지출 데이터를 확보하기 위해 스크래핑 데이터 조회 - 차후 해동 VO로 받아야 한다.
	 * @param consumeForm
	 * @return
	 */
	List<Map<String, String>> listScrCardApprovalInfo(ConsumeDataForm consumeForm);
	
	/**
	 * 소비지출 데이터를 확보하기 위해 스크래핑 데이터 조회 - 차후 해동 VO로 받아야 한다.
	 * @param consumeForm
	 * @return
	 */
	List<Map<String, String>> listScrRespCashReceipt(ConsumeDataForm consumeForm);
	
	/**
	 * 업종명으로 소비항목코드를 조회
	 * @param nm_business
	 * @return
	 */
	String getCdConsumeClass(Map<String, String> paramMap);
	
	/**
	 * cd_consume_class로 cd_class와 cd_type을 조회
	 * @param cd_consume_class
	 * @return
	 */
	ContentsVO getCodeByCdConsumeClass(Map<String, String> paramMap);
	
	/**
	 * 소비지출 테이블에 추가
	 * @param consumeVO
	 * @return
	 */
	int createConsumeInfo(ConsumeDataVO consumeVO);

	/**
	 * 국세청 코드 조회
	 * @return
	 */
	String getNtsCode();
}
