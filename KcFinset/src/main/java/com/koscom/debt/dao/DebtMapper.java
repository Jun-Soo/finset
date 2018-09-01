package com.koscom.debt.dao;

import java.util.HashMap;
import java.util.List;

public interface DebtMapper {

	/**
	 * 부채 내역 조회(회원별 상환중 부채)
	 * @param String no_person
	 * @return List<HashMap<String, String>>
	 */
	List<HashMap<String, String>> listDebtInfo(String no_person);
	
	/**
	 * 부채 프로시저 - 회원 부채 정보 금리 추정 처리
	 * @param HashMap<String, String> callParam
	 */
	void debtInterestEtmPdoc(HashMap<String, String> callParam);
		
}