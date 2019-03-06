package com.koscom.conditionbiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.conditionbiz.model.ConditionbizVO;
import com.koscom.conditionbiz.service.ConditionbizManager;
import com.koscom.conditionbiz.service.ConditionbizWebManager;
import com.koscom.domain.ConditionbizInfo;
import com.koscom.util.ReturnClass;

@Service("conditionbizWebManager")
public class ConditionbizWebManagerImpl implements ConditionbizWebManager{

	@Autowired
	private ConditionbizManager conditionbizManager;

	@Override
	public ConditionbizVO getConditionbizInfo(String no_prepare) {
		return conditionbizManager.getConditionbizInfo(no_prepare);
	}

	@Override
	public ReturnClass procConditionbizInfo(ConditionbizInfo conditionbizInfo) {
		return conditionbizManager.procConditionbizInfo(conditionbizInfo);
	}

	@Override
	public ReturnClass delConditionbizInfo(ConditionbizVO conditionbizVO) {
		return conditionbizManager.delConditionbizInfo(conditionbizVO);
	}

}
