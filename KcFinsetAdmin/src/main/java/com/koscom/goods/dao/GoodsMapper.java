package com.koscom.goods.dao;

import java.util.List;

import com.koscom.domain.GoodsInfo;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.model.MueVO;
import com.koscom.goods.model.RtoCommissionVO;

public interface GoodsMapper {

	/**
	 * 상품정보 조회
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getGoodsInfo(GoodsInfo goodsInfo);

	/**
	 * 금융사별 상품목록 조회
	 * @param goodsVO
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
	 * 상품코드, 상품명 가져옴
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
	int modifyGoodsDetails(GoodsVO goodsVO);

	/**
	 * 상품 연동정보 변경
	 * @param goodsVO
	 * @return
	 */
	int modifyGoodsTransmit(GoodsVO goodsVO);

	/**
	 * 상품구분별 상품리스트 조회
	 * @param goodsForm
	 * @return
	 */
	List<GoodsVO> listGoodsInfoBYType(GoodsForm goodsForm);

	/**
	 * 금융사별 상품리스트 조회
	 * @param goodsForm
	 * @return
	 */
	List<GoodsVO> listGoodsInfoBYComp(GoodsForm goodsForm);

	/**
	 * 상품코드로 상품정보 조회
	 * @param cd_goods
	 * @return
	 */
	GoodsVO getGoodsCode(String cd_goods);

	/**
	 * 상품 추가
	 * @param goodsVO
	 */
	void createGoods(GoodsVO goodsVO);

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
	 * 노출상품 여부 변경
	 * @param goodsVO
	 */
	int procAgencyGoods(GoodsVO goodsVO);

	/**
	 * 상품구분별 상품리스트 조회
	 * @param goodsForm
	 * @return
	 */
	List<GoodsVO> listGoodsOpenBYType(GoodsForm goodsForm);

	/**
	 * 금융사별 상품리스트 조회
	 * @param goodsForm
	 * @return
	 */
	List<GoodsVO> listGoodsOpenBYComp(GoodsForm goodsForm);

	/**
	 * 대리점별 진행중 상품리스트 조회
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
	int delMue(MueVO mueVO);

	void delGoodsRto(GoodsVO goodsVO);

	int createGoodsRto(GoodsVO goodsVO);

	GoodsVO getGoodsRtoInfo(GoodsInfo goodsInfo);
	List<GoodsVO> listGoodsInfoForFinset(GoodsForm goodsForm);
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
	List<GoodsForm> ChangeSelectBoxLMS(GoodsForm goodsForm);

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
	void createRtoCommission(RtoCommissionVO rtocommissionVO);

	/**
	 * 수수료 입력 delete
	 * @param RtoCommissionVO
	 */
	void deleteRtoCommission(RtoCommissionVO rtocommissionVO);


	/**
	 * 상품 정보 삭제
	 * @param GoodsVO
	 * @return
	 */
	int deleteGoods(GoodsVO goodsVO);

	/**
	 * cfs 리스트
	 * @param GoodsForm
	 */
	List<GoodsVO> listGoodsInfoForCfs(GoodsForm goodsForm);
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
	 * @param goodsInfo
	 * @return
	 */
	GoodsVO getCooconGoodsFavorite(GoodsVO goodsVO);
}
