package com.koscom.main.dao;

import com.koscom.main.model.MainForm;

public interface MainMapper {

	/**
	 * Finset메인 - 스크래핑건수
	 * @param MainForm
	 * @return int
	 */
	int getMainScrapCnt(MainForm mainForm);

	/**
	 * Finset메인 - 지출총금액
	 * @param no_person
	 * @return String
	 */
	String getMainConsumeSumAmt(String no_person);

	/**
	 * Finset메인 - 자산총금액
	 * @param no_person
	 * @return String
	 */
	String getMainAssetsSumAmt(String no_person);

	/**
	 * Finset메인 - 부채총금액
	 * @param no_person
	 * @return String
	 */
	String getMainDebtSumAmt(String no_person);



}
