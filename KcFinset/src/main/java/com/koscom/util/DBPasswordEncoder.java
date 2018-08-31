package com.koscom.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.koscom.person.service.PersonManager;

public class DBPasswordEncoder implements PasswordEncoder {

	@Autowired
	PersonManager personManager;

	@Override
	public String encode(CharSequence rawPassword) {

		if (rawPassword == null) {
			throw new NullPointerException();
		}

		String strPwdDB = "";
		try {
			if(!"userNotFoundPassword".equals(rawPassword) && rawPassword.length() < 5) {
				strPwdDB = personManager.getPwdDB(rawPassword.toString());
			} else {
				strPwdDB = rawPassword.toString();
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