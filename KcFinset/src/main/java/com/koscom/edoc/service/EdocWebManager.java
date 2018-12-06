package com.koscom.edoc.service;

import java.util.List;

import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocInfo;
import com.koscom.edoc.model.EdocVO;
import com.koscom.util.ReturnClass;

public interface EdocWebManager {
	public EdocInfo getEdocInfo(String seq_edoc);
	public EdocInfo getEdocInfo(EdocInfo edocInfo);
	public EdocInfo getEdoc(EdocForm edocForm);
	
	public List<EdocInfo> listEdocInfo(EdocInfo edocInfo);
	public List<EdocVO> listEdoc(EdocForm edocForm);
	
	public ReturnClass procEdocInfo(EdocVO edocVO);
	public ReturnClass delEdocInfo(EdocVO edocVO);

	public ReturnClass clearCacheEdoc();
	public EdocVO getMaxEdocInfo(EdocVO edocVO);
	public ReturnClass modifyListSeq(List<EdocVO> list);
	public List<String> listNmEdoc(EdocForm edocForm) throws Exception;
	public String getNmEdoc(EdocForm edocForm);
	
	/**
	 * 전문 중복체크
	 * @param edocVO
	 * @return
	 */
	int getEdocCnt(EdocVO edocVO);
	
	/**
	 * Name   :getEdocDetail
	 * Desc   : 금융사전문정보 조회
	 * input  : EdocForm
	 * output : EdocVO
	 * Date   : 2017.09.15
	**/
	EdocVO getEdocDetail(EdocForm edocForm);
}