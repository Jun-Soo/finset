package com.koscom.commission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.commission.model.CommissionForm;
import com.koscom.commission.model.CommissionVO;
import com.koscom.commission.service.CommissionManager;
import com.koscom.commission.service.CommissionWebManager;
import com.koscom.domain.CommissionInfo;
import com.koscom.util.ReturnClass;

@Service("commissionWebManager")
public class CommissionWebManagerImpl implements CommissionWebManager{
	@Autowired
	private CommissionManager commissionManager;

	@Override
	public CommissionVO getCommissionInfo(String no_apply) {
		// TODO Auto-generated method stub
		return commissionManager.getCommissionInfo(no_apply);
	}
	@Override
	public List<CommissionVO> listCommissionInfo(CommissionForm commissionForm) {
		// TODO Auto-generated method stub
		return commissionManager.listCommissionInfo(commissionForm);
	}
	@Override
	public int listCommissionCount(CommissionForm commissionForm) {
		// TODO Auto-generated method stub
		return commissionManager.listCommissionCount(commissionForm);
	}
	@Override
	public ReturnClass procCommissionDetails(CommissionVO commissionVO) {
		// TODO Auto-generated method stub
		return commissionManager.procCommissionDetails(commissionVO);
	}
	@Override
	public ReturnClass delCommissionDetails(CommissionVO commissionVO) {
		// TODO Auto-generated method stub
		return commissionManager.delCommissionDetails(commissionVO);
	}
}