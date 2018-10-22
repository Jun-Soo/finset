package com.koscom.main.service;

public interface MainManager {

	/**
	 * Finset메인 - 지출 총금액 조회
	 * @param no_person
	 * @return String
	 */
	String getMainConsumeSumAmt(String no_person);

	/**
	 * Finset메인 - 자산 총금액 조회
	 * @param no_person
	 * @return String
	 */
	String getMainAssetsSumAmt(String no_person);

	/**
	 * Finset메인 - 부채 총금액 조회
	 * @param no_person
	 * @return String
	 */
	String getMainDebtSumAmt(String no_person);
}