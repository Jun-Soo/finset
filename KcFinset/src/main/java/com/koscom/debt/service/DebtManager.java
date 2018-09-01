package com.koscom.debt.service;

public interface DebtManager {

	/**
	 * 부채 package call
	 * @param HashMap<String, String> callParam
	 * @return void
	 */
	void debtPdocRun(String no_person);

}