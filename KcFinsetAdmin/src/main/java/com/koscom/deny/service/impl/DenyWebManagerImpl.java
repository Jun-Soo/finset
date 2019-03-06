package com.koscom.deny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.deny.model.DenyForm;
import com.koscom.deny.model.DenyVO;
import com.koscom.deny.service.DenyManager;
import com.koscom.deny.service.DenyWebManager;

@Service("denyWebManager")
public class DenyWebManagerImpl implements DenyWebManager{
	
	@Autowired
	private DenyManager denyManager;
	
	@Override
	public List<DenyVO> listDenyDetail(DenyVO denyVO) {
		return denyManager.listDenyDetail(denyVO);
	}
}
