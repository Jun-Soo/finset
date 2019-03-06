package com.koscom.goods.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.koscom.domain.FinsetInfo;
import com.koscom.domain.GoodsInfo;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.model.MueVO;
import com.koscom.goods.model.RtoCommissionVO;
import com.koscom.util.ReturnClass;

public interface GoodsWebManager {

	/**
	 * 상품정보 조회(캐시)
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getGoodsInfo(GoodsInfo goodsInfo);

	/**
	 * 상품정보 조회(캐시)
	 * @param cd_fc
	 * @param cd_goods
	 * @return
	 */
	GoodsVO getGoodsInfo(String cd_goods);

	/**
	 * 금융사별 상품목록 조회
	 * @param GoodsVO
	 * @return
	 */
	List<GoodsVO> listGoodsInfo(GoodsForm goodsForm);

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
	 * 제휴사 상품목록 조회(부동산담보)
	 * @param goodsVO
	 * @return
	 */
	List<GoodsVO> listGoodsAllianceHouse(GoodsForm goodsForm);

	/**
	 * 제휴사 상품목록 카운트 조회(부동산담보)
	 * @param goodsForm
	 * @return
	 */
	int listGoodsAllianceHouseCount(GoodsForm goodsForm);

	/**
	 * 상품정보 조회(캐시)
	 * @param cd_goods
	 * @param type
	 * @return
	 */
	String getGoodsDetail(String cd_goods, String type);

	/**
	 * 상품목록 조회
	 * @return
	 */
	List<GoodsVO> listCodeGoods();

	/**
	 * 상품목록 조회(검색)
	 * @param commissionForm
	 * @return
	 */
	List<GoodsVO> listGoods(GoodsForm goodsForm);

	/**
	 * 상품정보 캐시 초기화
	 * @return
	 */
	ReturnClass clearCacheGoods();

	/**
	 * 상품목록 카운트 조회
	 * @param goodsForm
	 * @return
	 */
	int listGoodsCount(GoodsForm goodsForm);

	/**
	 * 상품 상세정보 변경
	 * @param goodsVO
	 * @return
	 */
	ReturnClass modifyGoodsDetails(GoodsVO goodsVO);

	/**
	 * 상품 연동정보 변경
	 * @param goodsVO
	 * @return
	 */
	ReturnClass modifyGoodsTransmit(GoodsVO goodsVO);

	/**
	 * 상품구분 별 상품리스트 조회
	 * @param cd_goods_tyoe
	 * @return
	 */
	List<GoodsVO> listGoodsInfoBYType(String cd_goods_type);

	/**
	 * 금융사 별 상품리스트 조회
	 * @param cd_goods_type
	 * @return
	 */
	List<GoodsVO> listGoodsInfoBYComp(String cd_fc);

	/**
	 * 상품추가
	 * @param goodsVO
	 * @return
	 */
	ReturnClass createGoods(GoodsVO goodsVO);

	/**
	 * 상품코드 중복조회
	 * @param cd_goods
	 * @return
	 */
	int getGoodsCnt(String cd_goods);


	/**
	 * 매체사 노출 상품목록 조회
	 * @param GoodsForm
	 * @return
	 */
	List<GoodsVO> listAgencyGoods(GoodsForm goodsForm);

	/**
	 * 노출상품 여부 일괄 변경
	 * @param goodsVO
	 * @return
	 */
	ReturnClass procAgencyOpen(GoodsVO goodsVO);

	/**
	 * 노출상품 여부 일괄 변경
	 * @param goodsVO
	 * @return
	 */
	ReturnClass procGoodsOpen(GoodsVO goodsVO);

	/**
	 * 상품구분 별 상품리스트 조회
	 * @param cd_goods_tyoe
	 * @return
	 */
	List<GoodsVO> listGoodsOpenBYType(String id_agency, String cd_goods_type);

	/**
	 * 금융사 별 상품리스트 조회
	 * @param cd_goods_type
	 * @return
	 */
	List<GoodsVO> listGoodsOpenBYComp(String id_agency, String cd_fc);

