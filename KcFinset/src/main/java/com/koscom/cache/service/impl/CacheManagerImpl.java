package com.koscom.cache.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.koscom.cache.service.CacheManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("cache")
public class CacheManagerImpl implements CacheManager{
	
	private static final Logger logger = LoggerFactory.getLogger(CacheManagerImpl.class);

	@Override
	@CacheEvict(value={"CacheAgency"}, allEntries=true)
	public ReturnClass clearCacheAgency() {
		logger.info("Cache clear : [CacheAgency]");
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	
	@Override
	@CacheEvict(value={"CacheCode","CacheListCode"}, allEntries=true)
	public ReturnClass clearCacheCode() {
		logger.info("Cache clear : [CacheCode,CacheListCode]");
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
}
