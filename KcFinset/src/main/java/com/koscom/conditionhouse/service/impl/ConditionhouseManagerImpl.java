package com.koscom.conditionhouse.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.conditionhouse.dao.ConditionhouseMapper;
import com.koscom.conditionhouse.model.ConditionhouseVO;
import com.koscom.conditionhouse.service.ConditionhouseManager;
import com.koscom.domain.ConditionhouseInfo;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("conditionhouseManager")
public class ConditionhouseManagerImpl implements ConditionhouseManager{

	private static final Logger logger = LoggerFactory.getLogger(ConditionhouseManagerImpl.class);

	@Autowired
	private ConditionhouseMapper conditionhouseMapper;

	@Override
	public ConditionhouseVO getConditionhouseInfo(String no_prepare) {
		return conditionhouseMapper.getConditionhouseInfo(no_prepare);
	}

	@Override
	public ReturnClass procConditionhouseInfo(ConditionhouseInfo conditionhouseInfo) {
		if (1 != conditionhouseMapper.procConditionhouseInfo(conditionhouseInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
}
