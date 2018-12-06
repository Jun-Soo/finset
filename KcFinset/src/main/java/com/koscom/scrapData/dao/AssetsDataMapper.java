package com.koscom.scrapData.dao;

import java.util.List;

import com.koscom.scrapData.model.AssetsDataForm;
import com.koscom.scrapData.model.AssetsInfoDataVO;

public interface AssetsDataMapper {

	/**
	 * 금융사 계좌정보 스크래핑 조회
	 * @param assetsDataForm
	 * @return
	 */
	List<AssetsInfoDataVO> listScrBankApiAnInfo(AssetsDataForm assetsDataForm);

	/**
	 * 증권사 API 조회
	 * @param assetsDataForm
	 * @return
	 */
	List<AssetsInfoDataVO> listStockApiInfo(AssetsDataForm assetsDataForm);

	/**
	 * 스크래핑 데이터 기반 자산관리 merge
	 * @param assetsInfoDataVO
	 * @return int
	 */
	int mergeAssetsInfo(AssetsInfoDataVO assetsInfoDataVO);
}
