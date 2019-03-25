/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * 소비 지출 - ConsumeManagerImpl.java를 호출하기 위한 인터페이스
 */
package com.koscom.scrapData.service;

import com.koscom.scrapData.model.ConsumeDataForm;


public interface ConsumeDataManager {
	
	/**
	 * 소비지출 데이터를 저장하는 함수
	 * @param consumeForm
	 * @throws Exception
	 */
	void saveConsumeData(ConsumeDataForm consumeForm) throws Exception ;
	
}
