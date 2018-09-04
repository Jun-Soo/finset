package com.koscom.goods.service;

import java.util.List;

import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;

public interface GoodsManager {
	
	/**
	 * 제휴사 상품목록 조회(개인신용)
	 * @param goodsVO
	 * @return
	 */
	List<GoodsVO> listGoodsAllianceCredit(GoodsForm goodsForm);
	
	/**
	 * 제휴사 상품목록 카운트 조회(개인신용)
	 * @param goodsForm
	 * @return
	 */
	int listGoodsAllianceCreditCount(GoodsForm goodsForm);
	
	/**
	 * 제휴사 상품목록 조회(사업자신용)
	 * @param goodsVO
	 * @return
	 */
	List<GoodsVO> listGoodsAllianceBiz(GoodsForm goodsForm);

	/**
	 * 제휴사 상품목록 카운트 조회(사업자신용)
	 * @param goodsForm
	 * @return
	 */
	int listGoodsAllianceBizCount(GoodsForm goodsForm);
	
	/**
	 * 상품정보 조회(즐겨찾기)
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getGoodsFavorite(GoodsVO goodsVO);

}
