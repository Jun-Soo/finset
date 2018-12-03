package com.koscom.batch.code.mapper;

import java.util.List;

import com.koscom.batch.code.domain.CodeInfo;

/**
 * CommCd Dao Interface
 *
 * @author dhkim
 *
 */
public interface CodeMapper {

	/**
	 * 해당 파라미터로 조회된 결과 CodeInfo객체 List를 반환합니다.
	 * @param codeInfo
	 * @return
	 */
	List<CodeInfo> listCodeInfo(CodeInfo codeInfo);

	/**
	 * code_value값을 이용해서 nm_code가져오기
	 * @param codeInfo
	 * @return
	 */
	String getCommCdNmCode(CodeInfo codeInfo);

}
