package com.koscom.loginsocial.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.PersonLoginHist;
import com.koscom.loginsocial.dao.LoginSocialMapper;
import com.koscom.loginsocial.model.LoginSocialForm;
import com.koscom.loginsocial.model.LoginSocialVO;
import com.koscom.loginsocial.service.LoginSocialManager;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("loginSocialManager")
public class LoginSocialManagerImpl implements LoginSocialManager {

	private static final Logger logger = LoggerFactory.getLogger(LoginSocialManagerImpl.class);

	@Autowired
	private LoginSocialMapper loginSocialMapper;
	@Autowired
	private PersonManager personManager;
	
	@Override
	public ReturnClass procLoginSocial(LoginSocialVO loginSocialVO) {
		//먼저 소셜업체, 소셜아이디로 검색해서 있으면 그게 no_person이고 없으면 새로운 Max 값을 딴다
		LoginSocialVO socialExistVO = getPersonSocial(loginSocialVO);
		logger.debug("procLoginSocial" +socialExistVO);
		
		if(socialExistVO != null && !socialExistVO.getNo_person().equals("") && StringUtil.nullToString(socialExistVO.getYn_use()).equals("N")){
			loginSocialVO.setNo_person(socialExistVO.getNo_person());
			ReturnClass returnClass = new ReturnClass(Constant.FAILED,"탈퇴 이력이 존재합니다. 재사용을 원하시면 확인을 눌러주세요", (Object) loginSocialVO);
			returnClass.setReturnObj(loginSocialVO);
			return returnClass;
		}else if(socialExistVO != null && !socialExistVO.getNo_person().equals("") && !StringUtil.nullToString(socialExistVO.getYn_use()).equals("N")){
			String ynAgreeUsing = personManager.getYnAgreeUsingInfo(socialExistVO.getNo_person());
			if(ynAgreeUsing != null && ynAgreeUsing.equals("Y")){
				loginSocialVO.setNo_person(socialExistVO.getNo_person());
			}else{
				loginSocialVO.setNo_person(socialExistVO.getNo_person());
				ReturnClass returnClass = new ReturnClass(Constant.NOT_AGREE_USING,"이용약관 미동의", (Object) loginSocialVO);
				returnClass.setReturnObj(loginSocialVO);
				return returnClass;
			} 
		}else{
			loginSocialVO.setNo_person(getMaxNoPerson());
		}
		if(1 != loginSocialMapper.procLoginSocial(loginSocialVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) loginSocialVO);
		returnClass.setReturnObj(loginSocialVO);
		return returnClass;
	}
	@Override
	public String getMaxNoPerson() {
		return loginSocialMapper.getMaxNoPerson();
	}
	@Override
	public LoginSocialVO getPersonSocial(LoginSocialVO loginSocialVO) {
		return loginSocialMapper.getPersonSocial(loginSocialVO);
	}
	@Override
	public List<PersonLoginHist> listPersonLoginHist(LoginSocialForm loginSocialForm) {
		return loginSocialMapper.listPersonLoginHist(loginSocialForm);
	}
	@Override
	public int listPersonLoginHistCount(LoginSocialForm loginSocialForm) {
		return loginSocialMapper.listPersonLoginHistCount(loginSocialForm);
	}
	@Override
	public void insertPersonLoginHist(PersonLoginHist personLoginHist) {
		loginSocialMapper.insertPersonLoginHist(personLoginHist);
	}
}