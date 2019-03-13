package com.koscom.login.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.koscom.domain.WorkerInfo;
import com.koscom.domain.WorkerLoginHist;
import com.koscom.env.service.IpManager;
import com.koscom.util.Constant;
import com.koscom.util.SecurityReqFilter;
import com.koscom.util.SpringApplicationContext;
import com.koscom.worker.service.WorkerManager;

public class LoginManager extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler,AuthenticationFailureHandler {

	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	WorkerManager workerManager;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginManager.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		Authentication authentication = authenticationException.getAuthentication();
		String cd_result = "";
		
		WorkerManager workerManager = (WorkerManager) SpringApplicationContext.getBean("workerManager");
		WorkerInfo workerInfo = workerManager.getWorkerInfo(authentication.getName());
		
		if(authentication.getName() == null || authentication.getName() == ""){
			cd_result = "100";
			logger.warn("ID 미입력 : "+authentication.getName()+" : "+request.getRemoteAddr());
			response.sendRedirect(request.getContextPath() + "/login/login.crz?denied="+cd_result);
		} else if(request.getParameter("j_password") == null || request.getParameter("j_password") == ""){
			cd_result = "101";
			session.setAttribute("SPRING_SECURITY_LAST_USERNAME", authentication.getName());	//ID 재입력 안하도록 처리
			logger.warn("PW 미입력 : "+authentication.getName()+" : "+request.getRemoteAddr());
			response.sendRedirect(request.getContextPath() + "/login/login.crz?denied="+cd_result);
		} else {
		
		//사용자 정보 존재 확인
		if(workerInfo == null){
			cd_result = Constant.LOGIN_ID_ERR;
			logger.warn("ID 오류 : "+authentication.getName()+" : "+request.getRemoteAddr());
		}else{
			cd_result =  Constant.LOGIN_PASS_ERR;
			session.setAttribute("SPRING_SECURITY_LAST_USERNAME", authentication.getName());	//ID 재입력 안하도록 처리
			logger.warn("PASS 오류 : "+authentication.getName()+" : "+request.getRemoteAddr());
		}
		
		//DB에 히스토리 저장
		WorkerLoginHist workerLoginHist = new WorkerLoginHist();
		workerLoginHist.setCd_system(Constant.AG);
		workerLoginHist.setCd_result(cd_result);
		workerLoginHist.setId_emp(authentication.getName());
		workerLoginHist.setIp_server(request.getLocalAddr());
		String ip = request.getHeader("X-FORWARDED-FOR"); 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr() ;
		}
		workerLoginHist.setIp_client(ip);
		workerManager.insertWorkerLoginHist(workerLoginHist);
		
		//로그인실패 메시지 나오도록 리다이렉트
		response.sendRedirect(request.getContextPath() + "/login/login.crz?denied="+cd_result);
		}
		
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		HttpSession session = request.getSession();
		WebAuthenticationDetails webDetail = new WebAuthenticationDetails(request);
		
//		boolean isAllowedIpAddress = false;
		boolean isAllowedIpAddress = true;
		String ipAddr = webDetail.getRemoteAddress();
		String authorities = "";

//		// IP 주소 체크에서 통과하지 못했을 경우,
		String cd_result = "";
			logger.info("사용자 로그인 성공 : "+ authentication.getName() +" : "+ webDetail.getRemoteAddress());
			logger.info("request.getContextPath() : "+ request.getContextPath());
			cd_result = Constant.LOGIN_SUCCESS;
		 
			// ID와 PASS 가 같으면 강제로 비밀번호 변경 화면으로 이동
			WorkerManager workerManager = (WorkerManager) SpringApplicationContext.getBean("workerManager");
			WorkerInfo workerInfo = workerManager.getWorkerInfo(authentication.getName());
			if( workerManager.getMD5Pass(workerInfo.getId_emp()).equals(workerInfo.getPass_emp()) )
			{	
				// 필터에 차단할 ID 를 추가하여 다른페이지로 이동하지 못하게 차단
				SecurityReqFilter securityReqFilter = (SecurityReqFilter) SpringApplicationContext.getBean("securityReqFilter");
				securityReqFilter.setBlockUser(workerInfo.getId_emp());
				response.sendRedirect(request.getContextPath()+"/changePasswd.crz");
			} else {
				response.sendRedirect(request.getContextPath()+"/");
				
			}
			
//		}
		
		//DB에 히스토리 저장
		WorkerLoginHist workerLoginHist = new WorkerLoginHist();
		workerLoginHist.setCd_system(Constant.AG);
		workerLoginHist.setCd_result(cd_result);
		workerLoginHist.setId_emp(authentication.getName());
		workerLoginHist.setAuthority(authorities);
		workerLoginHist.setIp_server(request.getLocalAddr());
		String ip = request.getHeader("X-FORWARDED-FOR"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr() ;
	    }
	    logger.info(ip);
		workerLoginHist.setIp_client(ip);
		workerManager.insertWorkerLoginHist(workerLoginHist);
		
//		super.onAuthenticationSuccess(request,response,authentication);
		
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
					}
				}
			}
		}
		return lastActive;
	}
	
}
