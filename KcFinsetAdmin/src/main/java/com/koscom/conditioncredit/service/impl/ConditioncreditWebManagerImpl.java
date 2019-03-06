package com.koscom.conditioncredit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.conditioncredit.model.ConditioncreditVO;
import com.koscom.conditioncredit.service.ConditioncreditManager;
import com.koscom.conditioncredit.service.ConditioncreditWebManager;
import com.koscom.domain.ConditioncreditInfo;
import com.koscom.util.ReturnClass;

@Service("conditioncreditWebManager")
public class ConditioncreditWebManagerImpl implements ConditioncreditWebManager{

	@Autowired
	private ConditioncreditManager conditioncreditManager;

	@Override
	public ConditioncreditVO getConditioncreditInfo(String no_prepare) {
		return conditioncreditManager.getConditioncreditInfo(no_prepare);
	}

	@Override
	public ReturnClass procConditioncreditInfo(ConditioncreditInfo conditioncreditInfo) {
		return conditioncreditManager.procConditioncreditInfo(conditioncreditInfo);
	}

	@Override
	public ReturnClass delConditioncreditInfo(ConditioncreditVO conditioncreditVO) {
		return conditioncreditManager.delConditioncreditInfo(conditioncreditVO);
	}

}
