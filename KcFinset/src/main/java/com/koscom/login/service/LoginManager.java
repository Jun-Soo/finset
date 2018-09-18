package com.koscom.login.service;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.util.UrlPathHelper;

import com.koscom.credit.service.CreditManager;
import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.FcmUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.ResUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;

import net.sf.json.JSONObject;

public class LoginManager extends SavedRequestAwareAuthenticationSuccessHandler implements UserDetailsService,AuthenticationSuccessHandler,AuthenticationFailureHandler {

	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	CodeManager codeManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private CreditManager creditManager;
	
	@Autowired
	private KcbManager kcbManager;
	
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
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response, 
			AuthenticationException authenticationException) throws IOException, ServletException {
		
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
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String requestUri = urlPathHelper.getRequestUri(request);
		if(requestUri.indexOf(".crz") > -1) {
			
			response.sendRedirect(ResUtil.getPath(request) + "/m/login/frameSecurityCodeConfirm.crz?denied="+cd_result);
		} else {
			
			PrintWriter out 	= response.getWriter();
		    JSONObject	result	= new JSONObject();
		    result.put("result", cd_result);
			out.print(result); 
			out.flush(); 
			out.close();
		}
		
		
	}

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		String authorities = "";
		String cd_result = Constant.LOGIN_SUCCESS;
		
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
			
			//당일 크롤링 여부 조회
			HashMap<String, String> schMap = new HashMap<String, String>();
			schMap.put("sch_no_person", personVO.getNo_person());
			schMap.put("nm_if", 		"600420");
			schMap.put("nm_if_sub", 	"210");
			schMap.put("sch_time", 		"1");
			HashMap<String, String> clobMap = creditManager.getKcbInfoCLOB(schMap);
			
			try {
				
				//01. 크롤링 처리
				if(clobMap == null) {
					cd_result = kcbManager.getKcbCrawling(personVO);
				}
				
				//02. 로그인시 로직 처리
				cd_result = loginProcess(personVO, request);
				
				if(Constant.SUCCESS.equals(cd_result)) cd_result = Constant.LOGIN_SUCCESS;
			} catch (FinsetException e) {
				cd_result = Constant.FAILED;
			}
			
			// 자동스크래핑 여부 관련 설정
			session.setAttribute("AutoScrap", "true");
			
			UrlPathHelper urlPathHelper = new UrlPathHelper();
			String requestUri = urlPathHelper.getRequestUri(request);
			if(requestUri.indexOf(".crz") > -1) {
				
				String linkUrl = (String)session.getAttribute("linkUrl");
				if(!StringUtil.isEmpty(linkUrl)) session.removeAttribute("linkUrl");
				linkUrl = StringUtil.isEmpty(linkUrl) ? "/m/credit/frameCreditInfoMain.crz" : linkUrl;
			    
				response.sendRedirect(ResUtil.getPath(request) + linkUrl);
			} else {
				
				PrintWriter out 	= response.getWriter();
			    JSONObject	result	= new JSONObject();
			    result.put("result", 	cd_result);
			    result.put("userToken", session.getId());
			    //result.put("linkUrl", 	linkUrl);
			    
			    //사용자정보
			    result.put("nmPerson", 	personVO.getNm_person());
			    
				out.print(result); 
				out.flush(); 
				out.close();
			}
		}
	}
	
	public String loginProcess(PersonVO personVO, HttpServletRequest request) {
		
		String cd_result = Constant.SUCCESS;
		
		try {
			
			String      no_person   = personVO.getNo_person();
	        
			//모바일 접속 이력 insert
			PersonLoginHistInfo personLoginHist = new PersonLoginHistInfo();
			personLoginHist.setNo_person(no_person);
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
			personManager.insertPersonLoginHist(personLoginHist);
			
	        //최근 접속 이력 업데이트
	        personManager.modifyLastLogin(no_person);
	        
	        // 비밀번호&지문인증 틀린횟수 초기화
			personVO.setCnt_fail_mode("all");
			personVO.setNo_person(personVO.getNo_person());
			personVO.setCnt_fail(0);
			ReturnClass modifyPwdFailCntReturnClass = personManager.modifyPwdFailCnt(personVO);
			logger.info("cd_result : {},  message : {}", modifyPwdFailCntReturnClass.getCd_result(), modifyPwdFailCntReturnClass.getMessage());
			
	        //마이페이지 - 공유관리 업데이트요청
	        PersonShareInfoVO personShareInfoVO = new PersonShareInfoVO();
	        personShareInfoVO.setOffer_no_person(no_person);
	        List<PersonShareInfoVO> listPersonShareInfoReqUpdate = personManager.listPersonShareInfoReqUpdate(personShareInfoVO);
	        
	        //푸시발송
	        for(PersonShareInfoVO updateItem : listPersonShareInfoReqUpdate) {
				logger.info("공유관리 업데이트요청 push발송");
				
				 String title = "[공유관리]";
				 String body = "";
				 String url = "";
				 String fcm_token = "";
				 
				 //메세지 정보 셋팅
				 PersonShareMessageInfo personShareMessageInfo = new PersonShareMessageInfo();
				 personShareMessageInfo.setSeq_share(updateItem.getSeq_share());
				 personShareMessageInfo.setId_lst(no_person); //최종수정아이디
				
				 body = updateItem.getOffer_nm_person()+"님이 공유 정보를 업데이트 하였습니다.";
				 
				 personShareMessageInfo.setReq_status("03"); //응답
			     personShareMessageInfo.setRes_message(body); //응답메세지
				 
				 PersonVO recPersonVO = personManager.getPersonInfo(updateItem.getReq_no_person());
				    
				 if (recPersonVO != null) {
				     fcm_token = recPersonVO.getFcm_token();
				     if (fcm_token != null && !fcm_token.equals("")) {
				    	 logger.debug("@@@@SendTo())"+fcm_token);
		
				         if(FcmUtil.sendFcm(  fcm_token
				                 , title
				                 , body
				                 , url
				                 , StringUtil.nullToString(recPersonVO.getYn_os(), "1")
				                 , StringUtil.nullToString(recPersonVO.getCd_push(), ""))){
				         }
				         ReturnClass rtnClass = personManager.mergePersonShareInfoMessage(personShareMessageInfo);
				     }
				 }
			}
			
		} catch (Exception e) {
			LogUtil.error(logger, e);
			cd_result = Constant.FAILED;
		}
		return cd_result;
		
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
