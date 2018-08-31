package com.koscom.util;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.koscom.env.service.CodeManager;

public class SecurityReqFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityReqFilter.class);

	@Autowired
	private CodeManager codeManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String requestUri = urlPathHelper.getRequestUri(request);
		String queryString = StringUtil.NVL(urlPathHelper.getOriginatingQueryString(request), "");
		if(requestUri.indexOf(".crz") > -1 && queryString.indexOf("hp") > -1) {
			
			final String hp = request.getParameter("hp");
			request.getSession().setAttribute("linkUrl", requestUri + "?" + queryString);
			request.getSession().setAttribute("hp", hp);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private HashMap<String, String> hmBlock = new HashMap<String, String>();
		
	public void setBlockUser(String id_emp) {
		hmBlock.put(id_emp, id_emp);
	}
	
	public void removeBlockUser(String id_emp) {
		hmBlock.remove(id_emp);
	}

}
