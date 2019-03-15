package com.koscom.coocon.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.koscom.coocon.model.CooconAPIinfoFormVO;
import com.koscom.coocon.model.CooconAPIinfoVO;
import com.koscom.coocon.model.CooconChangeVO;
import com.koscom.coocon.model.CooconVO;

@Service("cooconManager")
public interface CooconManager {

	/**
	 * @param List<CooconVO>
	 * @return String
	 */
	String getCdcoocongoods(String nm_coocon_goods, String cd_org);

	/**
	 * COOCON_GOODS_INFO DATA 추가
	 * API로 가져온 DATA COOCON_GOODS_INFO TABLE에 INSERT
	 * @param List<CooconVO>
	 * @return int
	 */
	int setCooconGoods(List<CooconVO> listCooconVO) throws UnsupportedEncodingException;

	/**
	 * COOCON_API_INFO DATA 가져오기
	 * API에 전달할 PARAM DATA 져오기
	 * @param
	 * @return List<CooconAPIinfoVO>
	 */
	List<CooconAPIinfoVO> getCooconAPIinfo();

	/**
	 * COOCON_GOODS_INFO SEQ  값 가져오기
	 * API로 가져온 DATA COOCON_GOODS_INFO TABLE에 INSERT 하기전 SEQ 가져오기
	 * @param cd_coocon_goods
	 * @return int
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
	String createcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 수정
	 * @param CooconAPIinfoFormVO
	 * @return String
	 */
	String updcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 삭제
	 * @param CooconAPIinfoFormVO
	 * @return String
	 */
	String delcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO);

}