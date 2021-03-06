package com.koscom.goodsbank.dao;

import java.util.List;

import com.koscom.domain.GoodsbankInfo;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;

public interface GoodsbankMapper {
	/**
	 * 비제휴사 상품목록 조회(개인신용)
	 * @param goodsbankForm
	 * @return
	 */
	List<GoodsbankInfo> listGoodsNoAllianceCredit(GoodsbankForm goodsbankForm);

	/**
	 * 비제휴사 상품목록 카운트 조회(개인신용)
	 * @param goodsbankForm
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
	
	/**
	 * 비제휴사 상품정보 조회(즐겨찾기)
	 * @param goodsbankVO
	 * @return
	 */
	GoodsbankVO getGoodsBankFavorite(GoodsbankVO goodsbankVO);
	
	/**
	 * 링크 카운트 추가
	 * @param goodsbankVO
	 * @return
	 */
	void addLinkCount(GoodsbankVO goodsbankVO);
}
