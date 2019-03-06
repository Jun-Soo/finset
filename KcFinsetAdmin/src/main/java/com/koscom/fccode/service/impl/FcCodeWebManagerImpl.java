package com.koscom.fccode.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.FcEdocRepeatInfo;
import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.model.FcCodeInfo;
import com.koscom.fccode.model.FcCodeVO;
import com.koscom.fccode.service.FcCodeManager;
import com.koscom.fccode.service.FcCodeWebManager;
import com.koscom.finance.model.FcEdocVO;
import com.koscom.util.ReturnClass;

@Service("fccodeWebManager")
public class FcCodeWebManagerImpl implements FcCodeWebManager {
	@Autowired
	private FcCodeManager fcCodeManager;
	@Override
	public FcCodeInfo getFcCodeInfo(FcCodeInfo codeInfo) {
		return fcCodeManager.getFcCodeInfo(codeInfo);
	}
	@Override
	public List<FcCodeInfo> listFcCodeInfo(FcCodeInfo codeInfo) {
		return fcCodeManager.listFcCodeInfo(codeInfo);
	}
	@Override
	public FcCodeInfo getFcCodeInfo(String group, String id) {
		// TODO Auto-generated method stub
		return fcCodeManager.getFcCodeInfo(group, id);
	}
	@Override
	public String getFcCodeName(String group, String id) {
		// TODO Auto-generated method stub
		return fcCodeManager.getFcCodeName(group, id);
	}
	@Override
	public String getNvlFcCodeName(String group, String id, String defaultStr) {
		// TODO Auto-generated method stub
		return fcCodeManager.getNvlFcCodeName(group, id, defaultStr);
	}
	@Override
	public List<FcCodeInfo> listFcCodeInfo(String group) {
		// TODO Auto-generated method stub
		return fcCodeManager.listFcCodeInfo(group);
	}
	@Override
	public List<FcCodeVO> listFcCode(FcCodeForm codeForm) {
		// TODO Auto-generated method stub
		return fcCodeManager.listFcCode(codeForm);
	}
	@Override
	public FcCodeInfo getFcCode(FcCodeForm codeForm) {
		// TODO Auto-generated method stub
		return fcCodeManager.getFcCode(codeForm);
	}
	@Override
	public ReturnClass procFcCodeInfo(FcCodeVO codeVO) {
		// TODO Auto-generated method stub
		return fcCodeManager.procFcCodeInfo(codeVO);
	}
	@Override
	public ReturnClass delFcCodeInfo(FcCodeVO codeVO) {
		// TODO Auto-generated method stub
		return fcCodeManager.delFcCodeInfo(codeVO);
	}
	@Override
	public ReturnClass clearCacheFcCode() {
		// TODO Auto-generated method stub
		return fcCodeManager.clearCacheFcCode();
	}
	@Override
	public FcCodeVO getMaxFcCodeInfo(FcCodeVO codeVO) {
		// TODO Auto-generated method stub
		return fcCodeManager.getMaxFcCodeInfo(codeVO);
	}
	@Override
	public ReturnClass modifyListSeq(List<FcCodeVO> codeVO) {
		// TODO Auto-generated method stub
		return fcCodeManager.modifyListSeq(codeVO);
	}
	@Override
	public HashMap<String, FcCodeVO> getFcCodeMapInfo(String code_group) {
		// TODO Auto-generated method stub
		return fcCodeManager.getFcCodeMapInfo(code_group);
	}
	@Override
	public ReturnClass listSrchFcCodeInfoJson(FcCodeForm form) {
		// TODO Auto-generated method stub
		return fcCodeManager.listSrchFcCodeInfoJson(form);
	}
	@Override
	public List<String> listNmFcEdoc(FcCodeForm codeForm) throws Exception {
		// TODO Auto-generated method stub
		return fcCodeManager.listNmFcEdoc(codeForm);
	}
	@Override
	public void updateFcCodeSeq(FcCodeVO codeVO) {
		// TODO Auto-generated method stub
		fcCodeManager.updateFcCodeSeq(codeVO);
	}
	@Override
	public FcEdocVO listFcEdocInfo(FcCodeForm fcCodeForm) {
		// TODO Auto-generated method stub
		return fcCodeManager.listFcEdocInfo(fcCodeForm);
	}
	@Override
	public String getFcCommCdToFcCd(FcCodeForm fcCodeForm) {
		// TODO Auto-generated method stub
		return fcCodeManager.getFcCommCdToFcCd(fcCodeForm);
	}
	@Override
	public String getFcCdToFcCommCd(FcCodeForm fcCodeForm) {
		// TODO Auto-generated method stub
		return fcCodeManager.getFcCdToFcCommCd(fcCodeForm);
	}
	@Override
	public ReturnClass listSrchFcRepeatJson(FcCodeForm form) {
		return fcCodeManager.listSrchFcRepeatJson(form);
	}
	@Override
	public void createFcRepeat(FcEdocRepeatInfo fcedocrepeatinfo) {
		fcCodeManager.createFcRepeat(fcedocrepeatinfo);
	}
	@Override
	public void deleteFcRepeat(FcEdocRepeatInfo fcedocrepeatinfo) {
		fcCodeManager.deleteFcRepeat(fcedocrepeatinfo);
	}
	@Override
	public FcEdocVO listFcEdocRepeatInfo(FcCodeForm fcCodeForm) {
		return fcCodeManager.listFcEdocRepeatInfo(fcCodeForm);
	}
}
