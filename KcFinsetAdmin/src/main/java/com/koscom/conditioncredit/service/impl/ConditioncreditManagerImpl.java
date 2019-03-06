package com.koscom.conditioncredit.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.conditioncredit.dao.ConditioncreditMapper;
import com.koscom.conditioncredit.model.ConditioncreditVO;
import com.koscom.conditioncredit.service.ConditioncreditManager;
import com.koscom.domain.ConditioncreditInfo;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("conditioncreditManager")
public class ConditioncreditManagerImpl implements ConditioncreditManager{

	private static final Logger logger = LoggerFactory.getLogger(ConditioncreditManagerImpl.class);

	@Autowired
	private ConditioncreditMapper conditioncreditMapper;

	@Override
	public ConditioncreditVO getConditioncreditInfo(String no_prepare) {
		return conditioncreditMapper.getConditioncreditInfo(no_prepare);
	}

	@Override
	public ReturnClass procConditioncreditInfo(ConditioncreditInfo conditioncreditInfo) {
		if (1 != conditioncreditMapper.procConditioncreditInfo(conditioncreditInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delConditioncreditInfo(ConditioncreditVO conditioncreditVO) {
		if (1 != conditioncreditMapper.delConditioncreditInfo(conditioncreditVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

}
