package com.koscom.conditionbiz.service;

import com.koscom.conditionbiz.model.ConditionbizVO;
import com.koscom.domain.ConditionbizInfo;
import com.koscom.util.ReturnClass;

public interface ConditionbizWebManager {

	/**
	 * 조건사업자정보 조회
	 * @param no_prepare
	 * @return
	 */
	ConditionbizVO getConditionbizInfo(String no_prepare);

	/**
	 * 조건사업자 삽입, 수정
	 * @param conditionbizVO
	 * @return
	 */
	ReturnClass procConditionbizInfo(ConditionbizInfo conditionbizInfo);

	/**
	 * 조건사업자 삭제
	 * @param conditionbizVO
	 * @return
	 */
	ReturnClass delConditionbizInfo(ConditionbizVO conditionbizVO);

}
