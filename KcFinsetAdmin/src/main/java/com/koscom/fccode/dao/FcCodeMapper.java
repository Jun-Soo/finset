package com.koscom.fccode.dao;

import java.util.List;

import com.koscom.domain.FcEdocRepeatInfo;
import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.model.FcCodeInfo;
import com.koscom.fccode.model.FcCodeVO;
import com.koscom.finance.model.FcEdocForm;


public interface FcCodeMapper {
	FcCodeInfo getFcCodeInfo(FcCodeInfo cd);
	List<FcCodeInfo> listFcCodeInfo(FcCodeInfo cd);
	List<FcCodeVO> listFcCode(FcCodeForm codeForm);
	int procFcCodeInfo(FcCodeInfo codeInfo);
	int delFcCodeInfo(FcCodeInfo codeInfo);
	FcCodeVO getMaxFcCodeInfo(FcCodeVO codeVO);
	int modifyListSeq(FcCodeVO codeVO);
	List<FcCodeVO> listSrchFcCodeInfoJson(FcCodeForm form);
	List<String> listNmFcEdoc(FcCodeForm codeForm);
	int updateFcItemTagCodeInfo(FcCodeVO fcCodeVO);
	void updateFcCodeSeq(FcCodeVO fcCodeVO);

	/**
	 * listFcEdocInfo
	 * 해당 전문의 리스트를 가져온다.
	 * @param FcCodeForm
	 * @return List<FcEdocForm>
	**/
	List<FcEdocForm> listFcEdocInfo(FcCodeForm fcCodeForm);

	/**
	 * getFcCommCdToFcCd
	 * 금웅사전문코드 <- 전문공통코드 값 매핑 결과 조회
	 * @param FcCodeForm
	 * @return String
	**/
	String getFcCommCdToFcCd(FcCodeForm fcCodeForm);

	/**
	 * getFcCdToFcCommCd
	 * 금웅사전문코드 -> 전문공통코드 값 매핑 결과 조회
	 * @param FcCodeForm
	 * @return String
	**/
	String getFcCdToFcCommCd(FcCodeForm fcCodeForm);

	/**
	 * listFcEdocRepeatInfo
	 * 전문 반복 구분의 리스트를 가져온다.
	 * @param FcCodeForm
	 * @return List<FcEdocForm>
	**/
	List<FcEdocForm> listFcEdocRepeatInfo(FcCodeForm fcCodeForm);

	/**
	 * listSrchFcRepeatJson
	 * 부모반복코드명 검색.
	 * @param FcCodeForm
	 * @return List<FcCodeForm>
	**/
	List<FcCodeVO> listSrchFcRepeatJson(FcCodeForm form);

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

	/**
	 * 금융사 코드 조회
	 * @param string
	 */
	String selectCdFc(String string);

}