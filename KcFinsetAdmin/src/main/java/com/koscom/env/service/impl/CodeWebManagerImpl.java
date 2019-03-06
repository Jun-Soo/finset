package com.koscom.env.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.service.CodeManager;
import com.koscom.env.service.CodeWebManager;
import com.koscom.util.ReturnClass;

@Service("codeWebManager")
public class CodeWebManagerImpl implements CodeWebManager {

	@Autowired
	private CodeManager codeManager;
	
	@Override
	public CodeInfo getCodeInfo(CodeInfo codeInfo) {
		return codeManager.getCodeInfo(codeInfo);
	}

	@Override
	public List<CodeInfo> listCodeInfo(CodeInfo codeInfo) {
		return codeManager.listCodeInfo(codeInfo);
	}

	@Override
	public CodeInfo getCodeInfo(String group, String id) {
		// TODO Auto-generated method stub
		return codeManager.getCodeInfo(group, id);
	}
	
	@Override
	public String getCodeId(String group, String name) {
		// TODO Auto-generated method stub
		return codeManager.getCodeId(group, name);
	}


	@Override
	public String getCodeName(String group, String id) {
		// TODO Auto-generated method stub
		return codeManager.getCodeName(group, id);
	}

	@Override
	public String getNvlCodeName(String group, String id, String defaultStr) {
		// TODO Auto-generated method stub
		return codeManager.getNvlCodeName(group, id, defaultStr);
	}

	@Override
	public List<CodeInfo> listCodeInfo(String group) {
		// TODO Auto-generated method stub
		return codeManager.listCodeInfo(group);
	}

	@Override
	public List<CodeVO> listCode(CodeForm codeForm) {
		// TODO Auto-generated method stub
		return codeManager.listCode(codeForm);
	}

	@Override
	public CodeInfo getCode(CodeForm codeForm) {
		// TODO Auto-generated method stub
		return codeManager.getCode(codeForm);
	}

	@Override
	public ReturnClass procCodeInfo(CodeVO codeVO) {
		// TODO Auto-generated method stub
		return codeManager.procCodeInfo(codeVO);
	}

	@Override
	public ReturnClass delCodeInfo(CodeVO codeVO) {
		// TODO Auto-generated method stub
		return codeManager.delCodeInfo(codeVO);
	}

	@Override
	public ReturnClass clearCacheCode() {
		// TODO Auto-generated method stub
		return codeManager.clearCacheCode();
	}

	@Override
	public CodeVO getMaxCodeInfo(CodeVO codeVO) {
		// TODO Auto-generated method stub
		return codeManager.getMaxCodeInfo(codeVO);
	}

	@Override
	public ReturnClass modifyListSeq(List<CodeVO> codeVO) {
		// TODO Auto-generated method stub
		return codeManager.modifyListSeq(codeVO);
	}

	@Override
	public HashMap<String, CodeVO> getCodeMapInfo(String code_group) {
		// TODO Auto-generated method stub
		return codeManager.getCodeMapInfo(code_group);
	}

}
