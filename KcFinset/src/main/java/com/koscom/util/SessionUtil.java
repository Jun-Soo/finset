package com.koscom.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.koscom.domain.PersonShareInfo;
import com.koscom.person.service.PersonManager;

public final class SessionUtil {

	private String Authority;
	private String ID;

	public SessionUtil() {};

	public SessionUtil(HttpServletRequest request) {

		HttpSession session = request.getSession();

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication == null)
			return;

		for (GrantedAuthority authority : authentication.getAuthorities()) {
			this.Authority = authority.toString();
		}

		this.ID = authentication.getName();

		if(session.getAttribute("no_person") == null || session.getAttribute("no_person").equals("anonymousUser")) {
			session.setAttribute("no_person", this.ID);
		}
	}

	public String getUserId() {
		return this.ID;
	}

	public String getAuthority() {
		return this.Authority;
	}

	public static Object setUser(Object vo, HttpServletRequest request) {

		SessionUtil session = new SessionUtil(request);
		try {

			Class c = vo.getClass();

			try {
				Method m = c.getMethod("setId_frt", String.class);
				if(m != null) {
					m.invoke(vo, session.getUserId());
				}
				m = c.getMethod("setId_lst", String.class);
				if(m != null) {
					m.invoke(vo, session.getUserId());
				}
			} catch (NoSuchMethodException e) {
				//e.printStackTrace();
			}

			try {
				Method m = c.getMethod("setIdFrt", String.class);
				if(m != null) {
					m.invoke(vo, session.getUserId());
				}
				m = c.getMethod("setIdLst", String.class);
				if(m != null) {
					m.invoke(vo, session.getUserId());
				}
			} catch (NoSuchMethodException e) {
				//e.printStackTrace();
			}

		} catch (IllegalAccessException e) {
			//e.printStackTrace();
		} catch (IllegalArgumentException e) {
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			//e.printStackTrace();
		}

		return vo;
	}

	public static Object setUser(Object vo, HttpSession session) {

		try {

			Class c = vo.getClass();

			try {
				Method m = c.getMethod("setId_frt", String.class);
				if(m != null) {
					m.invoke(vo, (String) session.getAttribute("no_person"));
				}
				m = c.getMethod("setId_lst", String.class);
				if(m != null) {
					m.invoke(vo, (String) session.getAttribute("no_person"));
				}
			} catch (NoSuchMethodException e) {
				//e.printStackTrace();
			}

			try {
				Method m = c.getMethod("setIdFrt", String.class);
				if(m != null) {
					m.invoke(vo, (String) session.getAttribute("no_person"));
				}
				m = c.getMethod("setIdLst", String.class);
				if(m != null) {
					m.invoke(vo, (String) session.getAttribute("no_person"));
				}
			} catch (NoSuchMethodException e) {
				//e.printStackTrace();
			}

		} catch (IllegalAccessException e) {
			//e.printStackTrace();
		} catch (IllegalArgumentException e) {
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			//e.printStackTrace();
		}

		return vo;
	}

	/**
	 * 해당 쿠키의 key 값을 받아 value를 반환다.
	 * @param cookies
	 * @param key
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String key) {
		if(cookies == null)
			return "";

		for(Cookie cook: cookies) {
			if(cook.getName().equals(key)) {
				return cook.getValue();
			}
		}
		return "";
	}

	 /**
	 * 회원번호(공유자여부) 체크
	 */
	public static String chkNoPerson(String ssNoPerson, String frmNoPerson, String cdInfo){
		String cdResult = "00";
		if(!frmNoPerson.equals(ssNoPerson)){
			PersonShareInfo personShareInfo = new PersonShareInfo();
			personShareInfo.setCd_info(cdInfo);
			personShareInfo.setReq_no_person(ssNoPerson);
			personShareInfo.setOffer_no_person(frmNoPerson);
			ReturnClass personShareRtnClass = new ReturnClass();
			PersonManager personManager = (PersonManager) SpringApplicationContext
					.getBean("personManager");
			if (personManager != null) {
				personShareRtnClass = personManager.checkSharePerson(personShareInfo);
				cdResult = personShareRtnClass.getCd_result();
			}else{
				cdResult = "01";
			}
		}

		return cdResult;
	}
	
	 /**
	    * 회원번호(공유자여부) 리스트 체크
	    * @param ssNoPerson
	    * @param frmNoPersonList
	    * @param cd_info
	    * @return
	    */
	   public static String chkNoPersonList(String ssNoPerson, List<String> frmNoPersonList, String cdInfo) {
	      String cdResult = "00";
	      for(String frmNoPerson: frmNoPersonList) {
	         cdResult = chkNoPerson(ssNoPerson, frmNoPerson, cdInfo);
	         if(!"00".equals(cdResult)){
	            return cdResult;
	         }
	      }
	      return cdResult;
	   }

}
