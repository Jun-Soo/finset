package com.koscom.login.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.koscom.worker.service.WorkerManager;

public class LoginWebManager extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler,AuthenticationFailureHandler {


	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	WorkerManager workerManager;

	@Autowired
	LoginManager loginManager;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginWebManager.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
		loginManager.onAuthenticationFailure(request, response, authenticationException);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		loginManager.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * Method Desc : 활성화 된 사용자 목록
	 * 2012. 11. 7. bwko <bwko@crizen.com>
	 * @return
	 */
	public HashMap<Object,Date> listActiveUsers() {
		return loginManager.listActiveUsers();
	}
}
