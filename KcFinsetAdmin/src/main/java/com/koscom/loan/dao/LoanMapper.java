package com.koscom.loan.dao;

import java.util.List;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.domain.JobClassInfo;
import com.koscom.domain.LoanAgreeHist;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.goods.model.GoodsVO;

public interface LoanMapper {

	/**
	 * 신청정보 insert
	 * @param txFcTransmitVO
	 * @return
	 */
	int insertLoanInfo(TxFcTransmitVO txFcTransmitVO);
	/**
	 * 신청정보 상품 insert
	 * @param goodsVO
	 * @return
	 */
	int insertLoanGoodsInfo(GoodsVO goodsVO);

	/**
	 * 소득정보  update (직장인 일 경우)
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanForOffice(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 소득정보  update (사업자 일 경우)
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanForBiz(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 금융기관신청번호  update
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanForNoFcReq(TxFcTransmitVO txFcTransmitVO);
	
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
	int insertLoanAgreeHist(LoanAgreeHist loanAgreeHist);
	
	/**
	 * 소득정보  update (자동차 담보대출, 부동산 담보대출)
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanForGuarantee(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 자동차 등록 정보  update
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyCarInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 등록 정보  update
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyRealEstateInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 즐겨찾기   insert
	 * @param cooconGoodsFavoriteInfo
	 * @return
	 */
	int insertLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);
	
	/**
	 * 즐겨찾기 delete
	 * @param cooconGoodsFavoriteInfo
	 * @return
	 */
	int deleteLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);
	
	/**
	 * 부가정보  update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanAdditional(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 직장/급여  update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 자영업자  update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanEmployedInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 신청조건 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanREConditionInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 부동산 소득정보 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanREIncomeInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 
	 * 부동산 상환정보 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanRERepaymentInfo(TxFcTransmitVO txFcTransmitVO);
	
	/**
	 * 
	 * 주택 정보 update 
	 * @param txFcTransmitVO
	 * @return
	 */
	int modifyLoanREHomeInfo(TxFcTransmitVO txFcTransmitVO);
	
}
