package com.koscom.debt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koscom.debt.model.CrawlingLoanDtlVO;
import com.koscom.debt.model.CrawlingLoanVO;
import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtPersonalInfoVO;
import com.koscom.debt.model.DebtPersonalRepayListVO;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.model.KcBankerDebtInfoVO;
import com.koscom.debt.model.KcBankerDebtRepayVO;
import com.koscom.debt.model.KcbLoanInfoVO;

public interface DebtMapper {

	int listDebtCount(DebtForm debtForm);

	List<DebtVO> listDebtPg(DebtForm debtForm);

	/**
	 * 연동한 대출 건수 DEPT_YN = 'Y'
	 * @param no_person
	 * @return
	 */
	int getDebtYCount(String no_person);
	/**
	 * 전체 대출 건수
	 * @param no_person
	 * @return
	 */
	int getDebtCount(String no_person);

	DebtSummaryVO getDebtSummary(String no_person);


	DebtVO getDebtInfo(DebtForm debtForm);

	List<DebtDetail12RepVO> listDebtLast12BizDay(DebtForm debtForm);

	String getDebtExistYn(String no_person);

	int newDebtCount(String no_person);

	//부채 데이터 삭제 처리 20180529 김휘경 - 표시 여부 업데이트 처리 20180611 김휘경
	void updateDebtDisplay(DebtForm debtForm);
	
	//부채 정보 수정을 위해 가져와야 하는 데이터 20180530 김휘경
	DebtVO getDebtInfoForUpdate(DebtForm debtForm);
	
	//부채 정보를 수정하는 쿼리 20180530 김휘경
	void updateDebtInfo(DebtVO debtVO);
	
	void modifySeqNewDeptReg(String no_person);
	
	//KCB 부채 크롤링
	String selectKcbNoManage(CrawlingLoanVO loanVO);
	String selectKcbNoManageSeq(CrawlingLoanVO loanVO);

	void insertKcbDebt(CrawlingLoanVO loanVO);

	void updateKcbDebt(Map<String, Object> paramMap);

	void updateKcbDtlDebt(CrawlingLoanDtlVO crawlingLoanDtlVO);

	//개별 부채 정보
	DebtPersonalInfoVO getDebtPersonalInfoVO(DebtPersonalInfoVO debtPersonalInfoVO);

	List<DebtPersonalRepayListVO> listDebtPersonalRepay(DebtPersonalInfoVO debtPersonalInfoVO);

	//개인별 부채 정보 저장- 요약
	void saveDebtPersonalInfo(DebtPersonalInfoVO debtPersonalInfoVO);

	//개인별 부채 정보 저장- 상환내역
	void saveDebtPersonalRepayList(DebtPersonalRepayListVO debtPersonalRepayListVO);

	//개인별 대출정보 저장
	void saveKcbLoanInfo(KcbLoanInfoVO saveKcbLoanInfo);

//	//부채 프로시져 호출 위한 조회 : Y이면 첫번째 실행 프로시져 호출, N이면 업데이트 프로시져 호출 할 것
//	String getDebtProcFirstYn(DebtPersonalInfoVO debtPersonalInfoVO);
//
//	//첫번째 부채 프로시저
//	void debtPdocFirstY(DebtPersonalInfoVO debtPersonalInfoVO);
//
//	//두번째 부채 프로시저
//	void debtPdocFirstN(DebtPersonalInfoVO debtPersonalInfoVO);

	//부채 프로시저 - 회원 부채 정보 금리 추정 처리
	void debtInterestEtmPdoc(HashMap<String, String> callParam);

//	//프로시저 테스트
//	void debtPdocTest(DebtPersonalInfoVO debtPersonalInfoVO);

	//관리자. 회원별 부채요약정보조회
	List<KcBankerDebtInfoVO> listDebtPersonInfo(DebtForm debtForm);

	//관리자. 회원별 부채별 상세정보조회
	List<KcBankerDebtRepayVO> listDebtPersonRepay(DebtForm debtForm);

	//부채 내역 조회(회원별 상환중 부채)
	List<HashMap<String, String>> listDebtInfo(String no_person);

	//해지 UPDATE
	int updateKcbDebtCancel(Map<String, Object> paramMap);

	//부채 캘린더
	List<DebtCalendarVO> listDebtCalendar(DebtForm debtForm);
}