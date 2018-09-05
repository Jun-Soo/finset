package com.koscom.assets.dao;

import java.util.List;

import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;

public interface AssetsMapper {

	/**
	 * 자산요약 총잔액 조회
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getSummaryTotalAmt(AssetsForm assetsForm);

	/**
	 * 자산요약 자산분류별 리스트 조회
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listSummaryClassTotalAmt(AssetsForm assetsForm);

	/**
	 * 계좌현황 총금액 조회
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAccountStatTotalAmt(AssetsForm assetsForm);

	/**
	 * 계좌현황 리스트 조회
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listAccountStatContents(AssetsForm assetsForm);

	/**
	 * 입출금 입금/출금 총 금액 조회
	 * @param AssetsForm
	 * @return AssetsInfoVO
	 */
	public AssetsInfoVO getDepWdrlTotalAmt(AssetsForm assetsForm);

	/**
	 * 입출금 전체/입금/출금 내역조회
	 * @param AssetsForm
	 * @return List<AssetsInfoVO>
	 */
	public List<AssetsInfoVO> listDepWdrlContents(AssetsForm assetsForm);

}
