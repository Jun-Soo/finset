package com.koscom.fccode.service;

import com.koscom.fccode.model.FcCodeForm;
import com.koscom.finance.model.FcEdocVO;


public interface FcCodeManager {
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


}
