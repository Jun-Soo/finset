package com.koscom.conditioncredit.service;

import com.koscom.conditioncredit.model.ConditioncreditVO;
import com.koscom.domain.ConditioncreditInfo;
import com.koscom.util.ReturnClass;

public interface ConditioncreditManager {

	/**
	 * 조건개인정보 조회
	 * @param no_prepare
	 * @return
	 */
	ConditioncreditVO getConditioncreditInfo(String no_prepare);

	/**
	 * 조건개인 삽입, 수정
	 * @param conditioncreditVO
	 * @return
	 */
	ReturnClass procConditioncreditInfo(ConditioncreditInfo conditioncreditInfo);

	/**
	 * 조건개인 삭제
	 * @param conditioncreditVO
	 * @return
	 */
	ReturnClass delConditioncreditInfo(ConditioncreditVO conditioncreditVO);

}
