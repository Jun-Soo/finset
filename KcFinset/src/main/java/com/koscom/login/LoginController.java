package com.koscom.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.login.service.SecureManager;
import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.NumberUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/m/login")
@PropertySource("classpath:prop/webservice.properties")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	PersonManager personManager;
	
//	@Autowired
//	ConditioncreditManager conditioncreditManager;
//	
//	@Autowired
//	ConditionbizManager conditionbizManager;
//	
//	@Autowired
//	ConditionhouseManager conditionhouseManager;
//	
//	@Autowired
//	private KcbManager kcbManager;
//	
//	@Autowired
//	private DebtManager debtManager;
	
	@Autowired
	private SecureManager secureManager;
	
//	@Autowired
//	private CreditManager creditManager;
	
	@Autowired
	CodeManager codeManager;
	
	@Resource
	Environment environment;
	
	/**
	 * app 버전정보 조회
	 * @param response
	 * @param request
	 * @param session
	 * @param PersonVO 
	 * @param model
	 * @return
	 */
	@RequestMapping("/appVersion.json")
	public String appVersion(
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			PersonVO personVO,
			Model model) throws Exception {
		
		CodeInfo codeInfo = new CodeInfo();
		
		if(!"1".equals(personVO.getYn_os())) {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
		} else {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
		}
		
		model.addAttribute("codeInfo", codeInfo);
		
		return "jsonView";
	}
	
	@RequestMapping("/appVersionCheck.json")
	public String appVersionCheck(
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			Model model) throws Exception {
		
		String userAgent = request.getParameter("user_agent");
		String appVersion = request.getParameter("app_version");
		
		CodeInfo codeInfo = new CodeInfo();
		
		if("iOS".equals(userAgent)) {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
			if(appVersion.length() < 4) appVersion = appVersion + ".0";
		} else {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
		}
		
		String[] new_version = codeInfo.getNm_code().split("\\.");
		String[] app_version = appVersion.split("\\.");
		
		try {
			if(app_version.length == new_version.length) {
				for(int i=0; i<new_version.length; i++) {
					if(Integer.valueOf(new_version[i]) > Integer.valueOf(app_version[i]) && "1.1.1".equals(codeInfo.getNm_code())) {
						model.addAttribute("result", "update");
						break;
					} else {
						model.addAttribute("result", "pass");
					}
				}
			}
		} catch (Exception e) {
			logger.error("new_version ::: " + new_version);
			logger.error("app_version ::: " + app_version);
			LogUtil.error(logger, e);
		}
		
		
		return "jsonView";
	}
	
	/**
	 * 최초 비밀번호 입력 화면
	 * @param response
	 * @param request
	 * @param model
	 * @param session
	 * @param PersonForm
	 * @return
	 */
	@RequestMapping("/frameSecurityCodeConfirm.crz")
	public String frameSecurityCodeConfirm(
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session, 
			PersonForm personForm,
			Model model) {
		
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
				msg = "다른 사용자가 로그인을 시도하였습니다.";
				break;
			case 100:
				msg = "아이디를 입력해주세요.";
				break;
			case 101:
				msg = "패스워드를 입력해주세요.";
				break;
			}
			model.addAttribute("msg", msg);
			return "/login/frameSecurityCodeConfirm";
		}
		
		String rtnUrl 	= "";
		String noPerson = (String)session.getAttribute("no_person");
		String hp = (String)session.getAttribute("hp");

		PersonVO personVO = new PersonVO();

		logger.debug("접속 IP			: " + request.getRemoteAddr());
		logger.debug("세션 NO_PERSON 	: " + noPerson);
		logger.debug("받은 핸드폰 번호 	: " + personForm.getHp());

		
		model.addAttribute("no_person", noPerson);
		
		//전화번호값이 넘어오면 session 초기화 처리
		if(!StringUtil.isEmpty(personForm.getHp())) {
			noPerson = "";
			session.setAttribute("expiredTime", "");
			session.setAttribute("rememberMe",  "");
			session.setAttribute("no_person", 	"");
			session.setAttribute("yn_reload",   "");
		}
		
		//session에 전화번호와 URL정보가 담겨있을 경우
		if(!StringUtil.isEmpty(hp)) {
			personForm.setHp(session.getAttribute("hp").toString());
		}
		
		if(StringUtil.isEmpty(noPerson)) {
			
			// 1.전화번호 조회
			personVO = personManager.getPersonInfoHp(personForm.getHp());
			
			if(personVO != null) {
				
				model.addAttribute("no_token", personVO.getPass_person());
				
				logger.debug("personVO2.toString() === " + personVO.toString());
				logger.debug("========= 세션 없는데 번호 있을경우 =======");
				
				setAutoLoginWithCookies(session, response, personVO.getNo_person());
				
				//사용여부 N:회원가입, Y:로그인화면
				if(personVO.getYn_use().equals("N")) {
					rtnUrl = "/login/frameCertStep1";
				} else {
					
					if(StringUtil.isEmpty(personVO.getPass_person())) {
						session.setAttribute("cert_result_value", Constant.SUCCESS);
						rtnUrl = "/base/frameSecurityCode";
					} else if(Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_pwd(), "0")) > 4) { //비밀번호 실패건수
						model.addAttribute("personHp", personVO.getHp());
						rtnUrl = "/person/frameFindPwdStep1";
					} else if("Y".equals(personVO.getYn_fingerprint()) && Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_finger(), "0")) < 5) {
						rtnUrl = "/login/frameFingerConfirm";
					}else {
						rtnUrl = "/login/frameSecurityCodeConfirm";
					}
				}
				
				//지문 활성화 일 경우 체크 Y일때만 지문 활성화 N or 빈값 일 경우 비활성화
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
				rtnUrl = "/login/frameServiceIntro";
			}
			
		} else {
			
			personVO = personManager.getPersonInfo(noPerson);
			
			model.addAttribute("no_token", personVO.getPass_person());
			
			//지문 활성화 일 경우 체크 Y일때만 지문 활성화 N or 빈값 일 경우 비활성화
			model.addAttribute("yn_fingerprint", 	personVO.getYn_fingerprint());
			model.addAttribute("cd_push", 			personVO.getCd_push());
			model.addAttribute("yn_push", 			personVO.getYn_push());
			model.addAttribute("cnt_fail_pwd", 		personVO.getCnt_fail_pwd());
			model.addAttribute("cnt_fail_finger", 	personVO.getCnt_fail_finger());
			
			logger.debug(request.getHeader("user-agent"));
			logger.debug(request.getRemoteAddr());

			model.addAttribute("securityResult", "Y");
			
			//사용여부 N:회원가입, Y:로그인화면
			if(personVO.getYn_use().equals("N")) {
				rtnUrl = "/login/frameCertStep1";
			} else {
				
				if(StringUtil.isEmpty(personVO.getPass_person())) {
					session.setAttribute("cert_result_value", Constant.SUCCESS);
					rtnUrl = "/base/frameSecurityCode";
				} else if(Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_pwd(), "0")) > 4) { //비밀번호 실패건수
					model.addAttribute("personHp", personVO.getHp());
					rtnUrl = "/person/frameFindPwdStep1";
				} else if("Y".equals(personVO.getYn_fingerprint()) && Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_finger(), "0")) < 5) {
					rtnUrl = "/login/frameFingerConfirm";
				} else {
					rtnUrl = "/login/frameSecurityCodeConfirm";
				}
			}
		}

		return rtnUrl;
	}
	
	@RequestMapping("/getYnfingerInfo.json")
	public String getYnfingerInfo(
			HttpSession session, 
			Model model) throws Exception {
		
		String noPerson = "";
		noPerson = (String)session.getAttribute("no_person");
		
		if(StringUtil.isEmpty(noPerson)) {
			model.addAttribute("yn_session", "N");
			model.addAttribute("yn_fingerprint", "");
		} else {
			PersonVO person = personManager.getPersonInfo(noPerson);
			model.addAttribute("yn_session", "Y");
			model.addAttribute("yn_fingerprint", person.getYn_fingerprint());
		}
		
		return "jsonView";
	}
	
	/**
	 * 비밀번호 입력 화면
	 * @param request
	 * @param model
	 * @param session
	 * @param personForm
	 * @return
	 */
	@RequestMapping("/frameCodeConfirm.crz")
	public String frameCodeConfirm(
			HttpServletRequest request,
			HttpSession session,
			PersonForm personForm, 
			Model model) throws FinsetException {
		
		String noPerson = (String)session.getAttribute("no_person");
		
		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(noPerson);
		logger.debug("personVO.toString()"+personVO.toString());
		
		model.addAttribute("no_person", noPerson);
		model.addAttribute("no_token", personVO.getPass_person());
		model.addAttribute("yn_fingerprint", personVO.getYn_fingerprint());
		model.addAttribute("cd_push", personVO.getCd_push());
		model.addAttribute("yn_push", personVO.getYn_push());
		model.addAttribute("cnt_fail_pwd", personVO.getCnt_fail_pwd());
		model.addAttribute("cnt_fail_finger", personVO.getCnt_fail_finger());
		
		if(!StringUtil.isEmpty(personForm.getYn_reload())) {
			session.setAttribute("yn_reload", personForm.getYn_reload());
		}
		model.addAttribute("yn_reload", (String)session.getAttribute("yn_reload"));
		model.addAttribute("securityResult", "Y");
			
		logger.debug(request.getHeader("user-agent"));
		logger.debug(request.getRemoteAddr());
		
		return "/login/frameSecurityCodeConfirm";
	}
	
	/**
	 * 지문 인증화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameFingerConfirm.crz")
	public String frameFingerConfirm(
			HttpSession session,
			PersonForm personForm,
			Model model) throws FinsetException {
		
		String noPerson = (String) session.getAttribute("no_person");
		
		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(noPerson);
		
		model.addAttribute("cnt_fail_finger", personVO.getCnt_fail_finger());
		model.addAttribute("no_token", personVO.getPass_person());
		if(!StringUtil.isEmpty(personForm.getYn_reload())) {
			session.setAttribute("yn_reload", personForm.getYn_reload());
		}
		model.addAttribute("yn_reload", (String)session.getAttribute("yn_reload"));
		
		return "/login/frameFingerConfirm";
	}
	
	/**
	 * 로그인 후 Kcb 크롤링 호출용 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameKcbCrawling.crz")
	public String frameKcbCrawling(
			HttpServletRequest request,
			Model model) {
		
		model.addAttribute("linkUrl", (String)request.getAttribute("linkUrl"));
		return "/login/frameKcbCrawling";
	}
	
	/**
	 * Kcb 크롤링 호출
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws FinsetException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getKcbCrawling.json")
	public String getKcbCrawling(
			HttpServletRequest request, 
			HttpSession session, 
			Model model) throws Exception {
		
		String noPerson = (String) session.getAttribute("no_person");
		String profile  = environment.getProperty("service.profile");
		
		logger.debug(":::::::::::::::::::::::::::::::::::: KCB START ::::::::::::::::::::::::::::::::::::");
		PersonVO 			person 	= personManager.getPersonInfo(noPerson);
		//KcbCreditInfoVO 	info 	= new KcbCreditInfoVO();

        boolean isSuccess = false;

        if("LOCAL".equals(profile)) {
			
			logger.debug("continue");
			model.addAttribute("cd_result", Constant.SUCCESS);
			
		} else {
			
//			try {
//
//				//600420 크롤링 시작
//				logger.debug(":::::::::::::::::::::::::::::::::::: KCB CRAW START ::::::::::::::::::::::::::::::::::::");
//				info.setNoPerson(person.getNo_person());
//				info.setNmCust(person.getNm_person());
//				info.setNmIf("600420");
//				info.setCd_regist("09");	//01 신규, 09 URL
//				info.setBgn(person.getBgn());
//				info.setDi(person.getKcb_di());
//				info.setHp(person.getHp());
//				
//				ReturnClass returnClass = kcbManager.urlCrawling(info);
//				
//				logger.debug(":::::::::::::::::::::::::::::::::::: KCB CRAW END ::::::::::::::::::::::::::::::::::::");
//				
//				//lca parsing
//				returnClass = kcbManager.parseCrawling(info);
//				if(Constant.SUCCESS.equals(returnClass.getCd_result())) {
//					
//					//TODO call Package
//					/*** 부채TABLE DATA생성 proc call ***/
//					debtManager.debtPdocRun(person.getNo_person());
//	                isSuccess = true;
//				}
//				
//				model.addAttribute("cd_result", returnClass.getCd_result());
//                
//			} catch (FinsetException e) {
//                isSuccess = false;
//                LogUtil.error(logger, e);
//                throw e;
//			} catch (IOException e) {
//                isSuccess = false;
//                LogUtil.error(logger, e);
//                throw e;
//			} finally {
//                if(isSuccess == false) {
//                    //error 발생시 당일 전문 데이터 DELETE
//                    kcbManager.deleteKcbCb(person.getNo_person());
//                    model.addAttribute("cd_result", Constant.FAILED);
//                }
//			}
		}
			
		logger.debug(":::::::::::::::::::::::::::::::::::: KCB END ::::::::::::::::::::::::::::::::::::");
		
		return "jsonView";
	}
	
	/**
	 * 본인인증화면 (약관)
	 * @param model 
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameCertStep1.crz")
	public String frameCertStep1() {
		return "/login/frameCertStep1";
	}
	
	/**
	 * 본인인증화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameCertStep2.crz")
	public String frameCertStep2(
			PersonVO personVO,
			Model model) {
		
		model.addAttribute("yn_eventPush", personVO.getYn_eventPush()); //이벤트푸시 수신여부
		return "/login/frameCertStep2";
	}
	
	/**
	 * 개인정보 등록
	 * @param session
	 * @param response
	 * @param personVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertPerson.json")
	public String insertPerson(HttpSession session, 
			HttpServletResponse response, 
			PersonVO personVO, 
			Model model) throws UnsupportedEncodingException, FinsetException, IOException {
		
		String profile  = environment.getProperty("service.profile");
		
		if(!profile.equals("LOCAL")){
			//KCB_CI를 기준으로 회원을 확인해, 기존 회원의 핸드폰 번호가 변경 된 것이지 확인
			String no_person = personManager.getPersonInfoDupCi(personVO);
			//없으면 넘어가도록 한다
			if(no_person!=null){
				if(!no_person.equals("")){
					personVO.setNo_person(no_person);
					int isUpdated = personManager.modifyPersonHp(personVO);
					if(isUpdated!=0){
						ReturnClass returnClass = new ReturnClass(personVO.PERSON_EXIST, "이미 등록된 정보가 있습니다.");
						model.addAttribute("message", returnClass.getMessage());
						model.addAttribute("result", returnClass.getCd_result());
						model.addAttribute("returnData", personVO.getNo_person());
						logger.debug("기존 회원 KCB_CI 가 있습니다.:"+personVO.getKcb_ci());
					} else {
						throw new FinsetException("기존 회원의 hp를 업데이트 하지 못했습니다. 확인해주세요.");
					}
				}
			}
		}
		
		logger.debug("회원가입 시작");
		//이미 가입된 정보가 있는지 체크
		PersonVO person = personManager.getPersonInfoDup(personVO);
		logger.debug("가입 유무 체크  : " + (person != null));
		
		if(person != null) {
			ReturnClass returnClass = new ReturnClass(personVO.PERSON_EXIST, "이미 등록된 정보가 있습니다.");
			model.addAttribute("message", returnClass.getMessage());
			model.addAttribute("result", returnClass.getCd_result());
			model.addAttribute("returnData", person.getNo_person());
			setAutoLoginWithCookies(session, response, person.getNo_person());
			
		} else {
			
			ReturnClass returnClass = personManager.insertPerson(personVO);
			model.addAttribute("message", returnClass.getMessage());		
			model.addAttribute("result", returnClass.getCd_result());

			if(returnClass.getCd_result().equals(Constant.SUCCESS)) {
				
				person = (PersonVO) returnClass.getReturnObj();
				model.addAttribute("returnData", person.getNo_person());
				logger.debug("회원가입 완료 : " + (person == null));
				
				setAutoLoginWithCookies(session, response, person.getNo_person());
				
            }
		}
		return "jsonView";
	}
	
	//쿠키, 세션 값 맺는 메소드
	private void setAutoLoginWithCookies(HttpSession session, HttpServletResponse response, String noPerson) {
		
		String currentDateTime = DateUtil.getCurrentDateTime(DateUtil.DATE_HMS_PATTERN);
		String expiredTime =  DateUtil.addHours(currentDateTime, 720);

		/*
		Cookie _rememberMe = new Cookie("rememberMe", "Y");
		_rememberMe.setMaxAge(30*24*60*60);
		_rememberMe.setPath("/");
		response.addCookie(_rememberMe);

		Cookie _noPerson = new Cookie("noPerson", noPerson);
		_noPerson.setMaxAge(30*24*60*60);
		_noPerson.setPath("/");
		response.addCookie(_noPerson);

		Cookie _expiredTime = new Cookie("expiredTime", expiredTime);
		_expiredTime.setMaxAge(30*24*60*60);
		_expiredTime.setPath("/");
		response.addCookie(_expiredTime);
		*/
		
		session.setAttribute("expiredTime", expiredTime);
		session.setAttribute("rememberMe", "Y");
		session.setAttribute("no_person", noPerson);
	}
	
	/**
	 * 인증서 비밀번호 복호화
	 * : App에서 호출하나, 보안문제로 Deprecated
	 * @param encPwd
	 * @return
	 */
	@Deprecated
	@RequestMapping("/getDecodedPassword.crz")
	@ResponseBody
	public ReturnClass getDecodedPassword(@RequestParam("encPwd") String encPwd) {
		
		ReturnClass returnClass = new ReturnClass();

		returnClass.setCd_result(Constant.TRANS_STATUS_92); //정적테스트 처리
		
		String decPwd = secureManager.getDecodedPassword(encPwd);
		
		if ( encPwd.equals( decPwd ) ) {
			returnClass.setCd_result( Constant.FAILED );
			returnClass.setMessage("복호화에 실패하였습니다.");
		} else {
			returnClass.setCd_result( Constant.SUCCESS );
			returnClass.setMessage(decPwd);
		}
		return returnClass;
	}
}
