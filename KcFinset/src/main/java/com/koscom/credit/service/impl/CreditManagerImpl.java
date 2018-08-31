package com.koscom.credit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.credit.dao.CreditMapper;
import com.koscom.credit.service.CreditManager;
import com.koscom.domain.CreditInfo;

@Service("creditManager")
@Transactional
public class CreditManagerImpl implements CreditManager {

	private static final Logger logger = LoggerFactory.getLogger(CreditManagerImpl.class);

	@Autowired
	private CreditMapper creditMapper;

	/**
	 * Method Desc : Client 신용관리 메인 기본정보
	 * 2018.01.03 jsp
	 * @param noPerson
	 * @return	CreditInfo
	 */
	@Override
	public CreditInfo getCreditMainBaseInfo(String no_person) {
		return creditMapper.getCreditMainBaseInfo(no_person);
	}
	
	/**
	 * Method Desc : Client 신용관리 메인 건수정보
	 * 2018.01.03 jsp
	 * @param noPerson
	 * @return	CreditInfo
	 */
	@Override
	public CreditInfo getCreditMainCntInfo(String no_person) {
		return creditMapper.getCreditMainCntInfo(no_person);
	}
	
	/**
	 * Method Desc : Client 신용관리_신용등급상세
	 * 2018.01.12 jsp
	 * @param noPerson, creditInfo
	 */
	@Override
	public List<CreditInfo> getCreditDetailGradeChartList(String no_person) {
		return creditMapper.getCreditDetailGradeChartList(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailGradeInquiryList(String no_person) {
		return creditMapper.getCreditDetailGradeInquiryList(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailGradeChangeList(CreditInfo creditInfo) {
		return creditMapper.getCreditDetailGradeChangeList(creditInfo);
	}
		
	/**
	 * Method Desc : kcb전문 송수신 이력조회 
	 * 2018.08.31
	 * @param	HashMap<String, String> searchMap
	 * @return	HashMap<String, String>
	 */
	public HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap) {
		return creditMapper.getKcbInfoCLOB(searchMap);
	}
}