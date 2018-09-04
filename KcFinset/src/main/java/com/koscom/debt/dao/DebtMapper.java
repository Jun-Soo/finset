package com.koscom.debt.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;

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
	
	/**
	 * 부채 달력용 리스트 조회
	 * @param debtForm
	 * @return
	 */
	List<DebtCalendarVO> listDebtCalendar(DebtForm debtForm);
	
	/**
	 * 부채 정보 조회
	 * @param debtForm
	 * @return
	 */
	DebtVO getDebtInfo(DebtForm debtForm);
	
	/**
	 * 최근 12개월 상환내역
	 * @param debtForm
	 * @return
	 */
	List<DebtDetail12RepVO> listDebtLast12BizDay(DebtForm debtForm);
	
	/**
	 * 부채 정보 수정에 필요한 데이터 조회
	 * @param debtForm
	 * @return
	 */
	DebtVO getDebtInfoForUpdate(DebtForm debtForm);
	
	/**
	 * 부채 수정
	 * @param debtVO
	 */
	void updateDebtInfo(DebtVO debtVO);
	
	/**
	 * 부채 삭제, 삭제 취소 처리
	 * @param debtForm
	 */
	void updateDebtDisplay(DebtForm debtForm);
}