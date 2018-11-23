package com.koscom.env.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;

public interface CodeManager {

	/**
	 * code_group , code_value 를 파라메터로 하여 CodeInfo 객체 1개를 반환합니다. (캐시)
	 * 
	 * @param codeInfo
	 * @return
	 */
	public CodeInfo getCodeInfo(CodeInfo codeInfo);	
	public CodeInfo getCodeInfo(String group, String id);
	
	/**
	 * 해당 파라미터로 조회된 결과 CodeInfo객체 List를 반환합니다. (캐시)
	 * @param codeInfo
	 * @return
	 */
	public List<CodeInfo> listCodeInfo(CodeInfo codeInfo);
	public List<CodeInfo> listCodeInfo(String group);
	
	/**
	 * 코드 목록을 조회합니다.
	 * @param codeForm
	 * @return
	 */
	public List<CodeVO> listCode(CodeForm codeForm);
	
	/**
	 * 코드그룹ID, 코드명에 해당하는 밸류값 가져옴
	 * @param group
	 * @param name
	 * @return String
	 */
	public String getCodeId(String group, String name);
	
	/**
	 * 코드그룹ID, 코드ID에 해당하는 밸류값 가져옴
	 * {@link #getCodeInfo(CodeInfo codeInfo)} method (캐시)
	 * @param group
	 * @param id
	 * @return String
	 */
	public String getCodeName(String group, String id);
	
	/**
	 * 코드그룹으로 약관동의를 가져옴 ()
	 * 
	 * @param codeInfo
	 * @return
	 */
	public CodeInfo getAgreeTerm(CodeInfo codeInfo);
	
	/**
	 * 코드그룹ID, 코드ID에 해당하는 밸류값 가져옴, 코드가 없으면 디폴트값 리턴
	 * {@link #getCodeInfo(CodeInfo codeInfo)} method (캐시)
	 * @param group
	 * @param id
	 * @param defaultStr
	 * @return
	 */
	public String getNvlCodeName(String group, String id, String defaultStr);
	
	/**
	 * 해당 코드그룹의 코드상세값을 해쉬맵으로 반환
	 * @param code_group
	 * @return
	 */
	public HashMap<String, CodeVO> getCodeMapInfo(String code_group);
}
