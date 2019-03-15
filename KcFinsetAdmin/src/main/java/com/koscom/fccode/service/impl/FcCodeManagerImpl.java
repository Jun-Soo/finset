package com.koscom.fccode.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.domain.FcEdocRepeatInfo;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.fccode.dao.FcCodeMapper;
import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.model.FcCodeInfo;
import com.koscom.fccode.model.FcCodeVO;
import com.koscom.fccode.service.FcCodeManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("fcCodeManager")
public class FcCodeManagerImpl implements FcCodeManager {

	private static final Logger logger = LoggerFactory.getLogger(FcCodeManagerImpl.class);
	
	private CodeManager codeManager;
	private FcCodeManager fcCodeManager;
	
	@Autowired
	private FcCodeMapper fcCdMapper;

	@Override
	@Cacheable(value="CacheFcCode" , key="#fcCodeInfo.code_group + #fcCodeInfo.code_value")
	public FcCodeInfo getFcCodeInfo(FcCodeInfo codeInfo) {
		return fcCdMapper.getFcCodeInfo(codeInfo);
	}
	@Override
	@Cacheable("CacheFcCode")
	public FcCodeInfo getFcCodeInfo(String group, String id) {
		FcCodeInfo fcCodeInfo = new FcCodeInfo();
		fcCodeInfo.setCode_group(group);
		fcCodeInfo.setCode_value(id);
		return fcCdMapper.getFcCodeInfo(fcCodeInfo);
	}
	
