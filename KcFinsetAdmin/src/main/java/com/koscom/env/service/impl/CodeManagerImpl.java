package com.koscom.env.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.env.dao.CodeMapper;
import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.service.CodeManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

@Service("codeManager")
public class CodeManagerImpl implements CodeManager {

	private static final Logger logger = LoggerFactory.getLogger(CodeManagerImpl.class);

	private CodeManager codeManager;

	@Autowired
	private CodeMapper commCdMapper;

	@Override
	@Cacheable(value="CacheCode" , key="#codeInfo.code_group + #codeInfo.code_value")
	public CodeInfo getCodeInfo(CodeInfo codeInfo) {
		return commCdMapper.getCodeInfo(codeInfo);
	}

	@Override
	@Cacheable("CacheCode")
	public CodeInfo getCodeInfo(String group, String id) {
		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(group);
		codeInfo.setCode_value(id);

		return commCdMapper.getCodeInfo(codeInfo);
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
	public String getCodeId(String group, String name){

		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(name)) {
			return name;
		}

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(group);
		codeInfo.setNm_code(name);

		String value = commCdMapper.getCodeId(codeInfo);
		
		if(value == null) return name;

		return StringUtil.nullToString(value, name);
	}

	@Override
	public String getNvlCodeName(String group, String id, String defaultStr) {

		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return defaultStr;
		}

		String returnValue = getCodeName(group, id);

		// codeValue 와 returnValue 가 같다는 의미는
		// code name이 null 이란 의미이다
		if(StringUtil.isEmpty(returnValue) || id.equals(returnValue))
			return defaultStr;

		return returnValue;
	}

	@Override
	@Cacheable(value="CacheListCode" , key="#codeInfo.code_group")
	public List<CodeInfo> listCodeInfo(CodeInfo codeInfo) {
		return commCdMapper.listCodeInfo(codeInfo);
	}

	@Override
	@Cacheable(value="CacheListCode")
	public List<CodeInfo> listCodeInfo(String group) {
		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(group);

		return commCdMapper.listCodeInfo(codeInfo);
	}

	@Override
	public List<CodeVO> listCode(CodeForm codeForm) {
		return commCdMapper.listCode(codeForm);
	}

	@Override
	public CodeInfo getCode(CodeForm codeForm) {

		CodeInfo codeInfo = new CodeInfo();
		if(StringUtil.isEmpty(codeForm.getCode_group()))
			return codeInfo;

		codeInfo.setCode_group(codeForm.getCode_group());
		if ("Y".equals(codeForm.getYn_code_group())) {
			codeInfo.setCode_value(" ");
		} else {
			codeInfo.setCode_value(codeForm.getCode_value());
		}

		return commCdMapper.getCodeInfo(codeInfo);
	}

	@Override
	public ReturnClass procCodeInfo(CodeVO codeVO) {

		if(StringUtil.isEmpty(codeVO.getCode_group())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}

		if("Y".equals(codeVO.getYn_code_group())) {
			codeVO.setCode_value(" ");
		}

		if(!"Y".equals(codeVO.getYn_code_group()) && StringUtil.isEmpty(codeVO.getCode_value())) {
			return new ReturnClass(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
		}

		// insert 구분
		if("C".equals(codeVO.getCd_proc_type()) && commCdMapper.getCodeInfo(codeVO) != null) {
			return new ReturnClass(Constant.FAILED, "이미 존재하는 코드입니다. 선택 후 수정하여주세요.");
		}

		if (1 != commCdMapper.procCodeInfo(codeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delCodeInfo(CodeVO codeVO) {

		if(StringUtil.isEmpty(codeVO.getCode_group())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}

		if(!"Y".equals(codeVO.getYn_code_group()) && StringUtil.isEmpty(codeVO.getCode_value())) {
			return new ReturnClass(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
		}

		if (1 != commCdMapper.delCodeInfo(codeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	@CacheEvict(value={"CacheCode","CacheListCode"}, allEntries=true)
	public ReturnClass clearCacheCode() {
		logger.info("Cache clear : [CacheCode,CacheListCode]");

		// SA 정보 초기화
		String targetUrl = getNvlCodeName("_CONF_SYSTEM","URL","");
		if(StringUtil.isNotEmpty(targetUrl)) {
			URLConnection url = new URLConnection();
			targetUrl += "/cache/clearCacheCode.json";
			url.sendReqGET(targetUrl, "");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public CodeVO getMaxCodeInfo(CodeVO codeVO) {
		return commCdMapper.getMaxCodeInfo(codeVO);
	}

	@Override
	public ReturnClass modifyListSeq(List<CodeVO> codeVO) {
		for (CodeVO cv : codeVO) {
			if (1 != commCdMapper.modifyListSeq(cv)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public HashMap<String, CodeVO> getCodeMapInfo(String code_group){
		HashMap<String, CodeVO> codeMap = new HashMap<String, CodeVO>();

		CodeForm codeForm = new CodeForm();
		codeForm.setCode_group(code_group);
		List<CodeVO> codeList = commCdMapper.listCode(codeForm);

		for(CodeVO info : codeList){
			codeMap.put(info.getCode_value(), info);
		}

		return codeMap;
	}

}
