package com.koscom.login.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.koscom.credit.service.CreditManager;
import com.koscom.env.service.CodeManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.ResUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;

public class LoginManager extends SavedRequestAwareAuthenticationSuccessHandler implements UserDetailsService,AuthenticationSuccessHandler,AuthenticationFailureHandler {

	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	CodeManager codeManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private CreditManager creditManager;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginManager.class);
	
	@Override
	public UserDetails loadUserByUsername(String no_person) throws UsernameNotFoundException {
		
		logger.debug("loadUserByUsername no_person :" + no_person);
		PersonVO personVO = personManager.getPersonInfo(no_person);
		if(personVO == null) {
			throw new UsernameNotFoundException("Wrong username");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        return new User(no_person, personVO.getPass_person(), authorities);
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		Authentication authentication = authenticationException.getAuthentication();
		
		String cd_result = "";
				
		//아이디로 사용자정보 가져오기
		PersonVO personVO = personManager.getPersonInfo(authentication.getName());
		
		//사용자 정보 존재 확인
		if(personVO == null){
			cd_result = Constant.LOGIN_ID_ERR;
			logger.warn("ID 오류 : "+authentication.getName()+" : "+request.getRemoteAddr());
		}else{
			cd_result =  Constant.LOGIN_PASS_ERR;
			session.setAttribute("SPRING_SECURITY_LAST_USERNAME", authentication.getName());	//ID 재입력 안하도록 처리
			logger.warn("PASS 오류 : "+authentication.getName()+" : "+request.getRemoteAddr());
		}
				
		//로그인실패 메시지 나오도록 리다이렉트
		response.sendRedirect(ResUtil.getPath(request) + "/m/login/frameSecurityCodeConfirm.crz?denied="+cd_result);
		
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		String authorities = "";
		String cd_result = ""; 
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			logger.debug("authority.toString():" + authority.toString());
			authorities = authority.toString();
		}
		
		//사용자 정보 조회
		PersonVO personVO = personManager.getPersonInfo(authentication.getName());
		
		if(personVO != null){
			
			String ynAgreeUsing = personManager.getYnAgreeUsingInfo(personVO.getNo_person());
			
			session.setAttribute("no_person", 		personVO.getNo_person());	
			session.setAttribute("ynAgreeUsing", 	ynAgreeUsing);	//ID 그랜티드받은 인증이름
			
			Cookie _hp = new Cookie("hp", personVO.getHp());
			_hp.setMaxAge(30*24*60*60);
			_hp.setPath("/");
			response.addCookie(_hp);
		}
		
		
		// 비밀번호&지문인증 틀린횟수 초기화
		personVO.setCnt_fail_mode("all");
		personVO.setNo_person(personVO.getNo_person());
		personVO.setCnt_fail(0);
		ReturnClass modifyPwdFailCntReturnClass = personManager.modifyPwdFailCnt((PersonVO)SessionUtil.setUser(personVO, session));
		logger.info("cd_result : {},  message : {}", modifyPwdFailCntReturnClass.getCd_result(), modifyPwdFailCntReturnClass.getMessage());

		//당일 크롤링 여부 조회
		HashMap<String, String> schMap = new HashMap<String, String>();
		schMap.put("sch_no_person", personVO.getNo_person());
		schMap.put("nm_if", 		"600420");
		schMap.put("nm_if_sub", 	"210");
		schMap.put("sch_time", 		"1");
		HashMap<String, String> clobMap = creditManager.getKcbInfoCLOB(schMap);
		
		cd_result = Constant.LOGIN_SUCCESS;
		
		String linkUrl = (String)session.getAttribute("linkUrl");
			   //linkUrl = StringUtil.isEmpty(linkUrl) ? "/m/credit/frameCreditInfoMain.crz" : linkUrl;
			   linkUrl = StringUtil.isEmpty(linkUrl) ? "/index.html" : linkUrl;
		
	    if(!StringUtil.isEmpty(linkUrl)) session.removeAttribute("linkUrl");
		if(clobMap == null) {
			linkUrl = URLEncoder.encode(linkUrl);
			response.sendRedirect(ResUtil.getPath(request) + "/m/login/frameKcbCrawling.crz?linkUrl="+linkUrl);
		} else {
			response.sendRedirect(ResUtil.getPath(request) + linkUrl);
		}
		// 자동스크래핑 여부 관련 설정
		session.setAttribute("AutoScrap", "true");
	}
	
	/**
	 * Method Desc : 활성화 된 사용자 목록
	 * 2012. 11. 7. bwko <bwko@crizen.com>
	 * @return
	 */
	public HashMap<Object,Date> listActiveUsers() {
		HashMap<Object,Date> lastActive = new HashMap<Object, Date>();
		for(Object principal : sessionRegistry.getAllPrincipals()){
			for(SessionInformation session : sessionRegistry.getAllSessions(principal, false)){
				if(lastActive.get(principal) == null){
					lastActive.put(principal, session.getLastRequest());
				}else{
					Date prevLastRequest = lastActive.get(principal);
					if(session.getLastRequest().after(prevLastRequest)){
						lastActive.put(principal, session.getLastRequest());
						/*** TEST ***/
						logger.error("ACTIVE USER ID : " + session.getSessionId());
					}
				}
			}
		}
		return lastActive;
	}
}