	@Override
	public String getFcCodeName(String group, String id){
		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return id;
		}
		codeManager = (CodeManager) SpringApplicationContext.getBean("codeManager");
		CodeInfo fcCodeInfo = codeManager.getCodeInfo(group, id);
		if(fcCodeInfo == null) return id;
		return StringUtil.nullToString(fcCodeInfo.getNm_code(), id);
	}
	@Override
	public String getNvlFcCodeName(String group, String id, String defaultStr) {
		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return defaultStr;
		}
		String returnValue = getFcCodeName(group, id);
		// codeValue 와 returnValue 가 같다는 의미는 
		// code name이 null 이란 의미이다
		if(StringUtil.isEmpty(returnValue) || id.equals(returnValue))
			return defaultStr;
		return returnValue;
	}
	@Override
	@Cacheable(value="CacheListFcCode" , key="#codeInfo.code_group")
	public List<FcCodeInfo> listFcCodeInfo(FcCodeInfo codeInfo) {
		return fcCdMapper.listFcCodeInfo(codeInfo);
	}
	@Override
	@Cacheable(value="CacheListFcCode")
	public List<FcCodeInfo> listFcCodeInfo(String group) {
		FcCodeInfo codeInfo = new FcCodeInfo();
		codeInfo.setCode_group(group);
		return fcCdMapper.listFcCodeInfo(codeInfo);
	}
	@Override
	public List<FcCodeVO> listFcCode(FcCodeForm fcCodeForm) {
		return fcCdMapper.listFcCode(fcCodeForm);
	}
	@Override
	public FcCodeInfo getFcCode(FcCodeForm fcCodeForm) {
		FcCodeInfo fcCodeInfo = new FcCodeInfo();
		if(StringUtil.isEmpty(fcCodeForm.getCd_fc()))
			return fcCodeInfo;
		if(StringUtil.isEmpty(fcCodeForm.getNo_edoc()))
			return fcCodeInfo;
		if(StringUtil.isEmpty(fcCodeForm.getCode_group()))
			return fcCodeInfo;
		fcCodeInfo.setCd_fc(fcCodeForm.getCd_fc());
		fcCodeInfo.setNo_edoc(fcCodeForm.getNo_edoc());
		fcCodeInfo.setCode_group(fcCodeForm.getCode_group());
		fcCodeInfo.setType_txrx(fcCodeForm.getType_txrx());
		if ("Y".equals(fcCodeForm.getYn_code_group())) {
			fcCodeInfo.setCode_value(" ");
		} else {
			fcCodeInfo.setCode_value(fcCodeForm.getCode_value());
		}
		return fcCdMapper.getFcCodeInfo(fcCodeInfo);
	}

	@Override
	public ReturnClass procFcCodeInfo(FcCodeVO fcCodeVO) {
		logger.info("==========procFcCodeInfo=====================");
		if(StringUtil.isEmpty(fcCodeVO.getCode_group())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}
		if("Y".equals(fcCodeVO.getYn_code_group())) {
			fcCodeVO.setCode_value(" ");
		}
		if(!"Y".equals(fcCodeVO.getYn_code_group()) && StringUtil.isEmpty(fcCodeVO.getCode_value())) {
			return new ReturnClass(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
		}
		// insert 구분
		if("C".equals(fcCodeVO.getCd_proc_type()) && fcCdMapper.getFcCodeInfo(fcCodeVO) != null) {
			return new ReturnClass(Constant.FAILED, "이미 존재하는 코드입니다. 선택 후 수정하여주세요.");
		}
		if (1 != fcCdMapper.procFcCodeInfo(fcCodeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		logger.info("fcCodeVO.getParent_code_group() : "+fcCodeVO.getParent_code_group());
		logger.info("fcCodeVO.getParent_code_group() : "+fcCodeVO.getParent_code_group());
		logger.info("fcCodeVO.getParent_code_group() : "+fcCodeVO.getParent_code_group());
		logger.info("fcCodeVO.getParent_code_group() : "+fcCodeVO.getParent_code_group());
		
		logger.info("=========deleteFcRepeat=====================");
		FcEdocRepeatInfo repeatinfo = new FcEdocRepeatInfo();
		repeatinfo.setCd_fc(fcCodeVO.getCd_fc());
		repeatinfo.setNo_edoc(fcCodeVO.getNo_edoc());
		repeatinfo.setSeq_order(fcCodeVO.getSeq_order());
		repeatinfo.setType_txrx(fcCodeVO.getType_txrx());
		repeatinfo.setParent_code_group(fcCodeVO.getItem_repeat_tag());
		repeatinfo.setReal_code_group(fcCodeVO.getCode_group());
		repeatinfo.setId_frt(fcCodeVO.getId_frt());
		repeatinfo.setDt_frt(fcCodeVO.getDt_frt());
		
		fcCdMapper.deleteFcRepeat(repeatinfo);
		
		if(StringUtil.isNotEmpty(fcCodeVO.getParent_code_group())){
			fcCdMapper.createFcRepeat(repeatinfo);
		}
		
		
		//그룹이 Y 고 item_tag_value 가 null 이면  ==> 기존 item_tag 가 변경된것으로 인식
		if("Y".equals(fcCodeVO.getYn_code_group()) && StringUtil.isEmpty(fcCodeVO.getItem_tag_value())) {
			if (1 > fcCdMapper.updateFcItemTagCodeInfo(fcCodeVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFcCodeInfo(FcCodeVO codeVO) {
		if(StringUtil.isEmpty(codeVO.getCode_group())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}
		if(!"Y".equals(codeVO.getYn_code_group()) && StringUtil.isEmpty(codeVO.getCode_value())) {
			return new ReturnClass(Constant.FAILED, "코드ID가 입력되지 않았습니다.");
		}
		
		if (1 > fcCdMapper.delFcCodeInfo(codeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		
		logger.info("=========delFcCodeInfo=====================");
		logger.info("=========codeVO====================="+codeVO.toString());
		logger.info("=========delFcCodeInfo=====================");
		FcEdocRepeatInfo repeatinfo = new FcEdocRepeatInfo();
		repeatinfo.setCd_fc(codeVO.getCd_fc());
		repeatinfo.setNo_edoc(codeVO.getNo_edoc());
		repeatinfo.setType_txrx(codeVO.getType_txrx());
		repeatinfo.setReal_code_group(codeVO.getCode_group());
		
		fcCdMapper.deleteFcRepeat(repeatinfo);
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	@CacheEvict(value={"CacheFcCode","CacheListFcCode"}, allEntries=true)
	public ReturnClass clearCacheFcCode() {
		logger.info("Cache clear : [CacheFcCode,CacheListFcCode]");
		// SA 정보 초기화
		String targetUrl = getNvlFcCodeName("_CONF_SYSTEM","URL","");
		if(StringUtil.isNotEmpty(targetUrl)) {
			URLConnection url = new URLConnection();
			targetUrl += "/cache/clearCacheFcCode.json";
			url.sendReqGET(targetUrl, "");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public FcCodeVO getMaxFcCodeInfo(FcCodeVO codeVO) {
		return fcCdMapper.getMaxFcCodeInfo(codeVO);
	}
	@Override
	public ReturnClass modifyListSeq(List<FcCodeVO> codeVO) {
		for (FcCodeVO cv : codeVO) {
			if (1 != fcCdMapper.modifyListSeq(cv)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public HashMap<String, FcCodeVO> getFcCodeMapInfo(String code_group){
		HashMap<String, FcCodeVO> codeMap = new HashMap<String, FcCodeVO>();
		FcCodeForm codeForm = new FcCodeForm();
		codeForm.setCode_group(code_group);
		List<FcCodeVO> codeList = fcCdMapper.listFcCode(codeForm);
		for(FcCodeVO info : codeList){
			codeMap.put(info.getCode_value(), info);
		}
		return codeMap;
	}
	@Override
	public ReturnClass listSrchFcCodeInfoJson(FcCodeForm form) {
		JSONArray list = new JSONArray();
		JSONObject object = null;
		List<FcCodeVO> res = fcCdMapper.listSrchFcCodeInfoJson(form);
		for (int i=0; i < res.size() ; i++ ) {
			object = new JSONObject();
			object.put("auto_com_txt", res.get(i).getAuto_com_txt());
			object.put("stdcode_group", res.get(i).getCode_group());
			object.put("stdnm_code", res.get(i).getNm_code());
			list.add(object);
		}
		return new ReturnClass( Constant.SUCCESS, "정상적으로 처리되었습니다.", list );
	}
	@Override
	public List<String> listNmFcEdoc(FcCodeForm codeForm) throws Exception {
		List<String> res = fcCdMapper.listNmFcEdoc(codeForm);
		return res;
	}
	
	@Override
	public void updateFcCodeSeq(FcCodeVO fcCodeVO) {
		fcCdMapper.updateFcCodeSeq(fcCodeVO);
		
		logger.info("=========fcCodeVO====================="+fcCodeVO.toString());
		logger.info("=========deleteFcRepeat=====================");
		FcEdocRepeatInfo repeatinfo = new FcEdocRepeatInfo();
		repeatinfo.setCd_fc(fcCodeVO.getCd_fc());
		repeatinfo.setNo_edoc(fcCodeVO.getNo_edoc());
		repeatinfo.setSeq_order(fcCodeVO.getSeq_order());
		repeatinfo.setType_txrx(fcCodeVO.getType_txrx());
		repeatinfo.setParent_code_group(fcCodeVO.getParent_code_group());
		repeatinfo.setReal_code_group(fcCodeVO.getCode_group());
		repeatinfo.setId_frt(fcCodeVO.getId_frt());
		repeatinfo.setDt_frt(fcCodeVO.getDt_frt());
		
		logger.info("=========repeatinfo====================="+repeatinfo.toString());
		fcCdMapper.deleteFcRepeat(repeatinfo);
		
		if(StringUtil.isNotEmpty(fcCodeVO.getParent_code_group())){
			fcCdMapper.createFcRepeat(repeatinfo);
		}
	}
	
	/**
	 * getFcCommCdToFcCd
	 * 금웅사전문코드 <- 전문공통코드 값 매핑 결과 조회
	 * @param FcCodeForm
	 * @return String
	**/
	@Override
	public String getFcCommCdToFcCd(FcCodeForm fcCodeForm){
		return fcCdMapper.getFcCommCdToFcCd(fcCodeForm);
	}
	
	/**
	 * getFcCdToFcCommCd
	 * 금웅사전문코드 -> 전문공통코드 값 매핑 결과 조회 
	 * @param FcCodeForm
	 * @return String
	**/
	@Override
	public String getFcCdToFcCommCd(FcCodeForm fcCodeForm){
		return fcCdMapper.getFcCdToFcCommCd(fcCodeForm);
	}
	
	/**
	 * listSrchFcRepeat
	 * 부모코드 반복 검색
	 * @param fcCodeForm
	 * @return fcCodeVO
	 */
	@Override
	public ReturnClass listSrchFcRepeatJson(FcCodeForm form) {
		JSONArray list = new JSONArray();
		JSONObject object = null;
		
		List<FcCodeVO> res = fcCdMapper.listSrchFcRepeatJson(form);
		for (int i=0; i < res.size() ; i++ ) {
			object = new JSONObject();
			object.put("auto_com_txt", res.get(i).getAuto_com_txt());
			object.put("fcedoc_code_group", res.get(i).getCode_group());
			object.put("fcedoc_nm_code", res.get(i).getNm_code());
			list.add(object);
		}
		return new ReturnClass( Constant.SUCCESS, "정상적으로 처리되었습니다.", list );
	}
	/**
	 * 부모코드반목명 insert
	 * @param FcEdocRepeatInfo
	 */
	@Override
	public void createFcRepeat(FcEdocRepeatInfo fcedocrepeatinfo) {
		fcCdMapper.createFcRepeat(fcedocrepeatinfo);
	}
	/**
	 * 부모코드반목명 delete
	 * @param FcEdocRepeatInfo
	 */
	@Override
	public void deleteFcRepeat(FcEdocRepeatInfo fcedocrepeatinfo) {
		fcCdMapper.deleteFcRepeat(fcedocrepeatinfo);
	}
}