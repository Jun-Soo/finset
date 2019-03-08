package com.koscom.env;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.domain.AllowIpInfo;
import com.koscom.env.model.AllowIpInfoForm;
import com.koscom.env.service.IpManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

@Controller
@RequestMapping("/ip")
public class IpController {

	private static final Logger logger = LoggerFactory.getLogger(IpController.class);
	
	@Autowired
	private IpManager ipManager;
	
	/**
	 * 허용IP 리스트 조회
	 * @param model
	 * @return
	 */
	@RequestMapping("/listIpInfoMain.crz")
	public String listAllowIpInfoMain(AllowIpInfo allowIpInfo, Model model) {
		
		model.addAttribute("List", ipManager.listAllowIpInfo(allowIpInfo));
		
		return "/env/listIpInfoMain";
	}
	
	@RequestMapping("/listIpInfo.crz")
	public String listAllowIpInfo(AllowIpInfo allowIpInfo, Model model) {
		
		model.addAttribute("List", ipManager.listAllowIpInfo(allowIpInfo));
		
		return "/env/listIpInfo";
	}
	
	/**
	 * 하나의 IP정보를 가져옴
	 * @param allowIpInfoVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/getIpInfo.crz")
	public String getIpInfo(AllowIpInfoForm allowIpInfoForm, Model model) {
		
		model.addAttribute("ipInfo", ipManager.getIpInfo(allowIpInfoForm));
		
		return "/env/formIpInfo";
	}
	
	/**
	 * 허용IP 추가, 수정
	 * @param allowIpInfoForm
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/procIpInfo.json")
	public String procIpInfo(HttpServletRequest request, AllowIpInfoForm allowIpInfoForm, Model model) throws Exception {
		SessionUtil session = new SessionUtil(request);
		allowIpInfoForm.setId_frt(session.getUserId());
		allowIpInfoForm.setId_lst(session.getUserId());
		
		ReturnClass returnClass = ipManager.procIpInfo(allowIpInfoForm);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 하나의 IP정보 삭제
	 * @param allowIpInfoForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/delIpInfo.json")
	public String delIpInfo(AllowIpInfoForm allowIpInfoForm, Model model) {
		ReturnClass returnClass = ipManager.delIpInfo(allowIpInfoForm);
		model.addAttribute("returnData", returnClass);
		return "jsonView";
	}

	@RequestMapping("/initIpInfoForm.crz")
	public String initIpInfoForm() {
		return "/env/formIpInfo";
	}
	
	/**
	 * 허용IP 캐시 초기화
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCacheIp.json")
	public String clearCacheIp(Model model) {
		
		ReturnClass returnClass = ipManager.clearCacheIp();
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
}
