/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~30
 * 스크래핑 데이터를 기준으로 필요한 테이블에 데이터를 집어넣는 과정을 총괄하는 인터페이스
 */
package com.koscom.scrapData.service;

import com.koscom.util.FinsetException;

public interface ScrapDataManager {
	
	void saveScrapData(String no_person) throws FinsetException;
	
}
