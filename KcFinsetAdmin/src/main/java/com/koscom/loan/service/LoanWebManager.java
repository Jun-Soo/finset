package com.koscom.loan.service;

import java.util.List;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.domain.JobClassInfo;
import com.koscom.domain.LoanAgreeHist;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.util.ReturnClass;

public interface LoanWebManager {

	/**
	 * 신청정보 insert
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass insertLoanInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 소득정보  update(직장인 일 경우)
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanForOffice(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 소득정보  update(사업자 일 경우)
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanForBiz(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * tx_fc_transmit 정보 select
	 * 
	 * @param no_bunch
	 * @return
	 */
	TxFcTransmitVO getTxFcTransmitInfo(String no_bunch);
	
	/**
	 * tx_fc_transmit 정보 select
	 * 
	 * @param no_bunch
	 * @return
	 */
	TxFcTransmitVO getTxFcTransmitInfoForMsg(String no_bunch);
	
	/**
	 * 직업 관련 select
	 * @param jobClassInfo
	 * @return
	 */
	List<JobClassInfo> getListJobcode(JobClassInfo jobClassInfo);
	
	/**
	 * 약관 동의 insert
	 * @param loanAgreeHist
	 * @return
	 */
	ReturnClass insertLoanAgreeHist(LoanAgreeHist loanAgreeHist);
	
	/**
	 * 소득정보  update (자동차 담보대출 , 부동산 담보대출)
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanForGuarantee(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 자동차 등록 정보  update
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyCarInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 등록 정보  update
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyRealEstateInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 즐겨찾기 insert
	 * @param insertLoanGoodsChoice
	 * @return
	 */
	ReturnClass insertLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);
	
	/**
	 * 즐겨찾기 delete
	 * @param deleteLoanGoodsChoice
	 * @return
	 */
	ReturnClass deleteLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);
	
	
	/**
	 * 부가정보  update 
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanAdditional(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 직장/급여  update 
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 자영업자  update
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanEmployedInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 신청조건 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanREConditionInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 소득정보 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanREIncomeInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 상환정보 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanRERepaymentInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 주택정보 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	ReturnClass modifyLoanREHomeInfo(TxFcTransmitVO txFcTransmitVO);
}
