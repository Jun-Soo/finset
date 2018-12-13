package com.koscom.goods.service;

import java.util.List;

import com.koscom.domain.FinsetInfo;
import com.koscom.domain.GoodsInfo;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;

public interface GoodsManager {
	/**
	 * 제휴사 상품목록 조회(전체)
	 * @param GoodsForm
	 * @return GoodsVO
	 */
	List<GoodsVO> listGoodsAllianceAll(GoodsForm goodsForm);
	
	/**
	 * 제휴사 상품목록 조회(개인신용)
	 * @param GoodsForm
	 * @return GoodsVO
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
	 * @param GoodsForm
	 * @return GoodsVO
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
	 * @param GoodsForm
	 * @return GoodsVO
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
	 * @param GoodsVO
	 * @return GoodsVO
	 */
	GoodsVO getGoodsFavorite(GoodsVO goodsVO);
	
	/**
	 * 상품정보 조회(캐시)
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getGoodsInfo(GoodsInfo goodsInfo);
	GoodsVO getGoodsInfo(String cd_goods);
	GoodsVO getGoodsInfo(String cd_fc, String cd_goods);
	
	/**
	 * 상품정보 조회(캐시)
	 * @param cd_goods
	 * @param type
	 * @return
	 */
	String getGoodsDetail(String cd_goods, String type);

	/**
	 * 관심상품 조회(제휴상품)
	 * @param goodsForm
	 * @return
	 */
	List<GoodsVO> listGoodsFavoriteAlliance(GoodsForm goodsForm);

	/**
	 * 관심상품 건수(제휴상품)
	 * @param goodsForm
	 * @return
	 */
	int getGoodsFavoriteAllianceCount(GoodsForm goodsForm);

	/**
	 * 관심상품 조회(비제휴상품)
	 * @param goodsForm
	 * @return
	 */
	List<GoodsVO> listGoodsFavoriteNoAlliance(GoodsForm goodsForm);

	/**
	 * 관심상품 건수(비제휴상품)
	 * @param goodsForm
	 * @return
	 */
	int getGoodsFavoriteNoAllianceCount(GoodsForm goodsForm);
	
	/**
	 * 쿠콘상품정보 조회(즐겨찾기)
	 * @param GoodsVO
	 * @return
	 */
	GoodsVO getCooconGoodsFavorite(GoodsVO goodsVO);
	
	
	
	
	/**
	 * 나이체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkAge(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 연소득 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkAmtYearIncome(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO);

	/**
	 * 상품판매유무
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkGoodsUseYn(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO);

	/**
	 * 신청금액 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkAmtApply 		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 연매출액 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkAmtYearSale 	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 고용형태 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCdEmployType	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 주거종류 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCdHouseType		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 주거소유형태 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCdLiveType		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 자금용도 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCdLoanUse		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 소득형태체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCdTypeIncome	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 연체여부(최근1년) 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkDayDelay12month	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 연체 여부(최근 6개월) 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkDayDelay6month	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * DTI_GRADE 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkDtiGrade		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * DTI 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkGradeDti		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 신용등급 (KCB)
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkGradeKcb		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * LTV 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkGradeLtv		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 신용등급 (NICE)
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkGradeNice		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 근속 연수 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkNoJobYear		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 신청기간(개월) 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkNoMonthApply	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 입사일자 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkYmdStartComp	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 4대 보험 가입여부 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkYn4insu			(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 신용관리정보등재자 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkYnBadCredit		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 연체 여부(현재) 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkYnDelayCurrent	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 소득증빙여부 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkYnProofIncome	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 성별 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCd_sex        (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 현금서비스(건) 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCash_service  (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 담보소유형태차종류 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCd_owncar_type(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 담보종류 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkCd_car_type   (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 차량가격 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkAmt_carprice  (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);

	/**
	 * 차량연식 체크
	 * @param TxFcTransmitVO, GoodsVO, List<FinsetInfo>, String, FinsetDenyVO
	 * @return String
	 */
	String checkNo_caryear    (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO);
}
