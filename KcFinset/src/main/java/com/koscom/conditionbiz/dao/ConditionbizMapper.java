package com.koscom.conditionbiz.dao;

import com.koscom.conditionbiz.model.ConditionbizVO;
import com.koscom.domain.ConditionbizInfo;

public interface ConditionbizMapper {
	
	/**
	 * 조건사업자정보 조회
	 * @param no_prepare
	 * @return
	 */
	ConditionbizVO getConditionbizInfo(String no_prepare);
	
	/**
	 * 조건사업자 삽입,수정
	 * @param conditionbizInfo
	 * @return
	 */
	int procConditionbizInfo(ConditionbizInfo conditionbizInfo);
	
	/**
	 * 조건사업자 추가
	 * @param no_person
	 * @return
	 */
	int insertConditionbizInfo(String no_person);
}
