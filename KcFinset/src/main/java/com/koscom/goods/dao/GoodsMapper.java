package com.koscom.goods.dao;

import java.util.List;

import com.koscom.domain.GoodsInfo;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;

public interface GoodsMapper {
	
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
	 * 제휴사 상품목록 조회(주택담보)
	 * @param goodsVO
	 * @return
	 */
	List<GoodsVO> listGoodsAllianceHouse(GoodsForm goodsForm);
	
	/**
	 * 제휴사 상품목록 카운트 조회(주택담보)
	 * @param goodsForm
	 * @return
	 */
	int listGoodsAllianceHouseCount(GoodsForm goodsForm);
	
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
	
	/**
	 * 상품정보 조회
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getGoodsInfo(GoodsInfo goodsInfo);
	
	/**
	 * 관심상품 조회(제휴상품)
	 * @param goodsForm
	 * @return List<GoodsVO>
	 */
	List<GoodsVO> listGoodsFavoriteAlliance(GoodsForm goodsForm);

	/**
	 * 관심상품 건수(제휴상품)
	 * @param goodsForm
	 * @return int
	 */
	int getGoodsFavoriteAllianceCount(GoodsForm goodsForm);

	/**
	 * 관심상품 조회(비제휴상품)
	 * @param goodsForm
	 * @return List<GoodsVO>
	 */
	List<GoodsVO> listGoodsFavoriteNoAlliance(GoodsForm goodsForm);

	/**
	 * 관심상품 건수(비제휴상품)
	 * @param goodsForm
	 * @return int
	 */
	int getGoodsFavoriteNoAllianceCount(GoodsForm goodsForm);
	
	/**
	 * 상품정보 조회(즐겨찾기)
	 * @param GoodsVO
	 * @return
	 */
	GoodsVO getCooconGoodsFavorite(GoodsVO goodsVO);
}
