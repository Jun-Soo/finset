package com.koscom.loan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.loan.dao.LoanMapper;
import com.koscom.loan.service.LoanManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("loanManager")
public class LoanManagerImpl implements LoanManager{
    private static final Logger logger = LoggerFactory.getLogger(LoanManagerImpl.class);

	@Autowired
	LoanMapper loanMapper;

	@Override
	public ReturnClass insertLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo) {
		if(1 != loanMapper.insertLoanGoodsChoice(cooconGoodsFavoriteInfo)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다", cooconGoodsFavoriteInfo);
	}

	@Override
	public ReturnClass deleteLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo) {
		if(1 != loanMapper.deleteLoanGoodsChoice(cooconGoodsFavoriteInfo)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다", cooconGoodsFavoriteInfo);
	}
}
