package com.koscom.edoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocInfo;
import com.koscom.edoc.model.EdocVO;
import com.koscom.edoc.service.EdocManager;
import com.koscom.edoc.service.EdocWebManager;
import com.koscom.util.ReturnClass;

@Service("edocWebManager")
public class EdocWebManagerImpl implements EdocWebManager {

	@Autowired
	private EdocManager codeManager;
	@Autowired
	EdocManager edocManager;
	@Override
	public EdocInfo getEdocInfo(EdocInfo codeInfo) {
		return codeManager.getEdocInfo(codeInfo);
	}

	@Override
	public List<EdocInfo> listEdocInfo(EdocInfo codeInfo) {
		return codeManager.listEdocInfo(codeInfo);
	}
	
	/**
	 * Name   :getEdocDetail
	 * Desc   : 금융사전문정보 조회
	 * input  : EdocForm
	 * output : EdocVO
	 * Date   : 2017.09.15
	**/
	@Override
	public EdocVO getEdocDetail(EdocForm edocForm) {
		return edocManager.getEdocDetail(edocForm);
	}

	@Override
	public EdocInfo getEdocInfo(String seq_edoc) {
		// TODO Auto-generated method stub
		return edocManager.getEdocInfo(seq_edoc);
	}

	@Override
	public EdocInfo getEdoc(EdocForm edocForm) {
		// TODO Auto-generated method stub
		return edocManager.getEdoc(edocForm);
	}

	@Override
	public List<EdocVO> listEdoc(EdocForm edocForm) {
		// TODO Auto-generated method stub
		return edocManager.listEdoc(edocForm);
	}

	@Override
	public ReturnClass procEdocInfo(EdocVO edocVO) {
		// TODO Auto-generated method stub
		return edocManager.procEdocInfo(edocVO);
	}

	@Override
	public ReturnClass delEdocInfo(EdocVO edocVO) {
		// TODO Auto-generated method stub
		return edocManager.delEdocInfo(edocVO);
	}

	@Override
	public ReturnClass clearCacheEdoc() {
		// TODO Auto-generated method stub
		return edocManager.clearCacheEdoc();
	}

	@Override
	public EdocVO getMaxEdocInfo(EdocVO edocVO) {
		// TODO Auto-generated method stub
		return edocManager.getMaxEdocInfo(edocVO);
	}

	@Override
	public ReturnClass modifyListSeq(List<EdocVO> list) {
		// TODO Auto-generated method stub
		return edocManager.modifyListSeq(list);
	}

	@Override
	public List<String> listNmEdoc(EdocForm edocForm) throws Exception {
		// TODO Auto-generated method stub
		return edocManager.listNmEdoc(edocForm);
	}

	@Override
	public String getNmEdoc(EdocForm edocForm) {
		// TODO Auto-generated method stub
		return edocManager.getNmEdoc(edocForm);
	}

	@Override
	public int getEdocCnt(EdocVO edocVO) {
		// TODO Auto-generated method stub
		return edocManager.getEdocCnt(edocVO);
	}

}
