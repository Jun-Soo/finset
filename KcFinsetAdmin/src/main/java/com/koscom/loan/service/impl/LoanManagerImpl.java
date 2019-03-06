package com.koscom.loan.service.impl;

import java.util.List;

import com.koscom.goods.model.GoodsVO;
import com.koscom.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.domain.JobClassInfo;
import com.koscom.domain.LoanAgreeHist;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finance.service.FinanceManager;
import com.koscom.loan.dao.LoanMapper;
import com.koscom.loan.service.LoanManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("loanManager")
public class LoanManagerImpl implements LoanManager{
    private static final Logger logger = LoggerFactory.getLogger(LoanManagerImpl.class);

	@Autowired
	LoanMapper loanMapper;

	@Autowired
	FinanceManager financeManager;

	@Override
	public ReturnClass insertLoanInfo(TxFcTransmitVO txFcTransmitVO) {
        LogUtil.debugLn(logger, "LoanManagerImpl.insertLoanInfo:txFcTransmitVO="+txFcTransmitVO);
        ReturnClass resultClass = null;
        int result = loanMapper.insertLoanInfo(txFcTransmitVO);
		List<GoodsVO> listGoods = null; //대출신청상품정보
        GoodsVO goodsVO = null;
        String no_bunch = null;
		listGoods = txFcTransmitVO.getListGoods();
		LogUtil.debugLn(logger, "LoanManagerImpl.insertLoanInfo:listGoods="+listGoods);
		if(listGoods != null && listGoods.size()>0) {
			no_bunch = txFcTransmitVO.getNo_bunch();
            LogUtil.debugLn(logger, "LoanManagerImpl.insertLoanInfo:no_bunch=" + no_bunch);
			for(int i=0;i<listGoods.size();i++) {
				goodsVO = listGoods.get(i);
				goodsVO.setNo_bunch(no_bunch);
        		result+= loanMapper.insertLoanGoodsInfo(goodsVO);
			}
		}
//        if(1 != result) {
//            resultClass = new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
//		} else {
        resultClass = new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다", txFcTransmitVO);
//        }
        LogUtil.debugLn(logger, "LoanManagerImpl.insertLoanInfo:resultClass="+resultClass);
        return resultClass;
	}

	@Override
	public ReturnClass modifyLoanForOffice(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanForOffice(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanForBiz(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanForBiz(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public TxFcTransmitVO getTxFcTransmitInfo(String no_bunch) {
		return loanMapper.getTxFcTransmitInfo(no_bunch);
	}

	@Override
	public TxFcTransmitVO getTxFcTransmitInfoForMsg(String no_bunch) {
		return loanMapper.getTxFcTransmitInfoForMsg(no_bunch);
	}

	@Override
	public List<JobClassInfo> getListJobcode(JobClassInfo jobClassInfo) {
		return loanMapper.getListJobcode(jobClassInfo);
	}

	@Override
	public ReturnClass insertLoanAgreeHist(LoanAgreeHist loanAgreeHist) {
		if(1 != loanMapper.insertLoanAgreeHist(loanAgreeHist)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanForGuarantee(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanForGuarantee(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}
	@Override
	public ReturnClass modifyCarInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyCarInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyRealEstateInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyRealEstateInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

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

	@Override
	public ReturnClass modifyLoanAdditional(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanAdditional(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanForNoFcReq(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanForNoFcReq(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanEmployedInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanEmployedInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanREConditionInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanREConditionInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanREIncomeInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanREIncomeInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanRERepaymentInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanRERepaymentInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public ReturnClass modifyLoanREHomeInfo(TxFcTransmitVO txFcTransmitVO) {
		if(1 != loanMapper.modifyLoanREHomeInfo(txFcTransmitVO)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

}
