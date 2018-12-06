package com.koscom.env.dao;

import java.util.List;

import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;


/**
 * CommCd Dao Interface 
 * 
 * @author dhkim
 *
 */
public interface CodeMapper {

	 /**
	  * code_group , code_value 를 파라메터로 하여 CodeInfo 객체 1개를 반환합니다.
	 * @param cd
	 * @return
	 */
	CodeInfo getCodeInfo(CodeInfo cd);
	
	/**
	  * code_group , nm_code 를 파라메터로 하여 code_valuer값을 반환합니다.
	 * @param cd
	 * @return
	 */
	String getCodeId(CodeInfo cd);
	
	/**
	 * 해당 파라미터로 조회된 결과 CodeInfo객체 List를 반환합니다. 
	 * @param cd
	 * @return
	 */
	List<CodeInfo> listCodeInfo(CodeInfo cd);

	/**
	 * codeForm 파라메터로 CodeInfo객체 List를 반환합니다. 
	 * @param codeForm
	 * @return
	 */
	List<CodeVO> listCode(CodeForm codeForm);

	/**
	 * 코드정보를 등록/수정합니다.
	 * @param codeInfo
	 * @return
	 */
	int procCodeInfo(CodeInfo codeInfo);

	/**
	 * 코드정보를 삭제합니다.
	 * @param codeInfo
	 * @return
	 */
	int delCodeInfo(CodeInfo codeInfo);

	/**
	 * 코드그룹의 seq, value의 최대값을 가져옴
	 * @return
	 */
	CodeVO getMaxCodeInfo(CodeVO codeVO);
	
	/**
	 * SMS 상용구 순서변경
	 * @param codeVO
	 */
	int modifyListSeq(CodeVO codeVO);

	/**
	 * openAPI 약관동의를 가져옴
	 * @return
	 */
	CodeInfo getAgreeTerm(CodeInfo codeInfo);
}
