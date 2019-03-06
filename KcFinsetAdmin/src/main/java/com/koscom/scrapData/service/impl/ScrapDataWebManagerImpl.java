/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * ScrapDataManager를 호출하기 위한 클래스
 */
package com.koscom.scrapData.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.scrapData.service.ScrapDataManager;
import com.koscom.scrapData.service.ScrapDataWebManager;
import com.koscom.util.FinsetException;

@Service("scrapDataWebManager")
public class ScrapDataWebManagerImpl implements ScrapDataWebManager{

	private static final Logger logger = LoggerFactory.getLogger(ScrapDataWebManagerImpl.class);

	@Autowired
	ScrapDataManager scrapDataManager;
	
	@Override
	public void saveScrapData(String no_person) throws FinsetException {
		logger.debug("saveScrapData");
		scrapDataManager.saveScrapData(no_person);
	}
}
