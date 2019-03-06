package com.koscom.conditionhouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.conditionhouse.model.ConditionhouseVO;
import com.koscom.conditionhouse.service.ConditionhouseManager;
import com.koscom.conditionhouse.service.ConditionhouseWebManager;
import com.koscom.domain.ConditionhouseInfo;
import com.koscom.util.ReturnClass;

@Service("conditionhouseWebManager")
public class ConditionhouseWebManagerImpl implements ConditionhouseWebManager{

	@Autowired
	private ConditionhouseManager conditionhouseManager;

	@Override
	public ConditionhouseVO getConditionhouseInfo(String no_prepare) {
		return conditionhouseManager.getConditionhouseInfo(no_prepare);
	}

	@Override
	public ReturnClass procConditionhouseInfo(ConditionhouseInfo conditionhouseInfo) {
		return conditionhouseManager.procConditionhouseInfo(conditionhouseInfo);
	}

	@Override
	public ReturnClass delConditionhouseInfo(ConditionhouseVO conditionhouseVO) {
		return conditionhouseManager.delConditionhouseInfo(conditionhouseVO);
	}

}
