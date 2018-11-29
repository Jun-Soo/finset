package com.koscom.debt.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.koscom.debt.model.ReqIntrCutForm;
import com.koscom.debt.service.DebtManager;
import com.koscom.domain.PersonInfo;
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
	public List<PersonInfo> listDebtSharePersonInfo(String no_person) {
		return debtMapper.listDebtSharePersonInfo(no_person);
	}

	@Override
	public DebtSummaryVO getDebtSummary(DebtForm debtForm) {
		return debtMapper.getDebtSummary(debtForm);
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

	@Override
	public List<DebtCalendarVO> listCalendarDebtData(DebtForm debtForm) {
		return debtMapper.listCalendarDebtData(debtForm);
	}

	@Override
	public List<DebtCalendarVO> listDetailCalendarDebtData(DebtForm debtForm) {
		return debtMapper.listDetailCalendarDebtData(debtForm);
	}

	@Override
	public Map<String, List<String>> listStatDebtSummary(DebtForm debtForm) {
		List<DebtSummaryVO> rawStatList = debtMapper.listStatDebtSummary(debtForm);
		List<String> dateList = new ArrayList<String>();
		List<String> dataList = new ArrayList<String>();
		for(int i = 0; i< rawStatList.size(); i++) {
			dateList.add(rawStatList.get(i).getReq_yyyymm().substring(4));
			dataList.add(rawStatList.get(i).getAmt_remain());
		}
		Map<String, List<String>> summaryMap = new HashMap<String, List<String>>();
		summaryMap.put("dataList", dataList);
		summaryMap.put("dateList", dateList);
		return summaryMap;
	}

	@Override
	public void createRepayment(DebtVO debtVO) {
		debtMapper.createRepayment(debtVO);
		debtMapper.updateAmtRemain(debtVO);
	}

	@Override
	public List<DebtVO> listReqIntrCutFixDate(ReqIntrCutForm reqIntCutForm) {
		return debtMapper.listReqIntrCutFixDate(reqIntCutForm);
	}

	@Override
	public List<DebtVO> listReqIntrCutIncome(ReqIntrCutForm reqIntCutForm) {
		return debtMapper.listReqIntrCutIncome(reqIntCutForm);
	}

	@Override
	public void createDebtPersonalInfo(DebtVO debtVO) {
		debtMapper.createDebtPersonalInfo(debtVO);
	}

	@Override
	public List<PersonInfo> listCalendarShareInfo(String no_person) {
		return debtMapper.listCalendarShareInfo(no_person);
	}

}
