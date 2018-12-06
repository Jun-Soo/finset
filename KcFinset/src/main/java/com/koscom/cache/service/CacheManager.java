package com.koscom.cache.service;

import com.koscom.util.ReturnClass;

public interface CacheManager {
	
	/**
	 * Agency 캐시 초기화
	 * @return ReturnClass
	 */
	ReturnClass clearCacheAgency();
	
	/**
	 * 코드 캐시를 초기화 합니다.
	 * @return ReturnClass
	 */
	public ReturnClass clearCacheCode();

}
