package com.koscom.loginsocial.dao;

import java.util.List;

import com.koscom.domain.PersonLoginHist;
import com.koscom.loginsocial.model.LoginSocialForm;
import com.koscom.loginsocial.model.LoginSocialVO;

public interface LoginSocialMapper {
	int procLoginSocial(LoginSocialVO loginSocialVO);
	String getMaxNoPerson();
	LoginSocialVO getPersonSocial(LoginSocialVO loginSocialVO);
	
	/**
	 * 로그인 이력 저장
	 * @param personLoginHist
	 */
	void insertPersonLoginHist(PersonLoginHist personLoginHist);
	List<PersonLoginHist> listPersonLoginHist(LoginSocialForm loginSocialForm);
	int listPersonLoginHistCount(LoginSocialForm loginSocialForm);
}