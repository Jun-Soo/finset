package com.koscom.env.service;

import java.util.List;

import com.koscom.domain.AllowIpInfo;
import com.koscom.env.model.AllowIpInfoForm;
import com.koscom.env.model.AllowIpInfoVO;
import com.koscom.util.ReturnClass;

public interface IpWebManager {

	/**
	 * 허용 IP 정보 조회(캐시)
	 * @param ipInfo
	 * @return
	 */
	public AllowIpInfoVO getAllowIpInfo(AllowIpInfo ipInfo);
	
	/**
	 * 허용 IP 정보 조회(캐시)
	 * @param cd_system
	 * @param ip
	 * @return
	 */
	public AllowIpInfoVO getAllowIpInfo(String cd_system, String ip);

	/**
	 * 허용IP 목록 조회
	 * @param ipInfo
	 * @return
	 */
	public List<AllowIpInfoVO> listAllowIpInfo(AllowIpInfo ipInfo);
	
	/**
	 * IP 검증
	 * @param cd_system
	 * @param ipAddr
	 * @return
	 */
	public boolean isVaildIp(String cd_system, String ipAddr);

	/**
	 * 허용IP 등록/수정
	 * @param allowIpInfoForm
	 * @return
	 */
	public ReturnClass procIpInfo(AllowIpInfoForm allowIpInfoForm);

	/**
	 * 하나의 IP정보를 가져옴
	 * @return AllowIpInfoVO
	 */
	public AllowIpInfoVO getIpInfo(AllowIpInfoForm allowIpInfoForm);
	
	/**
	 * 하나의 IP정보 삭제
	 * @return ReturnClass
	 */
	public ReturnClass delIpInfo(AllowIpInfoForm allowIpInfoForm);

	/**
	 * 허용Ip 캐시 초기화
	 * @return
	 */
	public ReturnClass clearCacheIp();
	
}
