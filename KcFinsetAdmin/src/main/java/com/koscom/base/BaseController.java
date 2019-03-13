package com.koscom.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.env.service.CodeManager;
import com.koscom.util.ResUtil;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;

@Controller
public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private WorkerManager workerManager;
	
	/**
	 * 로그인 후 기본메인화면 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/baseMain.crz")
	public String index(Model model, HttpServletRequest request) {
		SessionUtil session = new SessionUtil(request);
		
		//사용자 정보 조회
		WorkerVO agencyUserInfo = workerManager.getWorkerInfo(session.getUserId());
		
		String url = "";
		
		if("12".equals(StringUtil.NVL(agencyUserInfo.getCd_template_group(), ""))) {
			url = ResUtil.getPath(request) + "/goodsbank/listGoodsbankInfoMain.crz";
		} else {
			url = ResUtil.getPath(request) + "/person/listPersonMain.crz";
		}
		
		
		return "redirect:"+url;
	}
	
	/**
	 * 비밀번호와 ID가 같을때 강제 변경화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/changePasswd.crz")
	public String changePasswd(Model model, HttpServletRequest request) {
		SessionUtil session = new SessionUtil(request);
		
		model.addAttribute("worker", workerManager.getWorkerInfo(session.getUserId()));
		model.addAttribute("idxChange", "Y");
		
		return "/base/changePasswd";
	}
	
	/**
	 * 금융계산기
	 * @param model
	 * @return
	 */
	@RequestMapping("/calculator.crz")
	public String calculator(Model model) {
		return "/calc/calculator";

	}
	
	/**
	 * 즐겨찾기 팝업
	 */
	@RequestMapping("/popBookmark.crz")
	public String popBookmark(Model model) {
		
		model.addAttribute("listBookmark", codeManager.listCodeInfo("cd_bookmark_link"));
		return "/base/popBookmark";
	}
	
}
