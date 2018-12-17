package com.koscom.assets.dao;

import java.util.List;

import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;
import com.koscom.consume.model.ConsumeVO;

public interface AssetsMapper {

	/**
	 * 자산 - 메인
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsMainInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 메인 최근 입출금 내역
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsBankDepWdrlInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 메인 공유자list
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public List<AssetsInfoVO> listAssetsSharePerson(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 메인 계좌내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsBankAccount(AssetsForm assetsForm);
	public int listAssetsBankAccountCount(AssetsForm assetsForm);

	/**
	 * 자산 - 정렬순서변경
	 * @param assetsInfoVO
	 * @return int
	 */
	public int updateAssetsSortInfo(AssetsInfoVO assetsInfoVO);

	/**
	 * 자산 - 개별정보
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 검색키워드list
	 * @param AssetsForm
	 * @return List<String>
	 */
	public List<String> listAssetsSearchKeyword(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 계좌상세 입출금내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsBankActTrns(AssetsForm assetsForm);
	public int listAssetsBankActTrnsCount(AssetsForm assetsForm);
	/**
	 * 자산 - 은행 계좌상세 예적금내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsBankActSvng(AssetsForm assetsForm);
	public int listAssetsBankActSvngCount(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 계좌list
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsAccount(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 입출금내역
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsBankDepWdrlTotalAmt(AssetsForm assetsForm);
	public List<AssetsInfoVO> listAssetsBankDepWdrl(AssetsForm assetsForm);
	public int listAssetsBankDepWdrlCount(AssetsForm assetsForm);

	/**
	 * 자산 - 은행 입출금상세
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsBankDepWdrlDetail(AssetsForm assetsForm);
	/**
	 * 자산 - 은행 입출금상세(소비정보)
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsDetailCsInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 증권 메인 계좌내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsStockMainAccount(AssetsForm assetsForm);
	public int listAssetsStockMainAccountCount(AssetsForm assetsForm);

	/**
	 * 자산 - 증권 계좌상세
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsStockInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 증권 전체잔고상세
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsStockSumInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 증권 상세 계좌내역
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsStockDetailCompany(AssetsForm assetsForm);
	public List<AssetsInfoVO> listAssetsStockDetailAccount(AssetsForm assetsForm);
	public int listAssetsStockDetailAccountCount(AssetsForm assetsForm);

	/**
	 * 자산 - 증권 주식상세
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsStockShrInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 증권 펀드상세
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getAssetsStockFndInfo(AssetsForm assetsForm);

	/**
	 * 자산 - 직접입력
	 * @param AssetsInfoVO
	 * @return int
	 */
	public int createAssetsInfo(AssetsInfoVO assetsInfoVO);

	/**
	 * 자산 - 기타메인
	 * @param String no_person
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAssetsEtcMain(String no_person);


}
