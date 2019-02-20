package com.koscom.util;

import java.io.IOException;
import java.util.Arrays;
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
      
      //requestUri 는 /m/board/getBoardImg.json와 같은 형태
      String [] uriArr = requestUri.split("/");
      if(uriArr.length != 4) {
         //위와 같은 형식이 아니라면 처리 제외
         filterChain.doFilter(request, response);
         return;
      } else {
         String cd_info = ""; // 01: 부채, 02: 자산, 03: 소비, 04: 달력(부채, 소비)
         switch (uriArr[2]) {
         case "debt":
            if(uriArr[3].toLowerCase().contains("calendar")) {
               cd_info = "04";
            } else {
               cd_info = "01";
            }
            break;
         case "assets":
            cd_info = "02";
            break;
         case "consume":
            cd_info = "03";
            break;
         default:
            break;
         }
         // 세션에 저장된 no_person
         String ssNoPerson = (String)request.getSession().getAttribute("no_person");
         // 파라미터로 넘어온 no_person
         String param_no_person = request.getParameter("no_person");
         // no_person_list[] 로 넘어온 조회 리스트
         String [] param_no_person_list = request.getParameterValues("no_person_list[]");
         if(param_no_person_list == null) {
            // post 방식의 경우 [] 가 떨어져서 들어온다
            param_no_person_list = request.getParameterValues("no_person_list");
         }
         // person_share_list[] 로 넘어온 조회 리스트
         String [] param_person_share_list = request.getParameterValues("person_share_list[]");
         if(param_person_share_list == null) {
            param_person_share_list = request.getParameterValues("person_share_list");
         }
         
         // 위변조가 되지 않았는지 여부
         boolean isSecure1 = true;
         boolean isSecure2 = true;
         boolean isSecure3 = true;
         
         // 각 파라미터가 존재 하는지에 확인 후 각각 확인
         if(param_no_person != null && !param_no_person.isEmpty()) {
            logger.debug("param_no_person: " + param_no_person);
            isSecure1 = (Constant.SUCCESS == SessionUtil.chkNoPerson(ssNoPerson, param_no_person, cd_info));
         } 
         if(param_no_person_list != null) {
            for(String str: param_no_person_list) {
               logger.debug("param_no_person_list: "+ str);
            }
            if(!(param_no_person_list.length == 1 && param_no_person_list[0].isEmpty()))	{
            	isSecure2 = (Constant.SUCCESS == SessionUtil.chkNoPersonList(ssNoPerson, Arrays.asList(param_no_person_list), cd_info));
            }
         } 
         if(param_person_share_list != null) {
            for(String str:param_person_share_list) {
               logger.debug("param_person_share_list: "+ str);
            }
            if(!(param_person_share_list.length == 1 && param_person_share_list[0].isEmpty()))	{
            	isSecure3 = (Constant.SUCCESS == SessionUtil.chkNoPersonList(ssNoPerson, Arrays.asList(param_person_share_list), cd_info));
            }
         }
         logger.debug("isSecure1: "+isSecure1);
         logger.debug("isSecure2: "+isSecure2);
         logger.debug("isSecure3: "+isSecure3);
         if(!isSecure1 || !isSecure2 || !isSecure3) {
            throw new ServletException("회원번호 변조가 감지되었습니다.");
         } else {
            filterChain.doFilter(request, response);
            return;
         }
      }
   }
   
   private HashMap<String, String> hmBlock = new HashMap<String, String>();
      
   public void setBlockUser(String id_emp) {
      hmBlock.put(id_emp, id_emp);
   }
   
   public void removeBlockUser(String id_emp) {
      hmBlock.remove(id_emp);
   }

}