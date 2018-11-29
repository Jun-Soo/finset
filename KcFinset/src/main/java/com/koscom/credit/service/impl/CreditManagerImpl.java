package com.koscom.credit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.counsel.model.CounselForm;
import com.koscom.counsel.model.CounselVO;
import com.koscom.credit.dao.CreditMapper;
import com.koscom.credit.service.CreditManager;
import com.koscom.domain.CreditInfo;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

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

	@Override
	public List<CounselVO> listCreditCounselMain(CounselForm counselForm) {
		return creditMapper.listCreditCounselMain(counselForm);
	}

	@Override
	public CounselVO getCreditCounselInfo(CounselForm counselForm) {
		return creditMapper.getCreditCounselInfo(counselForm);
	}

	@Override
	public HashMap<String, String> getPersonCounselMapInfo(CounselForm counselForm) {
		return creditMapper.getPersonCounselMapInfo(counselForm);
	}

	@Override
	public CounselVO getCreditCounselBaseInfo(String no_person) {
		return creditMapper.getCreditCounselBaseInfo(no_person);
	}

	@Override
	public CounselVO getCreditCounselAddInfo(String no_person) {
		CounselVO counselVO = creditMapper.getCreditCounselAddInfo(no_person);
		int amt_consume_total = Integer.parseInt(counselVO.getAmt_consume_total());
		int amt_card_total = Integer.parseInt(counselVO.getAmt_card_total());
		//전월 사용금액 - 소비(지출) 총합, 카드 총이용금액 비교
		if(amt_consume_total > amt_card_total){
			counselVO.setAmt_expense_total(String.valueOf(amt_consume_total));
		}else{
			counselVO.setAmt_expense_total(String.valueOf(amt_card_total));
		}

		return counselVO;
	}

	@Override
	public ReturnClass createCreditCounselInfo(CounselVO counselVO) {
		if(1 != creditMapper.createCreditCounselInfo(counselVO)){
			return new ReturnClass(Constant.FAILED, "등록 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "등록 성공하였습니다.");
	}

	@Override
	public ReturnClass updateCreditCounselInfo(CounselVO counselVO) {
		if(1 != creditMapper.updateCreditCounselInfo(counselVO)){
			return new ReturnClass(Constant.FAILED, "수정 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "수정 성공하였습니다.");
	}

	@Override
	public ReturnClass deleteCreditCounselInfo(CounselVO counselVO) {
		if(1 != creditMapper.deleteCreditCounselInfo(counselVO)){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}


}