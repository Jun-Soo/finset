package com.koscom.conditioncredit.dao;

import com.koscom.conditioncredit.model.ConditioncreditVO;
import com.koscom.domain.ConditioncreditInfo;

public interface ConditioncreditMapper {
	
	/**
	 * 조건개인정보 조회
	 * @param no_prepare
	 * @return
	 */
	ConditioncreditVO getConditioncreditInfo(String no_prepare);
	
	/**
	 * 조건개인 삽입,수정
	 * @param conditioncreditInfo
	 * @return
	 */
	int procConditioncreditInfo(ConditioncreditInfo conditioncreditInfo);
}
