package com.koscom.env.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.domain.AllowIpInfo;
import com.koscom.env.dao.IpMapper;
import com.koscom.env.model.AllowIpInfoForm;
import com.koscom.env.model.AllowIpInfoVO;
import com.koscom.env.service.IpManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;


@Service("ipManager")
public class IpManagerImpl implements IpManager {

	private static final Logger logger = LoggerFactory.getLogger(IpManagerImpl.class);

	@Autowired
	private IpMapper ipMapper;
	
	@Override
	public AllowIpInfoVO getAllowIpInfo(AllowIpInfo ipInfo) {
		return ipMapper.getAllowIpInfo(ipInfo);
	}
	
	@Override
	public AllowIpInfoVO getAllowIpInfo(String cd_system, String ip) {
		AllowIpInfo ipInfo = new AllowIpInfo();
		ipInfo.setCd_system(cd_system);
		ipInfo.setIp(ip);
		return ipMapper.getAllowIpInfo(ipInfo);
	}

	@Override
	public List<AllowIpInfoVO> listAllowIpInfo(AllowIpInfo ipInfo) {
		return ipMapper.listAllowIpInfo(ipInfo);
	}
	
	@Override
	@Cacheable("CacheIp")
	public boolean isVaildIp(String cd_system, String ipAddr) {
		
		boolean isVaildIp = false;

		if(StringUtil.isEmpty(cd_system) || StringUtil.isEmpty(ipAddr))
			return false;

		AllowIpInfo ipInfo = new AllowIpInfo();
		ipInfo.setCd_system(cd_system);
		List<AllowIpInfoVO> listIp = listAllowIpInfo(ipInfo);
		
		for (AllowIpInfoVO ipVO : listIp) {
			String preg = ipVO.getReg_exp_ip();
			if(!StringUtil.isEmpty(preg) && Pattern.matches(preg, ipAddr))
			{
				isVaildIp = true;
			}
		}
		
		return isVaildIp;
	}
	
	private String createPattern(String ip) {
		StringBuffer preg = new StringBuffer();
		String[] ip_section = ip.split("\\.");
		
		for(int i=0; i<ip_section.length; i++ ) {
			//1) "-" 판별하여 수만큼 OR 조건을 추가 //
			if( ip_section[i].indexOf("-") != -1 ) {
				String[] section = ip_section[i].split("-");
				preg.append("(");
				for(int cnt_section=Integer.valueOf(section[0]); cnt_section<=Integer.valueOf(section[1]); cnt_section++) {
					preg.append( cnt_section );
					if( Integer.valueOf(section[1]) != cnt_section ) {
						preg.append("|");
					}
				}
				preg.append(")");
			}
			
			//2) "," 를 판별하여 OR 조건을 추가//
			else if( ip_section[i].indexOf(",") != -1 ) {
				preg.append( "(" + ip_section[i].replace(",", "|") + ")" );
			}
			
			//3) "*" 판별하여 "(" 없이 추가//
			else if( ip_section[i].indexOf("*") != -1 ) {
				preg.append( "([0-9]|[0-9][0-9]|[0-9][0-9][0-9])" );
			}
			
			//4) 나머지 데이터 추가//
			else {
				preg.append( "(" + ip_section[i] + ")" );
			}
			
			//5) 한마디가 끝날때마다 "//." 을 추가//
			if( ip_section.length-1 != i ) {
				preg.append("\\.");
			}
		}
		
		return preg.toString();
	}

	@Override
	public ReturnClass procIpInfo(AllowIpInfoForm allowIpInfoForm) {

		if (StringUtil.isEmpty(allowIpInfoForm.getCd_system())) {
			return new ReturnClass(Constant.FAILED, "시스템 코드가 입력되지 않았습니다.");
		}
		
		if (StringUtil.isEmpty(allowIpInfoForm.getIp())) {
			return new ReturnClass(Constant.FAILED, "IP가 입력되지 않았습니다.");
		}
		
		allowIpInfoForm.setReg_exp_ip(createPattern(allowIpInfoForm.getIp()));

		if (1 != ipMapper.procIpInfo(allowIpInfoForm)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public AllowIpInfoVO getIpInfo(AllowIpInfoForm allowIpInfoForm) {
		return ipMapper.getIpInfo(allowIpInfoForm);
	}

	@Override
	public ReturnClass delIpInfo(AllowIpInfoForm allowIpInfoForm) {
		
		if (StringUtil.isEmpty(allowIpInfoForm.getCd_system())) {
			return new ReturnClass(Constant.FAILED, "시스템 코드가 입력되지 않았습니다.");
		}
		
		if (StringUtil.isEmpty(allowIpInfoForm.getIp())) {
			return new ReturnClass(Constant.FAILED, "IP가 입력되지 않았습니다.");
		}
		
		if (1 != ipMapper.delIpInfo(allowIpInfoForm)) {
		return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	@CacheEvict(value={"CacheIp"}, allEntries=true)
	public ReturnClass clearCacheIp() {
		logger.info("Cache clear : [CacheIp]");
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	
}
