package com.koscom.person.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.person.dao.PersonMapper;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonShareInfoForm;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonSmsListVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("personManager")
@Transactional
public class PersonManagerImpl implements PersonManager {

	private static final Logger logger = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private PersonMapper personMapper;

	@Override
	public PersonVO getPersonInfoHp(String hp) {
		return personMapper.getPersonInfoHp(hp);
	}

	@Override
	public PersonVO getPersonInfo(String no_person) {
		return personMapper.getPersonInfo(no_person);
	}

	@Override
	public ReturnClass modifyPwdFailCnt(PersonVO personVO) {
		if(1 != personMapper.modifyPwdFailCnt(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public String getYnAgreeUsingInfo(String no_person) {
		return personMapper.getYnAgreeUsingInfo(no_person);
	}

	@Override
	public String getPwdDB(String pwd) {
		return personMapper.getPwdDB(pwd);
	}

	@Override
	public int checkPersonPass(PersonVO personVO) {
		return personMapper.checkPersonPass(personVO);
	}

	@Override
	public ReturnClass modifyFcmToken(PersonVO personVO) {
		if(1 != personMapper.modifyFcmToken(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPassPerson(PersonVO personVO) {
		//비밀번호 수정
		if(1 != personMapper.modifyPassPerson(personVO)) {
			logger.info("비밀번호 수정 처리에 실패하였습니다. 다시 시도해주세요 personVO :" + personVO.toString());
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		logger.info("비밀번호 수정 정상 처리 하였습니다. personVO :" + personVO.toString());
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public void modifyLastLogin(String no_person) {
		personMapper.modifyLastLogin(no_person);
	}

	@Override
	public void insertPersonLoginHist(PersonLoginHistInfo personLoginHist) {
		personMapper.insertPersonLoginHist(personLoginHist);

	}

	@Override
	public ReturnClass insertActiveHist(PersonActiveHistVO personActiveHistVO) {
		if(1 != personMapper.insertActiveHist(personActiveHistVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyFingerPrint(PersonVO personVO) {
		if(1 != personMapper.modifyFingerPrint(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPersonEmail(PersonVO personVO) {

		String email = personVO.getEmail();

		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);

        if( !m.matches() ) {
        	return new ReturnClass(Constant.FAILED, "올바른 E-mail 형식이 아닙니다.");
        }

		if(1 != personMapper.modifyPersonEmail(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPersonLogout(PersonVO personVO) {
		if(1 != personMapper.modifyPersonLogout(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPushNoti(PersonVO personVO) {

		int result = 0;

		//person정보 update
		result = personMapper.modifyPushNoti(personVO);

		//알림설정정보 insert,update
		if("each".equals(personVO.getType_push())){
			result = personMapper.modifyCdPush(personVO);
			result = personMapper.insertCdPushHist(personVO);
		}

		if(1 != result) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyYnUseAndLogout(PersonVO personVO) {
		//로그아웃, 사용여부 수정
		if(1 != personMapper.modifyYnUseAndLogout(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public String getLastPersonSmsDt(String no_person) {
		return personMapper.getLastPersonSmsDt(no_person);
	}

	@Override
	public ReturnClass createPersonSmsList(List<PersonSmsListVO> list) {

		logger.info("SMS 수신함 이력 저장 시작");
		int cnt_tot = 0;
		int cnt_fail = 0;
		cnt_tot = list.size();

		for (PersonSmsListVO personSmsListVO : list) {
			if ( 1 != personMapper.createPersonSmsList(personSmsListVO) ) {
				logger.info("SMS 수신이력 저장 실패");
				logger.debug( personSmsListVO.toString() );
				cnt_fail++;
			}
		}

		return new ReturnClass(Constant.SUCCESS, String.format("정상적으로 처리되었습니다. [ 전체: %1$d 건 / 성공: %2$d 건 / 실패: %3$d 건 ]", cnt_tot, cnt_tot-cnt_fail, cnt_fail));
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoSummary(PersonShareInfoForm personShareInfoForm) {
		return personMapper.listPersonShareInfoSummary(personShareInfoForm);
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoMain(PersonShareInfoForm personShareInfoForm) {
		return personMapper.listPersonShareInfoMain(personShareInfoForm);
	}
	@Override
	public int listPersonShareInfoMainCount(PersonShareInfoForm personShareInfoForm) {
		return personMapper.listPersonShareInfoMainCount(personShareInfoForm);
	}

	@Override
	public List<PersonShareInfoVO> listShareInfoAllCancel(PersonShareInfoVO personShareInfoVO) {
		return personMapper.listShareInfoAllCancel(personShareInfoVO);
	}

	@Override
	public PersonShareInfoVO getPersonShareInfo(PersonShareInfoVO personShareInfoVO) {
		return personMapper.getPersonShareInfo(personShareInfoVO);
	}

	@Override
	public PersonShareInfoVO getPersonShareEtmInfo(PersonShareInfoVO personShareInfoVO) {
		return personMapper.getPersonShareEtmInfo(personShareInfoVO);
	}

	@Override
	public ReturnClass createPersonShareInfo(PersonShareInfo personShareInfo) {
		String seq_share = personMapper.getPersonShareInfoSeq();

		personShareInfo.setSeq_share(seq_share);
		if(1 != personMapper.createPersonShareInfo(personShareInfo)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		personMapper.createPersonShareInfoHist(personShareInfo);
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) seq_share);
	}

	@Override
	public ReturnClass updatePersonShareInfoSet01(PersonShareInfo personShareInfo) {
		if(1 != personMapper.duplChkPersonShareInfo(personShareInfo)){ //변경할 내용이 있는지 체크
			if(1 != personMapper.updatePersonShareInfoSet01(personShareInfo)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}
		}

		return new ReturnClass(Constant.SUCCESS,"공유 재요청 되었습니다.", (Object) personShareInfo.getSeq_share());
	}

	@Override
	public ReturnClass updatePersonShareInfoSet02(PersonShareInfo personShareInfo) {
		String mode_nm = "";
		if("02".equals(personShareInfo.getShare_status())){
			mode_nm = "허용";
		}else if("03".equals(personShareInfo.getShare_status())){
			mode_nm = "거절";
		}

		if(1 != personMapper.updatePersonShareInfoSet02(personShareInfo)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		personMapper.createPersonShareInfoHist(personShareInfo);
		return new ReturnClass(Constant.SUCCESS, mode_nm+" 되었습니다.", (Object) personShareInfo.getSeq_share());
	}

	@Override
	public ReturnClass updatePersonShareInfoSet03(PersonShareInfo personShareInfo) {
		String mode_nm = "";
		if("02".equals(personShareInfo.getShare_status())){
			mode_nm = "변경";
		}else if("03".equals(personShareInfo.getShare_status())){
			mode_nm = "해지";
		}

		if("02".equals(personShareInfo.getShare_status()) && 1 == personMapper.duplChkPersonShareInfo(personShareInfo)){
			return new ReturnClass(Constant.FAILED,"변경할 내용이 없습니다.");
		}else{
			if(1 != personMapper.updatePersonShareInfoSet03(personShareInfo)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}
		}

		personMapper.createPersonShareInfoHist(personShareInfo);
		return new ReturnClass(Constant.SUCCESS, mode_nm+" 되었습니다.", (Object) personShareInfo.getSeq_share());
	}

	@Override
	public int chkPersonShareInfoMessageTerm(PersonShareMessageInfo personShareMessageInfo) {
		return personMapper.chkPersonShareInfoMessageTerm(personShareMessageInfo);
	}

	@Override
	public ReturnClass mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo) {
			if(1 != personMapper.mergePersonShareInfoMessage(personShareMessageInfo)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}

		return new ReturnClass(Constant.SUCCESS,"정상처리하였습니다.", (Object) personShareMessageInfo.getSeq_share());
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoReqUpdate(PersonShareInfoVO personShareInfoVO) {
		return personMapper.listPersonShareInfoReqUpdate(personShareInfoVO);
	}
}
