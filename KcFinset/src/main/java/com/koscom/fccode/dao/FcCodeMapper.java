package com.koscom.fccode.dao;

import java.util.List;

import com.koscom.fccode.model.FcCodeForm;
import com.koscom.finance.model.FcEdocForm;

public interface FcCodeMapper {

	/**
	 * 금융사 코드 조회
	 * @param string
	 */
	String selectCdFc(String string);

	/**
	 * 금융사 코드 조회 업종코드 추가
	 * @param string
	 */
	String selectCdFcWithCdFin(FcCodeForm fcCodeForm);
	
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
}