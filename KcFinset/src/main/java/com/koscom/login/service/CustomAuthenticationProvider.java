package com.koscom.login.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;

import com.koscom.util.AES256Util;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	LoginManager loginManager;
	
	@Autowired
	PersonManager personManager;
	
	@Autowired
	SecureManager secureManager;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		logger.info("password    : " + password);
		String seq_login = password.substring(0, 24);
		password = password.substring(24);
		logger.info("seq_login   : " + seq_login);
		logger.info("password    : " + password);
		
		PersonVO personVO = personManager.getPersonInfo(username);
		
		// AES 복호화 
		String hp = personVO.getHp();
		logger.info("hp : " + hp);
		String decValue = null;
		try	{
			AES256Util aes256 = new AES256Util(username+"."+hp);
			decValue = aes256.aesDecode(seq_login);
		}
		catch (Exception e) { 
			logger.error("복호화 처리 에러 : " + e.getMessage());
			e.printStackTrace();
			throw new BadCredentialsException("복호화 처리 에러");
		}
		
		if(!decValue.equals(personVO.getSeq_login().toString()))	{
			logger.error("로그인 시퀀스 에러  [ " + decValue + " : " + personVO.getSeq_login() + " ]");
			throw new BadCredentialsException("로그인 시퀀스 에러");
		}
		
		User user;
		Collection<? extends GrantedAuthority> authorities;
		try {
			user = (User)loginManager.loadUserByUsername(username);
			String hashedPassword = this.encode(password);
			
			logger.info(
					"username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
			logger.info("username : " + user.getUsername() + " / password : " + user.getPassword());
			
			if (!hashedPassword.equals(user.getPassword()))	{	
				logger.error("비밀번호 에러  [ " + hashedPassword + " : " + user.getPassword() + " ]");
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			}
			
			authorities = user.getAuthorities();
			
		} catch (UsernameNotFoundException e) {
			logger.info(e.toString());
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			logger.info(e.toString());
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			logger.info(e.toString());
			throw new RuntimeException(e.getMessage());
		}
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public String encode(String rawPassword) {

		if (rawPassword == null) {
			throw new NullPointerException();
		}
		String strPwdDB = "";
		
		try {
			String decPwd = rawPassword.toString();
			String type = "";
			if(decPwd.length() > 6) {
				type = decPwd.substring(0, 6);
			}
			
			if(!"userNotFoundPassword".equals(decPwd)) {
				
				if("999999".equals(type)) { //지문인증
					strPwdDB = decPwd.substring(6);
				} else {
					if(decPwd.length() > 5) { //Local
						decPwd = secureManager.getDecodedPassword(rawPassword.toString());
					}
					strPwdDB = personManager.getPwdDB(decPwd);
				}
			}
			
		} catch (RuntimeException e) {
			
		}

		return strPwdDB;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
