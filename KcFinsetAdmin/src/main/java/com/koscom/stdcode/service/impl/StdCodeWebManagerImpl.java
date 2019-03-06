package com.koscom.stdcode.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.stdcode.model.StdCodeForm;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.model.StdCodeVO;
import com.koscom.stdcode.service.StdCodeManager;
import com.koscom.stdcode.service.StdCodeWebManager;
import com.koscom.util.ReturnClass;

@Service("stdcodeWebManager")
public class StdCodeWebManagerImpl implements StdCodeWebManager {

	@Autowired
	private StdCodeManager stdCodeManager;
	
	@Override
	public StdCodeInfo getStdCodeInfo(StdCodeInfo codeInfo) {
		return stdCodeManager.getStdCodeInfo(codeInfo);
	}

	@Override
	public List<StdCodeInfo> listStdCodeInfo(StdCodeInfo codeInfo) {
		return stdCodeManager.listStdCodeInfo(codeInfo);
	}

	@Override
	public StdCodeInfo getStdCodeInfo(String group, String id) {
		// TODO Auto-generated method stub
		return stdCodeManager.getStdCodeInfo(group, id);
	}

	@Override
	public String getStdCodeName(String group, String id) {
		// TODO Auto-generated method stub
		return stdCodeManager.getStdCodeName(group, id);
	}

	@Override
	public String getNvlStdCodeName(String group, String id, String defaultStr) {
		// TODO Auto-generated method stub
		return stdCodeManager.getNvlStdCodeName(group, id, defaultStr);
	}

	@Override
	public List<StdCodeInfo> listStdCodeInfo(String group) {
		// TODO Auto-generated method stub
		return stdCodeManager.listStdCodeInfo(group);
	}

	@Override
	public List<StdCodeVO> listStdCode(StdCodeForm codeForm) {
		// TODO Auto-generated method stub
		return stdCodeManager.listStdCode(codeForm);
	}

	@Override
	public StdCodeInfo getStdCode(StdCodeForm codeForm) {
		// TODO Auto-generated method stub
		return stdCodeManager.getStdCode(codeForm);
	}

	@Override
	public ReturnClass procStdCodeInfo(StdCodeVO codeVO) {
		// TODO Auto-generated method stub
		return stdCodeManager.procStdCodeInfo(codeVO);
	}

	@Override
	public ReturnClass delStdCodeInfo(StdCodeVO codeVO) {
		// TODO Auto-generated method stub
		return stdCodeManager.delStdCodeInfo(codeVO);
	}

	@Override
	public ReturnClass clearCacheStdCode() {
		// TODO Auto-generated method stub
		return stdCodeManager.clearCacheStdCode();
	}

	@Override
	public StdCodeVO getMaxStdCodeInfo(StdCodeVO codeVO) {
		// TODO Auto-generated method stub
		return stdCodeManager.getMaxStdCodeInfo(codeVO);
	}

	@Override
	public ReturnClass modifyListSeq(List<StdCodeVO> codeVO) {
		// TODO Auto-generated method stub
		return stdCodeManager.modifyListSeq(codeVO);
	}

	@Override
	public HashMap<String, StdCodeVO> getStdCodeMapInfo(String code_group) {
		// TODO Auto-generated method stub
		return stdCodeManager.getStdCodeMapInfo(code_group);
	}

}
