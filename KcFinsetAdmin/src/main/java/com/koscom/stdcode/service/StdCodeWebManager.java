package com.koscom.stdcode.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.stdcode.model.StdCodeForm;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.model.StdCodeVO;
import com.koscom.util.ReturnClass;

public interface StdCodeWebManager {
	public StdCodeInfo getStdCodeInfo(StdCodeInfo codeInfo);
	public StdCodeInfo getStdCodeInfo(String group , String id);
	public String getStdCodeName(String group, String id);
	public String getNvlStdCodeName(String group, String id, String defaultStr);
	public List<StdCodeInfo> listStdCodeInfo(StdCodeInfo codeInfo);
	public List<StdCodeInfo> listStdCodeInfo(String group);
	public List<StdCodeVO> listStdCode(StdCodeForm codeForm);
	public StdCodeInfo getStdCode(StdCodeForm codeForm);
	public ReturnClass procStdCodeInfo(StdCodeVO codeVO);
	public ReturnClass delStdCodeInfo(StdCodeVO codeVO);
	public ReturnClass clearCacheStdCode();
	public StdCodeVO getMaxStdCodeInfo(StdCodeVO codeVO);
	public ReturnClass modifyListSeq(List<StdCodeVO> codeVO);
	public HashMap<String, StdCodeVO> getStdCodeMapInfo(String code_group);

}
