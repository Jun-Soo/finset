package com.koscom.commission.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.commission.dao.CommissionMapper;
import com.koscom.commission.model.CommissionForm;
import com.koscom.commission.model.CommissionVO;
import com.koscom.commission.service.CommissionManager;
import com.koscom.counsel.model.CounselVO;
import com.koscom.domain.CommissionInfo;
import com.koscom.domain.GoodsInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.finset.service.FinsetManager;
import com.koscom.goods.model.GoodsVO;
import com.koscom.loan.service.LoanManager;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.worker.service.WorkerManager;

@Service("commissionManager")
public class CommissionManagerImpl implements CommissionManager {

	private static final Logger logger = LoggerFactory.getLogger(CommissionManagerImpl.class);
	
	@Autowired
	private CommissionMapper commissionMapper;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private WorkerManager workerManager;

	@Autowired
	PersonManager personManager;
	
	@Autowired
	LoanManager loanManager;
	
	@Autowired
	FinsetManager finsetManager; // namik ADD 2017.07.25
	

	@Override
	public List<CommissionVO> listCommissionInfo(CommissionForm commissionForm) {
		return commissionMapper.listCommissionInfo(commissionForm);
	}
	
	@Override
	public int listCommissionCount(CommissionForm commissionForm){
		return commissionMapper.listCommissionCount(commissionForm);
	}
	@Override
	public CommissionVO getCommissionInfo(String no_apply) {
		return commissionMapper.getCommissionInfo(no_apply);
	}

	@Override
	public ReturnClass procCommissionDetails(CommissionVO commissionVO) {
		if(1 != commissionMapper.procCommissionDetails(commissionVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass delCommissionDetails(CommissionVO commissionVO) {

		if (1 != commissionMapper.delCommissionDetails(commissionVO)) {
		return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
}