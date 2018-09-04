package com.koscom.loan.dao;

import java.util.List;

import com.koscom.domain.CooconGoodsFavoriteInfo;

public interface LoanMapper {

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
}