	/**
	 * 대리점별 진행중 상품리스트 조회
	 * @param GoodsVO
	 * @return
	 */
	List<GoodsVO> listGoodsAgency(String id_agency);

	/**
	 * Mue 입력 insert
	 * @param MueVO
	 */
	void createMue(MueVO mueVO);


	/**
	 * Mue 입력 delete
	 * @param MueVO
	 */
	void delMue(MueVO mueVO);

	ReturnClass delGoodsRto(GoodsVO goodsRtoVO);

	ReturnClass createGoodsRto(GoodsVO vo);

	GoodsVO getGoodsInfo(String cd_fc, String cd_goods);

	GoodsVO getGoodsRtoInfo(String cd_fc, String cd_goods);

	/**
	 * Mue table select
	 * @param goodsInfo
	 * @return
	 */
	List<MueVO> listGoodsMue(GoodsInfo goodsInfo);

	List<GoodsVO> listYnReApply(GoodsVO goodsVO);
	GoodsVO getYnReApply(GoodsVO goodsVO);


	/**
	 * 대 중 소 분류
	 * @param GoodsForm
	 */
	public List<GoodsForm> ChangeSelectBoxLMS(GoodsForm goodsForm) throws Exception;

	/**
	 * 수수료 table select
	 * @param goodsInfo
	 * @return
	 */
	List<RtoCommissionVO> listGoodsRtoCommission(GoodsInfo goodsInfo);

	/**
	 * 수수료 입력 insert
	 * @param RtoCommissionVO
	 */
	void createRtoCommission(RtoCommissionVO rtoCommissionVO);

	/**
	 * 수수료 입력 delete
	 * @param RtoCommissionVO
	 */
	void deleteRtoCommission(RtoCommissionVO rtoCommissionVO);

	/**
	 * 금융사 정보 삭제
	 * @param GoodsVO
	 * @return
	 */
	ReturnClass deleteGoods(GoodsVO goodsVO);

	/**
	 * cfs로직
	 * @param GoodsForm
	 */
	LinkedHashMap<GoodsVO, List<FinsetInfo>> listGoodsInfoForFinset(GoodsForm goodsForm);

	/**
	 * cfs 관련 리스트
	 * @param GoodsForm
	 */
	List<GoodsVO> listGoodsInfoForCfs(GoodsForm goodsForm);

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


	String getChkAgeLoan(GoodsForm goodsForm);
	String getChkAmtApply(GoodsForm goodsForm);
	String getChkNoMonthApply(GoodsForm goodsForm);
	String getChkCdTypeIncome(GoodsForm goodsForm);
	String getChkCdEmployType(GoodsForm goodsForm);
	String getChkCdLoanUse(GoodsForm goodsForm);
	String getChkAmtYearIncome(GoodsForm goodsForm);
	String getChkAmtYearSale(GoodsForm goodsForm);
	String getChkYmdStartComp(GoodsForm goodsForm);
	String getChkNoJobYear(GoodsForm goodsForm);
	String getChkCdLiveType(GoodsForm goodsForm);
	String getChkCdHouseType(GoodsForm goodsForm);
	String getChkYnProofIncome(GoodsForm goodsForm);
	String getChkYn4Insu(GoodsForm goodsForm);
	String getChkYnBadCredit(GoodsForm goodsForm);
	String getChkYnDelayCurrent(GoodsForm goodsForm);
	String getChkDayDelay6Month(GoodsForm goodsForm);
	String getChkDayDelay12Month(GoodsForm goodsForm);
	String getChkGradeKcb(GoodsForm goodsForm);
	String getChkGradeNice(GoodsForm goodsForm);
	String getChkDtiGrade(GoodsForm goodsForm);

	/**
	 * 상품정보 조회(즐겨찾기)
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getGoodsFavorite(GoodsVO goodsVO);

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
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getCooconGoodsFavorite(GoodsVO goodsVO);
}
