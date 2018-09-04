package com.koscom.loan.service;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.util.ReturnClass;

public interface LoanManager {
	
	/**
	 * 즐겨찾기 insert
	 * @param CooconGoodsFavoriteInfo
	 * @return ReturnClass
	 */
	ReturnClass insertLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);

	/**
	 * 즐겨찾기 delete
	 * @param CooconGoodsFavoriteInfo
	 * @return ReturnClass
	 */
	ReturnClass deleteLoanGoodsChoice(CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo);

}
