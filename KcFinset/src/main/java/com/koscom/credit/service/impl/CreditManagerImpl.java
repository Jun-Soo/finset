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

	@Override
	public String getCreditInfoNextSeq() {
		return creditMapper.getCreditInfoNextSeq();
	}

	@Override
	public CreditInfo getCreditMainBaseInfo(String no_person) {
		return creditMapper.getCreditMainBaseInfo(no_person);
	}

	@Override
	public CreditInfo getCreditOverdueSumAmt(String no_person) {
		return creditMapper.getCreditOverdueSumAmt(no_person);
	}

	@Override
	public CreditInfo getCreditMainCntInfo(String no_person) {
		return creditMapper.getCreditMainCntInfo(no_person);
	}

	@Override
	public CreditInfo getCreditMainGradeChangeInfo(String no_person) {
		return creditMapper.getCreditMainGradeChangeInfo(no_person);
	}

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

	@Override
	public HashMap<String, String> getCreditDetailJsonInfo(String no_person) {
		return creditMapper.getCreditDetailJsonInfo(no_person);
	}

	@Override
	public CreditInfo getCreditDetailDEBTCreditSum(String no_person) {
		return creditMapper.getCreditDetailDEBTCreditSum(no_person);
	}

	@Override
	public List<CreditInfo> getCreditDetailDEBTCreditList(String no_person) {
		return creditMapper.getCreditDetailDEBTCreditList(no_person);
	}

	@Override
	public CreditInfo getCreditDetailDEBTLoanSum(String no_person) {
		return creditMapper.getCreditDetailDEBTLoanSum(no_person);
	}

	@Override
	public List<CreditInfo> getCreditDetailDEBTLoanList(String no_person) {
		return creditMapper.getCreditDetailDEBTLoanList(no_person);
	}

	@Override
	public CreditInfo getCreditDetailDEBTSum(String no_person) {
		return creditMapper.getCreditDetailDEBTSum(no_person);
	}

	@Override
	public List<CreditInfo> getCreditDetailDEBTList(String no_person) {
		return creditMapper.getCreditDetailDEBTList(no_person);
	}

	@Override
	public HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap) {
		return creditMapper.getKcbInfoCLOB(searchMap);
	}

	@Override
	public List<CreditInfo> getCreditInfoByNmIf(CreditInfo info) {
		return creditMapper.getCreditInfoByNmIf(info);
	}
}