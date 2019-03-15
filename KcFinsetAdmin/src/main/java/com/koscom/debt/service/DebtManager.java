package com.koscom.debt.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.model.KcBankerDebtInfoVO;
import com.koscom.debt.model.KcBankerDebtRepayVO;

public interface DebtManager {

//	List<DebtVO> listDebtPg(DebtForm debtForm);
//
//	int listDebtCount(DebtForm debtForm);
//
//	DebtSummaryVO getDebtSummary(String no_person);
//
//	DebtVO getDebtInfo(DebtForm debtForm);
//
//	List<DebtDetail12RepVO> listDebtLast12BizDay(DebtForm debtForm);
//
//	String getDebtExistYn(String no_person);
//
//	/**
//	 * 연동한 대출 건수 DEPT_YN = 'Y'
//	 * @param no_person
//	 * @return
//	 */
//	int getDebtYCount(String no_person);
//	/**
//	 * 전체 대출 건수
//	 * @param no_person
//	 * @return
//	 */
//	int getDebtCount(String no_person);
//
//	int newDebtCount(String no_person);
//
//	//부채 데이터 삭제 처리 20180529 김휘경 - 표시 여부 업데이트 처리 20180611 김휘경
//	void updateDebtDisplay(DebtForm debtForm);
//	
//	//부채 삭제 관리를 위해 여러 개의 데이터를 처리하고자 했다 - 20180614 김휘경
//	void updateDebtDisplayList(DebtForm debtForm);
//	
//	//부채 정보 수정을 위해 가져와야 하는 데이터 20180530 김휘경
//	DebtVO getDebtInfoForUpdate(DebtForm debtForm);
//	
//	//부채 정보를 수정하는 쿼리 20180530 김휘경
//	void updateDebtInfo(DebtVO debtVO);
//	
//	void modifySeqNewDeptReg(String no_person);


	//부채 프로시저 테스트
//	void debtPdocTest(DebtPersonalInfoVO debtPersonalInfoVO);

//	/**
//	 * 부채 package call
//	 * @param HashMap<String, String> callParam
//	 * @return void
//	 */
//	void debtPdocRun(HashMap<String, String> callParam);
//	void debtPdocRun(String no_person);
	
	//관리자. 회원별 부채요약정보조회
	List<KcBankerDebtInfoVO> listDebtPersonInfo(DebtForm debtForm);

	//관리자. 회원별 부채별 상세정보조회
	List<KcBankerDebtRepayVO> listDebtPersonRepay(DebtForm debtForm);

//	//부채 내역 조회(회원별 상환중 부채)
//	List<HashMap<String, String>> listDebtInfo(String no_person);
//
//	//부채 캘린더
//	List<DebtCalendarVO> listDebtCalendar(DebtForm debtForm);

}