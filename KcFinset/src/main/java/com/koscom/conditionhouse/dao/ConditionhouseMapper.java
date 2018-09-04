package com.koscom.conditionhouse.dao;

import com.koscom.conditionhouse.model.ConditionhouseVO;
import com.koscom.domain.ConditionhouseInfo;

public interface ConditionhouseMapper {
	
	/**
	 * 조건주택담보정보 조회
	 * @param no_prepare
	 * @return
	 */
	ConditionhouseVO getConditionhouseInfo(String no_prepare);
	
	/**
	 * 조건주택담보 삽입,수정
	 * @param conditionhouseInfo
	 * @return
	 */
	int procConditionhouseInfo(ConditionhouseInfo conditionhouseInfo);
}
