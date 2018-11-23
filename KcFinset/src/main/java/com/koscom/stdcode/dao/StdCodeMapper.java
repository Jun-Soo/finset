package com.koscom.stdcode.dao;

import java.util.List;

import com.koscom.stdcode.model.StdCodeForm;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.model.StdCodeVO;

public interface StdCodeMapper {
	StdCodeInfo getStdCodeInfo(StdCodeInfo cd);
	List<StdCodeInfo> listStdCodeInfo(StdCodeInfo cd);
	List<StdCodeVO> listStdCode(StdCodeForm codeForm);
	int procStdCodeInfo(StdCodeInfo codeInfo);
	int delStdCodeInfo(StdCodeInfo codeInfo);
	StdCodeVO getMaxStdCodeInfo(StdCodeVO codeVO);
	int modifyListSeq(StdCodeVO codeVO);
}