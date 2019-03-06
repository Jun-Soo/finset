package com.koscom.loan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.domain.JobClassInfo;
import com.koscom.domain.LoanAgreeHist;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.loan.service.LoanManager;
import com.koscom.loan.service.LoanWebManager;
import com.koscom.util.ReturnClass;

@Service("loanWebManager")
public class LoanWebManagerImpl implements LoanWebManager{

	@Autowired
	LoanManager loanManager;
	
	@Override
	public ReturnClass insertLoanInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.insertLoanInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanForOffice(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanForOffice(txFcTransmitVO);
	}
	
	@Override
	public ReturnClass modifyLoanForBiz(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanForBiz(txFcTransmitVO);
	}
	
	@Override
	public TxFcTransmitVO getTxFcTransmitInfo(String no_bunch) {
		return loanManager.getTxFcTransmitInfo(no_bunch);
	}
	
	@Override
	public TxFcTransmitVO getTxFcTransmitInfoForMsg(String no_bunch) {
		return loanManager.getTxFcTransmitInfoForMsg(no_bunch);
	}

	@Override
	public List<JobClassInfo> getListJobcode(JobClassInfo jobClassInfo) {
		return loanManager.getListJobcode(jobClassInfo);
	}

	@Override
	public ReturnClass insertLoanAgreeHist(LoanAgreeHist loanAgreeHist) {
		return loanManager.insertLoanAgreeHist(loanAgreeHist);
	}

	@Override
	public ReturnClass modifyLoanForGuarantee(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanForGuarantee(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyCarInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyCarInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyRealEstateInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyRealEstateInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanAdditional(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanAdditional(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass insertLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo) {
		return loanManager.insertLoanGoodsChoice(cooconGoodsFavoriteInfo);
	}

	@Override
	public ReturnClass deleteLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo) {
		return loanManager.deleteLoanGoodsChoice(cooconGoodsFavoriteInfo);
	}

	@Override
	public ReturnClass modifyLoanEmployedInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanEmployedInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanREConditionInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanREConditionInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanREIncomeInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanREIncomeInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanRERepaymentInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanRERepaymentInfo(txFcTransmitVO);
	}

	@Override
	public ReturnClass modifyLoanREHomeInfo(TxFcTransmitVO txFcTransmitVO) {
		return loanManager.modifyLoanREHomeInfo(txFcTransmitVO);
	}

}
