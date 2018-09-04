package com.koscom.conditionbiz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.conditionbiz.dao.ConditionbizMapper;
import com.koscom.conditionbiz.model.ConditionbizVO;
import com.koscom.conditionbiz.service.ConditionbizManager;
import com.koscom.domain.ConditionbizInfo;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("conditionbizManager")
public class ConditionbizManagerImpl implements ConditionbizManager{

	private static final Logger logger = LoggerFactory.getLogger(ConditionbizManagerImpl.class);

	@Autowired
	private ConditionbizMapper conditionbizMapper;

	@Override
	public ConditionbizVO getConditionbizInfo(String no_prepare) {
		return conditionbizMapper.getConditionbizInfo(no_prepare);
	}

	@Override
	public ReturnClass procConditionbizInfo(ConditionbizInfo conditionbizInfo) {
		if (1 != conditionbizMapper.procConditionbizInfo(conditionbizInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
}
