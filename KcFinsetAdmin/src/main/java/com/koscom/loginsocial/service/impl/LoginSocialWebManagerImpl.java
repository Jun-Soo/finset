package com.koscom.loginsocial.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.PersonLoginHist;
import com.koscom.loginsocial.model.LoginSocialForm;
import com.koscom.loginsocial.model.LoginSocialVO;
import com.koscom.loginsocial.service.LoginSocialManager;
import com.koscom.loginsocial.service.LoginSocialWebManager;
import com.koscom.util.ReturnClass;

@Service("loginSocialWebManager")
public class LoginSocialWebManagerImpl implements LoginSocialWebManager{

	@Autowired
	private LoginSocialManager loginSocialManager;
	
	@Override
	public ReturnClass procLoginSocial(LoginSocialVO loginSocialVO) {
		return loginSocialManager.procLoginSocial(loginSocialVO);
	}

	@Override
	public void insertPersonLoginHist(PersonLoginHist personLoginHist) {
		loginSocialManager.insertPersonLoginHist(personLoginHist);
	}

	@Override
	public String getMaxNoPerson() {
		// TODO Auto-generated method stub
		return loginSocialManager.getMaxNoPerson();
	}

	@Override
	public LoginSocialVO getPersonSocial(LoginSocialVO loginSocialVO) {
		// TODO Auto-generated method stub
		return loginSocialManager.getPersonSocial(loginSocialVO);
	}

	@Override
	public List<PersonLoginHist> listPersonLoginHist(LoginSocialForm loginSocialForm) {
		// TODO Auto-generated method stub
		return loginSocialManager.listPersonLoginHist(loginSocialForm);
	}

	@Override
	public int listPersonLoginHistCount(LoginSocialForm loginSocialForm) {
		// TODO Auto-generated method stub
		return loginSocialManager.listPersonLoginHistCount(loginSocialForm);
	}
}
