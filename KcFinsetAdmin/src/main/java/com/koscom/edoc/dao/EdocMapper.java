package com.koscom.edoc.dao;

import java.util.List;

import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocInfo;
import com.koscom.edoc.model.EdocVO;

public interface EdocMapper {
	EdocInfo getEdocInfo(EdocInfo cd);
	List<EdocInfo> listEdocInfo(EdocInfo cd);
	List<EdocVO> listEdoc(EdocForm codeForm);

	/**
	 * Name   :getEdocDetail
	 * Desc   : 금융사전문정보 조회
	 * input  : EdocForm
	 * output : EdocVO
	 * Date   : 2017.09.15
	**/
	EdocVO getEdocDetail(EdocForm edocForm);

	int procEdocInfo(EdocInfo codeInfo);
	int delEdocInfo(EdocInfo codeInfo);
	EdocVO getMaxEdocInfo(EdocVO codeVO);
	int modifyListSeq(EdocVO codeVO);
	List<String> listNmEdoc(EdocForm edocForm);
	String getNmEdoc(EdocForm edocForm);

	/**
	 * 전문 중복체크
	 * @param edocVO
	 * @return
	 */
	int getEdocCnt(EdocVO edocVO);
}
