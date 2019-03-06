package com.koscom.login;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.util.NumberUtil;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 로그인 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.crz")
	public String login(HttpServletRequest request, Model model) throws Exception {
		logger.debug("+++++++++login.crz");
		
		String denied = request.getParameter("denied");
		if(!StringUtil.isEmpty(denied)){
			String msg = "";
			int cd_result = NumberUtil.stringToInt(denied);
			//로그인 에러 코드에 따라 메시지 출력
			switch (cd_result) {
			case 21: 
				msg = "등록되지 않은 사용자 입니다.";
				break;
			case 22:
				msg = "비밀번호를 잘못 입력하였습니다.";
				break;
			case 91:
				msg = "시스템에 접근이 <strong>허용되지 않은 IP 주소</strong>입니다.";
				break;
			case 92:
				msg = "다른 PC에서 <strong>동일한 ID로 접속</strong> 하였습니다.";
				break;
			case 100:
				msg = "아이디를 입력해주세요.";
				break;
			case 101:
				msg = "패스워드를 입력해주세요.";
				break;
			}
			model.addAttribute("msg", msg);
		}
		
		return "/login/login";
	}
	
	/**
	 * 페이지 접근권한 view 페이지 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/403.crz")
	public String accessDenied(Model model) throws Exception {
		return "/login/403";
	}
	
}
