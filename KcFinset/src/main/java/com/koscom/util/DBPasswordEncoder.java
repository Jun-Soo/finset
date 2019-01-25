package com.koscom.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.koscom.login.LoginController;
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
			String decPwd = ""; 
			//받은 비밀번호가 4자리보다 클경우는 보안키보드 암호화가 된 케이스이 때문에 보안키보드 복호화 처리 -- 추후 조건처리 삭제
			if(rawPassword.length() > 4){
				decPwd = secureManager.getDecodedPassword(rawPassword.toString());
			}
			else	{
				decPwd = rawPassword.toString();
						
			}
			
			if(!"userNotFoundPassword".equals(decPwd) && decPwd.length() < 5) {
				strPwdDB = personManager.getPwdDB(decPwd);
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