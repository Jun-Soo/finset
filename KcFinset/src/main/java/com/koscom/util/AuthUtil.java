package com.koscom.util;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);

    /**
     * 접근 경로 제어
     * 여러개의 페이지에서 접근 할경우 사용하며,
     * 접근 대상 경로를 배열로 파라메터를 지정한다.
     * @param request
     * @param pArrPrePage
     * @return
     */
    public static boolean isHaveAuth(HttpServletRequest request, String[] pArrPrePage,Environment environment) {
        boolean isHaveAuth = false;
        if(pArrPrePage != null) {
            for(int i=0;i<pArrPrePage.length;i++) {
                isHaveAuth = isHaveAuth(request,pArrPrePage[i],environment);
                if(isHaveAuth == true) {
                    break;
                }
            }
        }
        return isHaveAuth;
    }
    /**
     * 현재페이지에 접근한 경로는 prePage 이어야만하다.
     * session 에 pre_page 라는 key 로 들어있는 값이 현재 페이지 인경우만 접근허가한다.
     * @param request
     * @param pPrePage 이전페이지
     * @return
     */
    public static boolean isHaveAuth(HttpServletRequest request, String pPrePage,Environment environment) {

        boolean isHaveAuth = false;
        /**
         * 세션에 이전 화면 값을 가져온다.
         */
        pPrePage = (pPrePage == null)? "":pPrePage;
        /**
         * 세션에 현재페이지 경로를 추가한다.
         */
        String curUrl = request.getServletPath();
        String referer = request.getHeader("referer");
        String log = "SessionUtil.isHaveAuth:\nreferer="+referer+"\npPrePage="+pPrePage+"\ncurUrl="+curUrl;
        LogUtil.debugLn(logger,log);
        String site = (environment != null)?environment.getProperty("service.profile"):"";
        LogUtil.debugLn(logger,"SessionUtil.isHaveAuth:site="+site);
        if(referer != null && referer.contains(pPrePage)) {
            isHaveAuth = true;
        }
        /**
         * 로컬, 개발인경우 접근제어 안함.
         */
        else if(!"REAL".equals(site)){
            isHaveAuth = true;
        }
        LogUtil.debugLn(logger,"SessionUtil.isHaveAuth:site="+site+",isHaveAuth="+isHaveAuth);
        return isHaveAuth;
    }
    
    public void isSessionAuth(HttpSession session) {
    	
    	this.isSessionAuth((String)session.getAttribute("no_person"));
    }
    
    public boolean isSessionAuth(String no_person) {
    	
    	// 시큐리티 컨텍스트 객체를 얻습니다. 
    	SecurityContext context = SecurityContextHolder.getContext(); 
    	// 인증 객체를 얻습니다. 
    	Authentication authentication = context.getAuthentication(); 
    	// 로그인한 사용자정보를 가진 객체를 얻습니다. 
    	Principal principal = (Principal) authentication.getPrincipal(); 
    	// 사용자가 가진 모든 롤 정보를 얻습니다. 
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
    	Iterator<? extends GrantedAuthority> iter = authorities.iterator(); 
    	while (iter.hasNext()) { 
    		GrantedAuthority auth = iter.next(); 
    		System.out.println(auth.getAuthority()); 
    	}
    	
    	return true;
    }
}
