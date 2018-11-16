package com.koscom.debt.service;

import java.util.List;
import java.util.Map;

import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.model.ReqIntrCutForm;
import com.koscom.domain.PersonInfo;

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
	 * 부채 공유 리스트 조회
	 * @param no_person
	 * @return
	 */
	List<PersonInfo> listDebtSharePersonInfo(String no_person);

	/**
	 * 부채 요약 조회
	 * @param no_person
	 * @return
	 */
	DebtSummaryVO getDebtSummary(DebtForm debtForm);

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

	/**
	 * 부채 다중 삭제, 취소 처리
	 * @param debtForm
	 */
	void updateDebtDisplayList(DebtForm debtForm);

	/**
	 * 전체 대출 건수
	 * @param no_person
	 * @return
	 */
	int getDebtCount(String no_person);

	/**
	 * 공통 캘린더 합계 조회
	 * @param debtForm
	 * @return
	 */
	List<DebtCalendarVO> getCalendarDebtData(DebtForm debtForm);

	/**
	 * 공통 캘린더 리스트 조회
	 * @param debtForm
	 * @return
	 */
	List<DebtCalendarVO> listCalendarDebtData(DebtForm debtForm);

	/**
	 * 부채 메인 상단
	 * @param debtForm
	 * @return
	 */
	Map<String, List<String>> listStatDebtSummary(DebtForm debtForm);

	/**
	 * 부채 상환금 입력
	 * @param debtVO
	 */
	void createRepayment (DebtVO debtVO);

	/**
	 * 금리인하요구 리스트 - 기준일 기준
	 * @param debtForm
	 * @return
	 */
	List<DebtVO> listReqIntrCutFixDate(ReqIntrCutForm reqIntCutForm);

	/**
	 * 금리인하요구 리스트 - 연소득 기준
	 * @param debtForm
	 * @return
	 */
	List<DebtVO> listReqIntrCutIncome(ReqIntrCutForm reqIntCutForm);

}