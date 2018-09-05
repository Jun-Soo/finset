package com.koscom.finset.dao;

import java.util.List;

import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;

public interface FinsetMapper {
	
	/**
	 * 상품조회결과 조회
	 * @param FinsetForm
	 * @return TxFcReceiveVO
	 */
	List<TxFcReceiveVO> listSearchGoods(FinsetForm finsetForm);

	/**
	 * 상품조회결과 조회 건수
	 * @param FinsetForm
	 * @return int
	 */
	int listSearchGoodsCount(FinsetForm finsetForm);
	
	/**
	 * 조회중 건수
	 * @param FinsetVO
	 * @return int
	 */
	int countLoading(FinsetVO vo);
}