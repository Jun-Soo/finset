package com.koscom.credit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.credit.model.NpacVO;
import com.koscom.credit.service.CreditManager;
import com.koscom.credit.service.CreditWebManager;
import com.koscom.domain.CreditInfo;
import com.koscom.util.ReturnClass;

@Service("creditWebManager")
public class CreditWebManagerImpl implements CreditWebManager {

	private static final Logger logger = LoggerFactory.getLogger(CreditWebManagerImpl.class);

	@Autowired
	private CreditManager creditManager;

	@Override
	public NpacVO getNpacHistBySeq(String seq) {
		return creditManager.getNpacHistBySeq(seq);
	}
	@Override
	public ReturnClass createNpacHist(NpacVO npacVO) {
		return creditManager.createNpacHist(npacVO);
	}
	@Override
	public List<CreditInfo> getCreditInfoByNmIf(CreditInfo info) {
		// TODO Auto-generated method stub
		return creditManager.getCreditInfoByNmIf(info);
	}
	@Override
	public CreditInfo getCreditInfo(int seq) {
		// TODO Auto-generated method stub
		return creditManager.getCreditInfo(seq);
	}
	@Override
	public void saveCredit(CreditInfo info) {
		// TODO Auto-generated method stub
		creditManager.saveCredit(info);
	}
	@Override
	public HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap) {
		// TODO Auto-generated method stub
		return creditManager.getKcbInfoCLOB(searchMap);
	}

	/**
	 * Method Desc : Client 신용관리 메인
	 * 2018.01.03 jsp
	 * @param noPerson
	 */
	@Override
	public CreditInfo getCreditMainBaseInfo(String no_person) {
		return creditManager.getCreditMainBaseInfo(no_person);
	}

	@Override
	public CreditInfo getCreditMainCntInfo(String no_person) {
		return creditManager.getCreditMainCntInfo(no_person);
	}

	/**
	 * Method Desc : Client 신용관리_신용등급상세
	 * 2018.01.12 jsp
	 * @param noPerson, creditInfo
	 */
	@Override
	public List<CreditInfo> getCreditDetailGradeChartList(String no_person) {
		return creditManager.getCreditDetailGradeChartList(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailGradeInquiryList(String no_person) {
		return creditManager.getCreditDetailGradeInquiryList(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailGradeChangeList(CreditInfo creditInfo) {
		return creditManager.getCreditDetailGradeChangeList(creditInfo);
	}

	/**
	 * Method Desc : Client 신용관리_카드현황,연체현황,연대보증현황
	 * 2018.01.10 jsp
	 * @param noPerson
	 */
	@Override
	public HashMap<String, String> getCreditDetailJsonInfo(String no_person) {
		return creditManager.getCreditDetailJsonInfo(no_person);
	}

	/**
	 * Method Desc : Client 신용관리_대출현황
	 * 2018.01.12 jsp
	 * @param noPerson
	 */
	@Override
	public CreditInfo getCreditDetailDEBTCreditSum(String no_person) {
		return creditManager.getCreditDetailDEBTCreditSum(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailDEBTCreditList(String no_person) {
		return creditManager.getCreditDetailDEBTCreditList(no_person);
	}
	@Override
	public CreditInfo getCreditDetailDEBTLoanSum(String no_person) {
		return creditManager.getCreditDetailDEBTLoanSum(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailDEBTLoanList(String no_person) {
		return creditManager.getCreditDetailDEBTLoanList(no_person);
	}
}
