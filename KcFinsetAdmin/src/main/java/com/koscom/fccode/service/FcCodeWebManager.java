package com.koscom.fccode.service;

import java.util.HashMap;
import java.util.List;

import com.koscom.domain.FcEdocRepeatInfo;
import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.model.FcCodeInfo;
import com.koscom.fccode.model.FcCodeVO;
import com.koscom.finance.model.FcEdocVO;
import com.koscom.util.ReturnClass;

public interface FcCodeWebManager {

	/**
	 * code_group , code_value 를 파라메터로 하여 FcCodeInfo 객체 1개를 반환합니다. (캐시)
	 * 
	 * @param codeInfo
	 * @return
	 */
	public FcCodeInfo getFcCodeInfo(FcCodeInfo codeInfo);
	
	/**
	 * {@link #getFcCodeInfo(FcCodeInfo codeInfo)} method (캐시)
	 */
	public FcCodeInfo getFcCodeInfo(String group , String id);
	
	/**
	 * 코드그룹ID, 코드ID에 해당하는 밸류값 가져옴
	 * {@link #getFcCodeInfo(FcCodeInfo codeInfo)} method (캐시)
	 * @param group
	 * @param id
	 * @return
	 */
	public String getFcCodeName(String group, String id);
	
	/**
	 * 코드그룹ID, 코드ID에 해당하는 밸류값 가져옴, 코드가 없으면 디폴트값 리턴
	 * {@link #getFcCodeInfo(FcCodeInfo codeInfo)} method (캐시)
	 * @param group
	 * @param id
	 * @param defaultStr
	 * @return
	 */
	public String getNvlFcCodeName(String group, String id, String defaultStr);
	
	/**
	 * 해당 파라미터로 조회된 결과 FcCodeInfo객체 List를 반환합니다. (캐시)
	 * @param codeInfo
	 * @return
	 */
	public List<FcCodeInfo> listFcCodeInfo(FcCodeInfo codeInfo);
	
	/**
	 * {@link #listFcCodeInfo(FcCodeInfo codeInfo)} method (캐시)
	 */
	public List<FcCodeInfo> listFcCodeInfo(String group);

	/**
	 * 코드 목록을 조회합니다.
	 * @param codeForm
	 * @return
	 */
	public List<FcCodeVO> listFcCode(FcCodeForm codeForm);

	/**
	 * 코드 단건을 조회합니다.
	 * @param codeForm
	 * @return
	 */
	public FcCodeInfo getFcCode(FcCodeForm codeForm);

	/**
	 * 코드정보를 수정합니다.
	 * @param codeVO
	 * @return
	 */
	public ReturnClass procFcCodeInfo(FcCodeVO codeVO);

	/**
	 * 코드정보를 삭제합니다.
	 * @param codeVO
	 * @return
	 */
	public ReturnClass delFcCodeInfo(FcCodeVO codeVO);

	/**
	 * 코드 캐시를 초기화 합니다.
	 * @return
	 */
	public ReturnClass clearCacheFcCode();

	/**
	 * 코드그룹의 seq, value의 최대값을 가져옴
	 * @return FcCodeVO
	 */
	public FcCodeVO getMaxFcCodeInfo(FcCodeVO codeVO);
	
	/**
	 * SMS 상용구 순서변경
	 * @param codeVO
	 * @return
	 */
	public ReturnClass modifyListSeq(List<FcCodeVO> codeVO);
	
	/**
	 * 해당 코드그룹의 코드상세값을 해쉬맵으로 반환
	 * @param code_group
	 * @return
	 */
	public HashMap<String, FcCodeVO> getFcCodeMapInfo(String code_group);
	public ReturnClass listSrchFcCodeInfoJson(FcCodeForm form);
	public List<String> listNmFcEdoc(FcCodeForm codeForm) throws Exception;
	
	/**
	 * 코드seq를 수정합니다.
	 * @param codeVO
	 * @return
	 */
	void updateFcCodeSeq(FcCodeVO codeVO);
	
	/**
	 * 전문 정보 가져오기
	 * @param fcCodeForm
	 * @return fcEdocVO
	 */
	public FcEdocVO listFcEdocInfo(FcCodeForm fcCodeForm);
	
	/**
	 * getFcCommCdToFcCd
	 * 금웅사전문코드 <- 전문공통코드 값 매핑 결과 조회
	 * @param FcCodeForm
	 * @return String
	**/
	public String getFcCommCdToFcCd(FcCodeForm fcCodeForm);
	
	/**
	 * getFcCdToFcCommCd
	 * 금웅사전문코드 -> 전문공통코드 값 매핑 결과 조회 
	 * @param FcCodeForm
	 * @return String
	**/
	public String getFcCdToFcCommCd(FcCodeForm fcCodeForm);
	
	/**
	 * listFcEdocRepeatInfo
	 * 전문 반복 구분의 리스트를 가져온다.
	 * @param fcCodeForm
	 * @return fcEdocVO
	 */
	public FcEdocVO listFcEdocRepeatInfo(FcCodeForm fcCodeForm);
	
	/**
	 *부모반복코드명 검색.
	 * @return
	 */
	public ReturnClass listSrchFcRepeatJson(FcCodeForm form);
	
	/**
	 * 부모코드반목명 insert
	 * @param FcEdocRepeatInfo
	 */
	void createFcRepeat(FcEdocRepeatInfo fcedocrepeatinfo);
	
	/**
	 * 부모코드반목명 delete
	 * @param FcEdocRepeatInfo
	 */
	void deleteFcRepeat(FcEdocRepeatInfo fcedocrepeatinfo);
}
