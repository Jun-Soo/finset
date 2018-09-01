package com.koscom.debt.service.impl;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.debt.dao.DebtMapper;
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
}
