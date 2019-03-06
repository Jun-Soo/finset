package com.koscom.coocongoodsbank.dao;

import com.koscom.coocongoodsbank.model.*;

import java.util.List;

public interface CooConGoodsbankMapper {

    /**
	 * 상품코드 중복조회
	 * @param cd_goods
	 * @return
	 */
	int getGoodsbankCnt(String cd_non_goods);
			
	/**
	 * 상품 추가
	 * @param goodsVO
	 */
	int createGoodsbank(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 상품 상세정보 변경
	 * @param goodsVO
	 * @return
	 */
	int modifyGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 상품 정보 삭제
	 * @param goodsbankVO
	 * @return
	 */
	int delGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 비제휴 상품 정보 삭제,변경,삭제에 따른 COOCON_GOODS_INFO 없데이트
	 * @param goodsbankVO
	 * @return
	 */
	int updateCdnongoods(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 비제휴 상품 정보 삭제,변경,삭제에 따른 COOCON_GOODS_INFO 없데이트
	 * @param goodsbankVO
	 * @return
	 */
	int updateYnuse(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 변경 통계 현황 조회
	 * @param 
	 * @return list GoodsStats
	 */
	List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO);
	
	/**
	 * 변경 통계 현황 조회
	 * @param 
	 * @return list GoodsStats
	 */
	int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO);
	
	/**
	 * 상품별 현황 조회
	 * @param cooconChangeInfoFormVO
	 * @return list cooconChangeInfoVO
	 */
	List<CooconChangeInfoFormVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 상품별 현황 카운트 조회
	 * @param cooconChangeInfoFormVO
	 * @return int
	 */
	int listcooconChangeInfoCount(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 과거 변경 data
	 * @param 
	 * @return list GoodsStats
	 */
	CooconChangeInfoVO pastChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 현재 변경 data
	 * @param 
	 * @return list GoodsStats
	 */
	CooconChangeInfoVO currentChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * cd_non_goods data 등록여부
	 * @param 
	 * @return list GoodsStats
	 */
	int chkcdNongoods(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * cd_non_goods data null 인 경우 data
	 * @param 
	 * @return list GoodsStats
	 */
	CooconGoodsBankInfoVO getCoocongoodsinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * cd_non_goods data null이 아닌 경우 data
	 * @param 
	 * @return list GoodsStats
	 */
	CooconGoodsBankInfoVO getGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

}
