package com.koscom.env.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.env.dao.CodeMapper;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;

@Service("codeManager")
public class CodeManagerImpl implements CodeManager {

	private static final Logger logger = LoggerFactory.getLogger(CodeManagerImpl.class);

	@Autowired
	private CodeMapper codeMapper;
	
	private CodeManager codeManager;
	
	@Override
	@Cacheable(value="CacheCode" , key="#codeInfo.code_group + #codeInfo.code_value")
	public CodeInfo getCodeInfo(CodeInfo codeInfo) {
		return codeMapper.getCodeInfo(codeInfo);
	}
	
	@Override
	@Cacheable("CacheCode")
	public CodeInfo getCodeInfo(String group, String id) {
		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(group);
		codeInfo.setCode_value(id);

		return codeMapper.getCodeInfo(codeInfo);
	}
	
	@Override
	@Cacheable(value="CacheListCode" , key="#codeInfo.code_group")
	public List<CodeInfo> listCodeInfo(CodeInfo codeInfo) {
		return codeMapper.listCodeInfo(codeInfo);
	}

	@Override
	@Cacheable(value="CacheListCode")
	public List<CodeInfo> listCodeInfo(String group) {
		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(group);

		return codeMapper.listCodeInfo(codeInfo);
	}
	
	@Override
	public String getCodeId(String group, String name){

		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(name)) {
			return name;
		}

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(group);
		codeInfo.setNm_code(name);

		String value = codeMapper.getCodeId(codeInfo);
		
		if(value == null) return name;

		return StringUtil.nullToString(value, name);
	}
	
	@Override
	public String getCodeName(String group, String id){

		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return id;
		}

		codeManager = (CodeManager) SpringApplicationContext.getBean("codeManager");
		CodeInfo ci = codeManager.getCodeInfo(group, id);

		if(ci == null) return id;

		return StringUtil.nullToString(ci.getNm_code(), id);
	}
	
	@Override
	public CodeInfo getAgreeTerm(CodeInfo codeInfo) {
		return codeMapper.getAgreeTerm(codeInfo);
	}
}
