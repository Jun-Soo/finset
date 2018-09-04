package com.koscom.goodsbank.service;

import java.util.List;

import com.koscom.domain.GoodsbankInfo;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goodsbank.model.GoodsbankForm;

public interface GoodsbankManager {
	
	/**
	 * 비제휴사 상품목록 조회(개인신용)
	 * @param goodsVO
	 * @return
	 */
	List<GoodsbankInfo> listGoodsNoAllianceCredit(GoodsbankForm goodsbankForm);

	/**
	 * 비제휴사 상품목록 카운트 조회(개인신용)
	 * @param goodsForm
	 * @return
	 */
	int listGoodsNoAllianceCreditCount(GoodsbankForm goodsbankForm);
	
	/**
	 * 비제휴사 상품목록 조회(사업자신용)
	 * @param goodsbankForm
	 * @return
	 */
	List<GoodsVO> listGoodsNoAllianceBiz(GoodsbankForm goodsbankForm);
	
	/**
	 * 비제휴사 상품목록 카운트 조회(사업자신용)
	 * @param goodsbankForm
	 * @return
	 */
	int listGoodsNoAllianceBizCount(GoodsbankForm goodsbankForm);
	
	/**
	 * 비제휴사 상품목록 조회(부동산담보)
	 * @param goodsbankForm
	 * @return
	 */
	List<GoodsVO> listGoodsNoAllianceHouse(GoodsbankForm goodsbankForm);
	
	/**
	 * 비제휴사 상품목록 카운트 조회(부동산담보)
	 * @param goodsbankForm
	 * @return
	 */
	int listGoodsNoAllianceHouseCount(GoodsbankForm goodsbankForm);

}
