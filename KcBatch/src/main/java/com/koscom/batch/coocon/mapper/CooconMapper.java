package com.koscom.batch.coocon.mapper;

import java.util.HashMap;
import java.util.List;

import com.koscom.batch.coocon.domain.CooconAPIinfoVO;
import com.koscom.batch.coocon.domain.CooconVO;

public interface CooconMapper {

	/**
	 * COOCON_API_INFO DATA 가져오기
	 * API에 전달할 PARAM DATA 져오기
	 * @param
	 * @return List<CooconAPIinfoVO>
	 */
	List<CooconAPIinfoVO> getCooconAPIinfo();

	/**
	 * @param CooconAPIinfoVO
	 * @return String
	 */
	String getCdcoocongoods(CooconAPIinfoVO cooconApiInfoVO);

	//coocon_goods_info 데이터 insert
	/**
	 * COOCON_GOODS_INFO의 cd_non_goods값 조회
	 * @param CooconVO
	 * @return String
	 */
	String getCooconGoodsInfoCdNongoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_INFO DATA 추가
	 * API로 가져온 DATA COOCON_GOODS_INFO TABLE에 INSERT
	 * @param CooconVO
	 * @return int
	 */
	int setCooconGoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_INFO DATA 상품별 CHECK
	 * @param CooconVO
	 * @return int
	 */
	int chkCooconGoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 DATA 유무 확인
	 * @param CooconVO
	 * @return int
	 */
	int chkCooconGoodsChangeInfo(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 CHECK 후 DATA 없을 경우 INSERT
	 * @param CooconVO
	 * @return int
	 */
	int insCooconChangeinfo(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_INFO의 데이터가 3건이상일 경우 min_seq 데이터 삭제
	 * @param CooconVO
	 * @return int
	 */
	int delCooconGoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO변경정보 UPDATE
	 * @param CooconVO
	 * @return int
	 */
	int updCooconChangeinfo(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 DATA 변경 건수 확인
	 * @param CooconVO
	 * @return int
	 */
	int chkChangeValue(CooconVO cooconVO);

	/**
	 * cd_non_goods값으로 GOODSBANK_INFO 데이터 조회
	 * @param cd_non_goods
	 * @return int
	 */
	int getCdnongoodsGoodsbankCnt(String cd_non_goods);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 STATUS UPDATE
	 * @param CooconVO
	 * @return int
	 */
	int updStatusCooconChangeinfo(CooconVO cooconVO);

	/**
	 * 쿠콘 상품 데이터 삭제
	 * @param
	 * @return int
	 */
	int delCoocongoodsinfo();

	/**
	 * 쿠콘 상품 데이터 삭제로 인한 COOCON_GOODS_CHANGE_INFO STATS update
	 * @param CooconVO
	 * @return int
	 */
	int delCoocongoodchangeinfo(CooconVO cooconVO);

	/**
	 * 쿠콘 상품 데이터 삭제로 인한 goodsbank_info의 yn_use(표시여부) update
	 * @param CooconVO
	 * @return int
	 */
	int updYnUseGoodsbankInfo(CooconVO cooconVO);
	//coocon_goods_info 데이터 insert

}
