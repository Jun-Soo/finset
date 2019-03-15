package com.koscom.goodsbank.dao;

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

public interface GoodsbankMapper {

	/**
	 * 링크 카운트 추가
	 * 
	 * @param goodsbankVO
	 */
	void addLinkCount(GoodsbankVO goodsbankVO);
	
	/**
	 * 금융사리스트 / 상품관리 / 상품현황
	 * @param GoodsbankForm goodsbankForm
	 * @param model
	 * @return
	 */
	List<GoodsbankVO> listGoodsbankInfo(GoodsbankForm goodsbankForm);
	int listGoodsbankCount(GoodsbankForm goodsbankForm);
	GoodsbankVO getGoodsbankInfo(GoodsbankInfo goodsbankInfo);

	/**
	 * 대 중 소 분류
	 * @param goodsbankForm
	 */
	List<GoodsbankForm> ChangeSelectBoxLMS(GoodsbankForm goodsbankForm);

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


	/** srchou */
	/**
	 * 금융사코드 / 대분류 / 중분류로 cd_non_goods seq값 가져오기
	 * @param cooconGoodsBankInfoForm
	 * @return String
	 */
	String getGoodsbankCdNonGoodsSeq(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품상세정보 등록시 중복여부 CHECK
	 * @param cd_non_goods
	 * @return int
	 */
	int getGoodsbankCnt(String cd_non_goods);

	/**
	 * 상품관리 / 상품상세정보 입력
	 * @param CooconGoodsBankInfoForm
	 * @return int
	 */
	int createGoodsbank(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품상세정보 수정
	 * @param CooconGoodsBankInfoForm
	 * @return int
	 */
	int modifyGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품상세정보 삭제
	 * @param CooconGoodsBankInfoForm
	 * @return int
	 */
	int delGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품상세정보 등록, 수정, 삭제시 cd_coocon_goods값 조회
	 * @param cd_non_goods
	 * @return String
	 */
	CooconGoodsBankInfoVO getCooconGoodsInfoByCdNonGoods(String cd_non_goods);

	/**
	 * 상품관리 / 상품상세정보 삭제, 등록시 COOCON_GOODS_INFO 의 CD_NON_GOODS 값 UPDATE
	 * @param CooconGoodsBankInfoForm
	 * @return int
	 */
	int updateCdnongoods(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 비제휴 상품 정보 삭제,변경,삭제에 따른 COOCON_GOODS_INFO 없데이트
	 * @param CooconGoodsBankInfoForm
	 * @return
	 */
	int updateYnuse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품별 현황
	 * @param CooconChangeInfoFormVO
	 * @return List<CooconChangeInfoVO>
	 */
	List<CooconChangeInfoVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO);

	/**
	 * 상품관리 / 상품별 현황 수
	 * @param cooconChangeInfoFormVO
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
	 * 상품관리 / 전체 현황 수
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
	 * 상품관리 / 상품상세정보 가져오는 기준(cd_non_goods)
	 * @param cooconGoodsBankInfoForm
	 * @return int
	 */
	String chkcdNongoods(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품상세정보 GOODSBANK_INFO 가져오기
	 * @param cooconGoodsBankInfoForm
	 * @return CooconGoodsBankInfoVO
	 */
	CooconGoodsBankInfoVO getGoodsbankDetailInfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 상품관리 / 상품상세정보 COOCON_GOODS_INFO 가져오기
	 * @param cooconGoodsBankInfoForm
	 * @return CooconGoodsBankInfoVO
	 */
	CooconGoodsBankInfoVO getCoocongoodsDetailInfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 금융사리스트 / 상품관리 / 상품현황
	 * @param CooconGoodsBankInfoForm
	 * @return List<CooconGoodsBankInfoVO>
	 */
	List<CooconGoodsBankInfoVO> listGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 금융사리스트 / 상품관리 / 상품현황 수
	 * @param CooconGoodsBankInfoForm
	 * @return int
	 */
	int listGoodsbankInfoUseCount(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

	/**
	 * 금융사리스트 / 상품관리 / 상품 상세정보 가져오기
	 * @param CooconGoodsBankInfoForm
	 * @return CooconGoodsBankInfoVO
	 */
	CooconGoodsBankInfoVO setGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm);

}
