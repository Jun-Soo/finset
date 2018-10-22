package com.koscom.main.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.main.dao.MainMapper;
import com.koscom.main.service.MainManager;

@Service("mainManager")
public class MainManagerImpl implements MainManager {

	private static final Logger logger = LoggerFactory.getLogger(MainManagerImpl.class);

	@Autowired
	private MainMapper mainMapper;

	@Override
	public String getMainConsumeSumAmt(String no_person) {
		return mainMapper.getMainConsumeSumAmt(no_person);
	}

	@Override
	public String getMainAssetsSumAmt(String no_person) {
		return mainMapper.getMainAssetsSumAmt(no_person);
	}

	@Override
	public String getMainDebtSumAmt(String no_person) {
		return mainMapper.getMainDebtSumAmt(no_person);
	}

}