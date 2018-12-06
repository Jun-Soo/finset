package com.koscom.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 파일명	: SessionLogging.java
 * 기능설명 : 세션 자동 유지 기능
 * 작성자	: HSJ
 * 작성일자 : 2017.06.07
 * 
 */
@Component
public class SessionLogging extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionLogging.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		Cookie[] cookies = request.getCookies();
		
		HttpSession session = request.getSession();
		String strHandle = handler.toString();
		logger.info("handler : {}", handler);
		
		boolean isAjax = false;					// AJAX 통신 여부
		
		if(isAjaxRequest(request)){
			isAjax = true;
		}
		logger.info("isAjax : {}", isAjax);
		
		// 쿠키 정보 디버깅
		if(cookies != null) {
			for(Cookie cook: cookies) {
				logger.info("name : {}, value : {} ",cook.getName(), cook.getValue());
			}
		}
		
		// 로그아웃시와 최초접속시 쿠키 정보 삭제
		if(strHandle != null && (strHandle.indexOf("frameCertStep1") > 0)) {
			logger.info(">>>>>>>>>>>>>>>>> 로그아웃 요청");
			session.setAttribute("expiredTime", "");
			session.setAttribute("no_person", "");
			session.setAttribute("rememberMe", "N");
			removeCookiesInfo(response, cookies);
			session.invalidate();
		} else {
			logger.info(">>>>>>>>>>>>>>>>> check ");
			//@SkipLoginCheck 어노테이션이 컨트롤러에 사용되었는지 체크함
			SkipLoginCheck usingLoginCheck = null;
			if (handler != null && handler instanceof HandlerMethod) {
				usingLoginCheck = ((HandlerMethod) handler).getMethodAnnotation(SkipLoginCheck.class);
			}
	 
			if(usingLoginCheck == null) {
				//SkipLoginCheck 어노테이션이 없음으로 무조건 로그인 체크
				//로그인 페이지가 아니면 세션 체크 하여 세션 갱신등 필요
				if(strHandle!= null && strHandle.indexOf(".login") < 0) {
					
					String noPerson = (String) session.getAttribute("no_person");
					logger.info("noPerson Session :  " + noPerson);
					if(StringUtil.isEmpty(noPerson)) { // 세션에 값이 없다면
						noPerson = getCookieValue(cookies, "noPerson");
						if(StringUtil.isEmpty(noPerson)) { // 쿠키에도 값이 없다면 로그 아웃 시킨다.
							logger.info("noPerson Cookie :  " + noPerson);
							if (response != null && cookies != null) {
								removeCookiesInfo(response, cookies);
							}
							response.sendRedirect("/logOut");
							return true;
						} else { // 세션X 쿠키O WAS재기동O 쿠키에서 꺼내서 세션에 담아준다.
							logger.info("Cookie O :  " + noPerson);
							String _expiredTime = getCookieValue(cookies, "expiredTime");
							String _noPerson = getCookieValue(cookies, "noPerson");
							String rememberMe = getCookieValue(cookies, "rememberMe");
							
							logger.info("세션 X 쿠키 O _expiredTime : {}" + _expiredTime);
							logger.info("noPerson   : {}" + _noPerson);
							logger.info("rememberMe : {}" + rememberMe);
							
							session.setAttribute("expiredTime", _expiredTime);
							session.setAttribute("no_person", _noPerson);
							session.setAttribute("rememberMe", rememberMe);
						}
					} else { // 세션X 쿠키O WAS재기동O 쿠키에서 꺼내서 세션에 담아준다.
						logger.info("Session O :  " + noPerson);
					}
					
					String _rememberMe = getCookieValue(cookies, "rememberMe");
					logger.info("_rememberMe : {}", _rememberMe);
					
					if(_rememberMe == null) _rememberMe = "N";
					
					String expiredTime = (String) session.getAttribute("expiredTime");
					logger.info("expiredTime  : " + expiredTime);
					
					if(_rememberMe.equals("Y")) {	 // 자동 로그인 유지
						
						logger.info("자동로그인 사용중");
						String currentDateTime = DateUtil.getCurrentDateTime(DateUtil.DATE_HMS_PATTERN);
						logger.info(">>>  현재시간(currentDateTime): " + currentDateTime + ", 세션 만료 시간(expiredTime): " + expiredTime);
						long maxInactiveInterval = DateUtil.getDiffMin(currentDateTime, expiredTime); // 남은시간(초)
						logger.info("남은시간 : " + maxInactiveInterval);
						if(maxInactiveInterval/60/24 < 14 && maxInactiveInterval > 0) {
							logger.info(">>>  expiredTime 14일 이하");
							String _expiredTime =  DateUtil.addHours(currentDateTime, 720);
							session.setAttribute("expiredTime", _expiredTime);
							session.setMaxInactiveInterval(Constant.SESSION_MAX_PERIOD);
						} else if(maxInactiveInterval <= 0) {
							logger.info(">>>  expiredTime 만료");
							if(isAjax) { 
								response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // ajax 에러
							} else {	 
								removeCookiesInfo(response, cookies);
								response.sendRedirect("/logOut"); // 로그아웃
							}
						}
						
					} else {							
						logger.info(">>> 자동 로그인이 off 인 상태 ");
						ReturnClass returnClass = checkSessionTime(response, cookies, session);
						String cd_result = "";
						if (returnClass != null) {
                            cd_result = returnClass.getCd_result();
                            if(cd_result != null) {
                                if(cd_result.equals(Constant.SUCCESS)) { 		// 세션 시간이 10분이내로 남아  갱신 되었다.
                                    session.setMaxInactiveInterval(Constant.SESSION_MAX_PERIOD_NOT_LOGIN);
                                } else if(cd_result.equals(Constant.FAILED)) { // 세션 끊긴 이후에 접근시
                                    if(isAjax) {
                                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);// ajax 에러
                                    } else {
                                        removeCookiesInfo(response, cookies);
                                        response.sendRedirect("/logOut"); // 로그아웃
                                    }
                                    return false;
                                } else if(cd_result.equals(Constant.NOTNEED)) {
                                    logger.info("nothing to do 세션 시간이 10분 이상 남아있다.");
                                }
                            }
						}
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 세션 남은 시간 체크
	 * @param response
	 * @param cookies
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private ReturnClass checkSessionTime(HttpServletResponse response, Cookie [] cookies, HttpSession session) throws Exception {
		ReturnClass returnClass = new ReturnClass(Constant.NOTNEED);
		// 세션 남은 시간 체크
		String currentDateTime = DateUtil.getCurrentDateTime(DateUtil.DATE_HMS_PATTERN);
		String sessionExpiredTime = (String) session.getAttribute("expiredTime");						//세션 expired 타임
		
		if(StringUtil.isEmpty(sessionExpiredTime)) {
			return new ReturnClass(Constant.FAILED);
		}
		
		long toExpired = DateUtil.getDiffMin(currentDateTime, sessionExpiredTime)*60;
		logger.info("expireInterval = {}", session.getMaxInactiveInterval());
		logger.info("expired - current = {}", toExpired);
		
		if(toExpired < 600 && toExpired > 0) {
			// 세션이 10분이내로 남았을 경우  갱신
			logger.info(">>> 세션이 10분이내로 남아 있어 갱신");
			String expiredTime = DateUtil.addHours(currentDateTime, 2);
			session.setAttribute("expiredTime", expiredTime);
//			Cookie _expiredTime = new Cookie("expiredTime", expiredTime);
//			_expiredTime.setMaxAge(60 * 60 * 2);
//			_expiredTime.setPath("/");
//			response.addCookie(_expiredTime);
			returnClass = new ReturnClass(Constant.SUCCESS, "세션 시간 갱신");
		} else if(toExpired <= 0) {
			// 세션 시간이 초과 되었을 경우
			logger.info(">>> 세션 시간이 초과");
			returnClass = new ReturnClass(Constant.FAILED);
		}
		
		return returnClass;
	}
	
	private boolean isAjaxRequest(HttpServletRequest req) {
        return req.getHeader(Constant.ajaxHeader) != null && req.getHeader(Constant.ajaxHeader).equals(Boolean.TRUE.toString());
	}

	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 쿠키 삭제
	 * @param response
	 * @param cookies
	 */
	private void removeCookiesInfo(HttpServletResponse response, Cookie[] cookies) {
		logger.info("쿠키삭제 1");
		String cookName = null;
		if(cookies != null) {
			logger.info("쿠키삭제 2");
			for(Cookie cook: cookies) {
			    if(cook != null) {
			        cookName = cook.getName();
				    logger.info("쿠키삭제 3  : " + cookName);
                    if (cookName != null) {
                        if(cookName.equals("rememberMe") || cookName.equals("noPerson") || cookName.equals("expiredTime")) {
                            cook.setPath("/");
                            cook.setMaxAge(0);
                            response.addCookie(cook);
                            logger.info("쿠키삭제 4  : ");
                        }
                    }
                }
            }
		}
	}
	
	/**
	 * 해당 쿠키의 key 값을 받아 value를 반환다.
	 * @param cookies
	 * @param key
	 * @return
	 */
	private String getCookieValue(Cookie[] cookies, String key) {
		if(cookies == null)
			return "";
		
		for(Cookie cook: cookies) {
			if(cook.getName().equals(key)) {
				return cook.getValue();
			}
		}
		return "";
	}
	

}
