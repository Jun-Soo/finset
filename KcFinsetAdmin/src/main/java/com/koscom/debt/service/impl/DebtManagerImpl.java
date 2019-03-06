package com.koscom.debt.service.impl;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.debt.dao.DebtMapper;
import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.model.KcBankerDebtInfoVO;
import com.koscom.debt.model.KcBankerDebtRepayVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.util.LogUtil;

@Service("debtManager")
public class DebtManagerImpl implements DebtManager {

	private static final Logger logger = LoggerFactory.getLogger(DebtManagerImpl.class);
	
	@Autowired
	private DebtMapper debtMapper;

	@Override
	public List<DebtVO> listDebtPg(DebtForm debtForm) {
		return debtMapper.listDebtPg(debtForm);
	}
	@Override
	public int listDebtCount(DebtForm debtForm){
		return debtMapper.listDebtCount(debtForm);
	}
	@Override
	public DebtSummaryVO getDebtSummary(String no_person) {
		return debtMapper.getDebtSummary(no_person);
	}
	@Override
	public DebtVO getDebtInfo(DebtForm debtForm) {
		return debtMapper.getDebtInfo(debtForm);
	}

	@Override
	public List<DebtDetail12RepVO> listDebtLast12BizDay(DebtForm debtForm) {
		return debtMapper.listDebtLast12BizDay(debtForm);
	}
	@Override
	public String getDebtExistYn(String no_person) {
		return debtMapper.getDebtExistYn(no_person);
	}
	/**
	 * 연동한 대출 건수 DEPT_YN = 'Y'
	 * @param no_person
	 * @return
	 */
	@Override
	public int getDebtYCount(String no_person) {
		return debtMapper.getDebtYCount(no_person);
	}
	/**
	 * 전체 대출 건수
	 * @param no_person
	 * @return
	 */
	@Override
	public int getDebtCount(String no_person) {
		return debtMapper.getDebtCount(no_person);
	}
	@Override
	public int newDebtCount(String no_person) {
		return debtMapper.newDebtCount(no_person);
	}
	
	/**
	 * 부채 데이터 삭제 처리
	 */
	@Override
	//부채 데이터 삭제 처리 20180529 김휘경 - 표시 여부 업데이트 처리 20180611 김휘경
	public void updateDebtDisplay(DebtForm debtForm) {
		debtMapper.updateDebtDisplay(debtForm);
	}
	//부채 삭제 관리를 위해 여러 개의 데이터를 처리하고자 했다 - 20180614 김휘경
	@Override
	public void updateDebtDisplayList(DebtForm debtForm) {
		String no_person = debtForm.getNo_person();
		List<DebtForm> list = debtForm.getList();
		for(DebtForm vo: list) {
			vo.setNo_person(no_person);
			debtMapper.updateDebtDisplay(vo);
		}
	}
	//부채 정보 수정을 위해 가져와야 하는 데이터 20180530 김휘경
	@Override
	public DebtVO getDebtInfoForUpdate(DebtForm debtForm){
		return debtMapper.getDebtInfoForUpdate(debtForm);
	}
	
	//부채 정보를 수정하는 쿼리 20180530 김휘경
	@Override
	public void updateDebtInfo(DebtVO debtVO){
		debtMapper.updateDebtInfo(debtVO);
	}
	
	@Override
	public void modifySeqNewDeptReg(String no_person) {
		debtMapper.modifySeqNewDeptReg(no_person);
	}

//	@Override
//	public void debtPdocTest(DebtPersonalInfoVO debtPersonalInfoVO) {
//
//		// TODO Auto-generated method stub
//		debtMapper.debtPdocTest(debtPersonalInfoVO);
//	}

	/**
	 * 관리자. 회원별 부채요약정보조회
	 */
	@Override
	public List<KcBankerDebtInfoVO> listDebtPersonInfo(DebtForm debtForm) {
		return debtMapper.listDebtPersonInfo(debtForm);
	}
	/**
	 * 관리자. 회원별 부채별 상세정보조회
	 */
	@Override
	public List<KcBankerDebtRepayVO> listDebtPersonRepay(DebtForm debtForm) {
		return debtMapper.listDebtPersonRepay(debtForm);
	}
	
	/**
	 * 부채 package call
	 * @param HashMap<String, String> callParam
	 * @return void
	 */
	public void debtPdocRun(String no_person) {
		
		HashMap<String, String> callParam = new HashMap<String, String>();
		//부채조회 (상환중인 부채)
		List<HashMap<String, String>> listDebt = (List<HashMap<String, String>>)debtMapper.listDebtInfo(no_person);
		for(HashMap<String, String> debt : listDebt) {
			callParam.put("no_person", no_person);
			callParam.put("no_manage_info", debt.get("no_manage_info").toString());
			callParam.put("p_rtn_cd", null);
			callParam.put("p_rtn_msg", null);
			debtMapper.debtInterestEtmPdoc(callParam);
			
			if(!"000".equals(callParam.get("p_rtn_cd").toString())) {
				LogUtil.error(logger, callParam.get("p_rtn_cd").toString());
				logger.error(callParam.get("p_rtn_msg").toString());
			}
		}
	}
	@Override
	public void debtPdocRun(HashMap<String, String> callParam) {
		//회원 부채 정보 금리 추정 처리
		debtMapper.debtInterestEtmPdoc(callParam);
	}

	//부채 내역 조회(회원별 상환중 부채)
	@Override
	public List<HashMap<String, String>> listDebtInfo(String no_person) {
		return debtMapper.listDebtInfo(no_person);
	}
	@Override
	public List<DebtCalendarVO> listDebtCalendar(DebtForm debtForm) {
		return debtMapper.listDebtCalendar(debtForm);
	}
	
}
