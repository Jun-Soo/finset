package com.koscom.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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
}
