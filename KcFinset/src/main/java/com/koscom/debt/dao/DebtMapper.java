package com.koscom.debt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koscom.debt.model.CrawlingLoanDtlVO;
import com.koscom.debt.model.CrawlingLoanVO;
import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.model.ReqIntrCutForm;
import com.koscom.domain.PersonInfo;

public interface DebtMapper {

	/**
	 * 정보관리번호 조회(KcbManagerImpl)
	 * @param loanVO
	 * @return
	 */
	String selectKcbNoManage(CrawlingLoanVO loanVO);

	/**
	 * 부채 추가(KcbManagerImpl)
	 * @param loanVO
	 */
	void insertKcbDebt(CrawlingLoanVO loanVO);

	/**
	 * 부채 상세 내역 MERGE(KcbManagerImpl)
	 * @param crawlingLoanDtlVO
	 */
	void updateKcbDtlDebt(CrawlingLoanDtlVO crawlingLoanDtlVO);

	/**
	 * 부채 해지 UPDATE(KcbManagerImpl)
	 * @param paramMap
	 * @return
	 */
	int updateKcbDebtCancel(Map<String, Object> paramMap);

	/**
	 * 부채 수정 (KcbManagerImpl)
	 * @param paramMap
	 */
	void updateKcbDebt(Map<String, Object> paramMap);

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
	List<DebtSummaryVO> listStatDebtSummary(DebtForm debtForm);

	/**
	 * 부채 상환금 입력
	 * @param debtVO
	 */
	void createRepayment(DebtVO debtVO);

	/**
	 * 부채 상환에 따른 부채 잔액 업데이트
	 * @param debtVO
	 */
	void updateAmtRemain(DebtVO debtVO);

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
	
	/**
	 * 부채 정보 등록
	 * @param debtVO
	 */
	void createDebtPersonalInfo(DebtVO debtVO);

}