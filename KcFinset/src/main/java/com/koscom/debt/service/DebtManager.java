package com.koscom.debt.service;

import java.util.List;

import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;

public interface DebtManager {

	/**
	 * 부채 package call
	 * @param HashMap<String, String> callParam
	 * @return void
	 */
	void debtPdocRun(String no_person);
	
	/**
	 * 신규 부채 여부 확인
	 * @param no_person
	 * @return
	 */
	String getDebtExistYn(String no_person);
	
	/**
	 * 신규 부채 수 조회
	 * @param no_person
	 * @return
	 */
	int newDebtCount(String no_person);
	
	/**
	 * 연동한 대출 건수 DEPT_YN = 'Y'
	 * @param no_person
	 * @return
	 */
	int getDebtYCount(String no_person);
	
	/**
	 * 신규부채 시퀀스 수정
	 * @param no_person
	 */
	void modifySeqNewDeptReg(String no_person);
	
	/**
	 * 부채 요약 조회
	 * @param no_person
	 * @return
	 */
	DebtSummaryVO getDebtSummary(String no_person);
	
	/**
	 * 부채 리스트 조회
	 * @param debtForm
	 * @return
	 */
	List<DebtVO> listDebtPg(DebtForm debtForm);
}