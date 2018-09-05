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
import com.koscom.debt.service.DebtManager;
import com.koscom.util.LogUtil;

@Service("debtManager")
public class DebtManagerImpl implements DebtManager {

	private static final Logger logger = LoggerFactory.getLogger(DebtManagerImpl.class);
	
	@Autowired
	private DebtMapper debtMapper;

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
	public String getDebtExistYn(String no_person) {
		return debtMapper.getDebtExistYn(no_person);
	}
	
	@Override
	public int newDebtCount(String no_person) {
		return debtMapper.newDebtCount(no_person);
	}
	
	@Override
	public int getDebtYCount(String no_person) {
		return debtMapper.getDebtYCount(no_person);
	}
	
	@Override
	public void modifySeqNewDeptReg(String no_person) {
		debtMapper.modifySeqNewDeptReg(no_person);
	}
	
	@Override
	public DebtSummaryVO getDebtSummary(String no_person) {
		return debtMapper.getDebtSummary(no_person);
	}
	
	@Override
	public List<DebtVO> listDebtPg(DebtForm debtForm) {
		return debtMapper.listDebtPg(debtForm);
	}
	
	@Override
	public List<DebtCalendarVO> listDebtCalendar(DebtForm debtForm) {
		return debtMapper.listDebtCalendar(debtForm);
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
	public DebtVO getDebtInfoForUpdate(DebtForm debtForm) {
		return debtMapper.getDebtInfoForUpdate(debtForm);
	}
	
	@Override
	public void updateDebtInfo(DebtVO debtVO){
		debtMapper.updateDebtInfo(debtVO);
	}
	
	@Override
	public void updateDebtDisplay(DebtForm debtForm) {
		debtMapper.updateDebtDisplay(debtForm);
	}
	
	@Override
	public void updateDebtDisplayList(DebtForm debtForm) {
		String no_person = debtForm.getNo_person();
		List<DebtForm> list = debtForm.getList();
		for(DebtForm vo: list) {
			vo.setNo_person(no_person);
			debtMapper.updateDebtDisplay(vo);
		}
	}
	
	@Override
	public int getDebtCount(String no_person) {
		return debtMapper.getDebtCount(no_person);
	}
}
