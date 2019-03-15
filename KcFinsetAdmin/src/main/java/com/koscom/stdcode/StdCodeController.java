package com.koscom.stdcode;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.stdcode.model.StdCodeForm;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.model.StdCodeVO;
import com.koscom.stdcode.service.StdCodeManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

@Controller
@RequestMapping("/stdcode")
public class StdCodeController {

	private static final Logger logger = LoggerFactory.getLogger(StdCodeController.class);
	
	@Autowired
	StdCodeManager stdcodeManager;
	
	/**
	 * 코드관리 메인
	 * @param stdcodeInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStdCodeMain.crz")
	public String listStdCodeMain(StdCodeInfo stdcodeInfo, Model model) {
		return "/stdcode/listStdCodeMain";
	}
	
	/**
	 * 코드 목록 조회
	 * @param stdcodeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStdCode.crz")
	public String listStdCode(StdCodeForm stdcodeForm, Model model) {
		
		model.addAttribute("List", stdcodeManager.listStdCode(stdcodeForm));
		
		if("Y".equals(stdcodeForm.getYn_code_group())) {
			return "/stdcode/listStdCodeGroup";
		}
		
		return "/stdcode/listStdCodeDetail";
	}
	
	/**
	 * 코드 정보 조회
	 * @param stdcodeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStdCode.crz")
	public String getStdCode(StdCodeForm stdcodeForm, Model model) {
		
		model.addAttribute("stdcodeInfo", stdcodeManager.getStdCode(stdcodeForm));
		
		if("Y".equals(stdcodeForm.getYn_code_group())) {
			return "/stdcode/formStdCodeGroup";
		}
		
		return "/stdcode/formStdCodeDetail";
	}
	
	/**
	 * 코드 등록/수정
	 * @param request
	 * @param stdcodeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/procStdCodeInfo.json")
	public String procStdCodeInfo(HttpServletRequest request, StdCodeVO stdcodeVO,Model model) {
		SessionUtil session = new SessionUtil(request);
		stdcodeVO.setId_frt(session.getUserId());
		stdcodeVO.setId_lst(session.getUserId());
		
		ReturnClass returnClass = stdcodeManager.procStdCodeInfo(stdcodeVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 코드 삭제
	 * @param stdcodeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/delStdCodeInfo.json")
	public String delStdCodeInfo(StdCodeVO stdcodeVO,Model model) {
		
		ReturnClass returnClass = stdcodeManager.delStdCodeInfo(stdcodeVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 코드 캐시 초기화
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCacheStdCode.json")
	public String clearCacheStdCode(Model model) {
		
		ReturnClass returnClass = stdcodeManager.clearCacheStdCode();
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
}
