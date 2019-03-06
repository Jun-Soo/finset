package com.koscom.counsel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.counsel.model.CounselForm;
import com.koscom.counsel.model.CounselVO;
import com.koscom.counsel.service.CounselManager;
import com.koscom.counsel.service.CounselWebManager;
import com.koscom.util.ReturnClass;

@Service("counselWebManager")
public class CounselWebManagerImpl implements CounselWebManager{
	
	@Autowired
	private CounselManager counselManager;
	
	@Override
	public List<CounselVO> listCounselInfoPg(CounselForm counselForm) {
		return counselManager.listCounselInfoPg(counselForm);
	}
	
	@Override
	public int listCounselCount(CounselForm counselForm) {
		return counselManager.listCounselCount(counselForm);
	}

	@Override
	public CounselVO getCounselInfo(CounselVO counselVO) {
		return counselManager.getCounselInfo(counselVO);
	}

	@Override
	public List<CounselVO> getCreditList(CounselVO counselVO) {
		return counselManager.getCreditList(counselVO);
	}

	@Override
	public List<CounselVO> getDebtList(CounselVO counselVO) {
		return counselManager.getDebtList(counselVO);
	}

	@Override
	public List<CounselVO> getCounselHist(CounselVO counselVO) {
		return counselManager.getCounselHist(counselVO);
	}

	@Override
	public ReturnClass saveCounselStatus(CounselVO counselVO) {
		return counselManager.saveCounselStatus(counselVO);
	}

	@Override
	public ReturnClass saveCounselContents(CounselVO counselVO) {
		return counselManager.saveCounselContents(counselVO);
	}

	@Override
	public ReturnClass procCounselInfo(CounselVO counselVO) {
		return counselManager.procCounselInfo(counselVO);
	}

	@Override
	public List<CounselVO> counselInfoList(CounselForm counselForm) {
		return counselManager.counselInfoList(counselForm);
	}
}
