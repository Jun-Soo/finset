package com.koscom.stdcode.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.stdcode.dao.StdCodeMapper;
import com.koscom.stdcode.model.StdCodeForm;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.model.StdCodeVO;
import com.koscom.stdcode.service.StdCodeManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;
import com.koscom.util.LogUtil;
@Service("stdCodeManager")
public class StdCodeManagerImpl implements StdCodeManager {

	private static final Logger logger = LoggerFactory.getLogger(StdCodeManagerImpl.class);
	
	private CodeManager codeManager;
	
	@Autowired
	private StdCodeMapper stdCdMapper;

	@Override
//	@Cacheable(value="CacheStdCode" , key="#StdCodeInfo.code_group + #StdCodeInfo.code_value")
	public StdCodeInfo getStdCodeInfo(StdCodeInfo codeInfo) {
		logger.debug("stdCdMapper.getStdCodeInfo(codeInfo) = " + codeInfo);
		return stdCdMapper.getStdCodeInfo(codeInfo);
	}
	
	@Override
	@Cacheable("CacheStdCode")
	public StdCodeInfo getStdCodeInfo(String group, String id) {
		LogUtil.debugLn(logger,"getStdCodeInfo" + group + ":" + id);
		logger.debug("id" + group + ":" + id);
		StdCodeInfo stdCodeInfo = new StdCodeInfo();
		stdCodeInfo.setCode_group(group);
		stdCodeInfo.setCode_value(id);
		
		return stdCdMapper.getStdCodeInfo(stdCodeInfo);
	}
	
	@Override
	public String getStdCodeName(String group, String id){
		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return id;
		}
		codeManager = (CodeManager) SpringApplicationContext.getBean("codeManager");
		CodeInfo codeInfo = codeManager.getCodeInfo(group, id);
		if(codeInfo == null) return id;
		return StringUtil.nullToString(codeInfo.getNm_code(), id);
	}
	@Override
	public String getNvlStdCodeName(String group, String id, String defaultStr) {
		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return defaultStr;
		}
		String returnValue = getStdCodeName(group, id);
		// codeValue 와 returnValue 가 같다는 의미는 
		// code name이 null 이란 의미이다
		if(StringUtil.isEmpty(returnValue) || id.equals(returnValue))
			return defaultStr;
		return returnValue;
	}

	@Override
	@Cacheable(value="CacheListStdCode" , key="#StdCodeInfo.code_group")
	public List<StdCodeInfo> listStdCodeInfo(StdCodeInfo codeInfo) {
		return stdCdMapper.listStdCodeInfo(codeInfo);
	}
	@Override
	@Cacheable(value="CacheListStdCode")
	public List<StdCodeInfo> listStdCodeInfo(String group) {
		StdCodeInfo codeInfo = new StdCodeInfo();
		codeInfo.setCode_group(group);
		return stdCdMapper.listStdCodeInfo(codeInfo);
	}
	@Override
	public List<StdCodeVO> listStdCode(StdCodeForm stdCodeForm) {
		return stdCdMapper.listStdCode(stdCodeForm);
	}
	@Override
	public StdCodeInfo getStdCode(StdCodeForm stdCodeForm) {
		StdCodeInfo stdCodeInfo = new StdCodeInfo();
		if(StringUtil.isEmpty(stdCodeForm.getCode_group()))
			return stdCodeInfo;
		stdCodeInfo.setCode_group(stdCodeForm.getCode_group());
		if ("Y".equals(stdCodeForm.getYn_code_group())) {
			stdCodeInfo.setCode_value(" ");
		} else {
			stdCodeInfo.setCode_value(stdCodeForm.getCode_value());
		}
		return stdCdMapper.getStdCodeInfo(stdCodeInfo);
	}
	@Override
	public ReturnClass procStdCodeInfo(StdCodeVO stdCodeVO) {
		if(StringUtil.isEmpty(stdCodeVO.getCode_group())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}
		if("Y".equals(stdCodeVO.getYn_code_group())) {
			stdCodeVO.setCode_value(" ");
		}
		if(!"Y".equals(stdCodeVO.getYn_code_group()) && StringUtil.isEmpty(stdCodeVO.getCode_value())) {
			return new ReturnClass(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
		}
		// insert 구분
		if("C".equals(stdCodeVO.getCd_proc_type()) && stdCdMapper.getStdCodeInfo(stdCodeVO) != null) {
			return new ReturnClass(Constant.FAILED, "이미 존재하는 코드입니다. 선택 후 수정하여주세요.");
		}
		if (1 != stdCdMapper.procStdCodeInfo(stdCodeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delStdCodeInfo(StdCodeVO codeVO) {
		if(StringUtil.isEmpty(codeVO.getCode_group())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}
		if(!"Y".equals(codeVO.getYn_code_group()) && StringUtil.isEmpty(codeVO.getCode_value())) {
			return new ReturnClass(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
		}
		if (1 != stdCdMapper.delStdCodeInfo(codeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	@CacheEvict(value={"CacheStdCode","CacheListStdCode"}, allEntries=true)
	public ReturnClass clearCacheStdCode() {
		logger.debug("Cache clear : [CacheStdCode,CacheListStdCode]");
		// SA 정보 초기화
		String targetUrl = getNvlStdCodeName("_CONF_SYSTEM","URL","");
		if(StringUtil.isNotEmpty(targetUrl)) {
			URLConnection url = new URLConnection();
			targetUrl += "/cache/clearCacheStdCode.json";
			url.sendReqGET(targetUrl, "");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public StdCodeVO getMaxStdCodeInfo(StdCodeVO codeVO) {
		return stdCdMapper.getMaxStdCodeInfo(codeVO);
	}
	@Override
	public ReturnClass modifyListSeq(List<StdCodeVO> codeVO) {
		for (StdCodeVO cv : codeVO) {
			if (1 != stdCdMapper.modifyListSeq(cv)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public HashMap<String, StdCodeVO> getStdCodeMapInfo(String code_group){
		HashMap<String, StdCodeVO> codeMap = new HashMap<String, StdCodeVO>();
		StdCodeForm codeForm = new StdCodeForm();
		codeForm.setCode_group(code_group);
		List<StdCodeVO> codeList = stdCdMapper.listStdCode(codeForm);
		for(StdCodeVO info : codeList){
			codeMap.put(info.getCode_value(), info);
		}
		return codeMap;
	}
}