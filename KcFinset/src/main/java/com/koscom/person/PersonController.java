package com.koscom.person;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.login.service.LoginManager;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonSmsListVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.LogUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.SkipLoginCheck;
import com.koscom.util.StringUtil;

import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("/m/person")
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonManager personManager;

	@Autowired
	private LoginManager loginManager;
	
	/**
	 * fcm 토큰 업데이트
	 * @param model
	 * @param request
	 * @param personVO
	 * @return
	 */
	@RequestMapping("/modifyFcmToken.crz")
	public String modifyFcmToken( 
			HttpServletRequest request, 
			PersonVO personVO,
			Model model) {
		
		String sYnOs = StringUtil.nullToString(personVO.getYn_os());
		
		logger.info("FCM TOKEN modify no person : {}, token : {}", StringUtil.nullToString(personVO.getNo_person()), StringUtil.nullToString(personVO.getFcm_token()));
		logger.info("os : "+sYnOs);
		
		if(sYnOs != "" && sYnOs.equals("android")){
			personVO.setYn_os("1");
		} else {
			personVO.setYn_os("");
		}
		
		ReturnClass returnClass = personManager.modifyFcmToken(personVO);
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		return "jsonView";
	}

	/**
	 * 본인인증화면 (보안코드 찾기)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameFindPwdStep1.crz")
	public String frameFindPwdStep1(
			HttpServletRequest request, 
			HttpSession session,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		PersonVO personVO = personManager.getPersonInfo(no_person);

		model.addAttribute("personHp", personVO.getHp());
		logger.info("personHP");
		logger.info(personVO.getHp());

		return "/person/frameFindPwdStep1";
	}

	/**
	 * 본인인증화면 (보안코드 찾기)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameFindPwdStep2.crz")
	public String frameFindPwdStep2(
			HttpServletRequest request,
			PersonVO personVO,
			Model model) {
		return "/person/frameFindPwdStep2";
	}

	/**
	 * 보안코드 입력 화면 (보안코드 찾기)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameSecurityCode.crz")
	public String frameSecurityCode() {
		return "/person/frameSecurityCode";
	}

	/**
	 * 보안코드 재입력 화면 (보안코드 찾기)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameSecurityCodeCertify.crz")
	public String frameSecurityCodeCertify(
			HttpSession session, 
			PersonVO personVO,
			Model model) {

		String pass_person = "";
		for(int i=0; i < personVO.getPass_number().size(); i++){
			pass_person += personVO.getPass_number().get(i);
		}
		//비밀번호 합쳐서 set
		model.addAttribute("yn_reload", (String)session.getAttribute("yn_reload"));
		model.addAttribute("pass_person", pass_person);

		return "/person/frameSecurityCodeCertify";
	}

	/**
	 * 완료화면 (보안코드 찾기)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/changePwd.json")
	public String changePwd(
			HttpSession session, 
			PersonVO personVO, 
			Model model) {

		String kcmCertValue = (String) session.getAttribute("cert_result_value");
		
		logger.debug("★★★★★ cert_result_value : " + kcmCertValue);
		
		if(!Constant.SUCCESS.equals(kcmCertValue)) {
			model.addAttribute("message", "본인인증 후 진행하시기 바랍니다.");
			model.addAttribute("result", "99");

			session.removeAttribute("cert_result_value");
			return "jsonView";
		} else {
			String pass_person = "";
			for(int i=0; i < personVO.getPass_number().size(); i++){
				pass_person += personVO.getPass_number().get(i);
			}
			//비밀번호 합쳐서 set
			personVO.setPass_person(pass_person);
			String no_person = (String) session.getAttribute("no_person");
			logger.info("핀 코드 업데이트 no_person : " + no_person);
			logger.info("핀 코드 업데이트 : " + personVO.getPass_person());
			personVO.setNo_person(no_person);
			ReturnClass returnClass = personManager.modifyPassPerson((PersonVO)SessionUtil.setUser(personVO, session));
			model.addAttribute("message", returnClass.getMessage());
			model.addAttribute("result", returnClass.getCd_result());
			model.addAttribute("no_person", no_person);
			return "jsonView";
		}
	}
	/**
	 * 비밀번호 확인
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginChkCode.json")
	public String loginChkCode(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {

		String noPerson 	= (String) session.getAttribute("no_person");
		String pass_person 	= "";

		logger.info("------------로그인체크---------------");
		logger.info("접속 ip 		: "+request.getRemoteAddr());
		logger.info("user-agent 	: "+request.getHeader("user-agent"));
		logger.info("세션 no_person : "+noPerson);
		logger.info(personVO.toString());

		for(int i=0; i < personVO.getPass_number().size(); i++){
			pass_person += personVO.getPass_number().get(i);
		}

		String no_person = (String) session.getAttribute("no_person");
		personVO.setNo_person(no_person);
		personVO.setPass_person(pass_person);
		int pwdCheck = personManager.checkPersonPass(personVO);
		
		if(pwdCheck > 0) {	//암호화 비밀번호 체크
			
			model.addAttribute("result", Constant.SUCCESS);
			
			//모바일 접속 이력 insert
			PersonLoginHistInfo personLoginHist = new PersonLoginHistInfo();
			personLoginHist.setNo_person(noPerson);
			personLoginHist.setCd_system("10");
			personLoginHist.setUser_agent(request.getHeader("user-agent"));
			String ip = request.getHeader("X-FORWARDED-FOR"); 
		    if (ip == null || ip.length() == 0) {
		        ip = request.getHeader("Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0) {
		        ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
		    }
		    if (ip == null || ip.length() == 0) {
		        ip = request.getRemoteAddr() ;
		    }
		    logger.info(ip);
		    personLoginHist.setIp_client(ip);
			personManager.insertPersonLoginHist((PersonLoginHistInfo)SessionUtil.setUser(personLoginHist, session));

		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("result", Constant.FAILED);
		}
		model.addAttribute("no_person", no_person);
		
		return "jsonView";
	}

	/**
	 * 보안코드찾기시 본인의 정보인지 체크
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/personCertify.json")
	public String personCertify(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {

		String no_person = (String) session.getAttribute("no_person");
		PersonVO vo = personManager.getPersonInfo(no_person);
		String p_nm_person  = personVO.getNm_person();
		String db_nm_person = vo.getNm_person();
		String p_hp         = personVO.getHp();
		String db_hp        = vo.getHp();
		p_nm_person  = (p_nm_person  != null)? p_nm_person .trim() : "";
		db_nm_person = (db_nm_person != null)? db_nm_person.trim() : "";
		p_hp         = (p_hp         != null)? p_hp        .trim() : "";
		db_hp        = (db_hp        != null)? db_hp       .trim() : "";
		String log = "session:p_nm_person="+p_nm_person+"\ndb_nm_person="+db_nm_person+"\np_hp="+p_hp+"\ndb_hp="+db_hp;
		LogUtil.debugLn(logger,log);
		if(!p_nm_person.equals(db_nm_person) || !p_hp.equals(db_hp)){
			model.addAttribute("message", "기존의 정보와 일치하지 않습니다.");
			model.addAttribute("result", Constant.FAILED);
		}
		return "jsonView";
	}

	/**
	 * 활동 로그 기록
	 * @param personForm
	 * @param session
	 * @return
	 */
	@RequestMapping("/insertActiveHist.json")
	public String insertActiveHist(
			HttpSession session, 
			PersonActiveHistVO personActiveHistVO,
			Model model) {
		
		/*** TEST ***/
		loginManager.listActiveUsers();
		
		String no_person = (String) session.getAttribute("no_person");
		logger.info("no_person : "+no_person);
		if(StringUtil.isNotEmpty(no_person)){
		
			personActiveHistVO.setNo_person(no_person);
			
			//return personManager.insertActiveHist(personForm);
			ReturnClass returnClass = personManager.insertActiveHist((PersonActiveHistVO)SessionUtil.setUser(personActiveHistVO, session));
			model.addAttribute("message", returnClass.getMessage());
			model.addAttribute("result" , returnClass.getCd_result());
		}
		return "jsonView";
	}

	/**
	 * 비밀번호/지문 틀린 횟수 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 */
	@RequestMapping("/modifyPwdFailCnt.json")
	public String modifyPwdFailCnt(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		logger.info("modifyPwdFailCnt.json start");
		String no_person = (String) session.getAttribute("no_person");
		logger.info("no_person : "+no_person);
		personVO.setNo_person(no_person);
		ReturnClass returnClass = personManager.modifyPwdFailCnt((PersonVO)SessionUtil.setUser(personVO, session));
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		model.addAttribute("result" , returnClass.getCd_result());
		return "jsonView";
	}
	
	/**
	 * 지문인증설정(비밀번호입력)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameSecurityCodeFingerSet.crz")
	public String frameSecurityCodeFingerSet() {
		return "/person/frameSecurityCodeFingerSet";
	}
	
	/**
	 * 지문인증설정 (비밀번호확인)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/fingerChkCode.json")
	public String fingerChkCode(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {

		String pass_person 	= "";

		for(int i=0; i < personVO.getPass_number().size(); i++){
			pass_person += personVO.getPass_number().get(i);
		}

		String no_person = (String) session.getAttribute("no_person");
		personVO.setNo_person(no_person);
		personVO.setPass_person(pass_person);
		int pwdCheck = personManager.checkPersonPass(personVO);
		
		if(pwdCheck > 0) {	//암호화 비밀번호 체크
			personVO.setYn_fingerprint("Y");
			ReturnClass returnClass = personManager.modifyFingerPrint((PersonVO)SessionUtil.setUser(personVO, session));
			logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
			
			model.addAttribute("result", Constant.SUCCESS);
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("result", Constant.FAILED);
		}
		return "jsonView";
	}
	
	/**
	 * 지문 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 */
	@RequestMapping("/modifyFingerPrint.json")
	public String modifyFingerPrint(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		logger.info("modifyFingerPrint.json start");
		String no_person = (String) session.getAttribute("no_person");
		logger.info("no_person : "+no_person);
		personVO.setNo_person(no_person);
		ReturnClass returnClass = personManager.modifyFingerPrint((PersonVO)SessionUtil.setUser(personVO, session));
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		model.addAttribute("result" , returnClass.getCd_result());
		return "jsonView";
	}

	/**
	 * 이메일 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 */
	@RequestMapping("/modifyPersonEmail.json")
	public String modifyPersonEmail(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		
		logger.info("no_person : "+no_person);
		personVO.setNo_person(no_person);
		ReturnClass returnClass = personManager.modifyPersonEmail((PersonVO)SessionUtil.setUser(personVO, session));
		
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		
		model.addAttribute("result" , returnClass.getCd_result());
		model.addAttribute("message" , returnClass.getMessage());
		if(returnClass.getCd_result().equals("00")){
			model.addAttribute("email" , personVO.getEmail());
		}
		return "jsonView";
	}

	/**
	 * 로그아웃 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 */
	@RequestMapping("/framePersonLogout.crz")
	public String framePersonLogout(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		
		if(!StringUtil.isEmpty(no_person)) {
			
			personVO = personManager.getPersonInfo(no_person);
			
			logger.info("no_person : "+no_person);
			personVO.setNo_person(no_person);
			personVO.setYn_logout("Y");
			ReturnClass returnClass = personManager.modifyPersonLogout((PersonVO)SessionUtil.setUser(personVO, session));
			
			logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
			model.addAttribute("result" , returnClass.getCd_result());
			model.addAttribute("message" , returnClass.getMessage());
			model.addAttribute("hp" , personVO.getHp());
			
			session.invalidate();
		} else {
			
			Cookie[] cookies = request.getCookies();
			String hp = SessionUtil.getCookieValue(cookies, "hp");
			model.addAttribute("hp" , hp);
			
			logger.info("cookies : ", hp);
			
		}
		
		return "/person/framePersonLogout";
	}
	
	/**
	 * 푸쉬알림설정 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 */
	@RequestMapping("/modifyPushNoti.json")
	public String modifyPushNoti(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		logger.info("no_person : "+no_person);
		personVO.setNo_person(no_person);
		ReturnClass returnClass = personManager.modifyPushNoti((PersonVO)SessionUtil.setUser(personVO, session));
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		model.addAttribute("result" , returnClass.getCd_result());
		return "jsonView";
	}

	/**
	 * 로그아웃 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 * TODO 사용여부 확인 필요
	 */
	@RequestMapping("/modifyYnUseAndLogout.json")
	public String modifyYnUseAndLogout(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		logger.info("no_person : "+no_person);
		personVO.setNo_person(no_person);
		ReturnClass returnClass = personManager.modifyYnUseAndLogout((PersonVO)SessionUtil.setUser(personVO, session));
		
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		model.addAttribute("result" , returnClass.getCd_result());
		model.addAttribute("message" , returnClass.getMessage());
		
		return "jsonView";
	}
	
	/**
	 * 문자내역 저장 
	 * @param request
	 * @param model
	 * @param String
	 * @return
	 */
	@SkipLoginCheck
	@ResponseBody
	@RequestMapping("/createPersonSmsList.crz")
	@Transactional
	public String createSmsList(
			@RequestBody String data,
			Model model ) {
		
		JSONObject jsonObject = new JSONObject();
		Gson gson = new Gson();
		
		logger.debug("createSmsList.crz");
		logger.debug("sms data ::: " + data);
		
		String no_person = null;
		ReturnClass returnClass = null;
		PersonSmsListVO personSmsInfo = new PersonSmsListVO();
		List<PersonSmsListVO> personSmsList = null;
		
		personSmsInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), PersonSmsListVO.class);
		
		no_person 		= personSmsInfo.getNo_person();
		personSmsList 	= personSmsInfo.getSms_list();
		
		if(personSmsList != null && personSmsList.size() > 0)	{
			for (PersonSmsListVO personSmsListVO : personSmsList) {
				personSmsListVO.setNo_person(no_person);
				personSmsListVO.setId_frt(no_person);
			}
			returnClass = personManager.createPersonSmsList(personSmsInfo.getSms_list());
		} else {
			returnClass = new ReturnClass(Constant.SUCCESS);
		}
			
        logger.debug("SMS 내역 저장 returnClass  : " + returnClass.toString());
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());

        jsonObject.put("result", returnClass.getCd_result());
        model.addAttribute("result", returnClass.getCd_result());
        
        return jsonObject.toString();
	}
}