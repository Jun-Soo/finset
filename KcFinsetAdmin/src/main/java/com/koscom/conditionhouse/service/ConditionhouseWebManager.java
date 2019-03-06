package com.koscom.conditionhouse.service;

import com.koscom.conditionhouse.model.ConditionhouseVO;
import com.koscom.domain.ConditionhouseInfo;
import com.koscom.util.ReturnClass;

public interface ConditionhouseWebManager {

	/**
	 * 조건주택담보정보 조회
	 * @param no_prepare
	 * @return
	 */
	ConditionhouseVO getConditionhouseInfo(String no_prepare);

	/**
	 * 조건주택담보 삽입, 수정
	 * @param conditionhouseVO
	 * @return
	 */
	ReturnClass procConditionhouseInfo(ConditionhouseInfo conditionhouseInfo);

	/**
	 * 조건주택담보 삭제
	 * @param conditionhouseVO
	 * @return
	 */
	ReturnClass delConditionhouseInfo(ConditionhouseVO conditionhouseVO);

}
