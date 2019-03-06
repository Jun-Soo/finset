/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * 스크래핑 데이터를 기준으로 필요한 테이블에 데이터를 집어넣는 과정을 총괄하는 클래스
 */
package com.koscom.scrapData.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.scrapData.model.AssetsDataForm;
import com.koscom.scrapData.model.ConsumeDataForm;
import com.koscom.scrapData.service.AssetsDataManager;
import com.koscom.scrapData.service.ConsumeDataManager;
import com.koscom.scrapData.service.ScrapDataManager;
import com.koscom.util.FinsetException;

@Service("scrapDataManager")
public class ScrapDataManagerImpl implements ScrapDataManager {

	private static final Logger logger = LoggerFactory.getLogger(ScrapDataManagerImpl.class);

	@Autowired
	ConsumeDataManager consumeDataManager;

	@Autowired
	AssetsDataManager assetsDataManager;

	@Transactional(rollbackFor=FinsetException.class)
	@Override
	public void saveScrapData(String no_person) throws FinsetException {
		logger.debug("saveScrapData");
		try{
			ConsumeDataForm consumeForm = new ConsumeDataForm();
			consumeForm.setNo_person(no_person);
			consumeDataManager.saveConsumeData(consumeForm);

			AssetsDataForm assetsDataForm = new AssetsDataForm();
			assetsDataForm.setNo_person(no_person);

			assetsDataManager.mergeAssetsInfoScrData(assetsDataForm);
		} catch(Exception e) {
			throw new FinsetException(e.getMessage());
		}

	}
}
