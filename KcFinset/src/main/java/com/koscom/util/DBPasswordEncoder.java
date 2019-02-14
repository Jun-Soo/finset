package com.koscom.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.koscom.login.service.SecureManager;
import com.koscom.person.service.PersonManager;

public class DBPasswordEncoder implements PasswordEncoder {

	@Autowired
	PersonManager personManager;
	
	@Autowired
	SecureManager secureManager;

	@Override
	public String encode(CharSequence rawPassword) {

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
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || rawPassword == null) {
			return false;
		}

		if (!encodedPassword.equals(encode(rawPassword))) {
			return false;
		}

		return true;
	}
}