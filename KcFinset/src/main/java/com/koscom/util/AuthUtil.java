package com.koscom.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class AuthUtil {
    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);
    @Resource
    Environment environment;
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
}
