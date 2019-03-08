package com.koscom.env;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.service.CodeManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

@Controller
@RequestMapping("/env")
public class CodeController {

	private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
	
	@Autowired
	CodeManager codeManager;
	
	/**
	 * 코드관리 메인
	 * @param codeInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCodeMain.crz")
	public String listCodeMain(CodeInfo codeInfo, Model model) {
		return "/env/listCodeMain";
	}
	
	/**
	 * 코드 목록 조회
	 * @param codeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCode.crz")
	public String listCode(CodeForm codeForm, Model model) {
		
		model.addAttribute("List", codeManager.listCode(codeForm));
		
		if("Y".equals(codeForm.getYn_code_group())) {
			return "/env/listCodeGroup";
		}
		
		return "/env/listCodeDetail";
	}
	
	/**
	 * 코드 정보 조회
	 * @param codeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCode.crz")
	public String getCode(CodeForm codeForm, Model model) {
		
		model.addAttribute("codeInfo", codeManager.getCode(codeForm));
		
		if("Y".equals(codeForm.getYn_code_group())) {
			return "/env/formCodeGroup";
		}
		
		return "/env/formCodeDetail";
	}
	
	/**
	 * 코드 등록/수정
	 * @param request
	 * @param codeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/procCodeInfo.json")
	public String procCodeInfo(HttpServletRequest request, CodeVO codeVO,Model model) {
		SessionUtil session = new SessionUtil(request);
		codeVO.setId_frt(session.getUserId());
		codeVO.setId_lst(session.getUserId());
		
		ReturnClass returnClass = codeManager.procCodeInfo(codeVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 코드 삭제
	 * @param codeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/delCodeInfo.json")
	public String delCodeInfo(CodeVO codeVO,Model model) {
		
		ReturnClass returnClass = codeManager.delCodeInfo(codeVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 코드 캐시 초기화
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCacheCode.json")
	public String clearCacheCode(Model model) {
		
		ReturnClass returnClass = codeManager.clearCacheCode();
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
}
