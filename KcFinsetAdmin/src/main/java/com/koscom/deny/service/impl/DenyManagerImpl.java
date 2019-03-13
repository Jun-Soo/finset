package com.koscom.deny.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.deny.dao.DenyMapper;
import com.koscom.deny.model.DenyForm;
import com.koscom.deny.model.DenyVO;
import com.koscom.deny.service.DenyManager;

@Service("denyManager")
public class DenyManagerImpl implements DenyManager {

	private static final Logger logger = LoggerFactory.getLogger(DenyManagerImpl.class);
	
	@Autowired
	private DenyMapper denyMapper;

	@Override
	public List<DenyVO> listDenyDetail(DenyVO denyVO) {
		return denyMapper.listDenyDetail(denyVO);
	}
}
