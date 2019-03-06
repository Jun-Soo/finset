package com.koscom.env.dao;

import java.util.List;

import com.koscom.domain.AllowIpInfo;
import com.koscom.env.model.AllowIpInfoForm;
import com.koscom.env.model.AllowIpInfoVO;

public interface IpMapper {

	/**
	 * 해당 파라미터로 조회된 결과 IP객체를 반환합니다.
	 * @param ipInfo
	 * @return
	 */
	AllowIpInfoVO getAllowIpInfo(AllowIpInfo ipInfo);
	
	/**
	 * 해당 파라미터로 조회된 결과 IP객체 List를 반환합니다. 
	 * @param cd
	 * @return
	 */
	List<AllowIpInfoVO> listAllowIpInfo(AllowIpInfo ipInfo);
	
	/**
	 * 허용IP 등록/수정
	 * @param allowIpInfoForm
	 * @return
	 */
	int procIpInfo(AllowIpInfoForm allowIpInfoForm);
	
	/**
	 * 하나의 IP정보를 가져옴
	 * @return AllowIpInfoVO
	 */
	AllowIpInfoVO getIpInfo(AllowIpInfoForm allowIpInfoForm);
	
	/**
	 * 하나의 IP정보 삭제
	 * @return ReturnClass
	 */
	int delIpInfo(AllowIpInfoForm allowIpInfoForm);
}
