package com.koscom.loginsocial.service;

import java.util.List;

import com.koscom.domain.PersonLoginHist;
import com.koscom.loginsocial.model.LoginSocialForm;
import com.koscom.loginsocial.model.LoginSocialVO;
import com.koscom.util.ReturnClass;

public interface LoginSocialManager {
	ReturnClass procLoginSocial(LoginSocialVO loginSocialVO);
	String getMaxNoPerson();
	LoginSocialVO getPersonSocial(LoginSocialVO loginSocialVO);
	
	/**
	 * 로그인 이력 저장
	 * @param personLoginHist
	 */
	public void insertPersonLoginHist(PersonLoginHist personLoginHist);
	public List<PersonLoginHist> listPersonLoginHist(LoginSocialForm loginSocialForm);
	public int listPersonLoginHistCount(LoginSocialForm loginSocialForm);
	
}
