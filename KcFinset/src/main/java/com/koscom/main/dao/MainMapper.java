package com.koscom.main.dao;

import com.koscom.main.model.MainVO;

public interface MainMapper {

	/**
	 * Finset메인 - 지출정보조회
	 * @param no_person
	 * @return String
	 */
	String getMainConsumeSumAmt(String no_person);

	/**
	 * Finset메인 - 자산정보조회
	 * @param no_person
	 * @return String
	 */
	String getMainAssetsSumAmt(String no_person);

	/**
	 * Finset메인 - 부채정보조회
	 * @param no_person
	 * @return String
	 */
	String getMainDebtSumAmt(String no_person);

}
