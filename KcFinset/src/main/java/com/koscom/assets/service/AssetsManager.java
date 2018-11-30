package com.koscom.assets.service;

import java.util.List;

import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.util.ReturnClass;

public interface AssetsManager {

	/**
	 * 자산 메인
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsMainInfo(AssetsForm assetsForm);

	/**
	 * 자산 은행메인 - 최근 입출금 내역
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsBankDepWdrlInfo(AssetsForm assetsForm);

	/**
	 * 자산 은행 메인 - 공유자list
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsSharePerson(AssetsForm assetsForm);

	/**
	 * 자산 은행 메인 - 계좌내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsBankAccount(AssetsForm assetsForm);
	public int listAssetsBankAccountCount(AssetsForm assetsForm);

	/**
	 * 자산 은행 메인 - 정렬순서변경
	 * @param assetsInfoVO
	 * @return void
	 */
	public void updateAssetsSortInfo(AssetsInfoVO assetsInfoVO);

	/**
	 * 자산 개별정보
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsInfo(AssetsForm assetsForm);

	/**
	 * 자산 은행 - 검색키워드list
	 * @param AssetsForm
	 * @return List<String>
	 */
	public List<String> listAssetsSearchKeyword(AssetsForm assetsForm);

	/**
	 * 자산 은행 계좌상세 - 입출금내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsBankActTrns(AssetsForm assetsForm);
	public int listAssetsBankActTrnsCount(AssetsForm assetsForm);

	/**
	 * 자산 - 계좌list
	 * @param String
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsAccount(String no_person);

	/**
	 * 자산 - 입출금내역
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsBankDepWdrlTotalAmt(AssetsForm assetsForm);
	public List<AssetsInfoVO> listAssetsBankDepWdrl(AssetsForm assetsForm);
	public int listAssetsBankDepWdrlCount(AssetsForm assetsForm);

	/**
	 * 자산 - 입출금상세
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsBankDepWdrlDetail(AssetsForm assetsForm);

	/**
	 * 자산 - 입출금상세(소비정보)
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsDetailCsInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 입출금상세(소비정보 업데이트)
	 * @param AssetsInfoVO
	 * @return int
	 */
	public int updateAssetsDetailCsInfo(AssetsInfoVO assetsInfoVO);

	/**
	 * 자산 - 직접입력
	 * @param AssetsInfoVO
	 * @return ReturnClass
	 */
	public ReturnClass createAssetsInfo(AssetsInfoVO assetsInfoVO);

	/**
	 * 자산 - 기타메인
	 * @param String no_person
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsEtcMain(String no_person);


}
