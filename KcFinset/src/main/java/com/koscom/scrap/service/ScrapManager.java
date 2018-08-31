package com.koscom.scrap.service;

public interface ScrapManager {
	
	/**
	 * 자동스크래핑 관련 정보 조회
	 * @param String
	 * @return String
	 */
	String getAutoScrapInfo(String cd_agency, String no_person);
}