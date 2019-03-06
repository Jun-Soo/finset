package com.koscom.goodsbank.service;

import java.util.List;

import com.koscom.domain.GoodsbankInfo;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goodsbank.model.CooconChangeInfoFormVO;
import com.koscom.goodsbank.model.CooconChangeInfoVO;
import com.koscom.goodsbank.model.CooconGoodsBankInfoForm;
import com.koscom.goodsbank.model.CooconGoodsBankInfoVO;
import com.koscom.goodsbank.model.GoodsStatsFormVO;
import com.koscom.goodsbank.model.GoodsStatsVO;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.util.ReturnClass;

public interface GoodsbankManager {
	
	/**
	 * 링크 카운트 추가
	 * 
	 * @param goodsbankVO
	 */
	ReturnClass addLinkCount(GoodsbankVO goodsbankVO);
	
	/**
	 * 금융사리스트 / 상품관리 / 상품현황
	 * @param GoodsbankForm goodsbankForm
	 * @param model
	 * @return
	 */
	List<GoodsbankVO> listGoodsbankInfo(GoodsbankForm goodsbankForm);
	int listGoodsbankCount(GoodsbankForm goodsbankForm);
	GoodsbankVO getGoodsbankInfo(String cd_fc, String cd_goods);

	/**
	 * 대 중 소 분류
	 * @param GoodsForm
	 */
	public List<GoodsbankForm> ChangeSelectBoxLMS(GoodsbankForm goodsbankForm) throws Exception;
	
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
	
	/**
	 * 비제휴사 상품정보 조회(즐겨찾기)
	 * @param goodsbankVO
	 * @return
	 */
	GoodsbankVO getGoodsBankFavorite(GoodsbankVO goodsbankVO);
	
	
	
	
	/** srchou */
	/**
	 * 상품관리 / 상품상세정보 입력
	 * @param CooconGoodsBankInfoForm
	 * @return String
	 */
	String createGoodsbank(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * 상품관리 / 상품상세정보 수정
	 * @param CooconGoodsBankInfoForm
	 * @return String
	 */
	String modifyGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * 상품관리 / 상품상세정보 삭제
	 * @param CooconGoodsBankInfoForm
	 * @return String
	 */
	String delGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * 상품관리 / 상품별 현황
	 * @param CooconChangeInfoFormVO
	 * @return List<CooconChangeInfoVO>
	 */
	List<CooconChangeInfoVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 상품관리 / 상품별 현황 수
	 * @param CooconChangeInfoFormVO
	 * @return int
	 */
	int listcooconChangeInfoCount(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 상품관리 / 전체 현황
	 * @param GoodsStatsFormVO
	 * @return List<GoodsStatsVO>
	 */
	List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO);
	
	/**
	 *  상품관리 / 전체 현황 수
	 * @param GoodsStatsFormVO
	 * @return int
	 */
	int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO);
	
	/**
	 * 상품관리 / 변경된 정보 / 변경 전
	 * @param CooconChangeInfoFormVO
	 * @return CooconChangeInfoVO
	 */
	CooconChangeInfoVO pastChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 상품관리 / 변경된 정보 / 변경 후
	 * @param CooconChangeInfoFormVO
	 * @return CooconChangeInfoVO
	 */
	CooconChangeInfoVO currentChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO);
	
	/**
	 * 상품관리 / 상품상세정보 가져오기
	 * @param CooconGoodsBankInfoForm
	 * @return list CooconGoodsBankInfoVO
	 */
	CooconGoodsBankInfoVO setGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * 금융사리스트 / 상품관리 / 상품현황
	 * @param CooconGoodsBankInfoForm
	 * @return List<CooconGoodsBankInfoVO>
	 */
	List<CooconGoodsBankInfoVO> listGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * 금융사리스트 / 상품관리 / 상품현황 수
	 * @param CooconGoodsBankInfoForm
	 * @return CooconGoodsBankInfoVO
	 */
	int listGoodsbankInfoUseCount(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);
	
	/**
	 * 금융사리스트 / 상품관리 / 상품 상세정보 가져오기
	 * @param CooconGoodsBankInfoForm
	 * @return CooconGoodsBankInfoVO
	 */
	CooconGoodsBankInfoVO setGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

}
