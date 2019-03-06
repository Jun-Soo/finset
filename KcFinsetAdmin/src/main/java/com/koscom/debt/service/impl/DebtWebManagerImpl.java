package com.koscom.debt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.model.KcBankerDebtInfoVO;
import com.koscom.debt.model.KcBankerDebtRepayVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.debt.service.DebtWebManager;


@Service("debtWebManager")
public class DebtWebManagerImpl implements DebtWebManager {

	@Autowired
	private DebtManager debtManager;

	@Override
	public List<DebtVO> listDebtPg(DebtForm debtForm) {

		return debtManager.listDebtPg(debtForm);
	}

	@Override
	public int listDebtCount(DebtForm debtForm) {

		return debtManager.listDebtCount(debtForm);
	}

	@Override
	public DebtSummaryVO getDebtSummary(String no_person) {

		return debtManager.getDebtSummary(no_person);
	}

	@Override
	public DebtVO getDebtInfo(DebtForm debtForm) {

		return debtManager.getDebtInfo(debtForm);
	}

	@Override
	public List<DebtDetail12RepVO> listDebtLast12BizDay(DebtForm debtForm) {

		return debtManager.listDebtLast12BizDay(debtForm);
	}

	@Override
	public String getDebtExistYn(String no_person) {

		return debtManager.getDebtExistYn(no_person);
	}

	@Override
	public int newDebtCount(String no_person) {

		return debtManager.newDebtCount(no_person);

	}
	
	/**
	 * 부채 데이터 삭제 처리
	 */
	@Override
	//부채 데이터 삭제 처리 20180529 김휘경 - 표시 여부 업데이트 처리 20180611 김휘경
	public void updateDebtDisplay(DebtForm debtForm) {
		debtManager.updateDebtDisplay(debtForm);
	}
	
	//부채 삭제 관리를 위해 여러 개의 데이터를 처리하고자 했다 - 20180614 김휘경
	@Override
	public void updateDebtDisplayList(DebtForm debtForm) {
		debtManager.updateDebtDisplayList(debtForm);
	}
	
	//부채 정보 수정을 위해 가져와야 하는 데이터 20180530 김휘경
	@Override
	public DebtVO getDebtInfoForUpdate(DebtForm debtForm){
		return debtManager.getDebtInfoForUpdate(debtForm);
	}
	
	//부채 정보를 수정하는 쿼리 20180530 김휘경
	@Override
	public void updateDebtInfo(DebtVO debtVO){
		debtManager.updateDebtInfo(debtVO);
	}
	
	@Override
	public void modifySeqNewDeptReg(String no_person) {

		debtManager.modifySeqNewDeptReg(no_person);

	}

	/**
	 * 연동한 대출 건수 DEPT_YN = 'Y'
	 *
	 * @param no_person
	 * @return
	 */
	@Override
	public int getDebtYCount(String no_person) {
		return debtManager.getDebtYCount(no_person);
	}

	/**
	 * 전체 대출 건수
	 *
	 * @param no_person
	 * @return
	 */
	@Override
	public int getDebtCount(String no_person) {
		return debtManager.getDebtCount(no_person);
	}

	/**
	 * 관리자. 회원별 부채요약정보조회
	 *
	 * @param no_person
	 * @return
	 */
	@Override
	public List<KcBankerDebtInfoVO> listDebtPersonInfo(DebtForm debtForm) {
		return debtManager.listDebtPersonInfo(debtForm);
	}

	/**
	 * 관리자. 회원별 부채별 상세정보조회
	 *
	 * @param no_person, no_manage_info
	 * @return
	 */
	@Override
	public List<KcBankerDebtRepayVO> listDebtPersonRepay(DebtForm debtForm) {
		return debtManager.listDebtPersonRepay(debtForm);
	}

	@Override
	public List<HashMap<String, String>> listDebtInfo(String no_person) {
		return debtManager.listDebtInfo(no_person);
	}
	
	/**
	 * 부채 package call
	 * @param HashMap<String, String> callParam
	 * @return void
	 */
	@Override
	public void debtPdocRun(HashMap<String, String> callParam) {
		debtManager.debtPdocRun(callParam);
	}
	public void debtPdocRun(String no_person) {
		debtManager.debtPdocRun(no_person);
	}
	
	@Override
	public List<DebtCalendarVO> listDebtCalendar(DebtForm debtForm) {

		return debtManager.listDebtCalendar(debtForm);
	}
}