/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * ScrapDataManager를 호출하기 위한 클래스
 */
package com.koscom.scrapData.service;

import com.koscom.util.FinsetException;

public interface ScrapDataWebManager {
	
	void saveScrapData(String no_person) throws FinsetException;
	
}
