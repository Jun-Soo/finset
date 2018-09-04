package com.koscom.finset.service;

import java.util.List;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.model.FinsetForm;

public interface FinsetManager {

	/**
	 * 상품조회결과 조회
	 * @param
	 * @return String
	 */
	List<TxFcReceiveVO> listSearchGoods(FinsetForm finsetForm);

	/**
	 * 상품조회결과 조회 건수
	 * @param 
	 * @return String
	 */
	int listSearchGoodsCount(FinsetForm finsetForm);
}
