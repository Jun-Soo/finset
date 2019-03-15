package com.koscom.coocon.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.coocon.model.CooconAPIinfoFormVO;
import com.koscom.coocon.model.CooconAPIinfoVO;
import com.koscom.coocon.model.CooconChangeVO;
import com.koscom.coocon.model.CooconVO;

public interface CooconMapper {

	/*	setCooconGoods start	*/

	/**
	 * COOCON_GOODS_INFO의 cd_non_goods값 조회
	 * @param cooconVO
	 * @return String
	 */
	String getCooconGoodsInfoCdNongoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_INFO DATA 추가
	 * API로 가져온 DATA COOCON_GOODS_INFO TABLE에 INSERT
	 * @param List<CooconVO>
	 * @return int
	 */
	int setCooconGoods(CooconVO cooconVO);

	/**
	 * @param List<CooconVO>
	 * @return String
	 */
	String getCdcoocongoods(HashMap<String, String> map);

	/**
	 * COOCON_GOODS_INFO의 데이터가 3건이상일 경우 min_seq 데이터 삭제
	 * @param CooconVO
	 * @return int
	 */
	int delCooconGoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_INFO DATA 상품별 CHECK
	 * @param cooconVO
	 * @return int
	 */
	int chkCooconGoods(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 CHECK 후 DATA 없을 경우 INSERT
	 * @param cooconVO
	 * @return int
	 */
	int insCooconChangeinfo(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO변경정보 UPDATE
	 * @param cooconVO
	 * @return int
	 */
	int updCooconChangeinfo(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 STATUS UPDATE
	 * @param cooconVO
	 * @return int
	 */
	int updStatusCooconChangeinfo(CooconVO cooconVO);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 DATA 변경 건수 확인
	 * @param cooconVO
	 * @return
	 */
	int chkChangeValue(CooconVO cooconVO);

	/**
	 * cd_non_goods값으로 GOODSBANK_INFO 데이터 조회
	 * @param cd_non_goods
	 * @return int
	 */
	int getCdnongoodsGoodsbankCnt(String cd_non_goods);

	/**
	 * COOCON_GOODS_CHANGE_INFO DATA 상품별 DATA 유무 확인
	 * @param CooconVO
	 * @return int
	 */
	int chkCooconGoodsChangeInfo(CooconVO cooconVO);

	/**
	 * 쿠콘 상품 데이터 삭제
	 * @param cooconVO
	 * @return int
	 */
	int delCoocongoodsinfo();

	/**
	 * 쿠콘 상품 데이터 삭제로 인한 COOCON_GOODS_CHANGE_INFO STATS update
	 * @param cooconVO
	 * @return
	 */
	int delCoocongoodchangeinfo(CooconVO cooconVO);

	/**
	 * 쿠콘 상품 데이터 삭제로 인한 goodsbank_info의 yn_use(표시여부) update
	 * @param cooconVO
	 * @return
	 */
	int updYnUseGoodsbankInfo(CooconVO cooconVO);

/*	setCooconGoods end	*/

	/**
	 * COOCON_API_INFO DATA 가져오기
	 * API에 전달할 PARAM DATA 져오기
	 * @param
	 * @return List<CooconAPIinfoVO>
	 */
	List<CooconAPIinfoVO> getCooconAPIinfo();

	/**
	 * 쿠콘 API 상품정보의 MIN SEQ 가져오기
	 * @param cooconChangeVO
	 * @return
	 */
	int getSeq(String cd_coocon_goods);


	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 조회
	 * @param cooconAPIinfoFormVO
	 * @return List<CooconAPIinfoVO>
	 */
	List<CooconAPIinfoVO> listcooconApiInfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 조회 수
	 * @param CooconAPIinfoFormVO
	 * @return int
	 */
	int listcooconApiInfoCount(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 추가
	 * @param CooconAPIinfoFormVO
	 * @return String
	 */
	int createcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 추가시 중복체크
	 * @param CooconAPIinfoFormVO
	 * @return
	 */
	int duplcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 수정
	 * @param CooconAPIinfoFormVO
	 * @return String
	 */
	int updcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 삭제
	 * @param CooconAPIinfoFormVO
	 * @return String
	 */
	int delcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 cd_fc로 중복체크
	 * @param CooconAPIinfoFormVO
	 * @return
	 */
	int duplcooconAPIFcInfo(String cd_fc);

}
