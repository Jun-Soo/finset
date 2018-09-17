package com.koscom.base;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.domain.MsgInfo;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/m/base")
public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	PersonManager personManager;
	
	@Autowired
	private FincorpManager fincorpManager;
	
	@Autowired
	private CodeManager codeManager;
	
	@Resource
	Environment environment;
	
	/** VUE
	 * 앱 메인 화면
	 * 전하번호 체크하여 페이지이동
	 * 비회원 -> 서비스안내
	 * 회원 -> 로그인
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameBase.json")
	public String frameBase(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session, 
			Model model,
			PersonForm personForm) {

		logger.debug("접속 IP			: " + request.getRemoteAddr());
		logger.debug("받은 핸드폰 번호 	: " + personForm.getHp());
		
		String rtnUrl 	= "";
		PersonVO personVO = new PersonVO();
		
		// 1.전화번호 조회
		personVO = personManager.getPersonInfoHp(personForm.getHp());
		
		if(personVO != null) {
			
			//사용여부 N:회원가입, Y:로그인화면
			if(personVO.getYn_use().equals("N")) {
				rtnUrl = "/login/frameCertStep1";
			} else {
				
				if(StringUtil.isEmpty(personVO.getPass_person())) {
					session.setAttribute("cert_result_value", Constant.SUCCESS);
					rtnUrl = "/login/frameSecurityCode";
				} else if(Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_pwd(), "0")) > 4) { //비밀번호 실패건수
					model.addAttribute("personHp", personVO.getHp());
					rtnUrl = "/person/frameFindPwdStep1";
				} else if("Y".equals(personVO.getYn_fingerprint()) && Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_finger(), "0")) < 5) {
					rtnUrl = "/member/certCodeConfirm";
				}else {
					rtnUrl = "/member/certCodeConfirm";
				}
			}
			
			//지문 활성화 일 경우 체크 Y일때만 지문 활성화 N or 빈값 일 경우 비활성화
			model.addAttribute("no_person", 		personVO.getNo_person());
			model.addAttribute("yn_fingerprint", 	personVO.getYn_fingerprint());
			model.addAttribute("cd_push", 			personVO.getCd_push());
			model.addAttribute("yn_push", 			personVO.getYn_push());
			model.addAttribute("cnt_fail_pwd", 		personVO.getCnt_fail_pwd());
			model.addAttribute("cnt_fail_finger", 	personVO.getCnt_fail_finger());
			
			logger.debug(request.getHeader("user-agent"));
			logger.debug(personVO.toString());
			logger.debug(request.getRemoteAddr());

			model.addAttribute("securityResult", "Y");

			//App Version Check
			CodeInfo codeInfo = new CodeInfo();
			if(!"1".equals(StringUtil.NVL(personVO.getYn_os(), "1"))) {
				codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
			} else {
				codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
			}
			model.addAttribute("app_version", 	codeInfo.getNm_code());	
			
		} else {
			rtnUrl = "intro";
		}
		model.addAttribute("rtnPath", rtnUrl);
		return "jsonView";
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 보안코드 입력 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameSecurityCode.crz")
	public String frameSecurityCode(Model model, HttpServletRequest request, PersonVO personVO) {
		return "/base/frameSecurityCode";
	}
	
	/**
	 * 보안코드 재입력 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameSecurityCodeCertify.crz")
	public String frameSecurityCodeCertify(HttpSession session, Model model, HttpServletRequest request, PersonVO personVO) {
		
		String pass_person = "";
		logger.info("personVO.getPass_number() : "+personVO.getPass_number());
		
		if(personVO.getPass_number() != null){
			for(int i=0; i < personVO.getPass_number().size(); i++){
				pass_person += personVO.getPass_number().get(i);
			}
			//비밀번호 합쳐서 set
			model.addAttribute("pass_person", pass_person);
		}
		
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		model.addAttribute("site", site);
		
		String no_person = (String) session.getAttribute("no_person");
		personVO = personManager.getPersonInfo(no_person);
		
		logger.info("no_person : "+no_person);
		logger.info("personVO.getNm_person() : "+personVO.getNm_person());
		
		model.addAttribute("nm_person", personVO.getNm_person());
				
		//스크래핑 대상 은행 리스트 가져오기
		List<String> bankList = fincorpManager.getCooconFcCd(codeManager.getCodeId("cd_fin","은행"));
		String bankCode = String.join(",", bankList);
		
		List<String> cardList = fincorpManager.getCooconFcCd(codeManager.getCodeId("cd_fin","카드"));
		String cardCode = String.join(",", cardList);
		
		logger.debug("bankCode : " + bankCode);
		logger.debug("cardCode : " + cardCode);
		
		model.addAttribute("bank_code", bankCode);
		model.addAttribute("card_code", cardCode);
		
		return "/base/frameSecurityCodeCertify";
	}
	
	/**
	 * 완료화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameSecurityCodeSuccess.crz")
	public String frameSecurityCodeSuccess(HttpSession session, Model model, HttpServletRequest request, PersonVO personVO) {
		
		String pass_person = "";
		
		if(personVO.getPass_number() != null) {
			for(int i=0; i < personVO.getPass_number().size(); i++){
				pass_person += personVO.getPass_number().get(i);
			}
			//비밀번호 합쳐서 set
			personVO.setPass_person(pass_person);
			String no_person = (String) session.getAttribute("no_person");
			logger.info("핀 코드 업데이트 no_person : " + no_person);
			logger.info("핀 코드 업데이트 : " + personVO.getPass_person());
			personVO.setNo_person(no_person);
			ReturnClass returnClass = personManager.modifyPassPerson(personVO);
			model.addAttribute("message", returnClass.getMessage());		
			model.addAttribute("result", returnClass.getCd_result());
		}
		
		return "/base/frameSecurityCodeSuccess"; 
	}

	
	/**
	 * 비밀번호 설정 가이드 팝업
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/popSecurityCodeGuide.crz")
	public String popSecurityCodeGuide(Model model, HttpServletRequest request) {
		return "/base/sub/popSecurityCodeGuide";
	}
	
//	/**
//	 * 비밀번호 안내 팝업 
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/popSecurityCodeInfo.crz")
//	public String popSecurityCodeInfo(HttpSession session, Model model, HttpServletRequest request) {
//		logger.info("접속 ip : "+request.getRemoteAddr());
//		logger.info("user-agent : "+request.getHeader("user-agent")); 
//		String noPerson = (String) session.getAttribute("no_person");
//		logger.info("세션 no_person : "+noPerson);
//			//최근 접속 이력 업데이트
//        personManager.modifyLastLogin(noPerson);
//
//		//모바일 접속 이력 insert
//		PersonLoginHist personLoginHist = new PersonLoginHist();
//		personLoginHist.setNo_person(noPerson);
//		personLoginHist.setCd_system("10");
//		personLoginHist.setUser_agent(request.getHeader("user-agent"));
//		String ip = request.getHeader("X-FORWARDED-FOR"); 
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr() ;
//		}
//		logger.info(ip);
//		personLoginHist.setIp_client(ip);
//		personManager.insertPersonLoginHist(personLoginHist);
//		return "/base/sub/popSecurityCodeInfo";
//	}
//	
//	/**
//	 * 비밀번호 팝업 
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/popSecurityCode.crz")
//	public String popSecurityCode(Model model, HttpServletRequest request) {
//		return "/base/sub/popSecurityCode";
//	}
	
	/**
	 * 지문 인증
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameFingerCheck.crz")
	public String frameFingerCheck(HttpSession session, Model model, HttpServletRequest request) {
		
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		model.addAttribute("site", site);
		
		String noPerson = (String) session.getAttribute("no_person");
		String passPerson = request.getParameter("pass_person");
		String bankCode = request.getParameter("bank_code");
		String cardCode = request.getParameter("card_code");
		String nmPerson = request.getParameter("nm_person");
		
		model.addAttribute("no_person", noPerson);
		model.addAttribute("pass_person", passPerson);
		model.addAttribute("bank_code", bankCode);
		model.addAttribute("card_code", cardCode);
		model.addAttribute("nm_person", nmPerson);
		
		return "/base/frameFingerCheck";
	}
	
	/**
	 * 공통 로딩 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/commLoading.crz")
	public String commLoading(Model model, HttpServletRequest request, MsgInfo msgInfo) {
		
		//logger.debug(String.format("msg: msg1=%1$s / msg2=%2$s / msg3=%3$s", msgInfo.getMsg1(), msgInfo.getMsg2(), msgInfo.getMsg3()));
		
		// xssEscapeServletFilter 에 의해 태그가 제거되는걸 방지하기 위해, "<br/> 태그를 "||" 문자열로 치환
		if ( StringUtil.isNotEmpty( msgInfo.getMsg1() ) ) {
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("&lt;", "<"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("&gt;", ">"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("<br[\\/]?>", "||"));
			
		}
		if ( StringUtil.isNotEmpty( msgInfo.getMsg2() ) ) {
			msgInfo.setMsg2(msgInfo.getMsg2().replaceAll("&lt;", "<"));
			msgInfo.setMsg2(msgInfo.getMsg2().replaceAll("&gt;", ">"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("<br[\\/]?>", "||"));
		}
		if ( StringUtil.isNotEmpty( msgInfo.getMsg3() ) ) {
			msgInfo.setMsg3(msgInfo.getMsg3().replaceAll("&lt;", "<"));
			msgInfo.setMsg3(msgInfo.getMsg3().replaceAll("&gt;", ">"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("<br[\\/]?>", "||"));
		}
		
		model.addAttribute("msg1", msgInfo.getMsg1());
		model.addAttribute("msg2", msgInfo.getMsg2());
		model.addAttribute("msg3", msgInfo.getMsg3());
		
		return "/comm/commLoading";
	}
	
	/**
	 * 재조회 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/commRetry.crz")
	public String commReload(Model model, HttpServletRequest request, MsgInfo msgInfo) {
		
		//logger.debug(String.format("msg: msg1=%1$s / msg2=%2$s / msg3=%3$s", msgInfo.getMsg1(), msgInfo.getMsg2(), msgInfo.getMsg3()));
		
		// xssEscapeServletFilter 에 의해 태그가 제거되는걸 방지하기 위해, "<br/> 태그를 "||" 문자열로 치환
		if ( StringUtil.isNotEmpty( msgInfo.getMsg1() ) ) {
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("&lt;", "<"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("&gt;", ">"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("<br[\\/]?>", "||"));
			
		}
		if ( StringUtil.isNotEmpty( msgInfo.getMsg2() ) ) {
			msgInfo.setMsg2(msgInfo.getMsg2().replaceAll("&lt;", "<"));
			msgInfo.setMsg2(msgInfo.getMsg2().replaceAll("&gt;", ">"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("<br[\\/]?>", "||"));
		}
		if ( StringUtil.isNotEmpty( msgInfo.getMsg3() ) ) {
			msgInfo.setMsg3(msgInfo.getMsg3().replaceAll("&lt;", "<"));
			msgInfo.setMsg3(msgInfo.getMsg3().replaceAll("&gt;", ">"));
			msgInfo.setMsg1(msgInfo.getMsg1().replaceAll("<br[\\/]?>", "||"));
		}
		
		model.addAttribute("msg1", msgInfo.getMsg1());
		model.addAttribute("msg2", msgInfo.getMsg2());
		model.addAttribute("msg3", msgInfo.getMsg3());
		
		return "/comm/commRetry";
	}
}
