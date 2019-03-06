package com.koscom.env.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.AllowIpInfo;
import com.koscom.env.model.AllowIpInfoForm;
import com.koscom.env.model.AllowIpInfoVO;
import com.koscom.env.service.IpManager;
import com.koscom.env.service.IpWebManager;
import com.koscom.util.ReturnClass;

@Service("ipWebManager")
public class IpWebManagerImpl implements IpWebManager {

	@Autowired
	private IpManager ipManager;

	@Override
	public AllowIpInfoVO getAllowIpInfo(AllowIpInfo ipInfo) {
		// TODO Auto-generated method stub
		return ipManager.getAllowIpInfo(ipInfo);
	}

	@Override
	public AllowIpInfoVO getAllowIpInfo(String cd_system, String ip) {
		// TODO Auto-generated method stub
		return ipManager.getAllowIpInfo(cd_system, ip);
	}

	@Override
	public List<AllowIpInfoVO> listAllowIpInfo(AllowIpInfo ipInfo) {
		// TODO Auto-generated method stub
		return ipManager.listAllowIpInfo(ipInfo);
	}

	@Override
	public boolean isVaildIp(String cd_system, String ipAddr) {
		// TODO Auto-generated method stub
		return ipManager.isVaildIp(cd_system, ipAddr);
	}

	@Override
	public ReturnClass procIpInfo(AllowIpInfoForm allowIpInfoForm) {
		// TODO Auto-generated method stub
		return ipManager.procIpInfo(allowIpInfoForm);
	}

	@Override
	public AllowIpInfoVO getIpInfo(AllowIpInfoForm allowIpInfoForm) {
		// TODO Auto-generated method stub
		return ipManager.getIpInfo(allowIpInfoForm);
	}

	@Override
	public ReturnClass delIpInfo(AllowIpInfoForm allowIpInfoForm) {
		// TODO Auto-generated method stub
		return ipManager.delIpInfo(allowIpInfoForm);
	}

	@Override
	public ReturnClass clearCacheIp() {
		// TODO Auto-generated method stub
		return ipManager.clearCacheIp();
	}
}
