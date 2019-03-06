package com.koscom.coocongoodsbank.service;

import com.koscom.coocongoodsbank.model.*;

import java.util.List;

public interface CooConGoodsbankWebManager {
	
	/**
	 * 전체현황 리스트
	 * @param 
	 * @return list GoodsStats
	 */
	List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO);
	
	/**
	 * 전체현황 리스트 총 갯수
	 * @param 
	 * @return int
	 */
	int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO);
	
	/**
	 * 상품 추가
	 * @param goodsbankVO
	 */
	String createGoodsbank(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 상품 상세정보 변경
	 * @param goodsbankVO
	 * @return
	 */
	String modifyGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 상품 정보 삭제
	 * @param goodsbankVO
	 * @return
	 */
	String delGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm);
	
	/**
	 * 상품별 현황 조회
	 * @param cooconChangeInfoFormVO
	 * @return list cooconChangeInfoFormVO
	 */
	List<CooconChangeInfoFormVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 상품별 현황 총 갯수 조회
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
	 * COOCON_GOODS_INFO에서 GOOD BANK INFO 상품정보 set data
	 * @param 
	 * @return list GoodsStats
	 */
	CooconGoodsBankInfoVO setGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

}